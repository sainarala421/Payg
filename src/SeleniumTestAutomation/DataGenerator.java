package SeleniumTestAutomation;

import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Encoder;


//import sun.misc.BASE64Encoder;

class DataGenerator extends Navigate
{
	static double randomNumber;
	static double randomNumberSetup;
	static char randomCharacter, firstCharacter;
	static String randName;
	static String date, time, randNumSales;
	static int day, month, year, CurrentDay, randPin1, randPin2, randPin3;
	public static void randomName()
	{
		String temprandName="";
		for (int i = 0; i < numberOfChar; i++)
		 {
			randomNumber = Math.random();
		   randomNumberSetup = (randomNumber * 26 + 'a');
		   randomCharacter = (char) randomNumberSetup;
		   if (i==0)
			{
				firstCharacter=randomCharacter;
			}
		   temprandName=temprandName+randomCharacter;
		  }
		randName = temprandName;
  }
	public static String randomName(int numberOfChar)
	{
		String temprandName="";
		for (int i = 0; i < numberOfChar; i++)
		 {
			randomNumber = Math.random();
		   randomNumberSetup = (randomNumber * 26 + 'a');
		   randomCharacter = (char) randomNumberSetup;
		   if (i==0)
			{
				firstCharacter=randomCharacter;
			}
		   temprandName=temprandName+randomCharacter;
		  }
		return temprandName;
  }
	public static  String randomNumberGenerator(int a)
	{
		  String number=null;
		  Random r = new Random();
		  long d=0;
		        while (0>=d){
		    	 d = r.nextLong();
		    }
		    number = String.format("%015d", d).substring(0,a);
		    System.out.println("number-- "+number);
		    return number;
	}
	
	public static String randomAlphaNuemaric( int len ) 
	{
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}
	public static void randomNumber()
	{
		int randNum1 = new Double( (Math.random()+1) * 10000).intValue();
		int randNum2 = new Double( (Math.random()+1) * 10000).intValue();
		randNum = Integer.toString(randNum1)+Integer.toString(randNum2);
	}
	public static void randomSalesVolume()
	{
		int randNum1 = new Double( (Math.random()+1) * 1000).intValue();
		randNumSales = Integer.toString(randNum1);
	}
	public static void generatePins(String noOfPins) throws IOException
	{
		  Writer output = null;
		  File file = new File(Configure.pinFilePath);
		  output = new BufferedWriter(new FileWriter(file));
		  for (int num=0; num<=Integer.parseInt(noOfPins)-1; num++)
		  {
		  randPin1 = new Double( (Math.random()+1) * 1000000000).intValue();
		  randPin2 = new Double( (Math.random()+1) * 10000000).intValue();
		  randPin3 = new Double( (Math.random()+1) * 10000000).intValue();
		  String pin= Integer.toString(randPin1)+" "+Integer.toString(randPin2)+Integer.toString(randPin3);
		  output.write(pin);
		  output.write("\n");
		  }
		  output.close();
	}
	public static void generateDate(String expVal) throws InterruptedException
	{			
		Calendar now = Calendar.getInstance();
		year = now.get(Calendar.YEAR);
		month = now.get(Calendar.MONTH);
	    day = now.get(Calendar.DAY_OF_MONTH);
		if(expVal.indexOf("CurrentDay")>=0)
    	{
    		String[] monthName = {"January", "February",
    							  "March", "April", "May", "June", "July",
    							  "August", "September", "October", "November",
    							  "December"};
    		if(expVal.indexOf(":SUB:")>=0)
    		{
    			int days=Integer.parseInt(expVal.split(":")[2]);
    			now.add(Calendar.DAY_OF_MONTH, -days);
    			String mon= monthName[now.get(Calendar.MONTH)];
    			String tempYear=Integer.toString(now.get(Calendar.YEAR));
    			int tempday=now.get(Calendar.DAY_OF_MONTH);
    			String monAndYear=mon+" "+tempYear;
    			String monthFromCal;
    			monthFromCal=sel.getText("//b");
    			if(monAndYear != monthFromCal)
    			{
    				do 
    				{
    					monthFromCal=sel.getText("//b");
    					sel.click("link=-Month");
    				}
    				while ((monAndYear.contains(monthFromCal))==false);
    				sel.click("link=Month+");
    			}
    			CurrentDay=tempday;
    		}
    		if(expVal.indexOf(":ADD:")>=0)
    		{
    			int days=Integer.parseInt(expVal.split(":")[2]);
    			now.add(Calendar.DAY_OF_MONTH, days);
    			String mon= monthName[now.get(Calendar.MONTH)];
    			String tempYear=Integer.toString(now.get(Calendar.YEAR));
    			int tempday=now.get(Calendar.DAY_OF_MONTH);
    			String monAndYear=mon+" "+tempYear;
    			String monthFromCal;
    			monthFromCal=sel.getText("//b");
    			if(monAndYear != monthFromCal)
    			{
    				do 
    				{
    					monthFromCal=sel.getText("//b");
    					sel.click("link=Month+");
    				}
    				while ((monAndYear.contains(monthFromCal))==false);
    				sel.click("link=-Month");
    			}
    			CurrentDay=tempday;
    		}
    		else
    		{
    		CurrentDay=day;
    		}
    	}
	    if(expVal.indexOf("CurrentDate")>=0)
	    {	    	
	    	if(expVal.indexOf(":ADD:")>=0)
	    	{
 	    		String valToAdd=expVal.split(":")[2];
	    		expVal=expVal.split(":")[0];
	    		if(valToAdd.equalsIgnoreCase("m"))
	    		{
	    			now.add(Calendar.MONTH, 1);
	    			month = now.get(Calendar.MONTH);
	    			day=day-1;
	    		}
	    		else
	    		{
	    			now.add(Calendar.DAY_OF_MONTH, Integer.parseInt(valToAdd));
	    		    day = now.get(Calendar.DAY_OF_MONTH);
	    		}
	    	}
	    	if(expVal.indexOf(":SUB:")>=0)
	    	{
 	    		String valToAdd=expVal.split(":")[2];
	    		expVal=expVal.split(":")[0];
	    		if(valToAdd.equalsIgnoreCase("m"))
	    		{
	    			now.add(Calendar.MONTH, -1);
	    			month = now.get(Calendar.MONTH);
	    		}
	    		else
	    		{
	    			now.add(Calendar.DAY_OF_MONTH, -(Integer.parseInt(valToAdd)));
	    		    day = now.get(Calendar.DAY_OF_MONTH);
	    		}
	    	}
	    	if(expVal.equalsIgnoreCase("CurrentDate"))
	    	{
	    		String tempday=Integer.toString(day);
	    		String tempmonth=Integer.toString(month+1);
	    		if (day < 10)
	    		{
	    			tempday="0"+tempday;
	    		}
	    		if ((month+1) < 10)
	    		{
	    			tempmonth="0"+tempmonth;
	    		}
	    		date=tempday+"/"+tempmonth+"/"+Integer.toString(year);
	    	}
	    }
	}
	
	
	public static String generatesimulator(String s){
		
		
		Date date = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("MMdd");
		  
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		 //  System.out.println(dateFormat.format(date));
		   
		   String CurrDate ;
		   CurrDate=dateFormat.format(date);
		   //keyVal11.split("");
		   System.out.println("-->"+CurrDate);
		
		return CurrDate;
		
	}
public static String generatesimulatorddmm(String s){
		
		
		Date date = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
		  
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		 //  System.out.println(dateFormat.format(date));
		   
		   String CurrDate ;
		   CurrDate=dateFormat.format(date);
		   //keyVal11.split("");
		   System.out.println("-->"+CurrDate);
		
		return CurrDate;
		
	}
	
	
	
	public static String generateDateNavigate(String sValue) {
		
        String pstDate=null;
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	TimeZone tz = TimeZone.getTimeZone("GMT");
	df.setTimeZone(tz);
	Calendar cal = Calendar.getInstance(tz);
	String actualDate;
if (sValue.substring(0, 11).equalsIgnoreCase("CurrentDate"))
{
int strlen = sValue.length();

System.out.println("CurrentDate Count::::" + strlen);
if (strlen == 11)
{
					
  	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	int day = cal.get(Calendar.DAY_OF_MONTH);
	 	
	int newday = day;
	int newMonth=month+1;
	/*if (newday < 10)
	{
		//pstDate = (month+1) +"/"+ "0"+day +"/"+ year;
		pstDate = "0"+newday+"/"+(month+1)+"/"+ year;
		sValue = pstDate;
		randNum = sValue;
	}
	else
	{
		//pstDate = "0"+(month+1) +"/"+ day +"/"+ year;//updated by srilatha
		pstDate = newday+"/"+"0"+(month+1)+"/"+ year;
		sValue = pstDate;
		randNum = sValue;
	}*/
	if ((newday < 10) && (newMonth < 10) )
	{
		System.out.println("day");
		//pstDate =(month+1) +"/"+ "0"+newday +"/"+ year;
		pstDate ="0"+newday+"/"+"0"+(month+1) +"/"+ year;
		System.out.println(" Current firstcondition-->"+pstDate);
		sValue = pstDate;
		randNum = sValue;
		return sValue;
		
	}
	if((newday < 10) && (newMonth > 10)){
		pstDate ="0"+newday+"/"+(month+1) +"/"+ year;
		System.out.println("Current Second condition-->"+pstDate);
		sValue = pstDate;
		randNum = sValue;
		return sValue;

	}
	if((newday > 10) && (newMonth > 10)){
		pstDate =newday+"/"+(month+1) +"/"+ year;
		System.out.println("Current Third Condition-->"+pstDate);
		sValue = pstDate;
		randNum = sValue;
		return sValue;

	}
	if((newday > 10) && (newMonth < 10)){
		pstDate =newday+"/"+"0"+(month+1) +"/"+ year;
		System.out.println("Current Fourth Condition-->"+pstDate);
		sValue = pstDate;
		randNum = sValue;
		return sValue;

	}

	else
	{
		if((newday <= 10) && (newMonth < 10))
           {
        	   pstDate = newday +"/"+"0"+(month+1)+"/"+ year;
				sValue = pstDate;
				randNum = sValue;
				return sValue;
           }
           else if((newday <= 10) && ((newMonth==10)||(newMonth==11)||(newMonth==12)))
           {
        	   pstDate = newday +"/"+(month+1)+"/"+ year;
				sValue = pstDate;
				randNum = sValue;
				return sValue;
           }
		/*System.out.println("date");
		pstDate =(month+1) +"/"+newday+"/"+ year;
		sValue = pstDate;
		randNum = sValue;*/
	}

}
if (strlen > 11)
{
	String sign = sValue.substring(11, 12);
	String days = sValue.substring(12);
	if (days.length() == 5)
	{	
		cal.add(Calendar.MONTH, 1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
    	int day = cal.get(Calendar.DAY_OF_MONTH);
		int newday = day;
	 	if (day < 10)
    	{
	 		pstDate = (month+1) +"/"+ "0"+newday +"/"+ year;
		 	sValue = pstDate;	
		 	randNum = sValue;
		 	System.out.println("newmonth="+randNum);
    	}
	 	else
    	{
	 		pstDate = "0"+(month+1) +"/"+ newday +"/"+ year;
    		sValue = pstDate;
    		randNum = sValue;
    	}
	}
	else
	{
		if (sign.equalsIgnoreCase("+")) 
		{
			
			int s1=Integer.parseInt(days);
			cal.add(Calendar.DAY_OF_MONTH,s1);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int newday = day;
			int newMonth=month+1;
			if ((newday < 10) && (newMonth < 10) )
			{
				System.out.println("day");
				//pstDate =(month+1) +"/"+ "0"+newday +"/"+ year;
				pstDate ="0"+newday+"/"+"0"+(month+1) +"/"+ year;
				System.out.println("firstcondition-->"+pstDate);
				sValue = pstDate;
				randNum = sValue;
				return sValue;
				
			}
			if((newday < 10) && (newMonth > 10)){
				pstDate ="0"+newday+"/"+(month+1) +"/"+ year;
				System.out.println("Second condition-->"+pstDate);
				sValue = pstDate;
				randNum = sValue;
				return sValue;

			}
			if((newday > 10) && (newMonth > 10)){
				pstDate =newday+"/"+(month+1) +"/"+ year;
				System.out.println("Third Condition-->"+pstDate);
				sValue = pstDate;
				randNum = sValue;
				return sValue;

			}
			if((newday > 10) && (newMonth < 10)){
				pstDate =newday+"/"+"0"+(month+1) +"/"+ year;
				System.out.println("Fourth Condition-->"+pstDate);
				sValue = pstDate;
				randNum = sValue;
				return sValue;

			}

			else
			{
				if((newday <= 10) && (newMonth < 10))
		           {
		        	   pstDate = newday +"/"+"0"+(month+1)+"/"+ year;
						sValue = pstDate;
						randNum = sValue;
						return sValue;
		           }
		           else if((newday <= 10) && ((newMonth==10)||(newMonth==11)||(newMonth==12)))
		           {
		        	   pstDate = newday +"/"+(month+1)+"/"+ year;
						sValue = pstDate;
						randNum = sValue;
						return sValue;
		           }
				/*System.out.println("date");
				pstDate =(month+1) +"/"+newday+"/"+ year;
				sValue = pstDate;
				randNum = sValue;*/
			}
		}
		else if (sign.equalsIgnoreCase("-")) 
		{
			
			int s1=Integer.parseInt(days);
			cal.add(Calendar.DAY_OF_MONTH,-s1);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int newday = day;
			int newMonth=month+1;
			System.out.println("Dayes---->"+day);
			System.out.println("month---->"+month);
			if ((newday < 10) && (newMonth < 10) )
			{
				System.out.println("day");
				//pstDate =(month+1) +"/"+ "0"+newday +"/"+ year;
				pstDate ="0"+newday+"/"+"0"+(month+1) +"/"+ year;
				System.out.println("firstcondition-->"+pstDate);
				sValue = pstDate;
				randNum = sValue;
				return sValue;
				
			}
			if((newday < 10) && (newMonth > 10)){
				pstDate ="0"+newday+"/"+(month+1) +"/"+ year;
				System.out.println("Second condition-->"+pstDate);
				sValue = pstDate;
				randNum = sValue;
				return sValue;

			}
			if((newday > 10) && (newMonth > 10)){
				pstDate =newday+"/"+(month+1) +"/"+ year;
				System.out.println("Third Condition-->"+pstDate);
				sValue = pstDate;
				randNum = sValue;
				return sValue;

			}
			if((newday > 10) && (newMonth < 10)){
				pstDate =newday+"/"+"0"+(month+1) +"/"+ year;
				System.out.println("Fourth Condition-->"+pstDate);
				sValue = pstDate;
				randNum = sValue;
				return sValue;

			}
			else
			{
	           if((newday <= 10) && (newMonth < 10))
	           {
	        	   pstDate = newday +"/"+"0"+(month+1)+"/"+ year;
					sValue = pstDate;
					randNum = sValue;
					return sValue;
	           }
	           else if((newday <= 10) && ((newMonth==10)||(newMonth==11)||(newMonth==12)))
	           {
	        	   pstDate = newday +"/"+(month+1)+"/"+ year;
					sValue = pstDate;
					randNum = sValue;
					return sValue;
	           }
	          /* else
				System.out.println("date");
				pstDate = newday +"/"+(month+1)+"/"+ year;
				sValue = pstDate;
				randNum = sValue;*/
			}
		}
	}
}
} 
return sValue;
}
    public static void generateTransdate(String expVal ){
       DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
 	   Date datetrance = new Date();
 	   System.out.println(dateFormat.format(datetrance));
 	   expVal=dateFormat.format(datetrance);
 	   date=dateFormat.format(datetrance);
    }
    
    public static String generateTransdateForWebServices(String expVal ){
       /* DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
  	   Date datetrance = new Date();
  	   System.out.println(dateFormat.format(datetrance));
  	   expVal=dateFormat.format(datetrance);
  	   System.out.println("Current Date of Web services-->"+expVal);
  	   expVal=expVal.trim();
  	   return expVal;*/
    	Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");

        String strDate = sdf.format(date);
        System.out.println("formatted date in mm/dd/yy : " + strDate);

        sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        expVal = sdf.format(date);
        System.out.println("formatted date in mm-dd-yyyy hh:mm:ss : " + expVal);
  	   return expVal;
    }
    
    
	public static boolean verifyPattern(String request)
	{
		Pattern p = Pattern.compile("[0-9a-zA-Z\\,\\:]");
        //String a = "0:0220,2:0922222222,3:401000,4:400,7:217161720,11:55,12:161720,13:0217,17:0217,38:541172,37:89982796855,41:123";
        boolean b =false;
        Matcher m = p.matcher(request);
        for (int i=0;i<request.length();i++){
              if(!m.find()){
                    b=true;
              }
        }
        return b;
	}
	public static boolean verifyPatternOnlyAlphaNumercis(String request)
	{
		Pattern p = Pattern.compile("[a-zA-Z]");
        //String a = "0:0220,2:0922222222,3:401000,4:400,7:217161720,11:55,12:161720,13:0217,17:0217,38:541172,37:89982796855,41:123";
        boolean b =false;
        Matcher m = p.matcher(request);
        for (int i=0;i<request.length();i++){
              if(!m.find()){
                    b=true;
              }
        }
        return b;
	}
	
	public static boolean RechargeSequenceNumber(String Actual){
		

		 //String phone = "123456";
		    String regex="^[0-9]{6}$";
		    Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(Actual);
		    boolean s=false;
		    if(matcher.find()==true){
						s=true;		
			}
		    System.out.println("shan----->"+s);
		    SeleniumFW.APPLICATION_LOGS.info(":Pattern Matched Status: " +s);
		    return s;
		
	}
	
	public static String getEncryptedPin(String pin) throws Exception
	{
		byte[] encryptedPinNo = null;
		String encPinString = "";

		try
		{
			BASE64Encoder encoder = new BASE64Encoder();

			byte[] myIV = {(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
			IvParameterSpec ivspec = new IvParameterSpec(myIV);

		    DESedeKeySpec      keyspec              =  null;
		    SecretKeyFactory   keyfactory           =  null;
		    SecretKey          key                  =  null;


			String pinKey = "EA5B6E98AE4A68D3D6EFBC165108D510D3A1CBF4A8CDC110";

		    byte keyBytes[] = hexToByte(pinKey);

		    keyspec = new DESedeKeySpec(keyBytes);
			keyfactory = SecretKeyFactory.getInstance("DESede");
			key = keyfactory.generateSecret(keyspec);

			Cipher encryptCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			encryptCipher.init(Cipher.ENCRYPT_MODE, key, ivspec);

			encryptedPinNo = encryptCipher.doFinal(pin.getBytes());
			encPinString = encoder.encode(encryptedPinNo);

		}
		catch(Exception e)
		{
			System.out.println("Exception in getEncryptedPin(String pin) ==>"+e);
		}
		return encPinString;
	}
	public static byte[] hexToByte (String hexString)
	{
		int len = hexString.length()/2;
		byte [] keyBytes = new byte[len];
		
		for (int i=0,j=0; j<len; i+=2,j++)
		{	
			int tmpByte = 0;
			int hVal = Byte.parseByte(hexString.substring(i,i+1), 16) ;
			int lVal = Byte.parseByte(hexString.substring(i+1,i+2), 16) ;
			tmpByte =  hVal & 0xFF;
			tmpByte = tmpByte<<4;
			tmpByte = (tmpByte | (lVal & 0xFF));
			
			keyBytes[j] = (byte)tmpByte;
		}
		
		return keyBytes;
	}//end of hexToByte()
}
