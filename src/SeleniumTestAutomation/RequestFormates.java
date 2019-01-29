package SeleniumTestAutomation;

public class RequestFormates {


	//TypicalRecharge

	public static String ElektraRecharge(String s,String s1,String s2,String s3,String s4,String s5,String s6,String s7){

		String ReqElk;	
		     
		     // 0:200,3:650400,4:00000001000,7:1025225417,11:825361,12:225417,13:0307,15:0226,17:0226,32:499,35:0000000000000000=,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE09908377723
		ReqElk="0:0200,3:650400,"+s+","+s1+",11:825361,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELEA"+s7;
		//System.out.println("Elektra Request-->"+ReqElk);
		SeleniumFW.APPLICATION_LOGS.info("Elektra Recharge Request Format");
		SeleniumFW.APPLICATION_LOGS.info("ELKREQ:-->"+ReqElk);
		return ReqElk;

		/*0:0200,
	3:650400,
	4:00000002000,
	7:0116160127,
	11:008393,
	12:0116,
	13:0116,
	15:0116,
	17:0116,
	32:0000000276,
	35:0000000000000000,
	37:000000005096,
	41:MOVILWAY,
	43:ELEKTRA7075           WS_CAJA01    MX MX,
	48:044A300 05200000000000,
	49:484,
	60:012B996PRO1+000,
	61:013B996PRO10101P,
	100:03996,
	102:0000,
	126:MOVIA0910300300     DATA
		 */
	}
	
	//0:200,3:650000,4:000000000200,7:0214160127,11:008393,12:230127,13:1230,15:0214,17:0214,32:1313 SD SP,35:0000000000000000,37:000000005095,38:000000,41:MOVILWAY,43:ELEKTRA7075           BANCOMER1    MX MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,102:0000,103:00,126:MOVIA7306935028
	
	
	public static String postPaidBillPayments(String s1,String s2,String s3,String s4,String s5,String s6,String s7){

		String ReqElk;	

		ReqElk="0:0200,3:650400,4:00000000500,"+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:233456789022,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:MOVIC"+s7;
		//System.out.println("Elektra Request-->"+ReqElk);


		//0:200,3:650000,4:00000000500,7:1025225417,11:825361,12:225417,13:0226,15:0226,17:0226,32:500,35:0000000000000000=,37:233456789022,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:MOVIC0910000000



		SeleniumFW.APPLICATION_LOGS.info("post Paid Bill Payments Request Format");
		SeleniumFW.APPLICATION_LOGS.info("postPaidBillPaymentsREQ:-->"+ReqElk);
		return ReqElk;

	}public static String postPaidBillPaymentsRrn(String s1,String s2,String s3,String s4,String s5,String s6,String s7){

		String ReqElk;	

		ReqElk="0:0200,3:650400,4:00000000500,"+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:233456789022,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELEC05100000"+s7+"4     ";
		//System.out.println("Elektra Request-->"+ReqElk);

		//TELEC0510000027384364     
		//0:200,3:650000,4:00000000500,7:1025225417,11:825361,12:225417,13:0226,15:0226,17:0226,32:500,35:0000000000000000=,37:233456789022,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:MOVIC0910000000



		SeleniumFW.APPLICATION_LOGS.info("post Paid Bill Payments Request Format");
		SeleniumFW.APPLICATION_LOGS.info("postPaidBillPaymentsREQ:-->"+ReqElk);
		return ReqElk;


	}
	public static String ussdBalanceEnquiry(String s,String s1,String s2,String s3,String s4,String s5){

		String ReqUssdBalanceEnquiry;	
                           //0:0200,3:650000,4:000000000200,7:1015160127,11:008393,12:101527,13:0206,15:1015,17:1015,32:5566778800,35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIG9876665552        9876665551        1234,102:0000
		                   //0:0200,3:650000,4:000000000000,7:0214160127,11:008393,12:101527,13:0215,15:0214,17:0214,32:499,35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIB                  8897769590        1234,102:0000
		ReqUssdBalanceEnquiry="0:0200,3:650000,4:000000000000,"+s+",11:008393,"+"12:101527,"+"13:"+s1+","+"15:"+s2+","+"17:"+s3+","+",32:499,35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIB                  "+s4+"        "+s5+",102:0000";
		System.out.println("USSD Request-->"+ReqUssdBalanceEnquiry);
		SeleniumFW.APPLICATION_LOGS.info("ussdFundTransfer-->"+ReqUssdBalanceEnquiry);
		return ReqUssdBalanceEnquiry;
	}
	
	public static String ussdLastTransactionEnquiry(String s,String s1,String s2,String s3,String s4,String s5,String s6){

		String ReqUssdBalanceEnquiry;	
                           //0:0200,3:650000,4:000000000200,7:1015160127,11:008393,12:101527,13:0206,15:1015,17:1015,32:5566778800,35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIG9876665552        9876665551        1234,102:0000
		                   //0:0200,3:650000,4:000000000000,7:0214160127,11:008393,12:101527,13:0215,15:0214,17:0214,32:499,35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIB                  8897769590        1234,102:0000
		
		//0:0200,3:650000,4:000000000000,7:0214160127,11:008393,12:101527,13:0215,15:0214,17:0214,32:499,35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVID9885134431        0988611111        1111,102:0000
		ReqUssdBalanceEnquiry="0:0200,3:650000,4:000000000000,"+s+",11:008393,"+"12:101527,"+"13:"+s1+","+"15:"+s2+","+"17:"+s3+","+",32:499,35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVID"+s4+"        "+s5+"        "+s6+",102:0000";
		System.out.println("USSD Request-->"+ReqUssdBalanceEnquiry);
		SeleniumFW.APPLICATION_LOGS.info("ussdFundTransfer-->"+ReqUssdBalanceEnquiry);
		return ReqUssdBalanceEnquiry;
	}
	public static String ussdTotalSummaryoftheDay(String s,String s1,String s2,String s3,String s4,String s5){

		String ReqUssdBalanceEnquiry;	
                           //0:0200,3:650000,4:000000000200,7:1015160127,11:008393,12:101527,13:0206,15:1015,17:1015,32:5566778800,35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIG9876665552        9876665551        1234,102:0000
		                   //0:0200,3:650000,4:000000000000,7:0214160127,11:008393,12:101527,13:0215,15:0214,17:0214,32:499,35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIB                  8897769590        1234,102:0000
		ReqUssdBalanceEnquiry="0:0200,3:650000,4:000000000000,"+s+",11:008393,"+"12:101527,"+"13:"+s1+","+"15:"+s2+","+"17:"+s3+","+",32:499,35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIE                  "+s4+"        "+s5+",102:0000";
		System.out.println("USSD Request-->"+ReqUssdBalanceEnquiry);
		SeleniumFW.APPLICATION_LOGS.info("ussdFundTransfer-->"+ReqUssdBalanceEnquiry);
		return ReqUssdBalanceEnquiry;
	}
	
	public static String postPaidBillPaymentsPos(String s0,String s1,String s2,String s3,String s4,String s5,String s6,String s7){

		String ReqElk;	

		ReqElk="0:0200,3:650400,"+s0+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:233456789022,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:MOVIF"+s7;
		//System.out.println("Elektra Request-->"+ReqElk);


		//0:200,3:650000,4:00000000500,7:1025225417,11:825361,12:225417,13:0226,15:0226,17:0226,32:500,35:0000000000000000=,37:233456789022,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:MOVIC0910000000



		SeleniumFW.APPLICATION_LOGS.info("post Paid Bill Payments Request Format");
		SeleniumFW.APPLICATION_LOGS.info("postPaidBillPaymentsREQ:-->"+ReqElk);
		return ReqElk;

	}
	public static String postPaidBillPaymentsPosReversal(String s0,String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8){

		String ReqElk;	

		ReqElk="0:0420,3:650400,"+s0+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:233456789022,38:"+s7+",41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:MOVIF"+s8;
		//System.out.println("Elektra Request-->"+ReqElk);


		//0:200,3:650000,4:00000000500,7:1025225417,11:825361,12:225417,13:0226,15:0226,17:0226,32:500,35:0000000000000000=,37:233456789022,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:MOVIC0910000000



		SeleniumFW.APPLICATION_LOGS.info("post Paid Bill Payments Request Reversal Format");
		SeleniumFW.APPLICATION_LOGS.info("postPaidBillPaymentsReversalREQ:-->"+ReqElk);
		return ReqElk;

	}
	
	public static String postPaidBillPaymentsRrnPos(String s0,String s1,String s2,String s3,String s4,String s5,String s6,String s7){

		String ReqElk;	

		ReqElk="0:0200,3:650400,"+s0+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:233456789022,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELEF05100000"+s7+"4     ";
		//System.out.println("Elektra Request-->"+ReqElk);

		//TELEC0510000027384364     
		//0:200,3:650000,4:00000000500,7:1025225417,11:825361,12:225417,13:0226,15:0226,17:0226,32:500,35:0000000000000000=,37:233456789022,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:MOVIC0910000000



		SeleniumFW.APPLICATION_LOGS.info("post Paid Bill Payments Request Format");
		SeleniumFW.APPLICATION_LOGS.info("postPaidBillPaymentsREQ:-->"+ReqElk);
		return ReqElk;


	}
	public static String postPaidBillPaymentsRrnPosReversal(String s0,String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8){

		String ReqElk;	

		ReqElk="0:0420,3:650400,"+s0+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:233456789022,38:"+s7+",41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELEF05100000"+s8+"4     ";
		//System.out.println("Elektra Request-->"+ReqElk);

		//TELEC0510000027384364     
		//0:200,3:650000,4:00000000500,7:1025225417,11:825361,12:225417,13:0226,15:0226,17:0226,32:500,35:0000000000000000=,37:233456789022,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:MOVIC0910000000



		SeleniumFW.APPLICATION_LOGS.info("post Paid Bill Payments Request Format");
		SeleniumFW.APPLICATION_LOGS.info("postPaidBillPaymentsREQ:-->"+ReqElk);
		return ReqElk;


	}

	//
	
	
	
	
	public static String TypicalRecharge(String s,String s1,String s2,String s3,String s4,String s5,String s6,String s7){

		String ReqTypical=null;	
		if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){
			//Casaley
			ReqTypical="0:0200,3:650000,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,41:1163004138260199,43:CASA LEY              HUMAYA       CULMX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0400000,126:TELEA"+s7;
		}	
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
			//fsama
			ReqTypical="0:0200,3:650000,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0400000,126:TELEA"+s7;
			System.out.println("typical Request-->"+ReqTypical);	
		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){

			//SORIANA
			//0:200,3:650000,4:000000000400,7:1025225417,11:825361,12:233017,13:1230,15:0230,17:0226,32:606060601,35:0000000000000000=,37:123456789019,41:1-17-194-2      ,43:RPMHIP02340           06           MX MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:MER,126:TELEA0611111111
			ReqTypical="0:0200,3:650000,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,41:1-17-194-2      ,43:RPMHIP02340           06           MX MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:MER,126:TELEA"+s7;
		}
		SeleniumFW.APPLICATION_LOGS.info(Locators.getLocators("RECHARGE_REQUEST_VENDER")+":"+"TYPICALREQ:-->:"+ReqTypical);
		
		
		
		
		return ReqTypical;
		//0:200,3:650000,4:00000001000,7:1025225417,11:825361,12:225417,13:0307,15:0226,17:0226,32:499,35:0000000000000000=,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE000009885134431

	}
	
	
	//0:200,3:650000,4:000000000200,7:0214160127,11:008393,12:230127,13:1230,15:0214,17:0214,32:1313 SD SP,35:0000000000000000,37:000000005095,38:000000,41:MOVILWAY,43:ELEKTRA7075           BANCOMER1    MX MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,102:0000,103:00,126:MOVIA7306935028
	public static String BankTypicalRecharge(String s,String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8){

		String ReqTypical=null;	
		if(s8.equalsIgnoreCase("BANCOMER")){
			//Casaley
			ReqTypical="0:0200,3:650000,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000,37:000000005095,38:000000,41:MOVILWAY,43:ELEKTRA7075           BANCOMER1    MX MX,48:044A300 05200000000000,49:484,60:B980PRO1+000,61:013B996PRO10101P,100:03996,102:0000,103:00,126:MOVIA"+s7;
		//SeleniumFW.APPLICATION_LOGS.info("Bank Typical Recharge Req:"+":"+"TYPICALREQ:-->:"+ReqTypical);
		}
		
		SeleniumFW.APPLICATION_LOGS.info(s8+" "+":"+"TYPICAL REQ:-->:"+ReqTypical);
		return ReqTypical;
		//0:200,3:650000,4:00000001000,7:1025225417,11:825361,12:225417,13:0307,15:0226,17:0226,32:499,35:0000000000000000=,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE000009885134431

	}

//
	
	
	public static String BankpocketSaleData(String s,String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8,String s9)
	{
		String ReqpockteSale=null;
		if(s9.equalsIgnoreCase("BANCOMER")){
			//casaley
			             //0:0200,3:650000,4:000000000200,7:1224160127,11:008393,12:160127,13:0123,15:1224,17:1224,32:6889,35:0000000000000000,37:000000005096,41:MOVILWAY,43:ELEKTRA7075           WS_CAJA01    MX MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,102:0000,126:MOVIH9885134431      2222
			ReqpockteSale="0:0200,3:650000,"+s+","+s1+",11:008393,"+"12:"+s2+",13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000,37:000000005096,41:MOVILWAY,43:ELEKTRA7075           WS_CAJA01    MX MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,102:0000,126:MOVIH"+s7+"      "+s8;
		}
		SeleniumFW.APPLICATION_LOGS.info(s9+" "+":"+"TYPICAL REQ:-->:"+ReqpockteSale);
		//SeleniumFW.APPLICATION_LOGS.info(Locators.getLocators("RECHARGE_REQUEST_VENDER")+":"+"POCKETSALEREQ:-->"+ReqpockteSale);
		return ReqpockteSale;
	}
	
	
	
	public static String BankSpecialRecharge(String s,String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8){

		String ReqTypical=null;	
		
		//0:0200,3:650001,4:000000001100,7:1224160127,11:008393,12:160127,13:0123,15:1224,17:1224,32:6889,35:0000000000000000,37:000000005096,41:MOVILWAY,43:ELEKTRA7075           WS_SHAN02    MX MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,102:0000,126:MOVIA00009885134431      DATA
		//String ReqTypical=null;	
		if(s8.equalsIgnoreCase("BANCOMER")){
			//Casaley
			ReqTypical="0:0200,3:650001,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000,37:000000005095,38:000000,41:MOVILWAY,43:ELEKTRA7075           BANCOMER1    MX MX,48:044A300 05200000000000,49:484,60:B980PRO1+000,61:013B996PRO10101P,100:03996,102:0000,103:00,126:MOVIA"+s7;
		//SeleniumFW.APPLICATION_LOGS.info("Bank Typical Recharge Req:"+":"+"TYPICALREQ:-->:"+ReqTypical);
		}
		
		SeleniumFW.APPLICATION_LOGS.info(s8+" "+":"+"Special REQ:-->:"+ReqTypical);
		return ReqTypical;
		
		//0:0200,3:650100,4:00000000100,7:1025225417,11:825361,12:225417,13:0307,15:0226,17:0226,32:499,35:0000000000000000=,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE09885134431      
		//0:0200,3:650000,04:0000002000,07:0418065029,11:008393,12:0418,13:0418,15:0418,17:0418,32:688,35:0000000000000000=,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE09885134431
		/*if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){

			//casaley
			ReqTypical="0:0200,3:650100,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,41:1163004138260199,43:CASA LEY              HUMAYA       CULMX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0400000,126:TELE0"+s7;

		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
			//fasma
			ReqTypical="0:0200,3:650100,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE0"+s7;

		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){
			//SORIYANA
			ReqTypical="0:0200,3:650100,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,41:1-17-194-2      ,43:RPMHIP02340           06           MX MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:MER,126:TELEA"+s7;

		}
*/

		//System.out.println("typical Request-->"+ReqTypical);
		//SeleniumFW.APPLICATION_LOGS.info(Locators.getLocators("RECHARGE_REQUEST_VENDER")+":"+"SPECREQ:-->"+ReqTypical);
		//return ReqTypical;
		//0:200,3:650000,4:00000001000,7:1025225417,11:825361,12:225417,13:0307,15:0226,17:0226,32:499,35:0000000000000000=,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE000009885134431

	}

	
	
	
	
	
	public static String TypicalRechargeReversal(String s,String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8){

		String ReqTypical=null;	

		if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){
			//casaley
			ReqTypical="0:0420,3:650000,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,38:"+s7+",41:1163004138260199,43:CASA LEY              HUMAYA       CULMX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0400000,126:TELEA"+s8;
		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
			//fasma
			ReqTypical="0:0420,3:650000,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,38:"+s7+",41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0400000,126:TELEA"+s8;

		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){
			//SORIANA
			//0:200,3:650000,4:000000000400,7:1025225417,11:825361,12:233017,13:1230,15:0230,17:0226,32:606060601,35:0000000000000000=,37:123456789019,41:1-17-194-2      ,43:RPMHIP02340           06           MX MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:MER,126:TELEA0611111111
			ReqTypical="0:0420,3:650000,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,38:"+s7+",41:1-17-194-2      ,43:RPMHIP02340           06           MX MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:MER,126:TELEA"+s8;
		}

		SeleniumFW.APPLICATION_LOGS.info(Locators.getLocators("RECHARGE_REQUEST_VENDER")+":"+"TYPICALREVERSALREQ:-->:"+ReqTypical);
		return ReqTypical;
		//0:200,3:650000,4:00000001000,7:1025225417,11:825361,12:225417,13:0307,15:0226,17:0226,32:499,35:0000000000000000=,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE000009885134431

	}



	public static String specialRecharge(String s,String s1,String s2,String s3,String s4,String s5,String s6,String s7){

		String ReqTypical=null;	
		//0:0200,3:650100,4:00000000100,7:1025225417,11:825361,12:225417,13:0307,15:0226,17:0226,32:499,35:0000000000000000=,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE09885134431      
		//0:0200,3:650000,04:0000002000,07:0418065029,11:008393,12:0418,13:0418,15:0418,17:0418,32:688,35:0000000000000000=,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE09885134431
		if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){

			//casaley
			ReqTypical="0:0200,3:650100,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,41:1163004138260199,43:CASA LEY              HUMAYA       CULMX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0400000,126:TELE0"+s7;

		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
			//fasma
			ReqTypical="0:0200,3:650100,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE0"+s7;

		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){
			//SORIYANA
			ReqTypical="0:0200,3:650100,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,41:1-17-194-2      ,43:RPMHIP02340           06           MX MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:MER,126:TELEA"+s7;

		}


		System.out.println("typical Request-->"+ReqTypical);
		SeleniumFW.APPLICATION_LOGS.info(Locators.getLocators("RECHARGE_REQUEST_VENDER")+":"+"SPECREQ:-->"+ReqTypical);
		return ReqTypical;
		//0:200,3:650000,4:00000001000,7:1025225417,11:825361,12:225417,13:0307,15:0226,17:0226,32:499,35:0000000000000000=,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE000009885134431

	}

	public static String seguraRecharge(String s,String s1,String s2,String s3,String s4,String s5,String s6,String s7){

		String ReqTypical=null;	
		if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){
			//casaley
			ReqTypical="0:0200,3:650500,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,41:1163004138260199,43:CASA LEY              HUMAYA       CULMX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0400000,126:TELEA"+s7;

		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
			//fasma
			ReqTypical="0:0200,3:650500,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE0"+s7;

		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA"))
		{
			//SORIYANA
			ReqTypical="0:0200,3:650500,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,41:1-17-194-2      ,43:RPMHIP02340           06           MX MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:MER,126:TELEA"+s7;
		}
		//0:0200,3:650500,4:00000001000,7:1025225417,11:825361,12:225417,13:0307,15:0226,17:0226,32:499,35:0000000000000000=,37:123456789099,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE09885134431        



		System.out.println("typical Request-->"+ReqTypical);
		SeleniumFW.APPLICATION_LOGS.info(Locators.getLocators("RECHARGE_REQUEST_VENDER")+":"+"SPECREQ:-->"+ReqTypical);
		return ReqTypical;
		//0:200,3:650000,4:00000001000,7:1025225417,11:825361,12:225417,13:0307,15:0226,17:0226,32:499,35:0000000000000000=,37:123456789012,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE000009885134431

	}

	public static String seguraRechargeReversal(String s,String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8){

		String ReqTypical=null;	
		if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){
			//casaley
			ReqTypical="0:0420,3:650500,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,38:"+s7+",41:1163004138260199,43:CASA LEY              HUMAYA       CULMX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0400000,126:TELEA"+s8;
		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){
			//SORIYANA
			ReqTypical="0:0420,3:650500,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,38:"+s7+",41:1-17-194-2      ,43:RPMHIP02340           06           MX MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:MER,126:TELEA"+s8;
		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
			//fasma
			ReqTypical="0:0420,3:650500,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,38:"+s7+",41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE0"+s8;
		}

		//System.out.println("typical Request-->"+ReqTypical);
		SeleniumFW.APPLICATION_LOGS.info(Locators.getLocators("RECHARGE_REQUEST_VENDER")+":"+"SPECREQ:-->"+ReqTypical);
		return ReqTypical;


	}
	public static String specialRechargeReversal(String s,String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8){

		String ReqTypical=null;	
		if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){
			//casaley
			ReqTypical="0:0420,3:650100,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,38:"+s7+",41:1163004138260199,43:CASA LEY              HUMAYA       CULMX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0400000,126:TELEA"+s8;
		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
			//fasma
			ReqTypical="0:0420,3:650100,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,38:"+s7+",41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELE0"+s8;
		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){
			//SORIYANA
			ReqTypical="0:0420,3:650100,"+s+","+s1+",11:008393,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789012,38:"+s7+",41:1-17-194-2      ,43:RPMHIP02340           06           MX MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:MER,126:TELEA"+s8;
		}
		//System.out.println("typical Request-->"+ReqTypical);
		SeleniumFW.APPLICATION_LOGS.info(Locators.getLocators("RECHARGE_REQUEST_VENDER")+":"+"SPECREQ:-->"+ReqTypical);
		return ReqTypical;
	}
	
	//kv
	public static String IVRRechargewithEpin(String s,String s1,String s2,String s3,String s4,String s5,String s6,String s7){

		String ReqIVRRechargewithEpin;	
                           //0:0200,3:650000,4:000000001100,7:1025225417,11:825361,12:233017,13:1230,15:0230,17:0226,32:996633001,35:0000000000000000=,37:123456789019,38:000000,41:1163004138260199,43:CASA LEY              HUMAYA       CULMX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0400000,126:TELEA0000000000
		ReqIVRRechargewithEpin="0:0200,3:650000,"+s+","+s1+",11:825361,"+"12:"+s2+","+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789019,41:1163004138260199,43:CASA LEY              HUMAYA       CULMX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0400000,126:TELEA"+s7;
		System.out.println("USSD Request-->"+ReqIVRRechargewithEpin);
		SeleniumFW.APPLICATION_LOGS.info("ussdFundTransfer-->"+ReqIVRRechargewithEpin);
		return ReqIVRRechargewithEpin;
	}
	
	
	
	public static String ussdFundTransfer(String s,String s1,String s3,String s4,String s5,String s6,String s7,String s8,String s9){

		String ReqUssdFundTransfer;	
                           //0:0200,3:650000,4:000000000200,7:1015160127,11:008393,12:101527,13:0206,15:1015,17:1015,32:5566778800,35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIG9876665552        9876665551        1234,102:0000
		ReqUssdFundTransfer="0:0200,3:650000,"+s+","+s1+",11:008393,"+"12:101527,"+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIG"+s7+"        "+s8+"        "+s9+",102:0000";
		System.out.println("USSD Request-->"+ReqUssdFundTransfer);
		SeleniumFW.APPLICATION_LOGS.info("ussdFundTransfer-->"+ReqUssdFundTransfer);
		return ReqUssdFundTransfer;
	}
	public static String ussdpacketsalerecharge(String s,String s1,String s2,String s3,String s4,String s5,String s6){

		String ReqUssdFundTransfer;	
                           
		//0:0200,3:650000,4:000000003300,7:1029125433,11:007273,12:125433,13:1029,15:1029,17:1029,32:996,35:0000000000000000=,37:000000007273,41:            USSD,43:             CELLICIUM             052MX,48:A                       300   05200000000000,49:484,60:B996PR01+000,61:B996PR010101P,100:996,102:0000,126:MOVIH9581023496        8888777741        1234      SMS      16Internet 2 hora
		ReqUssdFundTransfer="0:0200,3:650000,"+s+",7:1029125433,11:007273,12:"+s1+",13:"+s2+",15:1029,17:1029,32:996,35:0000000000000000=,37:000000007273,41:            USSD,43:             CELLICIUM             052MX,48:A                       300   05200000000000,49:484,60:B996PR01+000,61:B996PR010101P,100:996,102:0000,126:MOVIH"+s3+"        "+s4+"        "+s5+"      "+"SMS"+"      "+s6;
		//ReqUssdFundTransfer="0:0200,3:650000,"+s+",7:1029125433,11:008393,12:101527,"+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIG"+s7+"        "+s8+"        "+s9+",102:0000";
		System.out.println("USSD Request-->"+ReqUssdFundTransfer);
		SeleniumFW.APPLICATION_LOGS.info(Locators.getLocators("RECHARGE_REQUEST_VENDER")+":"+"SPECREQ:-->"+ReqUssdFundTransfer);
		SeleniumFW.APPLICATION_LOGS.info("ussdpacketsalerecharge-->"+ReqUssdFundTransfer);
		return ReqUssdFundTransfer;
	}
	
	public static String ussdpacketsalerechargeReversal(String s,String s1,String s2,String s3,String s4,String s5,String s6,String s7){

		String ReqUssdFundTransfer;	
                           
		//0:0200,3:650000,4:000000003300,7:1029125433,11:007273,12:125433,13:1029,15:1029,17:1029,32:996,35:0000000000000000=,37:000000007273,41:            USSD,43:             CELLICIUM             052MX,48:A                       300   05200000000000,49:484,60:B996PR01+000,61:B996PR010101P,100:996,102:0000,126:MOVIH9581023496        8888777741        1234      SMS      16Internet 2 hora
		ReqUssdFundTransfer="0:0420,3:650000,"+s+",7:1029125433,11:007273,12:"+s1+",13:"+s2+",15:1029,17:1029,32:996,35:0000000000000000=,37:000000007273,38:"+s3+",41:            USSD,43:             CELLICIUM             052MX,48:A                       300   05200000000000,49:484,60:B996PR01+000,61:B996PR010101P,100:996,102:0000,126:MOVIH"+s4+"        "+s5+"        "+s6+"      "+"SMS"+"      "+s7;
		//ReqUssdFundTransfer="0:0200,3:650000,"+s+",7:1029125433,11:008393,12:101527,"+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIG"+s7+"        "+s8+"        "+s9+",102:0000";
		System.out.println("USSD Request-->"+ReqUssdFundTransfer);
		SeleniumFW.APPLICATION_LOGS.info(Locators.getLocators("RECHARGE_REQUEST_VENDER")+":"+"SPECREQ:-->"+ReqUssdFundTransfer);
		SeleniumFW.APPLICATION_LOGS.info("ussdpacketsalerecharge-->"+ReqUssdFundTransfer);
		return ReqUssdFundTransfer;
	}
	public static String ussdtypicalrecharge(String s,String s1,String s2,String s3,String s4,String s5){

		String ReqUssdFundTransfer;	
                           
		//0:0200,3:650000,4:000000003300,7:1029125433,11:007273,12:125433,13:1029,15:1029,17:1029,32:996,35:0000000000000000=,37:000000007273,41:            USSD,43:             CELLICIUM             052MX,48:A                       300   05200000000000,49:484,60:B996PR01+000,61:B996PR010101P,100:996,102:0000,126:MOVIH9581023496        8888777741        1234      SMS      16Internet 2 hora
		ReqUssdFundTransfer="0:0200,3:650000,"+s+",7:1029125433,11:007273,12:"+s1+",13:"+s2+",15:1029,17:1029,32:996,35:0000000000000000=,37:000000007273,41:            USSD,43:             CELLICIUM             052MX,48:A                       300   05200000000000,49:484,60:B996PR01+000,61:B996PR010101P,100:996,102:0000,126:MOVIA"+s3+"        "+s4+"        "+s5;
		//ReqUssdFundTransfer="0:0200,3:650000,"+s+",7:1029125433,11:008393,12:101527,"+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIG"+s7+"        "+s8+"        "+s9+",102:0000";
		System.out.println("USSD Request-->"+ReqUssdFundTransfer);
		SeleniumFW.APPLICATION_LOGS.info("ussdTypicalrecharge-->"+ReqUssdFundTransfer);
		return ReqUssdFundTransfer;
	}
	public static String ussdtypicalrechargeReversal(String s,String s1,String s2,String s3,String s4,String s5,String s6){

		String ReqUssdFundTransfer;	
                           
		//0:0200,3:650000,4:000000003300,7:1029125433,11:007273,12:125433,13:1029,15:1029,17:1029,32:996,35:0000000000000000=,37:000000007273,41:            USSD,43:             CELLICIUM             052MX,48:A                       300   05200000000000,49:484,60:B996PR01+000,61:B996PR010101P,100:996,102:0000,126:MOVIH9581023496        8888777741        1234      SMS      16Internet 2 hora
		ReqUssdFundTransfer="0:0420,3:650000,"+s+",7:1029125433,11:007273,12:"+s1+",13:"+s2+",15:1029,17:1029,32:996,35:0000000000000000=,37:000000007273,38:"+s3+",41:            USSD,43:             CELLICIUM             052MX,48:A                       300   05200000000000,49:484,60:B996PR01+000,61:B996PR010101P,100:996,102:0000,126:MOVIA"+s4+"        "+s5+"        "+s6;
		//ReqUssdFundTransfer="0:0200,3:650000,"+s+",7:1029125433,11:008393,12:101527,"+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIG"+s7+"        "+s8+"        "+s9+",102:0000";
		System.out.println("USSD Request-->"+ReqUssdFundTransfer);
		SeleniumFW.APPLICATION_LOGS.info("ussdTypicalrecharge-->"+ReqUssdFundTransfer);
		return ReqUssdFundTransfer;
	}

	public static String ussdSpecialRecharge(String s,String s1,String s2,String s3,String s4,String s5){

		String ReqUssdFundTransfer;	
                           
		//0:0200,3:650000,4:000000003300,7:1029125433,11:007273,12:125433,13:1029,15:1029,17:1029,32:996,35:0000000000000000=,37:000000007273,41:            USSD,43:             CELLICIUM             052MX,48:A                       300   05200000000000,49:484,60:B996PR01+000,61:B996PR010101P,100:996,102:0000,126:MOVIH9581023496        8888777741        1234      SMS      16Internet 2 hora
		ReqUssdFundTransfer="0:0200,3:650100,"+s+",7:1029125433,11:007273,12:"+s1+",13:"+s2+",15:1029,17:1029,32:996,35:0000000000000000=,37:000000007273,41:            USSD,43:             CELLICIUM             052MX,48:A                       300   05200000000000,49:484,60:B996PR01+000,61:B996PR010101P,100:996,102:0000,126:MOVIA"+s3+"        "+s4+"        "+s5;
		//ReqUssdFundTransfer="0:0200,3:650000,"+s+",7:1029125433,11:008393,12:101527,"+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIG"+s7+"        "+s8+"        "+s9+",102:0000";
		System.out.println("USSD Request-->"+ReqUssdFundTransfer);
		SeleniumFW.APPLICATION_LOGS.info("ussdSpecialRecharge-->"+ReqUssdFundTransfer);
		return ReqUssdFundTransfer;
	}

	
	public static String ussdSpecialRechargeReversal(String s,String s1,String s2,String s3,String s4,String s5,String s6){

		String ReqUssdFundTransfer;	
                           
		//0:0200,3:650000,4:000000003300,7:1029125433,11:007273,12:125433,13:1029,15:1029,17:1029,32:996,35:0000000000000000=,37:000000007273,41:            USSD,43:             CELLICIUM             052MX,48:A                       300   05200000000000,49:484,60:B996PR01+000,61:B996PR010101P,100:996,102:0000,126:MOVIH9581023496        8888777741        1234      SMS      16Internet 2 hora
		ReqUssdFundTransfer="0:0420,3:650100,"+s+",7:1029125433,11:007273,12:"+s1+",13:"+s2+",15:1029,17:1029,32:996,35:0000000000000000=,37:000000007273,38:"+s3+",41:            USSD,43:             CELLICIUM             052MX,48:A                       300   05200000000000,49:484,60:B996PR01+000,61:B996PR010101P,100:996,102:0000,126:MOVIA"+s4+"        "+s5+"        "+s6;
		//ReqUssdFundTransfer="0:0200,3:650000,"+s+",7:1029125433,11:008393,12:101527,"+"13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000,37:000000000004,41:            USSD,43:CELLICIUM             052MX,48:044A300 05200000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,126:MOVIG"+s7+"        "+s8+"        "+s9+",102:0000";
		System.out.println("USSD Request-->"+ReqUssdFundTransfer);
		SeleniumFW.APPLICATION_LOGS.info("ussdSpecialRecharge-->"+ReqUssdFundTransfer);
		return ReqUssdFundTransfer;
	}
	
	
	
	
	public static String pocketSaleData(String s,String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8)
	{
		String ReqpockteSale=null;
		if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){
			//casaley
			//0:200,3:650300,4:000000002030,7:1025225417,11:825361,12:233017,13:1632,15:0230,17:0226,32:606060601,35:0000000000000000=,37:123456789019,38:000000,41:1163004138260199,43:CASA LEY              HUMAYA       CULMX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0400000,126:TELEH9885134431      1111
			ReqpockteSale="0:0200,3:650300,"+s+","+s1+",11:008393,"+"12:"+s2+",13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789019,41:1163004138260199,43:CASA LEY              HUMAYA       CULMX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0400000,126:TELEH"+s7+"      "+s8;
		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){
			//fasma
			ReqpockteSale="0:0200,3:650300,"+s+","+s1+",11:008393,"+"12:"+s2+",13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789019,41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELEH"+s7+"      "+s8;
		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){
			//SORIYANA
			ReqpockteSale="0:0200,3:650300,"+s+","+s1+",11:008393,"+"12:"+s2+",13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789019,41:1-17-194-2      ,43:RPMHIP02340           06           MX MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:MER,126:TELEH"+s7+"      "+s8;
		}
		SeleniumFW.APPLICATION_LOGS.info(Locators.getLocators("RECHARGE_REQUEST_VENDER")+":"+"POCKETSALEREQ:-->"+ReqpockteSale);
		return ReqpockteSale;
	}
	public static String pocketSaleDataReversal(String s,String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8,String s9)
	{

		String ReqpockteSale=null;
		if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("CASALEY")){

			ReqpockteSale="0:0420,3:650300,"+s+","+s1+",11:008393,"+"12:"+s2+",13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789019,38:"+s7+",41:1163004138260199,43:CASA LEY              HUMAYA       CULMX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELEH"+s8+"      "+s9;
		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("SORIANA")){
			ReqpockteSale="0:0420,3:650300,"+s+","+s1+",11:008393,"+"12:"+s2+",13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789019,38:"+s7+",41:1-17-194-2      ,43:RPMHIP02340           06           MX MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:MER,126:TELEH"+s8+"      "+s9;

		}
		else if(Locators.getLocators("RECHARGE_REQUEST_VENDER").equalsIgnoreCase("FAMSA")){

			ReqpockteSale="0:0420,3:650300,"+s+","+s1+",11:008393,"+"12:"+s2+",13:"+s3+","+"15:"+s4+","+"17:"+s5+","+"32:"+s6+",35:0000000000000000=,37:123456789019,38:"+s7+",41:988L,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:B980PRO1+000,61:            P,100:00,102:0000,126:TELEH"+s8+"      "+s9;
		}

		SeleniumFW.APPLICATION_LOGS.info("POCKETSALEREQ:-->"+ReqpockteSale);


		//0:0200,3:650300,4:000000000100,7:0211160127,11:008393,12:160127,13:0211,15:0211,17:0211,32:151984,35:0000000000000000,37:000000005096,38:122123,41:MOVILWAY,43:eHub FAMSA (16735*82456)           NL MX,48:A                       30000048400000000000,49:484,60:012B996PRO1+000,61:013B996PRO10101P,100:03996,102:0000,126:MOVIH9642003800      DATA

		return ReqpockteSale;
	}
	/*public static String TypicalRecharge(DTO dto)
	{
		String xml;
		xml =				
			"<RECARGA_ELECTRONICA>" 
			+"<PASSWORD_IVR>"+dto.getPASSWORD_IVR()+"</PASSWORD_IVR>" 
			+"<PUNTO_VENTA>"+dto.getPUNTO_VENTA()+"</PUNTO_VENTA>" 
			+"<MONTO>"+dto.getMONTO()+"</MONTO>"
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<DI>"+dto.getDI()+"</DI>" 
			+"<TRANSACCION>"+dto.getTRANSACCION()+"</TRANSACCION>"
			+"</RECARGA_ELECTRONICA>";
		System.out.println("IVR password-----" + dto.getPASSWORD_IVR());
		System.out.println("punto vento type-----" + dto.getPUNTO_VENTA());
		System.out.println("monto type-----" + dto.getMONTO());
		System.out.println("DN type-----" + dto.getDN());
		System.out.println("DI type-----" + dto.getDI());
		System.out.println("Transaction type-----" + dto.getTRANSACCION());
		//System.out.println("hhjh"+xml);
		xml = "<![CDATA[" + xml + "]]>";   
		//System.out.println("Typical Rechrge--->" +xml);
		SeleniumFW.APPLICATION_LOGS.info("Typical"+xml);
		return xml;
	}
	
	//added by kv
	
	public static String portInRechargeInitiation(DTO dto)
	{
		String xml;
		xml =		
			"<numero_pv>"+dto.getNumero_pv()+"</numero_pv>"
			+"<clave>"+dto.getClave()+"</clave>"
			+"<numero>"+dto.getNumero()+"</numero>" 
			+"<monto>"+dto.getMonto()+"</monto>"
			+"<tiporec>"+dto.getTiporec()+"</tiporec>" 
			+"<auto_reserva>"+dto.getAuto_reserva()+"</auto_reserva>" ;
		
		System.out.println("numero_pv-----" + dto.getNumero_pv());
		System.out.println("clave-----" + dto.getClave());
		System.out.println("numero-----" + dto.getNumero());
		System.out.println("monto-----" + dto.getMonto());
		System.out.println("tiporec-----" + dto.getTiporec());
		System.out.println("auto_reserva-----" + dto.getAuto_reserva());
		System.out.println("before appending CDATA rechargeinitiation request----"+xml);
		xml = "<![CDATA[<validaPBL>" + xml + "</validaPBL>]]>";   
		//System.out.println("Typical Rechrge--->" +xml);
		SeleniumFW.APPLICATION_LOGS.info("Typical"+xml);
		return xml;
	}
	
	//added by kv
	
		public static String portInRechargeProcess(DTO dto)
		{
			String xml;
			xml =		
				"<numero_pv>"+dto.getNumero_pv()+"</numero_pv>"
				+"<clave>"+dto.getClave()+"</clave>"
				+"<numero>"+dto.getNumero()+"</numero>" 
				+"<monto>"+dto.getMonto()+"</monto>"
				+"<tiporec>"+dto.getTiporec()+"</tiporec>" 
				+"<auto_reserva>"+dto.getAuto_reserva()+"</auto_reserva>" ;
			
			System.out.println("numero_pv-----" + dto.getNumero_pv());
			System.out.println("clave-----" + dto.getClave());
			System.out.println("numero-----" + dto.getNumero());
			System.out.println("monto-----" + dto.getMonto());
			System.out.println("tiporec-----" + dto.getTiporec());
			System.out.println("auto_reserva-----" + dto.getAuto_reserva());
			//System.out.println("hhjh"+xml);
			xml = "<![CDATA[<validaPBL>" + xml + "</validaPBL>]]>";   
			//System.out.println("Typical Rechrge--->" +xml);
			SeleniumFW.APPLICATION_LOGS.info("Typical"+xml);
			return xml;
		}
	public static String pocketSaleSMSRecharge(DTO dto)
	{
		String xml;
		xml =				
			"<RECARGA_ELECTRONICA>" 
			+"<PASSWORD_IVR>"+dto.getPASSWORD_IVR()+"</PASSWORD_IVR>" 
			+"<PUNTO_VENTA>"+dto.getPUNTO_VENTA()+"</PUNTO_VENTA>" 
			+"<MONTO>"+dto.getMONTO()+"</MONTO>"
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<DI>"+dto.getDI()+"</DI>" 
			+"<TRANSACCION>"+dto.getTRANSACCION()+"</TRANSACCION>"
			+"<TIPO>"+dto.getTIPO()+"</TIPO>" 
			+"<CLAVE>"+dto.getCLAVE()+"</CLAVE>" 
			+"</RECARGA_ELECTRONICA>";
		System.out.println("IVR password-----" + dto.getPASSWORD_IVR());
		System.out.println("punto vento -----" + dto.getPUNTO_VENTA());
		System.out.println("monto -----" + dto.getMONTO());
		System.out.println("DN -----" + dto.getDN());
		System.out.println("DI -----" + dto.getDI());
		System.out.println("Transaction type-----" + dto.getTRANSACCION());
		System.out.println("TIPO -----" + dto.getTRANSACCION());
		System.out.println("CLAVE -----" + dto.getTRANSACCION());
		System.out.println("PocketSaleSMSREcharge--->"+xml);
		xml = "<![CDATA[" + xml + "]]>";   
		// System.out.println("hhjh"+xml);
		SeleniumFW.APPLICATION_LOGS.info("PocketSale"+xml);
		return xml;
	}
	public static String addMvne(DTO dto)
	{
		String xml;
		xml =				
			"<ALTA>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<IMSI>"+dto.getIMSI()+"</IMSI>"
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>" 
			+"</ALTA>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("ID_OPER_VIRT  -----" + dto.getID_OPER_VIRT());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("ICC -----" + dto.getICC());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("addMvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		// System.out.println("hhjh"+xml);
		SeleniumFW.APPLICATION_LOGS.info("addMvne xml format--->" +xml);
		return xml;
	}
	public static String addWithOutMDNMvne(DTO dto)
	{
		String xml;
		xml =				
			"<ALTA>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<IMSI>"+dto.getIMSI()+"</IMSI>"
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>" 
			+"</ALTA>";
		System.out.println("ID_OPER_VIRT  -----" + dto.getID_OPER_VIRT());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("ICC -----" + dto.getICC());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("addMvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		// System.out.println("hhjh"+xml);
		SeleniumFW.APPLICATION_LOGS.info("addMvne xml format--->" +xml);
		return xml;
	}
	public static String addWithOutOperatorIDMvne(DTO dto)
	{
		String xml;
		xml =				
			"<ALTA>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<IMSI>"+dto.getIMSI()+"</IMSI>"
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>" 
			+"</ALTA>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("ICC -----" + dto.getICC());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("addWithOutOperatorIDMvne xml format--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		// System.out.println("hhjh"+xml);
		SeleniumFW.APPLICATION_LOGS.info("addMvne xml format--->" +xml);
		return xml;
	}
	public static String addWithOutIMSIMvne(DTO dto)
	{
		String xml;
		xml =				
			"<ALTA>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>" 
			+"</ALTA>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("ID_OPER_VIRT  -----" + dto.getID_OPER_VIRT());
		System.out.println("ICC -----" + dto.getICC());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("addMvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		// System.out.println("hhjh"+xml);
		SeleniumFW.APPLICATION_LOGS.info("addWithOutIMSIMvne xml format--->" +xml);
		return xml;
	}
	public static String addWithOutICCMvne(DTO dto)
	{
		String xml;
		xml =				
			"<ALTA>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<IMSI>"+dto.getIMSI()+"</IMSI>"
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>" 
			+"</ALTA>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("ID_OPER_VIRT  -----" + dto.getID_OPER_VIRT());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("addMvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		SeleniumFW.APPLICATION_LOGS.info("addWithOutICCMvne xml format--->" +xml);
		return xml;
	}
	public static String addWithOutCODUSOMvne(DTO dto)
	{
		String xml;
		xml =				
			"<ALTA>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<IMSI>"+dto.getIMSI()+"</IMSI>"
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"</ALTA>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("ID_OPER_VIRT  -----" + dto.getID_OPER_VIRT());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("ICC -----" + dto.getICC());
		System.out.println("addMvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		SeleniumFW.APPLICATION_LOGS.info("addWithOutCODUSOMvne xml format--->" +xml);
		return xml;
	}
	public static String modifyMvne(DTO dto)
	{
		String xml;
		xml =				
			"<MOD>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<IMSI>"+dto.getIMSI()+"</IMSI>"
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>"
			+"<ESTATUS>"+dto.getESTATUS()+"</ESTATUS>"
			+"<DESCRIPCION>"+dto.getDESCRIPCION()+"</DESCRIPCION>"
			+"</MOD>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("ID_OPER_VIRT  -----" + dto.getID_OPER_VIRT());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("ICC -----" + dto.getICC());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("ESTATUS -----" + dto.getESTATUS());
		System.out.println("DESCRIPCION -----" + dto.getDESCRIPCION());
		System.out.println("addMvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		SeleniumFW.APPLICATION_LOGS.info("modify mvne xml format----->" +xml);
		return xml;
	}
	public static String deleteMvne(DTO dto)
	{
		String xml;
		xml =				
			"<BAJA>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<IMSI>"+dto.getIMSI()+"</IMSI>"
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>"
			+"<DESCRIPCION>"+dto.getDESCRIPCION()+"</DESCRIPCION>"
			+"</BAJA>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("ID_OPER_VIRT  -----" + dto.getID_OPER_VIRT());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("ICC -----" + dto.getICC());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("DESCRIPCION -----" + dto.getDESCRIPCION());
		System.out.println("delete Mvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		SeleniumFW.APPLICATION_LOGS.info("delete mvne xml format----->" +xml);
		return xml;
	}
	public static String modifyWithOutMDNMvne(DTO dto)
	{
		String xml;
		xml =				
			"<MOD>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<IMSI>"+dto.getIMSI()+"</IMSI>"
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>"
			+"<ESTATUS>"+dto.getESTATUS()+"</ESTATUS>"
			+"<DESCRIPCION>"+dto.getDESCRIPCION()+"</DESCRIPCION>"
			+"</MOD>";
		System.out.println("ID_OPER_VIRT  -----" + dto.getID_OPER_VIRT());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("ICC -----" + dto.getICC());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("DESCRIPCION -----" + dto.getDESCRIPCION());
		SeleniumFW.APPLICATION_LOGS.info("--------------------------------------------------------");
		SeleniumFW.APPLICATION_LOGS.info("modifyWithOutMDNMvne format----->" +xml);
		SeleniumFW.APPLICATION_LOGS.info("--------------------------------------------------------");
		xml = "<![CDATA[" + xml + "]]>";   
		SeleniumFW.APPLICATION_LOGS.info("modifyWithOutMDNMvne format----->" +xml);
		return xml;
	}
	public static String modifyWithOutOperatorIDMvne(DTO dto)
	{
		String xml;
		xml =				
			"<MOD>" 
			+"<DN>"+dto.getDN()+"</DN>"
			+"<IMSI>"+dto.getIMSI()+"</IMSI>"
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>"
			+"<ESTATUS>"+dto.getESTATUS()+"</ESTATUS>"
			+"<DESCRIPCION>"+dto.getDESCRIPCION()+"</DESCRIPCION>"
			+"</MOD>";
		System.out.println("DN ----->" + dto.getDN());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("ICC -----" + dto.getICC());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("DESCRIPCION -----" + dto.getDESCRIPCION());
		SeleniumFW.APPLICATION_LOGS.info("--------------------------------------------------------");
		SeleniumFW.APPLICATION_LOGS.info("modifyWithOutOperatorIDMvne format----->" +xml);
		SeleniumFW.APPLICATION_LOGS.info("--------------------------------------------------------");
		xml = "<![CDATA[" + xml + "]]>";   
		SeleniumFW.APPLICATION_LOGS.info("modifyWithOutOperatorIDMvne format----->" +xml);
		return xml;
	}
	public static String modifyWithOutIMSIRequest(DTO dto)
	{
		String xml;
		xml =				
			"<MOD>" 
			+"<DN>"+dto.getDN()+"</DN>"
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>"
			+"<ESTATUS>"+dto.getESTATUS()+"</ESTATUS>"
			+"<DESCRIPCION>"+dto.getDESCRIPCION()+"</DESCRIPCION>"
			+"</MOD>";
		System.out.println("DN ----->" + dto.getDN());
		System.out.println("ID_OPER_VIRT -----" + dto.getID_OPER_VIRT());
		System.out.println("ICC -----" + dto.getICC());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("DESCRIPCION -----" + dto.getDESCRIPCION());
		SeleniumFW.APPLICATION_LOGS.info("--------------------------------------------------------");
		SeleniumFW.APPLICATION_LOGS.info("modifyWithOutIMSIRequest format----->" +xml);
		SeleniumFW.APPLICATION_LOGS.info("--------------------------------------------------------");
		xml = "<![CDATA[" + xml + "]]>";   
		SeleniumFW.APPLICATION_LOGS.info("modifyWithOutOperatorIDMvne format----->" +xml);
		return xml;
	}
	public static String modifyWithOutICCRequest(DTO dto)
	{
		String xml;
		xml =				
			"<MOD>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<IMSI>"+dto.getIMSI()+"</IMSI>"
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>"
			+"<ESTATUS>"+dto.getESTATUS()+"</ESTATUS>"
			+"<DESCRIPCION>"+dto.getDESCRIPCION()+"</DESCRIPCION>"
			+"</MOD>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("ID_OPER_VIRT  -----" + dto.getID_OPER_VIRT());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("ESTATUS -----" + dto.getESTATUS());
		System.out.println("DESCRIPCION -----" + dto.getDESCRIPCION());
		System.out.println("addMvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		SeleniumFW.APPLICATION_LOGS.info("modify mvne xml format----->" +xml);
		return xml;
	}
	public static String MOD_DNWithOutCOD_USORequest(DTO dto)
	{
		String xml;
		xml =				
			"<MOD>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<IMSI>"+dto.getIMSI()+"</IMSI>"
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"<ESTATUS>"+dto.getESTATUS()+"</ESTATUS>"
			+"<DESCRIPCION>"+dto.getDESCRIPCION()+"</DESCRIPCION>"
			+"</MOD>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("ID_OPER_VIRT  -----" + dto.getID_OPER_VIRT());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("ESTATUS -----" + dto.getESTATUS());
		System.out.println("DESCRIPCION -----" + dto.getDESCRIPCION());
		System.out.println("addMvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		SeleniumFW.APPLICATION_LOGS.info("modify mvne xml format----->" +xml);
		return xml;
	}
	public static String enquiryMvne(DTO dto)
	{
		String xml;
		xml =				
			"<CONS>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"</CONS>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("ID_OPER_VIRT  -----" + dto.getID_OPER_VIRT());
		System.out.println("enquiryMvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		SeleniumFW.APPLICATION_LOGS.info("enquiryMvne xml format--->" +xml);
		return xml;
	}
	public static String enquiryWithoutMDNMvne(DTO dto)
	{
		String xml;
		xml =				
			"<CONS>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"</CONS>";
		System.out.println("ID_OPER_VIRT  -----" + dto.getID_OPER_VIRT());
		System.out.println("enquiryMvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		SeleniumFW.APPLICATION_LOGS.info("enquiryWithoutMDNMvne xml format--->" +xml);
		return xml;
	}public static String enquiryWithOutOperatorIDMvne(DTO dto)
	{
		String xml;
		xml =				
			"<CONS>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"</CONS>";
		System.out.println("DN  -----" + dto.getDN());
		System.out.println("enquiryWithOutOperatorIDMvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		SeleniumFW.APPLICATION_LOGS.info("enquiryWithOutOperatorIDMvne xml format--->" +xml);
		return xml;
	}
	public static String deleteWithOutDescriptionMvne(DTO dto)
	{
		String xml;
		xml =				
			"<BAJA>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<IMSI>"+dto.getIMSI()+"</IMSI>"
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>"
			+"</BAJA>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("ID_OPER_VIRT  -----" + dto.getID_OPER_VIRT());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("ICC -----" + dto.getICC());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("deleteWithOutDescription Mvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		// System.out.println("hhjh"+xml);
		SeleniumFW.APPLICATION_LOGS.info("delete mvne xml format----->" +xml);
		return xml;
	}
	public static String deleteWithOutICCandIMSIMvne(DTO dto)
	{
		String xml;
		xml =				
			"<BAJA>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>"
			+"<DESCRIPCION>"+dto.getDESCRIPCION()+"</DESCRIPCION>"
			+"</BAJA>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("ID_OPER_VIRT  -----" + dto.getID_OPER_VIRT());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("DESCRIPCION -----" + dto.getDESCRIPCION());
		System.out.println("delete Mvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		// System.out.println("hhjh"+xml);
		SeleniumFW.APPLICATION_LOGS.info("delete mvne xml format----->" +xml);
		return xml;
	}
	public static String deleteWithOutOperatorIDMvne(DTO dto)
	{
		String xml;
		xml =				
			"<BAJA>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<IMSI>"+dto.getIMSI()+"</IMSI>"
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>"
			+"<DESCRIPCION>"+dto.getDESCRIPCION()+"</DESCRIPCION>"
			+"</BAJA>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("ICC -----" + dto.getICC());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("DESCRIPCION -----" + dto.getDESCRIPCION());
		System.out.println("delete Mvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		// System.out.println("hhjh"+xml);
		SeleniumFW.APPLICATION_LOGS.info("delete mvne xml format----->" +xml);
		return xml;
	}
	public static String deleteWithOutMDNMvne(DTO dto)
	{
		String xml;
		xml =				
			"<BAJA>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<IMSI>"+dto.getIMSI()+"</IMSI>"
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>"
			+"<DESCRIPCION>"+dto.getDESCRIPCION()+"</DESCRIPCION>"
			+"</BAJA>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("ICC -----" + dto.getICC());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("DESCRIPCION -----" + dto.getDESCRIPCION());
		System.out.println("delete Mvne--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		// System.out.println("hhjh"+xml);
		SeleniumFW.APPLICATION_LOGS.info("deleteWithOutMDNMvne xml format----->" +xml);
		return xml;
	}
	public static String deleteWithOutIMSIMvne(DTO dto)
	{
		String xml;
		xml =				
			"<BAJA>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>"
			+"<DESCRIPCION>"+dto.getDESCRIPCION()+"</DESCRIPCION>"
			+"</BAJA>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("ICC -----" + dto.getICC());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("DESCRIPCION -----" + dto.getDESCRIPCION());
		System.out.println("deleteWithOutIMSIMvne xml format--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		// System.out.println("hhjh"+xml);
		SeleniumFW.APPLICATION_LOGS.info("deleteWithOutIMSIMvne xml format----->" +xml);
		return xml;
	}
	public static String deleteWithOutICCMvne(DTO dto)
	{
		String xml;
		xml =				
			"<BAJA>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<IMSI>"+dto.getIMSI()+"</IMSI>" 
			+"<COD_USO>"+dto.getCOD_USO()+"</COD_USO>"
			+"<DESCRIPCION>"+dto.getDESCRIPCION()+"</DESCRIPCION>"
			+"</BAJA>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("COD_USO -----" + dto.getCOD_USO());
		System.out.println("DESCRIPCION -----" + dto.getDESCRIPCION());
		System.out.println("deleteWithOutICCMvne xml format--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		// System.out.println("hhjh"+xml);
		SeleniumFW.APPLICATION_LOGS.info("deleteWithOutIMSIMvne xml format----->" +xml);
		return xml;
	}
	public static String deleteWithOutCOD_USOMvne(DTO dto)
	{
		String xml;
		xml =				
			"<BAJA>" 
			+"<DN>"+dto.getDN()+"</DN>" 
			+"<ID_OPER_VIRT>"+dto.getID_OPER_VIRT()+"</ID_OPER_VIRT>" 
			+"<IMSI>"+dto.getIMSI()+"</IMSI>" 
			+"<ICC>"+dto.getICC()+"</ICC>" 
			+"<DESCRIPCION>"+dto.getDESCRIPCION()+"</DESCRIPCION>"
			+"</BAJA>";
		System.out.println("DN password-----" + dto.getDN());
		System.out.println("ICC -----" + dto.getICC());
		System.out.println("IMSI -----" + dto.getIMSI());
		System.out.println("DESCRIPCION -----" + dto.getDESCRIPCION());
		System.out.println("deleteWithOutCOD_USOMvne xml format--->" +xml);
		xml = "<![CDATA[" + xml + "]]>";   
		// System.out.println("hhjh"+xml);
		SeleniumFW.APPLICATION_LOGS.info("deleteWithOutCOD_USOMvne xml format----->" +xml);
		return xml;
	}
	public static String garbageString(DTO dto)
	{
		String xml;
		xml =dto.getGarbageString();				
		SeleniumFW.APPLICATION_LOGS.info("garbageString string----->" +xml);
		return xml;
	}*/
}
