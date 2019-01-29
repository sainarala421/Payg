package SeleniumTestAutomation;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PerformActions extends Navigate{
	static String randomValue;
	public static WebElement element;
	public static WebDriverWait wait; 
	static boolean status=false;
	public static boolean clickAndWait(WebDriver driver, String typeOflocator,
			String sObjectName, String sValue) throws IOException,ElementNotVisibleException {
		   wait = new WebDriverWait(driver, 10);
		   System.out.println("typeoflocator "+typeOflocator);
		   System.out.println("sObjectName "+sObjectName);
		   System.out.println("sValue "+sValue);
		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{
				try{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.name(sObjectName)));
				//long inTime=System.currentTimeMillis();
				element=driver.findElement(By.name(sObjectName));
				//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				

				long outTime=System.currentTimeMillis();
				
				//long totalTime;
				//totalTime=(outTime-inTime);
                //SeleniumFW.APPLICATION_LOGS.info("Getting Total Time Take Click and wait-->"+totalTime);
				if(element.isDisplayed()==true){
					element.click();
					driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}
				}catch(Exception e){
					e.printStackTrace();
					status=false;
					
				}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{
try{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.id(sObjectName)));
				element=driver.findElement(By.id(sObjectName));
				//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				if(element.isDisplayed()==true){
					element.click();
					driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}
           }catch (Exception e) {
	        e.printStackTrace();
	        status=false;
	// TODO: handle exception
}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("CSS"))
			{
try{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(sObjectName)));
				element=driver.findElement(By.cssSelector(sObjectName));
				//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				if(element.isDisplayed()==true){
					element.click();
					driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
					status=true;
				}
				else{
					status=false;
	
				}
}catch (Exception e) {
	e.printStackTrace();
	// TODO: handle exception
}
				return status;
			}	


			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				try{
				Thread.sleep(100);
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sObjectName)));
				element=driver.findElement(By.xpath(sObjectName));
				if(element.isDisplayed()==true){
					element.click();
					driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
					status=true;
				}
				else{
					status=false;
				}
			} catch(Exception e){
				e.printStackTrace();
				status=false;
						        }
				return status;
				
			}
			else if(typeOflocator.equalsIgnoreCase("link"))
			{
				try{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(sObjectName)));
				//By.ByPartialLinkText
				element=driver.findElement(By.linkText(sObjectName));
				//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				if(element.isDisplayed()==true){
					element.click();
					driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
					
					status=true;
				}
				else{
					status=false;
				}
				}catch(Exception e){
					e.printStackTrace();
					status=false;
				}
				return status;
			}

		}catch (Exception e) {
			e.printStackTrace();
			SeleniumFW.APPLICATION_LOGS.error("Getting Exceoption"+e);
		}
		return status;
	}
	public static boolean SearchDropDown(WebDriver driver, String typeOflocator,
			String sObjectName, String sValue)throws Exception{
			//element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sObjectName)));
			
		
		element=driver.findElement(By.xpath(sObjectName));
		
		
		//WebElement element = driver.findElement(By.linkText("Product Category"));
		/*Actions action = new Actions(driver);
		
		action.moveToElement(element).perform();
		
		 WebElement subElement = driver.findElement(By.xpath(sObjectName));
		 action.moveToElement(subElement);
		 action.click();
		 action.perform();
		 status=true;*/
		if(element.isDisplayed()==true){
				     // driver.switchTo().activeElement().findElement(By.xpath(sObjectName));
				      //driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				      //element.clear();
				      //driver.switchTo().activeElement().
				      driver.switchTo().activeElement().findElement(By.xpath(sObjectName));
				      System.out.println("ssdfsRaja--"+sValue);
				      element.sendKeys(sValue);
				      Thread.sleep(2000);
				     // driver.switchTo().activeElement().findElement(By.xpath(sObjectName));
				      //element.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
				   
				       element.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
				       Thread.sleep(2000);
				       driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				        //Robot robot = new Robot();
				       //robot.keyPress(KeyEvent.VK_DOWN);
				       //robot.keyRelease(KeyEvent.VK_DOWN);
				      //driver.switchTo().activeElement().sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
				     //driver.findElement(By.xpath(sObjectName)).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
				    //driver.switchTo().activeElement().sendKeys(Keys.ENTER);
				    //driver.findElement(By.xpath(sObjectName)).sendKeys(Keys.ENTER);
				   //driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				  // Robot robot = new Robot();
				   				   //robot.keyPress(KeyEvent.VK_ENTER);
			                       //robot.keyRelease(KeyEvent.VK_ENTER);
				  // driver.switchTo().activeElement().sendKeys(Keys.ENTER);
				   //driver.findElement(By.xpath(sObjectName)).sendKeys(Keys.ENTER);
				   //driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			       
				      
				      
				      
				      
				      
				      
				      
				      status=true;
				
			}else{

				status=false;
			}
				
			
			
			return status;
			
			
		}
	
	public static boolean ClickActionUsingMouse(WebDriver driver, String typeOflocator,
			String sObjectName, String sValue)throws Exception{
			//element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sObjectName)));
			
		
		element=driver.findElement(By.xpath(sObjectName));
		
		
		//WebElement element = driver.findElement(By.linkText("Product Category"));
		
		if(element.isDisplayed()==true){
			Actions action = new Actions(driver);
			Thread.sleep(1000);
			action.moveToElement(element).perform();
			Thread.sleep(1000);
			WebElement subElement = driver.findElement(By.xpath(sObjectName));
			 action.moveToElement(subElement);
			 action.click();
				Thread.sleep(1000);
			 action.perform();
			 status=true;
		}
		else{

				status=false;
			}
				
			
			
			return status;
			
			
		}
	
	
	
	public static boolean typeinEditbox(WebDriver driver, String typeOflocator,
			String sObjectName, String sValue) throws Exception {

		System.out.println("typeoflocator in type "+typeOflocator);
		System.out.println("sObjectName "+sObjectName);
		System.out.println("sValue "+sValue);

		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{try{
				//element = wait.until(ExpectedConditions.elementToBeClickable(By.name(sObjectName)));
				//element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(sObjectName)));
				long inTime=System.currentTimeMillis();
				element=driver.findElement(By.name(sObjectName));
                 long outTime=System.currentTimeMillis();
				
				long totalTime;
				totalTime=(outTime-inTime);
                SeleniumFW.APPLICATION_LOGS.info("Getting Total Time Take Type-->"+totalTime);
				if(element.isDisplayed()==true){
					element.clear();
					Thread.sleep(50);
					element.sendKeys(sValue);
					driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}
			}catch (Exception e) {
			     e.printStackTrace();
			     status=false;
				// TODO: handle exception
			}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{
try{
				//element = wait.until(ExpectedConditions.elementToBeClickable(By.id(sObjectName)));
				//element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(sObjectName)));
				element=driver.findElement(By.id(sObjectName));
				if(element.isDisplayed()==true){
					element.clear();
					element.sendKeys(sValue);
					driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}
}catch (Exception e) {
	e.printStackTrace();
	status=false;
	// TODO: handle exception
}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				//element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sObjectName)));
			try{	
				//element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sObjectName)));
				
				
				element=driver.findElement(By.xpath(sObjectName));
				if(element.isDisplayed()==true){
					element.clear();
					element.sendKeys(sValue);
					driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}
			}catch (Exception e) {
				// TODO: handle exception
			e.printStackTrace();
			status=false;
			}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("CSS"))
			{try{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(sObjectName)));
				element=driver.findElement(By.cssSelector(sObjectName));
				if(element.isDisplayed()==true){
					element.clear();
					element.sendKeys(sValue);
					driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				status=false;
			}
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status=false;
			SeleniumFW.APPLICATION_LOGS.error("Getting Exceoption"+e);
		}
		return status;

	}

	public static boolean selectAndWaitDropdown(WebDriver driver, String typeOflocator,
			String sObjectName, String sValue) throws Exception{
		System.out.println("dropdown typeoflocator "+typeOflocator);
		System.out.println("dropdown sObjectName "+sObjectName);
		System.out.println("dropdown sValue "+sValue);
		List  <WebElement>  dd=null;
		Select select;
		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{
				try{
				long inTime=System.currentTimeMillis();
				
				element=driver.findElement(By.name(sObjectName));
				
				long outTime=System.currentTimeMillis();
				
				long totalTime;
				totalTime=(outTime-inTime);
                SeleniumFW.APPLICATION_LOGS.info("Getting Total Time Take-->"+totalTime);
				select = new Select(element);
				if(element.isDisplayed()==true){
					//element.clear();
					//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					select.selectByVisibleText(sValue);
					driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
					status=true;

				}
				else
				{
					status=false;
				}
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					status=false;
				}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{try{
				element=driver.findElement(By.id(sObjectName));
				select = new Select(element);
				if(element.isDisplayed()==true){
					//element.clear();
					//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					select.selectByVisibleText(sValue);
					driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
					status=true;

				}
				else
				{
					status=false;
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				status=false;
			}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{try{
				long inTime=System.currentTimeMillis();
				Thread.sleep(300);
				element=driver.findElement(By.xpath(sObjectName));
                long outTime=System.currentTimeMillis();
				
				long totalTime;
				totalTime=(outTime-inTime);
                SeleniumFW.APPLICATION_LOGS.info("Getting Total Time Take-->"+totalTime);
				select = new Select(element);
				if(element.isDisplayed()==true){
					select.selectByVisibleText(sValue);
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					status=true;

				}
				else
				{
					status=false;
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				status=false;
			}
				return status;
			}

		} catch (Exception e) {
			e.printStackTrace();
			status=false;
			SeleniumFW.APPLICATION_LOGS.error("Getting Exceoption"+e);
		}
		return status;
	}
	public static void selectCheckbox(WebDriver driver, String typeOflocator,
			String sObjectName, String sValue)throws Exception {
		System.out.println("typeoflocator "+typeOflocator);
		System.out.println("sObjectName "+sObjectName);
		System.out.println("sValue "+sValue);
		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{try{
				driver.findElement(By.name(sObjectName)).click();
				driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				status=false;
			}
				
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{try{
				driver.findElement(By.id(sObjectName)).click();
				driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);

			}catch (Exception e) {
				// TODO: handle exception
			     e.printStackTrace();
			}
			}
			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				try{
				driver.findElement(By.xpath(sObjectName)).click();
				driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);

				}
				catch (Exception e) {
					// TODO: handle exception
				e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status=false;
			SeleniumFW.APPLICATION_LOGS.error("Getting Exceoption"+e);
		}

	}

	public static String getLocator(String sObjectName) throws Exception {
		String locator=null;
		try {
			if((sObjectName.contains("="))&&(sObjectName.contains("//")==false) && (sObjectName.contains("css=")==false))
			{
				System.out.println("In fist if = condition");
				String str1 = sObjectName;
				//sObjectName = str1.split("=")[1];
				locator = str1.split("=")[0];
				return locator;
			}
			else if(sObjectName.indexOf("//")==0)
			{
				System.out.println("In XXXXXXPath");
				locator = "xpath";
				return locator;
			}
			else if(sObjectName.contains("link="))
			{
				locator ="linkText";
				return locator;
			}
			else
			{
				locator = "CSS";
				return locator;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return locator;
	}
	public static boolean click(WebDriver driver, String typeOflocator,
			String sObjectName, String sValue) throws IOException,ElementNotVisibleException {

		wait = new WebDriverWait(driver, 5);
		System.out.println("typeoflocator "+typeOflocator);
		System.out.println("sObjectName "+sObjectName);
		System.out.println("sValue "+sValue);
		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{
				try{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.name(sObjectName)));
				if(isElementPresent(By.name(sObjectName))){
					//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
					element=driver.findElement(By.name(sObjectName));
					if(element.isDisplayed()==true){
						element.click();
						//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
						 Thread.sleep(500);
						 status=true;
					}
				}
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					status=false;
				}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{try{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.id(sObjectName)));
				if(isElementPresent(By.id(sObjectName))){
					//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
					element=driver.findElement(By.id(sObjectName));
					if(element.isDisplayed()==true){
						element.click();
						//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					}
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				status=false;
			}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("CSS"))
			{
				try{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(sObjectName)));
				if(isElementPresent(By.cssSelector(sObjectName))){
					//driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
					element=driver.findElement(By.cssSelector(sObjectName));
					if(element.isDisplayed()==true){
						element.click();
						status=true;
						//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					}
				}
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					status=false;
				}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				try{
				Thread.sleep(500);
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sObjectName)));
				if(isElementPresent(By.xpath(sObjectName))){
					//driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
					element=driver.findElement(By.xpath(sObjectName));
					if(element.isDisplayed()==true){
						element.click();
						//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
						 Thread.sleep(100);
					}
				}
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					status=false;
				}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("link"))
			{
				try{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(sObjectName)));
				if(isElementPresent(By.linkText(sObjectName))){
					driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
					element=driver.findElement(By.linkText(sObjectName));
					if(element.isDisplayed()==true){
						element.click();
						driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					}
				}
				}catch (Exception e) {
					e.printStackTrace();
					status=false;
					// TODO: handle exception
				}
				return status;
			}

		}catch (Exception e) {

			e.printStackTrace();
			SeleniumFW.APPLICATION_LOGS.error("Getting Exceoption"+e);
		}
		return  status;

	}
	public static boolean selectDropdown(WebDriver driver, String typeOflocator,
			String sObjectName, String sValue) throws Exception{
		    List  <WebElement>  dd=null;
		    Select select;
		    try {
			if(typeOflocator.equalsIgnoreCase("name")){
				try{
				long inTime=System.currentTimeMillis();
				element=driver.findElement(By.name(sObjectName));
                 long outTime=System.currentTimeMillis();
				
				long totalTime;
				totalTime=(outTime-inTime);
                SeleniumFW.APPLICATION_LOGS.info("Getting Total Time Take OnlyselectDropdown-->"+totalTime);
				select = new Select(element);
				if(element.isDisplayed()==true){
					//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					select.selectByVisibleText(sValue);
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					status=false;
				}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{
				try{
				element=driver.findElement(By.id(sObjectName));
				select = new Select(element);
				if(element.isDisplayed()==true){
					//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					select.selectByVisibleText(sValue);
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				status=false;
			}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{
              try{
				element=driver.findElement(By.xpath(sObjectName));
				select = new Select(element);
				if(element.isDisplayed()==true){
					//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					select.selectByVisibleText(sValue);
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}
           }catch (Exception e) {
	// TODO: handle exception
            e.printStackTrace();
            status=false;
                        }
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status=false;
			SeleniumFW.APPLICATION_LOGS.error("Getting Exceoption"+e);
		}
		return status;
	}
	public static String  radomSelect(WebDriver driver, String typeOflocator,
			String sObjectName, int index) throws Exception{
		System.out.println("dropdown typeoflocator "+typeOflocator);
		System.out.println("dropdown sObjectName "+sObjectName);
		System.out.println("dropdown sValue "+index);
		List  <WebElement>  dd=null;
		Select select;		
		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{
				select = new Select(driver.findElement( By.name(sObjectName)));
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				select.selectByIndex(index);				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				//dd = driver.findElement(By.name(sObjectName)).findElements(By.tagName("option"));
				randomValue=select.getFirstSelectedOption().getText();
				return randomValue;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{
				select = new Select(driver.findElement( By.id(sObjectName)));
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				select.selectByIndex(index);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				randomValue=select.getFirstSelectedOption().getText();
				return randomValue;
				//dd = driver.findElement(By.id(sObjectName)).findElements(By.tagName("option"));
			}else if(typeOflocator.equalsIgnoreCase("CSS"))
			{
				select = new Select(driver.findElement( By.cssSelector(sObjectName)));
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				select.selectByIndex(index);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				randomValue=select.getFirstSelectedOption().getText();
				return randomValue;
			}
			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				System.out.println("In XXXXXXPATH SSs");
				System.out.println("sObject Name "+sObjectName);
				select = new Select(driver.findElement( By.xpath(sObjectName)));
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				select.selectByIndex(index);
				//randomValue = select.getFirstSelectedOption().getText();
				randomValue=select.getFirstSelectedOption().getText();
				System.out.println("After typing");
				return randomValue;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			SeleniumFW.APPLICATION_LOGS.error("Getting Exceoption"+e);
		}
		return randomValue;
	}
	public static int getSelectedOptions(WebDriver driver, String typeOflocator,
			String sObjectName) throws Exception{
		System.out.println("dropdown typeoflocator "+typeOflocator);
		System.out.println("dropdown sObjectName "+sObjectName);
		//System.out.println("dropdown sValue "+index);
		List  <WebElement>  dd=null;
		Select select;
		int count = 0;
		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{
				select = new Select(driver.findElement( By.name(sObjectName)));
				count= select.getOptions().size();
				
				count= select.getOptions().size();
				
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{
				select = new Select(driver.findElement( By.id(sObjectName)));
				count= select.getOptions().size();
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			}
			else if(typeOflocator.equalsIgnoreCase("CSS"))
			{
				select = new Select(driver.findElement( By.cssSelector(sObjectName)));
				count= select.getOptions().size();
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

			}
			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				System.out.println("In XXXXXXPATH SSs");
				System.out.println("sObject Name "+sObjectName);
				select = new Select(driver.findElement( By.xpath(sObjectName)));
				count= select.getOptions().size();
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			SeleniumFW.APPLICATION_LOGS.error("Getting Exceoption"+e);
		}
		return count;
	}

	public static boolean isChecked(WebDriver driver, String typeOflocator,
			String sObjectName, String sValue) throws IOException,ElementNotVisibleException {
		wait = new WebDriverWait(driver, 10);
		System.out.println("typeoflocator "+typeOflocator);
		System.out.println("sObjectName "+sObjectName);
		System.out.println("sValue "+sValue);
		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{
				try{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.name(sObjectName)));
				element=driver.findElement(By.name(sObjectName));
				
				
				if(element.isSelected()==false){
					element.click();
					driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=true;
				}
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					status=false;
				}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{
try{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.id(sObjectName)));
				element=driver.findElement(By.id(sObjectName));
				if(element.isSelected()==false){
					element.click();
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=true;
				}
}catch (Exception e) {
	e.printStackTrace();
	status=false;
	// TODO: handle exception
}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("CSS"))
			{try{

				element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(sObjectName)));
				element=driver.findElement(By.cssSelector(sObjectName));
				if(element.isSelected()==false){
					element.click();
					driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
					status=true;
				}
				else{
					status=true;
				}
			}catch (Exception e) {
				e.printStackTrace();
				status=false;
				// TODO: handle exception
			}
				return status;
			}	


			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				try{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sObjectName)));
				element=driver.findElement(By.xpath(sObjectName));
				if(element.isSelected()==false){
					element.click();
					driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
					status=true;
				}
				else{
					status=true;
				}
				}catch (Exception e) {
					e.printStackTrace();
					status=false;
					// TODO: handle exception
				}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("link"))
			{try{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(sObjectName)));
				element=driver.findElement(By.linkText(sObjectName));
				if(element.isSelected()==false){
					element.click();
					driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
					status=true;
				}
				else{
					status=true;
				}
			}catch (Exception e) {
				e.printStackTrace();
				status=false;// TODO: handle exception
			}
				return status;

			}

		}catch (Exception e) {
			e.printStackTrace();
			SeleniumFW.APPLICATION_LOGS.error("Getting Exceoption"+e);
		}
		return status;
	}

	public static boolean removeSelection(WebDriver driver, String typeOflocator,
			String sObjectName, String sValue) throws Exception{
		System.out.println("dropdown typeoflocator "+typeOflocator);
		System.out.println("dropdown sObjectName "+sObjectName);
		System.out.println("dropdown sValue "+sValue);
		List  <WebElement>  dd=null;
		Select select;
		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{
				try{
				element=driver.findElement(By.name(sObjectName));
				select = new Select(element);
				if(element.isDisplayed()==true){
					//element.clear();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					select.deselectByVisibleText(sValue);
					//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					status=true;

				}
				else
				{
					status=false;
				}
				}catch (Exception e) {
					e.printStackTrace();
					status=false;
					// TODO: handle exception
				}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{
				if(isElementPresent(By.id(sObjectName))){
					try{
					element=driver.findElement(By.id(sObjectName));
					select = new Select(element);
					if(element.isDisplayed()==true){
						//element.clear();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
						select.deselectByVisibleText(sValue);
						//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
						status=true;

					}
					else
					{
						status=false;
					}
					}catch (Exception e) {
						e.printStackTrace();
						status=false;
						// TODO: handle exception
					}
					return status;
				}
			}
				else if(typeOflocator.equalsIgnoreCase("xpath"))
				{
					if(isElementPresent(By.xpath(sObjectName))){
						try{
						element=driver.findElement(By.xpath(sObjectName));
						select = new Select(element);
						if(element.isDisplayed()==true){
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
							select.deselectByVisibleText(sValue);
							//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
							status=true;

						}
						else
						{
							status=false;
						}
						}catch (Exception e) {
							e.printStackTrace();
							status=false;
							// TODO: handle exception
						}
						return status;
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			SeleniumFW.APPLICATION_LOGS.error("Getting Exceoption"+e);
			status=false;
		}
		return status;
	}
	public static boolean addSelection(WebDriver driver, String typeOflocator,
			String sObjectName, String sValue) throws Exception{
		System.out.println("dropdown typeoflocator "+typeOflocator);
		System.out.println("dropdown sObjectName "+sObjectName);
		System.out.println("dropdown sValue "+sValue);
		List  <WebElement>  dd=null;
		Select select;
		try 
		{
			if(typeOflocator.equalsIgnoreCase("name"))
		{
				try{
			if(isElementPresent(By.name(sObjectName)))
				element=driver.findElement(By.name(sObjectName));
				select = new Select(element);
				if(element.isDisplayed()==true){
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					select.selectByVisibleText(sValue);
					//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					status=false;
				}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{try{
				if(isElementPresent(By.id(sObjectName))){
					element=driver.findElement(By.id(sObjectName));
					select = new Select(element);
					if(element.isDisplayed()==true){
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
						select.selectByVisibleText(sValue);
						//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
						status=true;

					}
					else
					{
						status=false;
					}
					
				}
			}catch (Exception e) {
				e.printStackTrace();
				status=false;
				// TODO: handle exception
			}
				return status;
			}
				else if(typeOflocator.equalsIgnoreCase("xpath"))
				{try{
					if(isElementPresent(By.xpath(sObjectName))){
						element=driver.findElement(By.xpath(sObjectName));
						select = new Select(element);
						if(element.isDisplayed()==true){
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
							select.selectByVisibleText(sValue);
							//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
							status=true;

						}
						else
						{
							status=false;
						}
						
					}
				}catch (Exception e) {
					e.printStackTrace();
					status=false;
					// TODO: handle exception
				}
					return status;
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			SeleniumFW.APPLICATION_LOGS.error("Getting Exceoption"+e);
		}
		return status;
	}
	public static boolean mouseOver(WebDriver driver, String typeOflocator,
			String sObjectName, String sValue) throws IOException,ElementNotVisibleException {
		wait = new WebDriverWait(driver, 10);
		Actions action=new Actions(driver);
		Actions MouseOver;
		System.out.println("typeoflocator "+typeOflocator);
		System.out.println("sObjectName "+sObjectName);
		System.out.println("sValue "+sValue);
		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.name(sObjectName)));				
				element=driver.findElement(By.name(sObjectName));
				if(element.isDisplayed()==true){
					MouseOver=(Actions) action.moveToElement(webElement).build();
					MouseOver.perform();
					driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}

				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{

				element = wait.until(ExpectedConditions.elementToBeClickable(By.id(sObjectName)));
				element=driver.findElement(By.id(sObjectName));
				if(element.isDisplayed()==true){
					MouseOver=(Actions) action.moveToElement(webElement).build();
					MouseOver.perform();
					driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("CSS"))
			{

				element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(sObjectName)));
				element=driver.findElement(By.cssSelector(sObjectName));
				if(element.isDisplayed()==true){
					MouseOver=(Actions) action.moveToElement(webElement).build();
					MouseOver.perform();
					driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
					status=true;
				}
				else{
					status=false;
				}
				return status;
			}	


			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sObjectName)));
				element=driver.findElement(By.xpath(sObjectName));
				if(element.isDisplayed()==true){
					MouseOver=(Actions) action.moveToElement(webElement).build();
					MouseOver.perform();
					driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
					status=true;
				}
				else{
					status=false;
				}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("link"))
			{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(sObjectName)));
				element=driver.findElement(By.linkText(sObjectName));
				if(element.isDisplayed()==true){
					MouseOver=(Actions) action.moveToElement(webElement).build();
					MouseOver.perform();
					driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
					status=true;
				}
				else{
					status=false;
				}
				return status;
			}

		}catch (Exception e) {
			e.printStackTrace();
			SeleniumFW.APPLICATION_LOGS.error("Getting Exceoption"+e);
		}
		return status;
	}
	public static boolean typeAndWait(WebDriver driver, String typeOflocator,
			String sObjectName, String sValue) throws Exception {

		System.out.println("typeoflocator in type "+typeOflocator);
		System.out.println("sObjectName "+sObjectName);
		System.out.println("sValue "+sValue);

		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{try{
				//element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(sObjectName)));
				element=driver.findElement(By.name(sObjectName));
				if(element.isDisplayed()==true){
					element.clear();
					element.sendKeys(sValue);
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					driver.switchTo().activeElement().sendKeys(Keys.TAB);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}
			}catch (Exception e) {
				e.printStackTrace();
				status=false;
				// TODO: handle exception
			}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{
try{
				//element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(sObjectName)));
				element=driver.findElement(By.id(sObjectName));
				if(element.isDisplayed()==true){
					element.clear();
					element.sendKeys(sValue);
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					driver.switchTo().activeElement().sendKeys(Keys.TAB);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}
}catch (Exception e) {
	// TODO: handle exception
e.printStackTrace();
}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{try{
				//element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sObjectName)));
				element=driver.findElement(By.xpath(sObjectName));
				if(element.isDisplayed()==true){
					element.clear();
					Thread.sleep(500);
					element.sendKeys(sValue,Keys.TAB);
					//driver.switchTo().activeElement().sendKeys(Keys.TAB);
					
					driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
					//driver.navigate().refresh();
					//driver.switchTo().activeElement().sendKeys(Keys.TAB);
					//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}}catch (Exception e) {
					e.printStackTrace();
					status=false;
					// TODO: handle exception
				}
				return status;
			}
			else if(typeOflocator.equalsIgnoreCase("CSS"))
			{try{
				element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(sObjectName)));
				element=driver.findElement(By.cssSelector(sObjectName));
				if(element.isDisplayed()==true){
					element.clear();
					element.sendKeys(sValue);
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					driver.switchTo().activeElement().sendKeys(Keys.TAB);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					status=true;
				}
				else
				{
					status=false;
				}
			}catch (Exception e) {
				e.printStackTrace();
				status=false;
				// TODO: handle exception
			}
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			SeleniumFW.APPLICATION_LOGS.error("Getting Exceoption"+e);
		}
		return status;

	}
	private static boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			SeleniumFW.APPLICATION_LOGS.error("Getting Exceoption"+e);
			return false;
		}
	}

	public static boolean getAttributeValue(WebDriver driver,
			String typeOflocator, String sObjectName, String sValue) {

		System.out.println("typeoflocator in type "+typeOflocator);
		System.out.println("sObjectName "+sObjectName);
		System.out.println("sValue "+sValue);
		String status="";
		boolean isReadOnly=false;

		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{
				status=driver.findElement(By.name(sObjectName)).getAttribute("readonly");
				System.out.println("attrValue "+status);
				if(status.equalsIgnoreCase("true"))
				{
					JavascriptExecutor jse = (JavascriptExecutor)driver;
					jse.executeScript("document.getElementsByName('"+sObjectName+"')[0].setAttribute('value', '"+sValue+"');");
					isReadOnly=true;
					return isReadOnly;
				}
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{

				status=driver.findElement(By.id(sObjectName)).getAttribute("readonly");
				System.out.println("attrValue "+status);
				if(status.equalsIgnoreCase("true"))
				{
					JavascriptExecutor jse = (JavascriptExecutor)driver;
					jse.executeScript("document.getElementsById('"+sObjectName+"')[0].setAttribute('value', '"+sValue+"');");
					isReadOnly=true;
					return isReadOnly;
				}

			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			SeleniumFW.APPLICATION_LOGS.error("Getting Exceoption"+e);
		}
		return isReadOnly;

	}

}
