package SeleniumTestAutomation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.*;

import javax.swing.text.TabableView;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lowagie.text.List;

public class ExpectedValidations extends Expected{
	public static WebElement element;
	public static WebDriverWait wait; 
	static boolean expStatus=false;

	public static boolean  verifyElementPresent(WebDriver driver, String typeOflocator,
			String expTarget, String expVal) throws IOException,ElementNotVisibleException {

		System.out.println("typeoflocator "+typeOflocator);
		System.out.println("sObjectName "+expTarget);
		System.out.println("sValue "+expVal);
		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{
				//element = wait.until(ExpectedConditions.elementToBeClickable(By.name(expTarget)));

				element=driver.findElement(By.name(expTarget));
				if(element.isDisplayed()==true){
					expStatus=true;
				}
				else
				{
					expStatus=false;
				}

				return expStatus;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{

				//element = wait.until(ExpectedConditions.elementToBeClickable(By.id(expTarget)));
				element=driver.findElement(By.id(expTarget));
				if(element.isDisplayed()==true){

					expStatus=true;
				}
				else
				{
					expStatus=false;
				}
				return expStatus;
			}
			else if(typeOflocator.equalsIgnoreCase("CSS"))
			{

				//element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(expTarget)));
				element=driver.findElement(By.cssSelector(expTarget));
				if(element.isDisplayed()==true){

					expStatus=true;
				}
				else{
					expStatus=false;
				}
				return expStatus;
			}	


			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				//element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(expTarget)));
				element=driver.findElement(By.xpath(expTarget));
				if(element.isDisplayed()==true){

					expStatus=true;
				}
				else{
					expStatus=false;
				}
				return expStatus;
			}
			else if(typeOflocator.equalsIgnoreCase("link"))
			{
				//element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(expTarget)));
				element=driver.findElement(By.linkText(expTarget));
				if(element.isDisplayed()==true){

					expStatus=true;
				}
				else{
					expStatus=false;
				}
				return expStatus;
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return expStatus;

	}
	public static boolean  verifyElementNotPresent(WebDriver driver, String typeOflocator,
			String expTarget, String expVal) throws IOException,ElementNotVisibleException {

		System.out.println("typeoflocator "+typeOflocator);
		System.out.println("sObjectName "+expTarget);
		System.out.println("sValue "+expVal);
		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{
				//element = wait.until(ExpectedConditions.elementToBeClickable(By.name(expTarget)));

				element=driver.findElement(By.name(expTarget));
				if(element.isDisplayed()==false){
					expStatus=true;
				}
				else
				{
					expStatus=false;
				}

				return expStatus;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{

				//element = wait.until(ExpectedConditions.elementToBeClickable(By.id(expTarget)));
				element=driver.findElement(By.id(expTarget));
				if(element.isDisplayed()==false){

					expStatus=true;
				}
				else
				{
					expStatus=false;
				}
				return expStatus;
			}
			else if(typeOflocator.equalsIgnoreCase("CSS"))
			{

				//element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(expTarget)));
				element=driver.findElement(By.cssSelector(expTarget));
				if(element.isDisplayed()==false){

					expStatus=true;
				}
				else{
					expStatus=false;
				}
				return expStatus;
			}	


			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				//element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(expTarget)));
				element=driver.findElement(By.xpath(expTarget));
				if(element.isDisplayed()==false){

					expStatus=true;
				}
				else{
					expStatus=false;
				}
				return expStatus;
			}
			else if(typeOflocator.equalsIgnoreCase("link"))
			{
				//element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(expTarget)));
				element=driver.findElement(By.linkText(expTarget));
				if(element.isDisplayed()==false){

					expStatus=true;
				}
				else{
					expStatus=false;
				}
				return expStatus;
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return expStatus;

	}

	public static boolean  verifyItemsinComboBox(WebDriver driver, String typeOflocator,
			String expTarget, String expVal) throws IOException,ElementNotVisibleException {
		Select select = null;
		java.util.List<WebElement>  dd=null;	
		System.out.println("typeoflocator "+typeOflocator);
		System.out.println("sObjectName "+expTarget);
		System.out.println("sValue "+expVal);
		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{
				//element = wait.until(ExpectedConditions.elementToBeSelected(By.name(expTarget)));

				element=driver.findElement(By.name(expTarget));
				if(element.isDisplayed()==true){

					select = new Select(element);
					dd=select.getOptions();
					for(int i=0;i<=dd.size();i++){
						WebElement item = dd.get(i);
						String ac=item.getText();
						if(ac.equalsIgnoreCase(expVal)){
							expStatus=true;
							break;

						}else
						{
							expStatus=false;
							continue;

						}

					}
				}
				return expStatus;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{
				//element = wait.until(ExpectedConditions.elementToBeSelected(By.name(expTarget)));

				element=driver.findElement(By.id(expTarget));
				if(element.isDisplayed()==true){

					select = new Select(element);
					dd=select.getOptions();
					for(int i=0;i<=dd.size();i++){
						WebElement item = dd.get(i);
						String ac=item.getText();
						if(ac.equalsIgnoreCase(expVal)){
							expStatus=true;
							break;

						}else
						{
							expStatus=false;
							continue;

						}

					}
				}


				return expStatus;
			}
			else if(typeOflocator.equalsIgnoreCase("CSS"))
			{
				//element = wait.until(ExpectedConditions.elementToBeSelected(By.name(expTarget)));

				element=driver.findElement(By.cssSelector(expTarget));
				if(element.isDisplayed()==true){

					select = new Select(element);
					dd=select.getOptions();
					for(int i=0;i<=dd.size();i++){
						WebElement item = dd.get(i);
						String ac=item.getText();
						if(ac.equalsIgnoreCase(expVal)){
							expStatus=true;
							break;

						}else
						{
							expStatus=false;
							continue;

						}

					}
				}


				return expStatus;
			}	


			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				//element = wait.until(ExpectedConditions.elementToBeSelected(By.name(expTarget)));

				element=driver.findElement(By.xpath(expTarget));
				if(element.isDisplayed()==true){

					select = new Select(element);
					dd=select.getOptions();
					for(int i=0;i<=dd.size();i++){
						WebElement item = dd.get(i);
						String ac=item.getText();
						if(ac.equalsIgnoreCase(expVal)){
							expStatus=true;
							break;

						}else
						{
							expStatus=false;
							continue;

						}

					}
				}


				return expStatus;
			}
			else if(typeOflocator.equalsIgnoreCase("link"))
			{
				//element = wait.until(ExpectedConditions.elementToBeSelected(By.name(expTarget)));

				element=driver.findElement(By.linkText(expTarget));
				if(element.isDisplayed()==true){

					select = new Select(element);
					dd=select.getOptions();
					for(int i=0;i<=dd.size();i++){
						WebElement item = dd.get(i);
						String ac=item.getText();
						if(ac.equalsIgnoreCase(expVal)){
							expStatus=true;
							break;

						}else
						{
							expStatus=false;
							continue;

						}

					}
				}


				return expStatus;
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return expStatus;

	}

	public static boolean  verifyItemsNotInComboBox(WebDriver driver, String typeOflocator,
			String expTarget, String expVal) throws IOException,ElementNotVisibleException {
		Select select = null;
		java.util.List<WebElement>  dd=null;	
		System.out.println("typeoflocator "+typeOflocator);
		System.out.println("sObjectName "+expTarget);
		System.out.println("sValue "+expVal);
		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{
				//element = wait.until(ExpectedConditions.elementToBeSelected(By.name(expTarget)));

				element=driver.findElement(By.name(expTarget));
				if(element.isDisplayed()==true){

					select = new Select(element);
					dd=select.getOptions();
					for(int i=0;i<=dd.size();i++){
						WebElement item = dd.get(i);
						String ac=item.getText();
						if(ac.equalsIgnoreCase(expVal)){
							expStatus=false;
							break;

						}else
						{
							expStatus=true;
							continue;

						}

					}
				}


				return expStatus;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{
				//element = wait.until(ExpectedConditions.elementToBeSelected(By.name(expTarget)));

				element=driver.findElement(By.id(expTarget));
				if(element.isDisplayed()==true){

					select = new Select(element);
					dd=select.getOptions();
					for(int i=0;i<=dd.size();i++){
						WebElement item = dd.get(i);
						String ac=item.getText();
						if(ac.equalsIgnoreCase(expVal)){
							expStatus=false;
							break;

						}else
						{
							expStatus=true;
							continue;

						}

					}
				}


				return expStatus;
			}
			else if(typeOflocator.equalsIgnoreCase("CSS"))
			{
				//element = wait.until(ExpectedConditions.elementToBeSelected(By.name(expTarget)));

				element=driver.findElement(By.cssSelector(expTarget));
				if(element.isDisplayed()==true){

					select = new Select(element);
					dd=select.getOptions();
					for(int i=0;i<=dd.size();i++){
						WebElement item = dd.get(i);
						String ac=item.getText();
						if(ac.equalsIgnoreCase(expVal)){
							expStatus=false;
							break;

						}else
						{
							expStatus=true;
							continue;

						}

					}
				}


				return expStatus;
			}	


			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				//element = wait.until(ExpectedConditions.elementToBeSelected(By.name(expTarget)));

				element=driver.findElement(By.xpath(expTarget));
				if(element.isDisplayed()==true){

					select = new Select(element);
					dd=select.getOptions();
					for(int i=0;i<=dd.size();i++){
						WebElement item = dd.get(i);
						String ac=item.getText();
						if(ac.equalsIgnoreCase(expVal)){
							expStatus=false;
							break;

						}else
						{
							expStatus=true;
							continue;

						}

					}
				}


				return expStatus;
			}
			else if(typeOflocator.equalsIgnoreCase("link"))
			{
				//element = wait.until(ExpectedConditions.elementToBeSelected(By.name(expTarget)));

				element=driver.findElement(By.linkText(expTarget));
				if(element.isDisplayed()==true){

					select = new Select(element);
					dd=select.getOptions();
					for(int i=0;i<=dd.size();i++){
						WebElement item = dd.get(i);
						String ac=item.getText();
						if(ac.equalsIgnoreCase(expVal)){
							expStatus=false;
							break;

						}else
						{
							expStatus=true;
							continue;

						}

					}
				}


				return expStatus;
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return expStatus;

	}

	public static String getTable(WebDriver driver, String typeOflocator,
			String expTarget, String expVal ,int rownum,int coloumNum) throws IOException,ElementNotVisibleException {
		String cellData="";
		int row,col;
		System.out.println("typeoflocator "+typeOflocator);
		System.out.println("expTarget "+expTarget);
		System.out.println("expVal "+expVal);
		
		try {
			if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				element=driver.findElement(By.xpath(expTarget+"/tbody/tr["+rownum+"]"+"/td["+coloumNum+"]"));////td[2]/table/tbody/tr[2]/td[2]/table/tbody
				if(element.isDisplayed()==true){
					cellData=element.getText();
					System.out.println("getting tableData-->" + cellData);
					
				}
				return cellData;
			}
			return cellData;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return cellData;

	}
	
	public static String getTableLabel(WebDriver driver, String typeOflocator,
			String expTarget, String expVal,int rowNum,int colNum) throws IOException,ElementNotVisibleException {
		String cellData="";
		int row,col;
		System.out.println("typeoflocator "+typeOflocator);
		System.out.println("expTarget "+expTarget);
		System.out.println("expVal "+expVal);
		try {
			if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				element=driver.findElement(By.xpath(expTarget+"/tbody/tr["+(rowNum-1)+"]"+"/td["+colNum+"]"));////td[2]/table/tbody/tr[2]/td[2]/table/tbody
				if(element.isDisplayed()==true){
					cellData=element.getText().toString();
					if(cellData.isEmpty()!=false){
						element=driver.findElement(By.xpath(expTarget+"/tbody/tr["+(rowNum-2)+"]"+"/td["+colNum+"]"));////td[2]/table/tbody/tr[2]/td[2]/table/tbody
						cellData=element.getText();
					}
					
					System.out.println("getting tableData-->"+cellData);
					//cellData=getCellData(row,col,element);
				}
				return cellData;
			}
			return cellData;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return cellData;

	}
	
	
	
	public static String  verifyTextPresent(WebDriver driver, String typeOflocator,
			String expTarget, String expVal) throws IOException,ElementNotVisibleException {

		System.out.println("typeoflocator "+typeOflocator);
		System.out.println("sObjectName "+expTarget);
		System.out.println("sValue "+expVal);
		String actText="";
		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{
				element=driver.findElement(By.name(expTarget));
				if(element.isDisplayed()==true){
				actText=element.getText();	
				}
				else
				{
					actText="No Text Present";
				}

				return actText;
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{
				element=driver.findElement(By.id(expTarget));
				if(element.isDisplayed()==true){

					actText=element.getText();	
				}
				else
				{
					actText="No Text Present";
				}

				return actText;
			}
			else if(typeOflocator.equalsIgnoreCase("CSS"))
			{
				element=driver.findElement(By.cssSelector(expTarget));
				if(element.isDisplayed()==true){
					actText=element.getText();	
				}
				else
				{
					actText="No Text Present";
				}

				return actText;
			}	


			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				element=driver.findElement(By.xpath(expTarget));
				if(element.isDisplayed()==true){

					actText=element.getText();	
				}
				else
				{
					actText="No Text Present";
				}

				return actText;
			}
			else if(typeOflocator.equalsIgnoreCase("link"))
			{
				element=driver.findElement(By.linkText(expTarget));
				if(element.isDisplayed()==true){

					actText=element.getText();	
				}
				else
				{
					actText="No Text Present";
				}

				return actText;
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return actText;

	}
	public static String  verifyTextNotPresent(WebDriver driver, String typeOflocator,
			String expTarget, String expVal) throws IOException,ElementNotVisibleException {

		System.out.println("typeoflocator "+typeOflocator);
		System.out.println("sObjectName "+expTarget);
		System.out.println("sValue "+expVal);
		String actText="";
		boolean expStatus=false;
		try {
			if(typeOflocator.equalsIgnoreCase("name"))
			{
				element=driver.findElement(By.name(expTarget));
				if(element.isDisplayed()==true)
				actText=element.getText();	
				
			}
			else if(typeOflocator.equalsIgnoreCase("id"))
			{
				element=driver.findElement(By.id(expTarget));
				if(element.isDisplayed()==true)
					actText=element.getText();	
			}
			else if(typeOflocator.equalsIgnoreCase("CSS"))
			{
				element=driver.findElement(By.cssSelector(expTarget));
				if(element.isDisplayed()==true)
					actText=element.getText();	
			}	


			else if(typeOflocator.equalsIgnoreCase("xpath"))
			{
				element=driver.findElement(By.xpath(expTarget));
				if(element.isDisplayed()==true)
					actText=element.getText();	
			}
			else if(typeOflocator.equalsIgnoreCase("link"))
			{
				element=driver.findElement(By.xpath(expTarget));
				if(element.isDisplayed()==true)
				actText=element.getText();	
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return actText;

	}
	public static String getCellData(int roww,int coll,WebElement table_element) 
	{
		
		System.out.println("row data-->"+roww);
		System.out.println("col Data-->"+coll);
		System.out.println("table element-->"+table_element);
		java.util.List<WebElement> tableRows = table_element.findElements(By.tagName("tr"));
		System.out.println("getting Number of Rows-->"+tableRows.size());
		WebElement currentRow = tableRows.get(roww);
		java.util.List<WebElement> tableCols = currentRow.findElements(By.tagName("td"));
		System.out.println("getting Number of Coloums-->"+tableCols.size());
		//System.out.println("gettingData"+tableCols.get(0).getText()+"  "+tableCols.get(1).getText()+" "+tableCols.get(2).getText());
		WebElement cell = tableCols.get(coll);
		System.out.println("getting text"+tableCols.get(coll).getText());
		String cellValue = cell.getText();
		return cellValue;
		
		
	}


}
