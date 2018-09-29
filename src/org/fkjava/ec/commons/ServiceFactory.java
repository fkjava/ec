package org.fkjava.ec.commons;

import org.fkjava.ec.commons.service.EmailService;
import org.fkjava.ec.commons.service.impl.EmailServiceSMTP;
import org.fkjava.ec.identity.service.IdentityService;
import org.fkjava.ec.identity.service.impl.IdentityServiceImpl;

public class ServiceFactory {

	// 基于工厂模式的单例模式
	// 所谓的工厂模式：通过工厂来获得产品（实例），通过工厂统一修改所有使用对象的地方
	// 单例模式：只有一个实例
	static final private EmailService emailService = new EmailServiceSMTP();

	private static final IdentityService IDENTITY_SERVICE = new IdentityServiceImpl();

	public static EmailService getEmailService() {
		return emailService;
	}

	public static IdentityService getIdentityService() {
		return IDENTITY_SERVICE;
	}
}
