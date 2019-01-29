package SeleniumTestAutomation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class IVRSimulator {
	static String simulatorIP;
	static int simulatorPort;
	static Socket 	soc	= null;
	static PrintWriter		output	= null;
	static BufferedReader	input	= null;
	public static FileOutputStream fos = null;
	static String response=null;
	static String outLogFile;
	public static void connectIVR(String CsvRequest, String packageName,String transactionName,String TCID) throws IOException 
	{
		System.out.println("CsvRequest request:" +CsvRequest);
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(Configure.configFilePath));
			simulatorIP=properties.getProperty(packageName+"_simulatorIP");
			simulatorPort=Integer.parseInt(properties.getProperty(packageName+"_simulatorPort"));
			System.out.println("IP for the Simulator ---> " + simulatorIP);
			System.out.println("Port for the Simulator ---> " + simulatorPort);
			outLogFile=Configure.outputFilePath+"\\"+TCID+".log";
			System.out.println("outLogfile--->:" +outLogFile);
			soc = new Socket(simulatorIP, simulatorPort);
			soc.setSoTimeout(30000);
			input	= new BufferedReader(new InputStreamReader(soc.getInputStream()));
			output	= new PrintWriter(soc.getOutputStream(),true);
			
			if (transactionName.equalsIgnoreCase("BankReplenishmentRequestEncryptedKey")||transactionName.equalsIgnoreCase("LastRechargeTransactionInquiryRequest")||transactionName.equalsIgnoreCase("TransferAccountRequest")||transactionName.equalsIgnoreCase("BankBalanceRequest")||transactionName.equalsIgnoreCase("ReplenishmentRequest")||transactionName.equalsIgnoreCase("BadmarkandReplaceCouponRequest")||transactionName.equalsIgnoreCase("CouponSerialNumberEnquiryRequest")||transactionName.equalsIgnoreCase("BrandCouponRedemptionRequest")||transactionName.equalsIgnoreCase("RechargewithCouponRequest")||transactionName.equalsIgnoreCase("GetAccountBalanceRequest"))
			{
				
				System.out.println("request in if condition: "+CsvRequest);
				response=sendReqToServer(CsvRequest);
				System.out.println("responce" +response);
				writeToSuccessLog(outLogFile,response);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			soc.close();
			
		}
	}
	public static String sendReqToServer(String CsvRequest) {
		System.out.println("Sending Request to Server ===> " + CsvRequest);
		output.println(CsvRequest);
		String res=readMessage();
		//return readMessage();
		System.out.println("\n\n------------------res->>>"+res);
		return res;
	}

	public static  String readMessage() {
		String message = "";
		char [] chrStr = null;
		try {
			while(true) {
				chrStr= new char[5000];
				int msgExists = input.read(chrStr,0,4999);

				if(msgExists!=-1)
				{
					message = "";
					message = new String(chrStr).trim();
					break;
				}
			}// END -- Inner while(true)
		} catch (IOException exp) {
			System.out.println("EXP: at Server in receiveBCGData(): " + exp);
		}
		return message;
	}
	public static void writeToSuccessLog(String outLogFile,String response)
	{
		PrintStream outputFileStream=null;
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//String outputFileName=sdf.format(new java.util.Date())+remoteClient+".log";
		//String filePath=logPath+SimulatorConst.FILE_SEPARATOR+outputFileName;
		//System.out.println("logPath: "+logPath);
		//System.out.println("filePath: "+filePath);
		try {
			//System.out.println("FilePath: "+filePath);
			outputFileStream = new PrintStream(new FileOutputStream(outLogFile,true));
			outputFileStream.println("-------------------"+new java.util.Date()+"----------------------");
			outputFileStream.println();
			outputFileStream.println("RESPONSE : "+response);
			//System.out.println("RESPONSE :  "+message.getResponse());
		} catch (FileNotFoundException e) {
			System.out.println("The FileNotFoundException is in writeToLog() :  "+e);
		}
   }
	public static String EncriptData(String eccData) throws Exception{
	             String encryptedString = "",decryptedString="";
	             //byte[] key1=aeskeyGenerator();
	             //String key1="balajibodduluri1";
	             String key1=Configure.IVRAESKeyValue;
	             key1=key1.trim();
	             System.out.println("IvrAeKeyValue--->"+key1);
	             byte[] s;
	     		s=key1.getBytes();
	             encryptedString = aesEncryptFromPlainText(eccData, s);
	                  System.out.println("EncCryPteddata----->"+encryptedString);
	                  decryptedString = aesDecryptToplainText(encryptedString, s);
	                  System.out.println("decryptedData----->"+decryptedString);
	                  
	return encryptedString;
	}
	private static String aesEncryptFromPlainText(String eccData, byte[] key)throws Exception{
		

		byte[] raw = null;
		String encryptedString = "";
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(eccData.getBytes());
			encryptedString = byteToHex(encrypted);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (BadPaddingException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return encryptedString;

	
	}
	private static String byteToHex(byte[] base) {
		// TODO Auto-generated method stub

		int low = 15;
		int high = low << 4;

		StringBuffer output = new StringBuffer();
		for (int i = 0; i < base.length; i++) {
			String s1 = Integer.toHexString((base[i] & high) >> 4);
			String s2 = Integer.toHexString(base[i] & low);

			output.append(s1);
			output.append(s2);
		}
		return output.toString();
	
	}
	static private byte[] aeskeyGenerator() {
		// TODO Auto-generated method stub
		SecretKey key = null;
		String m_key = "";
		byte[] bytes = new byte[16];

		try {
			// Generates a KeyGenerator object for the AES algorithm
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128); // 128 bits key size
			key = kgen.generateKey();
			bytes = key.getEncoded();
			//m_key =byteToHex(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	public static String aesDecryptToplainText(String encryptedString, byte[] key) throws Exception {
		/*System.out.println("KEY = " + key);
		System.out.println("encryptedString  = " + encryptedString);*/

		
	/*	byte[] iv = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,	0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		IvParameterSpec ips = new IvParameterSpec(iv);
*/
		String originalString = "";
		try {
			byte[] encodedKey = null;

			//encodedKey = hexToByte(key);
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

			byte[] deciphertext = null;

			// converts from encrypt text to plain text

		/*	Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			decryptCipher.init(Cipher.DECRYPT_MODE, skeySpec, ips);*/
			
			Cipher decryptCipher = Cipher.getInstance("AES");
			decryptCipher.init(Cipher.DECRYPT_MODE, skeySpec);
			
			deciphertext = decryptCipher.doFinal(hexToByte(encryptedString));
			originalString = new String(deciphertext);

			//System.out.println("originalString text = " + originalString);

		} catch (java.security.InvalidKeyException e) {
			System.out.println("\nWrong Key entered ... \n");
			e.printStackTrace();
		}  catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			throw new Exception(e);
		}  catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (BadPaddingException e) {
			e.printStackTrace();
			throw new Exception(e);
		}catch (Exception e) {			
			e.printStackTrace();
		}
		return originalString;

	}
	
	
	public static byte[] hexToByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] keyBytes = new byte[len];

		for (int i = 0, j = 0; j < len; i += 2, j++) {
			int tmpByte = 0;
			int hVal = Byte.parseByte(hexString.substring(i, i + 1), 16);
			int lVal = Byte.parseByte(hexString.substring(i + 1, i + 2), 16);
			tmpByte = hVal & 0xFF;
			tmpByte = tmpByte << 4;
			tmpByte = (tmpByte | (lVal & 0xFF));

			keyBytes[j] = (byte) tmpByte;
		}
		return keyBytes;
	}// end of hexToByte()
}//End Class
