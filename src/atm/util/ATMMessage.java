package atm.util;

import java.util.Hashtable;
import java.util.StringTokenizer;

public class ATMMessage {
	private String 	  atmMessage 	= "";
	private Hashtable messageHt 	= null;
	private boolean isRequest		= false;
	private boolean isResponse		= false;
	
	public ATMMessage (String atmMessage, int messageType) {
		try {
			
			this.atmMessage = atmMessage;
			messageHt = new Hashtable();
			if(messageType == ATMConstants.REQUEST) {
				
				isRequest = true;
				
			} else if(messageType == ATMConstants.RESPONSE) {
				
				isResponse = true;
			}
			
			parseATMMessage ();
			
		} catch (Exception exp) {
			System.out.println("ATMInterface::ATMMessage-Ctor()");
		}
		
	}// END -- public ATMMessage (String message)
	
	public boolean isRequest () {
		
		return isRequest;
	}// END -- public boolean isRequest ()
	
	public boolean isResponse () {
		
		return isResponse;
	}// END -- public boolean isResponse ()
	
	public ATMMessage getATMMessage () {
		
		return this;
	}// END -- public ATMMessage getATMMessage ()
	
	public Hashtable getATMMessageHash () {
		
		return messageHt;
		
	}// END -- public Hashtable getATMMessageValues ()
	
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
			
			System.out.println("ATMInterface::ATMMessage.appendItem (String key, String value)");
			return false;
		}
	}
	
	public void setItem (String key, String value) {
		
		try {
			messageHt.put(key.trim(), value.trim());
			
		} catch (Exception exp) {
			
			System.out.println("ATMInterface::ATMMessage.setItem (String key, String value)");
		}
	}// END -- private void setItem (String, String)
	
	public String getMessage () {
		
		return atmMessage;
	}
	
	public String getMsgCode() {
		
		return (String)messageHt.get(ATMConstants.CODMSG);
	}
	
	private void parseATMMessage () {
		
		try {
			
			StringTokenizer strTok = new StringTokenizer(atmMessage, String.valueOf(ATMConstants.ITEM_SEPERATOR));
			
			String key	 = "";
			String value = "";
			String token = "";
			
			
			while(strTok.hasMoreTokens()) {
				
				token = strTok.nextToken();
				if (token.indexOf(ATMConstants.KV_SEPERATOR) != -1) {
					
					key   = token.substring(0, token.indexOf(ATMConstants.KV_SEPERATOR));
					value = token.substring(token.indexOf(ATMConstants.KV_SEPERATOR)+1, token.length());
					setItem(key, value);
					
				}
				
			}
			
			
		} catch (Exception exp) {
			
			System.out.println("ATMInterface::ATMMessage.parseATMMessage()");
		}
		
	}// END -- private void parseATMMessage ()
	
	private void append (String key, String value) {
		
		try {
			
			if(atmMessage != null && atmMessage.trim().length()>0) atmMessage += ATMConstants.ITEM_SEPERATOR;
			atmMessage = atmMessage + key + ATMConstants.KV_SEPERATOR + value;
			
		} catch (Exception exp) {
			
			System.out.println("ATMInterface::ATMMessage.append (String key, String value)");
		}
		
	}// END -- private void append ()
	
	public void addCloseTag() {
		
		atmMessage += ATMConstants.CLOSETAG;
	}
	
}// END -- public class ATMMessage
