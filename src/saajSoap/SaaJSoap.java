package saajSoap;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.io.StringReader;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.validation.Schema;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import SeleniumTestAutomation.Configure;
import SeleniumTestAutomation.DTO;



/**
 * SOAP Client Implementation using SAAJ Api.
 */
public class SaaJSoap
{
	public static String strMsg = null,reqMsg;
	public static String requestMsg;

	public  SOAPMessage createSOAPRequest(String transName,String packageName,int length,DTO dto) throws Exception

	{
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
try{
		if (packageName.equalsIgnoreCase("couponSerialNumberEnquiry"))
		{
			// SOAP Envelope
			SOAPEnvelope envelope = soapPart.getEnvelope();
			String mWnameSpace =Configure.couponMangementNameSpace;
			//envelope.addNamespaceDeclaration("mwal", "http://services.xius.com/services/mWallet");
			envelope.addNamespaceDeclaration("wss", mWnameSpace);
			// SOAP Body
			SOAPBody soapBody = envelope.getBody();

			if (transName.equalsIgnoreCase("WebServicescouponSerialNumberEnquiry"))
			{
				SOAPElement soapBodyElemmm = soapBody.addChildElement("CouponSerialNumberEnquiryRequest", "wss");
				SOAPElement soapBodyElem1 = soapBodyElemmm.addChildElement("UserCrendentialsRequest");
				SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("userName");
				SOAPElement soapBodyElem3 = soapBodyElem1.addChildElement("password");
				SOAPElement soapBodyElem4 = soapBodyElemmm.addChildElement("couponSerialNumber");
				soapBodyElem2.addTextNode(dto.getUserName());
				soapBodyElem3.addTextNode(dto.getPassword());
				soapBodyElem4.addTextNode(dto.getCouponSerialNumber());
				soapMessage.saveChanges();
				// Check the input
				/*System.out.println("Request SOAP Message = ");
				soapMessage.writeTo(System.out);
				System.out.println();*/
			}	
		}
}catch(Exception e){
	e.printStackTrace();
}
		/*if (packageName.equalsIgnoreCase("merchantServiceBinding"))
		{
			SOAPEnvelope envelope = soapPart.getEnvelope();
			SOAPEnvelope envelope1 = soapPart.getEnvelope();
			String mSnameSpaceMerchant = Configure.merchantServiceNameSpace1;
			String mSnameSpaceCommon = Configure.merchantServiceNameSpace2;
			System.out.println("mSnameSpaceMerchant----" +mSnameSpaceMerchant);
			System.out.println("mSnameSpaceCommon----" +mSnameSpaceCommon);

			envelope.addNamespaceDeclaration("mer", mSnameSpaceMerchant);
			envelope1.addNamespaceDeclaration("com", mSnameSpaceCommon);

			// envelope.addNamespaceDeclaration("mer", "http://services.xius.com/messages/merchant");
			//envelope1.addNamespaceDeclaration("com", "http://services.xius.com/messages/common");
			SOAPBody soapBody = envelope.getBody();
			if (transName.equalsIgnoreCase("WSsubscriberAccountTopUpRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("subscriberAccountTopUpRequest", "mer");
				SOAPElement requestId1 = soapBodyElem.addChildElement("requestId", "com");
				SOAPElement timeStamp1 = soapBodyElem.addChildElement("timeStamp", "com");
				SOAPElement version1 = soapBodyElem.addChildElement("version", "com");
				SOAPElement terminalData1 = soapBodyElem.addChildElement("terminalData", "mer");
				SOAPElement terminalId1 = terminalData1.addChildElement("terminalId", "mer");
				SOAPElement branchCode1= terminalData1.addChildElement("branchCode", "mer");
				SOAPElement merchantId1 = terminalData1.addChildElement("merchantId", "mer");
				SOAPElement mdn1 = soapBodyElem.addChildElement("mdn", "mer");
				SOAPElement amount1 = soapBodyElem.addChildElement("amount", "mer");
				SOAPElement paymentMode1 = soapBodyElem.addChildElement("paymentMode", "mer");

				requestId1.addTextNode(requestId);
				timeStamp1.addTextNode(timeStamp);
				version1.addTextNode("version");
				terminalId1.addTextNode(terminalId);
				branchCode1.addTextNode(branchCode);
				merchantId1.addTextNode(merchantId);
				paymentMode1.addTextNode(paymentMode);
				mdn1.addTextNode(mdn);
				amount1.addTextNode(amount);
				soapMessage.saveChanges();

				// Check the input
				 System.out.println("Request SOAP Message = ");
                soapMessage.writeTo(System.out);
                System.out.println();
			}
		}*/
		requestMsg=printSOAPRequest(soapMessage);
		//System.out.println("REquest satish ----------->" +reqMsg);
		
		return soapMessage;
	
	}
     

	public  String printSOAPResponse(SOAPMessage soapResponse) throws Exception
	{
		//System.out.println("\nResponse SOAP Message saaaaaaaa = ");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		soapResponse.writeTo(out);
		strMsg = new String(out.toByteArray());
		//System.out.println(strMsg);
		//XmlPath(strMsg,);
		return strMsg;
	}

	public  String printSOAPRequest(SOAPMessage soapMessage) throws Exception
	{
		//System.out.println("\nRequest SOAP Message chandraaaaaaaaaaa = ");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		soapMessage.writeTo(out);
		reqMsg = new String(out.toByteArray());
		//System.out.println(reqMsg);
		//XmlPath(strMsg,);
		return reqMsg;
	}

        

	public  String XmlPath(String strMsg,String expVal) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {


		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(strMsg));
		Document document = docBuilder.parse(is);
		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath = xPathFactory.newXPath();
		//String FROM="//result/responseCode" ;
		//String FROM1="//BalanceEnquiryResponse/accountI" ;	 
		String responseVal = xPath.evaluate(expVal, document);
		
		if((responseVal.contains("/")&&(responseVal.contains(":")))){
			
			String[] responseVal11 = responseVal.split(" ");
			System.out.println("responseVal11of Xpath-->"+responseVal11[0]);
			responseVal=responseVal11[0];
			return responseVal;
		}
		//System.out.println("responseVal----->"+responseVal);
		return responseVal;
		// String to = xPath.evaluate(TO, document);

	}
		
}

