package SeleniumTestAutomation;

import java.io.File;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

//***********************************************************************************************
//Class for selenium operations
//***********************************************************************************************

public class Session 
{
	public static Selenium sel,sel1;
	public static SeleniumServer seleniumServer; 
	static String UserName1;
	static String Password1;
	static boolean test;
	public static WebDriver driver;
	public static WebElement webElement;
	//static int tt=2;
	public static int serverport = 4444;

	public static void setUp() throws Exception
	{  	
		
		if(Configure.browser.equalsIgnoreCase("Firefox"))
		{
			/*if (Configure.WEB_SERVICES.equalsIgnoreCase("webServices"))
			{
				return;
			}*/
			
			System.setProperty("webdriver.gecko.driver", "D:\\Ecuador\\PM_Ecuador_17_03_2015\\Drivers\\geckodriver.exe");
		@SuppressWarnings("unchecked")
		Map<String, Boolean> capbiltesmap=new HashedMap();
		capbiltesmap.put("takesScreenShot", true);
		capbiltesmap.put("cssSelectorsEnabled",true);
			
			DesiredCapabilities capabilites =new DesiredCapabilities(capbiltesmap);
			//capabilites.setCapability(capabilityName, value)
			
			driver = new FirefoxDriver(capabilites);
		
		
			/*DesiredCapabilities cap=new DesiredCapabilities();
			
			cap.setBrowserName("firefox");
			//cap.setVersion("19.0.2");
			cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			
			driver=new RemoteWebDriver(new URL("http://10.10.21.84:1111/wd/hub"),cap);*/
			
		
		}
		else if(Configure.browser.equalsIgnoreCase("iexplore"))
		{   
			File file = new File("D:/Ecuador/PM_Ecuador_17_03_2015/Drivers/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			@SuppressWarnings("unchecked")
			Map<String, Boolean> capbiltesmap=new HashedMap();
			capbiltesmap.put("ELEMENT_SCROLL_BEHAVIOR", true);
			capbiltesmap.put("takesScreenShot", true);
			capbiltesmap.put("REQUIRE_WINDOW_FOCUS", true);
			capbiltesmap.put("INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS", true);
			capbiltesmap.put("IE_SET_PROXY_BY_SERVER",true);
			capbiltesmap.put("NATIVE_EVENTS",false);
		    DesiredCapabilities capabilites =new DesiredCapabilities(capbiltesmap);
			driver = new InternetExplorerDriver(capabilites);

		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}
	public static void open(String sUrl) throws InterruptedException 
	{	
		/*if (Configure.WEB_SERVICES.equalsIgnoreCase("webServices"))
		{
			return;
		}*/
		driver.manage().window().maximize();
	}
	public static void authentication()
	{ 
		try
		{
			do
			{
				if ((driver.getTitle().indexOf("Certificate Error")==0) && (Configure.browser.equals("iexplore")))
				{
					driver.findElement(By.xpath("overridelink")).click();
					//sel.click("overridelink");
					Thread.sleep(5000);
				}
				Runtime r = Runtime.getRuntime();
				UserName1 = Navigate.strval[0];
				Password1 = Navigate.strval[1];
				String s = Configure.authenticationhandler +" "+UserName1+" "+Password1;
				r.exec(s);
				Thread.sleep(8000);
				test = false;	
			}
			while(sel.getTitle().indexOf("Certificate Error")==0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void tearDown() 
	{
		/*	sel.shutDownSeleniumServer();
		sel.stop();
		seleniumServer.stop(); */
		
		driver.close();
		driver.quit();
	}
	public static void deleteCookie()
	{
		driver.manage().deleteAllCookies();
	}

}//Class Session

