/**
 * 
 */
package com.frain.model;

import java.util.Date;
import java.util.Set;

/**
 * @author kalven.meng
 *
 */
public class QueryTask {
	private String taskName;
	private String reqUri;
	private String mailSendTo;
	private boolean status = false;
	private Set<String> trainCodeSet;
	private String message;
	private String createDate;
	private String updateDate;
	
	/**
	 * @return the reqUri
	 */
	public String getReqUri() {
		return reqUri;
	}
	/**
	 * @param reqUri the reqUri to set
	 */
	public void setReqUri(String reqUri) {
		this.reqUri = reqUri;
	}
	/**
	 * @return the mailSendTo
	 */
	public String getMailSendTo() {
		return mailSendTo;
	}
	/**
	 * @param mailSendTo the mailSendTo to set
	 */
	public void setMailSendTo(String mailSendTo) {
		this.mailSendTo = mailSendTo;
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
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}
	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the trainCodeSet
	 */
	public Set<String> getTrainCodeSet() {
		return trainCodeSet;
	}
	/**
	 * @param trainCodeSet the trainCodeSet to set
	 */
	public void setTrainCodeSet(Set<String> trainCodeSet) {
		this.trainCodeSet = trainCodeSet;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
