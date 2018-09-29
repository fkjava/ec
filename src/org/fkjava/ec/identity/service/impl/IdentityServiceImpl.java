package org.fkjava.ec.identity.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fkjava.ec.commons.MapperFactory;
import org.fkjava.ec.commons.ServiceFactory;
import org.fkjava.ec.commons.service.EmailService;
import org.fkjava.ec.identity.domain.User;
import org.fkjava.ec.identity.mapper.UserMapper;
import org.fkjava.ec.identity.service.IdentityService;

public class IdentityServiceImpl implements IdentityService {

	private Logger log = LogManager.getLogger(IdentityServiceImpl.class);
	// 激活邮件的模板
	private String activeTemplate;

	public IdentityServiceImpl() {
		// 读取账户激活邮件的模板内容备用
		try (InputStream in = IdentityServiceImpl.class.getResourceAsStream("/email-template-active.html"); //
				InputStreamReader isr = new InputStreamReader(in, "UTF-8"); //
				BufferedReader reader = new BufferedReader(isr);//
		) {
			StringBuilder builder = new StringBuilder();
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				builder.append(line);
				builder.append("\n");
			}
			activeTemplate = builder.toString();
		} catch (IOException e) {
			// e.printStackTrace();
			log.error("读取账户激活邮件模板出现问题：" + e.getMessage(), e);
		}
	}

	@Override
	public void register(User user) {
		// MapperFactory负责初始化MyBatis、获取MyBatis的SqlSession、获取Mapper接口的实例
		UserMapper userDao = MapperFactory.getMapper(UserMapper.class);
		// 1.检查登录名是否被占用
		User old = userDao.findByLoginName(user.getLoginName());

		// 2.如果没有占用，则生成随机验证码、保存数据
		if (old != null) {
			// 在数据库查询到数据，登录名被占用
			throw new IllegalArgumentException("登录名已经被占用，不能重复使用");
		} else {
			old = userDao.findByEmail(user.getEmail());
			if (old != null) {
				throw new IllegalArgumentException("邮件地址已经被占用，不能重复使用");
			}
			// 生成随机激活码，UUID全称叫做“世界/宇宙唯一标识符”，目前长度是36个字符
			String uuid = UUID.randomUUID().toString();
			user.setActiveCode(uuid);

			userDao.save(user);
		}

		// 3.保存以后，要发送激活邮件
		// 接受者
		String email = user.getEmail();
		String title = "【疯狂软件】账户激活邮件";
		// 邮件内容就是HTML，读取模板文件，替换里面的${activeCode}
		// String.replace()方法
		String content = activeTemplate.replace("${activeCode}", user.getActiveCode());

		EmailService emailService = ServiceFactory.getEmailService();
		emailService.send(email, title, content);
	}

	@Override
	public User login(String loginName, String password) {
		UserMapper userDao = MapperFactory.getMapper(UserMapper.class);
		// 1.要根据登录名查找User对象
		User user = userDao.findByLoginName(loginName);
		// 2.判断User对象是否为空
		if (user == null) {
			return null;
		} else {
			// 3.如果不为空则判断密码是否匹配
			if (user.getPassword().equals(password)) {
				return user;
			} else {
				return null;
			}
		}
	}

	@Override
	public boolean active(String code) {
		// 1.根据验证码查询User对象
		UserMapper userDao = MapperFactory.getMapper(UserMapper.class);
		User user = userDao.findByActiveCode(code);
		if (user == null) {
			// 2.如果没有找到返回激活失败
			return false;
		} else {
			// 3.如果找到则把账户的激活码去掉，表示激活成功
			user.setActiveCode(null);
			userDao.update(user);
			return true;
		}
	}
}
