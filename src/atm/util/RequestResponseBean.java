/*
 * Created on May 4, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package atm.util;

import java.io.Serializable;

/**
 * @author buchaiahk
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RequestResponseBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String request;
	private String response;
	private String errorCode;	

	/**
	 * @return Returns the errorCode.
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode The errorCode to set.
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return Returns the request.
	 */
	public String getRequest() {
		return request;
	}
	/**
	 * @param request The request to set.
	 */
	public void setRequest(String request) {
		this.request = request;
	}
	/**
	 * @return Returns the response.
	 */
	public String getResponse() {
		return response;
	}
	/**
	 * @param response The response to set.
	 */
	public void setResponse(String response) {
		this.response = response;
	}
}
