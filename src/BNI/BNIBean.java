package BNI;

import java.io.Serializable;

public class BNIBean implements Serializable {
	
	public String port;
	public String NetworkId;
	public String messageNumber; //messageNumber 002(Recharge) 004(Reversal)
	public String bankTransId;
	public String RechargeAmount;
	public String mobileNumber;
	public String reversaCarrrNum;
	public String OperatorIdentity;//OPERATORIDENTITY 00(Movistar) 01(Banana)
	
	/*ipAddress = (String)msglist.get(0);//ipAddress
	port = Integer.parseInt((String)msglist.get(1));//port
	
	netId = (String)msglist.get(2);//NetworkId
	messageNbr = (String)msglist.get(3);//messageNumber 002(Recharge) 004(Reversal)
	bankTransId = (String)msglist.get(4);//bankTransId(A Randam Number)
	

	value=(String)msglist.get(6);//amount
	subPrefix = (String)msglist.get(7);//mobileNumber
	carrrnum = (String)msglist.get(8);//reversa carrrnum
	operatorIdentity=(String)msglist.get(9);//OPERATORIDENTITY 00(Movistar) 01(Banana)
*/	
	public String ipAddress;
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getNetworkId() {
		return NetworkId;
	}
	public void setNetworkId(String networkId) {
		NetworkId = networkId;
	}
	public String getMessageNumber() {
		return messageNumber;
	}
	public void setMessageNumber(String messageNumber) {
		this.messageNumber = messageNumber;
	}
	public String getBankTransId() {
		return bankTransId;
	}
	public void setBankTransId(String bankTransId) {
		this.bankTransId = bankTransId;
	}
	public String getRechargeAmount() {
		return RechargeAmount;
	}
	public void setRechargeAmount(String rechargeAmount) {
		RechargeAmount = rechargeAmount;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getReversaCarrrNum() {
		return reversaCarrrNum;
	}
	public void setReversaCarrrNum(String reversaCarrrNum) {
		this.reversaCarrrNum = reversaCarrrNum;
	}
	public String getOperatorIdentity() {
		return OperatorIdentity;
	}
	public void setOperatorIdentity(String operatorIdentity) {
		OperatorIdentity = operatorIdentity;
	}
	
	
	

}
