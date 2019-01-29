// Package Name
package BNI;

// Java SDK 1.3.1 Imports
import java.util.Hashtable;
import java.util.StringTokenizer;

public class BNIMessage {

	//static Logger Log	= Logger.getLogger(BNIMessage.class);
	private String 	  bniMessage 	= "";
	private Hashtable messageHt 	= null;

	private boolean isRequest		= false;
	private boolean isResponse		= false;
	private boolean hasLengthSet	= false;

	
	public BNIMessage (String bniMessage, int messageType) {

		try {

			this.bniMessage = bniMessage;
			//System.out.println(this.getClass().getName() + " bniMessage  : " + bniMessage);
			messageHt = new Hashtable();
			if(messageType == BNIConstants.REQUEST) {

				isRequest = true;

			} else if(messageType == BNIConstants.RESPONSE) {

				isResponse = true;
			}

			parseBNIMessage ();

		} catch (Exception exp) {
			//Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
				//		"ATMInterface::BNIMessage-Ctor()");
		}

	}// END -- public BNIMessage (String message)

	public boolean isRequest () {

		return isRequest;
	}// END -- public boolean isRequest ()

	public boolean isResponse () {

		return isResponse;
	}// END -- public boolean isResponse ()

	public BNIMessage getBNIMessage () {

		return this;
	}// END -- public BNIMessage getBNIMessage ()

	public Hashtable getBNIMessageHash () {

		return messageHt;

	}// END -- public Hashtable getBNIMessageValues ()

	public String getValue(String paramName) {
		String paramValue = "NONE";

			if(messageHt.get(paramName)!=null)
			 paramValue = (String)messageHt.get(paramName);
		return paramValue;

	}

	public boolean appendItem (String key, String value) {

		try {
				setItem (key, value);
				append (key, value);
				return true;

		} catch (Exception exp ) {

			//Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
				//		"BNIInterface::BNIMessage.appendItem (String key, String value)");
			return false;
		}
	}

	public void setItem (String key, String value) {

		try {
				messageHt.put(key.trim(), value.trim());

		} catch (Exception exp) {

			//Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
				//		"BNIInterface::BNIMessage.setItem (String key, String value)");
		}
	}// END -- private void setItem (String, String)

	public String getMessage () {

		if (!hasLengthSet)
			setLength();

		return bniMessage;
	}

	public String getMsgCode() {

		return (String)messageHt.get(BNIConstants.MSGNUM);
	}

	private void parseBNIMessage () {

		try {
		    	//System.out.println(this.getClass().getName() + "bniMessage" + bniMessage);
				StringTokenizer strTok = new StringTokenizer(bniMessage, String.valueOf(BNIConstants.ITEM_SEPERATOR));

				String key	 = "";
				String value = "";
				String token = "";


				while(strTok.hasMoreTokens()) {

					token = strTok.nextToken();
					if (token.indexOf(BNIConstants.KV_SEPERATOR) != -1) {

						key   = token.substring(0, token.indexOf(BNIConstants.KV_SEPERATOR));
						value = token.substring(token.indexOf(BNIConstants.KV_SEPERATOR)+1, token.length());
						setItem(key, value);
						//System.out.println(this.getClass().getName() + "key : " + key);
						//System.out.println(this.getClass().getName() + "value : " + value);

					}

				}


		} catch (Exception exp) {

			//Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
				//		"BNIInterface::BNIMessage.parseBNIMessage()");
		}

	}// END -- private void parseBNIMessage ()

	private void append (String key, String value) {

		try {

			if(bniMessage != null && bniMessage.trim().length()>0)
				bniMessage += BNIConstants.ITEM_SEPERATOR;
			bniMessage = bniMessage + key + BNIConstants.KV_SEPERATOR + value;

		} catch (Exception exp) {

			//Log.error(Utilities.getStackTraceAsString(exp) + "\n" +
				//		"BNIInterface::BNIMessage.append (String key, String value)");
		}

	}// END -- private void append ()

	private void setLength()
	{
	   String length = "0000" + bniMessage.length();
	   length = length.substring(length.length()-4);

	   bniMessage = BNIConstants.LENMSG + BNIConstants.KV_SEPERATOR
	   				+ length + BNIConstants.ITEM_SEPERATOR + bniMessage;


	   hasLengthSet = true;
	}

	public void addCloseTag() {

		bniMessage += BNIConstants.CLOSETAG;
	}

	public static String returnEmptyString(){
		 return "";
	}

	public String toString() {

		return getClass().getName() + " [ " + bniMessage +  " ] ";
	}

	//Newly added methods
/*
	public void setEncFlag(String enc_flag){

		this.enc_flag = enc_flag;


	}

	public String getEncFlag(){

		return enc_flag;

	}


	public void setEncType(String enc_type){

		this.enc_type = enc_type;

	}

	public String getEncType(){

		return enc_type;

	}


	public void setEncFormat(String enc_format){

		this.enc_format = enc_format;

	}

	public String getEncFormat(){

		return enc_format;

	}

*/

}// END -- public class BNIMessage
