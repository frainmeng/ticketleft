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
	 * �����ʼ�
	 * @param subject ����
	 * @param text ����
	 */
	public void sendMail (String subject, String text) {
		MailContent mailContent = new MailContent(subject, text);
		if (mailSenderUtil.sendMail(mailContent)) {
			System.out.println("�ʼ����ͳɹ�");
		}
	}
	
}
