package SeleniumTestAutomation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.management.ObjectName;





//import org.mortbay.util.IO;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.w3c.dom.Document;

import saajSoap.SaaJSoap;
import BNI.BNIClient;
import atm.ATMClientSimulator;

import com.thoughtworks.selenium.Wait;

//import ussd.DataDecoder;
//import ussd.USSDInitiator;

class Navigate extends Session 
{
	static DB db = new DB(); 
	Configure parserObj = new Configure();
	static Connection dbConn = null;
	public static String randName, feedWinId, errorMessage, filePath=null,keyVal,DbValue;
	public static double total;
	public static int numberOfChar, col;
	public static String stype, prevoiusRandomProfileName=null, prevoiusRandomUserName=null;
	public static String randNum,randMail,randtext,test1, test2,expVal1,errorMsg,BillPayment_Field38=null,BillPayment_Field44=null,BillPaymentField45=null,BillPaymentField126=null;
	public static  String str,UserName,Password;
	public static String inputfile, objectfile;
	public static String OrderNum,typeOflocator;
	public static String act= "False",value;
	static boolean mapkey;
	static String[] strval = null;
	public static double balanceAmount;
	public static DataInputStream dataIn = null;
	public static int numberLength;
	static HashMap<String, String> hm = new HashMap<String, String>(); 

	Document docInput=null, docObject=null;

	public void captureScreenshot(String fname)throws exception, IOException 
	{
		/*if (Configure.WEB_SERVICES.equalsIgnoreCase("webServices"))
		{
			return;
		}*/
		
		File screenshot=null;
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(fname));
		screenshot.delete();

	}

	public static void action( String sAction,String sValue, String sObjectName,String sObjectRefName,String waitTime,SaaJSoap soapRequest ) throws IOException
	{
		try
		{ 
			if (sAction.equalsIgnoreCase("click"))
			{
				typeOflocator = PerformActions.getLocator(sObjectName);	
				if((sObjectName.contains("="))&&(sObjectName.contains("//"))==false)
				{
					sObjectName = sObjectName.split("=")[1];
				}
				if(PerformActions.click(driver,typeOflocator,sObjectName,sValue)==true)
				{
					act="True";
					SeleniumFW.APPLICATION_LOGS.info("Click Action Performed On "+sObjectRefName+" with Locator:: "+sObjectName);
				}else{
					act="False";
					SeleniumFW.APPLICATION_LOGS.info("Click Action Performed failed "+sObjectRefName+" with Locator:: "+sObjectName);
				}
			}
			else if(sAction.equalsIgnoreCase("fireEvent"))
			{
				/*sel.fireEvent(sObjectName, sValue);
			sel.waitForPageToLoad(waitTime);
			act="True";*/
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.switchTo().activeElement().sendKeys(Keys.TAB);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				act="True";
			}
			else if(sAction.equalsIgnoreCase("isChecked"))
			{


				typeOflocator = PerformActions.getLocator(sObjectName);	
				if((sObjectName.contains("="))&&(sObjectName.contains("//"))==false)
				{
					sObjectName = sObjectName.split("=")[1];
				}
				if(PerformActions.isChecked(driver,typeOflocator,sObjectName,sValue)==true)
				{
					SeleniumFW.APPLICATION_LOGS.info("Performed Is checked"+sObjectRefName+" with Locator:: "+sObjectName);
					act="True";
				} else{
					act="False";
				}
			}
			/*else if(sAction.equalsIgnoreCase("executeStoreProcedure"))
			{
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
				}
				catch(ClassNotFoundException ex)
				{
					ex.printStackTrace();
					 SeleniumFW.APPLICATION_LOGS.error("Getting Exception::"+ex);
				}
				Connection con=null;
				CallableStatement cstmt=null;
				try
				{
					String sValue2="Y";
					con=DriverManager.getConnection("jdbc:oracle:thin:@"+Configure.server+":"+Configure.sid,Configure.userName,Configure.password);
					cstmt=con.prepareCall(sObjectName); //called the procedure
					cstmt.setString(1, sValue); 
					cstmt.setString(2, sValue2);
					cstmt.registerOutParameter(3, Types.INTEGER);
					cstmt.registerOutParameter(4, Types.VARCHAR);
					System.out.println("Executing Store Procedure.....");
					cstmt.executeUpdate();
					Thread.sleep(10000);
					String outParam = cstmt.getString(3);
					System.out.println("senthil output :" + outParam);
					System.out.println("done");
					if (outParam.equals("0"))
					{	            	
						act="True";
					}
					else
					{
						act="False";
					}
				}
				catch(SQLException ex)
				{
					ex.printStackTrace();
					act="False";
					SeleniumFW.APPLICATION_LOGS.error("Getting Exception::"+ex);
				}
				finally
				{
					try
					{
						if(cstmt!=null) //close the callablestatement
						{
							cstmt.close();
							cstmt=null;
						}
					}
					catch(SQLException ex)
					{
						ex.printStackTrace();
						act="False";
						SeleniumFW.APPLICATION_LOGS.error("Getting Exception::"+ex);
					}
					try
					{
						if(cstmt!=null)  //close the connection
						{
							cstmt.close();
							cstmt=null;
						}
					}
					catch(SQLException ex)
					{
						ex.printStackTrace();
						act="False";
						SeleniumFW.APPLICATION_LOGS.error("Getting Exception::"+ex);
					}
				}
			}*/
			
			else if (sAction.equalsIgnoreCase("PostpaidBillConsultationExpectedValues"))
			{
				//get-Billpaymentamount:get-subscriberSCLcodeAndCheckSumDigit:readPropertity-mdn
				//billpaymentAmount
				//38fieldAmount
				//44fieldStoreing 2000000000000000000100999
				//45field --->"% 0000000000000000000^051000000027384364        ^                          ?"
				//126field--->"000000100999051000000027384364        0910000000                   "
				String Field38=null,Field44=null,Field45=null,Field126=null;
				String Amount=null,MDN=null,SCLCODE=null;
				Amount=sValue.split(":")[0].trim();
				if(Amount.indexOf("get")==0)
				{
					Amount=hm.get(Amount.split("-")[1]);
					if(!(Amount.contains("."))){
						Amount=Amount+".00";
					}
					    String s=Amount.replace(".", "");
					    int n=Integer.parseInt(s);
					    NumberFormat format=NumberFormat.getInstance();  
					    format.setMaximumIntegerDigits(6);  
					    format.setMaximumFractionDigits(6);  
					    format.setMinimumFractionDigits(6);  
					    format.setMinimumIntegerDigits(6);  
					    System.out.println("Shan  value:-->"+format.format(n).replace(",",""));  
				        String swe=format.format(n).replace(",","");
				        Field38=swe.substring(0, 6);
					    System.out.println("Amount:--->"+swe);
				        SeleniumFW.APPLICATION_LOGS.info("Bill payment Amount 38 Field:--> "+Field38);
					    hm.put("BillPayment_Amount_38", Field38);
					    BillPayment_Field38=Field38;
					    Field44="20000000000000000000"+Field38;
					    SeleniumFW.APPLICATION_LOGS.info("Bill payment Amount 44 Field:--> "+Field44);
					    BillPayment_Field44=Field44;
					    hm.put("BillPayment_Amount_44", Field44);
					    /*hm.put("BillPayment_Amount_44", Field44);
					    Field45="% 0000000000000000000^05100000002"+Field38+"        ^                          ?";
					    */
				}
				
			        
				SCLCODE=sValue.split(":")[1].trim();
				if(SCLCODE.indexOf("get")==0){
				SCLCODE=hm.get(SCLCODE.split("-")[1]).trim();
				//% 0000000000000000000^051000000027384364        ^                          ?
				Field45="% 0000000000000000000^0510000000"+SCLCODE+"4        ^                          ?";
				hm.put("Bill_Payment_Reference_Number_45",Field45);
				SeleniumFW.APPLICATION_LOGS.info("Bill payment Reference Number 45 Field:--> "+Field45);
				BillPaymentField45=Field45;
				}
								
				MDN=sValue.split(":")[2].trim();
				
				if(MDN.indexOf("readProperty")==0){
				//MDN=hm.get(MDN.split("-")[1]).trim();
				MDN=Locators.getLocators(MDN.split("-")[1]);
				//000000100999051000000027384364        0910000000                   
				Field126="000000"+Field38+"0510000000"+SCLCODE+"4        "+MDN+"                   ";
				SeleniumFW.APPLICATION_LOGS.info("Bill payment Mobile Number 126 Field:--> "+Field126);
				hm.put("Bill_Payment_126_Field",Field126);
				BillPaymentField126=Field126;
				}
			}
				
			else if(sAction.equalsIgnoreCase("SearchDropDown"))
			{
					typeOflocator=PerformActions.getLocator(sObjectName);
					if(PerformActions.SearchDropDown(driver,typeOflocator,sObjectName,sValue)==true)
					{
						act="True";
						System.out.println("shan-Raj" +act);
						SeleniumFW.APPLICATION_LOGS.info("Search Drop Down Type data In :: "+ sObjectRefName+"with Locator :: "+sObjectName+"And Value :: "+sValue +"Navigation Result-->"+act);
							}
					else
					{
						act="False";
						SeleniumFW.APPLICATION_LOGS.info("Search Drop Down Type data In :: "+ sObjectRefName+"with Locator :: "+sObjectName+"And Value :: "+sValue+"Navigation Result-->"+act);
						
					}
					
			}
			
			else if(sAction.equalsIgnoreCase("ClickActionUsingMouse"))
			{
					typeOflocator=PerformActions.getLocator(sObjectName);
					if(PerformActions.ClickActionUsingMouse(driver,typeOflocator,sObjectName,sValue)==true)
					{
						act="True";
						System.out.println("shan-Raj" +act);
						SeleniumFW.APPLICATION_LOGS.info("ClickActionUsingMouse data In :: "+ sObjectRefName+"with Locator :: "+sObjectName+"And Value :: "+sValue +"Navigation Result-->"+act);
							}
					else
					{
						act="False";
						SeleniumFW.APPLICATION_LOGS.info("ClickActionUsingMouse In :: "+ sObjectRefName+"with Locator :: "+sObjectName+"And Value :: "+sValue+"Navigation Result-->"+act);
						
					}
					
			}
				
			else if (sAction.equalsIgnoreCase("PostpaidBillConsultationUsingRrnExpectedValues"))
			{
				//get-Billpaymentamount:get-subscriberSCLcodeAndCheckSumDigit:readPropertity-mdn
				
				
				//billpaymentAmount
				//38fieldAmount
				//44fieldStoreing 2000000000000000000100999
				//45field --->"% 0000000000000000000^051000000027384364        ^                          ?"
				//126field--->"000000100999051000000027384364        0910000000                   "
				String Field38=null,Field44=null,Field45=null,Field126=null;
				String Amount=null,MDN=null,SCLCODE=null;
				
				Amount=sValue.split(":")[0].trim();
				if(Amount.indexOf("get")==0)
				{
					Amount=hm.get(Amount.split("-")[1]);
					if(!(Amount.contains("."))){
						Amount=Amount+".00";
					}
					    String s=Amount.replace(".", "");
					    int n=Integer.parseInt(s);
					    NumberFormat format=NumberFormat.getInstance();  
					    format.setMaximumIntegerDigits(6);  
					    format.setMaximumFractionDigits(6);  
					    format.setMinimumFractionDigits(6);  
					    format.setMinimumIntegerDigits(6);  
					    //System.out.println("Shan  value:-->"+format.format(n).replace(",",""));  
				        String swe=format.format(n).replace(",","");
				        Field38=swe.substring(0, 6);
					    System.out.println("Amount:--->"+swe);
				        SeleniumFW.APPLICATION_LOGS.info("Bill payment Amount Using Rrn 38 Field:--> "+Field38);
					    hm.put("BillPayment_Amount_38", Field38);
					    BillPayment_Field38=Field38;
					    Field44="2000000000000000000"+Field38;
					    SeleniumFW.APPLICATION_LOGS.info("Bill payment Amount Using Rrn 44 Field:--> "+Field44);
					    BillPayment_Field44=Field44;
					    hm.put("BillPayment_Amount_44", Field44);
					    /*hm.put("BillPayment_Amount_44", Field44);
					    Field45="% 0000000000000000000^05100000002"+Field38+"        ^                          ?";
					    */
				}
				
			        
				SCLCODE=sValue.split(":")[1].trim();
				if(SCLCODE.indexOf("get")==0){
				SCLCODE=hm.get(SCLCODE.split("-")[1]).trim();
				//% 0000000000000000000^051000000027384364        ^                          ?
				//%0000000000000000000051000000027384364        ^            ?
				//Field45="% 0000000000000000000^0510000000"+SCLCODE+"4        ^                          ?";
				Field45="%00000000000000000000510000000"+SCLCODE+"4        ^            ?";
				hm.put("Bill_Payment_Reference_Number_45",Field45);//%0000000000000000000051000000027384364        ^            ?
				SeleniumFW.APPLICATION_LOGS.info("Bill payment Reference Using Rrn Number 45 Field:--> "+Field45);
				BillPaymentField45=Field45;
				
				Field126="000000"+Field38+"0510000000"+SCLCODE+"4        ";
				SeleniumFW.APPLICATION_LOGS.info("Bill payment Using Rrn 126 Field:--> "+Field126);
				hm.put("Bill_Payment_126_Field",Field126);
				BillPaymentField126=Field126;
				}
								
				
				
			}
	
	
			
			
			
			if(sAction.equalsIgnoreCase("ExecuteRejectedTransQueryAndStoreValues"))
	        {
	        	PreparedStatement pst=null;
	        	String varToStore=sValue.split(":")[0];
				sValue=sValue.split(":")[1];
	        	
	        	if(varToStore.equalsIgnoreCase("typicalRecharge")){
	        		
	            	ResultSet rs;
	                String temp=null;
	                dbConn = db.createConnection(sObjectName);
	               try {
	                 Statement stmt = dbConn.createStatement();
	              System.out.println ("sValueDB : "+sValue);
	               if (sValue.indexOf("get")>=0)
	                           {
	                                       String tempVal=sValue.split("-")[1];
	                                       if (tempVal.indexOf("'")>=0)
	                                       {
	                                                   tempVal=tempVal.split("'")[0];
	                                       }
	                                       sValue=sValue.replaceAll("get-"+tempVal, hm.get(tempVal));
	                           }

	               sValue=sValue.trim();
	               //sValue="select C_CUSTOMER_ID AS CellularPhoneNumber,C_AUTHORIZATION_ID AS AuthorizationNumber,C_TRANSACTION_ID AS TransactionNumber,C_RETAILER_ID AS DistributorId,C_PARTNER_ID AS DistributorId ,C_PAYMENT_AMOUNT AS TransactionAmount,C_FEE_AMOUNT AS FeeAmount,C_AIR_TIME AS AirTime,C_DEBIT_AMOUNT AS DebitAmount,C_PARENT_DIST_ID AS ParentDist from T_SETTLEMENT_TRANSACTION ss where ss.C_ORDER_ID='"+sValue+"'";
	               sValue="select * from (select C_PIN_TRANSID AS TransactionId,C_ERROR_DESC AS TransactionErrorDescription,C_CUSTOMER_ID AS CellularNumber,C_SYS_AMOUNT AS TransactionAmount,C_DISTRIBUTOR_ID as DistributorId from T_REJECT_REASON_T rt where C_DISTRIBUTOR_ID = '"+sValue+"'order by C_CREATED_DATE desc) where rownum=1";
	               System.out.println("typicalRecharge qurrey-->"+sValue);
	               
	               pst = dbConn.prepareStatement(sValue);
	    	        Thread.sleep(2000);
	    			System.out.println("connected");
	    			rs = pst.executeQuery();  
	    			SeleniumFW.APPLICATION_LOGS.info("**** Db Connected successfully And Executed Query Successfuly ****");
	    			SeleniumFW.APPLICATION_LOGS.info("---Query---");
	    			SeleniumFW.APPLICATION_LOGS.info( sValue );
	    			ResultSetMetaData rsMetaData = rs.getMetaData();
	    	        ArrayList<String> list= new ArrayList<String>();
	    	        while (rs.next()) {
	    	            list.add(rs.getString("TransactionId"));//TRANSACTIONID
	    	            list.add(rs.getString("TransactionErrorDescription"));//TRANSACTIONERRORDESCRIPTION
	    	            list.add(rs.getString("CellularNumber"));//CELLULARNUMBER
	    	            list.add(rs.getString("TransactionAmount"));//TRANSACTIONAMOUNT
	    	            list.add(rs.getString("DistributorId"));//DISTRIBUTORID
	    	            String[] result = new String[list.size()];
	    	            System.out.println("---list"+list.get(0));
	    	            
	    	            for(int j=1;j<=list.size();j++){
	    	            	
	    	            	String ssss;
	    	            	ssss=rsMetaData.getColumnName(j)+":"+list.get(j-1);
	    	            	hm.put(rsMetaData.getColumnName(j),list.get(j-1));
	    	            	System.out.println(rsMetaData.getColumnName(j)+":"+list.get(j-1));
	    	            	System.out.println("value-->" +ssss);
	    	            	SeleniumFW.APPLICATION_LOGS.info("----Stored values From Db----");
	    	            	SeleniumFW.APPLICATION_LOGS.info(rsMetaData.getColumnName(j)+" ---> "+list.get(j-1));
	    	            }
	    	        }
	    	    	act="True";
	               
	                
	                System.out.println ("QUIT FROM DATABASE");
	               }
	               catch (SQLException e) {
	            	   act="False";
	                   e.printStackTrace();
	                   SeleniumFW.APPLICATION_LOGS.info("***** getting Exception in Db Connect *****");
	   				   SeleniumFW.APPLICATION_LOGS.error(e);
	                  
	               }
	               finally{
	            	   pst.close();
	            	   dbConn.close();  
	            	   SeleniumFW.APPLICATION_LOGS.info("***** Db Connections Closed Successfully *****");
	               }
	               }
	        	

	        	if(varToStore.equalsIgnoreCase("ussdFundtransfer")) //kv
	        	{
	        		
	            	ResultSet rs;
	                String temp=null;
	                dbConn = db.createConnection(sObjectName);
	               try {
	                 Statement stmt = dbConn.createStatement();
	              System.out.println ("sValueDB : "+sValue);
	               if (sValue.indexOf("get")>=0)
	                           {
	                                       String tempVal=sValue.split("-")[1];
	                                       if (tempVal.indexOf("'")>=0)
	                                       {
	                                                   tempVal=tempVal.split("'")[0];
	                                       }
	                                       sValue=sValue.replaceAll("get-"+tempVal, hm.get(tempVal));
	                           }

	               sValue=sValue.trim();
	               //sValue="select C_CUSTOMER_ID AS CellularPhoneNumber,C_AUTHORIZATION_ID AS AuthorizationNumber,C_TRANSACTION_ID AS TransactionNumber,C_RETAILER_ID AS DistributorId,C_PARTNER_ID AS DistributorId ,C_PAYMENT_AMOUNT AS TransactionAmount,C_FEE_AMOUNT AS FeeAmount,C_AIR_TIME AS AirTime,C_DEBIT_AMOUNT AS DebitAmount,C_PARENT_DIST_ID AS ParentDist from T_SETTLEMENT_TRANSACTION ss where ss.C_ORDER_ID='"+sValue+"'";
//	               sValue="select * from (select C_PIN_TRANSID AS TransactionId,C_ERROR_DESC AS TransactionErrorDescription,C_CUSTOMER_ID AS CellularNumber,C_SYS_AMOUNT AS TransactionAmount,C_DISTRIBUTOR_ID as DistributorId from T_REJECT_REASON_T rt where C_DISTRIBUTOR_ID = '"+sValue+"'order by C_CREATED_DATE desc) where rownum=1";
	               sValue="select * from (select C_REJECT_ID as TransactionId,C_DISTRIBUTOR_ID as sourceDistributorId,C_PAY_AMOUNT as TransactionAmount,C_ERROR_DESC as TransactionErrorDescription,C_DEST_PARTNER_ID as destinationDistributorId from T_REJECT_TRANSACTIONS where C_DISTRIBUTOR_ID ='"+sValue+"' order by C_CREATED_DATE desc) where rownum=1";
	               System.out.println("ussd fundtransfe query-->"+sValue);
	               
	               pst = dbConn.prepareStatement(sValue);
	    	        Thread.sleep(2000);
	    			System.out.println("connected");
	    			rs = pst.executeQuery();  
	    			SeleniumFW.APPLICATION_LOGS.info("**** Db Connected successfully And Executed Query Successfuly ****");
	    			SeleniumFW.APPLICATION_LOGS.info("---Query---");
	    			SeleniumFW.APPLICATION_LOGS.info( sValue );
	    			ResultSetMetaData rsMetaData = rs.getMetaData();
	    	        ArrayList<String> list= new ArrayList<String>();
	    	        while (rs.next()) {
	    	            list.add(rs.getString("TransactionId"));//TRANSACTIONID
	    	            list.add(rs.getString("sourceDistributorId"));//SOURCEDISTRIBUTORID
	    	            list.add(rs.getString("TransactionAmount"));//TRANSACTIONAMOUNT
	    	            list.add(rs.getString("TransactionErrorDescription"));//TRANSACTIONERRORDESCRIPTION
	    	            list.add(rs.getString("destinationDistributorId"));//DESTINATIONDISTRIBUTORID
	    	            String[] result = new String[list.size()];
	    	            System.out.println("---list"+list.get(0));
	    	            
	    	            for(int j=1;j<=list.size();j++){
	    	            	
	    	            	String ssss;
	    	            	ssss=rsMetaData.getColumnName(j)+":"+list.get(j-1);
	    	            	hm.put(rsMetaData.getColumnName(j),list.get(j-1));
	    	            	System.out.println(rsMetaData.getColumnName(j)+":"+list.get(j-1));
	    	            	System.out.println("value-->" +ssss);
	    	            	SeleniumFW.APPLICATION_LOGS.info("****Stored values From Db****");
	    	            	SeleniumFW.APPLICATION_LOGS.info(rsMetaData.getColumnName(j)+" ---> "+list.get(j-1));
	    	            }
	    	        }
	    	    	act="True";
	               
	                
	                System.out.println ("QUIT FROM DATABASE");
	               }
	               catch (SQLException e) {
	            	   act="False";
	                   e.printStackTrace();
	                   SeleniumFW.APPLICATION_LOGS.info("***** getting Exception in Db Connect *****");
	   				   SeleniumFW.APPLICATION_LOGS.error(e);
	                  
	               }
	               finally{
	            	   pst.close();
	            	   dbConn.close();  
	            	   SeleniumFW.APPLICATION_LOGS.info("***** Db Connections Closed Successfully *****");
	               }
	               }
	      }
			else if (sAction.equalsIgnoreCase("storeAttributeFromWebServiceRequest"))
			{/*
				SeleniumFW.APPLICATION_LOGS.info("***** Store value from Web Services Request *****");
				
				Thread.sleep(1000);//SHANSUDINI
				String xPath=sValue.split(":")[0];
				SeleniumFW.APPLICATION_LOGS.info(" REQUEST XPATH :::: " +xPath);
				String tmp=sValue.split(":")[1];
				if (sValue.indexOf("get-")==0)
				{
					sValue=hm.get(sValue.split("-")[1]);
				}
				else if (sValue.indexOf("//RECARGA_ELECTRONICA")>=0)
				{
					String xPath1="//recargaElectronica[1]/string[1]";
					SaaJSoapClient res = new SaaJSoapClient();
					String appVal=res.XmlPath(TestCase.soapReq, xPath1);
					String xPathRequest=appVal;
					SaaJSoapClient res1 = new SaaJSoapClient();
					String appVal1=res.XmlPath(xPathRequest, xPath);
					sValue=tmp;
					SeleniumFW.APPLICATION_LOGS.info("Value Stored In ----------->" +sValue);
					SeleniumFW.APPLICATION_LOGS.info("stored value ---------->" +appVal);
					SeleniumFW.APPLICATION_LOGS.info("--------------------------------------------");
					hm.put(sValue, appVal1);
					keyVal=tmp;
				}
				else {
				//System.out.println("request _______>" +SaaJSoapClient.reqMsg );
				SaaJSoapClient res = new SaaJSoapClient();
				String appVal=res.XmlPath(TestCase.soapReq, xPath);
				//System.out.println("sValue----------->" +sValue);
				SeleniumFW.APPLICATION_LOGS.info("Value Stored In ----------->" +sValue);
				SeleniumFW.APPLICATION_LOGS.info("stored value ---------->" +appVal);
				SeleniumFW.APPLICATION_LOGS.info("--------------------------------------------");
				//System.out.println("value stored form request---------->" +appVal);
				sValue=tmp;
				if(sValue.equalsIgnoreCase("IVRSubscriberNumberwithCountrycode")!=false){
					appVal=appVal.substring(2);
							}
				hm.put(sValue, appVal);
				keyVal=tmp;
				}
				
				
			*/}
			else if (sAction.equalsIgnoreCase("storeAttributeFromWebServiceResponse"))
			{/*
				SeleniumFW.APPLICATION_LOGS.info("***** Store value from Web Services Response *****");
				Thread.sleep(1000);//SHANSUDINI
				String xPath=sValue.split(":")[0];
				SeleniumFW.APPLICATION_LOGS.info(" REQUEST XPATH :::: " +xPath);
				String tmp=sValue.split(":")[1];
				if (sValue.indexOf("get-")==0)
				{
					sValue=hm.get(sValue.split("-")[1]);
				}
				//System.out.println("request _______>" +SaaJSoapClient.strMsg );
				SaaJSoapClient res = new SaaJSoapClient();
				//System.out.println("X path------->" +xPath);
				//System.out.println("X path------->" +SaaJSoapClient.strMsg); 
				String appVal=res.XmlPath(SaaJSoapClient.strMsg, xPath);
				
				//hm.put(sValue, appVal1);
				//System.out.println("sValue----------->" +sValue);
				//System.out.println("appVal----------->" +appVal);
				sValue=tmp;
				hm.put(sValue, appVal);
				keyVal=tmp;
				SeleniumFW.APPLICATION_LOGS.info("Value Stored In ----------->>>" +sValue);
				SeleniumFW.APPLICATION_LOGS.info("stored value ---------->>>" +appVal);
				SeleniumFW.APPLICATION_LOGS.info("--------------------------------------------");
			*/}
			else if(sAction.equalsIgnoreCase("storeFromUSSDSimulator"))
			{
				String code=null;
				//int errVal=DataDecoder.res.indexOf("<"+sObjectName+">");
				int errVal=USSDClient.ussdResponse.indexOf("<"+sObjectName+">");
				System.out.println("errVal "+errVal);
				//int errVal1=DataDecoder.res.indexOf("</"+sObjectName+">");
				int errVal1=USSDClient.ussdResponse.indexOf("</"+sObjectName+">");
				System.out.println("errVal1 "+errVal1);
				//code=DataDecoder.res.substring(errVal+(sObjectName.length()+2), errVal1);
				code=USSDClient.ussdResponse.substring(errVal+(sObjectName.length()+2), errVal1);
				System.out.println("Value from USSD simulator : " + code);
				hm.put(sValue, code);
				System.out.println("Value from USSD simulator HM : " + hm.get(sValue));

			}
			else if(sAction.equalsIgnoreCase("storeFromSimulator"))
			{
				String code=null;
				String code1=null;
				//Thread.sleep(15000);
				Thread.sleep(3500);//SHANSUDINI
				try
				{
					FileInputStream fstream = new FileInputStream(TestCase.outLogFile);
					DataInputStream in = new DataInputStream(fstream);
					BufferedReader br = new BufferedReader(new InputStreamReader(in));
					String strLine;
					while ((strLine = br.readLine()) != null)
					{
						System.out.println("in first while -- "+strLine);
						if(strLine.contains("incoming"))
						{
							while ((strLine = br.readLine()) != null)
							{

								if (strLine.contains("<field id=") && strLine.contains(sObjectName))
								{
									int exp=strLine.indexOf("id=");
									code1=strLine.substring(exp+4, exp+6);
									System.out.println("IdValueInShan--->"+code1);
									if(code1.contains("\""))
									{
										code1=strLine.substring(exp+4, exp+5);
									}
									if(code1.equalsIgnoreCase(sObjectName)){

										int Val=strLine.indexOf("value=");
										int endPos=strLine.indexOf("/>");
										code=strLine.substring(Val+7, endPos-1);
										System.out.println("Value from simulator : " + code);
										System.out.println("IdValueInShan--->"+code1);
										hm.put(sValue, code);
										SeleniumFW.APPLICATION_LOGS.info("Value Stored In"+sValue+ "Value from simulator HM : "+code);
										//System.out.println("Value from simulator HM : " + hm.get(sValue));
										break;

									}

								}


							}
						}
					}
					in.close();
				}
				catch (Exception ex)
				{
					System.err.println("Response file not found");
					SeleniumFW.APPLICATION_LOGS.error("Getting Exception::"+ex);
				}	

			}

			else if(sAction.equalsIgnoreCase("storeFromSimulatorAmount"))
			{
				String code=null;
				Thread.sleep(3500);//SHANSUDINI
				try
				{
					FileInputStream fstream = new FileInputStream(TestCase.outLogFile);
					DataInputStream in = new DataInputStream(fstream);
					BufferedReader br = new BufferedReader(new InputStreamReader(in));
					String strLine;
					while ((strLine = br.readLine()) != null)
					{
						if (strLine.contains("<field id=") && strLine.contains(sObjectName))
						{
							int Val=strLine.indexOf("value=");
							int endPos=strLine.indexOf("/>");
							code=strLine.substring(Val+7, endPos-1);
							long str= Long.parseLong(code);
							String s = ""+str;
							System.out.println("Value after parse"+ s);
							String Amount=s.substring(0,s.length()-2)+".00";

							System.out.println("Value from simulator AMOUNT----->>>>" + Amount);
							hm.put(sValue, Amount);
							System.out.println("Value from simulator HM AMOUNT----->>>>>" + hm.get(sValue));
						}
					}
					in.close();
				}
				catch (Exception ex)
				{
					System.err.println("Response file not found");
					SeleniumFW.APPLICATION_LOGS.error("Getting Exception::"+ex);
				}
			}
			/*else if(sAction.equalsIgnoreCase("storeFromBankSimulator"))
			{
				String code=null;
				String code1=null;
				Thread.sleep(15000);
				try
				{
					FileInputStream fstream = new FileInputStream(TestCase.outLogFile);
					dataIn = new DataInputStream(fstream);
					BufferedReader br = new BufferedReader(new InputStreamReader(dataIn));
					String strLine;
					while ((strLine = br.readLine()) != null)
					{
						if(strLine.substring(0,3).equals("110")){
							if(sObjectName.equalsIgnoreCase("BankAuthorizationNumber"))
							{
								code  = strLine.substring(65,71);
								System.out.println("Autherization Number ==>"+ strLine.substring(65,71)+"<==");
								hm.put(sValue, code);
								System.out.println("Authorization Number Stored ==>"+ hm.get(sValue));
							}

						}
						if(strLine.substring(0,3).equals("230")){
							if(sObjectName.equalsIgnoreCase("BankAuthorizationNumber"))
							{
								code1 = strLine.substring(122,128);
								System.out.println("Transaction Id ==>"+ strLine.substring(122,128)+"<==");
								hm.put(sValue, code1);
							}
						}
					}

				}catch (Exception ex)
				{
					System.err.println("Response file not found");
					SeleniumFW.APPLICATION_LOGS.error("Getting Exception::"+ex);
				}finally{
					if(dataIn!= null)
					{
						dataIn.close();						
					}
				}

			}*/
			/*else if(sAction.equalsIgnoreCase("clickPopup"))//pending
			{
				sel.chooseOkOnNextConfirmation();
				sel.click(sObjectName);
				System.out.println("In Print");
				String a[]= sel.getAllWindowTitles();
				System.out.println(a.length);
				Thread.sleep(15000);
				//sel.chooseCancelOnNextConfirmation();
				sel.selectWindow(null);
				sel.windowFocus();
				//sel.waitForPageToLoad(waitTime);
			}*/
			
			else if (sAction.equalsIgnoreCase("storevalueinPropertyFile"))
			{
				
				String appVal = Locators.getLocators(sObjectName);
				//System.out.println("Locator value ---->"+appVal);
				SeleniumFW.APPLICATION_LOGS.info("storevalueinPropertyFile"+"Locator value ---->"+appVal);
				appVal=appVal.trim();
				hm.put(sValue, appVal);
				//String appVal = sel.getValue(sObjectName).trim();
				keyVal=sValue;
			}
			else if(sAction.equalsIgnoreCase("clickAndWait"))
			{
				try{
					if(sValue.indexOf("readProperty-")==0)
					{
						sValue=Locators.getLocators(sValue.split("-")[1]);
						if(driver.getTitle().equalsIgnoreCase("Prepaid Movistar - Channel Distributor Profile"))
						sObjectName="//a[contains(text(),'"+sValue+"')]";
						else
						sObjectName="link="+sValue;	
						System.out.println("sObjectName "+sObjectName);
					}
					if (sObjectName.indexOf("get")>=0)
					{
						sObjectName = sObjectName.replaceAll(sObjectName.split("=")[1], hm.get(sObjectName.split("-")[1]).toUpperCase());
					}
					
					typeOflocator=PerformActions.getLocator(sObjectName);
					if((sObjectName.contains("="))&&(sObjectName.contains("//")==false) && (sObjectName.contains("CSS=")==false))
					{
						sObjectName = sObjectName.split("=")[1];
					}

					if(PerformActions.clickAndWait(driver,typeOflocator,sObjectName,sValue)==true)
					{
						act="True";
						SeleniumFW.APPLICATION_LOGS.info("Click Action Performed On "+sObjectRefName+" with Locator:: "+sObjectName+"Navigate To"+driver.getTitle());
					}
					else
					{
						act="False";
						SeleniumFW.APPLICATION_LOGS.error("***Click Action failed***"+" Locator Not Fount"+ "'"+sObjectName+"'" +"Navigate To"+driver.getTitle());
					}

				}
				catch(Exception e)
				{
					e.printStackTrace();
					SeleniumFW.APPLICATION_LOGS.error(e);
				}
			}
			else if(sAction.equalsIgnoreCase("WaitAndclick"))
			{
				try{
					if(sValue.indexOf("readProperty-")==0)
					{
						sValue=Locators.getLocators(sValue.split("-")[1]);
						if(driver.getTitle().equalsIgnoreCase("Prepaid Movistar - Channel Distributor Profile"))
						sObjectName="//a[contains(text(),'"+sValue+"')]";
						else
						sObjectName="link="+sValue;	
						System.out.println("sObjectName "+sObjectName);
					}
					if (sObjectName.indexOf("get")>=0)
					{
						sObjectName = sObjectName.replaceAll(sObjectName.split("=")[1], hm.get(sObjectName.split("-")[1]).toUpperCase());
					}
					
					typeOflocator=PerformActions.getLocator(sObjectName);
					if((sObjectName.contains("="))&&(sObjectName.contains("//")==false) && (sObjectName.contains("CSS=")==false))
					{
						sObjectName = sObjectName.split("=")[1];
					}

					if(PerformActions.clickAndWait(driver,typeOflocator,sObjectName,sValue)==true)
					{
						  Thread.sleep(40000);
						act="True";
						SeleniumFW.APPLICATION_LOGS.info("Click Action Performed On "+sObjectRefName+" with Locator:: "+sObjectName+"Navigate To"+driver.getTitle());
					}
					else
					{
						act="False";
						SeleniumFW.APPLICATION_LOGS.error("***Click Action failed***"+" Locator Not Fount"+ "'"+sObjectName+"'" +"Navigate To"+driver.getTitle());
					}

				}
				catch(Exception e)
				{
					e.printStackTrace();
					SeleniumFW.APPLICATION_LOGS.error(e);
				}
			}
			else if (sAction.equalsIgnoreCase("removeSelection"))
			{
				sValue=sValue.split("=")[1];
				if (sValue.indexOf("get")>=0)
				{
					sValue=sValue.replaceAll("get-"+sValue.split("-")[1], hm.get(sValue.split("-")[1]));
				}
				if(sValue.indexOf("readProperty-")==0)
				{
					sValue=Locators.getLocators(sValue.split("-")[1]);
					sValue="label="+sValue;
					sValue=sValue.trim();
				}
				typeOflocator = PerformActions.getLocator(sObjectName);
				if((sObjectName.contains("="))&&(sObjectName.contains("//"))==false)
				{
					sObjectName = sObjectName.split("=")[1];
				}
				if(PerformActions.removeSelection(driver,typeOflocator,sObjectName,sValue)==true)
				{
					act="True";
					SeleniumFW.APPLICATION_LOGS.info("Remove Selection"+sObjectName+"Value"+sAction);
				}
				else
				{
					act="False";
					SeleniumFW.APPLICATION_LOGS.info("Remove Selection Fail"+sObjectName+"Value"+sAction);
				}
			}
			else if (sAction.equalsIgnoreCase("addSelection"))
			{ 
				sValue=sValue.split("=")[1];
				if (sValue.indexOf("get")>=0)
				{
					sValue=sValue.replaceAll("get-"+sValue.split("-")[1], hm.get(sValue.split("-")[1]));
				}
				if(sValue.indexOf("readProperty-")==0)
				{
					sValue=Locators.getLocators(sValue.split("-")[1]);
					sValue="label="+sValue;
					sValue=sValue.trim();
				}
				typeOflocator = PerformActions.getLocator(sObjectName);
				if((sObjectName.contains("="))&&(sObjectName.contains("//"))==false)
				{
					sObjectName = sObjectName.split("=")[1];
				}
				if(PerformActions.addSelection(driver,typeOflocator,sObjectName,sValue)==true)
				{
					SeleniumFW.APPLICATION_LOGS.info("Add Selection"+sObjectName+"Value"+sAction);
					act="True";
				}
				else
				{
					act="False";
					SeleniumFW.APPLICATION_LOGS.info("Add Selection Fail"+sObjectName+"Value"+sAction);
				}

			}
			else if (sAction.equalsIgnoreCase("openNewTabAndNavigateUrl"))
			{
				try{
				if (sValue.indexOf("readProperty-")==0)
				{
					String tempVal=sValue;
					sValue=sValue.split("-")[1];
					sValue=Locators.getLocators(sValue);
					sValue=sValue.trim();
				}
				Thread.sleep(1000);//SHANSUDINI
				driver.findElement(By.xpath("//body")).sendKeys(Keys.CONTROL+"t");
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);//7  @shan
				driver.navigate().to(sValue);
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);//7 @shan
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);//120
				SeleniumFW.APPLICATION_LOGS.info("Open New Tab And Navigate Url"+sValue);
				errorMsg=driver.getTitle();
				SeleniumFW.APPLICATION_LOGS.info("Open New Tab And Navigate Url"+sValue +"title:-->"+errorMsg );
			}catch(Exception e){
				e.printStackTrace();
			}
			}
			else if (sAction.equalsIgnoreCase("closeNewTab"))
			{
				driver.findElement(By.xpath("//body")).sendKeys(Keys.CONTROL+"w");
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//10
				SeleniumFW.APPLICATION_LOGS.info("Close New Tab Action Performed");
			}
			else if (sAction.equalsIgnoreCase("storeWindowId"))
			{
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				String windowId=driver.getWindowHandle();
				System.out.println("windowId--->"+windowId);
				hm.put(sValue,windowId);
				SeleniumFW.APPLICATION_LOGS.info("Strore window Id"+windowId);
			}
			else if (sAction.equalsIgnoreCase("switchToWindow"))
			{
				if (sValue.indexOf("get")>=0)
				{
					sValue=sValue.replaceAll("get-"+sValue.split("-")[1], hm.get(sValue.split("-")[1]));
				}
				driver.switchTo().window(sValue);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				SeleniumFW.APPLICATION_LOGS.info("Switch window Id"+sValue);
							}
			else if (sAction.equalsIgnoreCase("selectWindow"))
			{
				Thread.sleep(5000);
				if(sObjectName.equalsIgnoreCase("null")){
					driver.switchTo().window(feedWinId);
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					feedWinId=null;
					//act="True";
								}
				else{
					feedWinId = driver.getWindowHandle();
					String ParentWindowId=feedWinId;
					Set<String> allWindows = driver.getWindowHandles();
					String newWindow = null;
			        for (String window : allWindows) {
			            if (!feedWinId.equals(window)) {
			                newWindow = window;
			                driver.switchTo().window(newWindow);
			                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			                //act="True";
			            }
			        }
				}
				
				
				
				/*Set<String> allWindows = driver.getWindowHandles();
				if(!allWindows.isEmpty()) {
					for (String windowId : allWindows) {
						System.out.println("child window--->"+windowId);
						if(sObjectName!=null &&windowId!=parentWindow){
							driver.switchTo().window(windowId);
							driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
																			}
						else
						{
							driver.switchTo().window(parentWindow);
							driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
						}
						

					}
				}*/
			}
			//else if (sAction.equalsIgnoreCase("closePopup"))

			//{

			//}
			else if (sAction.equalsIgnoreCase("open"))
			{
				if ((sObjectName.indexOf("http://")==0)||(sObjectName.indexOf("https://")==0))
				{
					driver.get(sObjectName);
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					if ((sValue.indexOf("UserName")==0)&&(driver.getTitle().indexOf("Certificate Error")==0))
					{
						strval = sValue.split(":");
						Session.authentication();
						Thread.sleep(1000);
					}
					Thread.sleep(500);
				}
				errorMsg=driver.getTitle();
				act = "True";
				SeleniumFW.APPLICATION_LOGS.info("**Open Url**");
				SeleniumFW.APPLICATION_LOGS.info(""+sObjectName);
				errorMsg=driver.getTitle();
			}
			else if (sAction.equalsIgnoreCase("mouseOver"))//use only xpath
			{
				typeOflocator = PerformActions.getLocator(sObjectName);	
				if((sObjectName.contains("="))&&(sObjectName.contains("//"))==false)
				{
					sObjectName = sObjectName.split("=")[1];
				}
				if(PerformActions.mouseOver(driver,typeOflocator,sObjectName,sValue)==true)
				{
					act="True";
				}else{
					act="False";
				}
				act="True";
			}
			else if (sAction.equalsIgnoreCase("upLoadFiles"))
			{
				String s = Configure.fileUploadHandler+" "+sValue;
				Runtime r = Runtime.getRuntime();
				r.exec(s);

				typeOflocator=PerformActions.getLocator(sObjectName);
				if((sObjectName.contains("="))&&(sObjectName.contains("//")==false) && (sObjectName.contains("CSS=")==false))
				{
					sObjectName = sObjectName.split("=")[1];
				}

				if(PerformActions.clickAndWait(driver,typeOflocator,sObjectName,sValue)==true)
				{
					act="True";
				}
				else
				{
					act="False";
				}
			}
			if(sAction.equalsIgnoreCase("ExecuteQueryAndStoreValues"))
	        {
	        	String varToStore=sValue.split(":")[0];
				sValue=sValue.split(":")[1];
				PreparedStatement pst=null;
	        	if(varToStore.equalsIgnoreCase("typicalRecharge")||varToStore.equalsIgnoreCase("SpecialRecharge")){
	        		
	            	ResultSet rs;
	                String temp=null;
	                dbConn = db.createConnection(sObjectName);
	               try {
	                 Statement stmt = dbConn.createStatement();
	              System.out.println ("sValueDB : "+sValue);
	               if (sValue.indexOf("get")>=0)
	                           {
	                                       String tempVal=sValue.split("-")[1];
	                                       if (tempVal.indexOf("'")>=0)
	                                       {
	                                                   tempVal=tempVal.split("'")[0];
	                                       }
	                                       sValue=sValue.replaceAll("get-"+tempVal, hm.get(tempVal));
	                           }

	               sValue=sValue.trim();
	               sValue="select C_CUSTOMER_ID AS CellularPhoneNumber,C_AUTHORIZATION_ID AS AuthorizationNumber,C_TRANSACTION_ID AS TransactionNumber,C_RETAILER_ID AS DistributorId,C_PARTNER_ID AS DistributorId ,C_PAYMENT_AMOUNT AS TransactionAmount,C_FEE_AMOUNT AS FeeAmount,C_AIR_TIME AS AirTime,C_DEBIT_AMOUNT AS DebitAmount,C_PARENT_DIST_ID AS ParentDist from T_SETTLEMENT_TRANSACTION ss where ss.C_ORDER_ID='"+sValue+"'";
	               
	               System.out.println("typicalRecharge qurrey-->"+sValue);
	               
	               pst = dbConn.prepareStatement(sValue);
	    	        Thread.sleep(1000);//SHANSUDINI
	    			System.out.println("connected");
	    			rs = pst.executeQuery();  
	    			SeleniumFW.APPLICATION_LOGS.info("**** Db Connected successfully And Executed Query Successfuly ****");
	    			SeleniumFW.APPLICATION_LOGS.info("---Query---");
	    			SeleniumFW.APPLICATION_LOGS.info( sValue );
	    			ResultSetMetaData rsMetaData = rs.getMetaData();
	    	        ArrayList<String> list= new ArrayList<String>();
	    	        while (rs.next()) {
	    	            list.add(rs.getString("CellularPhoneNumber"));//CELLULARPHONENUMBER
	    	            list.add(rs.getString("AuthorizationNumber"));//AUTHoRIZATIONNUMBER
	    	            list.add(rs.getString("TransactionNumber"));//TRANSACTIONNUMBER
	    	            list.add(rs.getString("DistributorId"));//DISTRIBUTORID
	    	            list.add(rs.getString("DistributorId"));//DISTRIBUTORID
	    	            list.add(rs.getString("TransactionAmount"));//TRANSACTIONAMOUNT
	    	            list.add(rs.getString("FeeAmount"));//FEEAMOUNT
	    	            list.add(rs.getString("AirTime"));//AIRTIME
	    	            list.add(rs.getString("DebitAmount"));//DEBITAMOUNT
	    	            list.add(rs.getString("ParentDist"));//PARENTDIST
	    	            String[] result = new String[list.size()];
	    	            System.out.println("---list"+list.get(0));
	    	            
	    	            for(int j=1;j<=list.size();j++){
	    	            	
	    	            	String ssss;
	    	            	ssss=rsMetaData.getColumnName(j)+":"+list.get(j-1);
	    	            	hm.put(rsMetaData.getColumnName(j),list.get(j-1));
	    	            	System.out.println(rsMetaData.getColumnName(j)+":"+list.get(j-1));
	    	            	System.out.println("value-->" +ssss);
	    	            	SeleniumFW.APPLICATION_LOGS.info("****Stored values From Db****");
	    	            	SeleniumFW.APPLICATION_LOGS.info(rsMetaData.getColumnName(j)+" ---> "+list.get(j-1));
	    	            	
	    	            }
	    	        }
	    	    	act="True";
	              
	               
	                System.out.println ("QUIT FROM DATABASE");
	               }
	               catch (SQLException e) {
	            	   act="False";
	                   e.printStackTrace();
	                   SeleniumFW.APPLICATION_LOGS.info("***** getting Exception in Db Connect *****");
	   				   SeleniumFW.APPLICATION_LOGS.error(e);
	                  
	               }
	               finally{
	            	   pst.close();
	            	   dbConn.close();
	            	   System.out.println("Db Connection Closed");
	            	   SeleniumFW.APPLICATION_LOGS.info("***** Db Connections Closed Successfully *****");
	               }
	               }
	        	if(varToStore.equalsIgnoreCase("PacketsaleRecharge")){
	        		//PreparedStatement pst;
	            	ResultSet rs;
	                String temp=null;
	                dbConn = db.createConnection(sObjectName);
	               try {
	                 Statement stmt = dbConn.createStatement();
	              System.out.println ("sValueDB : "+sValue);
	               if (sValue.indexOf("get")>=0)
	                           {
	                                       String tempVal=sValue.split("-")[1];
	                                       if (tempVal.indexOf("'")>=0)
	                                       {
	                                                   tempVal=tempVal.split("'")[0];
	                                       }
	                                       sValue=sValue.replaceAll("get-"+tempVal, hm.get(tempVal));
	                           }

	               sValue=sValue.trim();
	               sValue="select C_CUSTOMER_ID AS CellularPhoneNumber,C_AUTHORIZATION_ID AS AuthorizationNumber,C_TRANSACTION_ID AS TransactionNumber,C_RETAILER_ID AS DistributorId,C_PARTNER_ID AS DistributorId ,C_PAYMENT_AMOUNT AS TransactionAmount,C_FEE_AMOUNT AS FeeAmount,C_AIR_TIME AS AirTime,C_DEBIT_AMOUNT AS DebitAmount,C_PARENT_DIST_ID AS ParentDist from T_SETTLEMENT_TRANSACTION ss where ss.C_ORDER_ID='"+sValue+"'";
	               
	               System.out.println("typicalRecharge qurrey-->"+sValue);
	               
	               pst = dbConn.prepareStatement(sValue);
	    	        Thread.sleep(2000);
	    			//System.out.println("connected");
	    			rs = pst.executeQuery();  
	    			SeleniumFW.APPLICATION_LOGS.info("**** Db Connected successfully And Executed Query Successfuly ****");
	    			SeleniumFW.APPLICATION_LOGS.info("---Query---");
	    			SeleniumFW.APPLICATION_LOGS.info( sValue );
	    			ResultSetMetaData rsMetaData = rs.getMetaData();
	    	        ArrayList<String> list= new ArrayList<String>();
	    	        while (rs.next()) {
	    	            list.add(rs.getString("CellularPhoneNumber"));//CELLULARPHONENUMBER
	    	            list.add(rs.getString("AuthorizationNumber"));//AUTHoRIZATIONNUMBER
	    	            list.add(rs.getString("TransactionNumber"));//TRANSACTIONNUMBER
	    	            list.add(rs.getString("DistributorId"));//DISTRIBUTORID
	    	            list.add(rs.getString("DistributorId"));//DISTRIBUTORID
	    	            list.add(rs.getString("TransactionAmount"));//TRANSACTIONAMOUNT
	    	            list.add(rs.getString("FeeAmount"));//FEEAMOUNT
	    	            list.add(rs.getString("AirTime"));//AIRTIME
	    	            list.add(rs.getString("DebitAmount"));//DEBITAMOUNT
	    	            list.add(rs.getString("ParentDist"));//PARENTDIST
	    	            String[] result = new String[list.size()];
	    	            System.out.println("---list"+list.get(0));
	    	            
	    	            for(int j=1;j<=list.size();j++){
	    	            	
	    	            	String ssss;
	    	            	ssss=rsMetaData.getColumnName(j)+":"+list.get(j-1);
	    	            	hm.put(rsMetaData.getColumnName(j),list.get(j-1));
	    	            	System.out.println(rsMetaData.getColumnName(j)+":"+list.get(j-1));
	    	            	System.out.println("value-->" +ssss);
	    	            	SeleniumFW.APPLICATION_LOGS.info("****Stored values From Db****");
	    	            	SeleniumFW.APPLICATION_LOGS.info(rsMetaData.getColumnName(j)+" ---> "+list.get(j-1));
	    	            }
	    	        }
	    	    	act="True";
	                System.out.println ("QUIT FROM DATABASE");
	               }
	               catch (SQLException e) {
	            	   act="False";
	                   e.printStackTrace();
	                   SeleniumFW.APPLICATION_LOGS.info("***** getting Exception in Db Connect *****");
	   				   SeleniumFW.APPLICATION_LOGS.error(e);
	                  
	               }
	               finally{
	            	   
	            	   pst.close();
	            	   dbConn.close();   
	            	   SeleniumFW.APPLICATION_LOGS.info("***** Db Connections Closed Successfully *****");
	               }
	               }
	        	
	        
	      }

			if (sAction.equalsIgnoreCase("DBConnect")) 
			{
				String temp=null;
			    Statement stmt=null;
			    String tempVal=null;
			    dbConn = db.createConnection(sObjectName);
		    	try {
				stmt = dbConn.createStatement();
				String varToStore=sValue.split(":")[0];
				keyVal=varToStore;
				sValue=sValue.split(":")[1];
				
				/*    if (sValue.indexOf("get")>=0)
	        {
	              String tempVal=sValue.split("-")[1];
	              if (tempVal.indexOf("'")>=0)
	              {
	                    tempVal=tempVal.split("'")[0];
	              }
	              sValue=sValue.replaceAll("get-"+tempVal, hm.get(tempVal));
	              if(sValue.indexOf("get")>=0)
	              {
	                    tempVal=sValue.split("-")[1];
	                    if (tempVal.indexOf("'")>=0)
	                    {
	                          tempVal=tempVal.split("'")[0];
	                    }
	                    sValue=sValue.replaceAll("get-"+tempVal, hm.get(tempVal));
	              }
	        }*/

				//select C_COMMENTS from T_SETTLEMENT_TRANSACTION where  C_RETAILER_ID='get-distID' and C_TRANSACTION_ID='get-TransactionNumber'
				if (sValue.indexOf("'readProperty")>=0)
				{
					tempVal=sValue.split("readProperty-")[1];//MXAH_MD' and C_AUTHORIZATION_ID='get-TransactionNumber'
					String tempVal1=tempVal.split("'")[0].trim();
					System.out.println("Pro file Value-->"+tempVal1);
					//String tempVal=sValue.split("-")[1];
					//sValue=sValue.split("-")[1];
					String s=Locators.getLocators(tempVal1);
					System.out.println("shan prop val-->"+s);
					sValue=sValue.replaceAll("readProperty-"+tempVal1,s);
					//sValue=Locators.getLocators(tempVal);
					sValue=sValue.trim();
										}
				//kv
				if (sValue.indexOf("'ElkBenfits")>=0)
				{
					tempVal=sValue.split("ElkBenfits-")[1];//MXAH_MD' and C_AUTHORIZATION_ID='get-TransactionNumber'
					String rr=tempVal.split("'")[0].trim();
					String tempVal1=tempVal.split("'")[0].trim();
					//3|14|26
					tempVal1= hm.get(tempVal1);
					System.out.println("getting Pipe value-->"+tempVal1);//3|14|26
					tempVal1=tempVal1.replace("|", ",");
					
					String s[] = tempVal1.split(",");
					 String d ="";
					  for(int i=0;i<s.length;i++){
						  String s1 = s[i];
						  String k;
						  k=s1;
						System.out.println("string---"+k);
						
						
						String r="'"+k+"'";
						
						
						
						if(d==""){
							d=r;	
						}else{
							d=d+","+""+r;
						}
						
						System.out.println("shan-->"+d);
						
					  }
					
					  sValue=sValue.replaceAll("'ElkBenfits-"+rr+"'",d);
					
					System.out.println("Elk---->"+sValue);
					
										}
				
				
				
				int count=sValue.split("'get").length;
				System.out.println("count-- "+count);
				for(int i=0;i<count-1;i++)
				{
					 tempVal=sValue.split("get-")[1];
					System.out.println("tempVal--"+tempVal);
					if (tempVal.indexOf("'")>=0)
					{
						tempVal=tempVal.split("'")[0];
					}
					if (hm.get(tempVal)!=null)
					{
						sValue=sValue.replaceAll("get-"+tempVal, hm.get(tempVal));
						System.out.println("svalue-- "+sValue);
						sValue=sValue.trim();
					}
				}
				

				//System.out.println("Query is shan "+sValue);
				Thread.sleep(500);//SHANSUDINI
				ResultSet rset = stmt.executeQuery(sValue);
				Thread.sleep(500);
				SeleniumFW.APPLICATION_LOGS.info("**** Db Connected successfully And Executed Query Successfuly ****");
				SeleniumFW.APPLICATION_LOGS.info("---Query---");
				SeleniumFW.APPLICATION_LOGS.info( sValue );
				
				while (rset.next()) 
				{
					System.out.println ("VALUE FROM DATABASE = " + rset.getString(1));
					temp = rset.getString(1).trim();
					DbValue=temp;
				}
				SeleniumFW.APPLICATION_LOGS.info("---Query Executed and Get value from Db and Strored varibale ---");
				SeleniumFW.APPLICATION_LOGS.info( temp );
				SeleniumFW.APPLICATION_LOGS.info( varToStore );
				
				hm.put(varToStore, temp);
				
			} catch (SQLException e) {
				e.printStackTrace();
				SeleniumFW.APPLICATION_LOGS.info("***** getting Exception in Db Connect *****");
				SeleniumFW.APPLICATION_LOGS.error(e);
			}
			finally{
				stmt.close();
				dbConn.close();
				SeleniumFW.APPLICATION_LOGS.info("***** Db Connections Closed Successfully *****");
			}
}
			/*	if (sAction.equalsIgnoreCase("ExecQuery")) 
		{		
			try {
            Statement stmt = dbConn.createStatement();
            System.out.println ("sValueDB : "+sValue);
            int a = stmt.executeUpdate(sValue);
            System.out.println ("returnDB " + a );
            stmt.close();
            dbConn.close();
			System.out.println ("QUIT FROM DATABASE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}*/
			if (sAction.equalsIgnoreCase("DBConnectSyn")) 
			{	
				Thread.sleep(2000);
	            String temp=null;
				Statement stmt=null;
				dbConn = db.createConnection(sObjectName);
				try {
					stmt = dbConn.createStatement();
					String varToStore=sValue.split(":")[0];
					sValue=sValue.split(":")[1];
					     if (sValue.indexOf("get")>=0)
	                {
	                      String tempVal=sValue.split("-")[1];
	                      if (tempVal.indexOf("'")>=0)
	                      {
	                            tempVal=tempVal.split("'")[0];
	                      }
	                      sValue=sValue.replaceAll("get-"+tempVal, hm.get(tempVal));
	                      if(sValue.indexOf("get")>=0)
	                      {
	                            tempVal=sValue.split("-")[1];
	                            if (tempVal.indexOf("'")>=0)
	                            {
	                                  tempVal=tempVal.split("'")[0];
	                            }
	                            sValue=sValue.replaceAll("get-"+tempVal, hm.get(tempVal));
	                      }
	                }

	                //select C_COMMENTS from T_SETTLEMENT_TRANSACTION where  C_RETAILER_ID='get-distID' and C_TRANSACTION_ID='get-TransactionNumber'
	                int count=sValue.split("'get").length;
	                System.out.println("count-- "+count);
	                for(int i=0;i<count-1;i++)
	                {
	                      String tempVal=sValue.split("-")[1];
	                      System.out.println("tempVal--"+tempVal);
	                      if (tempVal.indexOf("'")>=0)
	                      {
	                            tempVal=tempVal.split("'")[0];
	                      }

	                      sValue=sValue.replaceAll("get-"+tempVal, hm.get(tempVal));
	                      System.out.println("svalue-- "+sValue);
	                }
	                Thread.sleep(10000);
					ResultSet rset = stmt.executeQuery(sValue);
					Thread.sleep(1000);
					SeleniumFW.APPLICATION_LOGS.info("**** Db Connected successfully And Executed Query Successfuly ****");
					SeleniumFW.APPLICATION_LOGS.info("---Query---");
					SeleniumFW.APPLICATION_LOGS.info( sValue );

	            while (rset.next()) 
	            {
	                System.out.println ("VALUE FROM DATABASE = " + rset.getString(1));
	                temp = rset.getString(1).trim();
	            }
	            hm.put(varToStore, temp);
	            SeleniumFW.APPLICATION_LOGS.info("---Query Executed and Get value from Db and Stored varibale ---");
				SeleniumFW.APPLICATION_LOGS.info( temp );
				SeleniumFW.APPLICATION_LOGS.info( varToStore );
	            
			} catch (SQLException e) {
				e.printStackTrace();
				SeleniumFW.APPLICATION_LOGS.info("***** getting Exception in Db Connect *****");
				SeleniumFW.APPLICATION_LOGS.error(e);
			}
			finally{
				stmt.close();
	            dbConn.close();
	            SeleniumFW.APPLICATION_LOGS.info("***** Db Connections Closed Successfully *****");
			}
		
			}
			else if (sAction.equalsIgnoreCase("storeValueFromIvrRequest"))//store transaction id from IVR
			{
				Thread.sleep(4000);
				String ss=TestCase.IVRREQUEST;
				String sss=BNIClient.BNIRequest;
				if(ss != null){
				String appVal=ss;
				  String[] par1=appVal.split(";");
				  for(int g=0;g<par1.length;g++){
					  String[]s= par1[g].split("=");
					  if(s.length==2){
					  Map<String,String> mp=new HashMap<String, String>();
					  
					  mp.put(s[0], s[1]);
					  Set s1=mp.entrySet();
					  Iterator it=s1.iterator();
					  while(it.hasNext()){
						  Map.Entry m =(Map.Entry)it.next();
						  
				            String key=(String)m.getKey();
				            String value=(String)m.getValue();
				            System.out.println("Key :"+key+"  Value :"+value);
				           if(key.equalsIgnoreCase(sValue)){
				        	   
				        	   keyVal=(String)m.getValue();
				           }
				            hm.put(sValue,keyVal);
				            }
				            
					  }
				  } 
				  
				}
				else if(sss != null){
					String appVal=sss;
					  String[] par1=appVal.split(";");
					  for(int g=0;g<par1.length;g++){
						  String[]s= par1[g].split("=");
						  if(s.length==2){
						  Map<String,String> mp=new HashMap<String, String>();
						  
						  mp.put(s[0], s[1]);
						  Set s1=mp.entrySet();
						  Iterator it=s1.iterator();
						  while(it.hasNext()){
							  Map.Entry m =(Map.Entry)it.next();
							  
					            String key=(String)m.getKey();
					            String value=(String)m.getValue();
					            System.out.println("Key :"+key+"  Value :"+value);
					           if(key.equalsIgnoreCase(sValue)){
					        	   
					        	   keyVal=(String)m.getValue();
					           }
					            hm.put(sValue,keyVal);
					            }
					            
						  }
					  } 
					  
					}
			}
			else if (sAction.equalsIgnoreCase("storeValueFromWebSimulator"))//store transaction id from IVR
			  {
				Thread.sleep(4000);
				if(((IVRSimulator.response) != null)){
				String appVal=IVRSimulator.response;
				  String[] par1=appVal.split(";");
				  for(int g=0;g<par1.length;g++){
					  String[]s= par1[g].split("=");
					  if(s.length==2){
					  Map<String,String> mp=new HashMap<String, String>();
					  
					  mp.put(s[0], s[1]);
					  Set s1=mp.entrySet();
					  Iterator it=s1.iterator();
					  while(it.hasNext()){
						  Map.Entry m =(Map.Entry)it.next();
						  
				            String key=(String)m.getKey();
				            String value=(String)m.getValue();
				            System.out.println("Key :"+key+"  Value :"+value);
				           if(key.equalsIgnoreCase(sValue)){
				        	   
				        	   keyVal=(String)m.getValue();
				           }
				            hm.put(sValue,keyVal);
				            }
				            
					  }
				  } 
				 
				  
				}
				
				else if((BNIClient.BNIResponce != null)){

					String appVal=BNIClient.BNIResponce;
					  String[] par1=appVal.split(";");
					  for(int g=0;g<par1.length;g++){
						  String[]s= par1[g].split("=");
						  if(s.length==2){
						  Map<String,String> mp=new HashMap<String, String>();
						  
						  mp.put(s[0], s[1]);
						  Set s1=mp.entrySet();
						  Iterator it=s1.iterator();
						  while(it.hasNext()){
							  Map.Entry m =(Map.Entry)it.next();
							  
					            String key=(String)m.getKey();
					            String value=(String)m.getValue();
					            System.out.println("Key :"+key+"  Value :"+value);
					           if(key.equalsIgnoreCase(sValue)){
					        	   
					        	   keyVal=(String)m.getValue();
					           }
					            hm.put(sValue,keyVal);
					            }
					            
						  }
					  } 
					 
					  
					
				}
			}
			
			else if (sAction.equalsIgnoreCase("storeValueFromAtmWebSimulator"))//store transaction id from IVR
			  {
				
				/*
				 * RESPONSE : CODMSG=22&RETORNO=000&RETMSG=Exito&NUMTRAN=59559590&NUMAUT=284083&NUMNV=000001209814&VALORN=1.49&VALORICE=0.1791&VALORIVA=0.1493&EXPIRACION=2016-02-2
				 */
				
			Thread.sleep(4000);
		if((ATMClientSimulator.Atmresponse) != null){
			String appVal=ATMClientSimulator.Atmresponse;
			String[] par1=appVal.split("&");
			for(int g=0;g<par1.length;g++){
				  String[]s= par1[g].split("=");
				  if(s.length==2){
				  Map<String,String> mp=new HashMap<String, String>();
				  mp.put(s[0], s[1]);
				  Set s1=mp.entrySet();
				  Iterator it=s1.iterator();
				  while(it.hasNext()){
					  Map.Entry m =(Map.Entry)it.next();
					    String key=(String)m.getKey();
			            String value=(String)m.getValue();
			            System.out.println("Key :"+key+"  Value :"+value);
			           if(key.equalsIgnoreCase(sValue)){

			        	   keyVal=(String)m.getValue();
			           }
			           
		        	  // SeleniumFW.APPLICATION_LOGS.info("storeValueFromAtmWebSimulator From-->"+keyVal);
			            hm.put(sValue,keyVal);
			            
				  }
			            
				  }
			  } 
			  
			}
		SeleniumFW.APPLICATION_LOGS.info("storeValueFromAtmWebSimulator In-->"+sValue+":"+keyVal);
		}
			
			else if (sAction.equalsIgnoreCase("storeDamagedCoupon"))//store transaction id from IVR
			{
				//Amount_After_Charge=get-couponPin:get-DamagedCouponPinLength
				String temp="",sValue1="";
				int l=0,l1=0,l2=0;
				String DamageCoupon = null;
				
					sValue1= sValue.split("=")[0];
					System.out.println("Valuestore in ---->"+sValue1);
					
					temp = sValue.split("=")[1];//get-couponPin:get-DamagedCouponPinLength
					System.out.println("tempppppppp--->"+temp);
					String []a=temp.split(":");
					System.out.println("storeDamagedCoupon---->"+a[0]);
					System.out.println("storeDamagedCoupon---->"+a[1]);
					if (a[0].indexOf("get")>=0)
					{
						//DamageCoupon = sObjectName.replaceAll(sObjectName.split("=")[1], hm.get(sObjectName.split("-")[1]).trim());
						DamageCoupon=hm.get(a[0].split("-")[1]);
						System.out.println("DamageCoupon--->"+DamageCoupon);
					    l=DamageCoupon.length();
					}
					if (a[1].indexOf("get")>=0)
					{
						//String DamagedCouponMinimumPINLength = sObjectName.replaceAll(sObjectName.split("=")[1], hm.get(sObjectName.split("-")[1]).trim());
						String DamagedCouponMinimumPINLength=hm.get(a[1].split("-")[1]);
						System.out.println("DamageCouponPinLenght--->"+DamagedCouponMinimumPINLength);
					    l1=Integer.parseInt(DamagedCouponMinimumPINLength);
					}
					 l2=l-l1;
					 String temp1=DamageCoupon.substring(l2);
					System.out.println("getting Damaged bad coupon---->"+temp1);
					sValue=sValue1;
					hm.put(sValue,temp1);
					
					keyVal=temp1;
					System.out.println("sValue strored in --->" +sValue );
				
			}
			
			else if(sAction.equalsIgnoreCase("DeleteQuery"))
			{
				String temp=null;
				Statement stmt=null; 
				String tempVal=null;
				dbConn = db.createConnection(sObjectName);
				try {
					 stmt = dbConn.createStatement();
					System.out.println ("sValueDB : "+sValue);
					
					if (sValue.indexOf("'readProperty")>=0)
					{
						 tempVal=sValue.split("readProperty-")[1];//MXAH_MD' and C_AUTHORIZATION_ID='get-TransactionNumber'
						String tempVal1=tempVal.split("'")[0].trim();
						System.out.println("Pro file Value-->"+tempVal1);
						//String tempVal=sValue.split("-")[1];
						//sValue=sValue.split("-")[1];
						String s=Locators.getLocators(tempVal1);
						System.out.println("shan prop val-->"+s);
						sValue=sValue.replaceAll("readProperty-"+tempVal1,s);
						//sValue=Locators.getLocators(tempVal);
						sValue=sValue.trim();
											}
					
					
					
					int count=sValue.split("'get").length;
					System.out.println("count-- "+count);
					for(int i=0;i<count-1;i++)
					{
						 tempVal=sValue.split("get-")[1];
						System.out.println("tempVal--"+tempVal);
						if (tempVal.indexOf("'")>=0)
						{
							tempVal=tempVal.split("'")[0];
						}
						if (hm.get(tempVal)!=null)
						{
							sValue=sValue.replaceAll("get-"+tempVal, hm.get(tempVal));
							System.out.println("svalue-- "+sValue);
							sValue=sValue.trim();
						}
					}
					/*if (sValue.indexOf("get")>=0)
					{
						String tempVal=sValue.split("-")[1];
						if (tempVal.indexOf("'")>=0)
						{
							tempVal=tempVal.split("'")[0];
						}
						sValue=sValue.replaceAll("get-"+tempVal, hm.get(tempVal));
					}*/
					System.out.println("ExecuteQurrey--->"+sValue);
					int a = stmt.executeUpdate(sValue);
					SeleniumFW.APPLICATION_LOGS.info("**** Db Connected successfully And Executed Query Successfuly ****");
					SeleniumFW.APPLICATION_LOGS.info("---Query---");
					SeleniumFW.APPLICATION_LOGS.info( sValue );
					SeleniumFW.APPLICATION_LOGS.info( a );
					if((a>=1) || (a==0))
					{
						act="True";
					}
					else
					{
						act="False";
					}
					System.out.println ("returnDB " + a );
					
					System.out.println ("QUIT FROM DATABASE");
				} catch (SQLException e) {
					e.printStackTrace();
					SeleniumFW.APPLICATION_LOGS.info("***** getting Exception in Db Connect *****");
					SeleniumFW.APPLICATION_LOGS.error(e);
				}finally{
					stmt.close();
					dbConn.close();
					SeleniumFW.APPLICATION_LOGS.info("***** Db Connections Closed Successfully *****");
				}
				}
			else if(sAction.equalsIgnoreCase("ExecuteQuery"))
			{      
	        	Statement stmt = null;
	        	String temp=null;
				dbConn = db.createConnection(sObjectName);
				try {
					 stmt = dbConn.createStatement();
					System.out.println ("sValueDB : "+sValue);
					if (sValue.indexOf("'readProperty")>=0)
					{
						String tempVal=sValue.split("readProperty-")[1];//MXAH_MD' and C_AUTHORIZATION_ID='get-TransactionNumber'
						String tempVal1=tempVal.split("'")[0].trim();
						System.out.println("Pro file Value-->"+tempVal1);
						//String tempVal=sValue.split("-")[1];
						//sValue=sValue.split("-")[1];
						String s=Locators.getLocators(tempVal1);
						System.out.println("shan prop val-->"+s);
						sValue=sValue.replaceAll("readProperty-"+tempVal1,s);
						//sValue=Locators.getLocators(tempVal);
						sValue=sValue.trim();
											}
					if (sValue.indexOf("get")>=0)
					{
						String tempVal=sValue.split("get-")[1];
						if (tempVal.indexOf("'")>=0)
						{
							tempVal=tempVal.split("'")[0];
						}
						sValue=sValue.replaceAll("get-"+tempVal, hm.get(tempVal));
						System.out.println("query " +sValue);
					}
					if (sValue.indexOf("fetch")>=0) // satish
					{
						String tempVal=sValue.split("-")[1];
						if (tempVal.indexOf("'")>=0)
						{
							tempVal=tempVal.split("'")[0];
						}
						sValue=sValue.replaceAll("fetch-"+tempVal, hm.get(tempVal));
					}
					
					//System.out.println("Qurrey in ExecuteQuery Method-->"+sValue);
					SeleniumFW.APPLICATION_LOGS.info("**** Db Connected successfully And Executed Query Successfuly ****");
					SeleniumFW.APPLICATION_LOGS.info("---Query---");
					SeleniumFW.APPLICATION_LOGS.info( sValue );
					int a = stmt.executeUpdate(sValue);
					SeleniumFW.APPLICATION_LOGS.info( "Updated Query and get value"+ a );
					if(a>=1)
					{
						act="True";
					}
					else
					{
						act="False";
					}
					//System.out.println ("returnDB " + a );
					
					
					//System.out.println ("QUIT FROM DATABASE");
				} catch (SQLException e) {
					e.printStackTrace();
					SeleniumFW.APPLICATION_LOGS.info("***** getting Exception in Db Connect *****");
					SeleniumFW.APPLICATION_LOGS.error(e);
				}
				finally{
					stmt.close();
					dbConn.close();
					SeleniumFW.APPLICATION_LOGS.info("***** Db Connections Closed Successfully *****");
				}
			
	        }


			/*else if (sAction.equalsIgnoreCase("storeUniqueEmail"))
			{
				int randNum2 = new Double( (Math.random()+1) * 1000).intValue();
				randMail = "testing1"+randNum2+"@trashmail.net";
				sValue = randMail;
				hm.put(sObjectName, sValue);
			}
			else if (sAction.equalsIgnoreCase("storeUniqueMobileEmail"))
			{
				int randNum3 = new Double( (Math.random()+1) * 1000).intValue();
				randMail = "999424"+randNum3+"@docomo.ne.jp";
				sValue = randMail;
				hm.put(sObjectName, sValue);
			}
			else if (sAction.equalsIgnoreCase("storeRandomSecurityID"))
			{
				int randNum4 = new Double( (Math.random()+1) * 1000).intValue();
				randMail = "75"+randNum4;
				sValue = randMail;
				hm.put(sObjectName, sValue);
			}*/
			/*else if (sAction.equalsIgnoreCase("store"))
			{
				if(sObjectName.indexOf("Number")>=0)
				{
					sValue=DataGenerator.randNumSales;
					hm.put(sObjectName, sValue);
				}
				else
				{
					sValue=DataGenerator.randName;
					hm.put(sObjectName, sValue);
				}
			}
			else if ((sAction.equalsIgnoreCase("Attribute"))||(sAction.equalsIgnoreCase("TextPresent"))||(sAction.equalsIgnoreCase("storeTitle")))
			{
				hm.put(sValue, sObjectName);
			}*/
			else if (sAction.equalsIgnoreCase("storeText"))
			{
				Thread.sleep(500);//1000
				String appVal = StepValidateImplimentations.getValueFromEditbox(driver, typeOflocator, sObjectName, sValue);
				System.out.println("storeText Appvalue in navigate --> "+appVal);
				appVal=appVal.trim();
				hm.put(sValue, appVal);
				keyVal=sValue;
				System.out.println("storeText -----" + sValue);
				SeleniumFW.APPLICATION_LOGS.info("storeText"+"in"+appVal);
			}
			else if (sAction.equalsIgnoreCase("storeRechargeAmount"))
			{
				//sval=get-RechargeAmount*get-exchangeRate

				String rechargeAmt =  sObjectName.split("\\*")[0].split("-")[1];//RechargeAmount
				System.out.println("rechargeAmt--------->"+rechargeAmt);
				String excRate = sObjectName.split("\\*")[1].split("-")[1];//exchangeRate
				System.out.println("excRate--------->"+excRate);
				//expVal.split("-")[1]
				double rechargeAmount = Double.parseDouble(hm.get(rechargeAmt));
				System.out.println("rechargeAmount--------->"+rechargeAmount);
				double exchangeRate = Double.parseDouble(hm.get(excRate));
				System.out.println("exchangeRate--------->"+exchangeRate);
				double totalRechargeAmount = ((rechargeAmount) * (exchangeRate));
				System.out.println("totalRechargeAmount "+totalRechargeAmount);
				//DecimalFormat(“###.##”);
				DecimalFormat df = new DecimalFormat("##.00");
				String totalRechargeAmount1=df.format(totalRechargeAmount);
				System.out.println("AMOUNT------>"+totalRechargeAmount1);

				//hm.put(sValue,Double.toString(totalRechargeAmount));
				hm.put(sValue,totalRechargeAmount1);
				keyVal=sValue;
			}
			else if (sAction.equalsIgnoreCase("storeSelectedValue"))
			{
				Thread.sleep(200);
				String appVal=StepValidateImplimentations.getStoreSelectedValue(driver, typeOflocator, sObjectName, sValue);
				SeleniumFW.APPLICATION_LOGS.info("storeSelectedValue in appVal -->" +appVal);
				appVal=appVal.trim();
				hm.put(sValue, appVal);
				keyVal=appVal;
				SeleniumFW.APPLICATION_LOGS.info("store Selected Value In"+sValue+"with value"+appVal);
			}
			else if(sAction.equalsIgnoreCase("storeComboSelectedItem"))
			{
				String appVal = StepValidateImplimentations.getValueFromDropdown(driver, typeOflocator, sObjectName, sValue);
				System.out.println("appVAl from dropdown"+appVal);
				SeleniumFW.APPLICATION_LOGS.info("appVAl from dropdown"+appVal);
				Thread.sleep(3000);
				//sel.highlight(sObjectName);
				hm.put(sValue, appVal);             
				keyVal=appVal;
			}
			/*else if (sAction.equalsIgnoreCase("storeValueFromWebSimulator"))//store transaction id from IVR
			{
				SeleniumFW.APPLICATION_LOGS.info("storeValueFromWebSimulator");
				Thread.sleep(1000);//SHANSUDINI
				//sel.highlight(sObjectName);
				//String appVal = sel.getText(sObjectName);
				String appVal=driver.findElement(By.xpath(sObjectName)).getText();
				//String GetResp=sel.getText(expTarget);
				String var[]=appVal.split("$");
				System.out.println("varvalue---"+var[0]);
				//System.out.println("varvalue111---"+var[1]);
				String[] par=var[0].split(";");
				for(int g=0;g<= par.length;g++)
				{
					if(par[g].contains("ERRORCODE")){
						String actal[]=par[g].split("=");
						System.out.println("value"+actal[1]);
						hm.put("ErrorCodeIVR", actal[1]);
						//System.out.println("valugeivrrrrrrrryyyyERRORCODE"+actal[1]);
						//keyVal=sValue;
						SeleniumFW.APPLICATION_LOGS.info("valugeivrrrrrrrryyyyERRORCODE"+actal[1]);
						//break;
					}
					else if(par[g].contains("SOURCEACCTBAL")){
						String actal[]=par[g].split("=");
						System.out.println("value"+actal[1]);
						hm.put("SOURCEACCTBAL", actal[1]);
						//System.out.println("valugeivrrrrrrrryyyySOURCEACCTBAL"+actal[1]);
						SeleniumFW.APPLICATION_LOGS.info("valugeivrrrrrrrryyyySOURCEACCTBAL"+actal[1]);
						//keyVal=sValue;
						//break;
					}
					else if(par[g].contains("TRANSID")){
						String actal1[]=par[g].split("=");
						System.out.println("value"+actal1[1]);
						hm.put(sValue, actal1[1]);
						//System.out.println("valugeivrrrrrrrrTRANSID"+actal1[1]);
						SeleniumFW.APPLICATION_LOGS.info("valugeivrrrrrrrrTRANSID"+actal1[1]);
						keyVal=sValue;
						break;


						//actual=actal[1];
						//return actual.equals(expVal);
						//break;
					}
					// else if(par[g].contains("TRANSID")){
					//   String actal[]=par[g].split("=");


					//actual=actal[1];
					//return actual.equals(expVal);
					//break;
				}
			}
*/
			else if(sAction.equalsIgnoreCase("type"))
			{
				try{
				if (sValue.indexOf("readProperty-")==0)
				{
					String tempVal=sValue;
					sValue=sValue.split("-")[1];
					sValue=Locators.getLocators(sValue);
					sValue=sValue.trim();
				}
				if (sObjectName.indexOf(":")>=0)
				{
					String tempStr=sObjectName.split(":")[1];
					String tempStr1=tempStr.split("-")[1];
					sObjectName=sObjectName.split(":")[0]+((hm.get(tempStr1)).toUpperCase());
				}
				if ((sValue.equalsIgnoreCase("SalesVolume"))||(sValue.equalsIgnoreCase("CreditLimit"))||(sValue.equalsIgnoreCase("randomDenomination")))
				{
					DataGenerator.randomSalesVolume();
					sValue=DataGenerator.randNumSales;
				}
				if (sValue.indexOf("get")==0)
				{
					String tempSVal=sValue;
					sValue=hm.get(sValue.split("-")[1]);
					if ((tempSVal.indexOf("-inCaps"))>=0)
					{
						sValue=sValue.toUpperCase();
					}
					if ((tempSVal.indexOf("-sub"))>=0)
					{
						sValue="-"+sValue;

					}
					if ((tempSVal.indexOf("nagativeValue"))>=0)//updated by srialtha
					{

						sValue="-"+sValue.replace(",","" );
					}	
				}

				if (sValue.indexOf("repeatRandom")==0)
				{
					if (sValue.indexOf("inCaps")>=0)
					{
						sValue=DataGenerator.randName.toUpperCase();
					}
					else
					{
						sValue=DataGenerator.randName;
					}
				}
				if (sValue.indexOf("random")==0)
				{
					String[] result1=sValue.split(":");
					numberLength=Integer.parseInt( result1[1]);
					if (sValue.indexOf("Number")>=0)
					{
						sValue= DataGenerator.randomNumberGenerator(numberLength);
						//sValue=DataGenerator.randNum;
					}
					else
					{
						String[] result=sValue.split(":");
						numberOfChar=Integer.parseInt( result[1]);
						DataGenerator.randomName();
						sValue=DataGenerator.randName;
					}
				}
				if (sValue.indexOf("${")==0)
				{
					str = sValue.substring(sValue.indexOf("{")+1,sValue.indexOf("}"));
					mapkey = hm.containsKey(str);
					if (mapkey == true)
						sValue = hm.get(str).toString();
				}
				else if (sValue.equalsIgnoreCase("temppwd"))
				{
					sValue=AutoDismiss.showMessageDialog(null, "Enter the Reset Temp Password");
					hm.put("temppwd", sValue);
				}
				/*if (sValue.indexOf("CurrentDate")==0)
				{
					String sValue1=DataGenerator.generateDateNavigate(sValue);
					sValue=sValue1;
					String attrValue=sel.getAttribute(sObjectName+"@readonly");
					System.out.println("Attribute value " +attrValue);
					if(attrValue.equalsIgnoreCase("true"))
					{
						sel.type(sObjectName, sValue);
					}
				}*/
				value = sValue;
				typeOflocator=PerformActions.getLocator(sObjectName);
				if((sObjectName.contains("="))&&((sObjectName.contains("//"))==false) && (sObjectName.contains("css=")==false))
				{
					sObjectName = sObjectName.split("=")[1];
				}
				else if(sObjectName.contains("css="))
				{
					sObjectName = sObjectName.substring(4, sObjectName.length());
				}
				System.out.println("in type type of locator --> "+typeOflocator);
				System.out.println("sObject name  --> "+sObjectName);
				if(sValue.indexOf("CurrentDate")==0)
				{
					String sValue1=DataGenerator.generateDateNavigate(sValue);
					sValue=sValue1;
					value = sValue;
					if(PerformActions.getAttributeValue(driver,typeOflocator,sObjectName,sValue)==true)
					{
						act="True";
						SeleniumFW.APPLICATION_LOGS.info("Type Object name--->"+sObjectName);
						SeleniumFW.APPLICATION_LOGS.info("Type Value --->"+sValue);
					}
				}
				else if(PerformActions.typeinEditbox(driver,typeOflocator,sObjectName,sValue)==true)
				{
					act="True";
					SeleniumFW.APPLICATION_LOGS.info("Type data In :: "+ sObjectRefName+"with Locator :: "+sObjectName+"And Value :: "+sValue);
						}
				else
				{
					act="False";
					SeleniumFW.APPLICATION_LOGS.info("Type data Fail In :: "+ sObjectRefName+"with Locator :: "+sObjectName+"And Value :: "+sValue);
					
				}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			/*else if(sAction.equalsIgnoreCase("typeDate"))
		{
			sObjectName = sObjectName.split("=")[1];
			if(sValue.indexOf("CurrentDate")==0)
			{
				String sValue1=DataGenerator.generateDateNavigate(sValue);
				sValue=sValue1;
			}			
			sel.getEval("var property; property = window.document.getElementById('"+sObjectName+"').value='"+sValue+"';");
			value=sValue;
			System.out.println("Inside date set");
			act="True";		
		}*/
			else if (sAction.equalsIgnoreCase("actionOnWebTable"))

			{
				act="False";
				//readProperty-AH_SD11:3:CHECK:Null
				String keyVale,ExpType,cNum,ExpValueIntableSearch,rootLocator,collnum;
				keyVale=sValue.split(":")[0].trim();
				if(keyVale.indexOf("readProperty-")==0)
				{
					keyVale=Locators.getLocators(keyVale.split("-")[1]);
					keyVale=keyVale.trim();
					System.out.println("keyVale--> "+keyVale);
				}
				if(keyVale.indexOf("get-")==0)
				{
					//keyVale=Locators.getLocators(keyVale.split("-")[1]);
					keyVale=hm.get(keyVale.split("-")[1]);
					keyVale=keyVale.trim();
					System.out.println("keyVale--> "+keyVale);
				}
				if(keyVale.indexOf("get")==0){
				keyVale=hm.get(keyVale.split("-")[1]);
			}
				cNum=sValue.split(":")[1].trim();

				int rNum=Integer.parseInt(sValue.split(":")[1]);			

				ExpType=sValue.split(":")[2];

				ExpValueIntableSearch=sValue.split(":")[3];

				expVal1=ExpValueIntableSearch;
				if(ExpValueIntableSearch.indexOf("get")==0){

					expVal1=hm.get(ExpValueIntableSearch.split("-")[1]);

				}
				rootLocator=sObjectName; //form/table/tbody/tr/td/table/tbody/tr[6]/td/table/tbody
				String NumberofRowsCount;
				NumberofRowsCount=rootLocator+"/tr["+cNum+"]/td";//getting the number of rows // coloumsssss
				System.out.println("LocatorFormation--->"+NumberofRowsCount);

				//int xPathCountRow=(Integer) sel.getXpathCount(NumberofRowsCount);	
				int xPathCountRow=driver.findElements(By.xpath(NumberofRowsCount)).size();
				System.out.println("ShanCount---->"+ xPathCountRow);
				for(int i=1;i<=xPathCountRow;i++){
					int s=i;
					//System.out.println("Moving row Number---->"+s);
					String sss=NumberofRowsCount+"["+i+"]";
					System.out.println("Number of Coloums-->"+sss);
					//System.out.println("locatorrrrrrrrrr---->"+sss);
					String gettingKeyVal;
					
					//gettingKeyVal=sel.getText(NumberofRowsCount+"["+i+"]");
					gettingKeyVal=driver.findElement(By.xpath(NumberofRowsCount+"["+i+"]")).getText();
					System.out.println("gettin key value --->"+gettingKeyVal);

					if(gettingKeyVal.equalsIgnoreCase(keyVale)){
						int n;
						n=Integer.parseInt(cNum);
						if(ExpType.equalsIgnoreCase("CHECK")){
							//boolean ss=sel.isElementPresent(NumberofRowsCount+"["+(i-1)+"]");
							boolean ss= driver.findElement(By.xpath(NumberofRowsCount+"["+(i-1)+"]")).isDisplayed();
							if(ss==true){
								Thread.sleep(1500);
								driver.findElement(By.xpath(NumberofRowsCount+"["+(i-1)+"]/input[@type='radio']")).click();	
								//driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
								Thread.sleep(1500);
								//sel.check(NumberofRowsCount+"["+(i-1)+"]/input[@type='checkbox']");
								act="True";
								break;
							}
						}
						if(ExpType.equalsIgnoreCase("TYPE")){
							//boolean ss1=sel.isElementPresent(NumberofRowsCount+"["+(i+6)+"]/input[@type='text']");
							boolean ss1= driver.findElement(By.xpath(NumberofRowsCount+"["+(i+6)+"]/input[@type='text']")).isDisplayed();
							if(ss1==true){
								String ssss;
								ssss=NumberofRowsCount+"["+(i+6)+"]/input[@type='text']";
								//sel.type(ssss, expVal1);
								driver.findElement(By.xpath(ssss)).clear();
								driver.findElement(By.xpath(ssss)).sendKeys(expVal1);
								driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
								act="True";
								break;
							}

						}
						if(ExpType.equalsIgnoreCase("TYPEIN_MNPARTNER")){
							//boolean ss1=sel.isElementPresent(NumberofRowsCount+"["+(i+4)+"]/input[@type='text']");
							boolean ss1 = driver.findElement(By.xpath(NumberofRowsCount+"["+(i+4)+"]/input[@type='text']")).isDisplayed();
							if(ss1==true){
								String ssss;
								ssss=NumberofRowsCount+"["+(i+4)+"]/input[@type='text']";
								//sel.type(ssss, expVal1);
								driver.findElement(By.xpath(ssss)).clear();
								driver.findElement(By.xpath(ssss)).sendKeys(expVal1);
							
								act="True";
								break;
							}

						}
						if(ExpType.equalsIgnoreCase("CLICK_REVERSAL")){
							//boolean ss1=sel.isElementPresent(NumberofRowsCount+"["+(i+25)+"]/a/img[@alt='Reversal']");
							boolean ss1 = driver.findElement(By.xpath(NumberofRowsCount+"["+(i+25)+"]/a/img[@alt='Reversal']")).isDisplayed();
							if(ss1==true){
								String ssss;
								ssss=NumberofRowsCount+"["+(i+25)+"]/a/img[@alt='Reversal']";
								//sel.click(ssss);
								//sel.waitForPageToLoad(waitTime);
								driver.findElement(By.xpath(ssss)).click();
								driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
								act="True";
								break;
							}

						}


					}

				}
			}

			/*else if (sAction.equalsIgnoreCase("StoreValueInWebTable"))

			{
				act="False";
				//readProperty-AH_SD11:3:CHECK:Null
				String keyVale,ExpType,cNum,ExpValueIntableSearch,rootLocator,collnum;
				keyVale=sValue.split(":")[0].trim();
				if(keyVale.indexOf("readProperty-")==0)
				{
					keyVale=Locators.getLocators(keyVale.split("-")[1]);
					keyVale=keyVale.trim();
					System.out.println("keyVale--> "+keyVale);
				}
				if(keyVale.indexOf("get")==0)
				{
					keyVale=hm.get(keyVale.split("-")[1]);
				}
				if(keyVale.indexOf("get")==0){
				keyVale=hm.get(keyVale.split("-")[1]);
			}
				cNum=sValue.split(":")[1].trim();

				int rNum=Integer.parseInt(sValue.split(":")[1]);			

				ExpType=sValue.split(":")[2];

				ExpValueIntableSearch=sValue.split(":")[3];

				expVal1=ExpValueIntableSearch;
				if(ExpValueIntableSearch.indexOf("get")==0){

					expVal1=hm.get(ExpValueIntableSearch.split("-")[1]);

				}
				rootLocator=sObjectName; //form/table/tbody/tr/td/table/tbody/tr[6]/td/table/tbody
				String NumberofRowsCount;
				String totalRowsCount=rootLocator+"/tr";
				NumberofRowsCount=rootLocator+"/tr["+cNum+"]/td";//getting the number of rows // coloumsssss
				//int xPathCountRowtotal=(Integer) sel.getXpathCount(totalRowsCount);
				int xPathCountRowtotal=driver.findElements(By.xpath(totalRowsCount)).size();
				
				int SerachNum=Integer.parseInt(cNum);
				System.out.println("LocatorFormation--->"+NumberofRowsCount);
				for( int j=SerachNum;j<=xPathCountRowtotal;j++){
					NumberofRowsCount=rootLocator+"/tr["+j+"]/td";
					//int xPathCountRow=(Integer) sel.getXpathCount(NumberofRowsCount);
					int xPathCountRow = driver.findElements(By.xpath(NumberofRowsCount)).size();
					System.out.println("ShanCount---->"+ xPathCountRow);
					for(int i=1;i<=xPathCountRow;i++){
						int s=i;
						//System.out.println("Moving row Number---->"+s);
						String sss=NumberofRowsCount+"["+i+"]";
						System.out.println("Number of Coloums-->"+sss);
						//System.out.println("locatorrrrrrrrrr---->"+sss);
						String gettingKeyVal;
						//gettingKeyVal=sel.getText(NumberofRowsCount+"["+i+"]");
						gettingKeyVal=driver.findElement(By.xpath(NumberofRowsCount+"["+i+"]")).getText();
						System.out.println("gettin key value --->"+gettingKeyVal);

						if(gettingKeyVal.equalsIgnoreCase(keyVale)){
							int n;
							n=Integer.parseInt(cNum);
							if(ExpType.equalsIgnoreCase("TEXTFIELD")){
								//boolean ss1=sel.isElementPresent(NumberofRowsCount+"["+(i+7)+"]/input[@type='text']");
								boolean ss1=driver.findElement(By.xpath(NumberofRowsCount+"["+(i+7)+"]/input[@type='text']")).isDisplayed();
								if(ss1==true){
									String ssss;
									ssss=NumberofRowsCount+"["+(i+7)+"]/input[@type='text']";
									String appVal;
									//appVal=sel.getValue(ssss);
									appVal=driver.findElement(By.xpath(ssss)).getAttribute("value");
									appVal=appVal.trim();
									hm.put(ExpValueIntableSearch, appVal);
									keyVal=appVal;
									sValue=ExpValueIntableSearch;
									//sel.type(ssss, expVal1);
									act="True";
									//return true;
									break;

								}
							}
							if(ExpType.equalsIgnoreCase("TEXTFIELD_PARTNER")){
								System.out.println(NumberofRowsCount+"["+(i+7)+"]");
								//boolean ss1=sel.isElementPresent(NumberofRowsCount+"["+(i+7)+"]/input[@type='text']");
								boolean ss1=driver.findElement(By.xpath(NumberofRowsCount+"["+(i+7)+"]/input[@type='text']")).isDisplayed();
								if(ss1==true){
									String ssss;
									ssss=NumberofRowsCount+"["+(i+7)+"]/input[@type='text']";
									String appVal;
									//appVal=sel.getValue(ssss);
									appVal=driver.findElement(By.xpath(ssss)).getAttribute("value");
									appVal=appVal.trim();
									hm.put(ExpValueIntableSearch, appVal);
									keyVal=appVal;
									sValue=ExpValueIntableSearch;
									//sel.type(ssss, expVal1);
									act="True";
									//return true;
									break;

								}

							}
						}

					}
					if(act=="True"){
						act="True";
						break;
					}
					else{

						continue;
					}
				}
			}*/

			else if(sAction.equalsIgnoreCase("typeAndWait"))
			{

				if (sValue.indexOf("get")==0)
				{
					sValue=hm.get(sValue.split("-")[1]);
					sValue=sValue.trim();
				}
				if (sValue.indexOf("readProperty-")==0)
				{
					String tempVal=sValue;
					sValue=sValue.split("-")[1];
					sValue=Locators.getLocators(sValue);
					sValue=sValue.trim();
				}
				if (sValue.indexOf("repeatRandom")==0)
				{
					if (sValue.indexOf("inCaps")>=0)
					{
						sValue=DataGenerator.randName.toUpperCase();
					}
					else
					{
						sValue=DataGenerator.randName;
					}
				}
				if (sValue.indexOf("random")==0)
				{
					if (sValue.indexOf("Number")>=0)
					{
						DataGenerator.randomNumber();
						sValue=DataGenerator.randNum;
					}
					else
					{
						String[] result=sValue.split(":");
						numberOfChar=Integer.parseInt( result[1]);
						DataGenerator.randomName();
						sValue=DataGenerator.randName;
					}
				}
				
				value = sValue;
				typeOflocator=PerformActions.getLocator(sObjectName);
				if((sObjectName.contains("="))&&((sObjectName.contains("//"))==false) && (sObjectName.contains("css=")==false))
				{
					sObjectName = sObjectName.split("=")[1];
				}
				else if(sObjectName.contains("css="))
				{
					sObjectName = sObjectName.substring(4, sObjectName.length());
				}
				System.out.println("in type type of locator --> "+typeOflocator);
				System.out.println("sObject name  --> "+sObjectName);
				if(PerformActions.typeAndWait(driver,typeOflocator,sObjectName,sValue)==true)
				{
				act="True";
				}
				else
				{
					act="False";
				}
			}
			
			
			
			
			else if (sAction.equalsIgnoreCase("select"))
			{
				if(sValue.contains("=")==true)
				{
					sValue=sValue.split("=")[1];
					if (sValue.indexOf("get")>=0)
					{
						sValue=sValue.replaceAll("get-"+sValue.split("-")[1], hm.get(sValue.split("-")[1]));
					}
				}
				else if(sValue.indexOf("readProperty-")==0)
				{
					sValue=Locators.getLocators(sValue.split("-")[1]);
					//sValue="label="+sValue;
					sValue=sValue.trim();
				}
				typeOflocator = PerformActions.getLocator(sObjectName);
				if((sObjectName.contains("="))&&(sObjectName.contains("//"))==false)
				{
					sObjectName = sObjectName.split("=")[1];
				}
				
				if(Locators.getLocators("OperatorIdentity").equalsIgnoreCase("Movistar")){
           		 
           		 System.out
							.println("Movister Processing Code"+sValue);
           	     }
           	 else{
           		sValue="Banana";
           		 
           		 System.out
						.println("Banana Processing Code"+sValue);
           	 }
				
				if(PerformActions.selectDropdown(driver,typeOflocator,sObjectName,sValue)==true)
				{
					SeleniumFW.APPLICATION_LOGS.info("Select Value from Drop Down with Loacator"+sObjectName+"value"+sValue);
					act="True";
				}
				else
				{
					act="False";
				}
			}
			else if (sAction.equalsIgnoreCase("selectAndWait"))
			{
				if(sValue.contains("="))
				{
					sValue=sValue.split("=")[1];
					if (sValue.indexOf("get")>=0)
					{
						sValue=sValue.replaceAll("get-"+sValue.split("-")[1], hm.get(sValue.split("-")[1]));
					}
				}

				else if(sValue.indexOf("readProperty-")==0)
				{
					sValue=Locators.getLocators(sValue.split("-")[1]);
					//sValue="label="+sValue;
					sValue=sValue.trim();
				}
				typeOflocator = PerformActions.getLocator(sObjectName);
				if((sObjectName.contains("="))&&(sObjectName.contains("//"))==false)
				{
					sObjectName = sObjectName.split("=")[1];
				}
				if(PerformActions.selectAndWaitDropdown(driver,typeOflocator,sObjectName,sValue)==true)
				{
					act="True";
					SeleniumFW.APPLICATION_LOGS.info("Select Value from Drop Down with Loacator"+sObjectName+"value"+sValue);
				}
				else
				{
					act="False";
				}
			}
			else if (sAction.equalsIgnoreCase("randomSelect"))// pending
			{	
					
				    int count =0; 
					typeOflocator = PerformActions.getLocator(sObjectName);
					if((sObjectName.contains("="))&&(sObjectName.contains("//")==false) && (sObjectName.contains("css=")==false))
					{
						sObjectName = sObjectName.split("=")[1];
					}
					//String[] s=sel.getSelectOptions(sObjectName);
					count=PerformActions.getSelectedOptions(driver, typeOflocator, sObjectName);
					
					Random rand = new Random();
					if(count>0)
					{
						int StringIndex = rand.nextInt(count);
						sValue = PerformActions.radomSelect(driver,typeOflocator,sObjectName,StringIndex);
						System.out.println("Before Random Select  "+ sValue);
						if (StringIndex==0)
						{
							StringIndex = rand.nextInt(count);
							sValue = PerformActions.radomSelect(driver,typeOflocator,sObjectName,StringIndex);
							System.out.println("After Random Select  "+ sValue);
						}
					}
					if(sValue.equalsIgnoreCase("")==false)
					{act="True";}
					else
					{act="False";}
					
			}
			else if (sAction.equalsIgnoreCase("storevalueFromPropertyFile"))
			{

				String appVal = Locators.getLocators(sObjectName);
				System.out.println("Locator value ---->"+appVal);
				appVal=appVal.trim();
				hm.put(sValue, appVal);
				SeleniumFW.APPLICATION_LOGS.info("Store Value From Propertity File"+appVal);
				//String appVal = sel.getValue(sObjectName).trim();
				keyVal=sValue;
			}

			else if(sAction.equalsIgnoreCase("clickAlert"))
			{
				
				Alert alert = driver.switchTo().alert();
				Thread.sleep(1000);
				System.out.println("Alert text is --> " +alert.getText());
				if(alert.getText()!=""){
					alert.accept();
					act="True";
					SeleniumFW.APPLICATION_LOGS.info("Clicking Aleret"+alert.getText());
				}
				else{
					act="False";
				}
				/*boolean a=sel.isConfirmationPresent();
				System.out.println("COnfirmation " +a);
				if(a==true){
					//sel.getConfirmation();
					//	sel.keyDownNative(java.awt.event.KeyEvent.VK_RIGHT + "");
					//sel.keyUpNative(java.awt.event.KeyEvent.VK_RIGHT + "");
					//Thread.sleep(5000);
					sel.keyDownNative(java.awt.event.KeyEvent.VK_ENTER + "");
					sel.keyUpNative(java.awt.event.KeyEvent.VK_ENTER + "");

					act="True";
				}
				else
				{
					act="False";

				}

*/			}
			
			/*else if(sAction.equalsIgnoreCase("uncheck"))
			{
				sel.uncheck(sObjectName);		

			}*/
			else if (sAction.equalsIgnoreCase("storeNumber"))
			{
				sValue=DataGenerator.randNum;
				hm.put(sObjectName, sValue);
			}
			else if (sAction.equalsIgnoreCase("deleteAllVisibleCookies"))
			{
				
				driver.manage().deleteAllCookies();
				act="True";
			}
			else if (sAction.equalsIgnoreCase("setTimeout"))
			{
				
				driver.manage().wait(10000);
				act="True";
			}

			else if(sAction.equalsIgnoreCase("chooseOkOnNextConfirmationAndWait"))
			{
				
				Alert alert = driver.switchTo().alert();
				Thread.sleep(1000);
				System.out.println("Alert text is --> " +alert.getText());
				if(alert.getText()!=""){
					alert.accept();
					act="True";
				}
				else{
					act="False";
				}
			}
			else if (sAction.equalsIgnoreCase("storeBalance"))
			{
				//Amount_After_Charge=get-TransfereePrevbal:subtraction:get-TRANSFEREE_CHARGE
				SeleniumFW.APPLICATION_LOGS.info("storeBalance ::"+sObjectRefName);
				String temp="",sValue1="",sValue2="";
				if(sValue.indexOf("subtraction")>=0)
				{
					sValue1= sValue.split("=")[0];
					temp = sValue.split("=")[1];//get-TransfereePrevbal:subtraction:get-TRANSFEREE_CHARGE
					double transferAmt=0.00;
					double balance=0.00;
					System.out.println(" in sub function");
					String str = temp.split("-")[1];
					System.out.println("spilt first--->"+str);
					String str1=str.split(":")[0];
					//str1=str1.replace(",","");
					System.out.println("spilt value second--->"+str1);//TransfereePrevbal
					System.out.println("temp "+temp);//get-PrepaidBalance:subtraction:transferor_charge
					System.out.println("spit ----" + temp.split("-")[2]);
					String traAmt = hm.get(temp.split("-")[2]);
					System.out.println("Trance amount--->"+traAmt);
					String s=hm.get(str1);
					s=s.replace(",","");
					balance = Double.parseDouble(s);
					transferAmt = Double.parseDouble(traAmt);
					balanceAmount = (balance - transferAmt);
					String balanceAmt = Double.toString(balanceAmount);
					sValue=sValue1;
					balanceAmt=balanceAmt.trim();
					hm.put(sValue,balanceAmt);
					//System.out.println("Balance strored in subtraction--->" +balanceAmt );
					keyVal=balanceAmt;
					//System.out.println("sValue strored in subtraction--->" +sValue );
					SeleniumFW.APPLICATION_LOGS.info("Store balance In "+ sValue);
					SeleniumFW.APPLICATION_LOGS.info("Store balance value "+ balanceAmt);
				}
				if(sValue.indexOf("Addition")>=0)
				{
					sValue1= sValue.split("=")[0];
					temp = sValue.split("=")[1];//get-TransfereePrevbal:subtraction:get-TRANSFEREE_CHARGE
					double transferAmt=0.00;
					double balance=0.00;
					System.out.println(" in sub function");
					String str = temp.split("-")[1];
					System.out.println("spilt first--->"+str);
					String str1=str.split(":")[0];
					System.out.println("spilt value second--->"+str1);//TransfereePrevbal
					System.out.println("temp "+temp);//get-PrepaidBalance:subtraction:transferor_charge
					System.out.println("spit ----" + temp.split("-")[2]);
					String traAmt = hm.get(temp.split("-")[2]);
					System.out.println("Trance amount--->"+traAmt);
					balance = Double.parseDouble(hm.get(str1));
					transferAmt = Double.parseDouble(traAmt);
					balanceAmount = (balance + transferAmt);
					String balanceAmt = Double.toString(balanceAmount);
					balanceAmt=balanceAmt.trim();
					sValue=sValue1;
					hm.put(sValue,balanceAmt);
					//System.out.println("Balance strored in addition--->" +balanceAmt );
					keyVal=balanceAmt;
					//System.out.println("Balance strored addition-->" +balanceAmt );
					SeleniumFW.APPLICATION_LOGS.info("Store balance In "+ sValue);
					SeleniumFW.APPLICATION_LOGS.info("Store balance value "+ balanceAmt);
				}
				if(sValue.indexOf("get-")>=0)
				{
					//balance=get-oldPostPaidAccountBalance
					double balance=0.00;
					sValue2= sValue.split("=")[0];
					sValue1= sValue.split("-")[1];
					balance = Double.parseDouble(hm.get(sValue1));
					String balanceAmt = Double.toString(balance);
					balanceAmt=balanceAmt.trim();
					sValue=sValue2;
					hm.put(sValue,balanceAmt);
					keyVal=balanceAmt;
					//System.out.println("balanceAmt------------>" +balanceAmt);
					SeleniumFW.APPLICATION_LOGS.info("Store balance In "+ sValue);
					SeleniumFW.APPLICATION_LOGS.info("Store balance value "+ balanceAmt);
				}
			}

			//clickOnly keyword added by for calender clicks with locators
			else if(sAction.equalsIgnoreCase("clickOnly"))
			{
				typeOflocator=PerformActions.getLocator(sObjectName);
				if((sObjectName.contains("="))&&((sObjectName.contains("//"))==false) && (sObjectName.contains("css=")==false))
				{
					sObjectName = sObjectName.split("=")[1];
				}
				else if(sObjectName.contains("css="))
				{
					sObjectName = sObjectName.substring(4, sObjectName.length());
				}
				
				else if (sObjectName.indexOf("get")>=0)
				{
					sObjectName =hm.get(sObjectName.split("-")[1]);
					String s;
					s="link="+sObjectName;
					sObjectName=s;
				}
				if(PerformActions.click(driver,typeOflocator,sObjectName,sValue)==true)
				{
					act="True";
				}else{
					act="False";
				}
			}
			else if (sAction.equalsIgnoreCase("addTableValues"))
			{
				total=0;
				int row=Integer.parseInt(sObjectName.split(":")[1]);
				col=Integer.parseInt(sObjectName.split(":")[2]);
				sObjectName=sObjectName.split(":")[0];
				sObjectName=sObjectName+"/tbody/tr";
				//int xPathCount=(Integer) sel.getXpathCount(sObjectName);
				int xPathCount=driver.findElements(By.xpath(sObjectName)).size();
				System.out.println("Row Count step =  "+ xPathCount);			
				for(int iCounter=row; iCounter<xPathCount; iCounter++)
				{
					if (sel.isElementPresent(sObjectName+"["+iCounter+"]/td["+col+"]")==true)
					{
						//total=total+(Double.parseDouble(sel.getText(sObjectName+"["+iCounter+"]/td["+col+"]")));
						total=total+(Double.parseDouble(driver.findElement(By.xpath(sObjectName+"["+iCounter+"]/td["+col+"]")).getText()));
					}
				}
				System.out.println("Actual Table Value = " + total);
				hm.put(sValue, (Double.toString(total)+"0"));
				act="True";
			}
			else if (sAction.equalsIgnoreCase("StoreValueFromWebTableBasedOnLables"))
			{
				//LabeleName:StoredVariable
				act="False";
				String rootLocator,labelName,ValueToStore,TablerowCountVal,TablecoloumCount,TablecoloumCountget;
				labelName=sValue.split(":")[0];
				ValueToStore=sValue.split(":")[1];
				rootLocator=sObjectName;
				
				WebElement elementtttt=driver.findElement(By.xpath(rootLocator));
				if(elementtttt.isDisplayed()==true){
					rootLocator=sObjectName;
					rootLocator=rootLocator+"/tr";
					int RowNum;
					//RowNum=(Integer) sel.getXpathCount(rootLocator);
					RowNum=driver.findElements(By.xpath(rootLocator)).size();
					for(int i=1;i<=RowNum;i++){
						TablecoloumCount=rootLocator+"["+i+"]/td";
						TablecoloumCountget=rootLocator+"["+(i+1)+"]/td";
						//TablerowCountVal=rootLocator+"["+i+"]/td";
						//int ColoumCount=(Integer) sel.getXpathCount(TablecoloumCount);
						int ColoumCount=driver.findElements(By.xpath(TablecoloumCount)).size();
						for(int j=1;j<=ColoumCount;j++){

							rootLocator=TablecoloumCount+"["+j+"]";
							String s;
							//s=sel.getText(rootLocator);
							s=driver.findElement(By.xpath(rootLocator)).getText();
							if(s.equalsIgnoreCase(labelName)){
								String ss;
								ss=TablecoloumCountget+"["+j+"]";
								//String appVal=sel.getText(ss);
								String appVal=driver.findElement(By.xpath(ss)).getText();
								//appVal=sel.getValue(ssss);
								appVal=appVal.trim();
								hm.put(ValueToStore, appVal);
								keyVal=appVal;
								act="True";
								break;
							}
						}
						if(act=="True"){
							act="True";
							break;
						}
						else{

							continue;
						}
					}
				}
			}
		}
		catch (Exception Ex)

		{
			//Alert alert1 = driver.switchTo().alert();
			String alert=null;
			act="False";
			Ex.printStackTrace();
			errorMsg= Ex.getMessage();
			SeleniumFW.APPLICATION_LOGS.info("getting Exception"+errorMsg);
			/*alert=alert1.getText();
			alert1.accept();
			*///alert=sel.getAlert();
			//sel.keyDownNative(java.awt.event.KeyEvent.VK_ENTER + "");
			//sel.keyUpNative(java.awt.event.KeyEvent.VK_ENTER + "");

			System.out.println(alert + "-------->>");
			act="False";
			//System.out.println("Error msg is : " +Ex.getMessage());
			if (alert!=null) 
			{
				String s1 = Configure.Recovery;
				Runtime r1 = Runtime.getRuntime();
				Process p=r1.exec(s1);
				//sel.selectWindow(null);
				System.out.println("Process Exit");
				//r1.exit(1);
				p.destroy();
			}
			if (driver.getPageSource().contains("Please try after sometime as Altamira is offline."))
			{	
				Recovery.callRecovery();
			}
			if (driver.getPageSource().contains("Subscriber doest not exist or Comverese inactive"))
			{	
				Recovery.callRecovery();
			}
		}
	}
	protected static void sleep(int secs)
	{
		try 
		{
			Thread.sleep(secs*1000);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static String OrderNumber(String tempstr)
	{
		StringBuffer strBuff = new StringBuffer();
		char c;
		for (int i = 0; i < tempstr.length() ; i++)
		{
			c = tempstr.charAt(i);
			if ((Character.isDigit(c)) || (c=='#'))
			{ 
				strBuff.append(c);}
		}  
		return strBuff.toString();
	}

}//Class Navigate
