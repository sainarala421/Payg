package SeleniumTestAutomation;

import java.util.HashMap;

public class RequestToSimulator extends TestCase {
	//static HashMap<String, String> hm = new HashMap<String, String>(); 
	//String CsvRequest = "";
	public static  void MxTypicalRecharge(String CsvRequest,String AmountField_04, String CurrentDate_07, String CurrDate_12, String CurrDate_13, String CurrDate_12_13_15_17, String CurrDat_12_13_15_17, String DistributorId_32,String SubscriberId_126){
		
		//CsvRequest=RequestFormates.TypicalRecharge(AmountField_04, CurrentDate_07, CurrDate_12, CurrDate_13, CurrDate_12_13_15_17, CurrDate_12_13_15_17, DistributorId_32, SubscriberId_126);

		if(AmountField_04.contains("0000")){

			//"04:0000000500"
			String s=AmountField_04;
			String ss[]=s.split(":");
			String sss;
			sss=ss[1];
			AmountField_04=sss;
			long str= Long.parseLong(AmountField_04.trim());
			String Elk_Recharge_Amount = ""+str;
			String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
			//System.out.println("Typical_Recharge_Amount-->"+amount.trim());
			SeleniumFW.APPLICATION_LOGS.info("Typical_Recharge_Amount-->"+amount.trim());
			hm.put("Typical_Recharge_Amount", amount);

		}
		hm.put("Typical_Recharge_DistributorId", DistributorId_32.trim());
		//System.out.println("Typical_Recharge_DistributorId-->"+DistributorId_32);
		SeleniumFW.APPLICATION_LOGS.info("Typical_Recharge_DistributorId-->"+DistributorId_32);
		hm.put("Typical_Recharge_SubscriberId", SubscriberId_126.trim());
		SeleniumFW.APPLICATION_LOGS.info("Typical_Recharge_SubscriberId-->"+SubscriberId_126);
		//System.out.println("Typical_Recharge_SubscriberId-->"+SubscriberId_126);
		try{
           if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){
			String CANAL=CsvRequest.split(",")[19].split(":")[1];
			CANAL=CANAL.substring(0,7);
			System.out
					.println("CANAL ID-->"+CANAL);
			hm.put("CANAL", CANAL.trim());
			SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
			String SUCURSAL=CsvRequest.split(",")[12].split(":")[1];
			SUCURSAL=SUCURSAL.substring(0,4);
			System.out.println("SUCURSAL-->"+SUCURSAL);
			hm.put("SUCURSAL", SUCURSAL.trim());
			SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
			String TERMINAL=CsvRequest.split(",")[12].split(":")[1];
			TERMINAL=TERMINAL.substring(4,8);
			System.out
					.println("TERMINAL-->"+TERMINAL);
			hm.put("TERMINAL", TERMINAL.trim());
			SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);
			
		}
           
           
           else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){
          	   
          	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
          	   String CANAL=CsvRequest.split(",")[19].split(":")[1];
					CANAL=CANAL.substring(0,3);
					System.out
							.println("CANAL ID-->"+CANAL);
					hm.put("CANAL", CANAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
					String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
					SUCURSAL=SUCURSAL.substring(6,12);
					System.out.println("SUCURSAL-->"+SUCURSAL);
					hm.put("SUCURSAL", SUCURSAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
					String TERMINAL=CsvRequest.split(",")[13].split(":")[1];
					TERMINAL=TERMINAL.substring(22,25);
					System.out
							.println("TERMINAL-->"+TERMINAL);
					hm.put("TERMINAL", TERMINAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
             
             }
           
        else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
          	   
          	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
          	   String CANAL=CsvRequest.split(",")[13].split(":")[1];
					CANAL=CANAL.substring(5,10);
					System.out
							.println("CANAL ID-->"+CANAL);
					hm.put("CANAL", CANAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
					String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
					SUCURSAL=SUCURSAL.trim();
					System.out.println("SUCURSAL-->"+SUCURSAL);
					hm.put("SUCURSAL", SUCURSAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
					
					String TERMINAL=CsvRequest.split(",")[12].split(":")[1];
					TERMINAL=TERMINAL.trim();
					System.out
							.println("TERMINAL-->"+TERMINAL);
					hm.put("TERMINAL", TERMINAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
             }
           
		}catch(Exception e){
			e.printStackTrace();
			SeleniumFW.APPLICATION_LOGS.error("Getting Exception:",e);   
		}

	}
	
	//MxTypicalRechargeReversal
	
	
	public static void MxTypicalRechargeReversal(String CsvRequest,String AmountField_04, String CurrentDate_07, String CurrDate_12, String CurrDate_13, String CurrDate_12_13_15_17, String CurreDate_12_13_15_17, String DistributorId_32,String AuthId_38,String SubscriberId_126){if(AmountField_04.contains("0000")){

		//"04:0000000500"
		String s=AmountField_04;
		String ss[]=s.split(":");
		String sss;
		sss=ss[1];
		AmountField_04=sss;
		long str= Long.parseLong(AmountField_04.trim());
		String Elk_Recharge_Amount = ""+str;
		String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
		//System.out.println("Typical_Recharge_Amount-->"+amount.trim());
		SeleniumFW.APPLICATION_LOGS.info("Typical_Recharge_Reversal_Amount-->"+amount.trim());
		hm.put("Typical_Recharge_Reversal_Amount", amount);

	}
	hm.put("Typical_Recharge_Reversal_DistributorId", DistributorId_32.trim());
	//System.out.println("Typical_Recharge_DistributorId-->"+DistributorId_32);
	SeleniumFW.APPLICATION_LOGS.info("Typical_Recharge_Reversal_DistributorId-->"+DistributorId_32);
	hm.put("Typical_Recharge_Reversal_SubscriberId", SubscriberId_126.trim());
	SeleniumFW.APPLICATION_LOGS.info("Typical_Recharge_Reversal_SubscriberId-->"+SubscriberId_126);
	//System.out.println("Typical_Recharge_SubscriberId-->"+SubscriberId_126);
	 //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)
	try{
           if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){
				String CANAL=CsvRequest.split(",")[20].split(":")[1];
				CANAL=CANAL.substring(0,7);
				System.out
						.println("CANAL ID-->"+CANAL);
				hm.put("CANAL", CANAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
				String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
				SUCURSAL=SUCURSAL.substring(0,4);
				System.out.println("SUCURSAL-->"+SUCURSAL);
				hm.put("SUCURSAL", SUCURSAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
				String TERMINAL=CsvRequest.split(",")[13].split(":")[1];
				TERMINAL=TERMINAL.substring(4,8);
				System.out
						.println("TERMINAL-->"+TERMINAL);
				hm.put("TERMINAL", TERMINAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);
				
			}
              else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){
             	   
             	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
             	   String CANAL=CsvRequest.split(",")[20].split(":")[1];
					CANAL=CANAL.substring(0,3);
					System.out
							.println("CANAL ID-->"+CANAL);
					hm.put("CANAL", CANAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
					String SUCURSAL=CsvRequest.split(",")[14].split(":")[1];
					SUCURSAL=SUCURSAL.substring(6,12);
					System.out.println("SUCURSAL-->"+SUCURSAL);
					hm.put("SUCURSAL", SUCURSAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
					String TERMINAL=CsvRequest.split(",")[14].split(":")[1];
					TERMINAL=TERMINAL.substring(22,25);
					System.out
							.println("TERMINAL-->"+TERMINAL);
					hm.put("TERMINAL", TERMINAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
                
                }
              
           else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
             	   
             	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
             	   String CANAL=CsvRequest.split(",")[14].split(":")[1];
					CANAL=CANAL.substring(5,10);
					System.out
							.println("CANAL ID-->"+CANAL);
					hm.put("CANAL", CANAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
					String SUCURSAL=CsvRequest.split(",")[14].split(":")[1];
					SUCURSAL=SUCURSAL.trim();
					System.out.println("SUCURSAL-->"+SUCURSAL);
					hm.put("SUCURSAL", SUCURSAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
					
					String TERMINAL=CsvRequest.split(",")[13].split(":")[1];
					TERMINAL=TERMINAL.trim();
					System.out
							.println("TERMINAL-->"+TERMINAL);
					hm.put("TERMINAL", TERMINAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
                }
              
			}catch(Exception e){
		e.printStackTrace();
		SeleniumFW.APPLICATION_LOGS.error("getting exception :"+e);   
	}
	}
	
public static void MxSpecialRecharge(String CsvRequest,String AmountField_04, String CurrentDate_07, String CurrDate_12, String CurrDate_13, String CurrDate_12_13_15_17, String CurrrDate_12_13_15_17, String DistributorId_32, String SubscriberId_126){
	if(AmountField_04.contains("0000")){

		//"04:0000000500"
		String s=AmountField_04;
		String ss[]=s.split(":");
		String sss;
		sss=ss[1];
		AmountField_04=sss;
		long str= Long.parseLong(AmountField_04.trim());
		String Elk_Recharge_Amount = ""+str;
		String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
		//System.out.println("Special_Recharge_Amount-->"+amount.trim());
		SeleniumFW.APPLICATION_LOGS.info("Special_Recharge_Amount-->"+amount.trim());
		hm.put("Special_Recharge_Amount", amount);

	}
	hm.put("Special_Recharge_DistributorId", DistributorId_32.trim());
	//System.out.println("Special_Recharge_DistributorId-->"+DistributorId_32);
	SeleniumFW.APPLICATION_LOGS.info("Special_Recharge_DistributorId-->"+DistributorId_32);
	
	
	hm.put("Special_Recharge_SubscriberId", SubscriberId_126.trim());
	//System.out.println("Special_Recharge_SubscriberId-->"+SubscriberId_126);
	SeleniumFW.APPLICATION_LOGS.info("Special_Recharge_SubscriberId-->"+SubscriberId_126);
	try{
           if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){
				String CANAL=CsvRequest.split(",")[19].split(":")[1];
				CANAL=CANAL.substring(0,7);
				System.out
						.println("CANAL ID-->"+CANAL);
				hm.put("CANAL", CANAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
				String SUCURSAL=CsvRequest.split(",")[12].split(":")[1];
				SUCURSAL=SUCURSAL.substring(0,4);
				System.out.println("SUCURSAL-->"+SUCURSAL);
				hm.put("SUCURSAL", SUCURSAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
				String TERMINAL=CsvRequest.split(",")[12].split(":")[1];
				TERMINAL=TERMINAL.substring(4,8);
				System.out
						.println("TERMINAL-->"+TERMINAL);
				hm.put("TERMINAL", TERMINAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);
				
			}
              else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){
             	   
             	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
             	   String CANAL=CsvRequest.split(",")[19].split(":")[1];
					CANAL=CANAL.substring(0,3);
					System.out
							.println("CANAL ID-->"+CANAL);
					hm.put("CANAL", CANAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
					String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
					SUCURSAL=SUCURSAL.substring(6,12);
					System.out.println("SUCURSAL-->"+SUCURSAL);
					hm.put("SUCURSAL", SUCURSAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
					String TERMINAL=CsvRequest.split(",")[13].split(":")[1];
					TERMINAL=TERMINAL.substring(22,25);
					System.out
							.println("TERMINAL-->"+TERMINAL);
					hm.put("TERMINAL", TERMINAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
                
                }
              
           else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
             	   
             	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
             	   String CANAL=CsvRequest.split(",")[13].split(":")[1];
					CANAL=CANAL.substring(5,10);
					System.out
							.println("CANAL ID-->"+CANAL);
					hm.put("CANAL", CANAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
					String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
					SUCURSAL=SUCURSAL.trim();
					System.out.println("SUCURSAL-->"+SUCURSAL);
					hm.put("SUCURSAL", SUCURSAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
					
					String TERMINAL=CsvRequest.split(",")[12].split(":")[1];
					TERMINAL=TERMINAL.trim();
					System.out
							.println("TERMINAL-->"+TERMINAL);
					hm.put("TERMINAL", TERMINAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
                }
              
			}catch(Exception e){
		e.printStackTrace();
		SeleniumFW.APPLICATION_LOGS.error("getting Exception"+ e);
	}
	
}
public static void seguraMxSpecialRecharge(String CsvRequest,String AmountField_04, String CurrentDate_07, String CurrDate_12, String CurrDate_13, String CurrDate_12_13_15_17, String CurrrDate_12_13_15_17, String DistributorId_32, String SubscriberId_126){
	
	if(AmountField_04.contains("0000")){

		//"04:0000000500"
		String s=AmountField_04;
		String ss[]=s.split(":");
		String sss;
		sss=ss[1];
		AmountField_04=sss;
		long str= Long.parseLong(AmountField_04.trim());
		String Elk_Recharge_Amount = ""+str;
		String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
		//System.out.println("Special_Recharge_Amount-->"+amount.trim());
		SeleniumFW.APPLICATION_LOGS.info("Special_Recharge_Amount-->"+amount.trim());
		hm.put("Special_Recharge_Amount", amount);

	}
	hm.put("Special_Recharge_DistributorId", DistributorId_32.trim());
	//System.out.println("Special_Recharge_DistributorId-->"+DistributorId_32);
	SeleniumFW.APPLICATION_LOGS.info("Special_Recharge_DistributorId-->"+DistributorId_32);
	
	
	hm.put("Special_Recharge_SubscriberId", SubscriberId_126.trim());
	//System.out.println("Special_Recharge_SubscriberId-->"+SubscriberId_126);
	SeleniumFW.APPLICATION_LOGS.info("Special_Recharge_SubscriberId-->"+SubscriberId_126);
	
	try{

           if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){
			String CANAL=CsvRequest.split(",")[19].split(":")[1];
			CANAL=CANAL.substring(0,7);
			System.out
					.println("CANAL ID-->"+CANAL);
			hm.put("CANAL", CANAL.trim());
			SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
			String SUCURSAL=CsvRequest.split(",")[12].split(":")[1];
			SUCURSAL=SUCURSAL.substring(0,4);
			System.out.println("SUCURSAL-->"+SUCURSAL);
			hm.put("SUCURSAL", SUCURSAL.trim());
			SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
			String TERMINAL=CsvRequest.split(",")[12].split(":")[1];
			TERMINAL=TERMINAL.substring(4,8);
			System.out
					.println("TERMINAL-->"+TERMINAL);
			hm.put("TERMINAL", TERMINAL.trim());
			SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);
			
		}
           else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){
          	   
          	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
          	   String CANAL=CsvRequest.split(",")[19].split(":")[1];
					CANAL=CANAL.substring(0,3);
					System.out
							.println("CANAL ID-->"+CANAL);
					hm.put("CANAL", CANAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
					String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
					SUCURSAL=SUCURSAL.substring(6,12);
					System.out.println("SUCURSAL-->"+SUCURSAL);
					hm.put("SUCURSAL", SUCURSAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
					String TERMINAL=CsvRequest.split(",")[13].split(":")[1];
					TERMINAL=TERMINAL.substring(22,25);
					System.out
							.println("TERMINAL-->"+TERMINAL);
					hm.put("TERMINAL", TERMINAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
             
             }
           
        else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
          	   
          	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
          	   String CANAL=CsvRequest.split(",")[13].split(":")[1];
					CANAL=CANAL.substring(5,10);
					System.out
							.println("CANAL ID-->"+CANAL);
					hm.put("CANAL", CANAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
					String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
					SUCURSAL=SUCURSAL.trim();
					System.out.println("SUCURSAL-->"+SUCURSAL);
					hm.put("SUCURSAL", SUCURSAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
					
					String TERMINAL=CsvRequest.split(",")[12].split(":")[1];
					TERMINAL=TERMINAL.trim();
					System.out
							.println("TERMINAL-->"+TERMINAL);
					hm.put("TERMINAL", TERMINAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
             }
           
		
	}catch(Exception e){
		e.printStackTrace();
		SeleniumFW.APPLICATION_LOGS.error("Gettinh Exception",e);
	}
	
}

public static void seguraMxSpecialRechargeReversal(String CsvRequest,String AmountField_04, String CurrentDate_07, String CurrDate_12, String CurrDate_13, String CurrDate_12_13_15_17, String CurrrDate_12_13_15_17, String DistributorId_32,String AuthId_38,String SubscriberId_126){
	if(AmountField_04.contains("0000")){

		//"04:0000000500"
		String s=AmountField_04;
		String ss[]=s.split(":");
		String sss;
		sss=ss[1];
		AmountField_04=sss;
		long str= Long.parseLong(AmountField_04.trim());
		String Elk_Recharge_Amount = ""+str;
		String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
		//System.out.println("Special_Recharge_Amount-->"+amount.trim());
		SeleniumFW.APPLICATION_LOGS.info("Special_Recharge_Reversal_Amount-->"+amount.trim());
		hm.put("Special_Recharge_Reversal_Amount", amount);

	}
	hm.put("Special_Recharge_Reversal_DistributorId", DistributorId_32.trim());
	//System.out.println("Special_Recharge_DistributorId-->"+DistributorId_32);
	SeleniumFW.APPLICATION_LOGS.info("Special_Recharge_Reversal_DistributorId-->"+DistributorId_32);
	
	
	hm.put("Special_Recharge_Reversal_SubscriberId", SubscriberId_126.trim());
	//System.out.println("Special_Recharge_SubscriberId-->"+SubscriberId_126);
	SeleniumFW.APPLICATION_LOGS.info("Special_Recharge_Reversal_SubscriberId-->"+SubscriberId_126);
	hm.put("Original_Reversal_AuthId", AuthId_38.trim());
	//System.out.println("Special_Recharge_SubscriberId-->"+SubscriberId_126);
	SeleniumFW.APPLICATION_LOGS.info("Original_Reversal_AuthId-->"+SubscriberId_126);
	try{

           if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){
				String CANAL=CsvRequest.split(",")[20].split(":")[1];
				CANAL=CANAL.substring(0,7);
				System.out
						.println("CANAL ID-->"+CANAL);
				hm.put("CANAL", CANAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
				String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
				SUCURSAL=SUCURSAL.substring(0,4);
				System.out.println("SUCURSAL-->"+SUCURSAL);
				hm.put("SUCURSAL", SUCURSAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
				String TERMINAL=CsvRequest.split(",")[13].split(":")[1];
				TERMINAL=TERMINAL.substring(4,8);
				System.out
						.println("TERMINAL-->"+TERMINAL);
				hm.put("TERMINAL", TERMINAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);
				
			}
              else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){
             	   
             	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
             	   String CANAL=CsvRequest.split(",")[20].split(":")[1];
					CANAL=CANAL.substring(0,3);
					System.out
							.println("CANAL ID-->"+CANAL);
					hm.put("CANAL", CANAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
					String SUCURSAL=CsvRequest.split(",")[14].split(":")[1];
					SUCURSAL=SUCURSAL.substring(6,12);
					System.out.println("SUCURSAL-->"+SUCURSAL);
					hm.put("SUCURSAL", SUCURSAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
					String TERMINAL=CsvRequest.split(",")[14].split(":")[1];
					TERMINAL=TERMINAL.substring(22,25);
					System.out
							.println("TERMINAL-->"+TERMINAL);
					hm.put("TERMINAL", TERMINAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
                
                }
              
           else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
             	   
             	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
             	   String CANAL=CsvRequest.split(",")[14].split(":")[1];
					CANAL=CANAL.substring(5,10);
					System.out
							.println("CANAL ID-->"+CANAL);
					hm.put("CANAL", CANAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
					String SUCURSAL=CsvRequest.split(",")[14].split(":")[1];
					SUCURSAL=SUCURSAL.trim();
					System.out.println("SUCURSAL-->"+SUCURSAL);
					hm.put("SUCURSAL", SUCURSAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
					
					String TERMINAL=CsvRequest.split(",")[13].split(":")[1];
					TERMINAL=TERMINAL.trim();
					System.out
							.println("TERMINAL-->"+TERMINAL);
					hm.put("TERMINAL", TERMINAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
                }
              
			
	}catch(Exception e){
		e.printStackTrace();
		SeleniumFW.APPLICATION_LOGS.error("Gettinh Exception",e);
	}
	
}

public static void MxSpecialRechargeReversal(String CsvRequest,String AmountField_04, String CurrentDate_07, String CurrDate_12, String CurrDate_13, String CurrDate_12_13_15_17, String CurrrDate_12_13_15_17, String DistributorId_32,String AuthId_38,String SubscriberId_126){if(AmountField_04.contains("0000")){

	//"04:0000000500"
	String s=AmountField_04;
	String ss[]=s.split(":");
	String sss;
	sss=ss[1];
	AmountField_04=sss;
	long str= Long.parseLong(AmountField_04.trim());
	String Elk_Recharge_Amount = ""+str;
	String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
	//System.out.println("Special_Recharge_Amount-->"+amount.trim());
	SeleniumFW.APPLICATION_LOGS.info("Special_Recharge_Reversal_Amount-->"+amount.trim());
	hm.put("Special_Recharge_Reversal_Amount", amount);

}
hm.put("Special_Recharge_Reversal_DistributorId", DistributorId_32.trim());
//System.out.println("Special_Recharge_DistributorId-->"+DistributorId_32);
SeleniumFW.APPLICATION_LOGS.info("Special_Recharge_Reversal_DistributorId-->"+DistributorId_32);


hm.put("Special_Recharge_Reversal_SubscriberId", SubscriberId_126.trim());
//System.out.println("Special_Recharge_SubscriberId-->"+SubscriberId_126);
SeleniumFW.APPLICATION_LOGS.info("Special_Recharge_Reversal_SubscriberId-->"+SubscriberId_126);
hm.put("Original_Reversal_AuthId", AuthId_38.trim());
//System.out.println("Special_Recharge_SubscriberId-->"+SubscriberId_126);
SeleniumFW.APPLICATION_LOGS.info("Original_Reversal_AuthId-->"+SubscriberId_126);

try{

if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){
	String CANAL=CsvRequest.split(",")[20].split(":")[1];
	CANAL=CANAL.substring(0,7);
	System.out
			.println("CANAL ID-->"+CANAL);
	hm.put("CANAL", CANAL.trim());
	SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
	String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
	SUCURSAL=SUCURSAL.substring(0,4);
	System.out.println("SUCURSAL-->"+SUCURSAL);
	hm.put("SUCURSAL", SUCURSAL.trim());
	SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
	String TERMINAL=CsvRequest.split(",")[13].split(":")[1];
	TERMINAL=TERMINAL.substring(4,8);
	System.out
			.println("TERMINAL-->"+TERMINAL);
	hm.put("TERMINAL", TERMINAL.trim());
	SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);
	
}
   else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){
  	   
  	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
  	   String CANAL=CsvRequest.split(",")[20].split(":")[1];
			CANAL=CANAL.substring(0,3);
			System.out
					.println("CANAL ID-->"+CANAL);
			hm.put("CANAL", CANAL.trim());
			SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
			String SUCURSAL=CsvRequest.split(",")[14].split(":")[1];
			SUCURSAL=SUCURSAL.substring(6,12);
			System.out.println("SUCURSAL-->"+SUCURSAL);
			hm.put("SUCURSAL", SUCURSAL.trim());
			SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
			String TERMINAL=CsvRequest.split(",")[14].split(":")[1];
			TERMINAL=TERMINAL.substring(22,25);
			System.out
					.println("TERMINAL-->"+TERMINAL);
			hm.put("TERMINAL", TERMINAL.trim());
			SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
     
     }
   
else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
  	   
  	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
  	   String CANAL=CsvRequest.split(",")[14].split(":")[1];
			CANAL=CANAL.substring(5,10);
			System.out
					.println("CANAL ID-->"+CANAL);
			hm.put("CANAL", CANAL.trim());
			SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
			String SUCURSAL=CsvRequest.split(",")[14].split(":")[1];
			SUCURSAL=SUCURSAL.trim();
			System.out.println("SUCURSAL-->"+SUCURSAL);
			hm.put("SUCURSAL", SUCURSAL.trim());
			SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
			
			String TERMINAL=CsvRequest.split(",")[13].split(":")[1];
			TERMINAL=TERMINAL.trim();
			System.out
					.println("TERMINAL-->"+TERMINAL);
			hm.put("TERMINAL", TERMINAL.trim());
			SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
     }
   																
}catch(Exception e){

e.printStackTrace();
SeleniumFW.APPLICATION_LOGS.error("Gettinh Exception",e);
}}

public static void MxPocketSaleDada(String CsvRequest,String AmountField_04, String CurrentDate_07,String CurrDate_12,String CurrDate_13, String CurrDate_12_13_15_17,String CurrrDate_12_13_15_17, String DistributorId_32, String SubscriberId_126,String PocketCode_126)
{
	if(AmountField_04.contains("0000")){
	//"04:0000000500"
	String s=AmountField_04;
	String ss[]=s.split(":");
	String sss;
	sss=ss[1];
	AmountField_04=sss;
	long str= Long.parseLong(AmountField_04.trim());
	String Elk_Recharge_Amount = ""+str;
	String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
	//System.out.println("pocketSale_Amount-->"+amount.trim());
	SeleniumFW.APPLICATION_LOGS.info("pocketSale_Amount-->"+amount.trim());
	hm.put("pocketSale_Recharge_Amount", amount);

}

hm.put("pocketSale_DistributorId", DistributorId_32.trim());
//System.out.println("pocketSale_DistributorId-->"+DistributorId_32);
SeleniumFW.APPLICATION_LOGS.info("pocketSale_DistributorId-->"+DistributorId_32);
hm.put("pocketSale_SubscriberId", SubscriberId_126.trim());
//System.out.println("pocketSale_SubscriberId-->"+SubscriberId_126);
SeleniumFW.APPLICATION_LOGS.info("pocketSale_SubscriberId-->"+SubscriberId_126);
hm.put("pocketCode",PocketCode_126.trim());
//System.out.println("pocketCode -->"+PocketCode_126);
SeleniumFW.APPLICATION_LOGS.info("pocketCode -->"+PocketCode_126);

try{

       if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){
		String CANAL=CsvRequest.split(",")[19].split(":")[1];
		CANAL=CANAL.substring(0,7);
		System.out
				.println("CANAL ID-->"+CANAL);
		hm.put("CANAL", CANAL.trim());
		SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
		String SUCURSAL=CsvRequest.split(",")[12].split(":")[1];
		SUCURSAL=SUCURSAL.substring(0,4);
		System.out.println("SUCURSAL-->"+SUCURSAL);
		hm.put("SUCURSAL", SUCURSAL.trim());
		SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
		String TERMINAL=CsvRequest.split(",")[12].split(":")[1];
		TERMINAL=TERMINAL.substring(4,8);
		System.out
				.println("TERMINAL-->"+TERMINAL);
		hm.put("TERMINAL", TERMINAL.trim());
		SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);
		
	}
       else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){
      	   
      	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
      	   String CANAL=CsvRequest.split(",")[19].split(":")[1];
				CANAL=CANAL.substring(0,3);
				System.out
						.println("CANAL ID-->"+CANAL);
				hm.put("CANAL", CANAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
				String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
				SUCURSAL=SUCURSAL.substring(6,12);
				System.out.println("SUCURSAL-->"+SUCURSAL);
				hm.put("SUCURSAL", SUCURSAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
				String TERMINAL=CsvRequest.split(",")[13].split(":")[1];
				TERMINAL=TERMINAL.substring(22,25);
				System.out
						.println("TERMINAL-->"+TERMINAL);
				hm.put("TERMINAL", TERMINAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
         
         }
       
    else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
      	   
      	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
      	   String CANAL=CsvRequest.split(",")[13].split(":")[1];
				CANAL=CANAL.substring(5,10);
				System.out
						.println("CANAL ID-->"+CANAL);
				hm.put("CANAL", CANAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
				String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
				SUCURSAL=SUCURSAL.trim();
				System.out.println("SUCURSAL-->"+SUCURSAL);
				hm.put("SUCURSAL", SUCURSAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
				
				String TERMINAL=CsvRequest.split(",")[12].split(":")[1];
				TERMINAL=TERMINAL.trim();
				System.out
						.println("TERMINAL-->"+TERMINAL);
				hm.put("TERMINAL", TERMINAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
         }
       
	
}catch(Exception e){
	e.printStackTrace();
	SeleniumFW.APPLICATION_LOGS.error("Gettinh Exception",e);
	
}}

public static void MxPacketSaleDadaReversal(String CsvRequest,String AmountField_04, String CurrentDate_07,String CurrDate_12,String CurrDate_13, String CurrDate_12_13_15_17,String CurrrDate_12_13_15_17, String DistributorId_32,String AuthId_38,String SubscriberId_126,String PocketCode_126){if(AmountField_04.contains("0000")){
	//"04:0000000500"
	String s=AmountField_04;
	String ss[]=s.split(":");
	String sss;
	sss=ss[1];
	AmountField_04=sss;
	long str= Long.parseLong(AmountField_04.trim());
	String Elk_Recharge_Amount = ""+str;
	String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
	//System.out.println("pocketSale_Amount-->"+amount.trim());
	SeleniumFW.APPLICATION_LOGS.info("pocketSale_Amount-->"+amount.trim());
	hm.put("pocketSale_Reversal_Amount", amount);

}
hm.put("pocketSale_DistributorId", DistributorId_32.trim());
//System.out.println("pocketSale_DistributorId-->"+DistributorId_32);
SeleniumFW.APPLICATION_LOGS.info("pocketSale_DistributorId-->"+DistributorId_32);
hm.put("pocketSaleReversal_SubscriberId", SubscriberId_126.trim());
//System.out.println("pocketSale_SubscriberId-->"+SubscriberId_126);
SeleniumFW.APPLICATION_LOGS.info("pocketSale_SubscriberId-->"+SubscriberId_126);
hm.put("pocketCode_Reversal", PocketCode_126.trim());
//System.out.println("pocketCode -->"+PocketCode_126);
SeleniumFW.APPLICATION_LOGS.info("pocketCode -->"+PocketCode_126);

try{
       if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){
				String CANAL=CsvRequest.split(",")[20].split(":")[1];
				CANAL=CANAL.substring(0,7);
				System.out
						.println("CANAL ID-->"+CANAL);
				hm.put("CANAL", CANAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
				String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
				SUCURSAL=SUCURSAL.substring(0,4);
				System.out.println("SUCURSAL-->"+SUCURSAL);
				hm.put("SUCURSAL", SUCURSAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
				String TERMINAL=CsvRequest.split(",")[13].split(":")[1];
				TERMINAL=TERMINAL.substring(4,8);
				System.out
						.println("TERMINAL-->"+TERMINAL);
				hm.put("TERMINAL", TERMINAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);
				
			}
              else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){
             	   
             	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
             	   String CANAL=CsvRequest.split(",")[20].split(":")[1];
					CANAL=CANAL.substring(0,3);
					System.out
							.println("CANAL ID-->"+CANAL);
					hm.put("CANAL", CANAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
					String SUCURSAL=CsvRequest.split(",")[14].split(":")[1];
					SUCURSAL=SUCURSAL.substring(6,12);
					System.out.println("SUCURSAL-->"+SUCURSAL);
					hm.put("SUCURSAL", SUCURSAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
					String TERMINAL=CsvRequest.split(",")[14].split(":")[1];
					TERMINAL=TERMINAL.substring(22,25);
					System.out
							.println("TERMINAL-->"+TERMINAL);
					hm.put("TERMINAL", TERMINAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
                
                }
              
           else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
             	   
             	   //terminam :43(23,25),SUCURSAL:43(7,12) ,canel:102(1,3)//
             	   String CANAL=CsvRequest.split(",")[14].split(":")[1];
					CANAL=CANAL.substring(5,10);
					System.out
							.println("CANAL ID-->"+CANAL);
					hm.put("CANAL", CANAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("CANAL ID-->-->"+CANAL);
					String SUCURSAL=CsvRequest.split(",")[14].split(":")[1];
					SUCURSAL=SUCURSAL.trim();
					System.out.println("SUCURSAL-->"+SUCURSAL);
					hm.put("SUCURSAL", SUCURSAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
					
					String TERMINAL=CsvRequest.split(",")[13].split(":")[1];
					TERMINAL=TERMINAL.trim();
					System.out
							.println("TERMINAL-->"+TERMINAL);
					hm.put("TERMINAL", TERMINAL.trim());
					SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);   
                }
       
}catch(Exception e){
	e.printStackTrace();
	SeleniumFW.APPLICATION_LOGS.error("Gettinh Exception",e);
}}

//kv

public static void MxIVRRechargewithEpin(String CsvRequest,String AmountField_04,String CurrentDate_07,String CurrDate_12,String CurrDate_13,String CurrDate_12_13_15_17,String CurrDat_12_13_15_17,String DistributorId_32,String SubscriberId_126){

	if(AmountField_04.contains("0000")){
		//"04:0000000500"
		String s=AmountField_04;
		String ss[]=s.split(":");
		String sss;
		sss=ss[1];
		AmountField_04=sss;
		long str= Long.parseLong(AmountField_04.trim());
		String Elk_Recharge_Amount = ""+str;
		String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
		System.out.println("IVRRechargewithEpin_Amount-->"+amount.trim());
		hm.put("IVRRechargewithEpin_Amount", amount);

	}
	hm.put("IVRRechargewithEpin_MainDistributorId", DistributorId_32.trim());
	//System.out.println("UssdFundTransfer_MainDistributorId-->"+DistributorId_32);
	SeleniumFW.APPLICATION_LOGS.info("IVRRechargewithEpin_MainDistributorId-->"+DistributorId_32);
	hm.put("IVRRechargewithEpin_SubscriberId", SubscriberId_126.trim());
	SeleniumFW.APPLICATION_LOGS.info("IVRRechargewithEpin_SubscriberId-->"+SubscriberId_126);
}

public static void MX_USSD_FundTransferRequest(String CsvRequest,String AmountField_04, String CurrentDate_07, String CurrDate_12_13_15_17, String CurrrDate_12_13_15_17, String CurrrrDate_12_13_15_17, String DistributorId_32, String UssdFundTDestNum_126,String UssdFundTSourceNum_126,String UssdFundTSourcePassword_126){

	if(AmountField_04.contains("0000")){
		//"04:0000000500"
		String s=AmountField_04;
		String ss[]=s.split(":");
		String sss;
		sss=ss[1];
		AmountField_04=sss;
		long str= Long.parseLong(AmountField_04.trim());
		String Elk_Recharge_Amount = ""+str;
		String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
		System.out.println("UssdFundTransfer_Amount-->"+amount.trim());
		hm.put("UssdFundTranser_Amount", amount);

	}
	
	
	hm.put("UssdFundTransfer_MainDistributorId", DistributorId_32.trim());
	//System.out.println("UssdFundTransfer_MainDistributorId-->"+DistributorId_32);
	SeleniumFW.APPLICATION_LOGS.info("UssdFundTransfer_MainDistributorId-->"+DistributorId_32);

	hm.put("UssdFundTransfer_Destination_SubscriberId", UssdFundTDestNum_126.trim());
	//System.out.println("UssdFundTransfer_SubscriberId-->"+UssdFundTDestNum_126);
	SeleniumFW.APPLICATION_LOGS.info("UssdFundTransfer_SubscriberId-->"+UssdFundTDestNum_126);
	hm.put("UssdFundTransfer_Source_SubscriberId", UssdFundTSourceNum_126.trim());
	SeleniumFW.APPLICATION_LOGS.info("UssdFundTransfer_Source_SubscriberId -->"+UssdFundTSourceNum_126);
	//System.out.println("UssdFundTransfer_Source_SubscriberId -->"+UssdFundTSourceNum_126);
}

public static void UssdMxPacketSaleDada(String CsvRequest,String AmountField_04,String SubscriberId_126,String UssdFundTSourceNum_126,String UssdFundTSourcePassword_126,String UssdPocketCode_126){
	if(AmountField_04.contains("0000")&&!(AmountField_04.contains("AB"))){
		//"04:0000000500"
		String s=AmountField_04;
		String ss[]=s.split(":");
		String sss;
		sss=ss[1];
		AmountField_04=sss;
		long str= Long.parseLong(AmountField_04.trim());
		String Elk_Recharge_Amount = ""+str;
		String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
		System.out.println("UssdFundTransfer_Amount-->"+amount.trim());
		hm.put("UssdPacketSale_Amount", amount);

	}

	//hm.put("UssdFundTransfer_MainDistributorId", DistributorId_32.trim());
	//System.out.println("UssdFundTransfer_MainDistributorId-->"+DistributorId_32);
	//SeleniumFW.APPLICATION_LOGS.info("UssdFundTransfer_MainDistributorId-->"+DistributorId_32);
	String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
	SUCURSAL=SUCURSAL.substring(13,22).trim();
	
	System.out.println("SUCURSAL-->"+SUCURSAL);
	hm.put("SUCURSAL", SUCURSAL.trim());
	SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
	hm.put("PacketSale_SubscriberId", SubscriberId_126.trim());
	//System.out.println("UssdFundTransfer_SubscriberId-->"+UssdFundTDestNum_126);
	SeleniumFW.APPLICATION_LOGS.info("PacketSale_SubscriberId-->"+SubscriberId_126.trim());
	hm.put("UssdFundTransfer_Source_SubscriberId", UssdFundTSourceNum_126.trim());
	SeleniumFW.APPLICATION_LOGS.info("Ussd_Source_SubscriberId -->"+UssdFundTSourceNum_126);
	//System.out.println("UssdFundTransfer_Source_SubscriberId -->"+UssdFundTSourceNum_126);
	
}

public static void MX_USSD_BalanceEnquiryRequest(String CsvRequest,String CurrentDate_07, String CurrDate_12_13_15_17, String CurrrDate_12_13_15_17, String CurrrrDate_12_13_15_17, String UssdFundTSourceNum_126,String UssdFundTSourcePassword_126){

			hm.put("UssdBlanceEnquiry_Source_SubscriberId", UssdFundTSourceNum_126.trim());
		   SeleniumFW.APPLICATION_LOGS.info("UssdBlanceEnquiry_Source_SubscriberId -->"+UssdFundTSourceNum_126);
	
	}
public static void MX_USSD_ussdLastTransactionRequest(String CsvRequest,String CurrentDate_07, String CurrDate_12_13_15_17, String CurrrDate_12_13_15_17, String CurrrrDate_12_13_15_17, String SubscriberId_126,String UssdFundTSourceNum_126,String UssdFundTSourcePassword_126){

	hm.put("UssdBlanceEnquiry_Source_SubscriberId", UssdFundTSourceNum_126.trim());
   SeleniumFW.APPLICATION_LOGS.info("UssdBlanceEnquiry_Source_SubscriberId -->"+UssdFundTSourceNum_126);

}

public static void MX_USSD_ussdTotalSummaryoftheDay(String CsvRequest,String CurrentDate_07, String CurrDate_12_13_15_17, String CurrrDate_12_13_15_17, String CurrrrDate_12_13_15_17, String UssdFundTSourceNum_126,String UssdFundTSourcePassword_126){

	hm.put("UssdBlanceEnquiry_Source_SubscriberId", UssdFundTSourceNum_126.trim());
   SeleniumFW.APPLICATION_LOGS.info("UssdBlanceEnquiry_Source_SubscriberId -->"+UssdFundTSourceNum_126);
   
   hm.put("UssdBlanceEnquiry_Source_SubscriberId", UssdFundTSourcePassword_126.trim());
   
   System.out.println("Ussd Balance Encuary Subscri");
   

}


public static void UssdMxPacketSaleDadaReversal(String CsvRequest,String AmountField_04,String SubscriberId_126,String UssdFundTSourceNum_126,String UssdFundTSourcePassword_126,String UssdPocketCode_126){
	if(AmountField_04.contains("0000")){
		//"04:0000000500"
		String s=AmountField_04;
		String ss[]=s.split(":");
		String sss;
		sss=ss[1];
		AmountField_04=sss;
		long str= Long.parseLong(AmountField_04.trim());
		String Elk_Recharge_Amount = ""+str;
		String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
		System.out.println("UssdFundTransfer_Amount-->"+amount.trim());
		hm.put("UssdPacketSale_Reversal_Amount", amount);
	}
	hm.put("PacketSale_Reversal_SubscriberId", SubscriberId_126.trim());
	//System.out.println("UssdFundTransfer_SubscriberId-->"+UssdFundTDestNum_126);
	SeleniumFW.APPLICATION_LOGS.info("PacketSale_SubscriberId_Reversal-->"+SubscriberId_126.trim());
	hm.put("UssdFundTransfer_Source_Reversal_SubscriberId", UssdFundTSourceNum_126.trim());
	SeleniumFW.APPLICATION_LOGS.info("UssdFundTransfer_Source_Reversal_SubscriberId -->"+UssdFundTSourceNum_126);
	//System.out.println("UssdFundTransfer_Source_SubscriberId -->"+UssdFundTSourceNum_126);
	
}
public static void UssdMxTypicalRecharge(String CsvRequest,String AmountField_04,String SubscriberId_126,String UssdFundTSourceNum_126,String UssdFundTSourcePassword_126){if(AmountField_04.contains("0000")){
	//"04:0000000500"
	String s=AmountField_04;
	String ss[]=s.split(":");
	String sss;
	sss=ss[1];
	AmountField_04=sss;
	long str= Long.parseLong(AmountField_04.trim());
	String Elk_Recharge_Amount = ""+str;
	String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
	System.out.println("UssdFundTransfer_Amount-->"+amount.trim());
	hm.put("UssdTypicalRecharge_Amount", amount);
}
hm.put("UssdMxTypicalRecharge_SubscriberId", SubscriberId_126.trim());
SeleniumFW.APPLICATION_LOGS.info("UssdMxTypicalRecharge_SubscriberId-->"+SubscriberId_126.trim());
hm.put("UssdMxTypicalRecharge_DistributorNumber", UssdFundTSourceNum_126.trim());
SeleniumFW.APPLICATION_LOGS.info("UssdMxTypicalRecharge_DistributorNumber-->"+UssdFundTSourceNum_126);

}

public static void UssdMxTypicalRechargeReversal(String CsvRequest,String AmountField_04,String SubscriberId_126,String UssdFundTSourceNum_126,String UssdFundTSourcePassword_126){if(AmountField_04.contains("0000")){
	//"04:0000000500"
	String s=AmountField_04;
	String ss[]=s.split(":");
	String sss;
	sss=ss[1];
	AmountField_04=sss;
	long str= Long.parseLong(AmountField_04.trim());
	String Elk_Recharge_Amount = ""+str;
	String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
	System.out.println("UssdFundTransfer_Amount-->"+amount.trim());
	hm.put("UssdTypicalRechargeReversal_Amount", amount);
}
hm.put("UssdMxTypicalRechargeReversal_SubscriberId", SubscriberId_126.trim());
SeleniumFW.APPLICATION_LOGS.info("UssdMxTypicalRechargeReversal_SubscriberId-->"+SubscriberId_126.trim());
hm.put("UssdMxTypicalRechargeReversal_DistributorNumber", UssdFundTSourceNum_126.trim());
SeleniumFW.APPLICATION_LOGS.info("UssdMxTypicalRechargeReversal_DistributorNumber-->"+UssdFundTSourceNum_126);

}

public static void UssdMxSpecialRecharge(String CsvRequest,String AmountField_04,String SubscriberId_126,String UssdFundTSourceNum_126,String UssdFundTSourcePassword_126){if(AmountField_04.contains("0000")){
	//"04:0000000500"
	String s=AmountField_04;
	String ss[]=s.split(":");
	String sss;
	sss=ss[1];
	AmountField_04=sss;
	long str= Long.parseLong(AmountField_04.trim());
	String Elk_Recharge_Amount = ""+str;
	String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
	System.out.println("UssdFundTransfer_Amount-->"+amount.trim());
	hm.put("UssdSpecialRecharge_Amount", amount);
}
String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
SUCURSAL=SUCURSAL.substring(13,22).trim();

System.out.println("SUCURSAL-->"+SUCURSAL);
hm.put("SUCURSAL", SUCURSAL.trim());
SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
hm.put("UssdMxSpeialRecharge_SubscriberId", SubscriberId_126.trim());
SeleniumFW.APPLICATION_LOGS.info("UssdMxSpeialRecharge_SubscriberId-->"+SubscriberId_126.trim());
hm.put("UssdMxSpecialRecharge_DistributorNumber", UssdFundTSourceNum_126.trim());
SeleniumFW.APPLICATION_LOGS.info("UssdMxSpecialRecharge_DistributorNumber-->"+UssdFundTSourceNum_126);

}



public static void UssdMxSpecialRechargeReversal(String CsvRequest,String AmountField_04,String SubscriberId_126,String UssdFundTSourceNum_126,String UssdFundTSourcePassword_126){
	if(AmountField_04.contains("0000")){
	//"04:0000000500"
	String s=AmountField_04;
	String ss[]=s.split(":");
	String sss;
	sss=ss[1];
	AmountField_04=sss;
	long str= Long.parseLong(AmountField_04.trim());
	String Elk_Recharge_Amount = ""+str;
	String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
	System.out.println("UssdFundTransfer_Amount-->"+amount.trim());
	hm.put("UssdSpecialRecharge_Reversal_Amount", amount);
}
//String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
//SUCURSAL=SUCURSAL.substring(13,22).trim();

//System.out.println("SUCURSAL-->"+SUCURSAL);
//hm.put("SUCURSAL", SUCURSAL.trim());
//SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
hm.put("UssdMxSpeialRechargeReversal_SubscriberId", SubscriberId_126.trim());
SeleniumFW.APPLICATION_LOGS.info("UssdMxSpeialRechargeReversal_SubscriberId-->"+SubscriberId_126.trim());
hm.put("UssdMxSpecialRechargeReversal_DistributorNumber", UssdFundTSourceNum_126.trim());
SeleniumFW.APPLICATION_LOGS.info("UssdMxSpecialRechargeReversal_DistributorNumber-->"+UssdFundTSourceNum_126);

}
public static void MxElektraRecharge(String CsvRequest,String AmountField_04, String CurrentDate_07, String CurrDate_12_13_15_17, String CurrrDate_12_13_15_17, String CurrrrDate_12_13_15_17, String CurrrrrDate_12_13_15_17, String DistributorId_32, String SubscriberId_126){
	if(AmountField_04.contains("0000")){

		//"04:0000000500"
		String s=AmountField_04;
		String ss[]=s.split(":");
		String sss;
		sss=ss[1];
		AmountField_04=sss;
		long str= Long.parseLong(AmountField_04.trim());
		String Elk_Recharge_Amount = ""+str;
		String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
		System.out.println("Elk_Recharge_Amount-->"+amount.trim());
		//hm.put("Elk_Recharge_Amount", amount);
		SeleniumFW.APPLICATION_LOGS.info("Elk_Recharge_Amount-->"+amount);

	}
	hm.put("Elk_Recharge_DistributorId", DistributorId_32.trim());
	//System.out.println("Elk_Recharge_DistributorId-->"+DistributorId_32);
	SeleniumFW.APPLICATION_LOGS.info("Elk_Recharge_DistributorId-->"+DistributorId_32);
	hm.put("Elk_Recharge_SubscriberId-->", SubscriberId_126.trim());
	SeleniumFW.APPLICATION_LOGS.info("Elk_Recharge_SubscriberId-->"+SubscriberId_126.trim());
		
}

public static void MxTypicalbankRecharge(String CsvRequest,String AmountField_04, String CurrentDate_07, String CurrDate_12_13_15_17, String CurrrDate_12_13_15_17, String CurrrrDate_12_13_15_17, String CurrrrrDate_12_13_15_17, String DistributorId_32, String SubscriberId_126,String BankName){
	if(AmountField_04.contains("0000")){

		//"04:0000000500"
		String s=AmountField_04;
		String ss[]=s.split(":");
		String sss;
		sss=ss[1];
		AmountField_04=sss;
		long str= Long.parseLong(AmountField_04.trim());
		String Elk_Recharge_Amount = ""+str;
		String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
		System.out.println("Typical_Recharge_Bank_Amount-->"+amount.trim());
		hm.put("BankTypical_Recharge_Amount", amount);
		SeleniumFW.APPLICATION_LOGS.info("Typical_Recharge_Bank_Amount-->"+amount);

	}
	hm.put("Typical_Recharge_Bank_DistributorId", DistributorId_32.trim());
	//System.out.println("Elk_Recharge_DistributorId-->"+DistributorId_32);
	SeleniumFW.APPLICATION_LOGS.info("Typical_Recharge_Bank_DistributorId-->"+DistributorId_32);
	hm.put("Typical_Bank_Recharge_SubscriberId", SubscriberId_126.trim());
	SeleniumFW.APPLICATION_LOGS.info("Typical_Bank_Recharge_SubscriberId-->"+SubscriberId_126.trim());
	
	
	try{
	if(BankName.equalsIgnoreCase("BANCOMER")){
   	   
   	   
   	   String TERMINAL=CsvRequest.split(",")[13].split(":")[1];
				System.out.println("TERMINAL ID-->"+TERMINAL);
				hm.put("TERMINAL", TERMINAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);
			    String CANALID=CsvRequest.split(",")[14].split(":")[1];
				CANALID=CANALID.trim();
				System.out.println("CANALID-->"+CANALID);
				CANALID=CANALID.substring(5,10);
				
				hm.put("CANALID", CANALID.trim());
				SeleniumFW.APPLICATION_LOGS.info("CANALID-->"+CANALID);
				
				
				String SUCURSAL=CsvRequest.split(",")[14].split(":")[1];
				//SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
				SUCURSAL=SUCURSAL.trim();
				System.out
						.println("TERMINAL-->"+SUCURSAL);
				hm.put("SUCURSAL", SUCURSAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("SUCURSAL-->-->"+SUCURSAL);   
      }
	
	}catch(Exception e){
		e.printStackTrace();
	}
}
public static void MxSpecialbankRecharge(String CsvRequest,String AmountField_04, String CurrentDate_07, String CurrDate_12_13_15_17, String CurrrDate_12_13_15_17, String CurrrrDate_12_13_15_17, String CurrrrrDate_12_13_15_17, String DistributorId_32, String SubscriberId_126,String BankName){
	if(AmountField_04.contains("0000")){

		//"04:0000000500"
		String s=AmountField_04;
		String ss[]=s.split(":");
		String sss;
		sss=ss[1];
		AmountField_04=sss;
		long str= Long.parseLong(AmountField_04.trim());
		String Elk_Recharge_Amount = ""+str;
		String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
		System.out.println("Typical_Recharge_Bank_Amount-->"+amount.trim());
		hm.put("BankSpecial_Recharge_Amount", amount);
		SeleniumFW.APPLICATION_LOGS.info("BankSpecial_Recharge_Amount-->"+amount);

	}
	hm.put("Special_Recharge_Bank_DistributorId", DistributorId_32.trim());
	//System.out.println("Elk_Recharge_DistributorId-->"+DistributorId_32);
	SeleniumFW.APPLICATION_LOGS.info("Special_Recharge_Bank_DistributorId-->"+DistributorId_32);
	hm.put("Typical_Bank_Recharge_SubscriberId", SubscriberId_126.trim());
	SeleniumFW.APPLICATION_LOGS.info("Special_Bank_Recharge_SubscriberId-->"+SubscriberId_126.trim());
	
	
	try{
	if(BankName.equalsIgnoreCase("BANCOMER")){
   	   
   	   
   	   String TERMINAL=CsvRequest.split(",")[13].split(":")[1];
				System.out.println("TERMINAL ID-->"+TERMINAL);
				hm.put("TERMINAL", TERMINAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);
			    String CANALID=CsvRequest.split(",")[14].split(":")[1];
				CANALID=CANALID.trim();
				System.out.println("CANALID-->"+CANALID);
				CANALID=CANALID.substring(5,10);
				
				hm.put("CANALID", CANALID.trim());
				SeleniumFW.APPLICATION_LOGS.info("CANALID-->"+CANALID);
				
				
				String SUCURSAL=CsvRequest.split(",")[14].split(":")[1];
				//SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
				SUCURSAL=SUCURSAL.trim();
				System.out
						.println("TERMINAL-->"+SUCURSAL);
				hm.put("SUCURSAL", SUCURSAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("SUCURSAL-->-->"+SUCURSAL);   
      }
	
	}catch(Exception e){
		e.printStackTrace();
	}
}


public static void BankMxPocketSaleDada(String CsvRequest,String AmountField_04, String CurrentDate_07,String CurrDate_12,String CurrDate_13, String CurrDate_12_13_15_17,String CurrrDate_12_13_15_17, String DistributorId_32, String SubscriberId_126,String PocketCode_126,String BankName)
{
	if(AmountField_04.contains("0000")){
	//"04:0000000500"
	String s=AmountField_04;
	String ss[]=s.split(":");
	String sss;
	sss=ss[1];
	AmountField_04=sss;
	long str= Long.parseLong(AmountField_04.trim());
	String Elk_Recharge_Amount = ""+str;
	String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
	//System.out.println("pocketSale_Amount-->"+amount.trim());
	SeleniumFW.APPLICATION_LOGS.info("BankpocketSale_Amount-->"+amount.trim());
	hm.put("BankpocketSale_Recharge_Amount", amount);

}

hm.put("BankpocketSale_DistributorId", DistributorId_32.trim());
//System.out.println("pocketSale_DistributorId-->"+DistributorId_32);
SeleniumFW.APPLICATION_LOGS.info("BankpocketSale_DistributorId-->"+DistributorId_32);
hm.put("BankpocketSale_SubscriberId", SubscriberId_126.trim());
//System.out.println("pocketSale_SubscriberId-->"+SubscriberId_126);
SeleniumFW.APPLICATION_LOGS.info("BankpocketSale_SubscriberId-->"+SubscriberId_126);
hm.put("BankpocketCode",PocketCode_126.trim());
//System.out.println("pocketCode -->"+PocketCode_126);
SeleniumFW.APPLICATION_LOGS.info("BankpocketCode -->"+PocketCode_126);


try{
	if(BankName.equalsIgnoreCase("BANCOMER")){
   	   
   	   
   	   String TERMINAL=CsvRequest.split(",")[12].split(":")[1];
				System.out.println("TERMINAL ID-->"+TERMINAL);
				hm.put("TERMINAL", TERMINAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("TERMINAL ID-->-->"+TERMINAL);
			    String CANALID=CsvRequest.split(",")[13].split(":")[1];
				CANALID=CANALID.trim();
				System.out.println("CANALID-->"+CANALID);
				CANALID=CANALID.substring(5,10);
				
				hm.put("CANALID", CANALID.trim());
				SeleniumFW.APPLICATION_LOGS.info("CANALID-->"+CANALID);
				
				
				String SUCURSAL=CsvRequest.split(",")[13].split(":")[1];
				//SeleniumFW.APPLICATION_LOGS.info("SUCURSAL ID-->-->"+SUCURSAL);
				SUCURSAL=SUCURSAL.trim();
				System.out
						.println("TERMINAL-->"+SUCURSAL);
				hm.put("SUCURSAL", SUCURSAL.trim());
				SeleniumFW.APPLICATION_LOGS.info("SUCURSAL-->-->"+SUCURSAL);   
      }
	
	}catch(Exception e){
		e.printStackTrace();
	}



}



public static void MXPOSTPAIDBillPAYMENTS(String CsvRequest,String CurrentDate_07, String CurrDate_12, String CurrDate_13, String CurrDate_12_13_15_17, String CurrrDate_12_13_15_17, String DistributorId_32, String SubscriberId_126){
	hm.put("Bill_Payments_DistributorId", DistributorId_32.trim());
	//System.out.println("Elk_Recharge_DistributorId-->"+DistributorId_32);
	SeleniumFW.APPLICATION_LOGS.info("Bill_Payment_DistributorId-->"+DistributorId_32);
	hm.put("Bill_Payments_SubScriberId", SubscriberId_126.trim());
	SeleniumFW.APPLICATION_LOGS.info("Bill_Payment_SubscriberId-->"+SubscriberId_126.trim());
	
	//System.out.println("Elk_Recharge_SubscriberId-->"+SubscriberId_126);
}

public static void MXPOSTPAIDBillPAYMENTSRRN(String CsvRequest,String CurrentDate_07, String CurrDate_12, String CurrDate_13, String CurrDate_12_13_15_17, String CurrrDate_12_13_15_17, String DistributorId_32, String SubscriberId_126){
	hm.put("Bill_Payments_DistributorId", DistributorId_32.trim());
	//System.out.println("Elk_Recharge_DistributorId-->"+DistributorId_32);
	SeleniumFW.APPLICATION_LOGS.info("Bill_Payment_DistributorId_Rrn-->"+DistributorId_32);
	hm.put("Bill_Payments_SubScriberId", SubscriberId_126.trim());
	SeleniumFW.APPLICATION_LOGS.info("Bill_Payment_RRN Id-->"+SubscriberId_126.trim());
	
}

public static void MXPOSTPAIDBillPAYMENTSRRN(String CsvRequest,String AmountField_04,String CurrentDate_07, String CurrDate_12, String CurrDate_13, String CurrDate_12_13_15_17, String CurrrDate_12_13_15_17, String DistributorId_32, String SubscriberId_126){if(AmountField_04.contains("0000")){

	//"04:0000000500"
	String s=AmountField_04;
	String ss[]=s.split(":");
	String sss;
	sss=ss[1];
	AmountField_04=sss;
	long str= Long.parseLong(AmountField_04.trim());
	String Elk_Recharge_Amount = ""+str;
	String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
	//System.out.println("Elk_Recharge_Amount-->"+amount.trim());
	hm.put("BillPayment_Amount", amount);
	SeleniumFW.APPLICATION_LOGS.info("Bill payment Amount-->"+amount);

}
hm.put("Bill_Payments_DistributorId", DistributorId_32.trim());
//System.out.println("Elk_Recharge_DistributorId-->"+DistributorId_32);
SeleniumFW.APPLICATION_LOGS.info("Bill_Payment_DistributorId-->"+DistributorId_32);
hm.put("Bill_Payments_SubScriberId", SubscriberId_126.trim());
SeleniumFW.APPLICATION_LOGS.info("Bill_Payment_SubscriberId-->"+SubscriberId_126.trim());
}

public static void MXPOSTPAIDBillPAYMENTSRRNReversal(String CsvRequest,String AmountField_04,String CurrentDate_07, String CurrDate_12, String CurrDate_13, String CurrDate_12_13_15_17, String CurrrDate_12_13_15_17, String DistributorId_32, String SubscriberId_126){if(AmountField_04.contains("0000")){

	//"04:0000000500"
	String s=AmountField_04;
	String ss[]=s.split(":");
	String sss;
	sss=ss[1];
	AmountField_04=sss;
	long str= Long.parseLong(AmountField_04.trim());
	String Elk_Recharge_Amount = ""+str;
	String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
	//System.out.println("Elk_Recharge_Amount-->"+amount.trim());
	hm.put("BillPayment_Reversal_Amount", amount);
	SeleniumFW.APPLICATION_LOGS.info("Bill payment Amount-->"+amount);

}
hm.put("Bill_Payments_Reversal_DistributorId", DistributorId_32.trim());
//System.out.println("Elk_Recharge_DistributorId-->"+DistributorId_32);
SeleniumFW.APPLICATION_LOGS.info("Bill_Payment_Reversal_DistributorId-->"+DistributorId_32);
hm.put("Bill_Payments_Reversal_SubScriberId", SubscriberId_126.trim());
SeleniumFW.APPLICATION_LOGS.info("Bill_Payment_Reversal_SubscriberId-->"+SubscriberId_126.trim());
}

public static void MXPOSTPAIDBillPAYMENTSRRNPOS(String CsvRequest,String AmountField_04,String CurrentDate_07, String CurrDate_12, String CurrDate_13, String CurrDate_12_13_15_17,String CurrrDate_12_13_15_17, String DistributorId_32, String SubscriberId_126){
	if(AmountField_04.contains("0000")){

		//"04:0000000500"
		String s=AmountField_04;
		String ss[]=s.split(":");
		String sss;
		sss=ss[1];
		AmountField_04=sss;
		long str= Long.parseLong(AmountField_04.trim());
		String Elk_Recharge_Amount = ""+str;
		String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
		//System.out.println("Elk_Recharge_Amount-->"+amount.trim());
		hm.put("BillPayment_Amount", amount);
		SeleniumFW.APPLICATION_LOGS.info("Bill payment Amount-->"+amount);

	}
	hm.put("Bill_Payments_DistributorId", DistributorId_32.trim());
	//System.out.println("Elk_Recharge_DistributorId-->"+DistributorId_32);
	SeleniumFW.APPLICATION_LOGS.info("Bill_Payment_DistributorId_Rrn-->"+DistributorId_32);
	hm.put("Bill_Payments_SubScriberId", SubscriberId_126.trim());
	SeleniumFW.APPLICATION_LOGS.info("Bill_Payment_RRN Id-->"+SubscriberId_126.trim());
	
}


public static void MXPOSTPAIDBillPAYMENTSRRNPOSReversal(String CsvRequest,String AmountField_04,String CurrentDate_07, String CurrDate_12, String CurrDate_13, String CurrDate_12_13_15_17,String CurrrDate_12_13_15_17, String DistributorId_32, String SubscriberId_126){
	if(AmountField_04.contains("0000")){

		//"04:0000000500"
		String s=AmountField_04;
		String ss[]=s.split(":");
		String sss;
		sss=ss[1];
		AmountField_04=sss;
		long str= Long.parseLong(AmountField_04.trim());
		String Elk_Recharge_Amount = ""+str;
		String amount=Elk_Recharge_Amount.substring(0,Elk_Recharge_Amount.length()-2)+".00";
		//System.out.println("Elk_Recharge_Amount-->"+amount.trim());
		hm.put("BillPayment_Amount_RRN_Reversal", amount);
		SeleniumFW.APPLICATION_LOGS.info("Bill payment Amount-->"+amount);

	}
	hm.put("Bill_Payments_RRN_Reversal_DistributorId", DistributorId_32.trim());
	//System.out.println("Elk_Recharge_DistributorId-->"+DistributorId_32);
	SeleniumFW.APPLICATION_LOGS.info("Bill_Payment_DistributorId_Rrn-->"+DistributorId_32);
	hm.put("Bill_Payments_RRN_Reversal_SubScriberId", SubscriberId_126.trim());
	SeleniumFW.APPLICATION_LOGS.info("Bill_Payment_RRN Id-->"+SubscriberId_126.trim());
	
}
}

