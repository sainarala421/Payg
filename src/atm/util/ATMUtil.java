package atm.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;

import com.aircls.common.MCipherPad;

public class ATMUtil {
	
	private static Random		_random			= null;
	private static int 			n 				= 2000000000;
	
	
	public static String getURLEncodedString(String message) {
		return URLEncoder.encode(message);
	}
	public static String getURLDecodedString(String message) {
		return URLDecoder.decode(message);
	}
	
	public static String decrypt(byte[] message) {
		String decryptString = "";
		decryptString = MCipherPad.decipherText("42386C3173303874",message);
		return decryptString;
	}
	
	public static byte[] encrypt(String message) {
		byte[]	encString = null;
		try
		{
			encString = MCipherPad.encipherText("42386C3173303874",message.trim());
		}
		catch(Exception exp){
			System.out.println("Exception while encryption :" + exp.getMessage());
		}
		return encString;
	}
	
	public static String genTransNbr() {
		String str = "";
		
		try {
			
			if(_random == null)	_random = new Random();
			str = String.valueOf(new Date().getTime() + _random.nextInt(n));
			
		} catch (Exception exp) {
			
			System.out.println("ATMInterface::ATMUtil.genTransNbr()");
		}
		return str;
	}
}
