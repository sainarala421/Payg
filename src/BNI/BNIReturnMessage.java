package BNI;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.aircls.common.Utilities;

public class BNIReturnMessage {

	static Logger Log	= Logger.getLogger(BNIReturnMessage.class);
	private static Hashtable returnMsgHt = new Hashtable();

/*
000 --- "SUCCESS";
001 --- "INVALID MESSAGE NUMBER"
002 --- "INVALID SUBSCRIBER NUMBER";
003 --- "INVALID DENOMINATION VALUE";
004 --- "INVALID BANK IDENTIFICATION";
005 --- "INVALID AMOUNT";
006 --- "INVALID CARD TYPE";
007 --- "INVALID EXPIRY DATE";
008 --- "INCORRECT MESSAGE";
009 --- "INVALID BANK TRANSACTION NUMBER";
010 --- "INVALID AIRERS TRANSACTION NUMBER";
011 --- "INCORRECT VALUE";
012 --- "TRANSACTION DOES NOT EXIST FOR REVERSAL";
013 --- "TRANSACTION ALREADY REVERSED";
014 --- "INVALID CARD NUMBER";
015 --- "INVALID SECURITY CODE";
016 --- "INVALID ACCOUNT TYPE";
017 --- "INVALID EXPIRY DATE";
018 --- "INVALID ISSUER";
019 --- "SPENDING LIMIT EXCEEDED";
020 --- "FUNDS NOT AVAILABLE";
021 --- "REJECTED";
022 --- "INVALID ROUTING NUMBER";
099 --- "GENERAL ERROR";
*/

	public static final String SUCCESS									= "000";
	public static final String INVALID_MESSAGE_NUMBER					= "001";
	public static final String INVALID_SUBSCRIBER_NUMBER				= "002";
	public static final String INVALID_DENOMINATION_VALUE				= "003";
	public static final String INVALID_BANK_IDENTIFICATION				= "004";
	public static final String INVALID_AMOUNT							= "005";
	public static final String INVALID_CARD_TYPE						= "006";
	public static final String INVALID_EXPIRY_DATE						= "007";
	public static final String INCORRECT_MESSAGE						= "008";
	public static final String INVALID_BANK_TRANSACTION_NUMBER			= "009";
	public static final String INVALID_AIRERS_TRANSACTION_NUMBER		= "010";
	public static final String INCORRECT_VALUE							= "011";
	public static final String TRANSACTION_DOES_NOT_EXIST_FOR_REVERSAL	= "012";
	public static final String TRANSACTION_ALREADY_REVERSED				= "013";
	public static final String INVALID_CARD_NUMBER						= "014";
	public static final String INVALID_SECURITY_CODE					= "015";
	public static final String INVALID_ACCOUNT_TYPE						= "016";
	//public static final String INVALID_EXPIRY_DATE1						= "017";	Ignore this return message
	public static final String INVALID_ISSUER							= "018";
	public static final String SPENDING_LIMIT_EXCEEDED					= "019";
	public static final String FUNDS_NOT_AVAILABLE						= "020";
	public static final String REJECTED									= "021";
	public static final String INVALID_ROUTING_NUMBER					= "022";
	public static final String GENERAL_ERROR							= "099";

	public static void loadReturnMessages()
	{
		try
		{
			returnMsgHt.put(SUCCESS,"SUCCESS");
			returnMsgHt.put(INVALID_MESSAGE_NUMBER,"INVALID MESSAGE NUMBER");
			returnMsgHt.put(INVALID_SUBSCRIBER_NUMBER,"INVALID SUBSCRIBER NUMBER");
			returnMsgHt.put(INVALID_DENOMINATION_VALUE,"INVALID DENOMINATION VALUE");
			returnMsgHt.put(INVALID_BANK_IDENTIFICATION,"INVALID BANK IDENTIFICATION");
			returnMsgHt.put(INVALID_AMOUNT,"INVALID AMOUNT");
			returnMsgHt.put(INVALID_CARD_TYPE,"INVALID CARD TYPE");
			returnMsgHt.put(INVALID_EXPIRY_DATE,"INVALID EXPIRY DATE");
			returnMsgHt.put(INCORRECT_MESSAGE,"INCORRECT MESSAGE");
			returnMsgHt.put(INVALID_BANK_TRANSACTION_NUMBER,"INVALID BANK TRANSACTION NUMBER");
			returnMsgHt.put(INVALID_AIRERS_TRANSACTION_NUMBER,"INVALID AIRERS TRANSACTION NUMBER");
			returnMsgHt.put(INCORRECT_VALUE,"INCORRECT VALUE");
			returnMsgHt.put(TRANSACTION_DOES_NOT_EXIST_FOR_REVERSAL,"TRANSACTION DOES NOT EXIST FOR REVERSAL");
			returnMsgHt.put(TRANSACTION_ALREADY_REVERSED,"TRANSACTION ALREADY REVERSED");
			returnMsgHt.put(INVALID_CARD_NUMBER,"INVALID CARD NUMBER");
			returnMsgHt.put(INVALID_SECURITY_CODE,"INVALID SECURITY CODE");
			returnMsgHt.put(INVALID_ACCOUNT_TYPE,"INVALID ACCOUNT TYPE");
//			returnMsgHt.put(INVALID_EXPIRY_DATE1,"INVALID EXPIRY DATE"); *********** Ignore this *********
			returnMsgHt.put(INVALID_ISSUER,"INVALID ISSUER");
			returnMsgHt.put(SPENDING_LIMIT_EXCEEDED,"SPENDING LIMIT EXCEEDED");
			returnMsgHt.put(FUNDS_NOT_AVAILABLE,"FUNDS NOT AVAILABLE");
			returnMsgHt.put(REJECTED,"REJECTED");
			returnMsgHt.put(INVALID_ROUTING_NUMBER,"INVALID ROUTING NUMBER");
			returnMsgHt.put(GENERAL_ERROR,"GENERAL ERROR");
		} catch (Exception exp) {
			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"ATMInterface::BNIReturnMessage.loadReturnMessages()");
		}
	}// END -- public static loadReturnMessages()

	public static String getReturnMessage(String messageId) {

		return ((String) returnMsgHt.get(messageId));

	}// END -- public static String getReturnMessage(String messageId)

}// END -- public class BNIReturnMessage
