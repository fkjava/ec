package org.fkjava.ec.identity.action;

import org.fkjava.ec.identity.domain.User;
import org.fkjava.ec.identity.service.IdentityService;
import org.fkjava.ec.identity.service.impl.IdentityServiceImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	private String message;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute() {

		IdentityService identityService = new IdentityServiceImpl();
		User user = identityService.login(this.user.getLoginName(), this.user.getPassword());
		// 登录失败，得到null，返回到登录页面
		if (user == null) {
			// 把名为message的对象放入request。
			// 如果通过getSession()来put，那么就是放入Session。
			// ActionContext.getContext().put("message", "登录名或者密码错误！");
			message = "登录名或者密码错误！";
			ActionContext.getContext().getSession().put("message", message);

			// ActionContext.getContext().getSession().put("user", user);
			return INPUT;
		} else {
			// 把User设置到Session里面
			message = null;
			this.user = user;
			ActionContext.getContext().getSession().put("user", user);
		}
		return SUCCESS;
	}

	public String getMessage() {
		return message;
	}
}
