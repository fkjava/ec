package org.fkjava.ec.commons.service;

import org.fkjava.ec.commons.service.impl.EmailServiceSMTP;

public interface EmailService {

	public void send(String email, String title, String content);
	
	public static void main(String[] args) {
		EmailService emailService = new EmailServiceSMTP();
		emailService.send("luo_wenqiang@qq.com", "激活邮件", "ddddddddddddd");
	}
}
