package org.fkjava.ec.commons.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fkjava.ec.commons.MapperFactory;

// 以前Servlet都是要实现javax.servlet.Servlet接口的
// 后来大家觉得我们只需要service方法，实现接口太麻烦，于是有HttpServlet（适配器模式）
// 但是一直以来，Filter就是没有适配器，总是要实现一堆的抽象方法
// 在Servlet 4.0的时候，Filter也有了适配器（HttpFilter）
//@WebFilter("/*")
public class CommitAndCloseSqlSessionFilter extends HttpFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// 继续执行请求
		try {
			super.doFilter(request, response, chain);
		} finally {
			// 请求执行完成以后
			MapperFactory.commitAndClose();
		}
	}
}
