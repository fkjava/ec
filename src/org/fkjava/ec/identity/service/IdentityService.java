package org.fkjava.ec.identity.service;

import org.fkjava.ec.identity.domain.User;

public interface IdentityService {

	void register(User user);

	User login(String loginName, String password);

}
