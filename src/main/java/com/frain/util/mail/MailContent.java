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
	/**
	 * 邮件主题
	 */
	private String subject;
	/**
	 * 邮件内容
	 */
	private String text;
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
}
