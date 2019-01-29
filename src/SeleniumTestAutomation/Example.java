package SeleniumTestAutomation;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Example {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		
		/*String s="Socio No-Existe/Inactivo/Estado Fraude";
		String s1="Socio No-Existe/Inactivo/Estado Fraude";
		s.equalsIgnoreCase(s1);
		System.out.println("shan-->"+s.equalsIgnoreCase(s1));
		String s2="Socio No-Existe 
				  /Inactivo/Estado Fraude";
		//System.out.println("ssdfsdf"+ s2.trim());
*/		
		//System.out.println("shankar      is a good        boy".replaceAll("\\s+", " "));
		Example ss=new Example();
		//ss.asc(1,7);
		ss.shan();
		
		//ss.regularexp();
		//ss.changelowertoupper();
		// TODO Auto-generated method stub
		//String str = "<field id= 61  value= get-ValidationId />";
		//String temp = str.substring(11, 13);
		//System.out.println(temp);
	//	int a[]={1,2,3,0,7,0,3};
		//System.out.println(a[0]);
		//System.out.println("value"+(a.length));
		/*for(int i=0;i<a.length;i++)
		{
			//System.out.println("firstvales--->"+a[i]);
			if(a[i]==0){
				
				if(a[i+1]!=0){
					int temp;
					temp=a[i+1];
					a[i+1]=a[i];
					a[i]=temp;
				
					//Exit();
				}
				a[a.length-1]=a[i];
			}
		
			System.out.println("v------->"+a[i]);
			//i++;
		}*/
		
	/*	int i = 1;
		switch (i) {
			case 1:{ 
				System.out.print("1");
			}
			case 2:{ System.out.print("2");}
			default:{ System.out.print("D");}
		}
*/
		//System.out.println("valueeeeeeeeeeee---->"+System.getSecurityManager());
	/*	System.out.println("variables..."+System.getenv());//This code is Env 
		Map<String, String> arra;
		arra=System.getenv();
		String s,s1;
		s=arra.get("USERNAME");
		s1=arra.get("USERDNSDOMAIN");
		
		System.out.println("UserName----->"+s+"  "+"DomainNAme---->"+s1);
		//Map<String, String> a=System.getenv();
		//System.out.println("MAP VALUES"+a.keySet());
		*/
		
		
		
		
		 System.out.println(Double.parseDouble("1.00"));
		//System.out.println(Integer.parseInt("1"));
		
   
	}

/*	public static boolean regularexp(){
		 String phone = "123456";
		    String regex="^[0-9]{6}$";
		    Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(phone);
		    boolean s=false;
		    if(matcher.find()==true){
						s=true;		
			}
		    System.out.println("shan----->"+s);
		    return s;
	}
	*/
	public static void asc(int beginIndex,int endIndex){
		
		beginIndex=1;
	      endIndex=4;
		 String phone = "0400000";
		    //System.out.println("CANAL  "   +phone.substring(beginIndex-1, endIndex));
		    String phone1="1163004138260199";
		    System.out.println("sucursal  "   +phone1.substring(beginIndex-1, endIndex));
		    String phone2="1163004138260199";
		    System.out.println("terminal  "   +phone1.substring(4, 8));
		   
		    
		    
	} 
	
	
	public void shan() throws Exception{
		
		/*String s,s1;
		//s="750.05";
		//s="1449.55";
		s="449.95";
		//s="10049.95";
		Double d=Double.parseDouble(s);
		System.out.println("Double Value"+d);
		String s2=Double.toString(d);
		s=s2.replace(".", "");
		
		//Double d111=Double.parseDouble(s);
		int n=Integer.parseInt(s);
		
		
		System.out.println("Replace Value"+n);
		//int w=Integer.parseInt(s);
        String formatted = String.format("%05d0",n);  
	    System.out.println("Number with leading zeros: " + formatted);
	    String sddds=padData(formatted,1,false, "0");
	    sddds=sddds.substring(0, 6);
	    */
	    /*String strTest = "//table[@class='jrPage']/tbody.7.2";  
	    String[] arrayTest = strTest.split(".");  
	    
	    
	    String s=arrayTest[0];*/
	    
	    
	   // String strTest = "get-Typical_Recharge_SubscriberId,JTRM_CellularNumbe";  
	    String strTest1 = "//table[@class='jrPage']/tbody,7,3";  
	    String arrayTest=strTest1.split(",")[0];
	    String arrayTest1=strTest1.split(",")[1];
	    String arrayTest2=strTest1.split(",")[2];
	   
        //String[] arrayTest = strTest.split( ".", 1 );
	    
	    //s="//table[@class='jrPage']/tbody.7.2";
	    //s="shanlar.7.2";
	    //String temp=null;
	    //s =s.split(".")[0];
	   //s=s.split(",", 1);
	   //String r= s.substring(1,s.indexOf(','));
	    System.out.println(arrayTest);
	    System.out.println(arrayTest1);
	    System.out.println(arrayTest2);
	    //System.out.println(r);
	    
	    
	    
	    // System.out.println("length"+formatted.length());
	    	
	}
	
	private  String padData(String data,int length,boolean isRight,String delimeter)
    {
          StringBuffer tempData=isRight?new StringBuffer(data):new StringBuffer();
          
          for(int i=0;i<length;i++)
          {
                tempData.append(delimeter);
          }
          
          if(!isRight)
                tempData.append(data);
          
          return tempData.toString();
    }

	
	
/*	public static String changelowertoupper()
	{
	         String str = "Institución Bloqueada";
	         System.out.println("shshshs-->"+str);
	         str=str.toLowerCase(Locale.ENGLISH);
	         System.out.println("shan-->"+str.toLowerCase(Locale.ENGLISH));
	         return str;
	}*/
	

}
