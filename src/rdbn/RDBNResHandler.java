package rdbn;


import java.net.ServerSocket;
import java.net.Socket;
import SeleniumTestAutomation.*;


public class RDBNResHandler extends Thread {

	//private static Logger Log = null;
	private int rdbn_gateway_port = 8000;
	private int thread_pool_size = 50;
	private Socket clientListeningSocket = null;
	private  ServerSocket serverSocket = null;
	public ThreadPool ivrPool = null;
	public static String testCaseName;

	public RDBNResHandler(String tcid) {
		testCaseName=tcid;

	}
	@Override
	public void run() {
		startServer();
	}

	private void startServer() {
		try {
			ivrPool = new ThreadPool(thread_pool_size);
			System.out.println("About to start server socket for receiving response from bankgw :"+rdbn_gateway_port);
			serverSocket = new ServerSocket(rdbn_gateway_port);
			while (true) {
				System.out.println("RDBNResHandler Class is ready to accept the Request********47");
				clientListeningSocket = serverSocket.accept();
				
				System.out.println("RDBNResHandler Class ready to accept the Request**********51"+ clientListeningSocket);
				RDBNHandler clientConn = new RDBNHandler(clientListeningSocket,ivrPool,testCaseName);
				
				System.out.println("RDBNResHandler Class is ready to accept the Request*****************54"+ clientListeningSocket);
				ivrPool.assign(clientConn);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Exception in start() of the RDBNResHandler class "+ e);
		}
	}
}
