package SeleniumTestAutomation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.StringTokenizer;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

//import ussd.USSDRequest;

public class USSDClient {
	public static String ussdResponse=null;

	USSDRequest ussdRequest1 = null;

	public  void processUSSDRequest(String tcid,String csvReq){
		PostMethod method                 =  null;
		BufferedReader reader = null;
		char[] buffer = new char[5000];

		String request = "";
		try {
			HttpClient client = new HttpClient();
			System.out.println("USSD URL --> "+Configure.ussd_url);
			method = new PostMethod(Configure.ussd_url);
			ussdRequest1 = new USSDRequest();
			//request= "<USSDRequest><MSISDN>0911111111</MSISDN><VoucherPin>*9811215454*1234</VoucherPin><DialogueId>122</DialogueId><ProviderId>233</ProviderId><ServiceCode>444</ServiceCode><Response></Response></USSDRequest>";
			System.out.println("csvReq in USSDClinet "+csvReq);
			setData(csvReq);
			request=encodeUSSDXML(ussdRequest1);
			System.out.println("Reqeust ::::: "+request);
			method.setRequestBody(request);
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.out.println("*** Https Method statu  failed ***** : " + method.getStatusLine());
			}
			InputStream responseBody = method.getResponseBodyAsStream();
			reader = new BufferedReader(new InputStreamReader(responseBody));
			buffer = new char[5000];
			int msgExist = reader.read(buffer, 0, 4999);
			ussdResponse=String.valueOf(buffer).trim();
			System.out.println("Response : "+(String.valueOf(buffer).trim()));
			writeResponseToFile(ussdResponse,tcid);

		}catch(Exception exception) {
			System.out.println("Exception "+exception);
			exception.getStackTrace();

		}
		finally {
			try {
				if(reader!=null)
					reader.close();
			}catch(Exception exception) {
				System.out.println("Exception while closing"+exception);
			}
		}

	}
	public void writeResponseToFile(String ussdResponse2,String TCID) throws IOException {
		long cTime = System.currentTimeMillis();
		Writer output = null;
		File file = new File(Configure.outputFilePath+"\\"+TCID+"_"+cTime+".txt");
		output = new BufferedWriter(new FileWriter(file));
		output.write(ussdResponse2);
		output.close();
	}
	public  static String encodeUSSDXML(USSDRequest ussdRequest1) {
		System.out.println("started");
		try {
			System.out.println("Message sent");
			return "<USSDRequest>" + "<MSISDN>" + (ussdRequest1.getMsisdn()!=null?ussdRequest1.getMsisdn():"")  + "</MSISDN>"
			+ "<VoucherPin>" + (ussdRequest1.getVoucherPin()!=null?ussdRequest1.getVoucherPin():"")  + "</VoucherPin>"
			+ "<DialogueId>" + (ussdRequest1.getDialogueID()!=null?ussdRequest1.getDialogueID():"") + "</DialogueId>"
			+ "<ProviderId>" + (ussdRequest1.getProviderID()!=null?ussdRequest1.getProviderID():"")  + "</ProviderId>"
			+ "<ServiceCode>" + (ussdRequest1.getServiceCode()!=null?ussdRequest1.getServiceCode():"") + "</ServiceCode>"
			+ "<Response>" + "</Response>" + "</USSDRequest>";
		} catch (Exception except) {
			return null;
		}
	}	

	public void setData(String csvReq)
	{
		StringTokenizer st = new StringTokenizer(csvReq, ",");
		while (st.hasMoreTokens()) {
			String msgFieldData = st.nextToken();
			if ((msgFieldData != null) && (msgFieldData.length() > 0)) {
				int indexOfSemiColon = msgFieldData.indexOf(":");
				String fieldId = msgFieldData.substring(0,indexOfSemiColon);
				String fieldData = msgFieldData.substring(indexOfSemiColon + 1, msgFieldData.length());
				System.out.println(fieldId +"====="+fieldData);
				if(fieldId!=null && "MSISDN".equals(fieldId)){
					ussdRequest1.setMsisdn(fieldData);
				}else if(fieldId!=null && "VoucherPin".equals(fieldId)){
					ussdRequest1.setVoucherPin(fieldData);
				}else if(fieldId!=null && "DialogueID".equals(fieldId)){
					ussdRequest1.setDialogueID(fieldData);
				}else if(fieldId!=null && "ProviderID".equals(fieldId)){
					ussdRequest1.setProviderID(fieldData);
				}else if(fieldId!=null && "ServiceCode".equals(fieldId)){
					ussdRequest1.setServiceCode(fieldData);
				}else if(fieldId!=null && "Response".equals(fieldId)){
					ussdRequest1.setResponse(fieldData);
				}

			}}

	}
}
