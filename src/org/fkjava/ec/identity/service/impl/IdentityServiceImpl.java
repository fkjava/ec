package org.fkjava.ec.identity.service.impl;

import java.util.UUID;

import org.fkjava.ec.commons.MapperFactory;
import org.fkjava.ec.identity.domain.User;
import org.fkjava.ec.identity.mapper.UserMapper;
import org.fkjava.ec.identity.service.IdentityService;

public class IdentityServiceImpl implements IdentityService {

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
			// 生成随机激活码，UUID全称叫做“世界/宇宙唯一标识符”，目前长度是36个字符
			String uuid = UUID.randomUUID().toString();
			user.setActiveCode(uuid);

			userDao.save(user);
		}

		// TODO 3.保存以后，要发送激活邮件
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
}
