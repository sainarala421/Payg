package SeleniumTestAutomation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
public class Locators extends Configure {

 
 public static String  getLocators(String temp) {
	
	/*Properties properties = new Properties();
	try {
		properties.load(new FileInputStream(Configure.locatorsPath));
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}*/
	String loc= propertiesLocatores.getProperty(temp);
	
	if(loc==null){
		String s;
		s="parameter Not configured";
		return s;
	}else{
	return loc.trim();
 }
	
}
 public static String  getErrorCode(String temp) {
		String loc= propertiesLocatores.getProperty(temp);
		
		if(loc==null){
			String s;
			s="parameter Not configured";
			return s;
		}else{
		return loc.trim();
	 }
		
	}
 }
