package SeleniumTestAutomation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
class DB //DB class
{       
    public static String errMsg;   
	public Connection createConnection(String dbName) throws InterruptedException
        {
        	String dbServer,dbSid,dbUsername,dbPassword;
			System.out.println ("inside DBConnect");
			Properties properties = new Properties();
			try 
			{
				properties.load(new FileInputStream(Configure.configFilePath));
				dbServer = properties.getProperty(dbName+"_"+"server");
				dbSid = properties.getProperty(dbName+"_"+"sid");
				dbUsername = properties.getProperty(dbName+"_"+"username");
				dbPassword = properties.getProperty(dbName+"_"+"password");
				System.out.println ("PATH OF DATABASE --->"+"jdbc:oracle:thin:@"+dbServer+"/"+dbSid+"");
				DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
				//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@"+dbServer+"/XIUSRAC",dbUsername ,dbPassword);
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@"+dbServer+"/"+dbSid,dbUsername ,dbPassword);
				Thread.sleep(2000);
				System.out.println("connected");
				conn.setAutoCommit(true);
				return conn;         
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				errMsg= e.getMessage();
			}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errMsg= e.getMessage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errMsg= e.getMessage();
			}
			return null;
        }
};