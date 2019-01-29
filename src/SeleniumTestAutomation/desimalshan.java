package SeleniumTestAutomation;

import java.text.NumberFormat;

public class desimalshan {
	public static void main(String args[]){ 
	String intNumber = "1009.9";
	//String ss=Integer.toString(intNumber);
	
	if(!(intNumber.contains("."))){
		intNumber=intNumber+".00";
	}
	    String s=intNumber.replace(".", "");
	    int n=Integer.parseInt(s);
	    NumberFormat format=NumberFormat.getInstance();  
	    format.setMaximumIntegerDigits(6);  
	    format.setMaximumFractionDigits(6);  
	    format.setMinimumFractionDigits(6);  
	    format.setMinimumIntegerDigits(6);  
	    System.out.println("Shan  value:-->"+format.format(n).replace(",",""));  
        String swe=format.format(n).replace(",","");
	    swe=swe.substring(0, 6);
	    System.out.println("narayana:--->"+swe);
	}

}
