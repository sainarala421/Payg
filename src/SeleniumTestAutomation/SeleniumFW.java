package SeleniumTestAutomation;
import org.apache.log4j.Logger;
public class SeleniumFW

{
	public static final Logger SELENIUM_LOGS = Logger.getRootLogger();
	public static final Logger APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
	
	public static void main(String[] args) throws Exception
	{			
		Configure parserObj = new Configure();
		parserObj.configure();
		parserObj.locatorConfigure();
		FileFormatCheck checkfiles = new FileFormatCheck();
		checkfiles.checkxml(); 
		ObjectRepository objRepo = new ObjectRepository();
		objRepo.loadObjectDetails();
		Compare comp = new Compare();
		comp.compareXML();
		Result.XMLCreator();
		Session.setUp();
		Session.open(Configure.url);
		TestCase inputxml = new TestCase();
		inputxml.parseInputXML(); 
		Session.tearDown();	
	}	
	
}//Class SeleniumFW

