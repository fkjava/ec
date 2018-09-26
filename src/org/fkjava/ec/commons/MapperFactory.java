package org.fkjava.ec.commons;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MapperFactory {

	private static SqlSessionFactory sessionFactory;
	private static final ThreadLocal<SqlSession> SESSION = new ThreadLocal<>();

	static {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

		// 利用JDK的类加载器来读取在类路径中的xml文件
		// MapperFactory.class : 获取当前类
		// getResourceAsStream : 在类路径中读取文件
		// /mybatis-config.xml : /开头表示在类路径的根目录开始查找文件

		try (InputStream inputStream = MapperFactory.class.getResourceAsStream("/mybatis-config.xml")) {
			sessionFactory = builder.build(inputStream);
		} catch (Exception e) {
			// 这里不处理异常，直接把异常跑出去
			throw new RuntimeException("初始化MyBatis失败：" + e.getMessage(), e);
		}
	}

	private static SqlSession getSession() {
		// 1.判断SESSION里面是否有存储对象，如果有存储直接返回
		SqlSession session = SESSION.get();
		if (session == null) {
			// 2.如果没有则创建一个新的，并存储到SESSION里面
			session = sessionFactory.openSession();
			SESSION.set(session);
		}
		return session;
	}

	// 使用泛型的目的，是因为不确定具体的类型，需要根据参数传入的类型来决定
	public static <T> T getMapper(Class<T> class1) {
		// 这里获取到的SqlSession只能是在一个线程中唯一的！
		SqlSession session = getSession();
		T t = session.getMapper(class1);
		return t;
	}

	// 先要提交、然后再关闭，这个方法将会放到拦截器或者过滤器里面去调用
	// 所有的请求完成以后，都应该要清理现场
	public static void commitAndClose() {
		SqlSession session = SESSION.get();
		if (session != null) {
			// 提交事务
			session.commit();
			// 关闭连接
			session.close();
			// 清理现场
			SESSION.remove();
		}
	}
}
