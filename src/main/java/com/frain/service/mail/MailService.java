/**
 * 
 */
package com.frain.service.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frain.util.mail.MailContent;
import com.frain.util.mail.MailSenderUtils;

/**
 * @author kalven.meng
 *
 */
public class MailService {
	private static Logger log = LoggerFactory.getLogger(MailService.class);
	private MailSenderUtils mailSenderUtil = MailSenderUtils.getInstance();
	
	private String preMailText = "";
	/**
	 * 发送邮件
	 * @param subject 主题
	 * @param text 内容
	 */
	public void sendMail (String subject, String text) {
		if (text.equals(preMailText)) {
			log.info("MailContext has been send ever");
			log.info("This E-mail will not be send");
			return ;
		}
		MailContent mailContent = new MailContent(subject, text);
		if (mailSenderUtil.sendMail(mailContent)) {
			preMailText = text;
			log.info("邮件发送成功");
		}
	}
	
}
