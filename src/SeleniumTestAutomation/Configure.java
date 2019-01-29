package SeleniumTestAutomation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class Configure 
{
	static String url, pinFilePath, fileUploadHandler, passwordFilePath, server, userName, password, sid;
	static String htmlResultsFile, PDFDownloadHandler, ExcelDownloadHandler, Recovery;
	public static String browser,authenticationhandler, inputFilePath, outputFilePath;
	static String buildversion,resultlogpath,resultlog, simulatorIP, simulatorPort,locatorsPath,ussd_url,ErrorCodes;
	public static String inputfile,objectfile, configFilePath,SirCreditEndPoint,couponMangementendPoint,couponMangementNameSpace,SirCreditNameSpace,RecargaServiceEndPoint,RecargaServiceNameSpace,IVRAESKeyValue;
	public static String WEB_SERVICE_IP,WEB_SERVICE_PORT,WEB_SERVICES;
	public static Properties properties;
	public static Properties propertiesLocatores,propertiesErrorCodes;
	public void configure() 
	{
		try 
		{
			properties = new Properties();
			//configFilePath="D:/PaymentManager/TestAutomation/Modules/RDBN/config.properties";
			//configFilePath="D:/PM_0.3/GTQ/2.6.0.0_USSD/config.properties";
			//configFilePath="D:/PM_0.3/USSD/config.properties";
			//configFilePath="D:/WorkSpace/MXPM2.X/MxTestScripts/config.properties";
			configFilePath="D:/Ecuador/PM_Ecuador_17_03_2015/EcuadorTestScripts/config.properties";
			
			//D:\paymentMangerPos\Channels\Channels
			
			properties.load(new FileInputStream(configFilePath));
			url = properties.getProperty("url");
			browser = properties.getProperty("browser");
			inputfile = properties.getProperty("inputfile");
			objectfile = properties.getProperty("objectfile");
			resultlog = properties.getProperty("resultlog");
			resultlogpath = resultlog + "\\Resultlog_" + browser + ".xml";
			pinFilePath = properties.getProperty("pinFilePath");
			buildversion = properties.getProperty("BuildVersion");	
			fileUploadHandler=properties.getProperty("fileUploadHandler");
			passwordFilePath=properties.getProperty("passwordFilePath");
			server=properties.getProperty("POS_server");
			userName=properties.getProperty("POS_username");
			couponMangementNameSpace=properties.getProperty("couponMangementNameSpace");
			couponMangementendPoint=properties.getProperty("couponMangementendPoint");
			password=properties.getProperty("POS_password");
			sid=properties.getProperty("POS_sid");
			PDFDownloadHandler=properties.getProperty("PDFDownloadHandler");
			ExcelDownloadHandler=properties.getProperty("ExcelDownloadHandler");
			Recovery=properties.getProperty("Recovery");
			inputFilePath=properties.getProperty("inputFilePath");
			outputFilePath=properties.getProperty("outputFilePath");
			locatorsPath=properties.getProperty("locatorsPath");
			ErrorCodes=properties.getProperty("ErrorCodePath");
			ussd_url=properties.getProperty("USSD_URL");
			SirCreditEndPoint=properties.getProperty("SirCreditEndPoint");
			SirCreditNameSpace=properties.getProperty("SirCreditNameSpace");
			RecargaServiceEndPoint=properties.getProperty("RecargaServiceEndPoint");
			RecargaServiceNameSpace=properties.getProperty("RecargaServiceNameSpace");
			WEB_SERVICE_IP=properties.getProperty("WEB_SERVICE_IP");
			WEB_SERVICE_PORT=properties.getProperty("WEB_SERVICE_PORT");
			WEB_SERVICES=properties.getProperty("webServices");
			properties.load(new FileInputStream(locatorsPath));
			IVRAESKeyValue=properties.getProperty("IVRAESKeyValue");
				//properties.load(new FileInputStream(ErrorCodes));
		}//try 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}//configure function
	
	public void locatorConfigure() 
	{
		try 
		{
		
			propertiesLocatores = new Properties();
			//propertiesLocatores.load(new FileInputStream(locatorsPath));
			InputStream inputStream = new FileInputStream(locatorsPath);
			Reader reader = new InputStreamReader(inputStream, "UTF-8");
			propertiesLocatores.load(reader);
		}//try 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}//configure function
	
	
	
	/*public void errorCodeConfigure() 
	{
		try 
		{
			propertiesErrorCodes = new Properties();
			propertiesErrorCodes.load(new FileInputStream(ErrorCodes));
		}//try 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}//configure function
	*/
	
	
	
	
	
	
	
}//Class Configure