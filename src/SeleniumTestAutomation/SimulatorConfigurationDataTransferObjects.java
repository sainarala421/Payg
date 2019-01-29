package SeleniumTestAutomation;

public class SimulatorConfigurationDataTransferObjects
{
  private int serverPort = 0;
  private int messageFormatID = 0;
  private String serverIP = null;
  private String requestDataFileName;
  private String messageHeader = null;

  public int getSVBN_MDNEndPos() {
	return SVBN_MDNEndPos;
}

public void setSVBN_MDNEndPos(int sVBN_MDNEndPos) {
	SVBN_MDNEndPos = sVBN_MDNEndPos;
}
private int SVBN_MDNEndPos=0;
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

}