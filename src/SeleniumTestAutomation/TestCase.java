package SeleniumTestAutomation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import BNI.BNIBean;
import BNI.BNIClient;
import atm.ATMBean;
import atm.ATMClientSimulator;
import rdbn.RDBNGWClient;
//import ussd.USSDInitiator;
//import ussd.USSDInitiator;
import saajSoap.SaaJSoap;

//import rdbn.RDBNGWClient;

//import ussd.USSDInitiator;


class TestCase extends Navigate
{
	public static int tcCount;
	public boolean prevoiusText=true; 
	public int StepID;
	public int PassCnt, FailCnt=0;
	public long sTime1;
	public long sTime;
	public Document docInput = null;
	long timeStamp;
	String result, stepResult = null;
	String passText, failText = null;
	String tcResult = null;
	public static String wsresponce=null;
	boolean tcResult1, retresult;
	String retResult,PASS;
	long totTime, Second = 0;	
	long testcaseTime =0;	
	public String value=null;
	public String PwdValue=null;
	public static String ExpScreenshotPath,sspath, ObjHighlight,CsvRequest,xmlRequest,webServicesTransactionName=null,soapRes=null,soapReq=null,trName=null,fieldDesc="";
	public static int ExpScreenshot;
	public static String screenshot,Date=null,IVRREQUEST=null;
	public String tcResDescVal;
	public String TCID, TCDescription, HeaderExp, HeaderNode;
	public String tcResVal=null;
	public static boolean checkRecovery;
	Navigate objSel = new Navigate();
	StepValidate objStep = new StepValidate();
	Expected objexp = new Expected();
	public String severity, priority,summary,version;
	public String nameModule,nameApplication;
	public static final String waitTime = "40000";
	public static final String waitTime1 = "18000";
	static String outLogFile, inCSVFile;
	public static String TestName;
	public String temp=null,transactionAmount=null,TMCtransactionAmount=null,SvbntransactionAmount=null;
	public static int lengthofNum;
	public static DTO dto;
	ATMBean atmBean;
	BNIBean bniBean;
	static SaaJSoap soapRequest=null;
	ObjectRepository objxml = new ObjectRepository();

	public void parseInputXML() throws Exception	
	{
		File xmlInputFile = new File(Configure.inputfile);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder db = dbf.newDocumentBuilder();

		docInput = db.parse(xmlInputFile);
		docInput.getDocumentElement().normalize();

		String finalResult = "Pass";
		long diffTime = 0;	
		try 
		{
			NodeList inputLst = docInput.getChildNodes();
			for (int input = 0; input < inputLst.getLength(); input++) 
			{
				Node inputLstNode = inputLst.item(input);
				if (inputLstNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					NodeList ApplicationLst = inputLstNode.getChildNodes();
					for (int Application = 0; Application < ApplicationLst.getLength(); Application++) 
					{
						Node ApplicationLstNode = ApplicationLst.item(Application);
						nameApplication = ApplicationLst.item(Application).getNodeName();
						if (ApplicationLstNode.getNodeType() == Node.ELEMENT_NODE) 
						{
							NodeList ModulenameLst = ApplicationLstNode.getChildNodes();
							for (int Modulename = 0; Modulename < ModulenameLst.getLength(); Modulename++) 
							{
								Node ModulenameLstNode = ModulenameLst.item(Modulename);
								nameModule = ModulenameLst.item(Modulename).getNodeName();
								if (ModulenameLstNode.getNodeType() == Node.ELEMENT_NODE) 
								{
									NodeList TCLst = ModulenameLstNode.getChildNodes();
									tcCount = docInput.getElementsByTagName("TC").getLength();
									Result.XMLCreator();
									int i=0;
									TestCaseLoop: for (int TC = 0; TC < TCLst.getLength(); TC++) 
									{
								    	String tcRes = null;
								    	ExpScreenshot = 0;
										stepResult = "Pass";
										finalResult = "Pass";
										boolean recoveryRan = false;
										Node TCLstNode = TCLst.item(TC);
										hm.clear();
										if (TCLstNode.getNodeType() == Node.ELEMENT_NODE) 
										{
											checkRecovery=false;
											TCID = getAttribute(TCLstNode,"Id");
											TCDescription = getAttribute(TCLstNode,"Description");
											StepID=0;
											sTime = System.currentTimeMillis();
											NodeList StepLst = TCLstNode.getChildNodes();
											severity = getAttribute(TCLstNode,"Severity");
											priority = getAttribute(TCLstNode,"Priority");
											//System.out.println("======================================= TEST CASE NUMBER = "+ TCID +" =======================================");
											SeleniumFW.APPLICATION_LOGS.info("======================================= TEST CASE START ======================================================");
											SeleniumFW.APPLICATION_LOGS.info("======================================= TEST CASE ID ================================================");
											SeleniumFW.APPLICATION_LOGS.info("======================================= "+ TCID +" =======================================");
											SeleniumFW.APPLICATION_LOGS.info("======================================= TEST CASE ID ================================================");
											TestName = TCID; 
											SeleniumFW.APPLICATION_LOGS.info("======================================= TEST CASE DESCRIPTION ================================================");
											SeleniumFW.APPLICATION_LOGS.info(TCDescription);
											SeleniumFW.APPLICATION_LOGS.info("=======================================================================================");
											 
											// Calling XMLWriter  write the TC Node
											String ActualResult = null;
											String Content = null;
											String ExpectedResult = null;
											String Path = null;
											String Res = null;
											String Time = null;
											
											HeaderExp = docInput.getElementsByTagName("ExpectedResult").item(i).getTextContent();
											i=i+1;
											String HeaderNodeName=TCLstNode.getNodeName()+"_Step_"+StepID;
											Result.XMLWriter(TCID, TCLstNode.getNodeName(), ActualResult, Content, ExpectedResult, Path, Res, Time);
											Result.XMLWriter("", HeaderNodeName, "TC Suspended", TCDescription, HeaderExp, "", "NotExecuted", "0 Sec");

											for (int Step = 0; Step < StepLst.getLength(); Step++) 
											{
												System.out.println("No.of Steps:" + TCID+ ": Steps:"+  StepLst.getLength());
												String sValue = null;
												String sAction = null;
												ObjectType ot = null;										
												String eVal = null;
												String eTarget = null;
												String eAction = null;
												
												Node StepLstNode = StepLst.item(Step);
												NodeList a = StepLstNode.getChildNodes();
												
												if (StepLstNode.getNodeType() == Node.ELEMENT_NODE) 
													System.out.println("Step "+ StepLstNode.getNodeName()+" :" +a.getLength());
												{
													if (StepLstNode.getNodeName().equalsIgnoreCase("Step")) 
													{
														if (checkRecovery==true)
														{
															continue TestCaseLoop;
														}

														StepID=StepID+1;
														sspath = "Screenshot\\screenshot-TC-"+TCID+"-Step"+StepID+".png";
														screenshot = Configure.resultlog+"\\"+sspath;														
														sTime1 = System.currentTimeMillis();
														sValue = getAttribute(StepLstNode,"Val");
														sAction = getAttribute(StepLstNode,"Action");
														if (sAction.equalsIgnoreCase("include"))
														{
															function(sValue);
														}
														/*if (sAction.equalsIgnoreCase("setTimeout"))
														{
															sel.setTimeout(sValue);
														}*/
													
														//if((sAction.indexOf("include")!=0) && (sAction.indexOf("setTimeout")!=0))
														if(sAction.indexOf("include")!=0)
														{
															ot = objxml.getObjectName(getAttribute(StepLstNode,"RefObj"));
															navigate(sAction,sValue,ot,soapRequest);
															
															retresult=objStep.exp(ot.getObjectName(),sAction,sValue, Session.sel);
															System.out.println("step expected result::::"+retresult);
															if(retresult == true)
															{
																result = "Pass";
															}
															else
															{
																result = "Fail";
															}
															Path = "";
															if(result=="Fail")
															{
																try{
																stepResult = "Fail";
																ObjHighlight=ot.getObjectName();
																objSel.captureScreenshot(screenshot);
																Path =  sspath;
																}catch(Exception e){
																	e.printStackTrace();
																}
															}
															long eTime1 = System.currentTimeMillis();
															diffTime = eTime1 - sTime1;
															Time  = Long.toString(diffTime)+" ms";
															if (sAction.equalsIgnoreCase("store"))
															{
																sValue = ot.getObjectName();
															}
															if (sValue.equalsIgnoreCase("${passwd}"))
															{
																sValue= PwdValue;
															}
															if (sAction.equalsIgnoreCase("type"))
															{
																sValue = Navigate.value;
															}//SearchDropDown
															if (sAction.equalsIgnoreCase("SearchDropDown"))
															{
																sValue = Navigate.value;
															}
															if (sAction.equalsIgnoreCase("ClickActionUsingMouse"))
															{
																sValue = Navigate.value;
															}
															if (sAction.equalsIgnoreCase("typeAndWait"))
															{
																sValue = Navigate.value;
															}
															if (sAction.equalsIgnoreCase("refreshAndWait"))
															{
																sValue = Navigate.value;
															}
															if (ot.getObjectName().indexOf("prevoiusRandomProfileName")>=0)
															{
																sValue = sValue.replaceAll("prevoiusRandomProfileName", prevoiusRandomProfileName);
															}
															if (sValue.indexOf("get")==0)
															{
																sValue = hm.get(sValue.split("-")[1]);
															}
															if (sAction.equalsIgnoreCase("storeFromSimulator"))
															{
																sValue=hm.get(sValue);
															}
															if (sAction.equalsIgnoreCase("storevalueinPropertyFile"))
															{
																sValue = hm.get(sValue)+" in the variable "+sValue;
															}
															if (sAction.equalsIgnoreCase("storeValueFromWebSimulator"))
															{
																sValue = hm.get(sValue)+" in the variable "+sValue;
															}
															if (sAction.equalsIgnoreCase("storeValueFromAtmWebSimulator"))
															{
																sValue = hm.get(sValue)+" in the variable "+sValue;
															}
															if (sAction.equalsIgnoreCase("storeDamagedCoupon"))
															{
																sValue=sValue.split("=")[0];
																sValue=hm.get(sValue)+ " in the variable of"+sValue;
															}
															if (sAction.equalsIgnoreCase("storeValueFromIvrRequest"))
															{
																sValue = hm.get(sValue)+" in the variable "+sValue;
															}
															if (sAction.equalsIgnoreCase("storeText"))
															{
																sValue = hm.get(sValue)+" in the variable "+sValue;
															}
															if (sAction.equalsIgnoreCase("StoreValueInWebTable"))
															{
																sValue = hm.get(sValue)+" in the variable "+sValue;
															}
															if (sAction.equalsIgnoreCase("storeValueFromWebSimulator"))
															{
																sValue = hm.get(sValue)+" in the variable "+sValue;
															}
															if (sAction.equalsIgnoreCase("storeFromSimulatorAmount"))
															{
																sValue=hm.get(sValue);
															}
															if (sAction.equalsIgnoreCase("typeDate"))
															{
																sValue = Navigate.value;
															}
															if (sAction.equalsIgnoreCase("storeAttributeFromWebServiceRequest")||sAction.equalsIgnoreCase("storeAttributeFromWebServiceResponse"))
															{
																sValue=sValue.split(":")[1];
																sValue = hm.get(sValue);
															}
															if (sAction.equalsIgnoreCase("StoreValueFromWebTableBasedOnLables"))
															{
																sValue = hm.get(sValue)+" in the variable "+sValue;
															}
															if(sAction.equalsIgnoreCase("storeBalance"))
															{
																sValue=sValue.split("=")[0];
																sValue=hm.get(sValue)+ " in the variable of"+sValue;
																
															}
																XMLResultsModifier.XmlModifier(sAction,ot.getRefName(), ot.getObjectName(), StepLstNode.getNodeName(), sValue, StepValidate.StepActual, result, Time, Path, sValue);
														}

													}//Step NodeName
													else if (StepLstNode.getNodeName().equalsIgnoreCase("RequestToSimulator"))
													{	
														
														try{												
														
														String packageName=null;
														String transactionName=null;
														String RDBNReq = null;
														String tempRDBNReq=null;
														inCSVFile=null;
														outLogFile=null;
														int reqStepCount  = StepLstNode.getChildNodes().getLength();
														NodeList fieldNodeLst = StepLstNode.getChildNodes();
														String CsvRequest = "";
														for (int field=5; field<reqStepCount; field++)
														{

															Node FieldNode = fieldNodeLst.item(field);
															if(FieldNode.getNodeType() == Node.ELEMENT_NODE) 
															{
																packageName=getAttribute(fieldNodeLst.item(3),"Val");
																System.out.println("Package ---> " + packageName);
																transactionName=getAttribute(fieldNodeLst.item(1),"Val");
																System.out.println("transactionName ---> " + transactionName);
																String fieldValue = getAttribute(FieldNode,"Val");
																String fieldID = getAttribute(FieldNode,"ID");
																System.out.println("Field Value : " + fieldValue);
																System.out.println("Field ID : " + fieldID);
																if(packageName.equalsIgnoreCase("RDBN"))
																{
																	tempRDBNReq = tempRDBNReq+fieldValue;
																	RDBNReq = tempRDBNReq.substring(4);																	
																	System.out.println("RDBN Request --->"+ RDBNReq);
																}
																if(fieldValue.indexOf("readProperty-")>=0){
																	String tempfield=fieldValue.split("-")[1];
																	fieldValue=Locators.getLocators(tempfield).trim();
																	System.out
																			.println("Read form Property file"+fieldValue);
																	
																}
																
																
																if (fieldValue.indexOf("get-")>=0)
																{
																	if (fieldValue.indexOf("*")>=0)
																	{
																		String tempfield=fieldValue.split("-")[1];
																		String actTempText=tempfield.replace("*", "-");
																		String actText=actTempText.split("-")[0];
																		if(hm.containsValue(hm.get(actText))==true){
																		fieldValue=fieldValue.replaceAll("get-"+actText, hm.get(actText));
																		}
																	}
																	if(fieldValue.indexOf("get-")>=0)
                                                                    {
                                                                          String tempfield1=fieldValue.split("-")[1];
                                                                          String actTempText1=tempfield1.replace("*", "-");
                                                                          String actText1=actTempText1.split("-")[0];
                                                                         if(hm.containsValue( hm.get(actText1)) == true)
                                                                          {
                                                                          fieldValue=fieldValue.replaceAll("get-"+actText1, hm.get(actText1));
                                                                          System.out.println("fieldvalue in second iteration "+fieldValue);
                                                                          }
                                                                    }

																	else if (fieldValue.indexOf("randomNumber")>=0)
																	{
																		System.out.println("generate random number");
																		String[] result=fieldValue.split(":");
																		lengthofNum=Integer.parseInt( result[1]);
																		fieldValue=DataGenerator.randomNumberGenerator(lengthofNum);
																		hm.put("payRefNum", fieldValue);
																	}
																	else
																	{
																		fieldValue=hm.get(fieldValue.split("-")[1]);
																	}
																}
																if (packageName.equalsIgnoreCase("USSD") && fieldValue=="")
																{
																	fieldValue="\" \"";
																}
																if (field == 5)
																{
																	CsvRequest = fieldID+":"+fieldValue;
																}
																else
																{
                                                                     if(fieldID.equalsIgnoreCase("3")){
																		
                                                                    	 if(Locators.getLocators("OperatorIdentity").equalsIgnoreCase("Movistar")){
                                                                    		 
                                                                    		 System.out
																					.println("Movister Processing Code"+fieldValue);
                                                                    	     }
                                                                    	 else{
                                                                    		 String s=fieldValue;
                                                                    		 String newName1 = s.substring(0,5)+'1';
                                                                    		 fieldValue=newName1;
                                                                    		 System.out
																				.println("Banana Processing Code"+fieldValue);
                                                                    	 }
																	}
																	
																	CsvRequest = CsvRequest + ","+ fieldID+":"+fieldValue;
																}

															}
															//System.err.println("CSV-REQUEST : " +CsvRequest);	
														}//End for loop
														if (packageName.equalsIgnoreCase("RDBN"))
														{
															Writer output = null;
															File file = new File(Configure.inputFilePath+"\\"+TCID+".txt");
															output = new BufferedWriter(new FileWriter(file));
															output.write(RDBNReq);
															output.close();
															//CAll FUNCTION (i/P file path, o/p path)
														}
														else
														{
															Writer output = null;
															File file = new File(Configure.inputFilePath+"\\"+TCID+".csv");
															output = new BufferedWriter(new FileWriter(file));
															output.write(CsvRequest);
															output.close();
														}
														inCSVFile=Configure.inputFilePath+"\\"+TCID+".csv";
														outLogFile=Configure.outputFilePath+"\\"+TCID+".log";
														System.out.println("Input CSV file path ---> " + inCSVFile);
														System.out.println("Output LOG file path ---> " + outLogFile);
														//}
														//Added by sireesha for storing transaction amount from the request
														if(packageName.equalsIgnoreCase("USSD")&& (transactionName.equalsIgnoreCase("USSDRechargeRequest")))
														{
															if(CsvRequest.split(",")[4].contains("666")==false)
															{
																temp=CsvRequest.split(",")[1].split(":")[1];
																if(temp.split("\\*").length > 2)
																{
																	transactionAmount=temp.split("\\*")[2];	
																	transactionAmount=transactionAmount+".00";
																	hm.put("requestRecharge", transactionAmount);
																}
															}
														}
														if(packageName.equalsIgnoreCase("USSD")&& (transactionName.equalsIgnoreCase("USSDFundTransferRequest")))
														{

															temp=CsvRequest.split(",")[1].split(":")[1];
															transactionAmount=temp.split("\\*")[3];	
															transactionAmount=transactionAmount+".00";
															hm.put("requestFundTransfer", transactionAmount);
														}
														if(transactionName.equalsIgnoreCase("Asynchronous Recharge"))
														{

															System.out.println("In side Asynchronous Recharge POS---------" + CsvRequest.substring(2,6));
															/*if(CsvRequest.substring(2,6).equalsIgnoreCase("0800")==true)
															   {
																   String distID=CsvRequest.split(",")[3].split(":")[1]; 
																   hm.put("hisockDistID", distID);
															   }*/
															String CsvRequest1=CsvRequest.split(",")[3].split(":")[1];	
															//if(DataGenerator.verifyPattern(CsvRequest1)==false)
															if(DataGenerator.verifyPattern(CsvRequest1)==false){
															if((CsvRequest.substring(2,6).equalsIgnoreCase("0200")==true)||(CsvRequest.substring(2,6).equalsIgnoreCase("0220")==true))
															{
																if((CsvRequest.split(",").length==10)||(CsvRequest.split(",").length==11))
																{
																	if(CsvRequest.split(",")[9].split(":")[0].equalsIgnoreCase("42")){
																		String distID=CsvRequest.split(",")[9].split(":")[1];
																		hm.put("AsynchronousSubDistID", distID);
																	}
																}
																transactionAmount=CsvRequest.split(",")[3].split(":")[1];
																long str= Long.parseLong(transactionAmount);
																String s = ""+str;
																String amount=s.substring(0,s.length()-2)+".00";
																//String amount=s.substring(0,s.length()-2);
																System.out.println("Trans amount for HISHOCK----- "+amount);
																hm.put("AsynchronousTransAmount", amount);
																//this for Dist id
																String distIDmain=CsvRequest.split(",")[7].split(":")[1];
																hm.put("AsynchronousMainDistID", distIDmain);
																System.out
																		.println("AsynMainDistributor--->"+distIDmain);
																String systemTraceAuditNumber=CsvRequest.split(",")[4].split(":")[1];
																hm.put("SysTraceAuditNum", systemTraceAuditNumber);
																System.out
																		.println("AsynMainDistributor--->"+distIDmain);
																String AsycnMobileNum=CsvRequest.split(",")[1].split(":")[1];
																hm.put("AsynchronousMobileNum", AsycnMobileNum);
																String AsynchronousMobileNum=CsvRequest.split(",")[1].split(":")[1];
																hm.put("AsynchronousMobileNumber", AsynchronousMobileNum);
																String TerminalID1=CsvRequest.split(",")[8].split(":")[1]; 
																hm.put("AsynchronousTerminalId", TerminalID1);
																System.out
																		.println("AsynchronousTerminalId"+TerminalID1);

															}
															}
															if((CsvRequest.substring(2,6).equalsIgnoreCase("0400")==true))
															{

																if(!(CsvRequest.split(",").length==9))
																{
																	if(CsvRequest.split(",")[9].split(":")[0].equalsIgnoreCase("42")||CsvRequest.split(",")[8].split(":")[0].equalsIgnoreCase("42")){
																		String SubdistID=CsvRequest.split(",")[9].split(":")[1]; 
																		hm.put("AsynchRechargeReversalSubDistID", SubdistID);
																	}
																}
																transactionAmount=CsvRequest.split(",")[3].split(":")[1];
																long str= Long.parseLong(transactionAmount);
																String s = ""+str;
																String amount=s.substring(0,s.length()-2)+".00";
																//String amount=s.substring(0,s.length()-2);
																System.out.println("recharge reversal Trance amount----- "+amount);
																hm.put("AsynchRechargeReversalTransAmount", amount);
																//this for Dist id
																String distID=CsvRequest.split(",")[7].split(":")[1];
																hm.put("AsynchRechargeReversalDistID", distID);
																String RechargeReversalMobileNumber=CsvRequest.split(",")[1].split(":")[1]; 
																hm.put("AsynchRechargeReversalMobileNum", RechargeReversalMobileNumber);
															}
														}
														if(transactionName.equalsIgnoreCase("AsyncCouponValidationStep1"))
														{

															System.out.println("In side Asynchronous Coupon ValidatiomRecharge POS---------" + CsvRequest.substring(2,6));
															String CsvRequest1=CsvRequest.split(",")[3].split(":")[1];
															if(DataGenerator.verifyPattern(CsvRequest1)==false){
															if((CsvRequest.substring(2,6).equalsIgnoreCase("0200")==true))
															{
																if((CsvRequest.split(",").length==10))
																{
																	if(CsvRequest.split(",")[9].split(":")[0].equalsIgnoreCase("42")){
																		String distID=CsvRequest.split(",")[9].split(":")[1];
																		hm.put("AsynCouponValidationSubDistID", distID);
																	}
																}
																transactionAmount=CsvRequest.split(",")[3].split(":")[1];
																long str= Long.parseLong(transactionAmount);
																String s = ""+str;
																String amount=s.substring(0,s.length()-2)+".00";
																System.out.println("Trans amount for HISHOCK----- "+amount);
																hm.put("AsynCouponValidationTransAmount", amount);
																
																String distIDmain=CsvRequest.split(",")[7].split(":")[1];
																hm.put("AsynCouponValidationMainDistID", distIDmain);
																System.out.println("AsynCouponValidationMainDistID--->"+distIDmain);
													
																String AsynchronousMobileNum=CsvRequest.split(",")[1].split(":")[1];
																hm.put("AsynCouponValidationMobileNumber", AsynchronousMobileNum);
																
																String TerminalID1=CsvRequest.split(",")[9].split(":")[1]; 
																hm.put("AsynValidationCouponPin", TerminalID1);
																System.out.println("AsynValidationCouponPin--->"+TerminalID1);

															}
															}
														}
														if(transactionName.equalsIgnoreCase("AsyncCouponRedemptionStep2"))
														{

															System.out.println("In side Asynchronous Coupon ValidatiomRecharge POS---------" + CsvRequest.substring(2,6));
															String CsvRequest1=CsvRequest.split(",")[3].split(":")[1];
															if(DataGenerator.verifyPattern(CsvRequest1)==false){
															if((CsvRequest.substring(2,6).equalsIgnoreCase("0200")==true))
															{
																if((CsvRequest.split(",").length==11))
																{
																	if(CsvRequest.split(",")[9].split(":")[0].equalsIgnoreCase("42")){
																		String distID=CsvRequest.split(",")[9].split(":")[1];
																		hm.put("AsynCouponRedemptionSubDistID", distID);
																	}
																}
																transactionAmount=CsvRequest.split(",")[3].split(":")[1];
																long str= Long.parseLong(transactionAmount);
																String s = ""+str;
																String amount=s.substring(0,s.length()-2)+".00";
																//String amount=s.substring(0,s.length()-2);
																System.out.println("Trans amount for HISHOCK----- "+amount);
																hm.put("AsynCouponRedemptionTransAmount", amount);
																
																String distIDmain=CsvRequest.split(",")[7].split(":")[1];
																hm.put("AsynCouponRedemptionMainDistID", distIDmain);
																System.out.println("AsynCouponRedemptionMainDistID--->"+distIDmain);
													
																String AsynchronousMobileNum=CsvRequest.split(",")[1].split(":")[1];
																hm.put("AsynCouponRedemptionMobileNumber", AsynchronousMobileNum);
																
																String TerminalID1=CsvRequest.split(",")[9].split(":")[1]; 
																hm.put("AsynCouponRedemptionPin", TerminalID1);
																System.out.println("AsynCouponRedemptionPin--->"+TerminalID1);
																
																String CouponPin=CsvRequest.split(",")[11].split(":")[1]; 
																hm.put("AsynCouponValidationId", CouponPin);
																System.out.println("AsynCouponValidationId--->"+TerminalID1);
															}
															}
														}
														if(transactionName.equalsIgnoreCase("AsyncBrandCouponValidationStep1"))
														{

															System.out.println("In side Asynchronous Coupon ValidatiomRecharge POS---------" + CsvRequest.substring(2,6));
															String CsvRequest1=CsvRequest.split(",")[3].split(":")[1];
															if(DataGenerator.verifyPattern(CsvRequest1)==false){
															if((CsvRequest.substring(2,6).equalsIgnoreCase("0200")==true))
															{
																if((CsvRequest.split(",").length==9))
																{
																	if(CsvRequest.split(",")[8].split(":")[0].equalsIgnoreCase("42")){
																		String distID=CsvRequest.split(",")[8].split(":")[1];
																		hm.put("AsyncBrandCouponRedemptionCouponValidationSubDistID", distID);
																		System.out
																				.println("shanananananaa-->"+distID);
																	}
																}
																transactionAmount=CsvRequest.split(",")[3].split(":")[1];
																long str= Long.parseLong(transactionAmount);
																String s = ""+str;
																String amount=s.substring(0,s.length()-2)+".00";
																System.out.println("Trans amount for HISHOCK----- "+amount);
																hm.put("AsyncBrandCouponRedemptionCouponValidationTransAmount", amount);
																
																String distIDmain=CsvRequest.split(",")[7].split(":")[1];
																hm.put("AsyncBrandCouponRedemptionCouponValidationMainDistID", distIDmain);
																System.out.println("AsyncBrandCouponRedemptionCouponValidation--->"+distIDmain);
													
																String AsynchronousMobileNum=CsvRequest.split(",")[1].split(":")[1];
																hm.put("AsyncBrandCouponRedemptionCouponValidationMobileNumber", AsynchronousMobileNum);
															}
															}
														}
														if(transactionName.equalsIgnoreCase("AsyncBrandCouponRedemptionStep2"))
														{

															System.out.println("In side Asynchronous Coupon ValidatiomRecharge POS---------" + CsvRequest.substring(2,6));
															String CsvRequest1=CsvRequest.split(",")[3].split(":")[1];
															if(DataGenerator.verifyPattern(CsvRequest1)==false){
															if((CsvRequest.substring(2,6).equalsIgnoreCase("0200")==true))
															{
																if((CsvRequest.split(",").length==11))
																{
																	if(CsvRequest.split(",")[9].split(":")[0].equalsIgnoreCase("42")){
																		String distID=CsvRequest.split(",")[9].split(":")[1];
																		hm.put("AsyncBrandedCouponRedemptionSubDistID", distID);
																	}
																}
																transactionAmount=CsvRequest.split(",")[3].split(":")[1];
																long str= Long.parseLong(transactionAmount);
																String s = ""+str;
																String amount=s.substring(0,s.length()-2)+".00";
																System.out.println("Trans amount for HISHOCK----- "+amount);
																hm.put("AsyncBrandedCouponRedemptionTransAmount", amount);
																
																String distIDmain=CsvRequest.split(",")[7].split(":")[1];
																hm.put("AsyncBrandedCouponRedemptionMainDistID", distIDmain);
																System.out.println("AsyncBrandedCouponRedemptionMainDistID--->"+distIDmain);
																
																String AsynchronousMobileNum=CsvRequest.split(",")[1].split(":")[1];
																hm.put("AsyncBrandedCouponRedemptionMobileNumber", AsynchronousMobileNum);
															}
															}
														}
														if(transactionName.equalsIgnoreCase("RechargeReversal"))
														{

															System.out.println("in side POS---------"+CsvRequest.substring(2,6));
															String CsvRequest1=CsvRequest.split(",")[3].split(":")[1];	
															if(DataGenerator.verifyPattern(CsvRequest1)==false)
															{
																if(CsvRequest.substring(2,6).equalsIgnoreCase("0200")==true)
																{
																	if(!(CsvRequest.split(",").length==9))
																	{
																		if((CsvRequest.split(",")[9].split(":")[0].equalsIgnoreCase("42"))||(CsvRequest.split(",")[8].split(":")[0].equalsIgnoreCase("42"))){
																			String SubdistID=CsvRequest.split(",")[9].split(":")[1]; 
																			hm.put("RechargeSubDistID", SubdistID);
																		}
																	}
																	//else if(CsvRequest.split(",")[8].split(":")[0].equalsIgnoreCase("41")){
																	String TerminalID1=CsvRequest.split(",")[8].split(":")[1]; 
																	hm.put("terminalId", TerminalID1);
																	//  }
																	String distID=CsvRequest.split(",")[7].split(":")[1]; 
																	hm.put("RechargeDistID", distID);			
																	String RechargeAmounnt=CsvRequest.split(",")[3].split(":")[1]; 
																	hm.put("posReqAmount", RechargeAmounnt);
																	long str= Long.parseLong(RechargeAmounnt);
																	String s = ""+str;
																	String Rechargeamount1=s.substring(0,s.length()-2)+".00";
																	//String Rechargeamount1=s.substring(0,s.length()-2);
																	System.out.println("Trans amount for Pos----- "+Rechargeamount1);
																	hm.put("RechargeAmount", Rechargeamount1);
																	if(CsvRequest.split(",")[1].split(":")[0].equalsIgnoreCase("2")){
																		String RechargeMobileNumber=CsvRequest.split(",")[1].split(":")[1]; 
																		hm.put("RechargeMobileNum", RechargeMobileNumber);
																	}
																	String systemTraceAuditNum=CsvRequest.split(",")[4].split(":")[1]; 
																	hm.put("SysTracAuditNum", systemTraceAuditNum);
																	//
																}
															}
															if((CsvRequest.substring(2,6).equalsIgnoreCase("0400")==true))
															{

																if(!(CsvRequest.split(",").length==9))
																{
																	if(CsvRequest.split(",")[9].split(":")[0].equalsIgnoreCase("42")||CsvRequest.split(",")[8].split(":")[0].equalsIgnoreCase("42")){
																		String SubdistID=CsvRequest.split(",")[9].split(":")[1]; 
																		hm.put("RechargeReversalSubDistID", SubdistID);
																	}
																}
																transactionAmount=CsvRequest.split(",")[3].split(":")[1];
																long str= Long.parseLong(transactionAmount);
																String s = ""+str;
																String amount=s.substring(0,s.length()-2)+".00";
																//String amount=s.substring(0,s.length()-2);
																System.out.println("recharge reversal Trance amount----- "+amount);
																hm.put("RechargeReversalTransAmount", amount);
																//this for Dist id
																String distID=CsvRequest.split(",")[7].split(":")[1];
																hm.put("RechargeReversalDistID", distID);
																String RechargeReversalMobileNumber=CsvRequest.split(",")[1].split(":")[1]; 
																hm.put("RechargeReversalMobileNum", RechargeReversalMobileNumber);
															}
														}
														if(transactionName.equalsIgnoreCase("Pinpurchase_PinReversal"))
														{

															System.out.println("in side POS---------"+CsvRequest.substring(2,6));
															String CsvRequest1=CsvRequest.split(",")[2].split(":")[1];	
															if(DataGenerator.verifyPattern(CsvRequest1)==false)
															{
																if(CsvRequest.substring(2,6).equalsIgnoreCase("0200")==true)
																{
																	if(!(CsvRequest.split(",").length==11))
																	{
																		if(CsvRequest.split(":").length>=10)
																		{
																		if((CsvRequest.split(",")[8].split(":")[0].equalsIgnoreCase("42"))||(CsvRequest.split(",")[9].split(":")[0].equalsIgnoreCase("42"))){
																			String SubdistID=CsvRequest.split(",")[8].split(":")[1]; 
																			hm.put("PinPurchaseSubDistID", SubdistID);
																			System.out
																			.println("subdistributor id --->>>"+SubdistID);
																		}
																		}
																	}
																	//else if(CsvRequest.split(",")[8].split(":")[0].equalsIgnoreCase("41")){
																	//String TerminalID1=CsvRequest.split(",")[8].split(":")[1]; 
																	//hm.put("terminalId", TerminalID1);
																	//  }
																	String distID=CsvRequest.split(",")[6].split(":")[1]; 
																	hm.put("PinPurchaseMainDistID", distID);			
																	String RechargeAmounnt=CsvRequest.split(",")[2].split(":")[1]; 
																	hm.put("PinpurchaseAmount111", RechargeAmounnt);
																	long str= Long.parseLong(RechargeAmounnt);
																	String s = ""+str;
																	String Rechargeamount1=s.substring(0,s.length()-2)+".00";
																	//String Rechargeamount1=s.substring(0,s.length()-2);
																	System.out.println("Trans amount for Pos----- "+Rechargeamount1);
																	hm.put("PinpurchaseAmount", Rechargeamount1);
																	
																	//String systemTraceAuditNum=CsvRequest.split(",")[4].split(":")[1]; 
																	//hm.put("SysTracAuditNum", systemTraceAuditNum);
																	//
																}
															}
															if((CsvRequest.substring(2,6).equalsIgnoreCase("0400")==true))
															{

																if(!(CsvRequest.split(",").length==11))
																{
																	if(CsvRequest.split(":").length>=10){
																	if(CsvRequest.split(",")[8].split(":")[0].equalsIgnoreCase("42")||CsvRequest.split(",")[9].split(":")[0].equalsIgnoreCase("42")){
																		String SubdistID=CsvRequest.split(",")[8].split(":")[1]; 
																		hm.put("PinReversalSubDistID", SubdistID);
																		System.out
																				.println("subdistributor id --->>>"+SubdistID);
																		
																	}
																	}
																}
																transactionAmount=CsvRequest.split(",")[2].split(":")[1];
																long str= Long.parseLong(transactionAmount);
																String s = ""+str;
																String amount=s.substring(0,s.length()-2)+".00";
																//String amount=s.substring(0,s.length()-2);
																System.out.println("recharge reversal Trance amount----- "+amount);
																hm.put("PinReversalTransAmount", amount);
																//this for Dist id
																String distID=CsvRequest.split(",")[6].split(":")[1];
																hm.put("RechargeReversalDistID", distID);
																
															}
														}
														
														if(transactionName.equalsIgnoreCase("BrandedCouponRedemption"))
														{
															try{

															System.out.println("in side POS---------"+CsvRequest.substring(2,6));
															String CsvRequest1=CsvRequest.split(",")[2].split(":")[1];	
															if(DataGenerator.verifyPattern(CsvRequest1)==false)
															{
																if(CsvRequest.substring(2,6).equalsIgnoreCase("0200")==true)
																{
																	if(!(CsvRequest.split(",").length==11))
																	{
																		if(CsvRequest.split(":").length>=10)
																		{
																		if((CsvRequest.split(",")[9].split(":")[0].equalsIgnoreCase("42"))||(CsvRequest.split(",")[6].split(":")[0].equalsIgnoreCase("42"))){
																			String SubdistID=CsvRequest.split(",")[9].split(":")[1]; 
																			hm.put("RedimSubDistID", SubdistID);
																			System.out
																			.println("RedimSubDistID--->>>"+SubdistID);
																		}
																		}
																	}
																
																	String RedimSubscriberId=CsvRequest.split(",")[1].split(":")[1]; 
																	hm.put("RedimMobileNum", RedimSubscriberId);
																	System.out
																			.println("Redim moblenum"+RedimSubscriberId);
																	String distID=CsvRequest.split(",")[5].split(":")[1]; 
																	hm.put("RedimMainDistID", distID);			
																	String RechargeAmounnt=CsvRequest.split(",")[3].split(":")[1]; 
																	long str= Long.parseLong(RechargeAmounnt);
																	String s = ""+str;
																	String Rechargeamount1=s.substring(0,s.length()-2)+".00";
																	System.out.println("RedimRechargeAmount----- "+Rechargeamount1);
																	hm.put("RedimRechargeAmount", Rechargeamount1);
																	
																	//String systemTraceAuditNum=CsvRequest.split(",")[4].split(":")[1]; 
																	//hm.put("SysTracAuditNum", systemTraceAuditNum);
																	//
																}
															}
														}catch(Exception e){
															e.printStackTrace();
														}
														}

														// This is to store value from the request
														
														if(packageName.equalsIgnoreCase("USSD"))
														{
															//new USSDInitiator().callUSSDSimulator(TCID);
															Thread.sleep(2000);//changed time 5000
														}
														else if(packageName.equalsIgnoreCase("RDBN"))
														{
															RDBNGWClient rdbngwClient = new RDBNGWClient();
															rdbngwClient.callRDBNSimulator(TCID);
															Thread.sleep(2000);// changed time 5000
														}
														else
														{
															callSimulator.connectChannel(inCSVFile, packageName);
															Thread.sleep(2000);
														}

													}catch(Exception e){
														e.printStackTrace();
													}
													}
													else if (StepLstNode.getNodeName().equalsIgnoreCase("IVRRequest"))
													{	
														System.out.println("in side IVRRequest");
														String packageName=null;
														String transactionName=null;
														inCSVFile=null;
														outLogFile=null;
														int reqStepCount  = StepLstNode.getChildNodes().getLength();
														NodeList fieldNodeLst = StepLstNode.getChildNodes();
														String CsvRequest = "";
														for (int field=5; field<reqStepCount; field++)
														{
															Node FieldNode = fieldNodeLst.item(field);
															if(FieldNode.getNodeType() == Node.ELEMENT_NODE) 
															{
																packageName=getAttribute(fieldNodeLst.item(3),"Val");
																System.out.println("Package ---> " + packageName);
																transactionName=getAttribute(fieldNodeLst.item(1),"Val");
																System.out.println("transactionName ---> " + transactionName);
																String fieldValue = getAttribute(FieldNode,"Val");
																String fieldID = getAttribute(FieldNode,"ID");
																System.out.println("Field Value : " + fieldValue);
																System.out.println("Field ID : " + fieldID);
															
																if(fieldValue.indexOf("readProperty-")>=0){
																	String tempfield=fieldValue.split("-")[1];
																	fieldValue=Locators.getLocators(tempfield).trim();
																	System.out
																			.println("Ivr Read form Property file"+fieldValue);
																	
																}
																if (fieldValue.indexOf("get-")>=0)
																{
																	if (fieldValue.indexOf("*")>=0)
																	{
																		String tempfield=fieldValue.split("-")[1];
																		String actTempText=tempfield.replace("*", "-");
																		String actText=actTempText.split("-")[0];
																		fieldValue=fieldValue.replaceAll("get-"+actText, hm.get(actText));
																	}
																	else if (fieldValue.indexOf("randomNumber")>=0)
																	{
																		System.out.println("generate random number");
																		String[] result=fieldValue.split(":");
																		lengthofNum=Integer.parseInt( result[1]);
																		fieldValue=DataGenerator.randomNumberGenerator(lengthofNum);
																		hm.put("payRefNum", fieldValue);
																	}
																	else
																	{
																		fieldValue=hm.get(fieldValue.split("-")[1]);
																	}
																}
																if(transactionName.equalsIgnoreCase("BankReplenishmentRequestEncryptedKey")){
																	if((fieldID.equalsIgnoreCase("NEWACCOUNTNO")&&(fieldValue!="") )||(fieldID.equalsIgnoreCase("NEWSECCODE")&&(fieldValue!=null))||(fieldID.equalsIgnoreCase("TAXRUC")&&(fieldValue!="") )){
																		String ss=IVRSimulator.EncriptData(fieldValue);
																		
																		fieldValue=ss;
																		
																		System.out
																				.println("getting enc Dada-->"+fieldValue);
																		
																		//fieldValue=encryptedString;
																	}
																	
																}
																if (field == 5)
																{
																	CsvRequest = fieldID+"="+fieldValue;
																}
																else
																{
																	
																	
																	if(fieldID.equalsIgnoreCase("OPERATORIDENTITY")){
																		
																		if(Locators.getLocators("OperatorIdentity").equalsIgnoreCase("Movistar")){
                                                                   		 
                                                                   		 System.out
																					.println("Movister Processing Code"+fieldValue);
                                                                   	     }
                                                                   	 else{
                                                                   		 String s="01";
                                                                   		fieldValue=s;
                                                                   		 
                                                                   		 System.out
																				.println("Banana Processing Code"+fieldValue);
                                                                   	 }
																		
																	}
																	
																	CsvRequest = CsvRequest + ";"+ fieldID+"="+fieldValue;
																}
															}
														}//End for loop
														if(packageName.equalsIgnoreCase("IVRATM")){
															CsvRequest=CsvRequest;
															IVRREQUEST=CsvRequest;
															System.err.println("CSV-REQUEST : " +CsvRequest);
															Writer output = null;
															File file = new File(Configure.inputFilePath+"\\"+TCID+".csv");
															output = new BufferedWriter(new FileWriter(file));
															output.write(CsvRequest);
															output.close();
															inCSVFile=Configure.inputFilePath+"\\"+TCID+".txt";
														}else{
														CsvRequest=CsvRequest + ";$";
														IVRREQUEST=CsvRequest;
														SeleniumFW.APPLICATION_LOGS.info("IVR ATM REQUEST:"+IVRREQUEST);
														System.err.println("CSV-REQUEST : " +CsvRequest);
														Writer output = null;
														File file = new File(Configure.inputFilePath+"\\"+TCID+".csv");
														output = new BufferedWriter(new FileWriter(file));
														output.write(CsvRequest);
														output.close();
														inCSVFile=Configure.inputFilePath+"\\"+TCID+".txt";
														}
														if(packageName.equalsIgnoreCase("IVR"))
														{
															System.out.println("CsvRequest request:" +CsvRequest);
															IVRSimulator.connectIVR(CsvRequest,packageName,transactionName,TCID);
															//Thread.sleep(2000);
														}
														else if(packageName.equalsIgnoreCase("IVRATM")){
															atmBean=new ATMBean();
															int msgCount=1;
															String OPERATORIDENTITY;
															if(Locators.getLocators("OperatorIdentity").equalsIgnoreCase("Movistar")){
																OPERATORIDENTITY="00";
															}else{
																OPERATORIDENTITY="01";
															}
															
															System.out.println("CsvRequest request:" +CsvRequest);
															SeleniumFW.APPLICATION_LOGS.info("IVRATM--CSV--Req: "+CsvRequest);
															ATMClientSimulator ss=new ATMClientSimulator();
															
															if(transactionName.equals("AccreditationOfMinutesRequest")){
																SeleniumFW.APPLICATION_LOGS.info("*** AccreditationOfMinutesRequest ***");
																String MessageType="22";
																String[] testData = CsvRequest.split(";");
																String s3=testData[0].split("=")[1];
																String s4=testData[1].split("=")[1];
																atmBean.setAmount(s4);
																atmBean.setSubid(s3);
																atmBean.setCarrierId(OPERATORIDENTITY);
																atmBean.setMsgNum(MessageType);	
		
																ss.createMessage(atmBean,msgCount,OPERATORIDENTITY,packageName,TCID,transactionName);		
															}
															else if(transactionName.equals("AtmValidateTelephonerequest")){
																SeleniumFW.APPLICATION_LOGS.info("*** AtmValidateTelephonerequest ***");
																String MessageType="21";
																String[] testData = CsvRequest.split(";");
																String s3=testData[0].split("=")[1];
																String s4=testData[1].split("=")[1];
																atmBean.setAmount(s4);
																atmBean.setSubid(s3);
																atmBean.setCarrierId(OPERATORIDENTITY);
																atmBean.setMsgNum(MessageType);	
		
																ss.createMessage(atmBean,msgCount,OPERATORIDENTITY,packageName,TCID,transactionName);		
															}
															else if(transactionName.equals("AtmReversalRequest")){
																SeleniumFW.APPLICATION_LOGS.info("*** AtmReversalRequest ***");
																String MessageType="24";
																String[] testData = CsvRequest.split(";");
																String s3=testData[0].split("=")[1];
																String s4=testData[1].split("=")[1];
																String s5=testData[2].split("=")[1];
																atmBean.setAmount(s4);
																atmBean.setSubid(s3);
																atmBean.setRevno(s5);
																atmBean.setCarrierId(OPERATORIDENTITY);
																atmBean.setMsgNum(MessageType);	
																atmBean.setCarrierId(OPERATORIDENTITY);
																//atmBean.carrierId(OPERATORIDENTITY);
		
																ss.createMessage(atmBean,msgCount,OPERATORIDENTITY,packageName,TCID,transactionName);		
															}
		
															else if(transactionName.equals("AtmPinReversalRequest")){
																SeleniumFW.APPLICATION_LOGS.info("*** AtmPinReversalRequest ***");
																String MessageType="24";
																String[] testData = CsvRequest.split(";");
																String s4=testData[0].split("=")[1];
																String s5=testData[1].split("=")[1];
																atmBean.setAmount(s4);
																atmBean.setRevno(s5);
																atmBean.setCarrierId(OPERATORIDENTITY);
																atmBean.setMsgNum(MessageType);	
		
																ss.createMessage(atmBean,msgCount,OPERATORIDENTITY,packageName,TCID,transactionName);		
															}
		
															else if(transactionName.equals("AtmPinPurchase")){
																SeleniumFW.APPLICATION_LOGS.info("*** AtmPinPurchase ***");
																String MessageType="23";
																String[] testData = CsvRequest.split(";");
																String s4=testData[0].split("=")[1];
																atmBean.setAmount(s4);
																//atmBean.setSubid(s3);
																atmBean.setCarrierId(OPERATORIDENTITY);
																atmBean.setMsgNum(MessageType);	
		
																ss.createMessage(atmBean,msgCount,OPERATORIDENTITY,packageName,TCID,transactionName);		
															}
															
														}
													
														
														else if(packageName.equalsIgnoreCase("IVRBNI")){
															bniBean=new BNIBean();
															int msgCount=1;
															String OPERATORIDENTITY;
															if(Locators.getLocators("OperatorIdentity").equalsIgnoreCase("Movistar")){
																OPERATORIDENTITY="00";
															}else{
																OPERATORIDENTITY="01";
															}
															
															System.out.println("CsvRequest request:" +CsvRequest);
															SeleniumFW.APPLICATION_LOGS.info("IVRATM--CSV--Req: "+CsvRequest);
															BNIClient sss=new BNIClient(OPERATORIDENTITY);
															
															if(transactionName.equals("BNIRecharge")){
																SeleniumFW.APPLICATION_LOGS.info("*** BNIRechargeRequest ***");
																
																
																
;
																if(Locators.getLocators("OperatorIdentity").equalsIgnoreCase("Movistar")){
																	OPERATORIDENTITY="00";
																}else{
																	OPERATORIDENTITY="01";
																}

																String NetworkID="UNIBANCO";
																String TerminalId="1";
																String MessageNumber="002";
																
																String[] testData = CsvRequest.split(";");
																String SubScriberNumber=testData[0].split("=")[1];
																String RechargeAmount=testData[1].split("=")[1];
																
																bniBean.setNetworkId(NetworkID);
																bniBean.setMessageNumber(MessageNumber);
																bniBean.setMobileNumber(SubScriberNumber);
																bniBean.setRechargeAmount(RechargeAmount);
																bniBean.setOperatorIdentity(OPERATORIDENTITY);
																//atmBean.setAmount(s4);
																//atmBean.setSubid(s3);
																//atmBean.setCarrierId(OPERATORIDENTITY);
																//atmBean.setMsgNum(MessageType);	
		
																sss.createMessage(bniBean,msgCount,OPERATORIDENTITY,packageName,TCID,transactionName);		
															}
															
															else if(transactionName.equals("BNIRechargeReversal")){
																SeleniumFW.APPLICATION_LOGS.info("*** BNIRechargeRequest ***");
																
																
																
;
																if(Locators.getLocators("OperatorIdentity").equalsIgnoreCase("Movistar")){
																	OPERATORIDENTITY="00";
																}else{
																	OPERATORIDENTITY="01";
																}

																String NetworkID="UNIBANCO";
																//String TerminalId="1";
																String MessageNumber="004";
																
																String[] testData = CsvRequest.split(";");
																String SubScriberNumber=testData[0].split("=")[1];
																String RechargeAmount=testData[1].split("=")[1];
																String transactionIdforReversal=testData[2].split("=")[1];
																String Carriertransactionno=testData[3].split("=")[1];
																
																bniBean.setNetworkId(NetworkID);
																bniBean.setMessageNumber(MessageNumber);
																bniBean.setMobileNumber(SubScriberNumber);
																bniBean.setRechargeAmount(RechargeAmount);
																bniBean.setOperatorIdentity(OPERATORIDENTITY);
																bniBean.setReversaCarrrNum(transactionIdforReversal);//transID   Carriertransactionno
																bniBean.setBankTransId(Carriertransactionno);//recharge BTRNO  transactionIdforReversal
																//atmBean.setAmount(s4);
																//atmBean.setSubid(s3);
																//atmBean.setCarrierId(OPERATORIDENTITY);
																//atmBean.setMsgNum(MessageType);	
		
																sss.createMessage(bniBean,msgCount,OPERATORIDENTITY,packageName,TCID,transactionName);		
															}
															/*else if(transactionName.equals("AtmValidateTelephonerequest")){
																SeleniumFW.APPLICATION_LOGS.info("*** AtmValidateTelephonerequest ***");
																String MessageType="21";
																String[] testData = CsvRequest.split(";");
																String s3=testData[0].split("=")[1];
																String s4=testData[1].split("=")[1];
																atmBean.setAmount(s4);
																atmBean.setSubid(s3);
																atmBean.setCarrierId(OPERATORIDENTITY);
																atmBean.setMsgNum(MessageType);	
		
																ss.createMessage(atmBean,msgCount,OPERATORIDENTITY,packageName,TCID,transactionName);		
															}
															else if(transactionName.equals("AtmReversalRequest")){
																SeleniumFW.APPLICATION_LOGS.info("*** AtmReversalRequest ***");
																String MessageType="24";
																String[] testData = CsvRequest.split(";");
																String s3=testData[0].split("=")[1];
																String s4=testData[1].split("=")[1];
																String s5=testData[2].split("=")[1];
																atmBean.setAmount(s4);
																atmBean.setSubid(s3);
																atmBean.setRevno(s5);
																atmBean.setCarrierId(OPERATORIDENTITY);
																atmBean.setMsgNum(MessageType);	
																atmBean.setCarrierId(OPERATORIDENTITY);
																//atmBean.carrierId(OPERATORIDENTITY);
		
																ss.createMessage(atmBean,msgCount,OPERATORIDENTITY,packageName,TCID,transactionName);		
															}
		
															else if(transactionName.equals("AtmPinReversalRequest")){
																SeleniumFW.APPLICATION_LOGS.info("*** AtmPinReversalRequest ***");
																String MessageType="24";
																String[] testData = CsvRequest.split(";");
																String s4=testData[0].split("=")[1];
																String s5=testData[1].split("=")[1];
																atmBean.setAmount(s4);
																atmBean.setRevno(s5);
																atmBean.setCarrierId(OPERATORIDENTITY);
																atmBean.setMsgNum(MessageType);	
		
																ss.createMessage(atmBean,msgCount,OPERATORIDENTITY,packageName,TCID,transactionName);		
															}
		
															else if(transactionName.equals("AtmPinPurchase")){
																SeleniumFW.APPLICATION_LOGS.info("*** AtmPinPurchase ***");
																String MessageType="23";
																String[] testData = CsvRequest.split(";");
																String s4=testData[0].split("=")[1];
																atmBean.setAmount(s4);
																//atmBean.setSubid(s3);
																atmBean.setCarrierId(OPERATORIDENTITY);
																atmBean.setMsgNum(MessageType);	
		
																ss.createMessage(atmBean,msgCount,OPERATORIDENTITY,packageName,TCID,transactionName);		
															}
															*/
														}
														
														
														
														
														
														
														
														
														
													}
													else if (StepLstNode.getNodeName().equalsIgnoreCase("RequestToWebService"))
													{
														dto=new DTO();
													System.out.println("in side BulkUpload");
													String packageName=null;
													String transactionName=null;
													inCSVFile=null;
													outLogFile=null;
													String CsvRequest1=null;
													int reqStepCount  = StepLstNode.getChildNodes().getLength();
													NodeList fieldNodeLst = StepLstNode.getChildNodes();
													//String CsvRequest = "";
													for (int field=5; field<reqStepCount; field++)
													{
														Node FieldNode = fieldNodeLst.item(field);
														if(FieldNode.getNodeType() == Node.ELEMENT_NODE) 
														{
															packageName=getAttribute(fieldNodeLst.item(3),"Val");
															
															webServicesTransactionName=packageName;
															//System.out.println("Package ---> " + packageName);
															transactionName=getAttribute(fieldNodeLst.item(1),"Val");
															//System.out.println("transactionName ---> " + transactionName);
															String fieldValue = getAttribute(FieldNode,"Val");
															String fieldID = getAttribute(FieldNode,"ID");
															//System.out.println("Field Value : " + fieldValue);
															//System.out.println("Field ID : " + fieldID);
															if (fieldValue.indexOf("get-")>=0)
															{
																if (fieldValue.indexOf("*")>=0)
																{
																	String tempfield=fieldValue.split("-")[1];
																	String actTempText=tempfield.replace("*", "-");
																	String actText=actTempText.split("-")[0];
																	fieldValue=fieldValue.replaceAll("get-"+actText, hm.get(actText));
																}
																else if (fieldValue.indexOf("randomNumber")>=0)
																{
																	//System.out.println("generate random number");
																	String[] result=fieldValue.split(":");
																	lengthofNum=Integer.parseInt( result[1]);
																	fieldValue=DataGenerator.randomNumberGenerator(lengthofNum);
																}/*else if(fieldValue.indexOf("randomName")>=0)
																{
																	String[] result=sValue.split(":");
																	int lengthofname = Integer.parseInt( result[1]);
																	sValue=DataGenerator.randomName (lengthofname);
																}*/
																else
																{
																	if(hm.containsValue(hm.get(fieldValue.split("-")[1])) == true)
																	{
																		fieldValue=hm.get(fieldValue.split("-")[1]);
																		// System.out.println("fieldvalue in second iteration "+fieldValue);
																	}
																	//fieldValue=hm.get(fieldValue.split("-")[1]);
																}
															}
															if (field == 5)
															{
																CsvRequest =fieldValue;
																CsvRequest1 = fieldID;
															}
															else
															{
																CsvRequest = CsvRequest + ","+fieldValue;
																CsvRequest1 = CsvRequest1 + ","+ fieldID;	
																fieldDesc=CsvRequest1;
																xmlRequest=CsvRequest.trim();
															}
														}
													}//End for loop
													System.err.println("CSV-REQUEST : " +CsvRequest);
													/*if (CsvRequest.endsWith(" "))
													{
														CsvRequest=CsvRequest + ",";
														System.out.println("CsvRequest in the ends wit------------->" +CsvRequest);
													}*/
													if(packageName.equalsIgnoreCase("couponSerialNumberEnquiry"))
													{
														SaaJSoap soapRequest=null;
														SOAPConnection soapConnection1=null;
														SaaJSoap soapRequest1=null;
														String[] testData = CsvRequest.split(",");
														SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
														SOAPConnection soapConnection = soapConnectionFactory.createConnection();
														if (transactionName.equalsIgnoreCase("WSCancelWithdrawalTicketRequest"))
														{
															soapConnection1 = soapConnectionFactory.createConnection();
														}
														String endpoint=Configure.couponMangementendPoint;
														System.out.println("mWalletendPoint----" + endpoint);
														//String endpoint = "http://192.168.149.108:8080/services/mWalletServiceSOAP";
														if(transactionName.equalsIgnoreCase("WebServicescouponSerialNumberEnquiry"))												
														{
															
															dto.setUserName(testData[0]);
															dto.setPassword(testData[1]);
															dto.setCouponSerialNumber(testData[2]);
															//soapRequest=new SaaJSoap(testData[0], testData[1], testData[2]);
														
														}
														soapRequest=new SaaJSoap();
														SOAPMessage soapResponse = soapConnection.call(soapRequest.createSOAPRequest(transactionName,packageName,testData.length,dto), endpoint);
														wsresponce=soapRequest.printSOAPResponse(soapResponse);
														System.out.println("Web Service request----->" +transactionName   +SaaJSoap.reqMsg);
														System.out.println("Web Service responce----->" +transactionName   +wsresponce);
														soapReq=SaaJSoap.reqMsg;
														soapRes=wsresponce;
														soapConnection.close();
													}

													//for Request 
													Writer output = null;
													File file = new File(Configure.inputFilePath+"\\"+TCID+"_"+System.currentTimeMillis()+transactionName+"Request"+".xml");
													output = new BufferedWriter(new FileWriter(file));
													output.write(soapReq);
													output.close();
													//for Response
													file = new File(Configure.outputFilePath+"\\"+TCID+"_"+System.currentTimeMillis()+transactionName+"Response"+".xml");
													output = new BufferedWriter(new FileWriter(file));
													output.write(soapRes);
													output.close();
													//transName=transactionName;
												}
													
													else if (StepLstNode.getNodeName().equalsIgnoreCase("Expected"))
													{
														ExpScreenshot = ExpScreenshot +1;
														sspath = "Screenshot\\screenshot-TC-"+TCID+"-Expected-"+ExpScreenshot+".png";
														ExpScreenshotPath  = Configure.resultlog+"\\"+sspath;

														tcResult = "Pass";
														
						/*								*//***************************//*
														eAction = getAttribute(StepLstNode,"ExpAction");
														eVal = getAttribute(StepLstNode,"ExpVal");
														System.out.println(StepLstNode.getAttributes().getNamedItem("ExpRefName"));
														if(StepLstNode.getAttributes().getNamedItem("ExpRefName") != null)
														{
														System.out.println("yesssss");
														ot = objxml.getObjectName(getAttribute(StepLstNode,"ExpRefName"));
														eTarget = ot.getObjectName();

														}
														else
														{
														System.out.println("Nooooooooo");
														eTarget = getAttribute(StepLstNode,"ExpTarget");
														}
														
														*//***************************//*	*/										
														
														eAction = getAttribute(StepLstNode,"ExpAction");
														//eTarget = getAttribute(StepLstNode,"ExpTarget");
														eVal = getAttribute(StepLstNode,"ExpVal");
														ot = objxml.getObjectName(getAttribute(StepLstNode,"ExpRefName"));
														eTarget = ot.getObjectName();
														System.out.println(eTarget);
																					
														if((eAction.equalsIgnoreCase("verifyValueinExcel"))	||(eAction.equalsIgnoreCase("verifyValueinPDF")))
														{		
															tcResult1 = objexp.expected(eAction,eTarget, eVal, waitTime,dto);
															value=Expected.actual;
															//		System.out.println("Value of Actual is  : " + value);
															Path = "";				
															//String tcRes = null;
															if (tcResult1 == true)
															{
																tcResult = "Pass";
																System.out.println(eTarget+" present");
															}
															else
															{	
																try{
																tcResult = "Fail";
																System.out.println(eTarget+" not present");
																objSel.captureScreenshot(ExpScreenshotPath);
																Path = sspath;//change comments
																tcRes = "Fail";//change comments
																}catch(Exception e){
																	e.printStackTrace();
																}
															}	
														}	
														else
														{
														tcResult1 = objexp.expected(eAction,eTarget, eVal, waitTime,dto);
														value=Expected.actual;
														System.out.println("Value of Actual is  : " + value);
														Path = "";				
														//String tcRes = null;
														if (tcResult1 == true)
															tcResult = "Pass";
														else
														{	try{
															tcResult = "Fail";
															ObjHighlight=ot.getObjectName();
															objSel.captureScreenshot(ExpScreenshotPath);
															Path = sspath;
															tcRes = "Fail";
														}catch(Exception e){
															e.printStackTrace();
														}
														}
														//if(tcRes=="Fail" || stepResult=="Fail")
														//{
														//	finalResult = "Fail";
														//}	
														}
														if ((eAction.equalsIgnoreCase("verifyItemsinComboBox"))||(eAction.equalsIgnoreCase("VerifyValue"))||(eAction.equalsIgnoreCase("waitForValue"))||(eAction.equalsIgnoreCase("verifyTable"))|| (eAction.equalsIgnoreCase("verifyTextInTable"))||(eAction.equalsIgnoreCase("waitForValue")))
														{
															String[] resTableValue=null;
															if (eVal.split(",").length==4)
															{
																resTableValue=eVal.split(",");
																eVal=resTableValue[0]+","+resTableValue[1];
															}
															else
															{
																resTableValue=eVal.split(",");
																eVal=resTableValue[0];
															}
															if ((eVal.equalsIgnoreCase("randomProfileName"))||(eVal.equalsIgnoreCase("randomUserName"))||(eVal.equalsIgnoreCase("randomDistributorName")))
															{
																eVal=DataGenerator.randName;
															}
															if (eVal.indexOf("prevoiusRandomUserName")==0)
															{
															}
															if (eVal.indexOf("prevoiusRandomProfileName")==0)
															{
																eVal=prevoiusRandomProfileName;
															}
															if(eVal.indexOf("get")>=0)
															{
																if(hm.get(eVal.split("-")[1])!=null)
																{
																eVal=hm.get(eVal.split("-")[1]);
																eTarget = eVal;
																}
																else
																{
																	eVal="Value Not stored";
																	eTarget=eVal;
																}
															}
															if(eVal.indexOf("readProperty")>=0)
															{
																
																eVal=Locators.getLocators(eVal.split("-")[1].trim());
																if(eVal!=null)
																{
																eTarget = eVal;	
																}
																else
																{
																eVal="Value Not stored";
																eTarget=eVal;
																}
															}
															eTarget = eVal;
														}
														else if (eAction.equalsIgnoreCase("verifyTextPresent"))  
														{
															if (eTarget.indexOf("randomProfileName")>=0)
															{
																String temptext=eTarget.replaceAll("randomProfileName", DataGenerator.randName);
																eTarget=temptext;
															}
															if (eTarget.indexOf("${")==0)
															{
																str = eTarget.substring(eTarget.indexOf("{")+1,eTarget.indexOf("}"));
																mapkey = hm.containsKey(str);
																if (mapkey == true)
																	eTarget = hm.get(str).toString();
															}
															eTarget=eVal;
														}
														else if ((eAction.equalsIgnoreCase("verifyBalanceValues"))||(eAction.equalsIgnoreCase("verifyValuesinDb"))||(eAction.equalsIgnoreCase("verifyTableContent"))||(eAction.equalsIgnoreCase("verifyTableSearch"))||(eAction.equalsIgnoreCase("verifyItemsinComboBox"))||(eAction.equalsIgnoreCase("verifyItemsNotInComboBox"))||(eAction.equalsIgnoreCase("verifySelectedLabel"))||(eAction.equalsIgnoreCase("waitForSelectedLabel"))||(eAction.equalsIgnoreCase("verifyChecked")))  
														{
															if (eVal.indexOf("get")>=0)
															{
																eVal=hm.get(eVal.split("-")[1]);
																eTarget = eVal;	
															}
															eTarget = eVal;
														}
														else if (eAction.equalsIgnoreCase("verifyElementNotPresent"))
														{
													      eTarget=eVal;
														}
														else if (eAction.equalsIgnoreCase("verifyDynamicMessage"))
														{
															if(eVal.indexOf("get")>=0)
															{
																eVal=hm.get(eVal.split("-")[1]);
																eTarget = eVal;	
															}
															else{
															eTarget = eVal;
															}
														}

														else if(eAction.equalsIgnoreCase("verifyText"))
														{
															if(eVal.indexOf("get")==0||eVal.indexOf("fetch")==0)
															{
																if((eVal.contains("subtraction"))||(eVal.contains("Addition")))
																{
																	eTarget = Double.toString(Expected.expBalance);
																}
																else{
																	eVal=hm.get(eVal.split("-")[1]);
																	eTarget = eVal;
																}
															}
															/*if((eVal.contains("subtraction"))||(eVal.contains("Addition")))
															{
																//eTarget = Double.toString(Expected.expBalance);
																eVal = Double.toString(Expected.expBalance);
																eTarget = eVal;
																System.out
																		.println("E target Value-->"+eTarget);
										 					}*/
															/*if(eVal.indexOf("get")==0)
															{
																eVal=hm.get(eVal.split("-")[1]);
																eTarget = eVal;	
															}*/
															else
															{
															eTarget = eVal;
															}
														}
														
														else if(eAction.equalsIgnoreCase("verifyElementInWebTable"))
														{
															//get-transactionID:4:ReverseButtonInReversal:Null
															eVal=eVal.split(":")[2];
															eTarget=eVal;
														}
														if (eAction.equalsIgnoreCase("verifyFromSimulator")||(eAction.equalsIgnoreCase("verifyFromWebSimulator")||(eAction.equalsIgnoreCase("verifyFromAtmWebSimulator")||eAction.equalsIgnoreCase("verifyFromUSSDSimulator"))))
														{			
															if(eVal.indexOf("get")>=0)
															{
																eVal=hm.get(eVal.split("-")[1]);
																eTarget = eVal;	
															}
															eTarget=eVal;										
														}
														if (eAction.equalsIgnoreCase("verifyValueinDB"))
														{															
															eTarget=Expected.valFromDB;										
														}
														if (eAction.equalsIgnoreCase("verifyTableSearch"))
														{															
															eTarget=Expected.expVal1;										
														}
														//XMLResultsModifier.XmlExpModifier( StepLstNode.getNodeName(), eTarget,  Expected.actual, tcResult, Time, Path, eAction,ot.getRefName());																		
														XMLResultsModifier.XmlExpModifier( StepLstNode.getNodeName(), eTarget,  Expected.actual, tcResult, Time, Path, eAction,ot.getRefName(),ot.getObjectName());																		
													} //Expected Node
													//HeaderExp = docInput.getElementsByTagName("ExpectedResult").item(0).getTextContent();
													if(tcRes=="Fail" || stepResult=="Fail")
													{
														finalResult = "Fail";
													}
													if (finalResult == "Pass")
														if (StepLstNode.getNodeName().equalsIgnoreCase("ActualResult_Pass"))
														{														
															System.out.println("-----------------------PassTag-------------------");
															SeleniumFW.APPLICATION_LOGS.info("-----------------------PassTag-------------------");
															SeleniumFW.APPLICATION_LOGS.info("-----------------------End Test case----------------");
															passText = getInnerText(StepLstNode);
															tcResDescVal = passText;
															tcResVal = "Pass";
															HeaderNode = StepLstNode.getNodeName();
															Session.deleteCookie();
														}
													if (finalResult == "Fail")
														if (StepLstNode.getNodeName().equalsIgnoreCase("ActualResult_Fail"))
														{
															System.out.println("-----------------------FailTag-------------------");
															SeleniumFW.APPLICATION_LOGS.info("=================================== Test Cases Fail ==================================");
															SeleniumFW.APPLICATION_LOGS.info("-----------------------End Test case-------------------");
															failText = getInnerText(StepLstNode);
															tcResDescVal = failText;
															tcResVal = "Fail";
															HeaderNode = StepLstNode.getNodeName();
															//driver.manage().deleteAllCookies();
														}
													
													//HeaderNode = StepLstNode.getNodeName();
												}//Step NodeType
												if (recoveryRan)
												{
													System.out.println("about to break");
													tcResDescVal = "Recovery script was called";
													break;
												}
											}//Step for
											resultfooter(finalResult, sTime);
										}//TC if
									}//TC for

								}//ModuleName if
							}//ModuleName for
						}//Application tag if
					}//Application for
				}//Input if
			}//Input for
		
		}//try
		catch (Exception e)
		{
			e.printStackTrace();
			SeleniumFW.APPLICATION_LOGS.error("Getting Exception::"+e);
		}
	}//parseInputXML function

	public void resultfooter(String finalResult, long sTime) throws Exception 
	{
		totTime = 0;
		long tcTime;
		long eTime = System.currentTimeMillis();
		tcTime = eTime - sTime;
		tcTime=tcTime/1000;
		totTime = totTime + tcTime;

		String id = "",Path = "";
		String HeaderTime = Long.toString(totTime)+" Sec";
		System.out.println("inside header"+ HeaderNode  +" "+ tcResDescVal +" "+ TCDescription +" "+ HeaderExp +" "+finalResult);
		SeleniumFW.APPLICATION_LOGS.info("******** TEST SCRIPTS FINAL RESULT *********");
		SeleniumFW.APPLICATION_LOGS.info("inside header"+ HeaderNode  +" "+ tcResDescVal +" "+ TCDescription +" "+ HeaderExp +" "+finalResult);
		Result.XMLWriter(id, HeaderNode, tcResDescVal, TCDescription, HeaderExp, Path, finalResult, HeaderTime);
	}
	public void function(String sValue)
	{
		boolean resultstep;
		String Stepres = null;
		try
		{
		long diffTime = 0;	
		
		String Path = null;
		String Time = null;
		
		NodeList functionNodeList = docInput.getElementsByTagName(sValue);
		NodeList functionStepList = functionNodeList.item(0).getChildNodes();
		System.out.println(functionStepList.getLength());
		for (int  func= 0; func < functionStepList.getLength(); func++)
		{
			Node functionStepNode = functionStepList.item(func);
			sTime = System.currentTimeMillis();
			ObjectType FunctionRefName =null;
			String ExpeTarget =null;
			if (functionStepNode.getNodeName().equalsIgnoreCase("Step")) 
			{
				String objAction = getAttribute(functionStepNode,"Action");
				String objValue = getAttribute(functionStepNode,"Val");
				
				if (objAction.equalsIgnoreCase("include"))
				{
					function(objValue);
				}
				if(objAction.indexOf("include")!=0)
				{
				FunctionRefName =  objxml.getObjectName(getAttribute(functionStepNode,"RefObj"));
				navigate(objAction,objValue,FunctionRefName,soapRequest);
				
				resultstep=objStep.exp(FunctionRefName.getObjectName(),objAction,objValue, Session.sel);
				if(resultstep == true)
				{
					Stepres = "Pass";
				}
				else
				{
					Stepres = "Fail";
				}
				Path = "";
				if(Stepres=="Fail")
				{
				try{
					stepResult = "Fail";	
					ObjHighlight=FunctionRefName.getObjectName();
					objSel.captureScreenshot(screenshot);
					Path =  sspath;
				}catch(Exception e){
					e.printStackTrace();
				}
				}
				long eTime1 = System.currentTimeMillis();
				diffTime = eTime1 - sTime1;
				Time  = Long.toString(diffTime)+" ms";
				if (objAction.equalsIgnoreCase("store"))
				{
					PwdValue = FunctionRefName.getObjectName();
				}
				if (sValue.equalsIgnoreCase("${passwd}"))
				{
					sValue= PwdValue;
				}
				if (objAction.equalsIgnoreCase("type"))
				{
					sValue = Navigate.value;
				}
				else if ((objAction.equalsIgnoreCase("select"))||(objAction.equalsIgnoreCase("selectAndWait")))
				{
					if (objValue.indexOf("prevoiusRandomProfileName")>=0)
					{
						objValue=objValue.replaceAll("prevoiusRandomProfileName", prevoiusRandomProfileName);
					}
					sValue = StepValidate.val[1];
				}
				if (sValue.indexOf("get")==0)
				{
					sValue = hm.get(sValue.split("-")[1]);
				}
				XMLResultsModifier.XmlModifier(objAction,FunctionRefName.getRefName(), FunctionRefName.getObjectName(), functionStepNode.getNodeName(), objValue, StepValidate.StepActual, Stepres, Time, Path, objValue);
	
				}
			}
			else if (functionStepNode.getNodeName().equalsIgnoreCase("Expected")) 
			{
				ExpScreenshot = ExpScreenshot +1;
				sspath = "Screenshot\\screenshot-TC-"+TCID+"-Expected-"+ExpScreenshot+".png";
				ExpScreenshotPath  = Configure.resultlog+"\\"+sspath;
				sTime = System.currentTimeMillis();
				tcResult = "Pass";
				String ExpeAction = getAttribute(functionStepNode,"ExpAction");
				//String ExpeTarget = getAttribute(functionStepNode,"ExpTarget");
				String ExpeVal = getAttribute(functionStepNode,"ExpVal");
				FunctionRefName =  objxml.getObjectName(getAttribute(functionStepNode,"ExpRefName"));
				ExpeTarget = FunctionRefName.getObjectName();
				System.out.println("Function objname::::::"+ExpeTarget);

				tcResult1 = objexp.expected(ExpeAction,FunctionRefName.getObjectName(), ExpeVal, waitTime,dto);
				value=Expected.actual;
				System.out.println("Value of Actual is  : " + value);
				Path = "";				
				//String tcRes = null;
				if (tcResult1 == true)
					tcResult = "Pass";
				else
				{	
					try{
					tcResult = "Fail";
					ObjHighlight=FunctionRefName.getObjectName();
					objSel.captureScreenshot(ExpScreenshotPath);
					Path = sspath;
					//tcRes = "Fail";
				}catch(Exception e){
					e.printStackTrace();
					SeleniumFW.APPLICATION_LOGS.error("Getting Exception::"+e);
				}
				}
				long eTime1 = System.currentTimeMillis();
				diffTime = eTime1 - sTime1;
				Time  = Long.toString(diffTime)+" ms";
				if ((ExpeAction.equalsIgnoreCase("VerifyValue"))||(ExpeAction.equalsIgnoreCase("waitForValue"))||(ExpeAction.equalsIgnoreCase("verifyTable"))||(ExpeAction.equalsIgnoreCase("waitForValue"))||(ExpeAction.equalsIgnoreCase("verifyTableContent")))
				{
					if(ExpeVal.indexOf("get-")>=0)
					{
						ExpeVal=Expected.hashMapValue;
					}
					if(ExpeVal.equalsIgnoreCase("randomProfileName"))
					{
						ExpeVal=DataGenerator.randName;
					}
					ExpeTarget = ExpeVal;
				}
				else if (ExpeAction.equalsIgnoreCase("verifyTextPresent"))  
				{
					if (ExpeTarget.indexOf("${")==0)
					{
						str = ExpeTarget.substring(ExpeTarget.indexOf("{")+1,ExpeTarget.indexOf("}"));
						mapkey = hm.containsKey(str);
						if (mapkey == true)
							ExpeTarget = hm.get(str).toString();
					}
				}
				else if ((ExpeAction.equalsIgnoreCase("verifySelectedLabel"))||(ExpeAction.equalsIgnoreCase("waitForSelectedLabel")))  
				{
					ExpeTarget = ExpeVal;
				}
				XMLResultsModifier.XmlExpModifier( functionStepNode.getNodeName(), ExpeTarget,  Expected.actual, tcResult, Time, Path, ExpeAction, FunctionRefName.getRefName(),FunctionRefName.getObjectName());																		
				//XMLResultsModifier.XmlExpModifier( functionStepNode.getNodeName(), ExpeTarget,  Expected.actual, tcResult, Time, Path, ExpeAction, FunctionRefName.getRefName());																		
			}
		}
		}
		catch (Exception ee)
		{
			ee.printStackTrace();
			//Session.tearDown();
			SeleniumFW.APPLICATION_LOGS.error("Getting Exception::"+ee);
		}
	}


	public void navigate(String sAction,String sValue, ObjectType ot,SaaJSoap soapRequest)
	{
		try
		{
			if ((sAction.equalsIgnoreCase("clickPopup"))||(sAction.equalsIgnoreCase("switchToWindow"))||(sAction.equalsIgnoreCase("clickOnly"))||(sAction.equalsIgnoreCase("isChecked"))||(sAction.equalsIgnoreCase("closeNewTab"))||(sAction.equalsIgnoreCase("click"))||(sAction.equalsIgnoreCase("WaitAndclick"))||(sAction.equalsIgnoreCase("clickAndWait"))||(sAction.equalsIgnoreCase("ClickActionUsingMouse"))||(sAction.equalsIgnoreCase("SearchDropDown"))||(sAction.equalsIgnoreCase("typeAndWait"))||(sAction.equalsIgnoreCase("type"))||(sAction.equalsIgnoreCase("select"))||(sAction.equalsIgnoreCase("selectAndWait"))||(sAction.equalsIgnoreCase("deleteAllVisibleCookies"))|| (sAction.equalsIgnoreCase("typeDate")))
			{
				Navigate.action(sAction, sValue,ot.getObjectName(),ot.getRefName(), waitTime,soapRequest);
			}
			
			else if ((sAction.equalsIgnoreCase("addTableValues"))||(sAction.equalsIgnoreCase("storeDamagedCoupon"))||(sAction.equalsIgnoreCase("storevalueinPropertyFile"))||(sAction.equalsIgnoreCase("storeDamagedCoupon"))||(sAction.equalsIgnoreCase("storeValueFromIvrRequest"))||(sAction.equalsIgnoreCase("StoreValueFromWebTableBasedOnLables"))||(sAction.equalsIgnoreCase("storeValueFromAtmWebSimulator"))||(sAction.equalsIgnoreCase("storeValueFromWebSimulator"))||(sAction.equalsIgnoreCase("storeFromSimulator"))||(sAction.equalsIgnoreCase("storeFromSimulatorAmount"))||(sAction.equalsIgnoreCase("storeNumber"))||(sAction.equalsIgnoreCase("generatePins"))||(sAction.equalsIgnoreCase("store"))||(sAction.equalsIgnoreCase("storeText"))||(sAction.equalsIgnoreCase("storeAttribute"))||(sAction.equalsIgnoreCase("storeTitle"))||(sAction.equalsIgnoreCase("storeTextPresent"))||(sAction.equalsIgnoreCase("storeComboSelectedItem"))||(sAction.equalsIgnoreCase("storeRechargeAmount"))||(sAction.equalsIgnoreCase("storeWindowId"))||(sAction.equalsIgnoreCase("storeSelectedValue"))||(sAction.equalsIgnoreCase("storeBalance"))||(sAction.equalsIgnoreCase("StoreValueInWebTable")))
			{
				Navigate.action(sAction, sValue,ot.getObjectName(),ot.getRefName(), waitTime,soapRequest);
			}
			else if((sAction.equalsIgnoreCase("executeStoreProcedure"))||(sAction.equalsIgnoreCase("clickAlert"))||(sAction.equalsIgnoreCase("mouseOverAndWait"))||(sAction.equalsIgnoreCase("openNewTabAndNavigateUrl"))||(sAction.equalsIgnoreCase("open"))||(sAction.equalsIgnoreCase("openAndWait"))||(sAction.equalsIgnoreCase("setTimeout"))||(sAction.equalsIgnoreCase("chooseOkOnNextConfirmationAndWait")))
			{
				Navigate.action(sAction, sValue,ot.getObjectName(),ot.getRefName(), waitTime,soapRequest);//openNewTabAndNavigateUrl
			}
			else if((sAction.equalsIgnoreCase("actionOnWebTable"))||(sAction.equalsIgnoreCase("PostpaidBillConsultationUsingRrnExpectedValues"))||(sAction.equalsIgnoreCase("PostpaidBillConsultationExpectedValues"))||(sAction.equalsIgnoreCase("ExecuteRejectedTransQueryAndStoreValues"))||(sAction.equalsIgnoreCase("ExecuteQueryAndStoreValues"))||(sAction.equalsIgnoreCase("fireEvent"))||(sAction.equalsIgnoreCase("addSelection"))||(sAction.equalsIgnoreCase("upLoadFiles"))||(sAction.equalsIgnoreCase("removeSelection"))||(sAction.equalsIgnoreCase("typeAndWait"))||(sAction.equalsIgnoreCase("DBConnect"))||(sAction.equalsIgnoreCase("DBConnectSyn"))||(sAction.equalsIgnoreCase("DeleteQuery"))||(sAction.equalsIgnoreCase("ExecuteQuery"))||(sAction.equalsIgnoreCase("Query"))||(sAction.equalsIgnoreCase("check"))||(sAction.equalsIgnoreCase("storeFromUSSDSimulator"))||(sAction.equalsIgnoreCase("selectWindow")))
			{
				Navigate.action(sAction, sValue,ot.getObjectName(),ot.getRefName(), waitTime,soapRequest);
			}
			else if((sAction.equalsIgnoreCase("storevalueFromPropertyFile"))||(sAction.equalsIgnoreCase("randomSelect")))
			{
				Navigate.action(sAction, sValue,ot.getObjectName(),ot.getRefName(), waitTime,soapRequest);
			}
			
			else if((sAction.equalsIgnoreCase("storeAttributeFromWebServiceRequest"))||(sAction.equalsIgnoreCase("storeAttributeFromWebServiceResponse")))
			{
				Navigate.action(sAction, sValue,ot.getObjectName(),ot.getRefName(), waitTime,soapRequest);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			SeleniumFW.APPLICATION_LOGS.error("Getting Exception::"+ex);
		}
	}

	public static String getAttribute(Node node, String attribute) 
	{
		Node namedItem = node.getAttributes().getNamedItem(attribute);
		if (namedItem != null) 
		{
			return namedItem.getNodeValue();
		}
		return null;
	}

	public static String getInnerText(Node node) 
	{
		NodeList fstNm = node.getChildNodes();
		return ((Node) fstNm.item(0)).getNodeValue();
	}

}
