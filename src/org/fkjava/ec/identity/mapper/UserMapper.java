package org.fkjava.ec.identity.mapper;

import org.fkjava.ec.commons.mapper.GenericDao;
import org.fkjava.ec.identity.domain.User;

public interface UserMapper extends GenericDao<User, Integer> {

	// @Select(value = "select * from id_user where login_name = #{loginName}")
	// 引用XML里面定义的resultMap
	// @ResultMap("userMap")
	User findByLoginName(String loginName);

	// @Insert("insert into id_user(name, login_name, password, email, active_code)
	// "//
	// + "values ( #{name}, #{loginName}, #{password}, #{email}, #{activeCode} )")
	//void save(User user);

	// @Select(value = "select * from id_user where email = #{email}")
	// 每个方法都要自己写@Results注解，不能重用
	// 如果想要重用，只能通过XML来写
	// @Results(value = { //
	// @Result(column = "login_name", property = "loginName"), //
	// @Result(column = "active_code", property = "activeCode")//
	// })
	User findByEmail(String email);

	// @Select(value = "select * from id_user where active_code = #{code}")
	// @ResultMap("userMap")
	User findByActiveCode(String code);

	// @Update("update id_user "//
	// + "set name=#{name}, login_name=#{loginName}, email=#{email},
	// active_code=#{activeCode}"//
	// + " where id=#{id}"//
	// )
	//void update(User user);
}
