/**
 * 
 */
package com.frain.util.mail;

/**
 * @author kalven.meng
 *
 */
public class MailContent {
	
	public MailContent() {}
	
	public MailContent(String subject, String text) {
		this.subject = subject;
		this.text = text;
	}
	public MailContent(String subject, String text, String sendTo) {
		this.subject = subject;
		this.text = text;
		this.sendTo = sendTo;
	}
	/**
	 * 邮件主题
	 */
	private String subject;
	/**
	 * 邮件内容
	 */
	private String text;
	
	/**
	 * 收件人
	 */
	private String sendTo;
	
	
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the sendTo
	 */
	public String getSendTo() {
		return sendTo;
	}

	/**
	 * @param sendTo the sendTo to set
	 */
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("[sendTo:").append(sendTo).append(",content:").append(text).append("]");
		return sb.toString();
	}
	
}
