package org.fkjava.ec.identity.mapper.impl;

import org.fkjava.ec.commons.mapper.impl.GenericDaoImpl;
import org.fkjava.ec.identity.domain.User;
import org.fkjava.ec.identity.mapper.UserMapper;

public class UserMapperImpl extends GenericDaoImpl<User, Integer> implements UserMapper {

	@Override
	public User findByLoginName(String loginName) {
		String ql = "from User where loginName = ?1";
		return super.findOne(ql, User.class, loginName);
	}

	@Override
	public User findByEmail(String email) {
		String ql = "from User where email = ?1";
		return super.findOne(ql, User.class, email);
	}

	@Override
	public User findByActiveCode(String code) {
		String ql = "from User where activeCode = ?1";
		return super.findOne(ql, User.class, code);
	}
}
