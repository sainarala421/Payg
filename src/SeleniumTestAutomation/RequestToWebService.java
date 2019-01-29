package SeleniumTestAutomation;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class RequestToWebService extends TestCase {/*
	
	
	public  static void RecaurgaServicePort(String transactionName,String packageName) throws Exception{
		
		String ip=Configure.WEB_SERVICE_IP;
		String port=Configure.WEB_SERVICE_PORT;
		trName=transactionName;
		SaaJSoapClient soapRequest=null;
		String[] testData = CsvRequest.split(",");
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		String endpoint="http://"+ip+":"+port+"/services/RecargaServicioPort";
		if(transactionName.equalsIgnoreCase("TypicalRechargeRequest")||transactionName.equalsIgnoreCase("AgrandaRecargaRequest")||transactionName.equalsIgnoreCase("RecargaSeguraRequest"))
		{
			dto.setPASSWORD_IVR(testData[0]);
			dto.setPUNTO_VENTA(testData[1]);
			dto.setMONTO(testData[2]);
			dto.setDN(testData[3]);
			dto.setDI(testData[4]);
			dto.setTRANSACCION(testData[5]);
		}else if ((transactionName.equalsIgnoreCase("PocketSaleSMSRequest")||transactionName.equalsIgnoreCase("PocketSaleDataRequest")))
		{
			dto.setPASSWORD_IVR(testData[0]);
			dto.setPUNTO_VENTA(testData[1]);
			dto.setMONTO(testData[2]);
			dto.setDN(testData[3]);
			dto.setDI(testData[4]);
			dto.setTRANSACCION(testData[5]);
			dto.setTIPO(testData[6]);
			dto.setCLAVE(testData[7]);
		}
		soapRequest=new SaaJSoapClient();
		SOAPMessage soapResponse = soapConnection.call(soapRequest.createSOAPRequestForRecharge(transactionName,packageName,testData.length,dto), endpoint);
		wsresponce=soapRequest.printSOAPResponseForRecurgaRecharge(soapResponse);
		System.out.println("Web Service request----->" +transactionName +  SaaJSoapClient.reqMsg);
		System.out.println("Web Service responce----->" +transactionName +  wsresponce);
		soapReq=SaaJSoapClient.reqMsg;
		soapRes=wsresponce;
		soapConnection.close();
	}
	
	// added by kv
	
	public static void SubscriberPortInService(String transactionName,String packageName)throws Exception{
			
			
			String ip=Configure.WEB_SERVICE_IP;
			String port=Configure.WEB_SERVICE_PORT;
			trName=transactionName;
			SaaJSoapClient soapRequest=null;
			String[] testData = CsvRequest.split(",");
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			//String ip=Configure.WEB_SERVICE_IP;
			//String port=Configure.WEB_SERVICE_PORT;
			String endpoint="http://"+ip+":"+port+"/services/SubscriberPortInService";
			//http://192.168.149.174:7001/services/IVRRechargeService
			System.out.println("SubscriberPortInService----" + endpoint);
			if(transactionName.equalsIgnoreCase("portInInitiationRequest"))
			{
				dto.setNumero_pv(testData[0]);
				dto.setClave(testData[1]);
				dto.setNumero(testData[2]);
				dto.setMonto(testData[3]);
				dto.setTiporec(testData[4]);
				dto.setAuto_reserva(testData[5]);
			}
			else if (transactionName.equalsIgnoreCase("portInRechargeRequest"))
			{
				dto.setNumero_pv(testData[0]);
				dto.setClave(testData[1]);
				dto.setNumero(testData[2]);
				dto.setMonto(testData[3]);
				dto.setTiporec(testData[4]);
				dto.setAuto_reserva(testData[5]);
			}
			soapRequest=new SaaJSoapClient();
			SOAPMessage soapResponse = soapConnection.call(soapRequest.createSOAPRequestForportInRechargeInitiation(transactionName,packageName,testData.length,dto), endpoint);
			wsresponce=soapRequest.printSOAPResponseForportInRechargeInitiation(soapResponse);
			System.out.println("Web Service request----->" +transactionName +  SaaJSoapClient.reqMsg);
			System.out.println("Web Service responce----->" +transactionName +  wsresponce);
			soapReq=SaaJSoapClient.reqMsg;
			soapRes=wsresponce;
			soapConnection.close();
		}

	public static void FundTrasferService(String transactionName,String packageName)throws Exception{
		
		String ip=Configure.WEB_SERVICE_IP;
		String port=Configure.WEB_SERVICE_PORT;
		trName=transactionName;
		SaaJSoapClient soapRequest=null;
		String[] testData = CsvRequest.split(",");
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		String endpoint="http://"+ip+":"+port+"/services/FundTransferService";
		if (transactionName.equalsIgnoreCase("fundTransferRequest"))
		{
		    dto.setRequestDate(testData[0]);
		    dto.setDistributorId(testData[1]);
			dto.setDistributorPassword(testData[2]);
			dto.setDistributorType(testData[3]);
			dto.setFromDistributor(testData[4]);
			dto.setToDistributor(testData[5]);
			dto.setTransferAmount(testData[6]);
		}
		soapRequest=new SaaJSoapClient();
		SOAPMessage soapResponse = soapConnection.call(soapRequest.createSOAPRequest(transactionName,packageName,testData.length,dto), endpoint);
		wsresponce=soapRequest.printSOAPResponse(soapResponse);
		System.out.println("Web Service request----->" +transactionName +  SaaJSoapClient.reqMsg);
		System.out.println("Web Service responce----->" +transactionName +  wsresponce);
		soapReq=SaaJSoapClient.reqMsg;
		soapRes=wsresponce;
		soapConnection.close();
	
	}
	
	///////////kv
	
public static void ServiciosPdVPortType(String transactionName,String packageName)throws Exception{
		
		String ip=Configure.WEB_SERVICE_IP;
		String port=Configure.WEB_SERVICE_PORT;
		trName=transactionName;
		SaaJSoapClient soapRequest=null;
		String[] testData = CsvRequest.split(",");
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		String endpoint="http://"+ip+":"+port+"/services/ServiciosPdVPortTypePort";
		if (transactionName.equalsIgnoreCase("TraspasoSaldo"))
		{
		    dto.setDI(testData[0]);
		    dto.setIdentificadorDestino(testData[1]);
		    dto.setIdentificadorOrigen(testData[2]);
		    dto.setMonto(testData[3]);
		    dto.setNip(testData[4]);
		
		}
		soapRequest=new SaaJSoapClient();
		SOAPMessage soapResponse = soapConnection.call(soapRequest.createSOAPRequest(transactionName,packageName,testData.length,dto), endpoint);
		wsresponce=soapRequest.printSOAPResponse(soapResponse);
		System.out.println("Web Service request----->" +transactionName +  SaaJSoapClient.reqMsg);
		System.out.println("Web Service responce----->" +transactionName +  wsresponce);
		soapReq=SaaJSoapClient.reqMsg;
		soapRes=wsresponce;
		soapConnection.close();
	
	}




	public static void MVNEServices(String transactionName,String packageName)throws Exception{

		
		
		SeleniumFW.APPLICATION_LOGS.info("------------ entering in MVNEServices ---------");
		String ip=Configure.WEB_SERVICE_IP;
		String port=Configure.WEB_SERVICE_PORT;
		SeleniumFW.APPLICATION_LOGS.info("Binding ip and port for MVNEServices " +ip + "and" + port);
		trName=transactionName;
		SaaJSoapClient soapRequest=null;
		String[] testData = CsvRequest.split(",");
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		String endpoint="http://"+ip+":"+port+"/services/MVNEServicesSOAP";
		
		if (transactionName.equalsIgnoreCase("ALTA_DNRequest"))
		{
		    dto.setDN(testData[0]);
		    dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setICC(testData[3]);
			dto.setCOD_USO(testData[4]);
		}else if (transactionName.equalsIgnoreCase("ALTA_DNWithOutMDNRequest"))
		{
		    dto.setID_OPER_VIRT(testData[0]);
			dto.setIMSI(testData[1]);
			dto.setICC(testData[2]);
			dto.setCOD_USO(testData[3]);
		}else if (transactionName.equalsIgnoreCase("ALTA_DNWithOutOperatorIDRequest"))
		{
			dto.setDN(testData[0]);
			dto.setIMSI(testData[1]);
			dto.setICC(testData[2]);
			dto.setCOD_USO(testData[3]);
		}else if (transactionName.equalsIgnoreCase("ALTA_DNWithOutIMSIRequest"))
		{
			dto.setDN(testData[0]);
			dto.setID_OPER_VIRT(testData[1]);
			dto.setICC(testData[2]);
			dto.setCOD_USO(testData[3]);
		}else if (transactionName.equalsIgnoreCase("ALTA_DNWithOutICCRequest"))
		{
			dto.setDN(testData[0]);
		    dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setCOD_USO(testData[3]);
		}else if (transactionName.equalsIgnoreCase("ALTA_DNWithOutCOD_USORequest"))
		{
			dto.setDN(testData[0]);
			dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setICC(testData[3]);
		}else if (transactionName.equalsIgnoreCase("MOD_DNRequest"))
		{
			dto.setDN(testData[0]);
		    dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setICC(testData[3]);
			dto.setCOD_USO(testData[4]);
			dto.setESTATUS(testData[5]);
			dto.setDESCRIPCION(testData[6]);
			
		}else if (transactionName.equalsIgnoreCase("BAJA_DNRequest"))
		{
			dto.setDN(testData[0]);
		    dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setICC(testData[3]);
			dto.setCOD_USO(testData[4]);
			dto.setDESCRIPCION(testData[5]);
			
		}
		else if (transactionName.equalsIgnoreCase("BAJA_DNWithOutDescriptionRequest"))
		{
			dto.setDN(testData[0]);
		    dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setICC(testData[3]);
			dto.setCOD_USO(testData[4]);
			
		}
		else if (transactionName.equalsIgnoreCase("BAJA_DNWithOutICCandIMSIRequest"))
		{
			dto.setDN(testData[0]);
		    dto.setID_OPER_VIRT(testData[1]);
			dto.setCOD_USO(testData[2]);
			dto.setDESCRIPCION(testData[3]);
			
		}else if (transactionName.equalsIgnoreCase("BAJA_DNWithOutOperatorIDRequest"))
		{
			dto.setDN(testData[0]);
			dto.setIMSI(testData[1]);
			dto.setICC(testData[2]);
			dto.setCOD_USO(testData[3]);
			dto.setDESCRIPCION(testData[4]);
			
		}else if (transactionName.equalsIgnoreCase("BAJA_DNWithOutMDNRequest"))
		{
			dto.setID_OPER_VIRT(testData[0]);
			dto.setIMSI(testData[1]);
			dto.setICC(testData[2]);
			dto.setCOD_USO(testData[3]);
			dto.setDESCRIPCION(testData[4]);
		}else if (transactionName.equalsIgnoreCase("BAJA_DNWithOutIMSIRequest"))
		{
			dto.setDN(testData[0]);
			dto.setID_OPER_VIRT(testData[1]);
			dto.setICC(testData[2]);
			dto.setCOD_USO(testData[3]);
			dto.setDESCRIPCION(testData[4]);
		}else if (transactionName.equalsIgnoreCase("BAJA_DNWithOutICCRequest"))
		{
			dto.setDN(testData[0]);
			dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setCOD_USO(testData[3]);
			dto.setDESCRIPCION(testData[4]);
		}else if (transactionName.equalsIgnoreCase("BAJA_DNWithOutCOD_USORequest"))
		{
			dto.setDN(testData[0]);
			dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setICC(testData[3]);
			dto.setDESCRIPCION(testData[4]);
		}else if (transactionName.equalsIgnoreCase("AddServiceInBaja_DNRequest"))
		{
			dto.setDN(testData[0]);
			dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setICC(testData[3]);
			dto.setCOD_USO(testData[4]);
		}else if (transactionName.equalsIgnoreCase("ModifyServiceInBaja_DNRequest"))
		{
			dto.setDN(testData[0]);
		    dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setICC(testData[3]);
			dto.setCOD_USO(testData[4]);
			dto.setESTATUS(testData[5]);
			dto.setDESCRIPCION(testData[6]);
		}else if (transactionName.equalsIgnoreCase("GarbageStringInBaja_DNRequest"))
		{
			dto.setGarbageString(testData[0]);
		}
		else if (transactionName.equalsIgnoreCase("MOD_DNWithOutMDNRequest"))
		{
		    dto.setID_OPER_VIRT(testData[0]);
			dto.setIMSI(testData[1]);
			dto.setICC(testData[2]);
			dto.setCOD_USO(testData[3]);
			dto.setESTATUS(testData[4]);
			dto.setDESCRIPCION(testData[5]);
			
		}else if (transactionName.equalsIgnoreCase("MOD_DNWithOutIMSIRequest"))
		{
		    dto.setDN(testData[0]);
			dto.setID_OPER_VIRT(testData[1]);
			dto.setICC(testData[2]);
			dto.setCOD_USO(testData[3]);
			dto.setESTATUS(testData[4]);
			dto.setDESCRIPCION(testData[5]);
			
		}else if (transactionName.equalsIgnoreCase("MOD_DNWithOutOperatorIDRequest"))
		{
			dto.setDN(testData[0]);
			dto.setIMSI(testData[1]);
			dto.setICC(testData[2]);
			dto.setCOD_USO(testData[3]);
			dto.setESTATUS(testData[4]);
			dto.setDESCRIPCION(testData[5]);
		}else if (transactionName.equalsIgnoreCase("MOD_DNWithOutIMSIRequest"))
		{
			dto.setDN(testData[0]);
			dto.setID_OPER_VIRT(testData[1]);
			dto.setICC(testData[2]);
			dto.setCOD_USO(testData[3]);
			dto.setESTATUS(testData[4]);
			dto.setDESCRIPCION(testData[5]);
		}else if (transactionName.equalsIgnoreCase("MOD_DNWithOutICCRequest"))
		{
			dto.setDN(testData[0]);
		    dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setCOD_USO(testData[3]);
			dto.setESTATUS(testData[4]);
			dto.setDESCRIPCION(testData[5]);
		}else if (transactionName.equalsIgnoreCase("MOD_DNWithOutCOD_USORequest"))
		{
			dto.setDN(testData[0]);
		    dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setICC(testData[3]);
			dto.setESTATUS(testData[4]);
			dto.setDESCRIPCION(testData[5]);
		}
		else if (transactionName.equalsIgnoreCase("AddServiceInMOD_DNRequest"))
		{
			dto.setDN(testData[0]);
			dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setICC(testData[3]);
			dto.setCOD_USO(testData[4]);
		}else if (transactionName.equalsIgnoreCase("DeleteServiceInMOD_DNRequest"))
		{
			dto.setDN(testData[0]);
		    dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setICC(testData[3]);
			dto.setCOD_USO(testData[4]);
			dto.setDESCRIPCION(testData[5]);
		}
		else if (transactionName.equalsIgnoreCase("CONS_DNRequest"))
		{
			dto.setDN(testData[0]);
		    dto.setID_OPER_VIRT(testData[1]);
		}else if (transactionName.equalsIgnoreCase("CONS_DNWithOutMDNRequest"))
		{
		    dto.setID_OPER_VIRT(testData[0]);
		}
		else if (transactionName.equalsIgnoreCase("CON_DNWithOutOperatorIDRequest"))
		{
			dto.setDN(testData[0]);
		}else if (transactionName.equalsIgnoreCase("AddServiceInCON_DNRequest"))
		{
			dto.setDN(testData[0]);
			dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setICC(testData[3]);
			dto.setCOD_USO(testData[4]);
		}
		else if (transactionName.equalsIgnoreCase("DeleteServiceInCON_DNRequest"))
		{
			dto.setDN(testData[0]);
		    dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setICC(testData[3]);
			dto.setCOD_USO(testData[4]);
			dto.setDESCRIPCION(testData[5]);
		}else if (transactionName.equalsIgnoreCase("ModifyServiceInCON_DNRequest"))
		{
			dto.setDN(testData[0]);
		    dto.setID_OPER_VIRT(testData[1]);
			dto.setIMSI(testData[2]);
			dto.setICC(testData[3]);
			dto.setCOD_USO(testData[4]);
			dto.setESTATUS(testData[5]);
			dto.setDESCRIPCION(testData[6]);
		}else if (transactionName.equalsIgnoreCase("GarbageStringInCONS_DNRequest"))
		{
			dto.setGarbageString(testData[0]);
		}
		soapRequest=new SaaJSoapClient();
		SOAPMessage soapResponse = soapConnection.call(soapRequest.createSOAPRequest(transactionName,packageName,testData.length,dto), endpoint);
		wsresponce=soapRequest.printSOAPResponseForRecurgaRecharge(soapResponse);
		//SeleniumFW.APPLICATION_LOGS.info("------------------------"+transactionName+ "--" + "Request start-----------------------");
		//SeleniumFW.APPLICATION_LOGS.info("Web Service request----->" + transactionName + "--" +  SaaJSoapClient.reqMsg);
		//SeleniumFW.APPLICATION_LOGS.info("------------------------"+transactionName+ "--" + "Request end----------------------");
		//System.out.println("Web Service request----->" +transactionName + "--" +  SaaJSoapClient.reqMsg);
		//SeleniumFW.APPLICATION_LOGS.info("------------------------"+transactionName+ "--" + "Response start-----------------------");
		soapReq=SaaJSoapClient.reqMsg;
		soapRes=wsresponce;
		soapConnection.close();
	
	}
	
	public static void SirSystemService(String transactionName,String packageName)throws Exception{

		
		
		String ip=Configure.WEB_SERVICE_IP;
		String port=Configure.WEB_SERVICE_PORT;
		//trName=transactionName;
		trName=transactionName;
		SaaJSoapClient soapRequest=null;
		String[] testData = CsvRequest.split(",");
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		String endpoint="http://"+ip+":"+port+"/services/SIRSystemServices";
		//System.out.println("mWalletendPoint----" + endpoint);
		if(transactionName.equalsIgnoreCase("CreditLimitUpdate"))
		{
			dto.setUsername(testData[0]);
			dto.setPassword(testData[1]);
			dto.setReferenceId(testData[2]);
			dto.setDistributorId(testData[3]);
			dto.setDistributorNewCreditLimit(testData[4]);
		}else if(transactionName.equalsIgnoreCase("accountBalanceEnquiry"))
		{
			dto.setUsername(testData[0]);
			dto.setPassword(testData[1]);
			dto.setReferenceId(testData[2]);
			dto.setDistributorId(testData[3]);
		}else if (transactionName.equalsIgnoreCase("paymentProcess"))
		{
			dto.setUsername(testData[0]);
			dto.setPassword(testData[1]);
			dto.setReferenceId(testData[2]);
			dto.setDistributorId(testData[3]);
			dto.setPaymentAmount(testData[4]);
			dto.setCurrency(testData[5]);
			dto.setReferenceText(testData[6]);
		}
		soapRequest=new SaaJSoapClient();
		SOAPMessage soapResponse = soapConnection.call(soapRequest.createSOAPRequest(transactionName,packageName,testData.length,dto), endpoint);
		wsresponce=soapRequest.printSOAPResponse(soapResponse);
		System.out.println("Web Service request----->" +transactionName +  SaaJSoapClient.reqMsg);
		System.out.println("Web Service responce----->" +transactionName +  wsresponce);
		soapReq=SaaJSoapClient.reqMsg;
		soapRes=wsresponce;
		soapConnection.close();
		
	
		
	}
	
	public static void IVRRechargeService(String transactionName,String packageName)throws Exception{
		
		
		String ip=Configure.WEB_SERVICE_IP;
		String port=Configure.WEB_SERVICE_PORT;
		trName=transactionName;
		SaaJSoapClient soapRequest=null;
		String[] testData = CsvRequest.split(",");
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		//String ip=Configure.WEB_SERVICE_IP;
		//String port=Configure.WEB_SERVICE_PORT;
		String endpoint="http://"+ip+":"+port+"/services/IVRRechargeService";
		//http://192.168.149.174:7001/services/IVRRechargeService
		System.out.println("IVRREchargeService----" + endpoint);
		if(transactionName.equalsIgnoreCase("rechargeWithOutEPinRequest"))
		{
			dto.setUsername(testData[0]);
			dto.setPassword(testData[1]);
			dto.setReferenceId(testData[2]);
			dto.setSourceMDN(testData[3]);
			dto.setPin(testData[4]);
			dto.setDestinationMDN(testData[5]);
			dto.setTransactionAmount(testData[6]);
			dto.setTransactionType(testData[7]);
		}else if (transactionName.equalsIgnoreCase("rechargeWithEPinRequest"))
		{
			dto.setUsername(testData[0]);
			dto.setPassword(testData[1]);
			dto.setReferenceId(testData[2]);
			dto.setDestinationMDN(testData[3]);
			dto.setEpin(testData[4]);
			dto.setTransactionAmount(testData[5]);
			dto.setTransactionTimeStamp(testData[6]);
		}
		soapRequest=new SaaJSoapClient();
		SOAPMessage soapResponse = soapConnection.call(soapRequest.createSOAPRequest(transactionName,packageName,testData.length,dto), endpoint);
		wsresponce=soapRequest.printSOAPResponse(soapResponse);
		System.out.println("Web Service request----->" +transactionName +  SaaJSoapClient.reqMsg);
		System.out.println("Web Service responce----->" +transactionName +  wsresponce);
		soapReq=SaaJSoapClient.reqMsg;
		soapRes=wsresponce;
		soapConnection.close();
	}
	
*/}
