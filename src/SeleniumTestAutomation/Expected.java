package SeleniumTestAutomation;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

//import ussd.DataDecoder;
//import ussd.USSDServerSender;






import saajSoap.SaaJSoap;
import BNI.BNIClient;
import SeleniumTestAutomation.ReadPDF;
import SeleniumTestAutomation.ReadExcel;
import atm.ATMClientSimulator;

import com.thoughtworks.selenium.Selenium;

class Expected extends Navigate
{
	public String str=null;
	public static String actual, hashMapValue,Labelvalue=null;
	public  boolean ptr,element, textpresent,textNotPresent;
	public String retResult=null;
	public static String[] tableValue=null;
	public static String tempSelected, valFromDB,keyVal;
	public static int rownum, colnum;
	public static double expBalance;
	public static String expValForResults=null,expVal1,Appactualval=null,gettingExpValue,ExpValueIntableSearch,actualTableData;
	private DataInputStream in = null;
	public boolean expected(String expAction, String expTarget, String expVal, String waitTime,DTO dto) throws InterruptedException
	{
		try
		{
			expTarget = expTarget.trim();
			//String String;
			if (expAction.equalsIgnoreCase("verifyTitle"))
			{	
				
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				actual = driver.getTitle();
				SeleniumFW.APPLICATION_LOGS.info("Verify Title"+"Expected value:"+expVal+"Actual Value"+actual);
				//System.out.println("verify Title actual---------" +actual);
				//System.out.println("verify Title Expected---------" +expVal);
				return actual.equalsIgnoreCase(expVal);
			
			}
			/*else if (expAction.equalsIgnoreCase("verifyValueinPDF"))
			{
				ReadPDF r=new ReadPDF();
				int elementPresent=r.searchPDF(expTarget, Navigate.filePath);
				if (elementPresent>0)
					return true;
				else
					return false;
			}
			else if (expAction.equalsIgnoreCase("verifyValueinExcel"))
			{
				ReadExcel r=new ReadExcel();
				int elementPresent=r.searchxls(expTarget, Navigate.filePath);
				if (elementPresent>0)
					return true;
				else
					return false;
			}
			else if(expAction.equalsIgnoreCase("verifyDynamicMessage"))
			{
				String value;
				String value1;
				if(expVal.indexOf("get")>=0)
				{
					
                    if(expVal.indexOf("PrepaidCardPurchase")>=0)
					{
						value=hm.get(expVal.split("-")[1]);
						value1=hm.get(expVal.split("-")[2]);
						expVal1="An Amount of (USD) "+value1+" has been credited to the Cellular Number 0911111111";
					}
                      if(expVal.indexOf("PrepaidCardAmount")>=0)
					{
                    	
						value=hm.get(expVal.split("-")[1]);
						value1=hm.get(expVal.split("-")[2]);
						expVal1="A Payment of (USD) "+value1+" has been charged";
						//A Payment of (CRC) 8.00 has been charged
						
					}
                      if(expVal.indexOf("PromotionAmount")>=0)
  					{
                      	
  						value=hm.get(expVal.split("-")[1]);
  						value1=hm.get(expVal.split("-")[2]);
  						expVal1="Promotion Amount (USD) :  "+value1+"     Applied Promotion";
  						
  							
  					}
                      
					if(expVal.indexOf("newPaymentsTransfer")>=0)
                              {
                                    
                                    expVal1="New payments are done successfully\n"+"Following are the Payment Details :-";
                              }
					actual=hm.get(expVal.split("-")[1]);
                   	System.out.println(actual);
  					 expVal=expVal1;
  					 expValForResults=expVal;


                }
				return actual.equalsIgnoreCase(expVal);
			}*/
			else if (expAction.equalsIgnoreCase("verifyBalance"))
			{	
				SeleniumFW.APPLICATION_LOGS.info("****** verifyBalance  ******");
				if (expVal.indexOf("randomProfileName")>=0)
				{
					expVal=expVal.replaceAll("randomProfileName", DataGenerator.randName);
				}	
				else if(expVal.indexOf("subtraction")>=0)
				{
					double transferAmt=0.00;
					double balance=0.00;
					System.out.println(" in sub function");
				String str = expVal.split("-")[1];
				System.out.println("spilt first--->"+str);
				//get-balAmt1(Sub)get-transAmt
				String str1=str.split(":")[0];
				System.out.println("spilt value second--->"+str1);
				actual = sel.getText(expTarget);
				actual=actual.replace(",","");
				//System.out.println("actual value"+actual);
				System.out.println("In subfunction getting actual value" + actual);
				String traAmt = hm.get(expVal.split("-")[2]).replace(",", "");
				System.out.println("Trance amount--->"+traAmt);
				balance = Double.parseDouble(hm.get(str1).replace(",", ""));
				transferAmt = Double.parseDouble(traAmt);
				int precisionInt = Integer.parseInt("2");
				BigDecimal bdAmount = new BigDecimal(balanceAmount);
                bdAmount = bdAmount.setScale(precisionInt, BigDecimal.ROUND_HALF_UP);
                String amountStr = bdAmount + "";
                System.out.println("balanceAmount------subtraction>" +amountStr);
				expBalance = (balance - transferAmt);
				/*DecimalFormat df = new DecimalFormat("#.##");
				expBalance = Double.valueOf(df.format(expBalance));*/
				System.out.println("exp balance is ---->"+expBalance);
				
				if(expBalance==Double.parseDouble(actual))
				{
					SeleniumFW.APPLICATION_LOGS.info("Expected value " +expBalance);
					SeleniumFW.APPLICATION_LOGS.info("Actual value " +actual);
					return true;
					}
				else
				{
					return false;
					}
				}
				else if(expVal.indexOf("Addition")>=0)
				{
					double transferAmt=0.00;
					double balance=0.00;
					System.out.println(" in sub function");
				String str = expVal.split("-")[1];
				//get-balAmt1(Sub)get-transAmt
				String str1=str.split(":")[0];
				actual = sel.getText(expTarget);
				actual=actual.replace(",","");
				String Appactualval=actual;
				String traAmt = hm.get(expVal.split("-")[2]).replace(",", "");
				balance = Double.parseDouble(hm.get(str1).replace(",", ""));
				transferAmt = Double.parseDouble(traAmt);
				expBalance = (balance + transferAmt);
				System.out.println("exp balance is ---->"+expBalance);
				if(expBalance==Double.parseDouble(actual))
				{
					SeleniumFW.APPLICATION_LOGS.info("Expected value " +expBalance);
				    SeleniumFW.APPLICATION_LOGS.info("Actual value " +actual);
					return true;}
				else
				{
					SeleniumFW.APPLICATION_LOGS.info("Expected value " +expBalance);
			        SeleniumFW.APPLICATION_LOGS.info("Actual value " +actual);
					return false;}
				}
				else if (expVal.indexOf("get")>=0)
				{
					String tempVal=null;
					String tempVal1=null;
					tempVal=expVal.split("-")[1];
					tempVal1=tempVal.split(",")[0];
					expVal=expVal.replaceAll("get-"+tempVal, hm.get(tempVal));
					
					Double sat1 = Double.parseDouble(expVal);// added double for balance
					System.out.println("TEXT-----> :"+sat1);
					expVal=sat1.toString();
				
					
					hashMapValue=expVal;
					expValForResults=expVal;
					System.out.println("expVal---- "+expVal);
				}
//				Thread.sleep(10000);
				Thread.sleep(7000);
				actual =driver.findElement(By.xpath(expTarget)).getText();
				Double sat = Double.parseDouble(actual);
				System.out.println("TEXT-----> :"+sat);
				actual=sat.toString();
				System.out.println("TEXT :"+actual);
				System.out.println("EXPVAL:"+expVal);
				SeleniumFW.APPLICATION_LOGS.info("Expected value " +expVal);
			    SeleniumFW.APPLICATION_LOGS.info("Actual value " +actual);
				return actual.equalsIgnoreCase(expVal);
			}
			else if ((expAction.equalsIgnoreCase("verifyValuesinDb")))
			{	
				// get-firstvalue:get-Secondvalue comparing Db value with Actual value
				SeleniumFW.APPLICATION_LOGS.info("****** verifyValuesinDb ******");
				String s1=expVal.split(":")[0];
				String s2=expVal.split(":")[1];
			    
					//sel.getEval("window.scrollTo(0,60)");

				if(s1.indexOf("readProperty-")==0)
				{
					s1=Locators.getLocators(s1.split("-")[1]);
					s1=s1.trim();
					System.out.println("sObjectName "+s1);
					//expVal=s1;
				}
				 if (s1.indexOf("get")==0)
				{
					
					s1=hm.get(s1.split("-")[1].trim());
					s1=s1.trim();
					//String tempVal=null;
					//String tempVal1=null;
					//tempVal=expVal.split("-")[1];
					//tempVal1=tempVal.split(":")[0];
					//s1=expVal.replaceAll("get-"+tempVal1, hm.get(tempVal1));
					System.out.println("value of getting in expValue first----->"+s1);
					
				}
				
				 if(s2.indexOf("readProperty-")==0)
				{
					s2=Locators.getLocators(s2.split("-")[1]);
					s2=s2.trim();
					System.out.println("sObjectName "+s1);
					//expVal=s1;
				}
				
				 if (s2.indexOf("get")==0)
					{
						s2=hm.get(s2.split("-")[1].trim());
						//s2=s2.trim();
						System.out.println("value of getting in expValue second----->"+s2);
								}

					
					System.out.println("actual value from application "+s2);
					
					if(s1.equalsIgnoreCase(s2))
					{
						//String s;
						String s = s1;
						actual=s;
						expVal1=s2;
						expVal=s2;
						SeleniumFW.APPLICATION_LOGS.info("Expected value " +expVal);
						SeleniumFW.APPLICATION_LOGS.info("Actual Value "+actual);
						return true;
					}
					else{
						String ss = s1;
						actual=ss;
						expVal=s2;
						expVal1=s2;
						SeleniumFW.APPLICATION_LOGS.info("Expected value " +expVal);
						SeleniumFW.APPLICATION_LOGS.info("Actual Value "+actual);
						return false;
					}
				
					
			}
			else if ((expAction.equalsIgnoreCase("verifyBalanceValues")))
			{	
				// get-firstvalue:get-Secondvalue 

				SeleniumFW.APPLICATION_LOGS.info(" ******* verifyBalanceValues ******* ");
				String s1=expVal.split(":")[0];
				String s2=expVal.split(":")[1];
			    double x =0.00;
				double y=0.00;
					//sel.getEval("window.scrollTo(0,60)");

					if (s1.indexOf("get")==0)
					{
						
						s1=hm.get(s1.split("-")[1].trim());
						
						//String tempVal=null;
						//String tempVal1=null;
						//tempVal=expVal.split("-")[1];
						//tempVal1=tempVal.split(":")[0];
						//s1=expVal.replaceAll("get-"+tempVal1, hm.get(tempVal1));
						System.out.println("value of getting in expValue first----->"+s1);
						if(s1.contains(",")){
							s1=s1.replace(",", "");
						}
						x=Double.parseDouble(s1);
						
						//x=Float.parseFloat(s1);
					}
					
					if (s2.indexOf("get")==0)
					{
						s2=hm.get(s2.split("-")[1].trim());
						
						//String tempVal=null;
						//String tempVal1=null;
						//tempVal=expVal.split("-")[1];
						//tempVal1=tempVal.split(":")[0];
						//s2=expVal.replaceAll("get-"+tempVal1, hm.get(tempVal1));
						System.out.println("value of getting in expValue first----->"+s2);
						//x=Double.parseDouble(expVal);

					}

					//actual  = sel.getText(expTarget).trim();
					//actual  = sel.getTable(expTarget);
					y=Double.parseDouble(s2);
					//y=Float.parseFloat(s2);
					System.out.println("actual value from application "+s2);
					if(x==y)
					{
						//String s;
						String s = Double.toString(y);
						String ss1 = Double.toString(x);
						expVal=ss1;
						expVal1=expVal;
						actual=s;
						SeleniumFW.APPLICATION_LOGS.info(" Expected value " +expVal);
						SeleniumFW.APPLICATION_LOGS.info(" Actual value " +actual);
						return true;
					}
					else{
						String ss = Double.toString(y);
						actual=ss;
						String ss1 = Double.toString(x);
						expVal=ss1;
						expVal1=expVal;
						SeleniumFW.APPLICATION_LOGS.info(" Expected value " +expVal);
						SeleniumFW.APPLICATION_LOGS.info(" Actual value " +actual);
						return false;
					}
				
					
			}
			else if (expAction.equalsIgnoreCase("verifyAttributeInWebServiceResponse"))
			{	
				String xPath=expVal.split(":")[0];
				
				expVal=expVal.split(":")[1];
				if (expVal.indexOf("get-")==0)
				{
					expVal=hm.get(expVal.split("-")[1]);
				}
				if (expVal.indexOf("CurrentDate")==0)
				{
					DataGenerator.generateDate(expVal);
					expVal=DataGenerator.date;
					System.out.println("today " + expVal);
				}
				SaaJSoap client = new SaaJSoap();
				actual=client.XmlPath(TestCase.soapRes, xPath);
				System.out.println("xPath---verifyAttributeInWebServiceResponse "+xPath);
				System.out.println("actual--verifyAttributeInWebServiceResponse "+actual);
				System.out.println("expected--verifyAttributeInWebServiceResponse "+expVal);
				expValForResults=expVal;
				return actual.equalsIgnoreCase(expVal);
				
				
			}
			
			/*else if (expAction.equalsIgnoreCase("verifyElementInWebTable"))
			{
				boolean flag=false;
				//readProperty-AH_SD11:3:CHECK:Null
				String keyVale,ExpType,cNum,ExpValueIntableSearch,rootLocator;
				keyVale=expVal.split(":")[0].trim();
				if(keyVale.indexOf("get")==0)
				{
					keyVale=hm.get(keyVale.split("-")[1]);
				}
				if(keyVale.indexOf("readProperty-")==0)
				{
					keyVale=Locators.getLocators(keyVale.split("-")[1]);
					keyVale=keyVale.trim();
					System.out.println("keyVale--> "+keyVale);
				}
				cNum=expVal.split(":")[1].trim();
				int rNum=Integer.parseInt(expVal.split(":")[1]);			
				ExpType=expVal.split(":")[2];

				ExpValueIntableSearch=expVal.split(":")[3];
				expVal1=ExpValueIntableSearch;

				if(ExpValueIntableSearch.indexOf("get")==0){

					expVal1=hm.get(ExpValueIntableSearch.split("-")[1]);
				}
				rootLocator=expTarget; //form/table/tbody/tr/td/table/tbody/tr[6]/td/table/tbody
				String NumberofRowsCount;
				String totalRowsCount=rootLocator+"/tr";

				NumberofRowsCount=rootLocator+"/tr["+cNum+"]/td";//getting the number of rows // coloumsssss
				System.out.println("LocatorFormation--->"+NumberofRowsCount);
				//int xPathCountRowtotal=(Integer) sel.getXpathCount(totalRowsCount);
				int xPathCountRowtotal=driver.findElements(By.xpath(totalRowsCount)).size();
				int SerachNum=Integer.parseInt(cNum);
				for( int j=SerachNum;j<=xPathCountRowtotal;j++){
					NumberofRowsCount=rootLocator+"/tr["+j+"]/td";
					//int xPathCountRow=(Integer) sel.getXpathCount(NumberofRowsCount);
					int xPathCountRow=driver.findElements(By.xpath(NumberofRowsCount)).size();
					System.out.println("ShanCount---->"+ xPathCountRow);
					for(int i=1;i<=xPathCountRow;i++)
					{
						int s=i;
						//System.out.println("Moving row Number---->"+s);
						String sss=NumberofRowsCount+"["+i+"]";
						System.out.println("Number of Coloums-->"+sss);
						//System.out.println("locatorrrrrrrrrr---->"+sss);
						String gettingKeyVal;
						//gettingKeyVal=sel.getText(NumberofRowsCount+"["+i+"]");
						gettingKeyVal=driver.findElement(By.xpath(NumberofRowsCount+"["+i+"]")).getText();
						System.out.println("gettin key value --->"+gettingKeyVal);
						System.out.println("gettin ExpType --->"+ExpType);
						if(gettingKeyVal.equalsIgnoreCase(keyVale)){
							int n;
							n=Integer.parseInt(cNum);
							if(ExpType.equalsIgnoreCase("CheckBoxNotExist"))
							{
								//boolean ss=sel.isElementPresent(NumberofRowsCount+"["+(i-1)+"]/input[@type='checkbox']");
								boolean ss=driver.findElement(By.xpath(NumberofRowsCount+"["+(i-1)+"]/input[@type='checkbox']")).isDisplayed();
								System.out.println("ss in CheckBoxNotExist----> +ss");
								if(ss==false){
									flag=true;
									//sel.check(NumberofRowsCount+"["+(i-1)+"]/input[@type='checkbox']");
									break;
								}
							}else if (ExpType.equalsIgnoreCase("CheckBoxExist"))
							{
								//boolean ss=sel.isElementPresent(NumberofRowsCount+"["+(i-1)+"]/input[@type='checkbox']");
								boolean ss=driver.findElement(By.xpath(NumberofRowsCount+"["+(i-1)+"]/input[@type='checkbox']")).isDisplayed();
								System.out.println("ss in CheckBoxExist----> +ss");
								if(ss==true){
									flag=true;
									//sel.check(NumberofRowsCount+"["+(i-1)+"]/input[@type='checkbox']");
									break;
								}

							}else if(ExpType.equalsIgnoreCase("IsPartialReversalCheckBoxExist"))
							{
								//boolean ss=sel.isElementPresent(NumberofRowsCount+"["+(i+20)+"]/input[@type='checkbox']");
								boolean ss=driver.findElement(By.xpath(NumberofRowsCount+"["+(i+20)+"]/input[@type='checkbox']")).isDisplayed();
								System.out.println("ss in CheckBoxExist---->" +ss);
								if(ss==true){
									flag=true;
									//sel.check(NumberofRowsCount+"["+(i-1)+"]/input[@type='checkbox']");
									break;
								}
								else {
									flag=false;
								}

							}
							else if(ExpType.equalsIgnoreCase("ReverseButtonInReversalTable"))
							{
								//String loc=NumberofRowsCount+"["+(i+26)+"]/a/img";
								//boolean ss=sel.isElementPresent(NumberofRowsCount+"["+(i+25)+"]/a/img");
								boolean ss=driver.findElement(By.xpath(NumberofRowsCount+"["+(i+25)+"]/a/img")).isDisplayed();
								System.out.println("ss in CheckBoxExist---->" +ss);
								if(ss==true)
								{
									flag=true;
									break;
								}

							}
						}	
						if (flag==true)
						{
							break;
						}//inner for loop
					}
					if (flag==true)
					{
						break;
					}//outer for loop
					else
					{
						continue;
					}
				}
				if (flag==true)
				{
					actual = new Boolean(flag).toString();					
					return true;
				}else
				{
					return false;
				}
			}*/
			else if (expAction.equalsIgnoreCase("verifyFromUSSDSimulator"))
			{
				String errCode=null;
				//int errVal=DataDecoder.res.indexOf("<"+expTarget+">");
				int errVal=USSDClient.ussdResponse.indexOf("<"+expTarget+">");
				//int errVal1=DataDecoder.res.indexOf("</"+expTarget+">");
				int errVal1=USSDClient.ussdResponse.indexOf("</"+expTarget+">");
				//errCode=DataDecoder.res.substring(errVal+(expTarget.length()+2), errVal1);
				errCode=USSDClient.ussdResponse.substring(errVal+(expTarget.length()+2), errVal1);
				System.out.println("Error Code from simulator : " + errCode);
				if(errCode.contains(",")){
					
					errCode=errCode.replace(",", "").trim();
					System.out.println("Getting Error Code-->"+errCode);
				}
				actual=errCode;
				if(expVal.indexOf("-")>=0)
				{
					String temp=expVal.split("-")[1];
					expVal=hm.get(temp);
					if(expVal.contains("."))
					{
					      expVal=expVal.substring(0, expVal.length()-1);
					}

				}
				if(expVal.contains(",")){
				expVal=expVal.replace(",","").trim();
				}
				expValForResults=expVal;
				return errCode.equals(expVal);
			}
			else if (expAction.equalsIgnoreCase("verifyFromSimulator"))
			{
				SeleniumFW.APPLICATION_LOGS.info("*** verify from Simualtor Value ***");
				
				String errCode=null;
				//Thread.sleep(15000);
				Thread.sleep(4000);
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
							 while((strLine = br.readLine()) != null)
							 {
								 if (strLine.contains("<field id="))
								{								
								 String target = strLine.split("\"")[1];
								 //System.out.println("tatrget "+target);
								 //System.out.println("in seconed while -- "+strLine);
						 if ((strLine.contains("<field id=")) && (target.equalsIgnoreCase(expTarget)))
						 {
							 if(expTarget.equalsIgnoreCase("0"))
							 {
							 int errVal=strLine.indexOf("value=");
							// System.out.println("errVal--------->"+errVal);
							// SeleniumFW.APPLICATION_LOGS.info("Simualtor Values"+errVal+7 +":"+ errVal+11);
							 errCode=strLine.substring(errVal+7, errVal+11);
							 }
							 else if(expTarget.equalsIgnoreCase("39"))
							 {
							 int errVal=strLine.indexOf("value=");
							// System.out.println("errVal--------->"+errVal);
							 errCode=strLine.substring(errVal+7, errVal+9);
							 //SeleniumFW.APPLICATION_LOGS.info("Simualtor Values"+errVal+7 +":"+ errVal+9);
							 }
							 else if(expTarget.equalsIgnoreCase("38"))
							 {
							 int errVal=strLine.indexOf("value=");
							// System.out.println("errVal--------->"+errVal);
							 int endPos=strLine.indexOf("/>");
							 errCode=strLine.substring(errVal+7, endPos-1);
							
							// errCode=strLine.substring(errVal+7, errVal+9);
							 //System.out.println("Field 38-->"+errCode);
							 //SeleniumFW.APPLICATION_LOGS.info("Simualtor Values"+errVal+7 +":"+ errCode);
							 }
							 
							 else if(expTarget.equalsIgnoreCase("44"))
							 {
							 
							 int errVal=strLine.indexOf("value=");
							 int endPos=strLine.indexOf("/>");
							 errCode=strLine.substring(errVal+7, endPos-1);
							 
							 //errCode=strLine.substring(errVal+7, errVal+9);
							 //System.out.println("Field 44-->"+errCode);
							// SeleniumFW.APPLICATION_LOGS.info("Simualtor Values"+errVal+7 +":"+ errCode);
							 }
							 
							 else if(expTarget.equalsIgnoreCase("45"))
							 {
								 int errVal=strLine.indexOf("value=");
								 int endPos=strLine.indexOf("/>");
								 errCode=strLine.substring(errVal+7, endPos-1);
								 
							 //System.out.println("Field 45-->"+errCode);
							// SeleniumFW.APPLICATION_LOGS.info("Simualtor Values"+errVal+7 +":"+ errCode);
							 }
							 else if(expTarget.equalsIgnoreCase("4"))
							 {
								 int errVal=strLine.indexOf("value=");
								 int endPos=strLine.indexOf("/>");
								 errCode=strLine.substring(errVal+7, endPos-1);
								 
							// System.out.println("Field 4-->"+errCode);
							// SeleniumFW.APPLICATION_LOGS.info("Simualtor Values"+errVal+7 +":"+ errCode);
							 }
							 
							 else if(expTarget.equalsIgnoreCase("60"))
							 {
								 int errVal=strLine.indexOf("value=");
								 int endPos=strLine.indexOf("/>");
								 errCode=strLine.substring(errVal+7, endPos-1);
							// SeleniumFW.APPLICATION_LOGS.info("Simualtor Values 60"+errVal+7 +":"+ errCode);
							 }
							 else if(expTarget.equalsIgnoreCase("126"))
							 {
							// int errVal=strLine.indexOf("value=");
							// System.out.println("errVal--------->"+errVal);
								 int errVal=strLine.indexOf("value=");
								 int endPos=strLine.indexOf("/>");
								 errCode=strLine.substring(errVal+7, endPos-1);
								 //errCode=strLine.substring(errVal+7, errVal+9);
							// System.out.println("Field 38-->"+errCode);
							// SeleniumFW.APPLICATION_LOGS.info("Simualtor Values"+errVal+7 +":"+ errVal+9);
							 }
							 //System.out.println("Error Code from simulator : " + errCode);
							 //SeleniumFW.APPLICATION_LOGS.info("Error Code from simulator : " + errCode);
							 actual=errCode;
							 break;
						 } //end if
					    } // outer if  inner while
					   }//inner while
							 break;
				   }//outer if
					
				}//end outer while 
					// in.close();
				}
				catch (Exception ex)
				{
					//System.err.println("Response file not found");
					SeleniumFW.APPLICATION_LOGS.error("getting Exception"+ex);
				}
				finally{
					if(in != null)
					{
						 in.close();						
					}
				}
				errCode=errCode.trim();
				expVal=expVal.trim();
				if (expVal.indexOf("get")==0)
				{	
					expVal=hm.get(expVal.split("-")[1].trim());
					expVal=expVal.trim();
					System.out.println("Expected Value from Simulator: "+expVal);
					
				}
				SeleniumFW.APPLICATION_LOGS.error("Expected Value from Simulator: "+expVal);
				SeleniumFW.APPLICATION_LOGS.error("Actual Value from Simulator: "+errCode);
				return errCode.equals(expVal);
			}
			/*else if(expAction.equalsIgnoreCase("verifyFromBankSimulator"))
			{
				String errCode=null;
				//Thread.sleep(15000);
				Thread.sleep(1000);
				try
				{
					 FileInputStream fstream = new FileInputStream(TestCase.outLogFile);
					 in = new DataInputStream(fstream);
					 BufferedReader br = new BufferedReader(new InputStreamReader(in));
					 String strLine;
					 while ((strLine = br.readLine()) != null)
					 {
						 System.out.println("in first while -- "+strLine);

						 if(strLine.substring(0,3).equals("110")){
							 if(expTarget.equalsIgnoreCase("BankErrorCode"))
							 {
								 errCode = strLine.substring(63,65);
								 System.out.println("Response Code ==>"+strLine.substring(63,65)+"<==");
								 actual=errCode;
							 }
						 }
						  if(strLine.substring(0,3).equals("230")){
							 if(expTarget.equalsIgnoreCase("BankErrorCode"))
							 {
							 errCode = strLine.substring(120,122);
							 System.out.println("Response Code ==>"+strLine.substring(120,122)+"<==");
							 actual=errCode;
							 }
						 }
				}//end outer while 
				
				}
				catch (Exception ex)
				{
					System.err.println("Response file not found");
					
				}
				finally{
					if(in != null)
					{
						 in.close();						
					}
				}
				return errCode.equals(expVal);
			
			}*/
			else if (expAction.equalsIgnoreCase("verifyElementPresent"))
			{
				
				typeOflocator=PerformActions.getLocator(expTarget);
				if((expTarget.contains("="))&&((expTarget.contains("//"))==false) && (expTarget.contains("css=")==false))
				{
					expTarget = expTarget.split("=")[1];
				}
				else if(expTarget.contains("css="))
				{
					expTarget = expTarget.substring(4, expTarget.length());
				}
				ptr=ExpectedValidations.verifyElementPresent(driver,typeOflocator,expTarget,expVal);
				if(ptr==true)
				{
					
					actual = new Boolean(ptr).toString();
				}
				
					//actual =new Boolean("false").toString();
				
				     
				//ptr = sel.isElementPresent(expTarget);
				//actual = new Boolean(ptr).toString();
				return ptr;
			}
			/*else if (expAction.equalsIgnoreCase("verifySelectedValue"))
			{
				actual = sel.getSelectedLabel(expTarget);
				tempSelected=expVal;
				return actual.equalsIgnoreCase(expVal);				
			}*/
		/*	else if (expAction.equalsIgnoreCase("verifyTextNotExist"))
			{					
				actual  = sel.getText(expTarget);
				System.out.println("TEXT :"+actual);
				boolean res=actual.equalsIgnoreCase(expVal);
				if (res=true)
				{
					return false;				
				}
				else
				{
					return true;
				}
			}*/
			else if (expAction.equalsIgnoreCase("verifyText"))
			{	
				Appactualval=null;
				expValForResults=null;
				
				if (expVal.indexOf("randomProfileName")>=0)
				{
					expVal=expVal.replaceAll("randomProfileName", DataGenerator.randName);
				}	
				else if(expVal.indexOf("subtraction")>=0)
				{
					double transferAmt=0.00;
					double balance=0.00;
					System.out.println(" in sub function");
				String str = expVal.split("-")[1];
				System.out.println("spilt first--->"+str);
				//get-balAmt1(Sub)get-transAmt
				String str1=str.split(":")[0];
				System.out.println("spilt value second--->"+str1);
				
				//actual = sel.getText(expTarget);
				actual =driver.findElement(By.xpath(expTarget)).getText();
				actual=actual.replace(",","");
				Appactualval=actual;
				//System.out.println("actual value"+actual);
				System.out.println("In subfunction getting actual value-->" + actual);
				String traAmt = hm.get(expVal.split("-")[2]).replace(",", "");
				System.out.println("Trance amount--->"+traAmt);
				balance = Double.parseDouble(hm.get(str1).replace(",", ""));
				transferAmt = Double.parseDouble(traAmt);
				expBalance = (balance - transferAmt);
				
				System.out.println("exp balance is ---->"+expBalance);
			
				if(expBalance==Double.parseDouble(actual))
				{
					SeleniumFW.APPLICATION_LOGS.info("Verify Text:"+"Expected Value:"+expBalance+" Actual value"+actual);
					return true;
					
				}
				else
				{
					SeleniumFW.APPLICATION_LOGS.info("Verify Text:"+"Expected Value: "+expBalance+" Actual value: "+actual);
					return false;
					}
				}
				else if(expVal.indexOf("Addition")>=0)
				{
					double transferAmt=0.00;
					double balance=0.00;
					System.out.println(" in sub function");
				String str = expVal.split("-")[1];
				//get-balAmt1(Sub)get-transAmt
				String str1=str.split(":")[0];
				//actual = sel.getText(expTarget);
				actual =driver.findElement(By.xpath(expTarget)).getText();
				actual=actual.replace(",","");
				 Appactualval=actual;
				String traAmt = hm.get(expVal.split("-")[2]).replace(",", "");
				balance = Double.parseDouble(hm.get(str1).replace(",", ""));
				transferAmt = Double.parseDouble(traAmt);
				expBalance = (balance + transferAmt);
				System.out.println("exp balance is ---->"+expBalance);
				expVal=Double.toString(expBalance);
				if(expBalance==Double.parseDouble(actual))
				{
					SeleniumFW.APPLICATION_LOGS.info("Verify Text:"+"Expected Value:"+expBalance+" Actual value"+actual);
					return true;
					}
				else
				{
					SeleniumFW.APPLICATION_LOGS.info("Verify Text:"+"Expected Value:"+expBalance+" Actual value"+actual);
					return false;
					}
				}
				if (expVal.indexOf("fetch")==0)
				{
					String tempVal=null;
					String tempVal1=null;
					//actual  = sel.getText(expTarget);
					actual =driver.findElement(By.xpath(expTarget)).getText();
					double transferAmt = Double.parseDouble(actual);
					actual = Double.toString(transferAmt);
					tempVal=expVal.split("-")[1];
					tempVal1=tempVal.split(",")[0];
					expVal=expVal.replaceAll("fetch-"+tempVal1, hm.get(tempVal1));
					double transferAmt1 = Double.parseDouble(expVal);
					expVal=Double.toString(transferAmt1);
				if(actual.equalsIgnoreCase(expVal)){
					expValForResults=expVal;
					Appactualval=actual; //updated@30/10/2014
					SeleniumFW.APPLICATION_LOGS.info("Verify Text:"+"Expected Value:"+expVal+" Actual value"+actual);
					return true;
				}
				else{
					SeleniumFW.APPLICATION_LOGS.info("Verify Text:"+"Expected Value:"+expVal+" Actual value"+actual);
					expValForResults=expVal;
					Appactualval=actual; //updated@30/10/2014
					return false;
				}
				}
				
				else if (expVal.indexOf("get")>=0)
				{
					String tempVal=null;
					String tempVal1=null;
					tempVal=expVal.split("-")[1];
					tempVal1=tempVal.split(",")[0];
					
					expVal=expVal.replaceAll("get-"+tempVal, hm.get(tempVal));
					hashMapValue=expVal;
					expValForResults=expVal;
					System.out.println("expVal---- "+expVal);
				}
//				Thread.sleep(10000);
				Thread.sleep(4000);
				expValForResults=expVal;
				//sAppactualval=actual;
				actual =driver.findElement(By.xpath(expTarget)).getText();
				//actual  = sel.getText(expTarget);
				Appactualval=actual;
				
				System.out.println("TEXT :"+actual);
				System.out.println("EXPVAL:"+expVal);
				actual=actual.trim();
				expVal=expVal.trim();
				SeleniumFW.APPLICATION_LOGS.info("Verify Text:"+"Expected Value:"+expVal+" Actual value"+actual);
				return actual.equalsIgnoreCase(expVal);
				
			}	
			else if (expAction.equalsIgnoreCase("verifyTextEditable")) 
			{
				ptr = sel.isEditable(expTarget);
				actual = new Boolean(ptr).toString();
				return ptr;
			}
			else if (expAction.equalsIgnoreCase("verifyMaxLength"))
			{
				typeOflocator=PerformActions.getLocator(expTarget);
				actual =StepValidateImplimentations.getMaxlength(driver, typeOflocator, expTarget, expVal);
				System.out.println("Maxlength from the application "+ actual);
				return actual.equals(expVal);
			}
			else if (expAction.equalsIgnoreCase("verifyItemsinComboBox"))
			{	
				if(expVal.indexOf("get")==0)
	            {
	            	expVal=hm.get(expVal.split("-")[1]);
	            }
				typeOflocator=PerformActions.getLocator(expTarget);
				if((expTarget.contains("="))&&((expTarget.contains("//"))==false) && (expTarget.contains("css=")==false))
				{
					expTarget = expTarget.split("=")[1];
				}
				else if(expTarget.contains("css="))
				{
					expTarget = expTarget.substring(4, expTarget.length());
				}
				ptr=ExpectedValidations.verifyItemsinComboBox(driver,typeOflocator,expTarget,expVal);
				
				if(ptr==true)
				{
					actual = new Boolean(ptr).toString();
				}
				//int optionCount = sel.getXpathCount("//select[@name='"+expTarget+"']/option").intValue();
				/*int optionCount = sel.getXpathCount(expTarget+"/option").intValue();
				boolean actualres=false;
				checkcombo:for (int i = 1; i <= optionCount; i++) {
		            String option = sel.getText(expTarget+"/option["+i+"]");
		            if(expVal.indexOf("get")==0)
		            {
		            	expVal=hm.get(expVal.split("-")[1]);
		            }
		            if(expVal.equalsIgnoreCase("randomProfileName"))
		            {
		            	expVal=DataGenerator.randName;
		            }
		            if (option.equalsIgnoreCase(expVal))
		            {
		            	actualres=true;
		            	break checkcombo;
		            }
		        }	*/
		        return ptr;
			}
			else if (expAction.equalsIgnoreCase("verifyMailAlert"))
			{
				Thread.sleep(30000);
				readEmails readMail = new readEmails();
			    readMail.readmails(expTarget);
				actual=readEmails.mail;
				if(actual.indexOf("New Password")>=0)
				{
					String newPassword=(actual.split(":")[1]);
					System.out.println("senthil password   " + newPassword);
					hm.put("newPassword", newPassword);
				}
				if(expVal.indexOf("get-")>=0)
				{
					String tempExpVal1=null;
					String tempExpVal=expVal.split("get-")[1];
					tempExpVal1=tempExpVal;
					if(tempExpVal.indexOf("'")>=0)
					{
					tempExpVal1=tempExpVal.split("'")[0];
					}
					expVal=expVal.replaceAll("get-"+tempExpVal1, (hm.get(tempExpVal1).toUpperCase()));
				}
				if(actual.indexOf(expVal)>=0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else if (expAction.equalsIgnoreCase("verifyApplicationValues"))
			{
				actual=hm.get(expTarget.split("-")[1]);
				expVal=hm.get(expVal.split("-")[1]);
				if (actual.indexOf(expVal)>=0)
				{
					return true;
				}else
				{
					return false;
				}
			}
			else if (expAction.equalsIgnoreCase("verifyTableContent"))
			
			{
				try{
				boolean result=false;
				if(expVal.indexOf("get")==0)
				{
					expVal=hm.get(expVal.split("-")[1]);
				}
				int row=Integer.parseInt(expTarget.split(":")[1]);
				int col=Integer.parseInt(expTarget.split(":")[2]);
				expTarget=expTarget.split(":")[0];
				expTarget=expTarget+"/tbody/tr";
				
				//int xPathCount=(Integer) sel.getXpathCount(expTarget);		
				int xPathCount=driver.findElements(By.xpath(expTarget)).size();
				
				System.out.println("Row Count =  "+ xPathCount);			
				for(int iCounter=row; iCounter<xPathCount; iCounter++)
				{
					//driver.findElement(By.xpath(expTarget+"["+iCounter+"]/td["+col+"]")).isDisplayed();
					if (driver.findElement(By.xpath(expTarget+"["+iCounter+"]/td["+col+"]")).isDisplayed()==true)
					{
						//actual=sel.getText(expTarget+"["+iCounter+"]/td["+col+"]");	
						actual=driver.findElement(By.xpath(expTarget+"["+iCounter+"]/td["+col+"]")).getText();
						
						System.out.println("Actual Table Value = " + actual);
						if (actual.equals(expVal))
						{
							rownum=iCounter;
							colnum=col;
							result=true;
						}
					}
				}
				hashMapValue=expVal;
				
				return result;
				}catch (Exception e)
				{
					e.printStackTrace();
					SeleniumFW.APPLICATION_LOGS.error("Gettin Exception"+e);
				}
			}
			
			/*else if (expAction.equalsIgnoreCase("verifyTableSearch"))
				*//************************************************************************
				 * 
				 * 
				 * 		
				 *//*
			{

				//(keyValue:rowNumber:ExpValue)
				//(keyValue:colNumber:rowNumber:ExpValue)

				String keyVale,cNum,ExpValueIntableSearch,rootLocator,collnum;
				keyVale=expVal.split(":")[0].trim();
				if(keyVale.indexOf("get")==0){

					keyVale=hm.get(keyVale.split("-")[1]);
				}
				//System.out.println("key value isssssss--->"+keyVale);
				cNum=expVal.split(":")[2].trim();
				int rNum=Integer.parseInt(expVal.split(":")[1]);			
				ExpValueIntableSearch=expVal.split(":")[3];
				expVal1=ExpValueIntableSearch;
				if(ExpValueIntableSearch.indexOf("get")==0){

					expVal1=hm.get(ExpValueIntableSearch.split("-")[1]);
					//expVal1=ExpValueIntableSearch;
				}
				//System.out.println("expvalue is ----->"+ExpValueIntableSearch);
				rootLocator=expTarget; //form/table/tbody/tr/td/table/tbody/tr[6]/td/table/tbody
				String NumberofRowsCount;
				NumberofRowsCount=rootLocator+"/tr";//getting the number of rows
				//int xPathCountRow=(Integer) sel.getXpathCount(NumberofRowsCount);	
				int xPathCountRow=driver.findElements(By.xpath(NumberofRowsCount)).size();
				//System.out.println("Total Number Of Rows In Table---->"+ xPathCountRow);
				for(int i=rNum;i<=xPathCountRow;i++){
					int s=i;
					//System.out.println("Moving row Number---->"+s);
					String sss=NumberofRowsCount+"["+i+"]"+"/td"+"["+cNum+"]";
					//System.out.println("locatorrrrrrrrrr---->"+sss);
					String gettingKeyVal;
					//gettingKeyVal=sel.getText(NumberofRowsCount+"["+i+"]"+"/td"+"["+cNum+"]");
					gettingKeyVal=driver.findElement(By.xpath(NumberofRowsCount+"["+i+"]"+"/td"+"["+cNum+"]")).getText();
					System.out.println("gettin key value--->"+gettingKeyVal);
					if(gettingKeyVal.equalsIgnoreCase(keyVale)){
						//coloum locator count in particular row
						String NumberofcoloumsCount;
						NumberofcoloumsCount=NumberofRowsCount+"["+s+"]"+"/td";
						//System.out.println("Number of coloum count----->>>>"+NumberofcoloumsCount);
						//System.out.println("search coloum count locator-----"+NumberofcoloumsCount);
						
						//int xPathCountColoum=(Integer)sel.getXpathCount(NumberofcoloumsCount);
						int xPathCountColoum=driver.findElements(By.xpath(NumberofcoloumsCount)).size();
						
						System.out.println("search coloum count--->"+xPathCountColoum);
						for(int j=1;j<=xPathCountColoum;j++)
						{
							//String gettingExpValue=sel.getText(NumberofcoloumsCount+"["+j+"]");
							String gettingExpValue=driver.findElement(By.xpath(NumberofcoloumsCount+"["+j+"]")).getText();
							
							//System.out.println("getting text coloum in paticular------->>"+NumberofcoloumsCount+"["+j+"]");
							gettingExpValue.trim();
							System.out.println("getting EXp Val---->"+gettingExpValue);
							actual=gettingExpValue;
							if(actual.equalsIgnoreCase(expVal1)){

								return true;
							}
						}
						return false;
					}
				}
			}*/

		/*	else if (expAction.equalsIgnoreCase("verifyItemsNotInComboBox"))
			{	
				int optionCount = sel.getXpathCount("//select[@name='"+expTarget+"']/option").intValue();
				boolean actualres=true;
				for (int i = 1; i <= optionCount; i++) {
		            String option = sel.getText("//select[@name='"+expTarget+"']/option["+i+"]");
		            if(expVal.equalsIgnoreCase("randomProfileName"))
		            {
		            	expVal=DataGenerator.randName;
		            }
		            if (option.equalsIgnoreCase(expVal))
		            {
		            	actualres=false;
		            }
		        }	
				hashMapValue=expVal;
		        return actualres;
			}
			*/
				else if (expAction.equalsIgnoreCase("verifyItemsNotInComboBox"))
			{	
					if(expVal.indexOf("get")==0)
					{
						expVal=hm.get(expVal.split("-")[1]);
					}
					//expVal=expVal.split("=")[1];
					typeOflocator=PerformActions.getLocator(expTarget);
					if((expTarget.contains("="))&&((expTarget.contains("//"))==false) && (expTarget.contains("css=")==false))
					{
						expTarget = expTarget.split("=")[1];
					}
					else if(expTarget.contains("css="))
					{
						expTarget = expTarget.substring(4, expTarget.length());
					}
					ptr=ExpectedValidations.verifyItemsNotInComboBox(driver,typeOflocator,expTarget,expVal);
					
					if(ptr==true)
					{
						actual = new Boolean(ptr).toString();
					}
					//int optionCount = sel.getXpathCount("//select[@name='"+expTarget+"']/option").intValue();
					/*int optionCount = sel.getXpathCount(expTarget+"/option").intValue();
					boolean actualres=false;
					checkcombo:for (int i = 1; i <= optionCount; i++) {
			            String option = sel.getText(expTarget+"/option["+i+"]");
			            if(expVal.indexOf("get")==0)
			            {
			            	expVal=hm.get(expVal.split("-")[1]);
			            }
			            if(expVal.equalsIgnoreCase("randomProfileName"))
			            {
			            	expVal=DataGenerator.randName;
			            }
			            if (option.equalsIgnoreCase(expVal))
			            {
			            	actualres=true;
			            	break checkcombo;
			            }
			        }	*/
			        return ptr;
				}
			else if (expAction.equalsIgnoreCase("verifyValueinDB"))
			{
				
				actual=hm.get(expTarget.split("-")[1]);
				actual=actual.trim();
				System.out.println("Actual value in verifyValueinDB -----> " + actual);
				if(expVal.indexOf("get")>=0)
				{
					expVal=hm.get(expVal.split("-")[1]);
					expVal=expVal.trim();
					valFromDB=expVal;
				}
				valFromDB=expVal;
				System.out.println("EXP value in verify Value in DB" + expVal);
				if(expVal.contains(".0")){
					
					actual=actual+".0";
					actual=actual.trim();
					System.out.println("valuof actual is ---->"+actual);
				}
				
				SeleniumFW.APPLICATION_LOGS.info("actual--verifyValueinDB---> " +actual);
				SeleniumFW.APPLICATION_LOGS.info("expected--verifyValueinDB----> " +expVal);
				SeleniumFW.APPLICATION_LOGS.info("------------------------------------------------>");
				SeleniumFW.APPLICATION_LOGS.info("RESULT--COMPARING EXPECTED WITH ACTUAL----> " +actual.equalsIgnoreCase(expVal));
				SeleniumFW.APPLICATION_LOGS.info("------------------------------------------------>");
				return actual.equals(expVal.trim()); 
				
			}
			else if ((expAction.equalsIgnoreCase("verifyValue"))||(expAction.equalsIgnoreCase("waitForValue")))
			{	
				if (expVal.equalsIgnoreCase("null"))
				{
					expVal="";
				}
				if (expVal.indexOf("get")==0)
				{

					String tempVal=null;
					String tempVal1=null;
					tempVal=expVal.split("-")[1];
					tempVal1=tempVal.split(",")[0];
					expVal=expVal.replaceAll("get-"+tempVal1, hm.get(tempVal1));
				}
				actual = StepValidateImplimentations.getValueFromEditbox(driver, typeOflocator, expTarget, expVal);
				System.out.println("Actual in verifyValue" +actual);
				System.out.println("Expected in verifyValue" +expVal);
				SeleniumFW.APPLICATION_LOGS.info("verfyvalue"+"Exp Value"+expVal+"Act valu"+actual);
				return actual.equalsIgnoreCase(expVal);
			}
			else if (expAction.equalsIgnoreCase("verifyFromWebSimulator"))
			{
				Thread.sleep(4000);
				//String str = expVal.split("-")[1];
				System.out.println("EXPVALUE----->"+expVal);
				
				String n = expVal.split(":")[1];//:
				String n1 = expVal.split(":")[0];//:
				System.out.println("spiltvalue 0----->"+n);
				System.out.println("spiltvalue 0----->"+n1);
				//String[] n = null;
				if(n.indexOf("get-")==0)
				{
					expVal=hm.get(expVal.split("-")[1]);
					System.out.println("expvalue***&&-->"+expVal);
				}
				else
				{
					expVal=n.trim();
				}if(n.equalsIgnoreCase("TRANSDATE"))
				{
					DataGenerator.generateTransdate(expVal);
					expVal=DataGenerator.date;
					expVal=expVal.trim();
					System.out.println("TRANSDATE-->" + expVal);
				}
				if((IVRSimulator.response) != null){
				String appVal=IVRSimulator.response;//IVRSimulator.response
				System.out.println("valueis--------->>>>"+appVal);
			//String appVal = sel.getText(expTarget);
			//String var1[]=appVal.split("RESPONSE:");
			  System.out.println("spilt success");
			  
			  String[] par1=appVal.split(";");//;
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
			           if(key.equalsIgnoreCase(n1)){
			        	   
			        	   keyVal=(String)m.getValue();
			        	   keyVal=keyVal.trim();
			        	   actual=keyVal;
			        	   if(key.equalsIgnoreCase("TRANSDATE")){
			        		   keyVal=(String)m.getValue();
			        		   actual=keyVal.substring(0,8);
			        		   actual=actual.trim();
			        		   System.out.println("Trance Date--->"+actual);
			        		   //keyVal=keyVal.split(" ");
			        		   //String keyVal11=keyVal;   
			        	   }
			        	   if(key.equalsIgnoreCase("CHARGEAMT")){
			        		   double balance=0.00;
			        		   keyVal=(String)m.getValue();
			        		   //actual=keyVal.substring(0,8);
			        		   actual=keyVal.trim();
			        		   balance=Double.parseDouble(keyVal);
			        		   actual=Double.toString(balance);
			        		   System.out.println("Trance Date--->"+actual);
			        		   //keyVal=keyVal.split(" ");
			        		   //String keyVal11=keyVal;   
			        	   }
			        	   System.out.println("verifyFromWebSimulator ACTUAL VAL-->"+actual);
			        	   System.out.println("verifyFromWebSimulator EXP VAL-->"+expVal);
			        	   return actual.equalsIgnoreCase(expVal);	
			           }
			            //hm.put(sValue,keyVal);
			            }
			            
				  }
				  
			  } 
				}
				
				else if((BNIClient.BNIResponce) != null){

					String appVal=BNIClient.BNIResponce;//IVRSimulator.response
					System.out.println("valueis--------->>>>"+appVal);
				//String appVal = sel.getText(expTarget);
				//String var1[]=appVal.split("RESPONSE:");
				  System.out.println("spilt success");
				  
				  String[] par1=appVal.split(";");//;
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
				           if(key.equalsIgnoreCase(n1)){
				        	   
				        	   keyVal=(String)m.getValue();
				        	   keyVal=keyVal.trim();
				        	   actual=keyVal;
				        	   if(key.equalsIgnoreCase("TRANSDATE")){
				        		   keyVal=(String)m.getValue();
				        		   actual=keyVal.substring(0,8);
				        		   actual=actual.trim();
				        		   System.out.println("Trance Date--->"+actual);
				        		   //keyVal=keyVal.split(" ");
				        		   //String keyVal11=keyVal;   
				        	   }
				        	   if(key.equalsIgnoreCase("CHARGEAMT")){
				        		   double balance=0.00;
				        		   keyVal=(String)m.getValue();
				        		   //actual=keyVal.substring(0,8);
				        		   actual=keyVal.trim();
				        		   balance=Double.parseDouble(keyVal);
				        		   actual=Double.toString(balance);
				        		   System.out.println("Trance Date--->"+actual);
				        		   //keyVal=keyVal.split(" ");
				        		   //String keyVal11=keyVal;   
				        	   }
				        	   System.out.println("verifyFromWebSimulator ACTUAL VAL-->"+actual);
				        	   System.out.println("verifyFromWebSimulator EXP VAL-->"+expVal);
				        	   return actual.equalsIgnoreCase(expVal);	
				           }
				            //hm.put(sValue,keyVal);
				            }
				            
					  }
					  
				  } 
					
				}
				}
			else if (expAction.equalsIgnoreCase("verifyFromAtmWebSimulator"))
			{
				Thread.sleep(4000);
				//String str = expVal.split("-")[1];
				System.out.println("EXPVALUE----->"+expVal);
				
				String n = expVal.split("=")[1];//:
				String n1 = expVal.split("=")[0];//:
				System.out.println("spiltvalue 0----->"+n);
				System.out.println("spiltvalue 0----->"+n1);
				//String[] n = null;
				
				if(expVal.contains("=")==true){
					 n = expVal.split("=")[1];//:
				     n1 = expVal.split("=")[0];//:
				     System.out.println("spiltvalue 0----->"+n);
				     System.out.println("spiltvalue 0----->"+n1);
					
				}else{
					 n = expVal.split(":")[1];//:
					 n1 = expVal.split(":")[0];//:	
					System.out.println("spiltvalue 0----->"+n);
					System.out.println("spiltvalue 0----->"+n1);
				
				}
				
				if(n.indexOf("get-")==0)
				{
					expVal=hm.get(expVal.split("-")[1]);
					System.out.println("expvalue***&&-->"+expVal);
				}
				else
				{
					expVal=n.trim();
				}if(n.equalsIgnoreCase("TRANSDATE"))
				{
					DataGenerator.generateTransdate(expVal);
					expVal=DataGenerator.date;
					expVal=expVal.trim();
					System.out.println("TRANSDATE-->" + expVal);
				}
				if((ATMClientSimulator.Atmresponse) != null){
				String appVal=ATMClientSimulator.Atmresponse;//IVRSimulator.response
				System.out.println("valueis--------->>>>"+appVal);
			//String appVal = sel.getText(expTarget);
			//String var1[]=appVal.split("RESPONSE:");
			  System.out.println("spilt success");
			  
			  String[] par1=appVal.split("&");//;
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
			           if(key.equalsIgnoreCase(n1)){
			        	   
			        	   keyVal=(String)m.getValue();
			        	   keyVal=keyVal.trim();
			        	   actual=keyVal;
			        	   if(key.equalsIgnoreCase("TRANSDATE")){
			        		   keyVal=(String)m.getValue();
			        		   actual=keyVal.substring(0,8);
			        		   actual=actual.trim();
			        		   System.out.println("Trance Date--->"+actual);
			        		   //keyVal=keyVal.split(" ");
			        		   //String keyVal11=keyVal;   
			        	   }
			        	   if(key.equalsIgnoreCase("CHARGEAMT")){
			        		   double balance=0.00;
			        		   keyVal=(String)m.getValue();
			        		   //actual=keyVal.substring(0,8);
			        		   actual=keyVal.trim();
			        		   balance=Double.parseDouble(keyVal);
			        		   actual=Double.toString(balance);
			        		   System.out.println("Trance Date--->"+actual);
			        		   //keyVal=keyVal.split(" ");
			        		   //String keyVal11=keyVal;   
			        	   }
			        	   System.out.println("verifyFromWebSimulator ACTUAL VAL-->"+actual);
			        	   System.out.println("verifyFromWebSimulator EXP VAL-->"+expVal);
			        	   SeleniumFW.APPLICATION_LOGS.info("verifyFromWebSimulator ACTUAL VAL-->"+actual);
			        	   SeleniumFW.APPLICATION_LOGS.info("verifyFromWebSimulator EXP VAL-->"+expVal);
			        	   return actual.equalsIgnoreCase(expVal);	
			        	   
			        	   
			           }
			            //hm.put(sValue,keyVal);
			            }
			            
				  }
				  
			  } 
				}
				}
			else if ((expAction.equalsIgnoreCase("verifyTable"))||(expAction.equalsIgnoreCase("waitForTable")))
			{  
				//get-Typical_Recharge_SubscriberId,JTRM_CellularNumbe
				String rowNumColumnum="null";
				rowNumColumnum=expVal.split(",")[1].trim();
				expVal=expVal.split(",")[0];
				
				//getting row number and coloum Number
				
				String locatorcoloumRow = Locators.getLocators(rowNumColumnum);
				String row=locatorcoloumRow.split(",")[0].trim();
                String col=locatorcoloumRow.split(",")[1].trim();
                int rowNum=Integer.parseInt(row);
                int colNum=Integer.parseInt(col);
                
                String ss;
                
                //ss=driver.findElement(By.xpath(expTarget+"/tr["+rowNum+"]"+"/td/span")).getText();
                /*if(ss.equalsIgnoreCase("No data found")||ss.equalsIgnoreCase("No se encontró información")){
                	
                	actual=ss;
                	return false;*/
                	
               // }else{
                
				if (expVal.indexOf("get")==0)
                {
                      String tempVal=null;
                      String tempVal1=null;
                      tempVal=expVal.split("-")[1];
                      expVal=hm.get(tempVal).trim();
                //      System.out.println("expected in Get in verify Table"+ expVal);
                      //actual  = sel.getTable(expTarget);
                      //return actual.equalsIgnoreCase(expVal.trim());
                }
                if (expVal.indexOf("readProperty")==0)
                {
                      //readProperty-PM_Currecny,FT_currency
                      String tempVal=null;
                      //String tempVal1=null;
                      String tempVal2=null;
                      tempVal=expVal.split("-")[1].trim();
                      //tempVal1=tempVal.split(",")[0].trim();
                      tempVal2=Locators.getLocators(tempVal);
                      expVal=tempVal2.trim();
                      //System.out.println("exptarget value in GET verify text.....>"+expTarget);
                  //    System.out.println("expected in Get in verify Table"+ expVal);
                }
                if(expVal.indexOf("IvrRechargeAmount")>=0)//added new condition for portinRechargeAmount
																{
																	String tempVal3=expVal.split("-")[1];
																	expVal=hm.get(tempVal3);
																	expVal=expVal+".00";
																	System.out
																			.println("portinRechargeAmount-->"+expVal);
																	
																}
                
                 typeOflocator=PerformActions.getLocator(expTarget);
                 
                      if((expTarget.contains("="))&&((expTarget.contains("//"))==false) && (expTarget.contains("css=")==false))
                      {
                            expTarget = expTarget.split("=")[1];
                      }
                      else if(expTarget.contains("css="))
                      {
                            expTarget = expTarget.substring(4, expTarget.length());
                      }
                     
                      
                      try{
                    
                    	if((driver.findElement(By.xpath(expTarget+"/tbody/tr["+rowNum+"]"+"/td["+colNum+"]")).isDisplayed())!= false){
                          actual=ExpectedValidations.getTable(driver, typeOflocator, expTarget, expVal,rowNum,colNum);
                         System.out.println("Actual value:"+actual);
                         System.out.println("Exp value:"+expVal);
                         
                          Labelvalue=ExpectedValidations.getTableLabel(driver, typeOflocator, expTarget, expVal,rowNum,colNum);  
                      }
                      }catch(Exception e){
                    	  e.printStackTrace();
                    	  return false;
                      }
          if (expVal.equalsIgnoreCase("C_IN_TRANSACTION_ID"))
                                  {
                                String value=null;
                                System.out.println("length-->"+value);
                                System.out.println("getting Exp"+actual);
                                actual=actual.trim();
                               Boolean s=DataGenerator.RechargeSequenceNumber(actual);
                               if(s==true){
                                     expVal= actual;
                                     expVal1=expVal;
                                     expVal=expVal.trim();
                               }else{
                                     expVal="patternNotMatched";
                                     expVal1=expVal;
                               }
                                
                                  }
          
          if (expVal.indexOf("fetch")==0)
          {
                String tempVal=null;
                String tempVal1=null;
                double transferAmt = Double.parseDouble(actual);
                actual = Double.toString(transferAmt);
                tempVal=expVal.split("-")[1];
                tempVal1=tempVal.split(",")[0];
                expVal=expVal.replaceAll("fetch-"+tempVal1, hm.get(tempVal1));
                double ExpAmt = Double.parseDouble(expVal);
                expVal=Double.toString(ExpAmt);
                expVal1=expVal;

          }
          System.out.println("expexted Value in verifyTable--> "+expVal);
          expVal1=expVal;
          System.out.println(actual.replaceAll("\\s+", " "));
          expVal=expVal.replaceAll("\\s+", " ");
          actual=actual.replaceAll("\\s+", " ");
          System.out.println("*******--->"+actual);
          actual=actual.trim();
          SeleniumFW.APPLICATION_LOGS.info("----- Verify Table -----");
          SeleniumFW.APPLICATION_LOGS.info(Labelvalue+"-->"+" Value"+"Expected value: "+ expVal +""+" Actual value: "+ actual);
          SeleniumFW.APPLICATION_LOGS.info("----------------------------------------------------");
          
          
          return actual.equalsIgnoreCase(expVal.trim());
                //}

			}
			
				else if (expAction.equalsIgnoreCase("verifyAlert"))
			{	
				Thread.sleep(2000);
				if (expVal.indexOf("readProperty-")==0)
				{
					String tempVal=expVal;
					expVal=expVal.split("-")[1];
					expVal=Locators.getLocators(expVal);
					expVal=expVal.trim();
				}
				
				ExpectedCondition<Alert> ss = ExpectedConditions.alertIsPresent();//dout
				//driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				Alert alert = driver.switchTo().alert();
				//driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				Thread.sleep(5000);
				System.out.println("Alert text is --> " +alert.getText());
				//boolean a=sel.isAlertPresent();
				//System.out.println("Alert--->"+a);
				//if(ss.equals(true)){
					//actual=sel.getAlert();
					System.out.println("Alert lat is"+actual); 
					actual=alert.getText();
					alert.accept();
					//sel.keyDownNative(java.awt.event.KeyEvent.VK_ENTER + "");
					//sel.keyUpNative(java.awt.event.KeyEvent.VK_ENTER + "");
					//expVal="regexp:Post fund transfer exceeding fund limit for.*firstName";
					String Pattern111="\\W("+expVal+")";
					System.out.println("Seraching Pattern--->"+Pattern111);
					Pattern r = Pattern.compile(Pattern111);
					Matcher m = r.matcher(actual);
				      if (m.find( )) {
				       //  System.out.println("Found value: " + m.group(0) );
				        System.out.println("Found value: " + m.group(1) );
				        String s;
				        s=m.group(1);
				        System.out.println("---->"+s);
				        expVal1=s;
				        return s.equalsIgnoreCase(expVal);
				        
				         //System.out.println("Found value: " + m.group(2) );
				      } else {
				         System.out.println("NO MATCH");
				      }
				//}
				if(expVal.equalsIgnoreCase(actual)){
			    	return actual.equalsIgnoreCase(expVal);
			      }
				else
				{
					return false;
				}
				//return actual.equalsIgnoreCase(expTarget);
			}
		
		
			else if (expAction.equalsIgnoreCase("verifyConfirmation"))
			{	
				//actual  = sel.getConfirmation();
				SeleniumFW.APPLICATION_LOGS.info("Verify Conformation Box");
				Alert alert = driver.switchTo().alert();
				System.out.println("Confirmation is "+alert.getText());
				actual=alert.getText();
				
				if(actual!="")
				{
					alert.accept();
				}
				else
				{
					alert.dismiss();
				}
				SeleniumFW.APPLICATION_LOGS.info("Verify Conformation Box Actual"+actual+"Expected value"+expVal);
				return actual.equalsIgnoreCase(expVal);
			}
			else if (expAction.equalsIgnoreCase("verifyChecked"))
			{	
			typeOflocator=PerformActions.getLocator(expTarget);
			if((expTarget.contains("="))&&((expTarget.contains("//"))==false) && (expTarget.contains("css=")==false))
			{
				expTarget = expTarget.split("=")[1];
			}
			else if(expTarget.contains("css="))
			{
				expTarget = expTarget.substring(4, expTarget.length());
			}
			boolean check =PerformActions.isChecked(driver,typeOflocator,expTarget,expVal); 
			if(check==true)
			{
				actual = "true";
			}
				return actual.equalsIgnoreCase(expVal);
			}
			/*else if (expAction.equalsIgnoreCase("waitForText"))
			{	
				actual  = sel.getText(expTarget);
				return actual.equalsIgnoreCase(expTarget);
			}*/
			/*else if (expAction.equalsIgnoreCase("verifyTextPresent"))  
			{
				Thread.sleep(5000);
				if (expVal.indexOf("get")>=0)
				{
					if (expVal.indexOf("inCaps")>=0)
					{
						String temptext=expVal.split("-")[1];
						String temptext1=temptext.split(" ")[0];
						expTarget=expVal.replaceAll("get-"+temptext1, (hm.get(temptext1)).toUpperCase());
					}
					else
					{
						String temptext1=null;
						String temptext=expVal.split("-")[1];
						if (expVal.indexOf(":ADD:")>=0)
						{
							temptext1=temptext.split(":")[0];
							String val=expVal.split(":")[2];
							String val1=val.split(" ")[0];
							String tempVal=((hm.get(temptext1)).split("     ")[0]);
							double actualVal=(Double.parseDouble(tempVal))+(Integer.parseInt(val1));
							expVal=expVal.replaceAll("get-"+temptext1+":ADD:"+val1, ((Double.toString(actualVal))+"0"));
						}
						else
						{
							temptext1=temptext.split(" ")[0];
							expVal=expVal.replaceAll("get-"+temptext1, hm.get(temptext1));
						}
					}
				}
				if (expVal.indexOf("randomProfileName")>=0)
				{
					String temptext=expVal.replaceAll("randomProfileName", DataGenerator.randName);
					expVal=temptext;
				}
				if (expVal.indexOf("randomUserName")>=0)
				{
					String temptext=expVal.replaceAll("randomUserName", DataGenerator.randName);
					expVal=temptext;
				}
				if (expVal.indexOf("prevoiusRandomProfileName")>=0)
				{
					String temptext=expVal.replaceAll("prevoiusRandomProfileName", prevoiusRandomProfileName);
					expTarget=temptext;
				}
				if (expVal.indexOf("${")==0)
				{
					str = expVal.substring(expVal.indexOf("{")+1,expVal.indexOf("}"));
					mapkey = hm.containsKey(str);
					if (mapkey == true)
						expTarget = hm.get(str).toString();
				}
				//actual = new Boolean(sel.isTextPresent(expVal)).toString();
				typeOflocator=PerformActions.getLocator(expTarget);
				if((expTarget.contains("="))&&((expTarget.contains("//"))==false) && (expTarget.contains("css=")==false))
				{
					expTarget = expTarget.split("=")[1];
				}
				else if(expTarget.contains("css="))
				{
					expTarget = expTarget.substring(4, expTarget.length());
				}
				
				Thread.sleep(5000);
				boolean b = driver.getPageSource().contains(expVal);
				
				if(b==true)
				{
					
					return true;
					
				}
				else {
					return false;
				}
				
				//actual=ExpectedValidations.verifyTextPresent(driver,typeOflocator,expTarget,expVal);
				//System.out.println("actual value in VerifyTextPresent "+actual)
				//System.out.println("expexted value in VerifyTextPresent "+expVal);
				//return actual.equalsIgnoreCase(expVal);			
			}*/
			/*else if (expAction.equalsIgnoreCase("waitForTitle"))
			{	
				actual  = sel.getTitle();
				return actual.equalsIgnoreCase(expTarget);
			}*/
			else if (expAction.equalsIgnoreCase("waitForPopUp"))
			{	
				//String feedWinId = sel.getEval("{var windowId; for(var x in selenium.browserbot.openedWindows ) {windowId=x;} }");
				Thread.sleep(10000);
				try
				{	
				sel.waitForPopUp(expTarget, expVal);
				return true;
				}
				catch(Exception ex)
				{
					sel.selectWindow(expTarget);
					sel.windowFocus();
					sel.close();
					return false;
				}
			}
			else if ((expAction.equalsIgnoreCase("verifyElementNotPresent"))||(expAction.equalsIgnoreCase("waitForElementNotPresent")))
			{
				typeOflocator=PerformActions.getLocator(expTarget);
				if((expTarget.contains("="))&&((expTarget.contains("//"))==false) && (expTarget.contains("css=")==false))
				{
					expTarget = expTarget.split("=")[1];
				}
				else if(expTarget.contains("css="))
				{
					expTarget = expTarget.substring(4, expTarget.length());
				}
				ptr=ExpectedValidations.verifyElementNotPresent(driver,typeOflocator,expTarget,expVal);
				if(ptr==false)
				{
					ptr=true;
					actual = new Boolean(ptr).toString();
				}
				return ptr;
			}
			/*else if ((expAction.equalsIgnoreCase("verifyTextNotPresent"))||(expAction.equalsIgnoreCase("waitForTextNotPresent"))) 
			{
				if (expVal.indexOf("get")>=0)
				{
					if (expVal.indexOf("inCaps")>=0)
					{
						String temptext=expVal.split("-")[1];
						String temptext1=temptext.split(" ")[0];
						expVal=expVal.replaceAll("get-"+temptext1, (hm.get(temptext1)).toUpperCase());
					}
					else
					{
						String temptext=expVal.split("-")[1];
						String temptext1=temptext.split(" ")[0];
						expVal=expVal.replaceAll("get-"+temptext1, hm.get(temptext1));
					}
				}
				if (expVal.indexOf("randomProfileName")>=0)
				{
					String temptext=expVal.replaceAll("randomProfileName", DataGenerator.randName);
					expVal=temptext;
				}
				if (expVal.indexOf("randomUserName")>=0)
				{
					String temptext=expVal.replaceAll("randomUserName", DataGenerator.randName);
					expVal=temptext;
				}
				typeOflocator=PerformActions.getLocator(expTarget);
				if((expTarget.contains("="))&&((expTarget.contains("//"))==false) && (expTarget.contains("css=")==false))
				{
					expTarget = expTarget.split("=")[1];
				}
				else if(expTarget.contains("css="))
				{
					expTarget = expTarget.substring(4, expTarget.length());
				}
				actual=ExpectedValidations.verifyTextNotPresent(driver,typeOflocator,expTarget,expVal);
				
				if(actual.equalsIgnoreCase(expVal))
					return false;
				else
					return true;
				
				}*/
			else if (expAction.equalsIgnoreCase("waitForElementPresent"))
			{	
				int lcount=1;
				do
				{
					//sleep(5);
					sleep(2);
					ptr = sel.isElementPresent(expTarget);
					//lcount++;
					System.out.println("FeedWindid------->"+Navigate.feedWinId);
					System.out.print("ptr Value" +ptr);
					System.out.print("lcount Value" +lcount);
					
					if(Navigate.feedWinId!=null && ptr==false)
					{
						if(Navigate.feedWinId.equalsIgnoreCase("viewAssociatedChannelsPopUp"))
						{
							while(lcount>0){
							System.out.println("Inside Exception------------------->");
							sel.selectWindow(null);
							sel.click("//a[contains(@href, 'javascript:geoLocationsPop();')]");
							sleep(2);
							sel.selectWindow(Navigate.feedWinId);
							sel.windowFocus();
							if(sel.isElementPresent(expTarget)==true){
								actual = new Boolean(ptr).toString();
								return ptr;	
							}
							lcount++;
							}
						}
					}
				}while(ptr==false&&lcount<3);
				actual = new Boolean(ptr).toString();
				return ptr;
			}
			/*else if (expAction.equalsIgnoreCase("verifyErrorMessage"))
			{	
				Thread.sleep(20000);
				if (expVal.indexOf("readProperty-")==0)
				{
					String tempVal=expVal;
					expVal=expVal.split("-")[1];
					expVal=Locators.getLocators(expVal);
					expVal=expVal.trim();
				}
				if (expVal.indexOf("get-")==0)
				{
					expVal=hm.get(expVal.split("-")[1]);
					expVal=expVal.trim();
					System.out.println("expVal In Get---- "+expVal);
				}

				//actual=sel.getText(expTarget);
				actual=driver.findElement(By.xpath(expTarget)).getText();
				String Pattern111="\\W("+expVal+")";
				System.out.println("Seraching Pattern--->"+Pattern111);
				Pattern r = Pattern.compile(Pattern111);
				Matcher m = r.matcher(actual);
				if (m.find( )) {
					System.out.println("Found value: " + m.group(1) );
					String s;
					s=m.group(1);
					System.out.println("---->"+s);
					expVal1=s;
					SeleniumFW.APPLICATION_LOGS.info("Exp ErrorMsg:"+expVal+"Actual Value:"+s);
					return s.equalsIgnoreCase(expVal);
				} else {
					System.out.println("NO MATCH");
					SeleniumFW.APPLICATION_LOGS.info("failed");
					//SeleniumFW.APPLICATION_LOGS.info("Exp ErrorMsg:"+expVal+"Actual Value:"+s);
				}
				if(expVal.equalsIgnoreCase(actual)){
					return actual.equalsIgnoreCase(expVal);
				}
				else
				{
					return false;
				}
			}*/
			/*else if (expAction.equalsIgnoreCase("waitForTextPresent"))
			{	
				int lcount=0;
				do
				{
					sleep(5);
					ptr = sel.isTextPresent(expTarget);
					lcount++;
				}while(ptr==false&&lcount<6);
				actual = new Boolean(ptr).toString();
				return ptr;
			}
	*/
		/*	else if (expAction.equalsIgnoreCase("waitForSelectedLabel"))
			{	
				int lcount=0;
				do
				{
					sleep(5);
					ptr = (sel.getSelectedLabel(expTarget).equalsIgnoreCase(expVal));
					lcount++;
				}while(ptr==false&&lcount<6);
				actual = new Boolean(ptr).toString();
				return ptr;
				
			}*/
	
			/*else if (expAction.equalsIgnoreCase("verifySelectedLabel"))
			{	
				actual  = sel.getSelectedLabel(expTarget);
				System.out.println("sssssssssss: "+actual);
				return actual.equalsIgnoreCase(expVal);				
			}*/
			/*else if (expAction.equalsIgnoreCase("verifyAllCheckboxes"))
			{	
				int optionCount = sel.getXpathCount("//input[@type='checkbox']").intValue();
				boolean actualres=false;
			for (int i = 0; i <= optionCount-1; i++) {
		            //boolean check = sel.isChecked("name="+expTarget+i);
				boolean check = sel.isChecked(expTarget);
		          // boolean check = sel.isChecked(" /descendant-or-self::input[@type='checkbox'][" + i + "]");
		           
		            actual = new Boolean(check).toString(); 
		           // actualres=true;
		            if(actual.equalsIgnoreCase(expVal))
		            {
		            	actualres=true;
		            	//break checkcombo;
		            }
		            else
		            {
		            	actualres=false;
		            	break;
		            }
		        }	
		        return actualres;
			}*/
 //updated by Balaji VerifyTableDatatext
			else if (expAction.equals("VerifyTableDatatext")) {
				if (expVal.indexOf("get") == 0) {
					expVal1 = expVal.split(":")[1];
					System.out.println("The value is : " + expVal1);
					col = Integer.parseInt(expVal1);
					System.out.println("The column value is : " + col);
				}
				String finalexpTarget = expTarget + "/tr[*]/td[" + col + "]";
				//int optionCount = sel.getXpathCount(finalexpTarget).intValue();
				int optionCount=driver.findElements(By.xpath(finalexpTarget)).size();
				
				System.out.println("The total xpath are : " + optionCount);
				boolean result = false;
				if (expVal.indexOf("get") == 0) {
					// get-SubSalesPerson:1
					String tempVal = null;
					String tempVal1 = null;
					tempVal1 = expVal.split(":")[0];
					tempVal = tempVal1.split("-")[1];
					expVal = tempVal1.replaceAll("get-" + tempVal,
					hm.get(tempVal));
					System.out.println("expected in Get in verify Table"+expVal);
				}
				for (int row = 1; row <= optionCount; row++) {
					String valueofText = sel.getText("" + expTarget + "/tr["+ row + "]/td[" + col + "]");
					System.out.println("Actual Table Value = " + valueofText);
					System.out.println("The value is : " + expVal);
					actual = valueofText;
					if (actual.equalsIgnoreCase(expVal)) {
						System.out.println("The value in the table : " + expVal);
						result = true;
						// actual=expVal;
						break;
					} else {
						result = false;
					}
				}

				return result;

			}

			else 	
			{
				actual = driver.getTitle();
				return actual.equalsIgnoreCase(expTarget);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		return element;
	
		
	
}

}//Class Expected