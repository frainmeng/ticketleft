/**
 * 
 */
package com.frain.service.mail;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frain.cache.Cache;
import com.frain.util.mail.MailContent;
import com.frain.util.mail.MailSenderUtils;

/**
 * @author kalven.meng
 *
 */
public class MailService {
	private static Logger log = LoggerFactory.getLogger(MailService.class);
	private static final long CACHE_TIMEOUT = 5L;
	private static final TimeUnit TIME_UNIT = TimeUnit.MINUTES;
	private static final String CACHE_VALUE = null;
	private MailSenderUtils mailSenderUtil = MailSenderUtils.getInstance();
	private Cache<String, String> cache = new Cache<String,String>();
	/**
	 * 发送邮件
	 * @param subject 主题
	 * @param text 内容
	 */
	public void sendMail (String sendTo,String subject, String text) {
		MailContent mailContent = new MailContent(subject, text, sendTo);
		sendMail(mailContent);
	}
	
	public void sendMail (MailContent mailContent) {
		if (!isNeedSend(mailContent.toString())) {
			log.info("MailContext has been send ever:" + mailContent.toString());
			log.info("This E-mail will not be send");
			return ;
		}
		try {
			if (mailSenderUtil.sendMail(mailContent)) {
				cache.put(mailContent.toString(), CACHE_VALUE, CACHE_TIMEOUT, TIME_UNIT);
				log.info("Mail send success: " + mailContent.toString());
			} else {
				log.info("Mail send faild: " + mailContent.toString());
			}
		} catch (Exception e) {
			log.error("Send Mail Exception:", e);
		}
	}
	
	private boolean isNeedSend(String key) {
		return !cache.containKey(key);
	}
	
	
}
