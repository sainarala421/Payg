/*package SeleniumTestAutomation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

*//**
 * SOAP Client Implementation using SAAJ Api.
 *//*
public class SaaJSoapClient
{
	public static String strMsg = null,reqMsg;
	public  SOAPMessage createSOAPRequest(String transName,String packageName,int length, DTO dto) throws Exception
	{
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		SOAPEnvelope envelope = soapPart.getEnvelope();
		if (packageName.equalsIgnoreCase("SirSystemService"))
		{
			
			String SIRnameSpace = "http://services.xius.com/messages/common";
			envelope.addNamespaceDeclaration("com", SIRnameSpace);
			// SOAP Body
			SOAPBody soapBody = envelope.getBody();
			if (transName.equalsIgnoreCase("CreditLimitUpdate"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("creditLimitUpdateRequest", "com");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("username", "com");
				SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("password", "com");
				SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("referenceId", "com");
				SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("distributorId", "com");
				SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("distributorNewCreditLimit", "com");
				soapBodyElem1.addTextNode(dto.getUsername());
				soapBodyElem2.addTextNode(dto.getPassword());
				soapBodyElem3.addTextNode(dto.getReferenceId());
				soapBodyElem4.addTextNode(dto.getDistributorId());
				soapBodyElem5.addTextNode(dto.getDistributorNewCreditLimit());
				soapMessage.saveChanges();
			}
		}else if(packageName.equalsIgnoreCase("IVRRechargeService"))
		{
			
			if (transName.equalsIgnoreCase("rechargeWithOutEPinRequest"))
			{
				String IVRRechargeServiceNameSpace = "http://services.xius.com/messages/common";
				envelope.addNamespaceDeclaration("com", IVRRechargeServiceNameSpace);
				SOAPBody soapBody = envelope.getBody();
				SOAPElement soapBodyElem = soapBody.addChildElement("rechargeWithOutEPinRequest", "com");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("username", "com");
				SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("password", "com");
				SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("referenceId", "com");
				SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("sourceMDN", "com");
				SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("pin", "com");
				SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("destinationMDN", "com");
				SOAPElement soapBodyElem7 = soapBodyElem.addChildElement("transactionAmount", "com");
				SOAPElement soapBodyElem8 = soapBodyElem.addChildElement("transactionType", "com");
				soapBodyElem1.addTextNode(dto.getUsername());
				soapBodyElem2.addTextNode(dto.getPassword());
				soapBodyElem3.addTextNode(dto.getReferenceId());
				soapBodyElem4.addTextNode(dto.getSourceMDN());
				soapBodyElem5.addTextNode(dto.getPin());
				soapBodyElem6.addTextNode(dto.getDestinationMDN());
				soapBodyElem7.addTextNode(dto.getTransactionAmount());
				soapBodyElem8.addTextNode(dto.getTransactionType());
				soapMessage.saveChanges();
			}
			else if (transName.equalsIgnoreCase("rechargeWithEPinRequest"))
			{
				String IVRRechargeServiceNameSpace = "http://services.xius.com/messages/common";
				envelope.addNamespaceDeclaration("com", IVRRechargeServiceNameSpace);
				SOAPBody soapBody = envelope.getBody();
				SOAPElement soapBodyElem = soapBody.addChildElement("rechargeWithEPinRequest", "com");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("username", "com");
				SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("password", "com");
				SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("referenceId", "com");
				SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("destinationMDN", "com");
				SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("epin", "com");
				SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("transactionAmount", "com");
				SOAPElement soapBodyElem7 = soapBodyElem.addChildElement("transactionTimeStamp", "com");
				soapBodyElem1.addTextNode(dto.getUsername());
				soapBodyElem2.addTextNode(dto.getPassword());
				soapBodyElem3.addTextNode(dto.getReferenceId());
				soapBodyElem4.addTextNode(dto.getDestinationMDN());
				soapBodyElem5.addTextNode(dto.getEpin());
				soapBodyElem6.addTextNode(dto.getTransactionAmount());
				soapBodyElem7.addTextNode(dto.getTransactionTimeStamp());
				soapMessage.saveChanges();
			}
		}
		
		else if (transName.equalsIgnoreCase("fundTransferRequest"))
		{
			String FundTransferServiceNameSpace = "http://services.xius.com/messages/common";
			envelope.addNamespaceDeclaration("com", FundTransferServiceNameSpace);
			SOAPBody soapBody = envelope.getBody();
			SOAPElement soapBodyElem = soapBody.addChildElement("fundTransferRequest", "com");
			SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("requestDate", "com");
			SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("distributorId", "com");
			SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("distributorPassword", "com");
			SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("DistributorType", "com");
			SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("fromDistributor", "com");
			SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("toDistributor", "com");
			SOAPElement soapBodyElem7 = soapBodyElem.addChildElement("transferAmount", "com");
			soapBodyElem1.addTextNode(dto.getRequestDate());
			soapBodyElem2.addTextNode(dto.getDistributorId());
			soapBodyElem3.addTextNode(dto.getDistributorPassword());
			soapBodyElem4.addTextNode(dto.getDistributorType());
			soapBodyElem5.addTextNode(dto.getFromDistributor());
			soapBodyElem6.addTextNode(dto.getToDistributor());
			soapBodyElem7.addTextNode(dto.getTransferAmount());
			soapMessage.saveChanges();
		}
		//kv
		else if (transName.equalsIgnoreCase("TraspasoSaldo"))
		{
			String ServiciosPdVPortTypeNameSpace = "http://ws.sre.movistar.mx/";
			envelope.addNamespaceDeclaration("ws", ServiciosPdVPortTypeNameSpace);
			SOAPBody soapBody = envelope.getBody();
			SOAPElement soapBodyElem = soapBody.addChildElement("TraspasoSaldo", "ws");
			SOAPElement soapBodyarg = soapBodyElem.addChildElement("arg0");
			SOAPElement soapBodyElem1 = soapBodyarg.addChildElement("DI", "ws");
			SOAPElement soapBodyElem2 = soapBodyarg.addChildElement("identificadorDestino", "ws");
			SOAPElement soapBodyElem3 = soapBodyarg.addChildElement("identificadorOrigen", "ws");
			SOAPElement soapBodyElem4 = soapBodyarg.addChildElement("monto", "ws");
			SOAPElement soapBodyElem5 = soapBodyarg.addChildElement("nip", "ws");
			soapBodyElem1.addTextNode(dto.getDI());
			soapBodyElem2.addTextNode(dto.getIdentificadorDestino());
			soapBodyElem3.addTextNode(dto.getIdentificadorOrigen());
			soapBodyElem4.addTextNode(dto.getMonto());
			soapBodyElem5.addTextNode(dto.getNip());
						soapMessage.saveChanges();
		}
		else if (packageName.equalsIgnoreCase("MVNEServices"))
		{
			String addMvneNameSpace = "http://services.xius.com/mvne";
			envelope.addNamespaceDeclaration("mvne", addMvneNameSpace);
			SOAPBody soapBody = envelope.getBody();
			if (transName.equalsIgnoreCase("ALTA_DNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("ALTA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_ALTA", "mvne");
				String xmlBody=RequestFormates.addMvne(dto);
				System.out.println("XML body for addMvne service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("ALTA_DNWithOutMDNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("ALTA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_ALTA", "mvne");
				String xmlBody=RequestFormates.addWithOutMDNMvne(dto);
				System.out.println("XML body for ALTA_DNWithOutMDNRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("ALTA_DNWithOutOperatorIDRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("ALTA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_ALTA", "mvne");
				String xmlBody=RequestFormates.addWithOutOperatorIDMvne(dto);
				System.out.println("XML body for ALTA_DNWithOutMDNRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("ALTA_DNWithOutIMSIRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("ALTA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_ALTA", "mvne");
				String xmlBody=RequestFormates.addWithOutIMSIMvne(dto);
				System.out.println("XML body for ALTA_DNWithOutMDNRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("ALTA_DNWithOutICCRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("ALTA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_ALTA", "mvne");
				String xmlBody=RequestFormates.addWithOutICCMvne(dto);
				System.out.println("XML body for ALTA_DNWithOutMDNRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("ALTA_DNWithOutCOD_USORequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("ALTA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_ALTA", "mvne");
				String xmlBody=RequestFormates.addWithOutCODUSOMvne(dto);
				System.out.println("XML body for ALTA_DNWithOutMDNRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("MOD_DNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("MOD_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_MOD", "mvne");
				String xmlBody=RequestFormates.modifyMvne(dto);
				System.out.println("XML body for MOD_MVNE service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("BAJA_DNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("BAJA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_BAJA", "mvne");
				String xmlBody=RequestFormates.deleteMvne(dto);
				System.out.println("XML body for BAJA_MVNE service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
				
			}else if (transName.equalsIgnoreCase("BAJA_DNWithOutDescriptionRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("BAJA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_BAJA", "mvne");
				String xmlBody=RequestFormates.deleteWithOutDescriptionMvne(dto);
				System.out.println("XML body for BAJA_DNWithOutDescriptionRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
				
			}else if (transName.equalsIgnoreCase("BAJA_DNWithOutICCandIMSIRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("BAJA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_BAJA", "mvne");
				String xmlBody=RequestFormates.deleteWithOutICCandIMSIMvne(dto);
				System.out.println("XML body for BAJA_DNWithOutICCandIMSIRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
				
			}else if (transName.equalsIgnoreCase("BAJA_DNWithOutOperatorIDRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("BAJA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_BAJA", "mvne");
				String xmlBody=RequestFormates.deleteWithOutOperatorIDMvne(dto);
				System.out.println("XML body for deleteWithOutOperatorIDMvne service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
				
			}else if (transName.equalsIgnoreCase("BAJA_DNWithOutMDNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("BAJA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_BAJA", "mvne");
				String xmlBody=RequestFormates.deleteWithOutMDNMvne(dto);
				System.out.println("XML body for deleteWithOutOperatorIDMvne service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("BAJA_DNWithOutIMSIRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("BAJA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_BAJA", "mvne");
				String xmlBody=RequestFormates.deleteWithOutIMSIMvne(dto);
				System.out.println("XML body for deleteWithOutOperatorIDMvne service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("BAJA_DNWithOutICCRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("BAJA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_BAJA", "mvne");
				String xmlBody=RequestFormates.deleteWithOutICCMvne(dto);
				System.out.println("XML body for deleteWithOutOperatorIDMvne service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("BAJA_DNWithOutCOD_USORequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("BAJA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_BAJA", "mvne");
				String xmlBody=RequestFormates.deleteWithOutCOD_USOMvne(dto);
				System.out.println("XML body for deleteWithOutOperatorIDMvne service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("AddServiceInBaja_DNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("BAJA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_BAJA", "mvne");
				String xmlBody=RequestFormates.addMvne(dto);
				System.out.println("XML body for deleteWithOutOperatorIDMvne service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("ModifyServiceInBaja_DNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("BAJA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_BAJA", "mvne");
				String xmlBody=RequestFormates.modifyMvne(dto);
				System.out.println("XML body for deleteWithOutOperatorIDMvne service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("GarbageStringInBaja_DNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("BAJA_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_BAJA", "mvne");
				String xmlBody=RequestFormates.garbageString(dto);
				System.out.println("XML body GarbageStringInBaja_DNRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}
			else if (transName.equalsIgnoreCase("MOD_DNWithOutMDNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("MOD_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_MOD", "mvne");
				String xmlBody=RequestFormates.modifyWithOutMDNMvne(dto);
				System.out.println("XML body for MOD_DNWithOutMDNRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("MOD_DNWithOutOperatorIDRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("MOD_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_MOD", "mvne");
				String xmlBody=RequestFormates.modifyWithOutOperatorIDMvne(dto);
				System.out.println("XML body for MOD_DNWithOutOperatorIDRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("MOD_DNWithOutIMSIRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("MOD_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_MOD", "mvne");
				String xmlBody=RequestFormates.modifyWithOutIMSIRequest(dto);
				System.out.println("XML body for modifyWithOutIMSIRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("MOD_DNWithOutICCRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("MOD_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_MOD", "mvne");
				String xmlBody=RequestFormates.modifyWithOutICCRequest(dto);
				System.out.println("XML body for modifyWithOutICCRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("MOD_DNWithOutCOD_USORequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("MOD_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_MOD", "mvne");
				String xmlBody=RequestFormates.MOD_DNWithOutCOD_USORequest(dto);
				System.out.println("XML body for MOD_DNWithOutCOD_USORequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}
			else if (transName.equalsIgnoreCase("AddServiceInMOD_DNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("MOD_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_MOD", "mvne");
				String xmlBody=RequestFormates.addMvne(dto);
				System.out.println("XML body for AddServiceInMOD_DNRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("DeleteServiceInMOD_DNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("MOD_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_MOD", "mvne");
				String xmlBody=RequestFormates.deleteMvne(dto);
				System.out.println("XML body for DeleteServiceInMOD_DNRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("CONS_DNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("CONS_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_CONS", "mvne");
				String xmlBody=RequestFormates.enquiryMvne(dto);
				System.out.println("XML body for CONS_DNRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("CONS_DNWithOutMDNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("CONS_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_CONS", "mvne");
				String xmlBody=RequestFormates.enquiryWithoutMDNMvne(dto);
				System.out.println("XML body for CONS_DNWithOutMDNRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}
			else if (transName.equalsIgnoreCase("CON_DNWithOutOperatorIDRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("CONS_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_CONS", "mvne");
				String xmlBody=RequestFormates.enquiryWithOutOperatorIDMvne(dto);
				System.out.println("XML body for CON_DNWithOutOperatorIDRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}
			else if (transName.equalsIgnoreCase("CON_DNWithOutOperatorIDRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("CONS_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_CONS", "mvne");
				String xmlBody=RequestFormates.enquiryWithOutOperatorIDMvne(dto);
				System.out.println("XML body for CON_DNWithOutOperatorIDRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("AddServiceInCON_DNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("CONS_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_CONS", "mvne");
				String xmlBody=RequestFormates.addMvne(dto);
				System.out.println("XML body for AddServiceInCON_DNRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("DeleteServiceInCON_DNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("CONS_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_CONS", "mvne");
				String xmlBody=RequestFormates.deleteMvne(dto);
				System.out.println("XML body for DeleteServiceInCON_DNRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("GarbageStringInCONS_DNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("CONS_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_CONS", "mvne");
				String xmlBody=RequestFormates.garbageString(dto);
				System.out.println("XML body for CONS_DNRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}else if (transName.equalsIgnoreCase("ModifyServiceInCON_DNRequest"))
			{
				SOAPElement soapBodyElem = soapBody.addChildElement("CONS_DN", "mvne");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("XML_CONS", "mvne");
				String xmlBody=RequestFormates.modifyMvne(dto);
				System.out.println("XML body for ModifyServiceInCON_DNRequest service---->" +xmlBody);
				soapBodyElem1.addTextNode(xmlBody);
				soapMessage.saveChanges();
			}
		}
		reqMsg=printSOAPRequest(soapMessage);
		System.out.println("REquest satish ----------->" +reqMsg);
		return soapMessage;

	}
	
	public  SOAPMessage createSOAPRequestForRecharge(String transName,String packageName,int length, DTO dto) throws Exception
	{
		    MessageFactory messageFactory = MessageFactory.newInstance();
		   SOAPMessage soapMessage = messageFactory.createMessage();
		   SOAPPart soapPart = soapMessage.getSOAPPart();
		    SOAPEnvelope env = soapPart.getEnvelope();
		    env.addNamespaceDeclaration("xsi","http://www.w3.org/2001/XMLSchema-instance");
			env.addNamespaceDeclaration("xsd","http://www.w3.org/2001/XMLSchema");
			env.addNamespaceDeclaration("soapenv","http://schemas.xmlsoap.org/soap/envelop/");
			env.addNamespaceDeclaration("rec","http://services.xius.com/recharge");
			SOAPBody soapBody1 = env.getBody();
			SOAPElement soapBodyElem = soapBody1.addChildElement("recargaElectronica", "rec");
			soapBodyElem.setEncodingStyle("http://schemas.xmlsoap.org/soap/encoding/");
	        Name xsitype = env.createName("xsi:type");
		    Name xstype =env.createName("xs:type");
			SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("string");
			soapBodyElem1.addAttribute(xsitype,"xsd:string");
			soapBodyElem1.addAttribute(xstype,"type:string");
			soapBodyElem1.addNamespaceDeclaration("xs","http://www.w3.org/2000/XMLSchema-instance");
			//String SIRnameSpace = Configure.RecargaServiceNameSpace;
			if (transName.equalsIgnoreCase("TypicalRechargeRequest")||(transName.equalsIgnoreCase("AgrandaRecargaRequest")||(transName.equalsIgnoreCase("RecargaSeguraRequest"))))
			{
				String xmlBody=RequestFormates.TypicalRecharge(dto);
				soapBodyElem1.addTextNode(xmlBody);
				
			}else if (transName.equalsIgnoreCase("PocketSaleSMSRequest")||(transName.equalsIgnoreCase("PocketSaleDataRequest")))
			{
				String xmlBody=RequestFormates.pocketSaleSMSRecharge(dto);
				soapBodyElem1.addTextNode(xmlBody);
			}
			soapMessage.saveChanges();	
		//}
		reqMsg=printSOAPRequest(soapMessage);
		System.out.println("REquest satish ----------->" +reqMsg);
		return soapMessage;
	}
	
	//added by kv
	
	public  SOAPMessage createSOAPRequestForportInRechargeInitiation(String transName,String packageName,int length, DTO dto) throws Exception
	{
		    MessageFactory messageFactory = MessageFactory.newInstance();
		   SOAPMessage soapMessage = messageFactory.createMessage();
		   SOAPPart soapPart = soapMessage.getSOAPPart();
		    SOAPEnvelope env = soapPart.getEnvelope();
			env.addNamespaceDeclaration("sub","http://services.xius.com/subscriberportin");
			SOAPBody soapBody1 = env.getBody();
//			SOAPElement soapBodyElem = soapBody1.addChildElement("portInInitiationRequest", "sub");
//			SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("request", "sub");

			if (transName.equalsIgnoreCase("portInInitiationRequest"))
			{
				SOAPElement soapBodyElem = soapBody1.addChildElement("portInInitiationRequest", "sub");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("request", "sub");
				String xmlBody=RequestFormates.portInRechargeInitiation(dto);
				soapBodyElem1.addTextNode(xmlBody);
				
			}else if (transName.equalsIgnoreCase("portInRechargeRequest"))
					{
				SOAPElement soapBodyElem = soapBody1.addChildElement("portInRechargeRequest", "sub");
				SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("request", "sub");
				String xmlBody=RequestFormates.portInRechargeProcess(dto);
				soapBodyElem1.addTextNode(xmlBody);
			}
			soapMessage.saveChanges();	
		//}
		reqMsg=printSOAPRequest(soapMessage);
		System.out.println("REquest satish ----------->" +reqMsg);
		return soapMessage;
	}
	
	public  String printSOAPResponse(SOAPMessage soapResponse) throws Exception
	{
		//System.out.println("\nResponse SOAP Message saaaaaaaa = ");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		soapResponse.writeTo(out);
		strMsg = new String(out.toByteArray());
		//strMsg=XmlParser(strMsg);
		return strMsg;
	}
	public  String printSOAPResponseForRecurgaRecharge(SOAPMessage soapResponse) throws Exception
	{
		//System.out.println("\nResponse SOAP Message saaaaaaaa = ");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		soapResponse.writeTo(out);
		strMsg = new String(out.toByteArray());
		//System.out.println(strMsg);
		strMsg=XmlParser(strMsg);
		return strMsg;
	}
	
	//added by kv
	public  String printSOAPResponseForportInRechargeInitiation(SOAPMessage soapResponse) throws Exception
	{
		//System.out.println("\nResponse SOAP Message saaaaaaaa = ");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		soapResponse.writeTo(out);
		strMsg = new String(out.toByteArray());
		//System.out.println(strMsg);
		strMsg=XmlParser(strMsg);
		return strMsg;
	}
	
	public  String printSOAPRequest(SOAPMessage soapMessage) throws Exception
	{
		//System.out.println("\nRequest SOAP Message chandraaaaaaaaaaa = ");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		soapMessage.writeTo(out);
		reqMsg = new String(out.toByteArray());
		System.out.println("After parsing---" +reqMsg);
		return reqMsg;
	}


	public  String XmlParser(String ResMsg) {

	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	DocumentBuilder db = null;
	try {
		db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(ResMsg));
		try {
			org.w3c.dom.Document doc = db.parse(is);

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());
			NodeList nList = doc
					.getElementsByTagName("registerSubscriberResponse");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :"
						+ nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
				}
			}

			String message = doc.getDocumentElement().getTextContent();

			System.out.println("");
			System.out.println("satish --" +message);
	
			ResMsg=message;
		} catch (SAXException e) {
			// handle SAXException
			SeleniumFW.APPLICATION_LOGS.error("Getting Exception::"+e);
		} catch (IOException e) {
			// handle IOException
			SeleniumFW.APPLICATION_LOGS.error("Getting Exception::"+e);
		}
	} catch (ParserConfigurationException e1) {
		// handle ParserConfigurationException
		SeleniumFW.APPLICATION_LOGS.error("Getting Exception::"+e1);
	}
	return ResMsg;

}
	
	public  String XmlPath(String strMsg,String expVal) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException
	{
		String responseVal=null;
		if(strMsg.contains("soapenv:Server.generalException")){
		
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			InputSource is = new InputSource();
			//<?xml version="1.0" encoding="utf-8"?><soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"><soapenv:Body><soapenv:Fault><faultcode>soapenv:Server.generalException</faultcode><faultstring></faultstring><detail><fault><ns1:errorCode xmlns:ns1="http://services.xius.com/messages/common">515</ns1:errorCode><ns2:errorMessage xmlns:ns2="http://services.xius.com/messages/common"> Invalid Password </ns2:errorMessage></fault><ns3:exceptionName xmlns:ns3="http://xml.apache.org/axis/">com.xius.services.messages.common.ErrorData</ns3:exceptionName><ns4:hostname xmlns:ns4="http://xml.apache.org/axis/">payment.xiusrnd.com</ns4:hostname></detail></soapenv:Fault></soapenv:Body></soapenv:Envelope>
			is.setCharacterStream(new StringReader(strMsg));
			Document document = docBuilder.parse(is);
			XPathFactory xPathFactory = XPathFactory.newInstance();//<faultcode>
			XPath xPath = xPathFactory.newXPath();
			expVal="//Fault/detail/fault/errorMessage";////fundTransferResponse/transactionId:get-transactionID
			String responseVal = xPath.evaluate(expVal, document);
			String expVal1="//Fault/detail/fault/errorCode";
			String responseVal1 = xPath.evaluate(expVal1, document);
			responseVal="ErroCode: "+responseVal1+"Error Desc:"+ responseVal;
			return responseVal;
		}
		
		//else{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		InputSource is = new InputSource();
		//<?xml version="1.0" encoding="utf-8"?><soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"><soapenv:Body><soapenv:Fault><faultcode>soapenv:Server.generalException</faultcode><faultstring></faultstring><detail><fault><ns1:errorCode xmlns:ns1="http://services.xius.com/messages/common">515</ns1:errorCode><ns2:errorMessage xmlns:ns2="http://services.xius.com/messages/common"> Invalid Password </ns2:errorMessage></fault><ns3:exceptionName xmlns:ns3="http://xml.apache.org/axis/">com.xius.services.messages.common.ErrorData</ns3:exceptionName><ns4:hostname xmlns:ns4="http://xml.apache.org/axis/">payment.xiusrnd.com</ns4:hostname></detail></soapenv:Fault></soapenv:Body></soapenv:Envelope>
		//<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mvne="http://services.xius.com/mvne"><SOAP-ENV:Header/><SOAP-ENV:Body><mvne:ALTA_DN><mvne:XML_ALTA><![CDATA[<ALTA><DN>4951498783</DN><ID_OPER_VIRT>1003701234</ID_OPER_VIRT><IMSI>6043320323</IMSI><ICC>2247491827</ICC><COD_USO>1</COD_USO></ALTA>]]></mvne:XML_ALTA></mvne:ALTA_DN></SOAP-ENV:Body></SOAP-ENV:Envelope>
		is.setCharacterStream(new StringReader(strMsg));
		Document document = docBuilder.parse(is);
		XPathFactory xPathFactory = XPathFactory.newInstance();//<faultcode>
		XPath xPath = xPathFactory.newXPath();
		responseVal = xPath.evaluate(expVal, document);
		if((responseVal.contains("-")&&(responseVal.contains(":"))&&(responseVal.contains("T"))))
		{

			String[] responseVal11 = responseVal.split("T");
			System.out.println("responseVal11of Xpath-->"+responseVal11[0]);
			responseVal=responseVal11[0];
			return responseVal;
		}
		else if(responseVal.contains(":")){

			String[] responseVal12 = responseVal.split(":");
			System.out.println("responseVal12 of Xpath-->"+responseVal12[1]);
			responseVal=responseVal12[1];
			responseVal=responseVal.trim();
			return responseVal;
		}
		else if(responseVal==""){
			if (Expected.expVal1.equalsIgnoreCase("NULL_DATA"))
			{
				DocumentBuilderFactory docFactory1 = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder1 = docFactory1.newDocumentBuilder();
				InputSource is1 = new InputSource();
				//<?xml version="1.0" encoding="utf-8"?><soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"><soapenv:Body><soapenv:Fault><faultcode>soapenv:Server.generalException</faultcode><faultstring></faultstring><detail><fault><ns1:errorCode xmlns:ns1="http://services.xius.com/messages/common">515</ns1:errorCode><ns2:errorMessage xmlns:ns2="http://services.xius.com/messages/common"> Invalid Password </ns2:errorMessage></fault><ns3:exceptionName xmlns:ns3="http://xml.apache.org/axis/">com.xius.services.messages.common.ErrorData</ns3:exceptionName><ns4:hostname xmlns:ns4="http://xml.apache.org/axis/">payment.xiusrnd.com</ns4:hostname></detail></soapenv:Fault></soapenv:Body></soapenv:Envelope>
				is1.setCharacterStream(new StringReader(strMsg));
				Document document1 = docBuilder1.parse(is1);
				XPathFactory xPathFactory1 = XPathFactory.newInstance();//<faultcode>
				XPath xPath1 = xPathFactory1.newXPath();
				//expVal="//Fault/detail/fault/errorMessage";////fundTransferResponse/transactionId:get-transactionID
				String responseVal1 = xPath.evaluate(expVal, document1);
				//String expVal1="//Fault/detail/fault/errorCode";
				String responseVal11 = xPath1.evaluate(expVal, document);
				//responseVal="ErroCode: "+responseVal1+"Error Desc:"+ responseVal11;
				return responseVal;
			}else {
				DocumentBuilderFactory docFactory1 = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder1 = docFactory1.newDocumentBuilder();
				InputSource is1 = new InputSource();
				//<?xml version="1.0" encoding="utf-8"?><soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"><soapenv:Body><soapenv:Fault><faultcode>soapenv:Server.generalException</faultcode><faultstring></faultstring><detail><fault><ns1:errorCode xmlns:ns1="http://services.xius.com/messages/common">515</ns1:errorCode><ns2:errorMessage xmlns:ns2="http://services.xius.com/messages/common"> Invalid Password </ns2:errorMessage></fault><ns3:exceptionName xmlns:ns3="http://xml.apache.org/axis/">com.xius.services.messages.common.ErrorData</ns3:exceptionName><ns4:hostname xmlns:ns4="http://xml.apache.org/axis/">payment.xiusrnd.com</ns4:hostname></detail></soapenv:Fault></soapenv:Body></soapenv:Envelope>
				is1.setCharacterStream(new StringReader(strMsg));
				Document document1 = docBuilder1.parse(is1);
				XPathFactory xPathFactory1 = XPathFactory.newInstance();//<faultcode>
				XPath xPath1 = xPathFactory1.newXPath();
				expVal="//Fault/detail/fault/errorMessage";////fundTransferResponse/transactionId:get-transactionID
				String responseVal1 = xPath.evaluate(expVal, document1);
				String expVal1="//Fault/detail/fault/errorCode";
				String responseVal11 = xPath1.evaluate(expVal1, document);
				responseVal="ErroCode: "+responseVal1+"Error Desc:"+ responseVal11;
				return responseVal;
			}
			
		}
		
		return responseVal;
		//}
	}
	    private static void XmlParser(String strMsg) {

        				 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        				 DocumentBuilder db = null;
        				 try {
        				     db = dbf.newDocumentBuilder();
        				     InputSource is = new InputSource();
        				     is.setCharacterStream(new StringReader(strMsg));
        				     try {
        				         org.w3c.dom.Document doc = db.parse(is);

        				     	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        				     	NodeList nList = doc.getElementsByTagName("BalanceEnquiryResponse");
        				    	for (int temp = 0; temp < nList.getLength(); temp++) {

        				    		Node nNode = nList.item(temp);

        				    		System.out.println("\nCurrent Element :" + nNode.getNodeName());
        				    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

        				    			Element eElement = (Element) nNode;


        				    			System.out.println("responseCode : " + eElement.getElementsByTagName("responseCode").item(0).getTextContent());
        				    			System.out.println("responseDescription : " + eElement.getElementsByTagName("responseDescription").item(0).getTextContent());
        				    			System.out.println("phoneNumber : " + eElement.getElementsByTagName("phoneNumber").item(0).getTextContent());



        				    		}
        				    	}


        				         String message = doc.getDocumentElement().getTextContent();


        				         System.out.println("");
        				         System.out.println(message);
        				     } catch (SAXException e) {
        				         // handle SAXException
        				     } catch (IOException e) {
        				         // handle IOException
        				     }
        				 } catch (ParserConfigurationException e1) {
        				     // handle ParserConfigurationException
        				 }

		}

	*//**
	 * Starting point for the SAAJ - SOAP Client Testing
	 *//*
	 public static void main(String args[])
        {
                try
                {
                         // Create SOAP Connection
                        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
                        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

                        //Send SOAP Message to SOAP Server
                        String url = "http://192.168.149.108:8080/services/mWalletServiceSOAP";
                       SaaJSoapClient s = new SaaJSoapClient("5058888898","WEB", "1235", "27");

                        SOAPMessage soapResponse = soapConnection.call(s.createSOAPRequest(), url);

                        // Process the SOAP Response
                        printSOAPResponse(soapResponse);

                        soapConnection.close();


                }
                catch (Exception e)
                {
                        System.err.println("Error occurred while sending SOAP Request to Server");
                        e.printStackTrace();
                }


        }
}*/