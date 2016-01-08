/**
 * 
 */
package com.frain.model;

/**
 * @author kalven.meng
 *
 */
public class RespModel {
	public static final String SUCCESS = "success";
	public static final String ERROR = "error"; 
	
	private String respStatus;
	private Object data;
	
	
	/**
	 * @return the respStatus
	 */
	public String getRespStatus() {
		return respStatus;
	}
	/**
	 * @param respStatus the respStatus to set
	 */
	public void setRespStatus(String respStatus) {
		this.respStatus = respStatus;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
}
