package SeleniumTestAutomation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class StepValidateImplimentations {
	
	public static String returnValue;
	public static String getValueFromEditbox(WebDriver driver, String typeoflocator,
			String sObjectName, String sValue) {
		
		if((sObjectName.contains("="))&&((sObjectName.contains("//"))==false) && (sObjectName.contains("css=")==false))
		{
			String str = sObjectName;
			sObjectName = str.split("=")[1];
			typeoflocator = str.split("=")[0];
		}
		else if(sObjectName.indexOf("//")==0)
		{
			typeoflocator = "xpath";
		}
		else
		{
			typeoflocator = "CSS";
			sObjectName = sObjectName.substring(4, sObjectName.length());
		}
		
		if(typeoflocator.contains("name"))
		{
			returnValue = driver.findElement(By.name(sObjectName)).getAttribute("value");
		}
		else if(typeoflocator.contains("id"))
		{
			returnValue = driver.findElement(By.id(sObjectName)).getAttribute("value");
		}
		else if(typeoflocator.contains("xpath"))
		{
			returnValue = driver.findElement(By.xpath(sObjectName)).getAttribute("value");
		}
		else if(typeoflocator.contains("CSS"))
		{
			returnValue = driver.findElement(By.cssSelector(sObjectName)).getAttribute("value");
		}
		return returnValue;
	}
	public static String getValueFromDropdown(WebDriver driver,
			String typeOflocator, String sObjectName, String sValue) throws InterruptedException {
		WebElement  we=null;
		Select select = null;
		if((sObjectName.contains("="))&&(sObjectName.contains("//"))==false)
		{
			String str = sObjectName;
			sObjectName = str.split("=")[1];
			typeOflocator = str.split("=")[0];
		}
		else if(sObjectName.indexOf("//")==0)
		{
			typeOflocator = "xpath";
		}
		else
		{
			typeOflocator = "CSS";
		}
		if(typeOflocator.equalsIgnoreCase("name"))
		{			
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 select = new Select(driver.findElement( By.name(sObjectName)));
			 we = select.getFirstSelectedOption();
			  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 returnValue = we.getText();
			 System.out.println("storecombo---> " +returnValue);
			//we = driver.findElement(By.name(sObjectName)).findElements(By.tagName("option"));
		}
		else if(typeOflocator.equalsIgnoreCase("id"))
		{driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 select = new Select(driver.findElement( By.id(sObjectName)));
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			  we = select.getFirstSelectedOption();
			 returnValue = we.getText();
			 System.out.println("storecombo---> " +returnValue);
		}
		else if(typeOflocator.equalsIgnoreCase("xpath"))
		{
			//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Thread.sleep(500);
			select = new Select(driver.findElement( By.xpath(sObjectName)));
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			  we = select.getFirstSelectedOption();
				Thread.sleep(500);
			  returnValue = we.getText();
			 System.out.println("storecombo---> " +returnValue);;
		}
		return returnValue;
	}
	public static String getStoreSelectedValue(WebDriver driver, String typeoflocator,
			String sObjectName, String sValue) throws InterruptedException {
		
		if((sObjectName.contains("="))&& (sObjectName.contains("//")==false))
		{
			String str = sObjectName;
			sObjectName = str.split("=")[1];
			typeoflocator = str.split("=")[0];
		}
		else if(sObjectName.indexOf("//")==0)
		{
			typeoflocator = "xpath";
		}
		else
		{
			typeoflocator = "CSS";
		}
		if(typeoflocator.contains("xpath"))
		{
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Thread.sleep(100);
			returnValue = driver.findElement(By.xpath(sObjectName)).getText().trim();
						
		}
		return returnValue;
	}
	public static String getMaxlength(WebDriver driver, String typeOflocator,
			String expTarget, String expVal) {
		
		if((expTarget.contains("="))&&(expTarget.contains("//"))==false)
		{
			String str = expTarget;
			expTarget = str.split("=")[1];
			typeOflocator = str.split("=")[0];
		}
		else if(expTarget.indexOf("//")==0)
		{
			typeOflocator = "xpath";
		}
		else
		{
			typeOflocator = "CSS";
		}
		
		if(typeOflocator.contains("name"))
		{
			returnValue = driver.findElement(By.name(expTarget)).getAttribute("maxlength");
		}
		else if(typeOflocator.contains("id"))
		{
			returnValue = driver.findElement(By.id(expTarget)).getAttribute("maxlength");
		}
		else if(typeOflocator.contains("xpath"))
		{
			returnValue = driver.findElement(By.xpath(expTarget)).getAttribute("maxlength");
		}
		return returnValue;
	}
	
}
