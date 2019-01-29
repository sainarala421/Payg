package SeleniumTestAutomation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;
import org.jpos.iso.BaseChannel;
import org.jpos.iso.ISOField;
import org.jpos.iso.ISOMUX;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.channel.ATHISO87ABASE24Packager;
import org.jpos.iso.channel.SVBNISO87ABASE24Packager;



import org.jpos.iso.channel.BASE24Channel;
import org.jpos.iso.channel.LRC_ISO87APackager;
import org.jpos.iso.channel.LRISO87BPackager;
import org.jpos.iso.channel.LRP_ISO87APackager;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;
import SeleniumTestAutomation.SimulatorConfigurationDataTransferObjects;
import SeleniumTestAutomation.RequestMessageTimeInterval;
import org.jpos.iso.header.BaseHeader;



public class callSimulator
{
	static String messHeader, simulatorPort, simulatorIP;
	public static void connectChannel(String inCsvfile,String packageName) throws IOException
	{
		Properties properties = new Properties();
		properties.load(new FileInputStream(Configure.configFilePath));
		simulatorIP=properties.getProperty(packageName+"_simulatorIP");
		simulatorPort=properties.getProperty(packageName+"_simulatorPort");
		System.out.println("IP for the Simulator ---> " + simulatorIP);
		System.out.println("Port for the Simulator ---> " + simulatorPort);
		SimulatorConfigurationDataTransferObjects SCDTO = readConfigFile();
		ISOPackager packager = getMessagePackager(packageName);
		BaseChannel channel = null;
		if((packageName.equalsIgnoreCase("ATH")) || (packageName.equalsIgnoreCase("SVBN")))
		{
			System.out.println("Packager ---> " + packager);
			channel=createOldNACChannel(packager, simulatorIP, simulatorPort);
		}
		else
		{
			System.out.println("Packager ---> " + packager);
			channel = createNACChannel(packager, simulatorIP, simulatorPort);
		}
		CMRequestGenerator reqGenerator = new CMRequestGenerator(channel,SCDTO, packager);
		
		if(channel!=null && channel.isConnected())
			channel.disconnect();
		
		//new Thread(reqGenerator).start();
	}
	private static ISOPackager getMessagePackager(String packName)
	{
		ISOPackager packager = null;
		if (packName.equalsIgnoreCase("LR_C"))
		{
			packager=new LRC_ISO87APackager();
			messHeader = "49534f313233343536373839";			
		}
		if (packName.equalsIgnoreCase("LR"))
		{
			packager=new LRISO87BPackager();
			messHeader = "6000030000";			
		}
		else if (packName.equalsIgnoreCase("LR_P"))
		{
			packager = new LRP_ISO87APackager();
			messHeader = "015000055";
		}
		else if (packName.equalsIgnoreCase("ATH"))
		{
			packager = new ATHISO87ABASE24Packager();
			messHeader = "ISO014000050";
		}
		else if (packName.equalsIgnoreCase("SVBN"))
		{
			packager = new SVBNISO87ABASE24Packager();
			messHeader = "ISO014000055";
		}
		return packager;
	}
	private static org.jpos.iso.channel.BASE24Channel createBase24Channel(ISOPackager packager,String IP, String port) throws FileNotFoundException 
	{
		org.jpos.iso.channel.BASE24Channel channel = null;
		System.out.println("Message header ---> " + messHeader);
		if (messHeader != null && messHeader.length() > 0)
		{
			channel = new BASE24Channel(IP,Integer.parseInt(port), packager);
			channel.setHeader(messHeader.trim());
		}
		else
		channel = new BASE24Channel(IP,Integer.parseInt(port), packager);
		ISOMUX mux = new ISOMUX(channel);
		PrintStream outputFileStream = new PrintStream(new FileOutputStream(TestCase.outLogFile));
		Logger logger = new Logger();
		logger.addListener(new SimpleLogListener(outputFileStream));
		channel.setLogger(logger, "channel");
		mux.setLogger(logger, "mux");
		Thread t = new Thread(mux);
		t.start();
		return channel;
	}
	public static org.jpos.iso.channel.NACChannel createNACChannel(ISOPackager packager,String IP, String port) throws IOException
	{
		System.out.println("Message header ---> " + messHeader);
		org.jpos.iso.channel.NACChannel channel = null;
		channel = new org.jpos.iso.channel.NACChannel(IP,Integer.parseInt(port), packager,ISOUtil.hex2byte(messHeader));
		//ISOMUX mux = new ISOMUX(channel);
		PrintStream outputFileStream = new PrintStream(new FileOutputStream(TestCase.outLogFile));
		Logger logger = new Logger();
		logger.addListener(new SimpleLogListener(outputFileStream));
		channel.setLogger(logger, "channel");
		//mux.setLogger(logger, "mux");
		//Thread t = new Thread(mux);
		//t.start();
		//channel.disconnect();
		return channel;
		
	}
	public static org.jpos.iso.channel.NACChannel createOldNACChannel(ISOPackager packager,String IP, String port)throws IOException
	{
		    org.jpos.iso.channel.NACChannel channel = null;
		    System.out.println("Message header ---> " + messHeader);
			BaseHeader bh = new BaseHeader(messHeader.getBytes());
			channel = new org.jpos.iso.channel.NACChannel(IP,Integer.parseInt(port),packager,bh.pack());
			PrintStream outputFileStream = new PrintStream(new FileOutputStream(TestCase.outLogFile));
			Logger logger = new Logger();
			logger.addListener(new SimpleLogListener(outputFileStream));
			channel.setLogger(logger, "channel");
		return channel;
	}
	private static SimulatorConfigurationDataTransferObjects readConfigFile()
	{
		SimulatorConfigurationDataTransferObjects SCDTO=new SimulatorConfigurationDataTransferObjects();
		String requestDataFileName=TestCase.inCSVFile;
		SCDTO.setRequestDataFileName(requestDataFileName);
		return SCDTO;		
	}
}
class CMRequestGenerator implements Runnable
{
	private BaseChannel channel;
	private SimulatorConfigurationDataTransferObjects SCDTO;
	private ISOPackager packager;
	public CMRequestGenerator(BaseChannel nacChannel,SimulatorConfigurationDataTransferObjects SCDTO, ISOPackager isoPackager) 
	{
		this.channel = nacChannel;
		this.SCDTO = SCDTO;
		this.packager = isoPackager;
		ArrayList reqMsgList =readDataFile();
		limitedRequest(reqMsgList);
	}
	public void run() 
	{
		ArrayList reqMsgList =readDataFile();
		limitedRequest(reqMsgList);
	}
	private void limitedRequest(ArrayList reqMsgList) 
	{
		try 
		{
			RequestMessageTimeInterval msgCVO = null;
			Object reqObj = null;
			ISOMsg msg = null;
			for (int cnt = 0; cnt < reqMsgList.size(); ++cnt) 
			{
				reqObj = reqMsgList.get(cnt);
				if (reqObj == null)
					continue;
				msgCVO = (RequestMessageTimeInterval) reqObj;
				Thread.sleep(msgCVO.getTimeInterval());
				msg = msgCVO.getISOMsg();
				System.out.println("Request Sent ....");
				if (msg == null) 
				{
					continue;
				}
				msg.setPackager(this.packager);
				if (!(this.channel.isConnected())) 
				{
					this.channel.connect();
					System.out.println("Channels connection ---> " + this.channel.isConnected());
				}
				
				this.channel.send(msg);
				
				ISOMsg res = this.channel.receive(); 
				
				
				
			}
			msg.setPackager(null);
			
			//channel.disconnect();
		} 
		
		catch (Exception e) 
		{
			System.err.println("Cannot connect to channels");
			e.printStackTrace();
		}
	}
	private ArrayList readDataFile() 
	{
		BufferedReader bufRdr = null;
		ArrayList msgList = new ArrayList();
		try 
		{
			File file = new File(TestCase.inCSVFile);
			bufRdr = new BufferedReader(new FileReader(file));
			String line = null;
			int row = 0;
			int col = 0;
			String reqtimeinterval = "";
			int timeInterval = 0;
			while ((line = bufRdr.readLine()) != null) 
			{
				reqtimeinterval = "";
				timeInterval = 0;
				ISOMsg msg = new ISOMsg();
				StringTokenizer st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) 
				{
					String msgFieldData = st.nextToken();
					if ((msgFieldData != null) && (msgFieldData.length() > 0)) 
					{
						int indexOfSemiColon = msgFieldData.indexOf(":");
						String fieldId = msgFieldData.substring(0,indexOfSemiColon);
						String fieldData = msgFieldData.substring(indexOfSemiColon + 1, msgFieldData.length());
						if ((fieldId != null) && (fieldId.length() > 0)	&& (fieldId.equalsIgnoreCase("Time"))) 
						{
							reqtimeinterval = fieldData;
						} 
						else 
						{
							msg.set(new ISOField(Integer.parseInt(fieldId),fieldData));
						}
					}
					++col;
				}
				if ((reqtimeinterval != null) && (reqtimeinterval.length() > 0)) 
				{
					timeInterval = Integer.parseInt(reqtimeinterval);
				}
				msgList.add(new RequestMessageTimeInterval(timeInterval, msg));
				col = 0;
				++row;
			}
		} 
		catch (Exception ex) 
		{
			System.err.println("Caannot read input file : " + ex.getMessage());
		}
		finally
		{
			if (bufRdr != null)
			{
				try
				{
					bufRdr.close();
				} 
				catch (IOException ioex) 
				{
					System.err.println("Exception occured while closing Data File"+ ioex.getMessage());
				}
			}
		}
		return msgList;
	}
}