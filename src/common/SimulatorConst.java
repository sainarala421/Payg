package common;

public class SimulatorConst {

	//Current Directory
	public final static String CURRENT_PATH = System.getProperty("user.dir");

	public final static String FILE_SEPARATOR = System.getProperty("file.separator");

	public final static String IN_FILE_FOLDER = "infile";

	public final static String OUT_FILE_FOLDER = "outfile";
	
	public final static String CONFIG_FILE_FOLDER = "config";
	
	public final static String WEBINF_FILE_FOLDER = "WEB-INF";
	
	public final static String OUT_FILE_FOR_SUCCESS = "success";
	
	public final static String OUT_FILE_FOR_FAILURE = "failure";
	
	public final static String OUT_FILE_DIR_FOR_SERVIPAGOS = "servioutput";
	
	public final static String OUTPUT_LOG_DIR_PATH_BNI = "/var/log/tomcatlogs/logs/bnilogs/";
	
	
	public final static String OUTPUT_LOG_DIR_PATH_SERVIPAGOS = "/var/log/tomcatlogs/logs/servilogs/";
	
	
	public final static String IN_FILE_DIR_PATH = WEBINF_FILE_FOLDER +
				                                   FILE_SEPARATOR +
				                                   IN_FILE_FOLDER+
												   FILE_SEPARATOR;

	public final static String OUT_FILE_DIR_PATH_SUCCESS = WEBINF_FILE_FOLDER +
												   		   FILE_SEPARATOR +
														   OUT_FILE_FOLDER+
														   FILE_SEPARATOR+
														   OUT_FILE_FOR_SUCCESS+
														   FILE_SEPARATOR;
	
	public final static String SERVI_OUTFILE_DIR_PATH = WEBINF_FILE_FOLDER +
														FILE_SEPARATOR +
														OUT_FILE_FOLDER+
														FILE_SEPARATOR+
														OUT_FILE_DIR_FOR_SERVIPAGOS+
														FILE_SEPARATOR;
  
	public final static String OUT_FILE_DIR_PATH_FAILURE = WEBINF_FILE_FOLDER +
														   FILE_SEPARATOR +
														   OUT_FILE_FOLDER+
														   FILE_SEPARATOR+
														   OUT_FILE_FOR_FAILURE+
														   FILE_SEPARATOR;
	
	public final static String CONFIG_FILE_DIR_PATH = WEBINF_FILE_FOLDER+
													  FILE_SEPARATOR+
													  CONFIG_FILE_FOLDER+
													  FILE_SEPARATOR;
	
	//Config File Constants
		
	public final static int TIME_INTERVAL = 1000;
	
	
	}
