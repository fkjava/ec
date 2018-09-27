package org.fkjava.ec.commons.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fkjava.ec.commons.action.ValidateCodeAction;

public class ValidateCodeFilter extends HttpFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		// 用户输入的验证码
		String imageValidateCode = request.getParameter("imageValidateCode");
		if (imageValidateCode == null) {
			// 没有该参数（不是没有输入、是根本没有对应的输入框）
			// 假设有输入框，name为imageValidateCode，但是没有输入，此时得到空字符串""

			// 直接下一步
			super.doFilter(request, response, chain);
			session.removeAttribute("allParams");
		} else {
			// 从Session里面获取保存在服务器里面的随机验证码
			String key = ValidateCodeAction.class.getSimpleName() + ".randomCode";
			String randomCode = (String) session.getAttribute(key);

			// 把Session里面的验证码删除，避免重复使用
			session.removeAttribute(key);

			// 判断randomCode和imageValidateCode是否相同，如果相同表示请求有效
			// equalsIgnoreCase比较的时候忽略大小写
			if (imageValidateCode.equalsIgnoreCase(randomCode)) {
				// 直接下一步
				super.doFilter(request, response, chain);
				session.removeAttribute("allParams");
			} else {
				// 回到原来的页面
				String uri = request.getHeader("Referer");

				// 获取请求中所有的参数，以键值对方式返回
				Map<String, String[]> params = new HashMap<>();
				params.putAll(request.getParameterMap());
				// 把参数全部放入Session里面
				session.setAttribute("allParams", params);
//				System.out.println(params.get("user.loginName")[0]);
				session.setAttribute("message", "验证码错误");
//				String contextPath = super.getServletContext().getContextPath();
//				if (contextPath.length() > 0) {
//					// 把uri里面的contextPath截取掉
//					uri = uri.substring(uri.indexOf(contextPath) + contextPath.length());
//				}

				// request.getRequestDispatcher(uri).forward(request, response);
				response.sendRedirect(uri);
			}
		}
	}
}
