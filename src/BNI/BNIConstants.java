package BNI;

import java.util.Hashtable;

public class BNIConstants
{

	public static final short 	REQUEST					= 0x01;
	public static final short 	RESPONSE				= 0x02;
	public static final char 	KV_SEPERATOR			= '=';
	public static final char 	ITEM_SEPERATOR			= ';';
	public static final String 	CRYPT_ALGORITHM			= "DES";
	public static final String 	CLOSETAG				= "BNI_MSG_END";
	public static final String 	LINE_SEPERATOR			= ".";

	//public static Hashtable connectionHt				= BNIUtil.getTransactionConnectionDetails("1014");
	//public static final String 	BANK_ID					= (String)connectionHt.get("BANKID");
	public static Hashtable dcdHt						= null;
	public static String encKey							= "42386C3173303874";
	public static String ENC_FORMAT_HEXA				= "HEXA";
	public static String ENC_FORMAT_ALPHA				= "ALPHA";

	public static String ENC_TYPE_DES				= "DES";
	public static String ENC_TYPE_3DES				= "3DES";

/*
	====================================================
	Parameter 	Datatype 		 	MaxLength
	====================================================
	ACTBAL 		Numeric				10, 2 -Variable
	ACCCDT 		Numeric				10, 2 -Variable
	ACCTYP 		Numeric				2
	AUTHNO 		AlphaNumeric		20(Variable)NOTNULL
	BTRNNO 		AlphaNumeric		20 -Variable
	CRDTYP 		AlphaNumeric		3  -Variable
	CRDNUM 		AlphaNumeric		25 -Variable
	CTRNNO 		AlphaNumeric		25 -Variable
	DIALNO 		Dial Number			25 -Variable
	ERRCDE 		Numeric				3  -Fixed
	EXPDTE 		AlphaNumeric		5  -Fixed
	ISSNME 		AlphaNumeric		15 -Variable
	LENMSG 		Numeric				5  -Fixed
	MSGNUM 		AlphaNumeric		3  -Fixed
	NETWID 		AlphaNumeric		15 -Variable
	PRMAMT 		AlphaNumeric		15 -Variable
	PPINNO 		AlphaNumeric		25 -Variable)
	REQAMT 		Numeric				10,2-Variable
	SECCDE 		Numeric				10
	TAXICE 		Numeric				10, 2 - Variable
	TAXIVA 		Numeric				10, 2 - Variable
	TELNUM 		AlphaNumeric-		15 - Variable
	TERMID 		AlphaNumeric		15 - Variable


====================================================
*/

	public static final String ACTBAL	= "ACTBAL";
	public static final String ACCCDT	= "ACCCDT";
	public static final String ACCTYP	= "ACCTYP";
	public static final String AUTHNO	= "AUTHNO";
	public static final String BTRNNO	= "BTRNNO";
	public static final String CRDTYP	= "CRDTYP";
	public static final String CRDNUM	= "CRDNUM";
	public static final String CTRNNO	= "CTRNNO";
	public static final String DIALNO	= "DIALNO";
	public static final String ERRCDE	= "ERRCDE";
	public static final String EXPDTE	= "EXPDTE";
	public static final String ISSNME	= "ISSNME";
	public static final String LENMSG	= "LENMSG";
	public static final String MSGNUM	= "MSGNUM";
	public static final String NETWID	= "NETWID";
	public static final String PRMAMT	= "PRMAMT";
	public static final String PPINNO	= "PPINNO";
	public static final String REQAMT	= "REQAMT";
	public static final String SECCDE	= "SECCDE";
	public static final String TAXICE	= "TAXICE";
	public static final String TAXIVA	= "TAXIVA";
	public static final String TELNUM	= "TELNUM";
	public static final String TERMID	= "TERMID";
	public static final String OPERATORIDENTITY	= "OPERATORIDENTITY";


// messages for reversal
	public static final String ACCNUM	="ACCNUM";
	public static final String ROUNUM	="ROUNUM";
	public static final String EXPDAT	="EXPDAT";
	public static final String RETORNO	="RETORNO";

	// Message Constants
	public static final String BNI01$ENQUIRY 		= "001";
	public static final String BNI02$REPLENISHMENT 	= "002";
	public static final String BNI03$ECHO 			= "003";
	public static final String BNI04$REVERSAL		= "004";
	public static final String BNI05$TRANSACTIONSTATUS	= "005";

	public static final String BNI10CREDITANDDEBITCARDAUTHORIZATION = "10";
	public static final String BNI11DEBITACCOUNTAUTHORIZATION		= "11";
	public static final String BNI12REVERSAL						= "12";
}