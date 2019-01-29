package atm.util;


public class ATMConstants
{

	public static String REPLENISHMENT_CHANNEL_ID		= "";

	public static final short 	REQUEST					= 0x01;
	public static final short 	RESPONSE				= 0x02;
	public static final char 	KV_SEPERATOR			= '=';
	public static final char 	ITEM_SEPERATOR			= '&';
	public static final String 	CRYPT_ALGORITHM			= "DES";
	public static final String 	CLOSETAG				= "ATM_MSG_END";

	/******************************************
		--------------------------------
		KEY			--	D-TYPE		SIZE
		--------------------------------
		CEDRUC		--	Chain		13
		CLAVE		--	Chain		4
		CODMSG		--	Whole		2
		FECHEX 		--	TDate		4
		MID			--	Whole		15
		EMISOR		--	Chain		20
		EXPIRACION	--	Date		10
		NOMCLI		--	Chain		50
		NUMACT		--	Whole		20
		NUMCTA		--	Chain		12
		NUMAUT		--	Whole		12
		NUMREF		--	Whole		19
		NUMNV		--	Whole		12
		NUMSERIE	--	Whole		20
		NUMTRAN		--	Whole		15
		NUMTAR		--	Whole		17
		RETMSG		--	Chain		50
		RETORNO		--	Chain		5
		TELEFONO	--	Whole		7
		TIPOAUT		--	Whole		1
		TIPOCTA		--	Chain		2
		VALOR		--	Floating	13
		VALORICE	--	Floating	7
		VALORIVA	--	Floating	7
		VALORN		--	Floating	13
		FECHES		--
		CEDULA		--
		PROPIETARIO	--
		RELACION	--
		FECHAAPE	--
		ESTADO		--
		PROJUS		--
		PROINJUS	--
		PROMEDIO	--
	******************************************/

	//Parameter constants
	public static final String CEDRUC		= "CEDRUC";
	public static final String CLAVE		= "CLAVE";
	public static final String CODMSG		= "CODMSG";
	public static final String FECHEX 		= "FECHEX";
	public static final String MID			= "MID";
	public static final String EMISOR		= "EMISOR";
	public static final String EXPIRACION	= "EXPIRACION";
	public static final String NOMCLI		= "NOMCLI";
	public static final String NUMACT		= "NUMACT";
	public static final String NUMCTA		= "NUMCTA";
	public static final String NUMAUT		= "NUMAUT";
	public static final String NUMREF		= "NUMREF";
	public static final String NUMNV		= "NUMNV";
	public static final String NUMSERIE		= "NUMSERIE";
	public static final String NUMTRAN		= "NUMTRAN";
	public static final String NUMTAR		= "NUMTAR";
	public static final String RETMSG		= "RETMSG";
	public static final String RETORNO		= "RETORNO";
	public static final String TELEFONO		= "TELEFONO";
	public static final String TIPOAUT		= "TIPOAUT";
	public static final String TIPOCTA		= "TIPOCTA";
	public static final String VALOR		= "VALOR";
	public static final String VALORICE		= "VALORICE";
	public static final String VALORIVA		= "VALORIVA";
	public static final String VALORN		= "VALORN";
	// newly added 27th Aug, 2003
	public static final String FECHES		= "FECHES";
	public static final String CEDULA		= "CEDULA";
	public static final String PROPIETARIO	= "PROPIETARIO";
	public static final String RELACION		= "RELACION";
	public static final String FECHAAPE		= "FECHAAPE";
	public static final String ESTADO		= "ESTADO";
	public static final String PROJUS		= "PROJUS";
	public static final String PROINJUS		= "PROINJUS";
	public static final String PROMEDIO		= "PROMEDIO";
	public static final String CARRIERID		= "OPERATORIDENTITY";

	// newly added Message Constants , 27th Aug, 2003
	public static final String ATM11$CREDIT_CARD_AUTHORIZATION		= "11";
	public static final String ATM12$DEBIT_CARD_AUTHORIZATION		= "12";
	public static final String ATM13$DEBIT_TO_ACCOUNT_AUTHORIZATION = "13";
	public static final String ATM14$REVERSE_AUTHORIZATION 			= "14";
	public static final String ATM15$CREDIT_REPORT 					= "15";


	// Message Constants
	public static final String ATM21$VALIDATE_TELEPHONE 			= "21";
	public static final String ATM22$ACCREDITATION_MINUTES 			= "22";
	public static final String ATM23$PURCHASE_ACTIVATION_CODE 		= "23";
	public static final String ATM24$REVERSE						= "24";
	public static final String ATM30$ADMIN_TRANSACTION				= "30";
	
	public static String getMessage(String errorCode)
	{

		String message = "UNKNOWN ERROR";

		if(errorCode.equals("000")) 		{ message = "SUCCESS"; 									}
		else if(errorCode.equals("001")) { message = "PHONE NOT EXIST"; 					}
		else if(errorCode.equals("002")) { message = "INACTIVE PHONE"; 					}
		else if(errorCode.equals("010")) { message = "DUPLICATE TRANSACTION"; 				}
		else if(errorCode.equals("011")) { message = "INCORRECT VALUE"; 						}
		else if(errorCode.equals("020")) { message = "TRANSACTION NOT EXIST FOR REVERSING";							}
		else if(errorCode.equals("021")) { message = "TRANS PREVIOUSLY REVERSED"; 						}
		else if(errorCode.equals("022")) { message = "AMOUNTS ALREADY CONSUMED CANNOT BE REVERSED"; 						}
		else if(errorCode.equals("998")) { message = "GENERAL ERROR INCORRECT MESSAGE"; 					}
		else if(errorCode.equals("999")) { message = "GENERAL ERROR SYSTEM NOT AVAILABLE"; 					}
		
		return message;

	}
	

} // END -- ATMConstants
