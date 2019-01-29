package rdbn;


import java.util.Properties;
import SeleniumTestAutomation.*;

/**
 * @author ayub.mohammed
 *
 */
public class RDBNServerConstants {
	
	public final static String CURRENT_PATH = System.getProperty("user.dir");
	public final static String FILE_SEPARATOR = System.getProperty("file.separator");
	public final static String CONFIG_FILE_FOLDER_NAME = "config";
	public final static String LOG_FILE_NAME = "Log4j.properties";
	public final static String CONFIG_FILE_NAME = "rdbnservercfg.properties";
	public final static String LOG_FILE_FOLDER_NAME = "log";
	public final static String DATA_FILE_NAME = "rdbnreqmsg.txt";
	
	public final static String CONFIG_FILE_FOLDER_PATH = CURRENT_PATH +
														 FILE_SEPARATOR +
														 CONFIG_FILE_FOLDER_NAME+
														FILE_SEPARATOR;
	public final static String LOG_FILE_FOLDER_PATH = CURRENT_PATH +
													  FILE_SEPARATOR +
													  LOG_FILE_FOLDER_NAME+
													  FILE_SEPARATOR;
	public final static String DATA_FILE_FOLDER_PATH = CURRENT_PATH +
	 FILE_SEPARATOR +
	 CONFIG_FILE_FOLDER_NAME+
	FILE_SEPARATOR;
	
	public final static String RDBN_GW_IPADDRESS = "RDBN_GW_IPADDRESS";
	public final static String RDBN_GW_PORT_NUMBER = "RDBN_GW_PORT_NUMBER";
	public final static String SERVER_PORT = "SERVER_PORT";
	
	public final static String THREAD_POOL_SIZE = "THREAD_POOL_SIZE";
	public final static String GCFREQENCY = "GCFREQENCY";
	public static int ivrPoolSize = 0;
	public static Properties properties =  new Properties();

}
