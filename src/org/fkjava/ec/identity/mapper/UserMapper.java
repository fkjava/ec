package org.fkjava.ec.identity.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.fkjava.ec.identity.domain.User;

public interface UserMapper {

	@Select(value = "select * from id_user where login_name = #{loginName}")
	User findByLoginName(@Param("loginName") String loginName);

	@Insert("insert into id_user(name, login_name, password, email, active_code) "//
			+ "values ( #{name}, #{loginName}, #{password}, #{email}, #{activeCode} )")
	void save(User user);
}
