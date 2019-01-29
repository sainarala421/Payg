package rdbn;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import org.apache.log4j.Logger;
import SeleniumTestAutomation.*;


public class RDBNReqHandler  implements Runnable{

	private static Logger Log	= Logger.getLogger(RDBNReqHandler.class);

	private BufferedReader	inputbufferR	    = null;
	private Socket 			clientSocket		= null;
	ThreadPool ivrPool                          = null;

	public RDBNReqHandler(Socket clientSocket,ThreadPool ivrPool) {
		try{
			this.clientSocket = clientSocket;
			this.ivrPool = ivrPool;		
		} catch (Exception exp) {
			Log.error("Exception in RDBNReqHandler() in of RDBNReqHandler  class."+exp);
		}
	}
	public void run()
	{
		try
		{
			readIVRClientData();
		}
		catch (Exception exp) {

			Log.error("Exception in run() in of RDBNReqHandler  class."+exp);
		}
	}
	public void readIVRClientData(){

		String reqMessage = "";
		char[] chrStr = null;
		int msgExists=-1;
		chrStr= new char[5000];
		try
		{
			StringBuffer strBuffer = new StringBuffer();
			inputbufferR = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			while(true)
			{
				chrStr= new char[5000];
				msgExists = inputbufferR.read(chrStr,0,4999);
				Log.info("read...count..."+msgExists);
				if(msgExists!=-1)
				{
					reqMessage="";
					reqMessage = new String(chrStr);
					Log.info(" Part Request from RDBN GW == RDBNReqHandler==> "+reqMessage.trim());
					strBuffer.append(reqMessage.trim());
					Log.info("From client:  in RDBNReqHandler "+strBuffer.toString());
				}
				else
				{  
					Log.info(" exiting from the listening....");
					break;
				}
			}
		}
		catch(Exception exp)
		{
			Log.error(" Exception in readIVRClientData() of the RDBNReqHandler "+exp);
			exp.printStackTrace();
		}
		finally
		{
			try
			{
				if(inputbufferR != null)
				{
					inputbufferR.close();
					inputbufferR = null;
				}	
			}
			catch(Exception ex)
			{
				Log.equals(" Exception in finally block of the RDBNReqHandler"+ex);

			}
		}
	}
}

