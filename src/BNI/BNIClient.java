package BNI;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Random;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import SeleniumTestAutomation.Configure;
import SeleniumTestAutomation.SeleniumFW;

import com.aircls.common.MCipherPad;

import common.SimulatorConst;

public class BNIClient {
	String messageNbr="";
	String ipAddress;
	String subPrefix 	= "";
	String bniTransIdForReversal = "";
	String netId 		= "";
	String reqAmt = "";
	String telnum = "";
	String btrnNo = "";
	Socket 			s		= null;
	BufferedInputStream input = null;
	BufferedOutputStream output = null;
	int port= 16600;
	String message="";
	String response="";
	String bankTransId="";
	String value="";
	String request= null;
	String carrrnum ="";
	String request1;
	String msgCount="";
	public static String BNIResponce=null;
	public static String BNIRequest=null;
	public static boolean isAlpha = true;
	public static boolean printFile = true;
	public static boolean flag = false;
	BufferedInputStream input1= null;
	BufferedOutputStream output1= null;
	String printResponse= "";
	public static Random rand = new Random();
	bniISOField simField3; 
	bniISOField simField2;
	String realpath="";
	String hostName = "";
	String operatorIdentity="";
	static String simulatorIP;
	static String simulatorPort;
	static String outLogFile;
	public static String Atmresponse=null;
	String response1=null;
	public  bniISOField isoField[]=new bniISOField[129];


	public BNIClient(String realpath1) {

		this.realpath = realpath1;
	}

	public ArrayList<String> createMessage(BNIBean bniBean,int msgCount,String Operatoridentity,String packageName,String TCID,String transactionName) {
		@SuppressWarnings("unused")
		ArrayList<?> msglist = new ArrayList<Object>();
		ArrayList<String> responsereq = new ArrayList<String>();
		
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(Configure.configFilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		simulatorIP=properties.getProperty(packageName+"_simulatorIP");
		simulatorPort=properties.getProperty(packageName+"_simulatorPort");
		//simulatorPort=Integer.parseInt(properties.getProperty(packageName+"_simulatorPort"));
		System.out.println("IP for the Simulator ---> " + simulatorIP);
		System.out.println("Port for the Simulator ---> " + simulatorPort);
		outLogFile=Configure.outputFilePath+"\\"+TCID+".log";
		System.out.println("outLogfile--->:" +outLogFile);
		bniBean.setIpAddress(simulatorIP);
		bniBean.setPort(simulatorPort);
		//bniBean.setPortNo(simulatorPort);
		
		
		
		/*this.msgCount = msgcnt;
		int noOfIterations;

		if(msgCount!=null)
		{
			noOfIterations = Integer.parseInt(msgCount);
		}
		else{
			noOfIterations = 1;
		}
*/
		try
		{
			//msglist=request1;

			/*if(!check)
			{
				ipAddress = (String)msglist.get(0);//ipAddress
				port = Integer.parseInt((String)msglist.get(1));//port
				
				netId = (String)msglist.get(2);//NetworkId
				messageNbr = (String)msglist.get(3);//messageNumber 002(Recharge) 004(Reversal)
				bankTransId = (String)msglist.get(4);//bankTransId(A Randam Number)
				

				value=(String)msglist.get(6);//amount
				subPrefix = (String)msglist.get(7);//mobileNumber
				carrrnum = (String)msglist.get(8);//reversa carrrnum
				operatorIdentity=(String)msglist.get(9);//OPERATORIDENTITY 00(Movistar) 01(Banana)

			}
*/
			s = new Socket(bniBean.getIpAddress(), Integer.parseInt(bniBean.getPort()));
			s.setTcpNoDelay(true);
			input	= new BufferedInputStream(s.getInputStream());
			output	= new BufferedOutputStream(s.getOutputStream());
			validateRequest(bniBean);
			String requestResponse = "";

			System.out.println("message count"+msgCount);
			for(int count=0;count<msgCount;count++)
			{
				response=sendReqToServer();
				
				showResponse(response);
				
				SeleniumFW.APPLICATION_LOGS.info("BNI Response:::"+response);
				writeToSuccessLog(outLogFile,response);
				BNIResponce=response;
				requestResponse = request +"$"+ response; 
				//printResponse = print(response,host);
				
				responsereq.add(requestResponse);
				System.out.println("printing the request and response from bniclient**"+requestResponse);	

			}
		} catch (Exception exp) {
			System.out.println(" CreateMessage:	 " + exp);
		}					
		return responsereq;

	}
	
	public static void writeToSuccessLog(String outLogFile,String response)
	{
		PrintStream outputFileStream=null;
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//String outputFileName=sdf.format(new java.util.Date())+remoteClient+".log";
		//String filePath=logPath+SimulatorConst.FILE_SEPARATOR+outputFileName;
		//System.out.println("logPath: "+logPath);
		//System.out.println("filePath: "+filePath);
		try {
			//System.out.println("FilePath: "+filePath);
			outputFileStream = new PrintStream(new FileOutputStream(outLogFile,true));
			outputFileStream.println("-------------------"+new java.util.Date()+"----------------------");
			outputFileStream.println();
			outputFileStream.println("RESPONSE : "+response);
			//System.out.println("RESPONSE :  "+message.getResponse());
		} catch (FileNotFoundException e) {
			System.out.println("The FileNotFoundException is in writeToLog() :  "+e);
		}
   }
	
	public String createMessage(ArrayList<String> request1,String hostName,String serverIP,String serverPort) {
		ArrayList<String> msglist = new ArrayList<String>();
		String reqRes = "";
		try
		{

			msglist=request1;
			{				
				messageNbr = (String)msglist.get(0);
				bankTransId = (String)msglist.get(1);
				netId = (String)msglist.get(2);
				value = (String)msglist.get(4);
				subPrefix = (String)msglist.get(6);
				ipAddress = serverIP;
				port = Integer.parseInt(serverPort);

			}

			s = new Socket(ipAddress, port);
			s.setTcpNoDelay(true);
			input	= new BufferedInputStream(s.getInputStream());
			output	= new BufferedOutputStream(s.getOutputStream());
			boolean status = validateRequest(null);
			if(status)
			{
				response=sendReqToServer();
				showResponse(response);

				print(response,hostName);				
				reqRes = request +"$"+ response;

				//System.out.println("request In Create Message"+request);

			}
		} catch (Exception exp) {
			System.out.println(" CreateMessage:from the  " + exp);
			return "ConnExp";

		}

		return reqRes;

	}

	@SuppressWarnings("resource")
	public String print(String response,String host)
	{
		String filePath = "";
		java.util.Properties properties = new java.util.Properties();
		PrintStream outputFileStream=null;
		try {

			properties.load(new java.io.FileInputStream(realpath+SimulatorConst.FILE_SEPARATOR+SimulatorConst.IN_FILE_DIR_PATH+"LogConfiguration.properties"));
			filePath = properties.getProperty("bni_log_path")+SimulatorConst.FILE_SEPARATOR;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String outputFileName = "01-04-2006";
			System.out.println("Filepath******"+filePath );
			message = response;

			if(outputFileName.equals(sdf.format(new java.util.Date())))
			{
				filePath = filePath+outputFileName+host+".log";
				outputFileStream = new PrintStream(new FileOutputStream(filePath,true));
			}
			else
			{
				outputFileName=sdf.format(new java.util.Date())+host+".log";
				filePath = filePath+outputFileName;
				outputFileStream = new PrintStream(new FileOutputStream(filePath,true));
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		outputFileStream.println("-------------------"+new java.util.Date()+"----------------------\n");
		outputFileStream.println("Request :");
		outputFileStream.println(request);
		outputFileStream.println();
		outputFileStream.println("\n RESPONSE :  ");
		outputFileStream.println(message);
		outputFileStream.println();

		return request;
	}

	public boolean validateRequest(BNIBean bniBean)
	{

		if(bniBean.getMessageNumber().equals(BNIConstants.BNI01$ENQUIRY))
			buildForEnquiry();
		else if(bniBean.getMessageNumber().equals(BNIConstants.BNI02$REPLENISHMENT))
			buildForReplenishment(bniBean);
		else if(bniBean.getMessageNumber().equals(BNIConstants.BNI03$ECHO))
			buildForEcho();
		else if(bniBean.getMessageNumber().equals(BNIConstants.BNI04$REVERSAL))
			buildForReversal(bniBean);
		else if(bniBean.getMessageNumber().equals(BNIConstants.BNI05$TRANSACTIONSTATUS))
			buildForTransactionStatus();                 
		else
		{

			return false;
		}
		return true;
	}


	public void buildForEnquiry()
	{
	
		try
		{
			BNIMessage bniRequest = new BNIMessage("", BNIConstants.REQUEST);
			bniRequest.appendItem(BNIConstants.OPERATORIDENTITY, operatorIdentity);

			bniRequest.appendItem(BNIConstants.MSGNUM, BNIConstants.BNI01$ENQUIRY);
			bniRequest.appendItem(BNIConstants.BTRNNO, bankTransId);
			bniRequest.appendItem(BNIConstants.NETWID, netId);
			bniRequest.appendItem(BNIConstants.TERMID, "333");
			bniRequest.appendItem(BNIConstants.REQAMT, value);
			bniRequest.appendItem(BNIConstants.TELNUM, subPrefix);

			if(netId !=null && !netId.equals("UNIBANCO"))
			{
				request = BNIUtil.getURLEncodedString(bniRequest.getMessage());
			}
			else
				request = bniRequest.getMessage();
			System.out.println("BNI REQUEST::" + request);

		}
		catch (Exception exp)
		{
			System.out.println("Error In buildValidateTelephonicValue : " + exp.getMessage());
		}

	}

	public void buildForReplenishment(BNIBean bniBean)
	{
		try
		{
			BNIMessage bniRequest = new BNIMessage("", BNIConstants.REQUEST);
			

			bniRequest.appendItem(BNIConstants.MSGNUM, BNIConstants.BNI02$REPLENISHMENT);
			bniRequest.appendItem(BNIConstants.BTRNNO, generateMsgId());
			bniRequest.appendItem(BNIConstants.NETWID, bniBean.getNetworkId());
			bniRequest.appendItem(BNIConstants.TERMID, "1");
			bniRequest.appendItem(BNIConstants.REQAMT, bniBean.getRechargeAmount());
			bniRequest.appendItem(BNIConstants.TELNUM, bniBean.getMobileNumber());//GenRandomData.getSubscriberId(subPrefix)
			bniRequest.appendItem(BNIConstants.OPERATORIDENTITY, bniBean.getOperatorIdentity());
			if(bniBean.getNetworkId() !=null && !bniBean.getNetworkId().equals("UNIBANCO"))
			{
				request = getURLEncodedString(bniRequest.getMessage());

			}
			else
			{
				request = bniRequest.getMessage();
							}
			SeleniumFW.APPLICATION_LOGS.info("BNI Request:::"+request);
		}
		catch (Exception exp)
		{
			System.out.println("Error In buildForReplenishment : " + exp.getMessage());
		}

	}
	@SuppressWarnings("deprecation")
	public static String getURLEncodedString(String message) {

		return URLEncoder.encode(message);

	}

	@SuppressWarnings("deprecation")
	public static String getURLDecodedString(String message) {

		return URLDecoder.decode(message);
	}

	public static byte[] encipherTextAlpha(String plainStr)
	{
		
		try
		{
			MCipherPad.encipherTextAlpha(BNIConstants.encKey, plainStr);
		}
		catch (Exception exp)
		{
		}


		return plainStr.getBytes();
	}// END -- public static byte[] encipherTextAlpha(String plainStr)

	public static String decipherTextAlpha(byte[] encStr)
	{
		String decStr="";
		try
		{
			decStr = MCipherPad.decipherTextAlpha(BNIConstants.encKey, encStr);
		}
		catch (Exception exp)
		{
		}

		return decStr;
	}// END -- public static String decipherTextAlpha(byte[] encStr)

	public static String encipherTextHexa(String plainStr)
	{
		String encStr = "";
		try
		{
			encStr = MCipherPad.encipherTextHexa(BNIConstants.encKey, plainStr);

		}
		catch (Exception exp)
		{
		}

		return encStr;
	}// END -- public static String encipherTextHexa(String plainStr)

	public static String decipherTextHexa(String encStr)
	{
		String decStr = "";
		try
		{
			decStr = MCipherPad.decipherTextHexa(BNIConstants.encKey ,encStr);
		}
		catch (Exception exp)
		{
		}

		return decStr;
	}// END -- public static String decipherTextHexa(String encStr)


	public void buildForEcho()
	{
		try
		{
			BNIMessage bniRequest = new BNIMessage("", BNIConstants.REQUEST);
			bniRequest.appendItem(BNIConstants.MSGNUM, BNIConstants.BNI03$ECHO);
			bniRequest.appendItem(BNIConstants.BTRNNO, bankTransId);
			bniRequest.appendItem(BNIConstants.CTRNNO, "");
			bniRequest.appendItem(BNIConstants.NETWID, netId);
			bniRequest.appendItem(BNIConstants.REQAMT, "10");


			if(netId !=null && !netId.equals("UNIBANCO"))
			{
				request = getURLEncodedString(bniRequest.getMessage());
			}
			else
			{
				request = bniRequest.getMessage();
			}
		}	
		catch (Exception exp)
		{
			System.out.println("Error In buildForEcho : " + exp.getMessage());
		}
	}

	public void buildForReversal(BNIBean bniBean)
	{
		try
		{
			BNIMessage bniRequest = new BNIMessage("", BNIConstants.REQUEST);

			bniRequest.appendItem(BNIConstants.OPERATORIDENTITY, bniBean.getOperatorIdentity());

			bniRequest.appendItem(BNIConstants.MSGNUM, BNIConstants.BNI04$REVERSAL);
			bniRequest.appendItem(BNIConstants.BTRNNO, bniBean.getBankTransId());
			bniRequest.appendItem(BNIConstants.CTRNNO, bniBean.getReversaCarrrNum());
			bniRequest.appendItem(BNIConstants.NETWID, bniBean.getNetworkId());
			bniRequest.appendItem(BNIConstants.REQAMT, bniBean.getRechargeAmount());
			bniRequest.appendItem(BNIConstants.TELNUM, bniBean.getMobileNumber());

			if(bniBean.getNetworkId() !=null && !bniBean.getNetworkId().equals("UNIBANCO")){
				request = getURLEncodedString(bniRequest.getMessage());
			}
			else{
				request = bniRequest.getMessage();
			}
			SeleniumFW.APPLICATION_LOGS.info("BNI Reversal request:::" + request);
			}
		catch (Exception exp)
		{
			System.out.println("Error In buildForEcho : " + exp.getMessage());
		}

	}

	public void buildForTransactionStatus()
	{

		try
		{
			BNIMessage bniRequest = new BNIMessage("", BNIConstants.REQUEST);
			bniRequest.appendItem(BNIConstants.OPERATORIDENTITY, operatorIdentity);

			bniRequest.appendItem(BNIConstants.MSGNUM, BNIConstants.BNI05$TRANSACTIONSTATUS);
			bniRequest.appendItem(BNIConstants.BTRNNO, bankTransId);
			bniRequest.appendItem(BNIConstants.NETWID, netId);
			bniRequest.appendItem(BNIConstants.TERMID, "333");
			bniRequest.appendItem(BNIConstants.REQAMT, value);


			if(netId !=null && !netId.equals("UNIBANCO"))
			{
				request = getURLEncodedString(bniRequest.getMessage());
			}
			else
				request = bniRequest.getMessage();

		}
		catch (Exception exp)
		{
			System.out.println("Error In buildForTransactionStatus : " + exp.getMessage());
		}
	}

	private String readMessage() {
		String message = "";

		byte[] chrStr = new byte[2000];
		try {
			while(true) {
				int msgExists = input.read(chrStr,0,1999);
				if(msgExists!=-1)
				{
					byte [] actualStr = new byte[msgExists];
					for(int index=0; index<msgExists; index++)
						actualStr[index] = chrStr[index];
					message = new String(actualStr);

					break;
				}
				else
					break;
			}// END -- Inner while(true)

		} catch (IOException exp) {

			System.out.println("EXP: at Server in receiveBCGData(BufferedReader): " + exp);
		}
		return message;
	}// END -- private String readMessage(BufferedReader inputBr)


	public String sendReqToServer() {
		try
		{

			byte [] encByteArray = null;

			if(isAlpha)
			{
				encByteArray = encipherTextAlpha(request);
				output.write(encByteArray,0,encByteArray.length);
				output.flush();
			}
			else
			{
				if(netId !=null && !netId.equals("UNIBANCO"))
				{
					request = encipherTextHexa(request);
					output.write(encByteArray);
					output.flush();
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(">>>>>>>>>>BNIClient :Exception sendReqToServer() >>"+ex);
		}
		message = readMessage(); 
		return message;
	}

	public Hashtable<?, ?> showResponse(String resp) {


		Hashtable<?, ?> msgHt = null;
		try {

			if (resp == null || resp.trim().length()==0) {

				System.out.println("NO RESPONSE RECEIVED FROM SERVER.");
				return new Hashtable<Object, Object>();
			}
			BNIMessage bniResponse = new BNIMessage(resp , BNIConstants.RESPONSE);
			msgHt = bniResponse.getBNIMessageHash();


		} catch (Exception exp) {

			System.out.println("AirERSIVRClientThread :: showResponse(String resp)");
		}
		return msgHt;
	}


	public ArrayList<String> readInputXMLFile(String filename,String realpath,String hostName,String serverIP,String serverPort)
	{

		ArrayList<String> msgList;
		ArrayList<String> reqres = new ArrayList<String>();
		String fieldName = "";
		String fileName = "";
		String path = "";
		String requestResponse = "";

		fileName = filename;
		path = realpath+SimulatorConst.IN_FILE_DIR_PATH;

		this.hostName = hostName;

		msgList = new ArrayList<String>();

		String XMLToParse = path+fileName;
		DOMParser parser = new DOMParser();

		try
		{
			parser.parse(XMLToParse);
			Document document = parser.getDocument();
			NodeList reqList = document.getElementsByTagName("Request");
			for(int x=0;x<reqList.getLength();x++)
			{
				ISOFieldObject fieldobj=null;
				ArrayList<ISOFieldObject> fields_of_request = new ArrayList<ISOFieldObject>();
				Node reqNode = reqList.item(x);
				NodeList fnodes = reqNode.getChildNodes();
				for(int c=0;c < fnodes.getLength();c++)
				{
					Node fieldNode =  fnodes.item(c);
					if(fieldNode != null)
					{
						NodeList nlist = fieldNode.getChildNodes();
						fieldName = "";
						String fieldval = "";
						for (int i = 0; i < nlist.getLength(); i++)
						{
							Node childNode = nlist.item(i);
							if(childNode.getChildNodes().getLength()!=0){
								NodeList children = childNode.getChildNodes();
								Node child = children.item(0);
								if (child.getNodeType() == Node.TEXT_NODE)
								{
									if(childNode.getNodeName().equalsIgnoreCase("FieldName")){
										fieldName = child.getNodeValue();
									}
									else if(childNode.getNodeName().equalsIgnoreCase("FieldValue")){
										fieldval = child.getNodeValue();
									}
								}
							}	
						}
						if(nlist.getLength()!=0)
						{
							fieldobj = new ISOFieldObject(fieldName, fieldval);
							fields_of_request.add(fieldobj);

							msgList.add(fieldval);

						}
					}
				}
				if(requestResponse != "ConnExp")
				{
					BNIClient bniClient = new BNIClient(realpath);
					requestResponse =bniClient.createMessage(msgList,hostName,serverIP,serverPort);
				}

				if(requestResponse.equalsIgnoreCase("ConnExp"))
				{	
					System.out.println("@@@@@@@@@@@@@@@@@ConnExp");
					reqres.clear();
				}
				if(requestResponse  !=null && !(requestResponse.equalsIgnoreCase("ConnExp")))
				{
					reqres.add(requestResponse);
				}
				msgList.clear();

			}

		} 
		catch (SAXException e)
		{
			System.out.println("The error is in readInputXMLFile():  "+e);
		}
		catch (IOException e)
		{
			System.out.println("The error is in readinputXMLFile():  "+e);
		}
		return reqres;
	}


	public static String generateMsgId() {

		try {
			if(rand==null)
				rand = new Random();

		} catch (Exception exp) {

			System.out.println("Entered in Randon Excepti0on " + exp);
		}

		return rand.nextInt(2000000000) + "";
	}// END -- public static int genReqRefKey()
}

class GenRandomData {

	private static Random rand = new Random();
	private static String suffix = "";

	public static String getSubscriberId(String prefix) {

		String subId = "0000000000";

		try {
			if (prefix.trim().length()>=10) {

				subId = prefix;
			} else {

				int randValue = Integer.parseInt(String.valueOf(Math.round((Math.pow(10, (10-prefix.length()))-1))));

				suffix = String.valueOf(Math.abs(rand.nextInt(randValue)));

				suffix = subId + suffix;

				subId = prefix + suffix.substring(suffix.length()-(10-prefix.length()));
			}

		} catch (Exception exp) {

			subId="2122027966";
		}

		return subId;
	}// END -- public String getSubscriber()

	public static String generateMsgId() {

		try {
			if(rand==null)
				rand = new Random();

		} catch (Exception exp) {

			System.out.println("Entered in Randon Excepti0on " + exp);
		}

		return rand.nextInt(2000000000) + "";
	}// END -- public static int generateMsgId()

}// END - class GenRandomData

