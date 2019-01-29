package SeleniumTestAutomation;

import java.io.File;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.Selenium;

class StepValidate extends Navigate
{
	public static String StepActual = null;
	Boolean storeval,status;
	String Storekey = null;
	public static WebElement element;
	public static WebDriverWait wait; 
	static String[] val;
	public boolean exp(String sObjectName,String sAction,String sValue,Selenium sel)
	{
		try
		{
			if(sAction.equalsIgnoreCase("type") || sAction.equalsIgnoreCase("typeAndWait"))
			{
				sValue = Navigate.value;
				sValue=sValue.trim();
				if (sValue.indexOf("get")>=0)
				{
					String tempSelectedName=null;
					tempSelectedName=hm.get(sValue.split("-")[1]);
					String str="get-"+(sValue.split("-")[1]);
					sValue=sValue.replaceAll(str, tempSelectedName);
				}
				
				
				if ((sValue.indexOf("\r")>=0) && (sValue.indexOf("\n")>=0))
				{
					sValue=sValue.replaceAll("\r\n", "");
				}
				if (sObjectName.indexOf(":")>=0)
				{
					String tempStr=sObjectName.split(":")[1];
					String tempStr1=tempStr.split("-")[1];
					sObjectName=sObjectName.split(":")[0]+((hm.get(tempStr1)).toUpperCase());
				}
				StepActual = StepValidateImplimentations.getValueFromEditbox(driver, typeOflocator, sObjectName, sValue);
				//StepActual=sel.getValue(sObjectName);
				System.out.println("senthil value========" + StepActual);
				return StepActual.equalsIgnoreCase(sValue);
			}
			else if(sAction.equalsIgnoreCase("SearchDropDown"))
			{
				return true;
			}//ClickActionUsingMouse
			else if(sAction.equalsIgnoreCase("ClickActionUsingMouse"))
			{
				return true;
			}//ClickActionUsingMouse
			else if(sAction.equalsIgnoreCase("removeSelection"))
			{
				return true;
			}
			else if(sAction.equalsIgnoreCase("upLoadFiles"))
			{
				return new Boolean(act);
			}
			else if(sAction.equalsIgnoreCase("addSelection"))
			{
				return true;
			}
			else if(sAction.equalsIgnoreCase("clickPopup"))
			{
				return true;	
			}
			else if((sAction.equalsIgnoreCase("closeNewTab"))||(sAction.equalsIgnoreCase("WaitAndclick"))||(sAction.equalsIgnoreCase("openNewTabAndNavigateUrl"))||(sAction.equalsIgnoreCase("click"))||(sAction.equalsIgnoreCase("switchToWindow"))||(sAction.equalsIgnoreCase("isChecked"))||(sAction.equalsIgnoreCase("clickOnly"))||(sAction.equalsIgnoreCase("clickAndWait")) ||(sAction.equalsIgnoreCase("executeStoreProcedure"))||(sAction.equalsIgnoreCase("mouseOver"))||(sAction.equalsIgnoreCase("addTableValues"))||(sAction.equalsIgnoreCase("open"))||(sAction.equalsIgnoreCase("setTimeout"))||(sAction.equalsIgnoreCase("PostpaidBillConsultationUsingRrnExpectedValues"))||(sAction.equalsIgnoreCase("PostpaidBillConsultationExpectedValues"))||(sAction.equalsIgnoreCase("actionOnWebTable"))||(sAction.equalsIgnoreCase("fireEvent")))
			{//WaitAndclick
				//closeNewTab
				StepActual = Navigate.act;
				return new Boolean(StepActual);
			}
			else if(sAction.equalsIgnoreCase("chooseOkOnNextConfirmationAndWait")||sAction.equalsIgnoreCase("clickAlert"))
			{
				StepActual = Navigate.act;
				return new Boolean(StepActual);
			}
			else if(sAction.equalsIgnoreCase("storeUniqueEmail"))
			{
				Storekey = sObjectName;
				storeval = Navigate.hm.containsKey(Storekey);
				StepActual = new Boolean(storeval).toString();
				return storeval;
			}
			else if((sAction.equalsIgnoreCase("storeUniqueMobileEmail"))||(sAction.equalsIgnoreCase("storeRandomSecurityID")))
			{
				Storekey = sObjectName;
				storeval = Navigate.hm.containsKey(Storekey);
				StepActual = new Boolean(storeval).toString();
				return storeval;
			}
			else if(sAction.equalsIgnoreCase("StoreValueInWebTable"))
			{
				sValue=sValue.split(":")[3];
				storeval = Navigate.hm.containsKey(sValue);
				StepActual = hm.get(sValue);
				return storeval;
			}
			else if(sAction.equalsIgnoreCase("StoreValueFromWebTableBasedOnLables"))
			{
				sValue=sValue.split(":")[1];
				sValue=sValue.trim();
				storeval = Navigate.hm.containsKey(sValue);
				StepActual = hm.get(sValue);
				StepActual=StepActual.trim();
				return storeval;
				
			}//storeValueFromAtmWebSimulator
			else if(sAction.equalsIgnoreCase("storeSelectedValue")||sAction.equalsIgnoreCase("storeValueFromIvrRequest")||sAction.equalsIgnoreCase("storeValueFromWebSimulator")||sAction.equalsIgnoreCase("storeWindowId")||sAction.equalsIgnoreCase("storeRechargeAmount")||sAction.equalsIgnoreCase("storevalueinPropertyFile")||sAction.equalsIgnoreCase("storeComboSelectedItem")||(sAction.equalsIgnoreCase("storeText"))||(sAction.equalsIgnoreCase("storeValueFromAtmWebSimulator")) ||(sAction.equalsIgnoreCase("storeValueFromWebSimulator"))||(sAction.equalsIgnoreCase("storeFromSimulator")||(sAction.equalsIgnoreCase("storeFromUSSDSimulator"))||(sAction.equalsIgnoreCase("storeFromSimulatorAmount"))||(sAction.equalsIgnoreCase("storeFromBankSimulator"))||(sAction.equalsIgnoreCase("storevalueFromPropertyFile"))))
			{     //sAction.equalsIgnoreCase("storeSelectedValue")||sAction.equalsIgnoreCase("storeValueFromIvrRequest")||sAction.equalsIgnoreCase("storeValueFromWebSimulator")||sAction.equalsIgnoreCase("storeComboSelectedItem")||(sAction.equalsIgnoreCase("storevalueinPropertyFile"))||(sAction.equalsIgnoreCase("storeText")) ||(sAction.equalsIgnoreCase("storeFromSimulator")||(sAction.equalsIgnoreCase("storeFromUSSDSimulator"))||(sAction.equalsIgnoreCase("storeFromSimulatorAmount"))||(sAction.equalsIgnoreCase("storeFromBankSimulator")))
				storeval = Navigate.hm.containsKey(sValue);
				StepActual = hm.get(sValue);
				return storeval;
			}
			else if((sAction.equalsIgnoreCase("store"))||(sAction.equalsIgnoreCase("storeTitle"))||(sAction.equalsIgnoreCase("storeTextPresent"))||(sAction.equalsIgnoreCase("storeNumber")))
			{
				Storekey = sObjectName;
				storeval = Navigate.hm.containsKey(Storekey);
				StepActual = new Boolean(storeval).toString();
				return storeval;
			}
			else if(sAction.equalsIgnoreCase("storeDamagedCoupon"))
			{
				sValue=sValue.split("=")[0];
				storeval = Navigate.hm.containsKey(sValue);
				StepActual = hm.get(sValue);
				return storeval;
			}
			else if((sAction.equalsIgnoreCase("selectWindow")))
			{
				if(sObjectName!="")
				{
					return true;
				}
			}
			else if (sAction.equalsIgnoreCase("generatePins"))
			{
				File file = new File(Configure.pinFilePath);  
				return file.exists();
			}
			else if((sAction.equalsIgnoreCase("select"))||(sAction.equalsIgnoreCase("selectAndWait")))
			{
				if (sValue.indexOf("get")>=0)
				{
					String tempSelectedName=null;
					tempSelectedName=hm.get(sValue.split("-")[1]);
					String str="get-"+(sValue.split("-")[1]);
					if(sValue.indexOf("inCaps")>=0)
					{
						tempSelectedName=tempSelectedName.toUpperCase();
						str=str+"-"+(sValue.split("-")[2]);
					}
					sValue=sValue.replaceAll(str, tempSelectedName);
				}
				if (sValue.indexOf("prevoiusRandomProfileName")>=0)
				{
					sValue=sValue.replaceAll("prevoiusRandomProfileName", prevoiusRandomProfileName);
				}
				if(sValue.contains("=")==true)
				{
				val = sValue.split("=");
				sValue=val[1];
				}
				else
				{
					sValue=Locators.getLocators(sValue.split("-")[1]);			
				}
				StepActual = StepValidateImplimentations.getValueFromDropdown(driver, typeOflocator, sObjectName, sValue);
				System.out.println("StepActual in select--> "+ StepActual);
				System.out.println("sValue-- in select>" +sValue);
				return StepActual.equalsIgnoreCase(sValue);
			}
			else if(sAction.equalsIgnoreCase("randomSelect"))
			{	
				boolean status=false;
				String selectedVal= StepValidateImplimentations.getValueFromDropdown(driver, typeOflocator, sObjectName, sValue);
				if(selectedVal.equalsIgnoreCase("")==false)
				{
					StepActual="True";
					status=true;
				}
				else
				{
					StepActual="False";
					status=false;
				}
				
				return status;
			}
			/*else if(sAction.equalsIgnoreCase("check"))
			{
				status=sel.isChecked(sObjectName); 
				return status;
			}*/
			else if(sAction.equalsIgnoreCase("DBConnect"))
			{
				Storekey=sValue.split(":")[0];
				storeval = Navigate.hm.containsKey(Storekey);
				StepActual = new Boolean(storeval).toString();
				return storeval;
			}	
			else if(sAction.equalsIgnoreCase("DBConnectSyn"))
			{
				Storekey=sValue.split(":")[0];
				storeval = Navigate.hm.containsKey(Storekey);
				StepActual = new Boolean(storeval).toString();
				return storeval;
			}
			else if(sAction.equalsIgnoreCase("ExecuteQueryAndStoreValues"))
			{
				StepActual = Navigate.act;
				return new Boolean(StepActual);
			}
			else if(sAction.equalsIgnoreCase("ExecuteRejectedTransQueryAndStoreValues"))
			{
				StepActual = Navigate.act;
				return new Boolean(StepActual);
			}
			
			else if(sAction.equalsIgnoreCase("DeleteQuery"))
			{
				StepActual = Navigate.act;
				return new Boolean(StepActual);
			}
			else if(sAction.equalsIgnoreCase("ExecuteQuery"))
			{
				StepActual = Navigate.act;
				return new Boolean(StepActual);
			}
			/*else if(sAction.equalsIgnoreCase("uncheck"))
			{
				status=sel.isChecked(sObjectName); 
				if(status==true){return false;}
				else{return true;}
			}*/
			else if(sAction.equalsIgnoreCase("refreshAndWait"))
			{
				StepActual = Navigate.act;
				return new Boolean(StepActual);
			}
			else if(sAction.equalsIgnoreCase("deleteAllVisibleCookies"))
			{
				StepActual = Navigate.act;
				return new Boolean(StepActual);
			}
			/*else if(sAction.equalsIgnoreCase("typeDate"))
			{
				sValue = Navigate.value;
				StepActual=sel.getValue(sObjectName);
				return StepActual.equalsIgnoreCase(sValue);
			}*/
			else if(sAction.equalsIgnoreCase("storeAttributeFromWebServiceRequest"))
			{
				sValue=sValue.split(":")[1];
				storeval = Navigate.hm.containsKey(sValue);
				StepActual = hm.get(sValue);
				return storeval;
			}
			else if(sAction.equalsIgnoreCase("storeAttributeFromWebServiceResponse"))
			{
				sValue=sValue.split(":")[1];
				storeval = Navigate.hm.containsKey(sValue);
				StepActual = hm.get(sValue);
				return storeval;
			}
			else if(sAction.equalsIgnoreCase("storeBalance"))
			{
				sValue=sValue.split("=")[0];
				storeval = Navigate.hm.containsKey(sValue);
				StepActual = hm.get(sValue);
				return storeval;
			}
			return StepActual.equalsIgnoreCase(sValue);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}

	}//exp function

}//Class StepValidate
