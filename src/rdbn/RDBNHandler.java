package rdbn;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import org.apache.log4j.Logger;

import SeleniumTestAutomation.Configure;

import java.util.Properties;

public class RDBNHandler implements Runnable {

	Properties properties = new Properties();
	private static Logger Log = Logger.getLogger(RDBNHandler.class);
	public static String testCaseName;
	private BufferedReader inputbufferR = null;
	private Socket clientSocket = null;
	ThreadPool ivrPool = null;

	public RDBNHandler(Socket clientSocket, ThreadPool ivrPool,String TCID) {
		testCaseName=TCID;
		Log.info("In the RDBN Handler Class*******************25"+clientSocket);
		try {
			this.clientSocket = clientSocket;
			this.ivrPool = ivrPool;

		} catch (Exception exp) {
			Log.error("Exception in RDBNHandler() of RDBNHandler class "+ exp);
		}
	}

	public void run() {
		Log.info("In the RDBN Handler Class Run Method *******************36"+clientSocket);
		try {
			readIVRClientData();

		} catch (Exception exp) {

			Log.error("Exception in run() of RDBNHandler class " + exp);
		}
	}

	public void readIVRClientData() {

		BufferedWriter bufWriter = null;
		Log.info("In the RDBN Handler Class Run Method *******************47"+clientSocket);
		String reqMessage = "";
		char[] chrStr = null;
		int msgExists = -1;
		chrStr = new char[5000];
		String outFileName;

		try {
			System.out.println("T");
			StringBuffer strBuffer = new StringBuffer();
			inputbufferR = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			String datafilePath=Configure.configFilePath;
			properties.load(new FileInputStream(datafilePath));	

			while (true) {
				chrStr = new char[5000];
				msgExists = inputbufferR.read(chrStr, 0, 4999);
				Log.info("read...count..." + msgExists);
				if (msgExists != -1) {
					reqMessage = "";
					reqMessage = new String(chrStr);

					Log.info(" Part of Request from RDBN Client in RDBNHandler "+ reqMessage.trim());

					strBuffer.append(reqMessage.trim());
					Log.info("From client:  in RDBNReqHandler "+ strBuffer.toString());
					System.out.println("In  RDBNHandler "+ strBuffer.toString());
					if(strBuffer.toString().length()>1){
						String temp = strBuffer.toString();
						if(temp.length()==250){
							System.out.println("res msg ::: "+temp);
							outFileName = properties.getProperty("outputFilePath");
							if (outFileName != null) {
								outFileName = outFileName.trim();
							} else {
								System.out.println("Out File Name not set in Configuration File.");
							}
							System.out.println("response--- "+temp);
							String oFile=outFileName+"\\"+testCaseName+".log";
							System.out.println("response file path---- "+oFile);
							bufWriter = new BufferedWriter(new FileWriter(oFile));
							//Start writing to the output stream
							bufWriter.write(temp);
						}
					}
				} else {
					Log.info(" exiting from the listening....");
					break;
				}
			}

		} catch (Exception exp) {
			Log.error(" Exception in readIVRClientData() of the RDBNHandler "+ exp);
			exp.printStackTrace();
		} finally {
			try {
				if (inputbufferR != null) {
					inputbufferR.close();
					inputbufferR = null;
					RDBNGWClient.clientPrintWriter.close();
					clientSocket.close();	
					bufWriter.flush();
					bufWriter.close();
				}

			} catch (Exception ex) {
				Log.equals(" Exception in finally block of the RDBNHandler "+ ex);
			}
		}

	}

}
