package SeleniumTestAutomation;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;

class Result
{
	
	public static void XMLCreator() throws ParserConfigurationException, TransformerException
	{
		try
		{
		DocumentBuilderFactory dbfcreate = DocumentBuilderFactory.newInstance();
		DocumentBuilder docreate = dbfcreate.newDocumentBuilder();

		Calendar cal = new GregorianCalendar();
		String am_pm;
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	   
	    int hour = cal.get(Calendar.HOUR);
	    int minute = cal.get(Calendar.MINUTE);
	    int second = cal.get(Calendar.SECOND);
	    if(cal.get(Calendar.AM_PM) == 0)
	      am_pm = "AM";
	    else
	    
	      am_pm = "PM";
		
        Document createdocument = docreate.newDocument();
 
        // Creating the roots
        Element rootElement = createdocument.createElement("Results");
        createdocument.appendChild(rootElement);
        Attr versionattr = createdocument.createAttribute("BV");
        versionattr.setValue(Configure.buildversion);
        rootElement.setAttributeNode(versionattr);
        Attr timeattr = createdocument.createAttribute("Time");
        timeattr.setValue(hour + ":" + minute + ":" + second + " " + am_pm);
        rootElement.setAttributeNode(timeattr);
        Attr dateattr = createdocument.createAttribute("Date");
        dateattr.setValue( day + "/" + (month + 1) + "/" + year  );
        rootElement.setAttributeNode(dateattr);
        Attr tccountattr = createdocument.createAttribute("TotalTC");
        tccountattr.setValue(Integer.toString(TestCase.tcCount));
        rootElement.setAttributeNode(tccountattr);
        	                   
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(createdocument);
        StreamResult xmlresult =  new StreamResult(new File(Configure.resultlogpath));
        transformer.transform(source, xmlresult);
        System.out.println("Xml Created");
		}
		catch(ParserConfigurationException pce)
		{
			  pce.printStackTrace();
		
		}
		
	}
	
	public static void XMLWriter(String id, String NodeName, String ActualResult, String Content, String ExpectedResult, String Path, String Result, String Time) throws Exception
	{
		
		try
		{ 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(new File(Configure.resultlogpath));
		
		if (NodeName.equalsIgnoreCase("TC") )
		{
			Element rootElement = doc.getDocumentElement();
			Element TCNode = doc.createElement(NodeName);
			TCNode.setAttribute("ID", id);
			rootElement.appendChild(TCNode);			
		}
		else if (NodeName.equalsIgnoreCase("Step") )
		{
			
			int position = doc.getElementsByTagName("TC").getLength();
									
			Element StepNode = doc.createElement(NodeName);
			doc.getElementsByTagName("TC").item(position-1).appendChild(StepNode);
			Attr Contentattr = doc.createAttribute("Content");
			Contentattr.setValue(XMLResultsModifier.Content);
		    StepNode.setAttributeNode(Contentattr);
		    Attr Expectedattr = doc.createAttribute("ExpectedResult");
		    Expectedattr.setValue(XMLResultsModifier.ExpRes);
		    StepNode.setAttributeNode(Expectedattr);
		    Attr Actualattr = doc.createAttribute("ActualResult");
		    Actualattr.setValue(XMLResultsModifier.ActRes);
		    StepNode.setAttributeNode(Actualattr);
		    Attr Timeattr = doc.createAttribute("Time");
		    Timeattr.setValue(XMLResultsModifier.StepTime);
		    StepNode.setAttributeNode(Timeattr);
		    Attr Resattr = doc.createAttribute("StepResult");
		    Resattr.setValue(XMLResultsModifier.StepRes);
		    StepNode.setAttributeNode(Resattr);
		    Attr Pathattr = doc.createAttribute("Path");
		    Pathattr.setValue(XMLResultsModifier.StepPath);
		    StepNode.setAttributeNode(Pathattr);
		}
		
		else if (NodeName.equalsIgnoreCase("Expected") )
		{
			int Expposition = doc.getElementsByTagName("TC").getLength();
									
			Element ExpNode = doc.createElement(NodeName);
			doc.getElementsByTagName("TC").item(Expposition-1).appendChild(ExpNode);
			Attr ExpContentattr = doc.createAttribute("Content");
			ExpContentattr.setValue(Content);
			ExpNode.setAttributeNode(ExpContentattr);
		    Attr ExpExpectedattr = doc.createAttribute("ExpectedResult");
		    ExpExpectedattr.setValue(ExpectedResult);
		    ExpNode.setAttributeNode(ExpExpectedattr);
		    Attr ExpActualattr = doc.createAttribute("ActualResult");
		    ExpActualattr.setValue(ActualResult);
		    ExpNode.setAttributeNode(ExpActualattr);
		    Attr ExpTimeattr = doc.createAttribute("Time");
		    ExpTimeattr.setValue(Time);
		    ExpNode.setAttributeNode(ExpTimeattr);
		    Attr ExpResattr = doc.createAttribute("ExpResult");
		    ExpResattr.setValue(Result);
		    ExpNode.setAttributeNode(ExpResattr);
		    Attr ExpPathattr = doc.createAttribute("Path");
		    ExpPathattr.setValue(Path);
		    ExpNode.setAttributeNode(ExpPathattr);
		}
		
		else if ((NodeName.equalsIgnoreCase("ActualResult_Pass")) || (NodeName.equalsIgnoreCase("ActualResult_Fail"))) 
		{
			int resposition = doc.getElementsByTagName("TC").getLength();
			doc.getElementsByTagName("Header").item(resposition-1).getAttributes().getNamedItem("ActualResult").setNodeValue(ActualResult);
			doc.getElementsByTagName("Header").item(resposition-1).getAttributes().getNamedItem("Result").setNodeValue(Result);
			doc.getElementsByTagName("Header").item(resposition-1).getAttributes().getNamedItem("Content").setNodeValue(Content);
			doc.getElementsByTagName("Header").item(resposition-1).getAttributes().getNamedItem("TCTime").setNodeValue(Time);
			//doc.getElementsByTagName("Header").item(resposition-1).getAttributes().getNamedItem("ExpectedResult").setNodeValue(ExpectedResult);
		}
		else if (NodeName.equalsIgnoreCase("TC_Step_0")) 
		{
			int Headerposition = doc.getElementsByTagName("TC").getLength();

			Element HeaderNode = doc.createElement("Header");
			doc.getElementsByTagName("TC").item(Headerposition-1).appendChild(HeaderNode);
			Attr HeaderContentattr = doc.createAttribute("Content");
			HeaderContentattr.setValue(Content);
			HeaderNode.setAttributeNode(HeaderContentattr);
		    Attr HeaderExpectedattr = doc.createAttribute("ExpectedResult");
		    HeaderExpectedattr.setValue(ExpectedResult);
		    HeaderNode.setAttributeNode(HeaderExpectedattr);
		    Attr HeaderActualattr = doc.createAttribute("ActualResult");
		    HeaderActualattr.setValue(ActualResult);
		    HeaderNode.setAttributeNode(HeaderActualattr);
		    Attr HeaderTimeattr = doc.createAttribute("TCTime");
		    HeaderTimeattr.setValue(Time);
		    HeaderNode.setAttributeNode(HeaderTimeattr);
		    Attr HeaderResattr = doc.createAttribute("Result");
		    HeaderResattr.setValue(Result);
		    HeaderNode.setAttributeNode(HeaderResattr);
		}
			
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result =  new StreamResult(new File(Configure.resultlogpath));
	        transformer.transform(source, result);
		}
		catch(ParserConfigurationException pce)
		{
			  pce.printStackTrace();
		}
	}

}//Class Result

class XMLResultsModifier extends Expected
{
	public static String Content = null;
	public static String ExpRes = null;
	public static String ActRes = null;
	public static String StepTime = null;
	public static String StepPath = null;
	public static String StepRes = null;
	public static String StepNode = null;
	public static String FinalResult = null;
	static String id = null;
	public static void XmlModifier(String Action,String ObjRefName, String ObjectName, String NodeName, String ExpectedResult, String ActualResult, String FinalResult, String Time, String Path, String expprop) throws Exception
	{
		StepTime = Time;
		StepPath = Path;
		StepRes = FinalResult;
		StepNode = NodeName;
		if (NodeName.equalsIgnoreCase("Step"))
		{
			Content  = null;
			ExpRes = null;
			ActRes = null;
			if(FinalResult.equalsIgnoreCase("Pass"))
			{
				if(Action.equalsIgnoreCase("click") || (Action.equalsIgnoreCase("isChecked"))|| (Action.equalsIgnoreCase("clickOnly"))|| (Action.equalsIgnoreCase("WaitAndclick")) || (Action.equalsIgnoreCase("clickAndWait"))||(Action.equalsIgnoreCase("clickPopup")))
				{
					Content = "Click on '"+ObjRefName+"'";
					ExpRes = "Click action should be performed on '"+ObjRefName+"'";
					ActRes = "Click action is performed on '"+ObjRefName+"'";
				}if(Action.equalsIgnoreCase("closeNewTab"))
				{
					Content = "close tab'"+ObjRefName+"'";
					ExpRes = "close Tab Action Performed'"+ObjRefName+"'";
					ActRes = "close Tab Action Performed'"+ObjRefName+"'";
				}
				else if(Action.equalsIgnoreCase("open") || (Action.equalsIgnoreCase("openAndWait"))||(Action.equalsIgnoreCase("openNewTabAndNavigateUrl")))
				{
					Content = "Open"+"'"+Navigate.errorMsg+"'";
					ExpRes = "Open action should be performed on '"+ObjRefName+"'";
					ActRes = "Open action is performed on"+"'"+Navigate.errorMsg+"'";
				}
				else if (Action.equalsIgnoreCase("type") || Action.equalsIgnoreCase("ClickActionUsingMouse")|| Action.equalsIgnoreCase("SearchDropDown")|| Action.equalsIgnoreCase("typeAndWait")||Action.equalsIgnoreCase("typeDate"))
				{
					Content = "Enter "+ObjRefName+" textbox with value '"+ExpectedResult+"'";
					ExpRes = "'"+ExpectedResult+"' should be displayed in "+ObjRefName+" field";
					ActRes = "'"+ActualResult+"' is displayed in "+ObjRefName+" field";
				}
				else if(Action.equalsIgnoreCase("storeAttributeFromWebServiceRequest"))
				{
					
					Content = "The '"+expprop+ " is stored in  " +Navigate.keyVal;
					ExpRes = "The '"+expprop+"' value should be stored in " +Navigate.keyVal;
					ActRes = "The '"+expprop+"' value is stored in " +Navigate.keyVal;
				}
				else if(Action.equalsIgnoreCase("storeAttributeFromWebServiceResponse"))
				{
					
					Content = "The '"+expprop+ " is stored in  " +Navigate.keyVal;
					ExpRes = "The '"+expprop+"' value should be stored in " +Navigate.keyVal;
					ActRes = "The '"+expprop+"' value is stored in " +Navigate.keyVal;
				}
				else if((Action.equalsIgnoreCase("mouseOver"))||(Action.equalsIgnoreCase("mouseOverAndWait")))
				{
					Content = "MouseOver action performed on '"+ObjRefName+"' ";
					ExpRes = "MouseOver should be performed on '"+ObjRefName+"'";
					ActRes = "MouseOver is performed on '"+ObjRefName+"'";
				}
				else if ((Action.equalsIgnoreCase("select"))||(Action.equalsIgnoreCase("selectAndWait")))
				{
					Content = "Select "+ObjRefName+" with value '"+ExpectedResult+"'";
					ExpRes = "'"+ExpectedResult+"' should be displayed in "+ObjRefName+" listbox";
					ActRes = "'"+ActualResult+"' is displayed in "+ObjRefName+" listbox";
				}
				else if ((Action.equalsIgnoreCase("randomSelect")))
				{
					Content = "Select "+ObjRefName+" with value '"+PerformActions.randomValue+"'";
					ExpRes = "'"+PerformActions.randomValue+"' should be displayed in "+ObjRefName+" listbox";
					ActRes = "'"+PerformActions.randomValue+"' is displayed in "+ObjRefName+" listbox";
				}
				else if(Action.equalsIgnoreCase("storeUniqueEmail"))
				{
					Content = "Store the value in '"+ObjectName+"' ";
					ExpRes = "The value should be stored in '"+ObjectName+"'";
					ActRes = "The value is stored in '"+ObjectName+"'";
				}
				else if((Action.equalsIgnoreCase("DBConnect"))||(Action.equalsIgnoreCase("DBConnectSyn")))
				{
					Content = "Should Be Connect to'"+"'"+ObjectName+"'"+"database";
					ExpRes = ObjectName+"database connected successfully"+"And Get Value"+""+"'"+Expected.DbValue+"'";
					ActRes = ObjectName+"database connected successfully"+"And Get Value"+""+"'"+Expected.DbValue+"'";
				}
				else if((Action.equalsIgnoreCase("DeleteQuery")))
				{
					Content = "Executed in '"+ObjectName+"' database";
					ExpRes = "Query should be updated Successfully in "+ObjectName;
					ActRes = "Query is updated in "+ObjectName;
				}
				else if((Action.equalsIgnoreCase("ExecuteQuery")))
				{
					Content = "Executed in '"+ObjectName+"' database";
					ExpRes = "Query should be updated Successfully in "+ObjectName;
					ActRes = "Query is updated in "+ObjectName;
				}
				else if((Action.equalsIgnoreCase("ExecuteQueryAndStoreValues")))
				{
					Content = "Executed in '"+ObjectName+"' database";
					ExpRes = "Query should be updated Successfully in "+ObjectName;
					ActRes = "Query is updated in "+ObjectName;
				}
				else if((Action.equalsIgnoreCase("ExecuteRejectedTransQueryAndStoreValues")))
				{
					Content = "Executed in '"+ObjectName+"' database";
					ExpRes = "Query should be updated Successfully in "+ObjectName;
					ActRes = "Query is updated in "+ObjectName;
				}
				else if(Action.equalsIgnoreCase("storeSelectedValue")||Action.equalsIgnoreCase("storevaluefromPropertyFile"))
				{
					Content = "Store the value in '"+expprop+"with value " +Navigate.keyVal;
					ExpRes = "The '"+expprop+"' value should be stored" +Navigate.keyVal;
					ActRes = "The '"+expprop+"' value is stored" +Navigate.keyVal;
				}
				else if(Action.equalsIgnoreCase("storeBalance"))
				{
					Content = "Store the value in '"+ExpectedResult+"' ";
					ExpRes = "The value should be stored in '"+ExpectedResult+"'";
					ActRes = "The value is stored in '"+ExpectedResult+"'";
				}
				else if((Action.equalsIgnoreCase("storeUniqueMobileEmail"))||(Action.equalsIgnoreCase("storeRandomSecurityID")))
				{
					Content = "Store the value in '"+ObjectName+"' ";
					ExpRes = "The value should be stored in '"+ObjectName+"'";
					ActRes = "The value is stored in '"+ObjectName+"'";
				}
				else if((Action.equalsIgnoreCase("store"))||(Action.equalsIgnoreCase("storevalueinPropertyFile"))||(Action.equalsIgnoreCase("storeDamagedCoupon"))||(Action.equalsIgnoreCase("storeValueFromIvrRequest"))||(Action.equalsIgnoreCase("storeValueFromAtmWebSimulator"))||(Action.equalsIgnoreCase("storeValueFromWebSimulator"))||(Action.equalsIgnoreCase("storeAttribute")) || (Action.equalsIgnoreCase("storeText")) ||(Action.equalsIgnoreCase("storeTextPresent"))||(Action.equalsIgnoreCase("storeTitle"))||(Action.equalsIgnoreCase("storeNumber"))||(Action.equalsIgnoreCase("storeSelectedValueInTable")))
				{
					Content = "Store the value in '"+ExpectedResult+"' ";
					ExpRes = "The value should be stored in '"+ExpectedResult+"'";
					ActRes = "The value is stored in '"+ExpectedResult+"'";
				}
				else if(Action.equalsIgnoreCase("storeBalance"))
				{
					Content = "Store the value in '"+ExpectedResult+"' ";
					ExpRes = "The value should be stored in '"+ExpectedResult+"'";
					ActRes = "The value is stored in '"+ExpectedResult+"'";
				}
				else if(Action.equalsIgnoreCase("StoreValueInWebTable")||Action.equalsIgnoreCase("StoreValueFromWebTableBasedOnLables"))
				{
					Content = "'"+Navigate.keyVal+"'"+"Store In Web Table";
					ExpRes = "'"+Navigate.keyVal+"'"+"The value should be stored";
					ActRes = "The value stored in Web Table"+"'"+Navigate.keyVal+"'";
				}
				else if (Action.equalsIgnoreCase("deleteAllVisibleCookies"))
				{
					Content = "Delete '"+ObjRefName+"'";
					ExpRes = "'"+ObjRefName+"' should delete all visible cookies";
					ActRes = "'"+ObjRefName+"' deleted all visible cookies ";
				}
				else if (Action.equalsIgnoreCase("setTimeout"))
				{
					Content = "Set '"+ObjRefName+"'";
					ExpRes = "'"+ObjRefName+"' should time out once action performed";
					ActRes = "'"+ObjRefName+"' timed out ";
				}
				else if (Action.equalsIgnoreCase("check"))
				{
					Content = "Check '"+ObjRefName+"' Checkbox";
					ExpRes = "Checkbox '"+ObjRefName+"' should be in 'ON' state";
					ActRes = "Checkbox '"+ObjRefName+"' is in 'ON' state";
				}
				else if (Action.equalsIgnoreCase("uncheck"))
				{
					Content = "UnCheck '"+ObjRefName+"' Checkbox";
					ExpRes = "Checkbox '"+ObjRefName+"' should be in 'OFF' state";
					ActRes = "Checkbox '"+ObjRefName+"' is in 'OFF' state";
				}
				else if((Action.equalsIgnoreCase("chooseOkOnNextConfirmationAndWait"))||(Action.equalsIgnoreCase("clickAlert")))
				{
					Content = "Click on OK action performed on '"+ObjRefName+"' ";
					ExpRes = "Click on OK should be performed on '"+ObjRefName+"'";
					ActRes = "Click on OK is performed on '"+ObjRefName+"'";
				}
				else if(Action.equalsIgnoreCase("refreshAndWait"))
				{
					Content = "To"+ObjRefName+"Current Page";
					ExpRes = "Current Page should '"+ObjRefName+"'";
					ActRes = "Current Page is '"+ObjRefName+"'";
				}
				else if(Action.equalsIgnoreCase("selectWindow"))
				{
					Content = "'"+ObjectName+"' is focussed";
					ExpRes = "Window name '"+ObjectName+"' is should be focussed";
					ActRes = "Window name '"+ObjectName+"' is focussed";
				}
				else if(Action.equalsIgnoreCase("switchToWindow"))
				{
					Content = "'"+ObjectName+"' is focussed";
					ExpRes = "Window name '"+ObjectName+"' is should be focussed";
					ActRes = "Window name '"+ObjectName+"' is focussed";
				}
				
				else if(Action.equalsIgnoreCase("upLoadFiles"))
				{
					Content = "'Upload the file'";
					ExpRes = "'File should be uploaded'";
					ActRes = "'File is uploaded'";
				}
				else if(Action.equalsIgnoreCase("generatePins"))
				{
					Content = "Generate "+ObjectName+" pins";
					ExpRes = "'"+ObjectName+"' pins should be generated";
					ActRes = "'"+ObjectName+"' pins are generated";
				}
				else if(Action.equalsIgnoreCase("removeSelection"))
				{
					Content = "Unselect '"+ExpectedResult+"'";
					ExpRes = "'"+ExpectedResult+"' should be unselected";
					ActRes = "'"+ActualResult+"' is unselected";
				}
				else if(Action.equalsIgnoreCase("addSelection"))
				{
					Content = "Select '"+ExpectedResult+"'";
					ExpRes = "'"+ExpectedResult+"' should be selected";
					ActRes = "'"+ActualResult+"' is selected";
				}
				else if(Action.equalsIgnoreCase("actionOnWebTable"))
				{
					Content = "'"+ExpectedResult+"' is performed";
					ExpRes = "'"+ExpectedResult+"' should be performed";
					ActRes = "'"+ActualResult+"' is performed";
				}
				
				else if(Action.equalsIgnoreCase("PostpaidBillConsultationExpectedValues"))
				{
					Content ="'"+"PostpaidBillConsultationExpectedValues Stored"+"'"+"'"+"38Field: "+"'"+Navigate.BillPayment_Field38+"'"+"44Field: "+"'"+Navigate.BillPayment_Field44;
					ExpRes = "PostpaidBillConsultation FieldValues"+"'"+"45Field:"+"'"+Navigate.BillPaymentField45+"'"+"126FieldValue:"+"'"+Navigate.BillPaymentField126;
					ActRes = "PostpaidBillConsultation FieldValues Strored";
				}
				else if(Action.equalsIgnoreCase("PostpaidBillConsultationUsingRrnExpectedValues"))
				{
					Content ="'"+"PostpaidBillConsultationExpectedValues Stored"+"'"+"'"+"38Field: "+"'"+Navigate.BillPayment_Field38+"'"+"44Field: "+"'"+Navigate.BillPayment_Field44;
					ExpRes = "PostpaidBillConsultation FieldValues"+"'"+"45Field:"+"'"+Navigate.BillPaymentField45+"'"+"126FieldValue:"+"'"+Navigate.BillPaymentField126;
					ActRes = "PostpaidBillConsultation FieldValues Strored";
				}
				
				else if(Action.equalsIgnoreCase("storeWindowId"))
				{
					Content = "'"+ExpectedResult+"' is performed";
					ExpRes = "'"+ExpectedResult+"' should be performed";
					ActRes = "'"+ActualResult+"' is performed";
				}
				else if(Action.equalsIgnoreCase("fireEvent"))
				{
					Content = "'Refresh the page'";
					ExpRes = "'The page should be refreshed'";
					ActRes = "'The page is refreshed'";
				}
				else if((Action.equalsIgnoreCase("storeFromSimulatorAmount"))||(Action.equalsIgnoreCase("storeRechargeAmount"))||(Action.equalsIgnoreCase("storeFromSimulator")) ||(Action.equalsIgnoreCase("storeFromSimulator")) ||(Action.equalsIgnoreCase("storeFromUSSDSimulator"))|| (Action.equalsIgnoreCase("storeFromBankSimulator")))
				{
					Content = "Store the '"+ObjRefName+"' field value from the simulator";
					ExpRes = "'"+ObjRefName+"' field should be stored with value "+ExpectedResult;
					ActRes = "'"+ObjRefName+"' field should be stored with value "+ActualResult;
				}			
				else if(Action.equalsIgnoreCase("addTableValues"))
				{
					Content = "Table values from column number '"+Navigate.col+"' should be summed";
					ExpRes = "Table values from column number '"+Navigate.col+"' should be summed";
					ActRes = "Table values from column number '"+Navigate.col+"' is summed and the value is '"+Navigate.total+"'";
				}
				else if(Action.equalsIgnoreCase("executeStoreProcedure"))
				{
					Content = "'To execute Store Procedure'";
					ExpRes = "'Store Procedure should be executed'";
					ActRes = "'Store Procedure Executed'";
				}
				
			}
			else if(FinalResult.equalsIgnoreCase("Fail"))
			{
				if(Action.equalsIgnoreCase("click") || (Action.equalsIgnoreCase("isChecked"))|| (Action.equalsIgnoreCase("clickOnly")) ||(Action.equalsIgnoreCase("WaitAndclick"))||(Action.equalsIgnoreCase("clickAndWait"))||(Action.equalsIgnoreCase("clickPopup")))
				{
					Content = "Click on '"+ObjRefName+"' ";
					ExpRes = "Click action should be performed on '"+ObjRefName+"'";
					ActRes = "'"+Navigate.errorMsg+"'";
				}//openNewTabAndNavigateUrl
				
				else if(Action.equalsIgnoreCase("open") || (Action.equalsIgnoreCase("openAndWait")))
				{
					Content = "Open  '"+ObjRefName+"' ";
					ExpRes = "Open action should be performed on '"+ObjRefName+"'";
					ActRes = "'"+Navigate.errorMsg+"'";
				}
				else if(Action.equalsIgnoreCase("closeNewTab"))
				{
					Content = "Close Tab   '"+ObjRefName+"' ";
					ExpRes = "close tab action should be performed on '"+ObjRefName+"'";
					ActRes = "'"+Navigate.errorMsg+"'";
				}
				else if(Action.equalsIgnoreCase("openNewTabAndNavigateUrl"))
				{
					Content = "Open  '"+ObjRefName+"' ";
					ExpRes = "Open action should be performed on '"+ObjRefName+"'";
					ActRes = "'"+Navigate.errorMsg+"'";
				}
				else if (Action.equalsIgnoreCase("type") || Action.equalsIgnoreCase("ClickActionUsingMouse")|| Action.equalsIgnoreCase("SearchDropDown")|| Action.equalsIgnoreCase("typeAndWait")||Action.equalsIgnoreCase("typeDate"))
				{
					Content = ObjRefName+" textbox is entered with value '"+ExpectedResult+"'";
					ExpRes = "'"+ExpectedResult+"' should be displayed in "+ObjRefName+" field";
					ActRes = "'"+Navigate.errorMsg+"'";
				}
				else if(Action.equalsIgnoreCase("storeAttributeFromWebServiceRequest"))
				{
					Content = "The '"+expprop+ " is stored in  " +Navigate.keyVal;
					ExpRes = "The '"+expprop+"' value should be stored in " +Navigate.keyVal;
					ActRes = "The '"+expprop+"' value is not stored in " +Navigate.keyVal;
				}
				else if(Action.equalsIgnoreCase("storeAttributeFromWebServiceRespose"))
				{
					Content = "The '"+expprop+ " is stored in  " +Navigate.keyVal;
					ExpRes = "The '"+expprop+"' value should be stored in " +Navigate.keyVal;
					ActRes = "The '"+expprop+"' value is not stored in " +Navigate.keyVal;
				}
				else if((Action.equalsIgnoreCase("mouseOver"))||(Action.equalsIgnoreCase("mouseOverAndWait")))
				{
					Content = "MouseOver action performed on '"+ObjRefName+"' ";
					ExpRes = "MouseOver should be performed on '"+ObjRefName+"'";
					ActRes = "MouseOver is not performed on '"+ObjRefName+"'";
				}
				else if ((Action.equalsIgnoreCase("select"))||(Action.equalsIgnoreCase("selectAndWait")))
				{
					Content = "'"+ExpectedResult+"' is selected in the listbox "+ObjRefName;
					ExpRes = "'"+ExpectedResult+"' should be displayed in "+ObjRefName+" listbox";
					ActRes = "'"+Navigate.errorMsg+"'";
					
				}
				else if ((Action.equalsIgnoreCase("randomSelect")))
				{
					Content = "Select "+ObjRefName+" with value '"+PerformActions.randomValue+"'";
					ExpRes = "'"+PerformActions.randomValue+"' should not be displayed in "+ObjRefName+" listbox";
					ActRes = "'"+Navigate.errorMsg+"'";
				}
				else if(Action.equalsIgnoreCase("storeSelectedValue")||Action.equalsIgnoreCase("storevaluefromPropertyFile"))
				{
					Content = "Store the value in '"+expprop+"with value " +Navigate.keyVal;
					ExpRes = "The '"+expprop+"' value should be stored" +Navigate.keyVal;
					ActRes = "The '"+expprop+"' value is not stored" +Navigate.keyVal;
				}
				else if(Action.equalsIgnoreCase("storeBalance"))
				{
					Content = "Store the value in '"+ExpectedResult+"' ";
					ExpRes = "The value should be stored in '"+ExpectedResult+"'";
					ActRes = "The value is not stored in '"+ExpectedResult+"'";
				}
				else if(Action.equalsIgnoreCase("storeUniqueEmail"))
				{
					Content = "Store the value in '"+ObjectName+"' ";
					ExpRes = "The value should be stored in '"+ObjectName+"'";
					ActRes = "The value is not stored in '"+ObjectName+"'";
				}
				else if((Action.equalsIgnoreCase("DBConnect"))||(Action.equalsIgnoreCase("DBConnectSyn")))
				{
					Content = "Connect to/not Connected"+ObjectName+"'database";
					ExpRes = "'"+ObjectName+"' database should Not be connected/Not Connected"+"Value not Get "+Expected.DbValue;
					ActRes = "'"+DB.errMsg+"'";
				}
				else if((Action.equalsIgnoreCase("DeleteQuery")))
				{
					Content = "Executed in '"+ObjectName+"' database";
					ExpRes = "Query should be updated Successfully in "+ObjectName;
					ActRes = "Query is not updated in "+ObjectName;
				}
				else if((Action.equalsIgnoreCase("ExecuteQuery")))
				{
					Content = "Executed in '"+ObjectName+"' database";
					ExpRes = "Query should be updated Successfully in "+ObjectName;
					ActRes = "Query is not updated in "+ObjectName;
				}
				else if((Action.equalsIgnoreCase("ExecuteQueryAndStoreValues")))
				{
					Content = "Executed in '"+ObjectName+"' database";
					ExpRes = "Query should be updated Successfully in "+ObjectName;
					ActRes = "Query is not updated in "+ObjectName;
				}
				else if((Action.equalsIgnoreCase("ExecuteRejectedTransQueryAndStoreValues")))
				{
					Content = "Executed in '"+ObjectName+"' database";
					ExpRes = "Query should be updated Successfully in "+ObjectName;
					ActRes = "Query is not updated in "+ObjectName;
				}
				else if((Action.equalsIgnoreCase("storeUniqueMobileEmail"))||(Action.equalsIgnoreCase("storeRandomSecurityID")))
				{
					Content = "Store the value in '"+ObjectName+"' ";
					ExpRes = "The value should be stored in '"+ObjectName+"'";
					ActRes = "The value is stored in not'"+ObjectName+"'";
				}
				else if((Action.equalsIgnoreCase("store"))||(Action.equalsIgnoreCase("storeAttribute")) || (Action.equalsIgnoreCase("storeText")) ||(Action.equalsIgnoreCase("storevalueinPropertyFile"))||(Action.equalsIgnoreCase("storeTextPresent"))||(Action.equalsIgnoreCase("storeTitle"))||(Action.equalsIgnoreCase("storeNumber")))
				{
					Content = "Store the value in '"+ExpectedResult+"' ";
					ExpRes = "The value should be stored in '"+ExpectedResult+"'";
					ActRes = "The value is not stored in '"+ExpectedResult+"'";
				}
				else if(Action.equalsIgnoreCase("StoreValueInWebTable")||Action.equalsIgnoreCase("StoreValueFromWebTableBasedOnLables"))
				{
					Content = "'"+Navigate.keyVal+"'"+"Not Store In Web Table";
					ExpRes = "'"+Navigate.keyVal+"'"+"Not Store In Web Table";
					ActRes = "The value is Not stored in Web Table"+"'"+Navigate.keyVal+"'";
				}
				else if (Action.equalsIgnoreCase("deleteAllVisibleCookies"))
				{
					Content = "Delete '"+ObjRefName+"'";
					ExpRes = "'"+ObjRefName+"' should delete all visible cookies";
					ActRes = "'"+ObjRefName+"' all visible cookies are not deleted";
				}
				else if (Action.equalsIgnoreCase("setTimeout"))
				{
					Content = "Set '"+ObjRefName+"'";
					ExpRes = "'"+ObjRefName+"' should time out once action performed";
					ActRes = "'"+ObjRefName+"' is not timed out ";
				}
				else if (Action.equalsIgnoreCase("check"))
				{
					Content = "Check "+ObjRefName+" Checkbox";
					ExpRes = "Checkbox "+ObjRefName+" should be in 'ON' state";
					ActRes = "'"+Navigate.errorMsg+"'";
				}
				else if (Action.equalsIgnoreCase("uncheck"))
				{
					Content = "UnCheck "+ObjRefName+" Checkbox";
					ExpRes = "Checkbox "+ObjRefName+" should be in 'OFF' state";
					ActRes = "'"+Navigate.errorMsg+"'";
				}
				else if((Action.equalsIgnoreCase("chooseOkOnNextConfirmationAndWait"))||(Action.equalsIgnoreCase("clickAlert")))
				{
					Content = "Click on OK action performed on '"+ObjRefName+"' ";
					ExpRes = "Click on OK should be performed on '"+ObjRefName+"'";
					ActRes = "'"+Navigate.errorMsg+"'";
				}
				else if(Action.equalsIgnoreCase("refreshAndWait"))
				{
					Content = "To"+ObjRefName+"Current Page";
					ExpRes = "Current Page should "+ObjRefName+"'";
					ActRes = "'"+Navigate.errorMsg+"'";
				}
				else if(Action.equalsIgnoreCase("selectWindow"))
				{
					Content = "'"+ObjectName+"' is focussed";
					ExpRes = "Window name '"+ObjectName+"' is should be focussed";
					ActRes = "'"+Navigate.errorMsg+"'";
				}
				
				else if(Action.equalsIgnoreCase("switchToWindow"))
				{
					Content = "'"+ObjectName+"' is focussed";
					ExpRes = "Window name '"+ObjectName+"' is should be focussed";
					ActRes = "'"+Navigate.errorMsg+"'";
				}
				else if(Action.equalsIgnoreCase("upLoadFiles"))
				{
					Content = "'Upload the file'";
					ExpRes = "'File should be uploaded'";
					ActRes = "'File cannot be uploaded'";
				}
				else if(Action.equalsIgnoreCase("generatePins"))
				{
					Content = "Generate '"+ObjectName+"' pins";
					ExpRes = "'"+ObjectName+"' pins should be generated";
					ActRes = "'"+ObjectName+"' pins cannot be generated";
				}
				else if(Action.equalsIgnoreCase("removeSelection"))
				{
					Content = "Unselect '"+ExpectedResult+"'";
					ExpRes = "'"+ExpectedResult+"' should be unselected";
					ActRes = "'"+Navigate.errorMsg+"'";
				}
				else if(Action.equalsIgnoreCase("addSelection"))
				{
					Content = "Select '"+ExpectedResult+"'";
					ExpRes = "'"+ExpectedResult+"' should be selected";
					ActRes = "'"+Navigate.errorMsg+"'";
				}
				else if(Action.equalsIgnoreCase("fireEvent"))
				{
					Content = "'Refresh the page'";
					ExpRes = "'The page should be refreshed'";
					ActRes = "'The page cannot be refreshed'";
				}
				else if(Action.equalsIgnoreCase("addTableValues"))
				{
					Content = "Table values from column number '"+Navigate.col+"' should be summed";
					ExpRes = "Table values from column number '"+Navigate.col+"' should be summed";
					ActRes = "Table values from column number '"+Navigate.col+"' cannot be summed";
				}
				else if((Action.equalsIgnoreCase("storeFromSimulator"))||(Action.equalsIgnoreCase("storeFromUSSDSimulator")) || (Action.equalsIgnoreCase("storeFromBankSimulator")))
				{
					Content = "Store the '"+ObjRefName+"' field value from the simulator";
					ExpRes = "'"+ObjRefName+"' field should be stored with value "+ExpectedResult;
					ActRes = "'"+ObjRefName+"' field should be stored with value "+ActualResult;
				}
				
				else if(Action.equalsIgnoreCase("storeWindowId"))
				{
					Content = "'"+ExpectedResult+"' is  not performed";
					ExpRes = "'"+ExpectedResult+"' should not be performed";
					ActRes = "'"+Navigate.errorMsg+"'";
				}
				else if(Action.equalsIgnoreCase("actionOnWebTable"))
				{
					Content = "'"+ExpectedResult+"' is  not performed";
					ExpRes = "'"+ExpectedResult+"' should not be performed";
					ActRes = "'"+Navigate.errorMsg+"'";
				}
				
				else if(Action.equalsIgnoreCase("PostpaidBillConsultationExpectedValues"))
				{
					Content ="post paid Bill consultation expeted values failed to store";
					ExpRes = "post paid Bill consultation expeted values failed to store";
					ActRes = "post paid Bill consultation expeted values failed to store";	
				}
				
				else if(Action.equalsIgnoreCase("PostpaidBillConsultationUsingRrnExpectedValues"))
				{
					Content ="post paid Bill consultation expeted values failed to store";
					ExpRes = "post paid Bill consultation expeted values failed to store";
					ActRes = "post paid Bill consultation expeted values failed to store";	
				}
				else if(Action.equalsIgnoreCase("executeStoreProcedure"))
				{
					Content = "'To execute Store Procedure'";
					ExpRes = "'Store Procedure should be executed'";
					ActRes = "'Store Procedure not Executed'";
				}
			}
			Result.XMLWriter(id, StepNode, ActRes, Content, ExpRes, StepPath, StepRes, StepTime);
		}
	}
	public static void XmlExpModifier( String NodeName, String ExpectedResult, String ActualResult, String FinalResult, String Time, String Path, String expprop,String expRefName,String expObjectName) throws Exception
	{
			StepTime = Time;
			StepPath = Path;
			StepRes = FinalResult;
			StepNode = NodeName;
		if (NodeName.equalsIgnoreCase("Expected"))
		{
			Content  = null;
			ExpRes = null;
			ActRes = null;
			if(FinalResult.equalsIgnoreCase("Pass"))
			{
				 if(expprop.equalsIgnoreCase("VerifyTextPresent")||(expprop.equalsIgnoreCase("waitForTextPresent"))||(expprop.equalsIgnoreCase("waitForText")))
				{
					Content = "To verify '"+ExpectedResult+"' text is present";
					ExpRes = "'"+ExpectedResult+"' text should be present";
					ActRes = "'"+ExpectedResult+"' text is present";
				}
				 else if(expprop.equalsIgnoreCase("VerifyText"))
				 {		 
					 if(ExpectedResult!=null)
					 {
					   if((ExpectedResult.contains("subtraction"))||(ExpectedResult.contains("Addition")))
						 {
							Content = "'"+ExpectedResult+Expected.expBalance+"' balance is displayed Is Updated";
							ExpRes = "'"+ExpectedResult+Expected.expBalance+"' balance  should  be displayed for source";
							ActRes = "'"+ExpectedResult+Expected.Appactualval+"' balance is displayed for source";
						 } 
					
					   else if(ExpectedResult.indexOf("get-")==0)
					   {
						   Content = expRefName+"is"+"'"+expValForResults+"'";
						   ExpRes = "Expected "+"'"+expValForResults+"'"+"value  should be present";
						   ActRes = "Actual "+"'"+Expected.Appactualval+"'"+"value is present";
					   }
					 else
					 {
						Content = expRefName +"'"+ExpectedResult+"'";
						ExpRes = "Expected value "+"'"+ExpectedResult+"'"+"should be present";
						ActRes = "Actual value "+"'"+Expected.Appactualval+"'"+"Present";
					 }
				 }
				 }
				 else if(expprop.equalsIgnoreCase("VerifyBalance"))//added kv
				 {		 
					 if(ExpectedResult!=null)
					 {
					   if((ExpectedResult.contains("subtraction"))||(ExpectedResult.contains("Addition")))
						 {
							Content = "'"+ExpectedResult+Expected.expBalance+"' balance is displayed Is Updated";
							ExpRes = "'"+ExpectedResult+Expected.expBalance+"' balance  should  be displayed for source";
							ActRes = "'"+ExpectedResult+Expected.Appactualval+"' balance is displayed for source";
						 } 
					
					   else if(ExpectedResult.indexOf("get-")==0)
					   {
						   Content = "'"+expValForResults+"'"+"value is present";
							ExpRes = "'"+expValForResults+"'"+"value  should be present";
							ActRes = "'"+Expected.Appactualval+"'"+"value is present";
					   }
					 else
					 {
						Content = expRefName+"Value"+"'"+Expected.actual+"'"+"Should be Present";
						ExpRes = expRefName+"Expected Value"+"'"+Expected.expValForResults+"'";
						ActRes = expRefName+"Getting Actual Value"+"'"+Expected.actual+"'";
					 }
				 }
				 }
				 else if(expprop.equalsIgnoreCase("verifyItemsinComboBox"))
				{
					Content = "To verify '"+ExpectedResult+"' text is present inside the combobox";
					ExpRes = "'"+ExpectedResult+"' text should be present inside the combobox";
					ActRes = "'"+ExpectedResult+"' text is present inside the combobox";
				}
				 else if(expprop.equalsIgnoreCase("verifyValuesinDb"))
					{
						Content = "To verify"+" "+expObjectName+" "+"'"+Expected.actual+"'"+"Value Is Displayed in Db";
						ExpRes = expObjectName+" "+"'"+Expected.actual+"'"+" "+"Value Should Be Displayed";
						ActRes = expObjectName+" "+"'"+ActualResult+"'"+" "+"Value Is Displayed";
					}
				 else if(expprop.equalsIgnoreCase("verifyAttributeInWebServiceResponse"))
					{
						if(ExpectedResult!=null)
						{
							Content = "To Verify"+"'"+ TestCase.trName + "'"+ "in Response"+" "+"As"+Expected.expValForResults;
							ExpRes = "Value from the webservice response should be"+"'"+expVal1+":'"+"'"+Expected.expValForResults+ "'"+"is displayed.";
							ActRes = "Value  from the webservice responce is"+":"+expVal1+":'"+"'"+ActualResult+"'"+"is displayed.";
						}
						
					}
				 else if(expprop.equalsIgnoreCase("verifyItemsNotInComboBox"))
				{
					Content = "To verify"+"'"+Expected.expVal1+"'"+"Value is not present inside the combobox";
					ExpRes = "'"+Expected.expVal1+"'"+"Value should not be present inside the combobox";
					ActRes = "'"+Expected.expVal1+"'"+"Value is not present inside the combobox";
				}
				 else if (expprop.equalsIgnoreCase("verifyConfirmation")||(expprop.equalsIgnoreCase("verifyAlert")))
				{
					Content = "To verify"+"'"+Expected.expVal1+"'"+"Value is present in"+"window";
					ExpRes = "'"+Expected.expVal1+"'"+"window should be displayed with innertext : '"+actual+"'";
					ActRes = "'"+Expected.expVal1+"'"+"Value is Present In window"+"'"+actual+"'";
				}
				else if((expprop.equalsIgnoreCase("VerifyTitle"))||(expprop.equalsIgnoreCase("waitForTitle")))
				{
					Content = "To verify '"+ExpectedResult+"' title";
					ExpRes = "'"+ExpectedResult+"' title should be displayed";
					ActRes = "'"+ExpectedResult+"' title is displayed";
				}
				 else if (expprop.equalsIgnoreCase("verifyErrorMessage"))
					{
						Content = "To verify"+"'"+Expected.expVal1+"'"+"Value is present in Error Message";
						ExpRes = "Error Message should be displayed with innertext :"+"'"+actual+"'";
						ActRes = "'"+Expected.expVal1+"'"+"Value is Present"+"'"+actual+"'";
					}
				 
				else if((expprop.equalsIgnoreCase("verifyElementPresent"))||(expprop.equalsIgnoreCase("waitForElementPresent")))
				{
					Content = "To verify '"+expRefName+"' element is present";
					ExpRes = "'"+expRefName+"' element should be present";
					ActRes = "'"+expRefName+"' element is present";
				}
				else if((expprop.equalsIgnoreCase("verifyElementNotPresent"))||(expprop.equalsIgnoreCase("waitForElementNotPresent")))
				{
					Content = "To verify '"+expRefName+"' element is not present";
					ExpRes = "'"+expRefName+"' element should not present";
					ActRes = "'"+expRefName+"' element is not present"+ExpectedResult;
				}
				else if((expprop.equalsIgnoreCase("verifyChecked")))
				{
					Content = "To verify the state of '"+expRefName+"'";
					ExpRes = "'"+expRefName+"' element should be in '"+ExpectedResult+"' state";
					ActRes = "'"+expRefName+"' element is in '"+ActualResult+"' state";
				}
				else if(expprop.equalsIgnoreCase("verifyTextNotPresent")||(expprop.equalsIgnoreCase("waitForTextNotPresent"))||(expprop.equalsIgnoreCase("verifyTextNotExist")))
				{
					Content = "To verify '"+ExpectedResult+"' text is not present";
					ExpRes = "'"+ExpectedResult+"' text should not present";
					ActRes = "'"+ExpectedResult+"' text is not present";
				}
				else if((expprop.equalsIgnoreCase("verifyDynamicMessage")))
				{
				 if(ExpectedResult!=null)
					{
				 if(ExpectedResult.indexOf("get")==0){
					Content = "To verify whether the message '"+expValForResults +"' is displayed in the webpage";
					ExpRes =  "The message '"+expValForResults +"' should be displayed in the webpage";
					ActRes = "The message '"+expValForResults +"' is displayed in the webpage";
				}
				 else{
				    Content = "To verify whether the message '"+ExpectedResult +"' is displayed in the webpage";
					ExpRes =  "The message '"+ExpectedResult +"' should be displayed in the webpage";
					ActRes = "The message '"+ExpectedResult +"' is displayed in the webpage";
				 }
				}
				}
				
				else if(expprop.equalsIgnoreCase("verifyValue")||(expprop.equalsIgnoreCase("waitForValue")))
				{
					Content = "To verify whether '"+ExpectedResult+"'  is displayed";
					ExpRes = "'"+ExpectedResult+"' value should displayed";
					ActRes = "'"+ExpectedResult+"' value is displayed";
				}
				else if(expprop.equalsIgnoreCase("verifyTable")||(expprop.equalsIgnoreCase("waitForTable"))||(expprop.equalsIgnoreCase("verifyTextInTable")))
				{
					if(ExpectedResult!=null && tableValue!=null)
					{
					if(ExpectedResult.indexOf("get")==0){
						Content = "To verify '"+ExpectedResult+"' table value is displayed in "+tableValue[1]+" row "+tableValue[2]+"column"+" "+"in"+""+expRefName;
						ExpRes = "'"+ExpectedResult+"' table value should be displayed in "+tableValue[1]+" row "+tableValue[2]+"column"+" "+"in"+" "+expRefName;
						ActRes = "'"+ExpectedResult+"' table value is displayed in "+tableValue[1]+" row "+tableValue[2]+"column"+" "+"in"+" "+expRefName;
						}
					else{
					Content = "To verify " +"'"+Expected.Labelvalue+"'"+" "+"'"+Expected.actual+"'table value is displayed in "+tableValue[1]+" row "+tableValue[2]+"column"+" "+"in"+" "+expRefName;
					ExpRes = "'"+Expected.Labelvalue+"'"+" "+" is "+"'"+Expected.expVal1+"' table value should be displayed in "+tableValue[1]+" row "+tableValue[2]+"column"+" "+"in"+" "+expRefName;
					ActRes = "'"+Expected.Labelvalue+"'"+" "+"is "+"'"+Expected.actual+"' table value is displayed in "+tableValue[1]+" row "+tableValue[2]+"column"+" "+"in"+" "+expRefName;
					}
				}
					else
					{
						Content = "Verify "+ Expected.Labelvalue+" is: "+ "'"+Expected.expVal1+"'"+"table value is displayed ";
						ExpRes = Expected.Labelvalue+" is: "+"'"+Expected.expVal1+"'"+"table value should be displayed";
						ActRes = Expected.Labelvalue+" is: "+"'"+Expected.actual+"'"+"table value is displayed";
						
					}
				}
				else if((expprop.equalsIgnoreCase("verifySelectedLabel")) || (expprop.equalsIgnoreCase("waitForSelectedLabel")))
				{
					Content = "To verify '"+expRefName+"' value is selected on "+expprop+"";
					ExpRes = "'"+expRefName+"' value should be selected on "+expprop+"";
					ActRes = "'"+expRefName+"' value is selected on "+expprop+"";
				}
				else if((expprop.equalsIgnoreCase("verifySelectedValue")))
				{
					Content = "To verify '"+tempSelected+"' is selected in "+expRefName+" combobox";
					ExpRes = "'"+tempSelected+"' should be selected in "+expRefName+" combobox";
					ActRes = "'"+tempSelected+"' is selected in "+expRefName+" combobox";
				}
				else if((expprop.equalsIgnoreCase("waitForPopUp")))
				{
					Content = "'To wait for a popup to appear'";
					ExpRes = "'Popup should appear'";
					ActRes = "'Popup appears'";
				}
				else if((expprop.equalsIgnoreCase("verifyValueinDB")))
				{
					
					Content = "To verify whether the value '"+ExpectedResult +"' is present in the database";
					ExpRes =  "The value '"+ Expected.actual +"' should be present in the database";
					ActRes = "The value '"+valFromDB +"' is not present in the database";
				
				}
				else if((expprop.equalsIgnoreCase("verifyTableContent")))
				{
					Content = "To verify '"+ExpectedResult+"' table value is displayed";
					ExpRes = "'"+ExpectedResult+"' table value should displayed";
					ActRes = "'"+ExpectedResult+"' table value is displayed in "+rownum+" row "+colnum+" column";
				}
				else if((expprop.equalsIgnoreCase("verifyTableSearch")))
				{
					Content = "verify value In"+"'"+expRefName+"'";
					ExpRes = "Expected value In Table"+"'"+Expected.expVal1+"'";
					ActRes = "'Actual value getting in"+"'"+expRefName+"'"+"Value is"+"'"+actual+"'";
				}
				else if((expprop.equalsIgnoreCase("verifyMailAlert")))
				{
					Content = "To check weather the mail has been sent to the user '"+Configure.userName+"'";
					ExpRes = "Mail should be sent to the user '"+Configure.userName+"'";
					ActRes = "Folllowing mail with details '"+readEmails.mail+"' has been sent to the user "+Configure.userName;
				}
				else if((expprop.equalsIgnoreCase("verifyApplicationValues")))
				{
					Content = "'To verify two values from application'";
					ExpRes = "'Both the application values should be equal'";
					ActRes = "Application values '"+ExpectedResult+"' and '"+ActualResult+"' are compared and found equal" ;
				}
				else if((expprop.equalsIgnoreCase("verifyMaxLength")))
				{
					Content = "To verify maximun length of '"+expRefName+"' field";
					ExpRes = "'"+expRefName+"' has field length of "+ExpectedResult+" characters";
					ActRes = "'"+expRefName+"' has field length of "+ActualResult+" characters";
				}
				else if((expprop.equalsIgnoreCase("verifyValueinExcel"))||(expprop.equalsIgnoreCase("verifyValueinPDF")))
				{
					Content = "To verify whether '"+ExpectedResult+"'  text is present in the file "+Navigate.filePath;
					ExpRes = "'"+ExpectedResult+"' text value should be present in the file "+Navigate.filePath;
					ActRes = "'"+ExpectedResult+"' text value is present in the file "+Navigate.filePath;
				}
				else if((expprop.equalsIgnoreCase("verifyFromSimulator"))||(expprop.equalsIgnoreCase("verifyFromAtmWebSimulator"))||(expprop.equalsIgnoreCase("verifyFromWebSimulator"))||(expprop.equalsIgnoreCase("verifyFromUSSDSimulator"))||(expprop.equalsIgnoreCase("verifyFromBankSimulator")))
				{
					if(ExpectedResult!=null)
					{
					if(ExpectedResult.indexOf("get")==0){
						Content = "To verify  '"+expValForResults +"' from simulator";
						ExpRes =  "Value of the '"+expValForResults +"' from the simulator should be "+ExpectedResult;
						ActRes = "Value of the'"+expValForResults +"' from the simulator is "+ActualResult;
					}
					else{
					Content = "To verify '"+expRefName+"' from simulator";
					ExpRes = "Value of the '"+expRefName+"' from the simulator should be "+ExpectedResult;
					ActRes = "Value of the '"+expRefName+"' from the simulator is "+ActualResult;
					}
				}
				}
				else if(expprop.equalsIgnoreCase("verifyBalanceValues"))
				{
					Content = "To verify"+" "+expObjectName+" "+"'"+Expected.actual+"'"+"Value Is Displayed In Db";
					ExpRes = expObjectName+" "+"'"+Expected.actual+"'"+" "+"Expected Value Should Be Displayed";
					ActRes = expObjectName+" "+"'"+ActualResult+"'"+" "+"Actual value is Displayed";
				}
				else if ((expprop.equalsIgnoreCase("VerifyTableDatatext")))
				{
					Content = "To verify the state of '" + expRefName + "'";
					ExpRes = "'" + expRefName + "' element should be in '"
					+ ExpectedResult + "' state";
					ActRes = "'" + expRefName + "' element is in '"
					+ ActualResult + "' state";
				}
				else if ((expprop.equalsIgnoreCase("verifyElementInWebTable")))
				{
					Content = "To verify '"+ExpectedResult+"' element is displayed";
					ExpRes = "'"+ExpectedResult+"'  element should be displayed";
					ActRes = "'"+Expected.actual+"' element is displayed";
				}
			}
			else if (FinalResult.equalsIgnoreCase("Fail"))
			   {
				 if(expprop.equalsIgnoreCase("VerifyTextPresent")||(expprop.equalsIgnoreCase("waitForTextPresent"))||(expprop.equalsIgnoreCase("waitForText")))
				{
					Content =  "To verify '"+ExpectedResult+"' text is present";
					ExpRes =  "'"+ExpectedResult+"' text should be present";
					ActRes = "'"+ExpectedResult+"' text is not present";
				}
				 else if(expprop.equalsIgnoreCase("VerifyText"))
				 {		 
					 if(ExpectedResult!=null)
					 {
					   if((ExpectedResult.contains("subtraction"))||(ExpectedResult.contains("Addition")))
						 {
							Content = ExpectedResult + Expected.expBalance+"' balance is displayed Is Not Updated";
							ExpRes = "'"+ExpectedResult+Expected.expBalance+"' balance  should Not be displayed for source";
							ActRes = "'"+ExpectedResult+Expected.Appactualval+"' balance is Not displayed for source";
						 } 
					   else if(ExpectedResult.indexOf("get-")==0)
					   {
						   Content = expRefName+"is"+"'"+expValForResults+"'";
						   ExpRes = "Expected "+"'"+expValForResults+"'"+"value  should be present";
						   ActRes = "Actual "+"'"+Expected.Appactualval+"'"+"value is present";
					   }					
					 else
					 {
						 Content = expRefName +"'"+ExpectedResult+"'";
						 ExpRes = "Expected value "+"'"+ExpectedResult+"'"+"should be present";
					     ActRes = "Actual value "+"'"+Expected.Appactualval+"'"+"Present";
					 }
					 }
				 }
				 else if(expprop.equalsIgnoreCase("verifyBalance"))//added kv
				 {		 
					 if(ExpectedResult!=null)
					 {
					   if((ExpectedResult.contains("subtraction"))||(ExpectedResult.contains("Addition")))
						 {
							Content = ExpectedResult + Expected.expBalance+"' balance is displayed Is Not Updated";
							ExpRes = "'"+ExpectedResult+Expected.expBalance+"' balance  should Not be displayed for source";
							ActRes = "'"+ExpectedResult+Expected.Appactualval+"' balance is Not displayed for source";
						 } 
					   else if(ExpectedResult.indexOf("get-")==0)
					   {
						   Content = expValForResults+"' value is present";
							ExpRes = "'"+expValForResults+"' value  should be present";
							ActRes = "'"+expValForResults+"' value is not present";
					   }					
					 else
					 {
						 Content = expRefName+"Value"+"'"+Expected.actual+"'"+"Should be Present";
							ExpRes = expRefName+"Expected Value"+"'"+Expected.actual+"'";
							ActRes = expRefName+"Getting Actual Value"+"'"+Expected.expValForResults+"'";
					 }
					 }
				 }
				 
				 else if((expprop.equalsIgnoreCase("verifyDynamicMessage")))
					{
					  if(ExpectedResult!=null)
							{ 
					 if(ExpectedResult.indexOf("get")==0){
						Content = "To verify whether the message '"+expValForResults +"' is  Not displayed in the webpage";
						ExpRes =  "The message '"+expValForResults +"'  Not should be displayed in the webpage";
						ActRes = "The message '"+expValForResults +"' is Not displayed in the webpage";
					}
					 else{
					    Content = "To verify whether the message '"+ExpectedResult +"' is Not displayed in the webpage";
						ExpRes =  "The message '"+ExpectedResult +"' should  Not be displayed in the webpage";
						ActRes = "The message '"+ExpectedResult +"' is Not displayed in the webpage";
					 }
					}
					}
				 else if(expprop.equalsIgnoreCase("verifyValuesinDb"))
					{
						Content = "To verify "+" "+expRefName+" "+"'"+Expected.actual+"'"+"value is Displayed";
						ExpRes = expRefName+" "+"'"+Expected.expVal1+"'"+" "+"Expected value is Should be Displayed";
						ActRes = expRefName+" "+"'"+ActualResult+"'"+" "+"Actual value is Displayed";
					}
				 else if(expprop.equalsIgnoreCase("verifyAttributeInWebServiceResponse"))
					{
						if(ExpectedResult!=null)
						{
							Content = "'To verify webservice Response'";
							ExpRes = "Value from the webservice response should be '"+ExpectedResult+"' displayed.";
							ActRes = "Value from the webservice responce is not '"+ActualResult+"'displayed.";
						}
						
					}
				 else if(expprop.equalsIgnoreCase("verifyBalanceValues"))
					{
						
						/*if((Expected.actual==null)&&(ActualResult.equalsIgnoreCase("null"))){
							Content = "verify is Balance is Displayed";
							ExpRes = "Expected value is Should be Displayed";
							ActRes = "expected and Actual values Not Displayed";
						}*/
						
						Content = "To verify"+" "+expRefName+" "+"'"+Expected.actual+"'"+"value is Not Displayed";
						ExpRes = expRefName+" "+"'"+Expected.expVal1+"'"+" "+"Expected value is Not Displayed";
						ActRes = expRefName+" "+"'"+ActualResult+"'"+" "+"Actual value is Displayed";
				
					}
				 else if(expprop.equalsIgnoreCase("verifyItemsinComboBox"))
				{
					Content = "To verify '"+ExpectedResult+"' text is present inside the combobox";
					ExpRes = "'"+ExpectedResult+"' text should be present inside the combobox";
					ActRes = "'"+ExpectedResult+"' text is not present inside the combobox";
				}
				 else if(expprop.equalsIgnoreCase("verifyItemsNotInComboBox"))
				{
					Content = "To verify"+"'"+expVal1+"'"+"text is not present inside the combobox";
					ExpRes = "'"+expVal1+"'"+"Value should not be present inside the combobox";
					ActRes = "'"+expVal1+"'"+"Value is present inside the combobox";
				}
				else if((expprop.equalsIgnoreCase("VerifyTitle"))||(expprop.equalsIgnoreCase("waitForTitle")))
				{
					Content = "To verify '"+ExpectedResult+"' title";
					ExpRes = "'"+ExpectedResult+"' title should be displayed";
					ActRes = "'"+ExpectedResult+"' title is not displayed";
				}
				else if (expprop.equalsIgnoreCase("verifyErrorMessage"))
				{
					Content = "To verify"+"'"+Expected.expVal1+"'"+"Value is Not present in Error Message";
					ExpRes = "'"+Expected.expVal1+"'"+"Error Message Not displayed with innertext : '"+actual+"'";
					ActRes = "'"+Expected.expVal1+"'"+"Value is Not Present"+"'"+actual+"'";
				}
				
				else if ((expprop.equalsIgnoreCase("verifyConfirmation"))||(expprop.equalsIgnoreCase("verifyAlert")))
				{
					Content = "To verify '"+ExpectedResult+"' text is present in "+expprop+" window";
					ExpRes = expprop+" window should be displayed with innertext : '"+ExpectedResult+"'";
					ActRes = expprop+" window is not displayed with innertext : '"+ActualResult+"'";
				}
				else if((expprop.equalsIgnoreCase("verifyElementPresent"))||(expprop.equalsIgnoreCase("waitForElementPresent")))
				{
					Content = "To verify '"+expRefName+"' element is present";
					ExpRes = "'"+expRefName+"' element should be present";
					ActRes = "'"+expRefName+"' element is not present";
				}
				else if((expprop.equalsIgnoreCase("verifyElementNotPresent"))||(expprop.equalsIgnoreCase("waitForElementNotPresent")))
				{
					Content = "To verify '"+expRefName+"' element is not present";
					ExpRes = "'"+expRefName+"' element should not present";
					ActRes = "'"+expRefName+"' element is present";
				}
				else if((expprop.equalsIgnoreCase("verifyChecked")))
				{
					Content = "To verify the state of '"+expRefName+"'";
					ExpRes = "'"+expRefName+"' element should be in '"+ExpectedResult+"' state";
					ActRes = "'"+expRefName+"' element is in '"+ActualResult+"' state";
				}
				else if((expprop.equalsIgnoreCase("verifySelectedValue")))
				{
					Content = "To verify '"+tempSelected+"' is selected in "+expRefName+" combobox";
					ExpRes = "'"+tempSelected+"' should be selected in "+expRefName+" combobox";
					ActRes = "'"+tempSelected+"' is not selected in "+expRefName+" combobox";
				}
				else if(expprop.equalsIgnoreCase("verifyTextNotPresent")||(expprop.equalsIgnoreCase("waitForTextNotPresent")) ||(expprop.equalsIgnoreCase("verifyTextNotExist")))
				{
					Content = "To verify '"+ExpectedResult+"' text is not present";
					ExpRes = "'"+ExpectedResult+"' text should not present";
					ActRes = "'"+ExpectedResult+"' text is present";
				}
				else if(expprop.equalsIgnoreCase("verifyValue")||(expprop.equalsIgnoreCase("waitForValue")))
				{
					Content = "To verify whether '"+ExpectedResult+"'  is displayed";
					ExpRes = "'"+ExpectedResult+"' value should displayed";
					ActRes = "'"+ExpectedResult+"' value is not displayed";
				}
				else if(expprop.equalsIgnoreCase("verifyTable")||(expprop.equalsIgnoreCase("waitForTable"))||(expprop.equalsIgnoreCase("verifyTextInTable")))
				{
				
					if(ExpectedResult!=null && tableValue!=null)
					{
						if(ExpectedResult.indexOf("get")==0){
						Content = "To verify '"+ExpectedResult+"' table value is displayed in "+tableValue[1]+" row "+tableValue[2]+" column"+" "+"in"+" "+expRefName;
						ExpRes = "'"+ExpectedResult+"' table value should displayed in "+tableValue[1]+" row "+tableValue[2]+" column"+" "+"in"+" "+expRefName;
						ActRes = "'"+Expected.actual+"' table value is not displayed in "+tableValue[1]+" row "+tableValue[2]+" column"+" "+"in"+" "+expRefName;
						}
					else{
					Content = "To verify '"+ExpectedResult+"' table value is displayed in "+tableValue[1]+" row "+tableValue[2]+" column"+" "+"in"+" "+expRefName;
					ExpRes = "'"+ExpectedResult+"' table value should displayed in "+tableValue[1]+" row "+tableValue[2]+" column"+" "+"in"+" "+expRefName;
					ActRes = "'"+Expected.actual+"' table value is not displayed in "+tableValue[1]+" row "+tableValue[2]+" column"+" "+"in"+" "+expRefName;
					}
					}
					else
					{
						Content = "To verify Expected Value In "+"'"+ExpectedResult+"'"+"table displayed";
						ExpRes = "'"+ExpectedResult+"'"+"table value should be displayed";
						ActRes = "'"+Expected.actual+"'"+"table value is not displayed";
						
					}
					
				}
				else if((expprop.equalsIgnoreCase("verifySelectedLabel")) || (expprop.equalsIgnoreCase("waitForSelectedLabel")))
				{
					Content = "To verify '"+expRefName+"' value is selected on "+expprop+"";
					ExpRes = "'"+expRefName+"' value should be selected on "+expprop+"";
					ActRes = "'"+expRefName+"' value is not selected on "+expprop+"";
				}
				else if((expprop.equalsIgnoreCase("waitForPopUp")))
				{
					Content = "'To wait for a popup to appear'";
					ExpRes = "'Popup should appear'";
					ActRes = "'Popup doesnot appear'";
				}
				else if((expprop.equalsIgnoreCase("verifyValueinDB")))
				{
					Content = "To verify whether the value '"+ExpectedResult +"' is Not present in the database";
					ExpRes =  "The value '"+ Expected.actual +"' should Not be present in the database";
					ActRes = "The value '"+valFromDB +"' is not present in the database";
				}
				else if((expprop.equalsIgnoreCase("verifyTableContent")))
				{
					Content = "To verify '"+ExpectedResult+"' table value is displayed";
					ExpRes = "'"+ExpectedResult+"' table value should displayed";
					ActRes = "'"+ExpectedResult+"' value is not displayed in the table";
				}
				else if((expprop.equalsIgnoreCase("verifyTableSearch")))
				{
					Content = "verify value In"+"'"+expRefName+"'";
					ExpRes = "Expected value In Table"+"'"+Expected.expVal1+"'";
					ActRes = "'Actual value getting in"+"'"+expRefName+"'"+"Value is"+"'"+actual+"'";
				}
				else if((expprop.equalsIgnoreCase("verifyMailAlert")))
				{
					Content = "To check weather the mail has been sent to the user '"+Configure.userName+"'";
					ExpRes = "Mail should be sent to the user '"+Configure.userName+"'";
					ActRes = "'"+readEmails.errmsg+"'";
				}
				else if((expprop.equalsIgnoreCase("verifyApplicationValues")))
				{
					Content = "'To verify two values from application'";
					ExpRes = "'Both the application values should be equal'";
					ActRes = "Application values '"+ExpectedResult+"' and '"+ActualResult+"' are compared and found notequal" ;
				}
				else if((expprop.equalsIgnoreCase("verifyMaxLength")))
				{
					Content = "To verify maximun length of '"+expRefName+"' field";
					ExpRes = "'"+expRefName+"' has field length of "+ExpectedResult+" characters";
					ActRes = "'"+expRefName+"' has field length of "+ActualResult+" characters";
				}
				else if((expprop.equalsIgnoreCase("verifyValueinExcel"))||(expprop.equalsIgnoreCase("verifyValueinPDF")))
				{
					Content = "To verify whether '"+ExpectedResult+"'  text is present in the file "+Navigate.filePath;
					ExpRes = "'"+ExpectedResult+"' text value should be present in the file "+Navigate.filePath;
					ActRes = "'"+ExpectedResult+"' text value is not present in the file "+Navigate.filePath;
				}
				else if((expprop.equalsIgnoreCase("verifyFromSimulator"))||(expprop.equalsIgnoreCase("verifyFromAtmWebSimulator"))||(expprop.equalsIgnoreCase("verifyFromWebSimulator"))||(expprop.equalsIgnoreCase("verifyFromUSSDSimulator"))||(expprop.equalsIgnoreCase("verifyFromBankSimulator")))
				{
					if(ExpectedResult!=null)
					{
					if(ExpectedResult.indexOf("get")==0){
						Content = "To verify  '"+expValForResults +"' from simulator";
						ExpRes =  "Value of the '"+expValForResults +"' from the simulator should be "+ExpectedResult;
						ActRes = "Value of the'"+expValForResults +"' from the simulator is "+ActualResult;
					}
					else{
					Content = "To verify '"+expRefName+"' from simulator";
					ExpRes = "Value of the '"+expRefName+"' from the simulator should be "+ExpectedResult;
					ActRes = "Value of the '"+expRefName+"' from the simulator is "+ActualResult;
					}	}
				
				}
				else if ((expprop.equalsIgnoreCase("VerifyTableDatatext")))
				{
					Content = "To verify the state of '" + expRefName + "'";
					ExpRes = "'" + expRefName + "' element should be in '"
					+ ExpectedResult + "' state";
					ActRes = "'" + expRefName + "' element is in '"
					+ ActualResult + "' state";
				}else if ((expprop.equalsIgnoreCase("verifyElementInWebTable")))
				{
					Content = "To verify '"+ExpectedResult+"' element is displayed";
					ExpRes = "'"+ExpectedResult+"' element  should not be displayed";
					ActRes = "'"+Expected.actual+"' element is not displayed";
				}
			//}
			   }
			Result.XMLWriter(id, StepNode, ActRes, Content, ExpRes, StepPath, StepRes, StepTime);
		}		
		}
}//Class XMLResultsModifier