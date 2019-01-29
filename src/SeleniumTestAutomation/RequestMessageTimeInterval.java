package SeleniumTestAutomation;

import org.jpos.iso.ISOMsg;

public class RequestMessageTimeInterval
{
  private int timeinterval = 0;
  private ISOMsg reqIsoMsg = null;
  public RequestMessageTimeInterval() 
  {
  }
  public RequestMessageTimeInterval(int timeInter, ISOMsg isomsg) 
  {
    this.timeinterval = timeInter;
    this.reqIsoMsg = isomsg;
  }
  public void setTimeInterval(int time) 
  {
    this.timeinterval = time;
  }
  public void setISOMsg(ISOMsg msg) 
  {
    this.reqIsoMsg = msg;
  }
  public int getTimeInterval() 
  {
    return this.timeinterval;
  }
  public ISOMsg getISOMsg() 
  {
    return this.reqIsoMsg;
  }
}