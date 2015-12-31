/**
 * 
 */
package com.frain.service.mail;

import com.frain.util.mail.MailContent;
import com.frain.util.mail.MailSenderUtils;

/**
 * @author kalven.meng
 *
 */
public class MailService {
	
	private MailSenderUtils mailSenderUtil = MailSenderUtils.getInstance();
	
	/**
	 * 发送邮件
	 * @param subject 主题
	 * @param text 内容
	 */
	public void sendMail (String subject, String text) {
		MailContent mailContent = new MailContent(subject, text);
		if (mailSenderUtil.sendMail(mailContent)) {
			System.out.println("邮件发送成功");
		}
	}
	
}
