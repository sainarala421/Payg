/*
 * Created on Aug 1, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package atm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import atm.util.RequestResponseBean;
import atm.util.SimulatorConst;
/**
 * @author buchaiahk
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AtmServerSim {
	private static String remoteClient;
	ArrayList reqResList = null;
	atm.util.RequestResponseBean requestResponse=null;
	
	public ArrayList readInputXMLFile(String simPath,String fileName,String remoteHost,String serverIP,String port)
	{
		try {
			ATMClientSimulator atmClient = new ATMClientSimulator();
			Socket soc = null;
			String fieldName = "";
			String fieldValue = "";
			remoteClient = remoteHost;
			DOMParser parser = new DOMParser();
			/*System.out.println("Connecting to " + serverIP + "--" + port+"---------------");*/
			reqResList=new ArrayList();
			soc = new Socket(serverIP, Integer.parseInt(port));
			parser.parse(fileName);
			Document document = parser.getDocument();
			NodeList reqList = document.getElementsByTagName("Request");
			for(int x=0;x<reqList.getLength();x++)
			{
				Hashtable fields_of_request = new Hashtable();
				Node reqNode = reqList.item(x);
				/*System.out.println("reqNode.getNodeName()=  "+reqNode.getNodeName());*/
				NodeList fnodes = reqNode.getChildNodes();
				for(int c=0;c < fnodes.getLength();c++)
				{
					// The length of fnodes.getLength() specifies the number of <Field> tags in XML File
					Node fieldNode =  fnodes.item(c);
					if(fieldNode != null)
					{
						NodeList nlist = fieldNode.getChildNodes();
						
						//The nlist.getLength() specifies the number of child tags and their content.
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
										//System.out.println("********--------- fieldnum= "+fieldNum);
									}
									else if(childNode.getNodeName().equalsIgnoreCase("FieldValue")){
										fieldValue = child.getNodeValue();
										//System.out.println("******---------- fieldval= "+fieldValue);
									}
								}
							}
						}
						if(nlist.getLength()!=0)
						{
							fields_of_request.put(fieldName, fieldValue);
						}
					}
				}
				requestResponse = new RequestResponseBean();
				
				java.util.Properties properties = new java.util.Properties();
				
				properties.load(new java.io.FileInputStream(simPath+SimulatorConst.FILE_SEPARATOR+SimulatorConst.IN_FILE_DIR_PATH+"LogConfiguration.properties"));
				String target = properties.getProperty("atm_log_path");
				//	System.out.print("Target==>"+target);
				java.io.File filePath=new java.io.File(target);
				if(filePath.exists()){
					requestResponse=atmClient.createSendRequest(fields_of_request,soc);
					if(requestResponse.getResponse() != null)
					{
						writeToSuccessLog(requestResponse,target);
					}
				}
				else{
					requestResponse.setErrorCode("logFileException");
					requestResponse.setRequest(null);
					requestResponse.setResponse(null);						
				}
				
				reqResList.add(requestResponse);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("NumberFormatException in Bulk"+e);
			requestResponse = new RequestResponseBean();
			reqResList=new ArrayList();
			requestResponse.setErrorCode("NumberFormatException");
			reqResList.add(requestResponse);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("UnknownHostException in Bulk"+e);
			requestResponse = new RequestResponseBean();
			reqResList=new ArrayList();
			requestResponse.setErrorCode("UnknownHostException");
			reqResList.add(requestResponse);
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			System.out.println("UnknownHostException in Bulk"+e);
			requestResponse = new RequestResponseBean();
			reqResList=new ArrayList();
			requestResponse.setErrorCode("UnknownHostException");
			reqResList.add(requestResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IOException in Bulk"+e);
			requestResponse = new RequestResponseBean();
			reqResList=new ArrayList();
			requestResponse.setErrorCode("IOException");
			reqResList.add(requestResponse);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			System.out.println("SAXException in Bulk"+e);
			requestResponse = new RequestResponseBean();
			reqResList=new ArrayList();
			requestResponse.setErrorCode("SAXException");
			reqResList.add(requestResponse);
		}
		
		
		return reqResList;
	}
	
	
	public static void writeToSuccessLog(RequestResponseBean message,String target)
	{
		try {
			PrintStream outputFileStream=null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String outputFileName=sdf.format(new java.util.Date())+remoteClient+".log";
			String filePath=target+SimulatorConst.FILE_SEPARATOR+outputFileName;
			
			/*System.out.println("FilePath: "+filePath);*/
			outputFileStream = new PrintStream(new FileOutputStream(filePath,true));
			outputFileStream.println("-------------------"+new java.util.Date()+"----------------------");
			outputFileStream.println();
			outputFileStream.println("REQUEST :  "+message.getRequest());
			outputFileStream.println("RESPONSE :  "+message.getResponse());
			/*	System.out.println("RESPONSE :  "+message.getResponse());*/
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException in writeToSuccessLog"+e);
		}  		
		
		
	}
}