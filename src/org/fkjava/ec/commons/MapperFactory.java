package org.fkjava.ec.commons;

import java.util.HashMap;
import java.util.Map;

import org.fkjava.ec.commerce.mapper.ArticleDao;
import org.fkjava.ec.commerce.mapper.ArticleTypeDao;
import org.fkjava.ec.commerce.mapper.OrderMapper;
import org.fkjava.ec.commerce.mapper.impl.ArticleDaoImpl;
import org.fkjava.ec.commerce.mapper.impl.ArticleTypeDaoImpl;
import org.fkjava.ec.commerce.mapper.impl.OrderMapperImpl;
import org.fkjava.ec.commons.mapper.GenericDao;
import org.fkjava.ec.identity.mapper.UserMapper;
import org.fkjava.ec.identity.mapper.impl.UserMapperImpl;

public class MapperFactory {

	// 不能自动确定哪个类实现了DAO，而我们的代码里面需要DAO接口作为数据类型使用
	// 所以使用一个Map把DAO的实例和对应的接口类型保存起来
	private static final Map<Class<?>, Object> daoMap = new HashMap<>();
	static {
		UserMapper userMapper = new UserMapperImpl();
		daoMap.put(UserMapper.class, userMapper);

		ArticleDao articleDao = new ArticleDaoImpl();
		daoMap.put(ArticleDao.class, articleDao);

		ArticleTypeDao articleTypeDao = new ArticleTypeDaoImpl();
		daoMap.put(ArticleTypeDao.class, articleTypeDao);

		OrderMapper orderMapper = new OrderMapperImpl();
		daoMap.put(OrderMapper.class, orderMapper);
	}

	// 使用泛型的目的，是因为不确定具体的类型，需要根据参数传入的类型来决定
	@SuppressWarnings("unchecked")
	public static <T extends GenericDao<?, ?>> T getMapper(Class<T> clazz) {
		T dao = (T) daoMap.get(clazz);
		dao.begin();
		return dao;
	}

	// 先要提交、然后再关闭，这个方法将会放到拦截器或者过滤器里面去调用
	// 所有的请求完成以后，都应该要清理现场
	public static void commitAndClose() {
		// 获取任意的一个DAO，就可以把当前线程的事务提交、关闭当前线程的连接
		UserMapper userMapper = new UserMapperImpl();
		userMapper.commit();
	}
}
