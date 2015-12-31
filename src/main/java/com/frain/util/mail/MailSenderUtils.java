/**
 * 
 */
package com.frain.util.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.frain.util.Constants;
import com.frain.util.PropertyUtils;

/**
 * @author kalven.meng
 *
 */
public class MailSenderUtils {
	private Log log = LogFactory.getLog(MailSenderUtils.class);
	private static final String MAIL_USER_NAME = "mail.user.name";
	private static final String MAIL_USER_PASSWORD = "mail.user.password";
	private static final String MAIL_SEND_TO = "mail.send.to";
	
	private Properties props = null ;
	
	private Session session = null;
	
	private static MailSenderUtils self = new MailSenderUtils();
	
	private boolean isServiceOK = false;
	
	private MailSenderUtils() {
		isServiceOK = init();
	} 
	
	public static MailSenderUtils getInstance(){
		return self;
	}
	
	/**
	 * 初始化操作
	 * @return
	 */
	private synchronized boolean init (){
		if (isServiceOK) return isServiceOK; 
		props = PropertyUtils.getProps(Constants.MAIL_CONFIG);
		try {
			session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// TODO Auto-generated method stub
					return new PasswordAuthentication(props.getProperty(MAIL_USER_NAME), props.getProperty(MAIL_USER_PASSWORD));
				}
			});
			isServiceOK = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			isServiceOK = false;
			log.error("初始化失败", e);
		}
		return isServiceOK;
	}
	
	/**
	 * 发送邮件
	 * @param mailContent
	 * @return 发送成功：true；失败：false
	 */
	public boolean sendMail(MailContent mailContent) {
		boolean isSuccess = false;
		if (!isServiceOK && !init()) return isSuccess; 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty(MAIL_USER_NAME)));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(props.getProperty(MAIL_SEND_TO)));
			message.setSubject(mailContent.getSubject());
			message.setText(mailContent.getText());
			Transport.send(message);
			isSuccess = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return isSuccess;
	}
	
}
