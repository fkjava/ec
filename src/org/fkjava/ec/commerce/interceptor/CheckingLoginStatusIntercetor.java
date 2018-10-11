package org.fkjava.ec.commerce.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckingLoginStatusIntercetor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		// 检查Session里面是否有User对象，如果没有则重定向到登录页面；否则继续请求
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.containsKey("user")) {
			return invocation.invoke();
		} else {
			// 返回固定的result名字，找到对应的全局result
			return "redirectToLoginForm";
		}
	}
}
