/**
 * 
 */
package com.frain.model;

import java.util.List;

/**
 * @author kalven.meng
 *
 */
public class TicketLeftQueryDTO {
	private String validateMessagesShowId;
	private boolean status;
	private int httpstatus;
	private List<TicketDataInfo> data;
	private String[] messages;
	/**
	 * @return the validateMessagesShowId
	 */
	public String getValidateMessagesShowId() {
		return validateMessagesShowId;
	}
	/**
	 * @param validateMessagesShowId the validateMessagesShowId to set
	 */
	public void setValidateMessagesShowId(String validateMessagesShowId) {
		this.validateMessagesShowId = validateMessagesShowId;
	}
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return the httpstatus
	 */
	public int getHttpstatus() {
		return httpstatus;
	}
	/**
	 * @param httpstatus the httpstatus to set
	 */
	public void setHttpstatus(int httpstatus) {
		this.httpstatus = httpstatus;
	}
	/**
	 * @return the data
	 */
	public List<TicketDataInfo> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<TicketDataInfo> data) {
		this.data = data;
	}
	/**
	 * @return the messages
	 */
	public String[] getMessages() {
		return messages;
	}
	/**
	 * @param messages the messages to set
	 */
	public void setMessages(String[] messages) {
		this.messages = messages;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("httpstatus:").append(httpstatus);
		sb.append(",status:").append(status);
		sb.append(",messages:[");
		StringBuilder messageStr = new StringBuilder();
		for(String message : messages){
			messageStr.append(message).append(",");
		}
		
		sb.append(messageStr.length()<1? "" :  messageStr.toString().substring(0, messageStr.length()-1));
		sb.append("]}");
		return sb.toString();
	}
	
}
