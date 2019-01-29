package atm;
/*
 * Created on Jul 10, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author buchaiahk
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.io.Serializable;

public class ATMBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String ipAddress;
	public String portNo;
	public String msgNum;
	public String subid;
	
	public String amount;
	public String revno;
	
	public String carrierId;


	public String getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}
	/**
	 * @return Returns the amount.
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount The amount to set.
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return Returns the ipAddress.
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
	 * @param ipAddress The ipAddress to set.
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * @return Returns the msgNum.
	 */
	public String getMsgNum() {
		return msgNum;
	}
	/**
	 * @param msgNum The msgNum to set.
	 */
	public void setMsgNum(String msgNum) {
		this.msgNum = msgNum;
	}
	/**
	 * @return Returns the portNo.
	 */
	public String getPortNo() {
		return portNo;
	}
	/**
	 * @param portNo The portNo to set.
	 */
	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}
	/**
	 * @return Returns the revno.
	 */
	public String getRevno() {
		return revno;
	}
	/**
	 * @param revno The revno to set.
	 */
	public void setRevno(String revno) {
		this.revno = revno;
	}
	/**
	 * @return Returns the subid.
	 */
	public String getSubid() {
		return subid;
	}
	/**
	 * @param subid The subid to set.
	 */
	public void setSubid(String subid) {
		this.subid = subid;
	}
	}