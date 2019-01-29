package rdbn;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;

import org.apache.log4j.Logger;

import SeleniumTestAutomation.Configure;

public class RDBNGWClient {

	static Logger Log = Logger.getLogger(RDBNGWClient.class);
	private  static Socket rdbnClientSocket = null;
	public static PrintWriter clientPrintWriter = null;
	String rdbnGWIPaddress = null;
	String rdbnGWPortNum = null;

	Properties properties = new Properties();
	public static String testCaseName,reqFile;

	public RDBNGWClient() {
	}

	private void readRDBNConfigFile() {
		System.out.println("inside readRDBNConfigFile() ");
		try {
			/*String configfilePath = RDBNServerConstants.CONFIG_FILE_FOLDER_PATH+ RDBNServerConstants.CONFIG_FILE_NAME;
			Log.info(" ConfigfilePath in readRDBNConfigFile() of RDBNGWClient class" + configfilePath);
			System.out.println("ConfigfilePath in readRDBNConfigFile() of RDBNGWClient class" + configfilePath);

			RDBNServerConstants.properties.load(new FileInputStream(configfilePath));

			rdbnGWIPaddress = RDBNServerConstants.properties.getProperty(RDBNServerConstants.RDBN_GW_IPADDRESS);
			System.out.println("rdbnGWIPaddress in readRDBNConfigFile() of RDBNGWClient class "+ rdbnGWIPaddress);
			rdbnGWPortNum = RDBNServerConstants.properties.getProperty(RDBNServerConstants.RDBN_GW_PORT_NUMBER);
			System.out.println("rdbnGWPortNum in readRDBNConfigFile() of RDBNGWClient class "+ rdbnGWPortNum);

			Log.info(" REDBAN Gateway Server IP Address and Port Numbers "+ rdbnGWIPaddress + ":" + rdbnGWPortNum);*/

		} catch (Exception e) {
			System.out.println("Request cannot be processed as bank gateway is down:: Please up gateway to send request"+ e);
		}
	}

	private static void log(String logMsg) {
		System.out.println(logMsg);
	}

	public String readDataFile() {
		BufferedReader bufRdrIn = null;
		String strMsg = null;
		try {
			String datafilePath=Configure.configFilePath;
			properties.load(new FileInputStream(datafilePath));
			Log.info("datafilePath in  readDataFile() of RDBNGWClient class " + datafilePath);
			System.out.println("datafilePath in  readDataFile() of RDBNGWClient class " + datafilePath);
			String requestDataFile = properties.getProperty("inputFilePath");
			if (requestDataFile != null) {
				requestDataFile = requestDataFile.trim();
			} else {
				System.out.println("Request Data File Name not set in Configuration File.");
			}				
			reqFile=requestDataFile+"\\"+testCaseName+".txt";
			bufRdrIn = new BufferedReader(new FileReader(reqFile));
			strMsg = bufRdrIn.readLine();
		} catch (Exception ex) {
			Log.info("Exception while reading data file in readDataFile() of RDBNGWClient class "+ ex.getMessage());
			System.out.println("Exception while reading data file in readDataFile() of RDBNGWClient class "+ ex.getMessage());
		} finally {
			if (bufRdrIn != null) {
				try {
					bufRdrIn.close();
				} catch (IOException ioex) {
					log("Exception while closing data file in readDataFile() of RDBNGWClient class "+ ioex.getMessage());
					System.out.println("Exception while closing data file in readDataFile() of RDBNGWClient class "+ ioex.getMessage());
				}
			}
		}
		System.out.println("strMsg" + strMsg);
		return strMsg;
	}

	public void doPushRdbnRequest(String strRDBNRequest) {
		try {
			rdbnGWIPaddress = properties.getProperty("RDBN_server");
			System.out.println("rdbnGWIPaddress---- > "+rdbnGWIPaddress);
			rdbnGWPortNum = properties.getProperty("RDBN_PortNumber");
			System.out.println("rdbnGWPortNum---- > "+rdbnGWPortNum);
			System.out.println("I am trying to inititate reqeust using socket connection : "+rdbnGWIPaddress);
			System.out.println("I am trying to inititate reqeust using socket connection : "+rdbnGWPortNum);
			rdbnClientSocket = new Socket(rdbnGWIPaddress, Integer.parseInt(rdbnGWPortNum));
			System.out.println("Established BANK GW socket connection");
			Log.info(" Pushing the Request to REDBAN Server ::: "+ strRDBNRequest);
			System.out.println("Pushing the Request to REDBAN Server ::: "+ strRDBNRequest);
			if (rdbnClientSocket != null)
				clientPrintWriter = new PrintWriter(rdbnClientSocket.getOutputStream(), true);
			if (rdbnClientSocket != null) {
				if (strRDBNRequest != null) {
					Log.info(" Push operation on RDBN Gateway is successful ");
					clientPrintWriter.write(strRDBNRequest.trim().toCharArray());
					clientPrintWriter.flush();
				}
			}
		} catch (Exception e) {
			Log.error(" Exception in doPushRdbnRequest() of RDBNGWClient class "+ e);
		}
		finally{
			try{
				if (rdbnClientSocket != null){
					clientPrintWriter.close();
					rdbnClientSocket.close();
				}
			}catch (Exception ie){
				ie.printStackTrace();
			}
		}
	}

	public void generateReqMsg() {
		try {
			readRDBNConfigFile();
			System.out.println("************* before server start ********************");
			new RDBNResHandler(testCaseName).start();
			Thread.sleep(1000);
			System.out.println("************* after server start ********************");

			String Reqmsg = readDataFile();
			System.out.println("Request message: " + Reqmsg);
			doPushRdbnRequest(Reqmsg);
			System.out.println("************* Request Sent Successfully ********************");
		} catch(Exception exception) {
			exception.printStackTrace();
		}


	}

	public void callRDBNSimulator(String tcid) {
		testCaseName=tcid;
		System.out.println("main Method");
		new RDBNGWClient().generateReqMsg();
	}
}
