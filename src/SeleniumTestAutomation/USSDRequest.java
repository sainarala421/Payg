package SeleniumTestAutomation;

public class USSDRequest {
	private String msisdn;
	private String voucherPin;
	private String dialogueID;
	private String providerID;
	private String serviceCode;
	private String response;

	public String getDialogueID() {
		return dialogueID;
	}

	public void setDialogueID(String dialogueID) {
		this.dialogueID = dialogueID;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getProviderID() {
		return providerID;
	}

	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getVoucherPin() {
		return voucherPin;
	}
	public void setVoucherPin(String voucherPin) {
		this.voucherPin = voucherPin;
	}
}
