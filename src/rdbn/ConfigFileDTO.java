package rdbn;
public class ConfigFileDTO {
	

	  private int serverPort = 0;
	  private int messageFormatID = 0;
	  private String bulkRequestNumStartRange = "1";
	  private String useSystemDate = "NO";
	  private String bulkrequest = "NO";
	  private String bulkRequestRepeatTimes = "1";
	  private String serverIP = "localhost";
	  private String requestDataFileName = "requestdata.csv";
	  private String outputFileName = "out.log";
	  private String messageHeader = "";

	  public void setPort(int port)
	  {
	    this.serverPort = port;
	  }

	  public int getPort() {
	    return this.serverPort;
	  }

	  public void setIPAddress(String ipaddress) {
	    this.serverIP = ipaddress;
	  }

	  public String getIPAddress() {
	    return this.serverIP;
	  }

	  public void setRequestDataFileName(String reqDataFileName) {
	    this.requestDataFileName = reqDataFileName;
	  }

	  public String getRequestDataFileName() {
	    return this.requestDataFileName;
	  }

	  public void setOutputFileName(String outFileName) {
	    this.outputFileName = outFileName;
	  }

	  public String getOutputFileName() {
	    return this.outputFileName;
	  }

	  public void setMessageHeader(String header) {
	    this.messageHeader = header;
	  }

	  public String getMessageHeader() {
	    return this.messageHeader;
	  }

	  public void setMessageFormatID(int id) {
	    this.messageFormatID = id;
	  }

	  public int getMessageFormatID() {
	    return this.messageFormatID;
	  }

	  public String getBulkrequest() {
	    return this.bulkrequest;
	  }

	  public void setBulkrequest(String bulkrequest) {
	    this.bulkrequest = bulkrequest;
	  }

	  public String getBulkRequestNumStartRange() {
	    return this.bulkRequestNumStartRange;
	  }

	  public void setBulkRequestNumStartRange(String bulkRequestNumStartRange) {
	    this.bulkRequestNumStartRange = bulkRequestNumStartRange;
	  }

	  public String getBulkRequestRepeatTimes()
	  {
	    return this.bulkRequestRepeatTimes;
	  }

	  public void setBulkRequestRepeatTimes(String bulkRequestRepeatTimes)
	  {
	    this.bulkRequestRepeatTimes = bulkRequestRepeatTimes;
	  }

	  public String getUseSystemDate()
	  {
	    return this.useSystemDate;
	  }

	  public void setUseSystemDate(String useSystemDate)
	  {
	    this.useSystemDate = useSystemDate;
	  }
	  //toString() to display the fields
	  public String toString()
	  {
	    return "port:"+getPort()+"\n ipaddress:"+getIPAddress()+"\n requested datafile name"+getRequestDataFileName()+"\n outputFileName:"+getOutputFileName()+"\n MsgHeader:"+getMessageHeader()+"\n Msg format ID:"+getMessageFormatID()+"\n bulk request:"+getBulkrequest()+"\n Bulk req Num Start range:"+getBulkRequestNumStartRange()+"\nBulk req repeat times"+getBulkRequestRepeatTimes()+"\n use system Date:"+getUseSystemDate();
	  }


}
