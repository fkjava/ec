package org.fkjava.ec.commons.service.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fkjava.ec.commons.service.EmailService;

public class EmailServiceSMTP implements EmailService {

	private Logger log = LogManager.getLogger(EmailServiceSMTP.class);

	@Override
	public void send(String email, String title, String content) {
		try {
			Properties props = new Properties();
			// 设置发送邮件的服务器地址
			props.setProperty("mail.smtp.host", "smtp.126.com");
			// 设置邮件服务器需要授权，有些邮件服务器是可以匿名发邮件的，不过126需要授权
			props.setProperty("mail.smtp.auth", "true");

			// 登录SMTP服务器的账户
			String senderEmail = "fkmailtest@126.com";
			String senderPassword = "fkjava1org";
			props.setProperty("mail.smtp.user", senderEmail);

			// 直接创建一个邮件会话，使用之前设置好的配置信息
			Session mailSession = Session.getInstance(props, new Authenticator() {

				// 负责登录
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// 提供用户名和密码进行鉴权
					return new PasswordAuthentication(senderEmail, senderPassword);
				}
			});

			// 创建MimeMessage实例，邮件被称之为一个“消息”，所以要一个Message
			MimeMessage msg = new MimeMessage(mailSession);

			// 邮件标题
			msg.setSubject(title);
			// 设置接受者，TO表示接受者、CC表示抄送、BCC表示密送
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			// 设置发送者，如果不设置，并且配置了mail.smtp.from，则会自动设置一个
			msg.setFrom(new InternetAddress(senderEmail));
			// 设置邮件内容，这是一个HTML格式的邮件
			msg.setContent(content, "text/html;charset=UTF-8");

			// 执行发送
			Transport.send(msg);
		} catch (MessagingException e) {
			log.error("发送邮件出现问题：" + e.getMessage(), e);
		}
	}
}
