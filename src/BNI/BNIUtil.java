
package BNI;

// Java SDK v1.3.1 imports
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.aircls.common.MCipherPad;
import com.aircls.common.Utilities;
import com.aircls.dbconnection.DBConnection;
import com.aircls.utilities.Cryptography;

public class BNIUtil {

	static Logger Log	= Logger.getLogger(BNIUtil.class);
	private static Random		_random			= null;
	private static int 			n 				= 2000000000;

	private static Hashtable msgHt = new Hashtable();

	BNIUtil () {

	}// END -- BNIUtil ()

	public static String getURLEncodedString(String message) {

		return URLEncoder.encode(message);

	}

	public static String getURLDecodedString(String message) {

		return URLDecoder.decode(message);
	}

	public static String decrypt(char[] message) {
		Cryptography crypt = new Cryptography();
		message = crypt.decryptCharData("DES", "/apps/weblogic/airtel/classes/atminterface/key.txt", message);
		System.out.println("Message after decryt:  "+new String(message));
		return new String(message);
	}

	public static char[] encrypt(char[] message) {

		try
		{
		Cryptography crypt = new Cryptography();
		message = crypt.encryptCharData("DES", "/apps/weblogic/airtel/classes/atminterface/key.txt", message);
		System.out.println("Message after encryt:  "+message.toString());
	}
	catch(Exception exp){
				 System.out.println("Exception while encryption :" + exp.getMessage());
		}
		return message;
	}

	public static String genTransNbr() {

		String str = "";

		try {

			if(_random == null)	_random = new Random();
			str = String.valueOf(new Date().getTime() + _random.nextInt(n));

		} catch (Exception exp) {

			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
				"BNIInterface::BNIUtil.genTransNbr()");
		}

		return str;
	}

/*	public static PrePaid getPrepaidObj() {

		try {

			//if (homeObj == null)		homeObj 		= HomeLoader.invoker();
			//if (prepaidRemote==null)	prepaidRemote	= homeObj.prePaidHome.create();


		} catch (Exception exp) {

			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"BNIInterface::BNIUtil.getPrepaidObj()");
			return null;
		}

		return prepaidRemote;
	}*/

	/*public static External getExternalObj() {

		try {

			//if(homeObj == null)			homeObj 		= HomeLoader.invoker();
			//if(externalRemote==null)	externalRemote	= homeObj.externalHome.create();

			//return homeObj.externalHome.create();

		} catch (Exception exp) {

			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"BNIInterface::BNIUtil.getExternalObj()");
			return null;
		}

		//return externalRemote;
		return null;
	}*/

	/*public static Subscriber getSubscriberObj() {

		try {

			//if(homeObj == null)			homeObj 		= HomeLoader.invoker();
			//if(subscriberRemote==null)	subscriberRemote= homeObj.subscriberHome.create();

		} catch (Exception exp) {

			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"BNIInterface::BNIUtil.getSubscriberObj()");
			return null;
		}

		return subscriberRemote;
	}*/

	public static Hashtable getDenominations() {

		Hashtable htDenom = null;
		try {

				//if(prepaidRemote==null)
				//	getPrepaidObj();

				//htDenom = prepaidRemote.getDenominationIdName();

			} catch (Exception exp) {

				Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"BNIInterface::BNIUtil.getDenominations()");
			}

		return htDenom;
	}

	public static String getDenominationId (String denomValue) {

			String 		denomId       	= "";
			Hashtable	denomHt			= null;

			double denominationValue 	= 0.0;

		try {
				denomHt				= getDenominations();
				denominationValue	= Double.parseDouble(denomValue);

				if (denomHt != null  && denomHt.size()>0) {

					Enumeration enumb = denomHt.keys();

					while(enumb.hasMoreElements()) {

						String tempStrId = (String)enumb.nextElement();
						String tempStrValue = (String) denomHt.get(tempStrId);

						tempStrValue = (tempStrValue == null || tempStrValue.trim().length()==0)?
										"0.0":tempStrValue;

						double tempDblValue = Double.parseDouble(tempStrValue);

						if (denominationValue == tempDblValue) {
							denomId = tempStrId;
							break;
						}
					}
				}

		} catch (Exception exp) {

			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"BNIInterface::BNIUtil.getDenominationId (String)");
		}

		return denomId;
	}

	/*public static String[] validateSubscriber(String phoneNumber) {

		String [] retVal = new String[2];
		try {*/
			/*retVal[0] = "SYSTEM_ERROR";
			retVal[1] = "";

			SubscriberSer subscriberSer = new SubscriberSer();
			subscriberSer.SUB_ID 		= phoneNumber;
			return getSubscriberObj().validateSubscriber(subscriberSer, "ATM");
			*/
		/*} catch (Exception exp) {
			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"BNIInterface::BNIUtil.validateSubscriber(String)");
		}

		return retVal;
	}*/

/*	public static Hashtable purchasePin (String subscriberId, String distributorId,
										 String denomValue, String atmTransId) {
		Hashtable retVal = null;
		String denomId	 = "";

		try {*//*
				denomId = getDenominationId(denomValue);
				retVal  = getExternalObj().setPaymentForPins (distributorId,
															 "1",	// paymentTypeId
															 "",	// paymentAccntId
															 "",	// paymentCardType
															 subscriberId,
															 "1",	//customerType
															 denomValue,
															 "",	//custAccntNumb
															 "",	//chequeNo
															 "",	//bankName
															 atmTransId,
															 denomId,
															 "1",	//topUpId
															 SystemVariables.REPLENISHMENT_ATM+"",
															 "",	//expiryDate
															 "",	//subGroupId
															 ""		//dbPin
															 );
			*/
		/*} catch (Exception exp) {

			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"ATMInterface::BNIUtil.purchasePin (String, String, String, String)");
		}

		return retVal;
	}*/ // END -- public static Hashtable purchasePin (String, String, String, String, String)

	/*public static String cancelInterfacePinTransaction (String transId, String finInstRefNbr, boolean prepaidCallFlag) {

		try {

				//return getExternalObj().cancelInterfacePinTransaction (transId, finInstRefNbr, SystemVariables.INTERFACE_ATM, prepaidCallFlag);

		} catch (Exception exp) {

			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"ATMInterface::BNIUtil.cancelInterfacePinTransaction (String, String, String, boolean)");
		}

		return "";
	}*/

	public static Hashtable getTransactionConnectionDetails(String companyDesc) {

		PreparedStatement	pStmt	= null;
		Connection		conn	= null;
		StringBuffer	sql		= null;
		ResultSet rs=null;
		Hashtable columnDet = new Hashtable();

		try {

			conn=DBConnection.getConnection();

			sql = new StringBuffer();
			sql.append(" SELECT c_company_desc,					");
			sql.append("		c_portnbr,						");
			sql.append("		c_misc							");
			sql.append(" FROM 	t_transport_conection_details 	");
			sql.append(" WHERE  C_CONNECTION_ID = ?	");

			pStmt	= conn.prepareStatement(sql.toString());
			pStmt.setString(1,companyDesc);
			rs	= pStmt.executeQuery();

			if (rs.next()) {

				columnDet.put("COMPANYDESC",Utilities.getValidData(rs.getString(1), "string"));
				columnDet.put("PORTNBR",Utilities.getValidData(rs.getString(2), "string"));
				columnDet.put("BANKID",Utilities.getValidData(rs.getString(3), "string"));

				//System.out.println("columnDet: " + columnDet);
			}

		} catch(Exception exp) {


			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"ATMInterface::BNIUtil.getTransactionConnectionDetails (String)");
		} finally {

			DBConnection.closeConnection(rs, pStmt, conn, "ATMInterface");
		}

		return 	 columnDet;

	}// END -- public static Hashtable getTransactionConnectionDetails(String)


	public static String getBoolean(boolean booleanValue) {

		//Bolean		'S' for true 'F' for false

		if(booleanValue)
			return "S";
		return "F";
	}

	public static String getISODate(String sourceDate, String sourceFormat) {

		//Date		Standard format ISO yy-mm-dd

		String date  = "";
		String year  = "";
		String month = "";
		String returnDate = "";

		try {

			sourceDate = sourceDate.trim().replace('-','/');
			sourceFormat = sourceFormat.trim().replace('-','/');

			Log.debug("sourceDate: " + sourceDate);
			Log.debug("sourceFormat: " + sourceFormat);

			if (sourceDate==null || sourceDate.trim().length()==0 || sourceDate.indexOf("/") == -1)
				return "00-00-00";

			StringTokenizer strTok = new StringTokenizer(sourceDate,"/");

			if (sourceFormat.equalsIgnoreCase("MM/DD/YYYY") ||
				sourceFormat.equalsIgnoreCase("MM/DD/YY")	) {

				if (strTok.hasMoreTokens()) {

					month = (String)strTok.nextToken();
					date  = (String)strTok.nextToken();
					year  = (String)strTok.nextToken();
				}

			} else if (sourceFormat.equalsIgnoreCase("DD/MM/YYYY") ||
					   sourceFormat.equalsIgnoreCase("DD/MM/YY")   ) {

				 if (strTok.hasMoreTokens()) {

					date  = (String)strTok.nextToken();
					month = (String)strTok.nextToken();
					year  = (String)strTok.nextToken();
				 }
			}



			year  = year.substring(2);
			returnDate =  year + "-" + month + "-" + date;
		} catch (Exception exp) {

			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"BNIInterface::BNIUtil.getISODate(String, String)");
			returnDate = "";
		}

		return returnDate;
	}// END -- public static String getISODate(String, String)

	public static String setResponseInMessageBag(String responseStream) {

		String response = "";
		try {
			while(true) {

				int len = Math.abs(Integer.parseInt(responseStream.substring(0,4)));
				if ((len+4) > responseStream.length()) break;
				response = responseStream.substring(4,len+4);
				responseStream  = responseStream.substring(len+4);

 				BNIMessage bniResponse = new BNIMessage(response, BNIConstants.RESPONSE);
				setMessage(bniResponse.getValue(BNIConstants.MSGNUM), bniResponse);
			}
		} catch (Exception exp) {

			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"BNIInterface::BNIUtil setResponseInMessageBag(.).");
			responseStream = "";
		}
		return responseStream;

	}// END -- public static String setResponseInMessageBag(String responseStream)

	public static void setMessage(String reqId, BNIMessage bniMessage)
	{
		try
		{
			msgHt.put(reqId, bniMessage);
			Log.debug(">>>>>>>>>> BNI block >> setMessage block in BNIAuthorizationInterface");
		}
		catch(Exception e)
		{
			Log.error(Utilities.getStackTraceAsString(e) + "\n" +
				"BNIInterface::BNIUtil setMessage(String reqId, BNIMessage bniMessage).");
		}
	}


	public static BNIMessage getMessage(String key) {

		BNIMessage bniMessage = null;
		try {

			int cnt = 60*100;
			for (int index=0; index<cnt; index++) {

				Thread.sleep(10);
				bniMessage = (BNIMessage)msgHt.get(key);
				if(bniMessage != null) break;
			}

			msgHt.remove(key);

		} catch (Exception exp) {
			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"BNIInterface::BNIAuthorizationInterface getMessage(String key).");
		}
		return bniMessage;
	}

	public static String validateDenomination(String denomValue) {

			String 		errorCode       = BNIReturnMessage.INCORRECT_VALUE;
			Hashtable	denomHt			= null;

			double denominationValue 	= 0.0;

		try {
				denomHt				= getDenominations();
				denominationValue	= Double.parseDouble(denomValue);

				if (denomHt == null ) {
				    System.out.println(">>>>>>>>>>hashtable is null vasu >>");
					//errorCode = BNIReturnMessage.GENERAL_ERROR_SYSTEM_NOT_AVAILABLE;
					errorCode = "GENERAL_ERROR_SYSTEM_NOT_AVAILABLE";

				} else if (denomHt.size()>0) {
				    System.out.println(">>>>>>>>>>i am in else if >>>>>"+denomHt.size());
					Enumeration enumb = denomHt.keys();

					while(enumb.hasMoreElements()) {

						String tempStrId = (String)enumb.nextElement();
						String tempStrValue = (String) denomHt.get(tempStrId);

						tempStrValue = (tempStrValue == null || tempStrValue.trim().length()==0)?
										"0.0":tempStrValue;

						double tempDblValue = Double.parseDouble(tempStrValue);

						if (denominationValue == tempDblValue) {
							errorCode = BNIReturnMessage.SUCCESS;
							break;
						}
					}
				} else {

					errorCode = BNIReturnMessage.INCORRECT_VALUE;
				}

		} catch (Exception exp) {

			//errorCode = BNIReturnMessage.GENERAL_ERROR_SYSTEM_NOT_AVAILABLE;
			errorCode = "GENERAL_ERROR_SYSTEM_NOT_AVAILABLE";

			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"ATMInterface::ATMUtil.validateDenomination(String)");

		}

		return errorCode;
	}// END -- private String validateDenomination(String denomValue)

	public static byte[] encipherTextAlpha(String plainStr)
	{
		byte[] encByteArray = null;
		try
		{
			encByteArray = MCipherPad.encipherTextAlpha(BNIConstants.encKey, plainStr);
		}
		catch (Exception exp)
		{
			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"in finally at BNIInterface::BNIUtil.encipherTextAlpha(String plainStr)");
		}

		return encByteArray;
	}// END -- public static byte[] encipherTextAlpha(String plainStr)

	public static String decipherTextAlpha(byte[] encStr)
	{
		String decStr = "";
		try
		{
			decStr = MCipherPad.decipherTextAlpha(BNIConstants.encKey, encStr);
		}
		catch (Exception exp)
		{
			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"in finally at BNIInterface::BNIUtil.decipherTextAlpha(byte[] encStr)");
		}

		return decStr;
	}// END -- public static String decipherTextAlpha(byte[] encStr)

	public static String encipherTextHexa(String plainStr)
	{
		String encStr = "";
		try
		{
			encStr = MCipherPad.encipherTextHexa(BNIConstants.encKey, plainStr);
			System.out.println("********************  encStr       "+encStr);
		}
		catch (Exception exp)
		{
			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"in finally at BNIInterface::BNIUtil.encipherTextHexa(String plainStr)");
		}

		return encStr;
	}// END -- public static String encipherTextHexa(String plainStr)

	public static String decipherTextHexa(String encStr)
	{
		String decStr = "";
		try
		{
			decStr = MCipherPad.decipherTextHexa(BNIConstants.encKey ,encStr);
		}
		catch (Exception exp)
		{
			Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
						"in finally at BNIInterface::BNIUtil.decipherTextHexa(String encStr)");
		}

		return decStr;
	}// END -- public static String decipherTextHexa(String encStr)

}// END -- public class BNIUtil
