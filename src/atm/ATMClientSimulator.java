/*
 * Created on Jul 10, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author buchaiahk
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package atm;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Random;

import SeleniumTestAutomation.Configure;
import SeleniumTestAutomation.SeleniumFW;
import atm.util.ATMConstants;
import atm.util.ATMMessage;
import atm.util.ATMUtil;

public class ATMClientSimulator {
	
	public String normalStr 	= "";	
	public static String guiMessage 	= "";
	public static Random rand = new Random();
	Socket s = null;
	BufferedOutputStream output	= null;
	BufferedInputStream	input = null;
	atm.util.RequestResponseBean reqRes = new atm.util.RequestResponseBean();;
	public static FileOutputStream fos = null;
	static String simulatorIP;
	static String simulatorPort;
	static String outLogFile;
	public static String Atmresponse=null;
	String response=null;
	public ArrayList createMessage(ATMBean atmBean,int msgCount,String Operatoridentity,String packageName,String TCID,String transactionName ) {
		
		ArrayList reqResList = new ArrayList();
		String errorCode=null;
		
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(Configure.configFilePath));
			simulatorIP=properties.getProperty(packageName+"_simulatorIP");
			simulatorPort=properties.getProperty(packageName+"_simulatorPort");
			//simulatorPort=Integer.parseInt(properties.getProperty(packageName+"_simulatorPort"));
			System.out.println("IP for the Simulator ---> " + simulatorIP);
			System.out.println("Port for the Simulator ---> " + simulatorPort);
			outLogFile=Configure.outputFilePath+"\\"+TCID+".log";
			System.out.println("outLogfile--->:" +outLogFile);
			atmBean.setIpAddress(simulatorIP);
			atmBean.setPortNo(simulatorPort);
			
			if(atmBean.getIpAddress()!= null && atmBean.getPortNo() != null){
				s = new Socket(atmBean.getIpAddress(), Integer.parseInt(atmBean.getPortNo()));
				input	= new BufferedInputStream(s.getInputStream());
				output	= new BufferedOutputStream(s.getOutputStream());
				
				
				  String request = validateRequest(atmBean);
				  SeleniumFW.APPLICATION_LOGS.info("Atm Request:::"+request);
				
				if(!(request.equalsIgnoreCase("Invalid Message")))
				{
					for(int noOfIterations=0;noOfIterations<msgCount;noOfIterations++)
					{	
						
						Atmresponse=sendReqToServer(request);
						 SeleniumFW.APPLICATION_LOGS.info("Atm Response:::"+Atmresponse);
						writeToSuccessLog(outLogFile,Atmresponse);
						if(!(response.equalsIgnoreCase("IOException")))
						{
							errorCode=showResponse(response);
							reqRes.setRequest(request);
							reqRes.setResponse(response);
							reqRes.setErrorCode(errorCode);
							reqResList.add(reqRes);
						}
						else
						{
							reqRes.setRequest(null);
							reqRes.setResponse(null);
							reqRes.setErrorCode(response);
						}
					}
					
				}
				else
				{
					reqRes.setRequest(null);
					reqRes.setResponse(null);
					reqRes.setErrorCode("Invalid Message");
					reqResList.add(reqRes);
				}
			} 
		}catch (NumberFormatException e) {
			
			reqRes.setRequest(null);
			reqRes.setResponse(null);
			reqRes.setErrorCode("NumberFormatException");
			
			reqResList.add(reqRes);
		} catch (UnknownHostException e) {
			
			reqRes.setRequest(null);
			reqRes.setResponse(null);
			reqRes.setErrorCode("UnknownHostException");
			reqResList.add(reqRes);
		} catch (IOException e) {
			reqRes.setRequest(null);
			reqRes.setResponse(null);
			
			reqRes.setErrorCode("IOException");
			reqResList.add(reqRes);
		}
		catch (IllegalArgumentException e) {
			reqRes.setRequest(null);
			reqRes.setResponse(null);
			
			reqRes.setErrorCode("IllegalArgumentException");
			reqResList.add(reqRes);
		}
		catch (NullPointerException e) {
			reqRes.setRequest(null);
			reqRes.setResponse(null);
			
			reqRes.setErrorCode("NullPointerException");
			reqResList.add(reqRes);
		}
		
		finally
		{
			try {
				if(s!=null)
					s.close();
			} catch (IOException e) {
				
				reqRes.setRequest(null);
				reqRes.setResponse(null);
				reqRes.setErrorCode("IOException");					
			}
		}
		
		return reqResList;		
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
	public atm.util.RequestResponseBean createSendRequest(Hashtable successReqs,Socket s)
	{
		String response=null;
		String errorCode=null;
		reqRes = new atm.util.RequestResponseBean();
		ATMBean atmBean = new ATMBean();
		
		try {
			for (Enumeration e = successReqs.keys(); e.hasMoreElements(); )
			{
				String fieldName = (String)e.nextElement();
				if((fieldName).equalsIgnoreCase("Message Number"))
				{
					atmBean.setMsgNum(successReqs.get(fieldName).toString());
				}
				else if((fieldName).equalsIgnoreCase("Telephone Number"))
				{
					atmBean.setSubid(successReqs.get(fieldName).toString());
				}
				else if((fieldName).equalsIgnoreCase("Amount"))
				{
					atmBean.setAmount(successReqs.get(fieldName).toString());
				}
				else if((fieldName).equalsIgnoreCase("Reversal Number"))
				{
					atmBean.setRevno(successReqs.get(fieldName).toString());
				}					
			}
			
			input	= new BufferedInputStream(s.getInputStream());
			output	= new BufferedOutputStream(s.getOutputStream());
			String request = validateRequest(atmBean);
			
			if(!(request.equalsIgnoreCase("Invalid Message")))
			{
				response=sendReqToServer(request);
				if(!(response.equalsIgnoreCase("IOException")))
				{
					errorCode=showResponse(response);
					reqRes.setRequest(request);
					reqRes.setResponse(response);
					reqRes.setErrorCode(errorCode);
				}
				else
				{
					reqRes.setRequest(null);
					reqRes.setResponse(null);
					reqRes.setErrorCode(response);
				}
			}
			else
			{
				reqRes.setRequest(request);
				reqRes.setResponse("No Response");
				reqRes.setErrorCode("Invalid Message");
				//	reqResList.add(reqRes);
			}
			
		} catch (NumberFormatException e) {
			System.out.println("The error is in createMessage NumberFormatException :"+e);
			reqRes.setRequest(null);
			reqRes.setResponse(null);
			reqRes.setErrorCode("NumberFormatException");
		} catch (UnknownHostException e) {
			System.out.println("The error is in createMessage UnknownHostException :"+e);
			reqRes.setRequest(null);
			reqRes.setResponse(null);
			reqRes.setErrorCode("UnknownHostException");
		} catch (IOException e) {
			System.out.println("The error is in createMessage IOException :"+e);
			reqRes.setRequest(null);
			reqRes.setResponse(null);
			reqRes.setErrorCode("IOException");
		}
		return reqRes;
	} 
	
	public String validateRequest(ATMBean atmBean) {
		String request = null;
		String res=null;
		
		res = transactionCodeRequest();
		if(res != null && res.equals("000"))
		{
			if(atmBean.getMsgNum().equals(atm.util.ATMConstants.ATM21$VALIDATE_TELEPHONE))
				request = validateTelephoneRequest(atmBean);
			else if(atmBean.getMsgNum().equals(atm.util.ATMConstants.ATM22$ACCREDITATION_MINUTES))
				request = accreditationMinutesRequest(atmBean);
			else if(atmBean.getMsgNum().equals(atm.util.ATMConstants.ATM23$PURCHASE_ACTIVATION_CODE))
				request = purchaseActivationRequest(atmBean);
			else if(atmBean.getMsgNum().equals(atm.util.ATMConstants.ATM24$REVERSE))
				request = cancelRequest(atmBean);
			else if(atmBean.getMsgNum().equals(atm.util.ATMConstants.ATM30$ADMIN_TRANSACTION))
				transactionCodeRequest();
			else {
				return "Invalid Message";
			}
		}	
		else
		{
			return "Invalid Message";
		}
		
		return request;
	}// END -- public String validateRequest(IVRBean atmBean)
	
	
	
	
	public String validateTelephoneRequest(ATMBean atmBean) {
		ATMMessage atmRequest = new ATMMessage("", ATMConstants.REQUEST);
		
		atmRequest.appendItem(ATMConstants.CARRIERID, atmBean.getCarrierId());

		atmRequest.appendItem(ATMConstants.CODMSG, atmBean.getMsgNum());
		atmRequest.appendItem(ATMConstants.NUMTRAN,generateMsgId());
		atmRequest.appendItem(ATMConstants.VALOR, atmBean.getAmount());
		atmRequest.appendItem(ATMConstants.TELEFONO, atmBean.getSubid());
		String normalStr = atmRequest.getMessage();
		
		
		return(normalStr);
	}// END -- validateTelephoneRequest()
	
	public String accreditationMinutesRequest(ATMBean atmBean) {
		ATMMessage atmRequest = new ATMMessage("", ATMConstants.REQUEST);
		
		atmRequest.appendItem(ATMConstants.CARRIERID, atmBean.getCarrierId());

		atmRequest.appendItem(ATMConstants.CODMSG, atmBean.getMsgNum());
		atmRequest.appendItem(ATMConstants.NUMTRAN,generateMsgId());
		atmRequest.appendItem(ATMConstants.VALOR, atmBean.getAmount());
		atmRequest.appendItem(ATMConstants.TELEFONO, atmBean.getSubid());
		String normalStr = atmRequest.getMessage();
		
		return(normalStr);
	}// END -- accreditationMinutesRequest()
	
	public String purchaseActivationRequest(ATMBean atmBean) {
		
		ATMMessage atmRequest = new ATMMessage("", ATMConstants.REQUEST);
		
		atmRequest.appendItem(ATMConstants.CARRIERID, atmBean.getCarrierId());

		atmRequest.appendItem(ATMConstants.CODMSG, atmBean.getMsgNum());
		atmRequest.appendItem(ATMConstants.NUMTRAN,generateMsgId());
		atmRequest.appendItem(ATMConstants.VALOR, atmBean.getAmount());
		String normalStr = atmRequest.getMessage();
		//	System.out.println("REQUEST 23 : " + normalStr);				
		
		return(normalStr);
	}// END -- purchaseActivationRequest()
	
	public String cancelRequest(ATMBean atmBean) {
		String atmTransIdForReversal = (atmBean.getMsgNum() ==null || atmBean.getMsgNum().trim().length()==0)?
				generateMsgId():atmBean.getMsgNum();
				
				ATMMessage atmRequest = new ATMMessage("", ATMConstants.REQUEST);
				atmRequest.appendItem(ATMConstants.CARRIERID, atmBean.getCarrierId());

				atmRequest.appendItem(ATMConstants.CODMSG, atmTransIdForReversal);
				atmRequest.appendItem(ATMConstants.NUMTRAN,atmBean.getRevno());
				atmRequest.appendItem(ATMConstants.VALOR, atmBean.getAmount());
				if(atmBean.getSubid() == null){
					
					atmRequest.appendItem(ATMConstants.TELEFONO, "");
				}
				else{
					
					atmRequest.appendItem(ATMConstants.TELEFONO, atmBean.getSubid());
				}
				String normalStr = atmRequest.getMessage();
				//System.out.println("REQUEST 24 : " + normalStr);				
				
				return(normalStr);
	}// END -- sendPaymentMethodSaveRequest()
	
	public String transactionCodeRequest()
	{
		String res="";
		
		try
		{
			ATMMessage atmRequest = new ATMMessage("", ATMConstants.REQUEST);
			atmRequest.appendItem(ATMConstants.CODMSG, ATMConstants.ATM30$ADMIN_TRANSACTION);
			String normalStr = atmRequest.getMessage();
			res = showResponse(sendReqToServer(normalStr));			
		}
		catch(Exception exp)
		{
			//	System.out.println("Error In buildTransactionCode : " + exp.getMessage());
		}
		return res;
	}// End of transactionCodeRequest()
	
	
	public static String buildMessage(Hashtable htMsg)
	{
		Enumeration enumb = htMsg.keys();
		String message 	 = "";
		String paramName = "";
		String paramValue = "";
		String totalLength = "";
		String paramLength = "";
		try
		{
			while(enumb.hasMoreElements())
			{
				paramName = (String)enumb.nextElement();
				if(paramName.equalsIgnoreCase("REQUEST")
						|| paramName.equalsIgnoreCase("RESPONSE"))
					continue;
				
				paramValue = (String)htMsg.get(paramName);
				
				totalLength = "0000" + (paramName.length() + paramValue.length() + 4);
				totalLength = totalLength.substring(totalLength.length()-4);
				
				paramLength = "0000" + paramValue.length();
				paramLength = paramLength.substring(paramLength.length()-4);
				
				message = message + totalLength + paramLength + paramName + paramValue;
			}
		}
		catch(Exception ex)
		{
			//	System.out.println("Error While building the message " + ex);
		}
		
		return message;
	}
	
	public String sendReqToServer(String message) {
		
		try {
			byte req[] = ATMUtil.encrypt(ATMUtil.getURLEncodedString(message));
			String resLength = "0000" + req.length;
			resLength = resLength.substring(resLength.length()-4);
			byte [] actStr = new byte[4 + req.length];
			actStr[0] = (byte)resLength.charAt(0);
			actStr[1] = (byte)resLength.charAt(1);
			actStr[2] = (byte)resLength.charAt(2);
			actStr[3] = (byte)resLength.charAt(3);
			
			for(int i=0;i<req.length;i++)
				actStr[i+4]=req[i];
			
			output.write(actStr,0,actStr.length);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return readMessage();
	}
	
	private String readMessage()
	{
		String message = "";
		byte[] chrStr = new byte[2000];
		
		try 
		{
			while(true) 
			{
				int msgExists = input.read(chrStr,0,1999);
				if(msgExists!=-1)
				{
					
					byte[] actStr = new byte[msgExists-4];
					for(int i=0;i<msgExists-4;i++)
						actStr[i] = chrStr[i+4];
					message = ATMUtil.getURLDecodedString(ATMUtil.decrypt(actStr));
					break;
				}
				else
					break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return message;
	}// END -- private String readMessage(BufferedReader inputBr)
	
	
	public String showResponse(String resp) {
		Hashtable ht = null;
		if (resp == null || resp.trim().length()==0) {
			//	System.out.println("NO RESPONSE RECEIVED FROM SERVER.");
			return "No Response";
		}
		
		ATMMessage atmResponse = new ATMMessage(resp, ATMConstants.RESPONSE);
		
		ht = atmResponse.getATMMessageHash();
		
		ht.remove("REQUEST");
		
		return ((String) ht.get("RETORNO"));
	}
	
	public static String generateMsgId() {
		if(rand==null)
			rand = new Random();
		return rand.nextInt(2000000000) + "";
	}// END -- public static int genReqRefKey()
	
}