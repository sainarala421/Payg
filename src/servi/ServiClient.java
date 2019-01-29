//package airersbni;
package servi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import BNI.ISOFieldObject;
import common.SimulatorConst;


public class ServiClient {

	String ipAddress;

	Socket 			socket		= null;
	BufferedInputStream input = null;
	BufferedOutputStream output = null;
	int port;
	String message="";
	String response="";

	String request= null;
	String requestType = "";
	static ServerSocket server	=	null;

	static InputStream inStr    = null;
	static OutputStream outStr  = null;
	static byte[] bt	=	null;
	ArrayList reqList = new ArrayList();
	String referenceNO = "";
	String amount = "";
	String subscriberNO = "";
	String TransDate = "";
	String serviRequest = "";
	String serviResponse = "";
	String hostName = "";

	public static boolean printFile = true;


	public static boolean flag = false;
	BufferedInputStream input1= null;
	BufferedOutputStream output1= null;
	String printResponse= "";


	String realpath="";


	public ServiClient(String realpath1) {

		// TODO Auto-generated constructor stub
		//System.out.println("in constructor");
		this.realpath = realpath1;

	}


	public String sendRequest(ArrayList list)
	{
		//System.out.println("in sendrequest");

		try
		{
			this.reqList = list;
			System.out.println("from servipagos Client"+reqList);
			if(reqList.size() != 0)
			{
				this.ipAddress = (String)reqList.get(0);
				this.port = Integer.parseInt(reqList.get(1).toString());
				this.requestType = (String)reqList.get(2);
				this.referenceNO = (String)reqList.get(3);
				this.amount = (String)reqList.get(4);
				this.subscriberNO = (String)reqList.get(5);;
				this.TransDate = (String)reqList.get(6);



				//System.out.println("requestList"+reqList+"ipAddress"+ipAddress+"port"+port+"requestType"+requestType);
				StringBuffer input = null;
				if(requestType.equalsIgnoreCase("001") || requestType.equalsIgnoreCase("003"))
				{
					serviRequest = "ReferenceNumber="+referenceNO+"Amount="+amount+"SubscriberNo="+subscriberNO+"TransactionDate="+TransDate;
				}
				else{
					serviRequest = "Amount=10.00"+"SubscriberNo=0091562004"+"TransactionDate=20060927";
				}
				socket = new Socket(ipAddress,port);
				inStr  = socket.getInputStream();
				input = new StringBuffer();


				for(int index = 0;index < 1;index++)
				{
					if(requestType.equals("001"))
					{
						//input.append("!@TR09140129136712371230422+2004072808520200001209100104200407280000100000000010189000000000000000000+88882004072804000001TA0501041291854988880000000001018900355                       0100000003104129101S00PF0700001000000000101890000104129100100000000002000000000000030000000000000500000000000003000000000000000000000000000000000100000000002000000000000030000098765976                    50.00");

						input.append("ASTR09140129136712371230422+2004072808520200001209100104"+TransDate+"0000100000000010189000");
						input.append("000000000000000+88882004072804000001TA0501041291854988880000009156200400355                       ");
						input.append("01"+referenceNO+"01S00PF070000100000000010189000010412910010000000000200000000000003");
						input.append("000000000000010000000000000300000000000000000000000000000000010000000000200000000000003000");
						input.append(subscriberNO+"                     "+amount+"                         ");
					}
					if(requestType.equals("002"))
					{
						input.append("ASAR09140129136712371230422+2004072808520200001209100104200407280000100000000010189000");
						input.append("000000000000000+88882004072804000001TA0501041291854988880000009156200400355                       ");
						input.append("0100000003104129401S00PF070000100000000010189000010412910010000000000200000000000003");
						input.append("000000000000010000000000000300000000000000000000000000000000010000000000200000000000003000");
						input.append("0091562004                    10.00                         ");
					}
					else if(requestType.equals("003"))
					{
						System.out.println("amount"+amount);
						input.append("ASXR09140129136712371230422+2004072808520200001209100104"+TransDate+"0000100000000010189000");
						input.append("000000000000000+88882004072804000001TA0501041291854988880000009156200400355                       ");
						input.append("01"+referenceNO+"01S00PF070000100000000010189000010412910010000000000200000000000003");
						input.append("000000000000010000000000000300000000000000000000000000000000010000000000200000000000003000");
						input.append(subscriberNO+"                     "+amount+"                         ");
					}

					/*
					AB                    -> Protocol Header
					TR					  -> Message Type Code
					0914				  -> Source Fi Number
					12345				  -> Source Terminal Number
					654321				  -> Source Sequence Number
					4321				  -> Message Sequence Number
					0421S				  -> Transaction Code
					20040702			  -> Source Date
					113930				  -> Source Time
					0000120910			  -> Source ABA Number
					1212				  -> Source Branch Number
					20040702			  -> Source Bussiness Date
					0					  -> Force Post Indicator
					0					  -> Reversal Indicator
					001000000091562004	  -> Trans Acct Number
					000000000000002000	  -> Transaction Amount
					1234				  -> Auth Fi Number
					20040702			  -> Host Bussiness Date
					0					  -> Standin Auth Type
					4					  -> Standin Auth Method
					0000				  -> Card APL Code
					01					  -> Area Proposito General
					 */
					//input.append("LENOFMSG=153;CHANNELID=1234321;MESSAGEID=920572236;MESSAGENO=17;LENOFPAYLOAD=72;SUBNO=KISHORE;ACCOUNTTYPE=3;REQUESTTYPE=1;OLDPWD=kishore;NEWPWD=aeiou;$");

					outStr	=	socket.getOutputStream();
					outStr.write(input.toString().getBytes());
					input.delete(0,input.length());
					System.out.println("After Deleting input.toString() = " + input.toString());

					bt = new byte[3000];
					int no = inStr.read(bt,0,2999);
					if (no!=-1)
					{
						//System.out.println(">>Received1 : "+new String(bt));
						serviResponse = new String(bt);
					}


				}

			}
		}
		catch(Exception e)
		{
			System.out.println("	======> " + e);
			return "ConnExp";
		}
		finally
		{
			try
			{
				//server.close();
				if(socket != null)
					socket.close();
			}
			catch(Exception e)
			{
				System.out.println("	Exception in closing the socket objects======> " + e);
			}
		}
		//System.out.println("serviRequest"+serviRequest +"serviResponse"+serviResponse);
		return serviRequest+"$"+serviResponse;
	}


	public String sendRequest(ArrayList list,String hostName,String serverIP,String portNO)
	{
		System.out.println("in sendrequest");
		this.ipAddress = serverIP;
		this.port = Integer.parseInt(portNO);
		this.reqList = list;

		try
		{

			System.out.println("from servipagos Client"+reqList);
			if(reqList.size() != 0)
			{

				this.requestType = (String)reqList.get(0);
				this.referenceNO = (String)reqList.get(1);
				this.amount = (String)reqList.get(2);
				this.subscriberNO = (String)reqList.get(3);
				this.TransDate = (String)reqList.get(4);



				System.out.println("requestList"+reqList+"ipAddress"+ipAddress+"port"+port+"requestType"+requestType+"TransDate"+TransDate);
				StringBuffer input = null;
				if(requestType.equalsIgnoreCase("001") || requestType.equalsIgnoreCase("003"))
				{
					serviRequest = "ReferenceNumber="+referenceNO+"Amount="+amount+"SubscriberNo="+subscriberNO+"TransactionDate="+TransDate;
				}
				else{
					serviRequest = "Amount=10.00"+"SubscriberNo=0091562004"+"TransactionDate=20060927";
				}
				socket = new Socket(ipAddress,port);
				inStr  = socket.getInputStream();
				input = new StringBuffer();


				for(int index = 0;index < 1;index++)
				{
					if(requestType.equals("001"))
					{
						//input.append("!@TR09140129136712371230422+2004072808520200001209100104200407280000100000000010189000000000000000000+88882004072804000001TA0501041291854988880000000001018900355                       0100000003104129101S00PF0700001000000000101890000104129100100000000002000000000000030000000000000500000000000003000000000000000000000000000000000100000000002000000000000030000098765976                    50.00");

						input.append("ASTR09140129136712371230422+2004072808520200001209100104"+TransDate+"0000100000000010189000");
						input.append("000000000000000+88882004072804000001TA0501041291854988880000009156200400355                       ");
						input.append("01"+referenceNO+"01S00PF070000100000000010189000010412910010000000000200000000000003");
						input.append("000000000000010000000000000300000000000000000000000000000000010000000000200000000000003000");
						input.append(subscriberNO+"                     "+amount+"                         ");
					}
					if(requestType.equals("002"))
					{
						input.append("ASAR09140129136712371230422+2004072808520200001209100104200407280000100000000010189000");
						input.append("000000000000000+88882004072804000001TA0501041291854988880000009156200400355                       ");
						input.append("0100000003104129401S00PF070000100000000010189000010412910010000000000200000000000003");
						input.append("000000000000010000000000000300000000000000000000000000000000010000000000200000000000003000");
						input.append("0091562004                    10.00                         ");
					}
					else if(requestType.equals("003"))
					{
						System.out.println("amount"+amount);
						input.append("ASXR09140129136712371230422+2004072808520200001209100104"+TransDate+"0000100000000010189000");
						input.append("000000000000000+88882004072804000001TA0501041291854988880000009156200400355                       ");
						input.append("01"+referenceNO+"01S00PF070000100000000010189000010412910010000000000200000000000003");
						input.append("000000000000010000000000000300000000000000000000000000000000010000000000200000000000003000");
						input.append(subscriberNO+"                     "+amount+"                         ");
					}

					/*
					AB                    -> Protocol Header
					TR					  -> Message Type Code
					0914				  -> Source Fi Number
					12345				  -> Source Terminal Number
					654321				  -> Source Sequence Number
					4321				  -> Message Sequence Number
					0421S				  -> Transaction Code
					20040702			  -> Source Date
					113930				  -> Source Time
					0000120910			  -> Source ABA Number
					1212				  -> Source Branch Number
					20040702			  -> Source Bussiness Date
					0					  -> Force Post Indicator
					0					  -> Reversal Indicator
					001000000091562004	  -> Trans Acct Number
					000000000000002000	  -> Transaction Amount
					1234				  -> Auth Fi Number
					20040702			  -> Host Bussiness Date
					0					  -> Standin Auth Type
					4					  -> Standin Auth Method
					0000				  -> Card APL Code
					01					  -> Area Proposito General
					 */
					//input.append("LENOFMSG=153;CHANNELID=1234321;MESSAGEID=920572236;MESSAGENO=17;LENOFPAYLOAD=72;SUBNO=KISHORE;ACCOUNTTYPE=3;REQUESTTYPE=1;OLDPWD=kishore;NEWPWD=aeiou;$");

					outStr	=	socket.getOutputStream();
					outStr.write(input.toString().getBytes());
					input.delete(0,input.length());
					System.out.println("After Deleting input.toString() = " + input.toString());

					bt = new byte[3000];
					int no = inStr.read(bt,0,2999);
					if (no!=-1)
					{
						//System.out.println(">>Received1 : "+new String(bt));
						serviResponse = new String(bt);
					}


				}

			}
		}
		catch(Exception e)
		{
			System.out.println("	======> " + e);
			return "ConnExp";
		}
		finally
		{
			try
			{
				//server.close();
				if(socket != null)
					socket.close();
			}
			catch(Exception e)
			{
				System.out.println("	Exception in closing the socket objects======> " + e);
			}
		}
		//System.out.println("serviRequest"+serviRequest +"serviResponse"+serviResponse);
		return serviRequest+"$"+serviResponse;
	}


	public ArrayList readInputXMLFile(String filename,String realpath,String hostName,String serverIP,String serverPort)
	{

		ArrayList msgList;
		ArrayList reqres = new ArrayList();
		String fieldName = "";
		String fileName = "";
		String path = "";
		String requestResponse = "";

		fileName = filename;
		path = realpath+SimulatorConst.IN_FILE_DIR_PATH;

		this.hostName = hostName;
		
		msgList = new ArrayList();

		String XMLToParse = path+fileName;
		DOMParser parser = new DOMParser();

		System.out.println("in create message Bulk testing"+XMLToParse);
		try
		{

			parser.parse(XMLToParse);
			Document document = parser.getDocument();
			NodeList reqList = document.getElementsByTagName("Request");


			for(int x=0;x<reqList.getLength();x++)
			{
				ISOFieldObject fieldobj=null;
				ArrayList fields_of_request = new ArrayList();
				
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
				System.out.println("message list from servipagos"+msgList);
				if(requestResponse != "ConnExp")
				{
					ServiClient serviClient = new ServiClient(realpath);
					requestResponse = serviClient.sendRequest(msgList,hostName,serverIP,serverPort);

					

				}

				if(requestResponse.equalsIgnoreCase("ConnExp"))
				{	
					System.out.println("@@@@@@@@@@@@@@@@@ConnExp");
					reqres.clear();
					//reqres.esize() = 0;

				}
				if(requestResponse  !=null && !(requestResponse.equalsIgnoreCase("ConnExp")))
				{
					reqres.add(requestResponse);
				}
				msgList.clear();

				System.out.println("reqres.size()"+reqres.size()+"requestbulk"+reqres);

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

	public boolean print(String request,String response,String host,String path)
	{

		PrintStream outputFileStream=null;
		java.util.Properties properties = new java.util.Properties();
		try {
			properties.load(new java.io.FileInputStream(realpath+SimulatorConst.FILE_SEPARATOR+SimulatorConst.IN_FILE_DIR_PATH+"LogConfiguration.properties"));
			String filePath = properties.getProperty("servi_log_path")+SimulatorConst.FILE_SEPARATOR;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String outputFileName = "01-04-2006";

			this.serviRequest = request;
			this.serviResponse = response;
			this.realpath = path;


			//System.out.println("*****from print*******"+request+"resonse"+response+"host"+host+"path"+path);


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


		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println("request===>"+serviRequest+"response==>"+serviResponse);
		outputFileStream.println("-------------------"+new java.util.Date()+"----------------------");
		outputFileStream.println("Request :");
		outputFileStream.println(request);
		outputFileStream.println();
		outputFileStream.println("RESPONSE :  ");
		outputFileStream.println(response);
		outputFileStream.println();

		return true;
	}

}




