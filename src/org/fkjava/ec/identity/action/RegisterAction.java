package org.fkjava.ec.identity.action;

import java.util.Map;

import org.fkjava.ec.identity.domain.User;
import org.fkjava.ec.identity.service.IdentityService;
import org.fkjava.ec.identity.service.impl.IdentityServiceImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 复合类型，必须同时具有get和set方法
	// 页面传过来的参数，必须是【属性名.】开头的
	private User user;
	private String title;
	private String message;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute() {

		// Action（控制器）不会处理任何的业务逻辑，所有业务逻辑都应该在业务逻辑层

		// 现在由于没有Spring、也没有其他的IoC容器，所以需要对象的时候都是自己new，或者调用工厂获取。
		// 目前第一个代码先直接new，后面要改为工厂（工厂模式）。
		IdentityService identityService = new IdentityServiceImpl();

		identityService.register(user);

		// 把注册成功的提示信息，放入Session，方便prompt.jsp显示提示信息
		Map<String, Object> session = ActionContext.getContext().getSession();

		title = "注册成功";
		message = "恭喜你注册成功，请登录" //
				+ user.getEmail() //
				+ "邮箱接收激活邮件，完成激活以后才可以正常登录使用！";
		session.put("title", title);
		session.put("message", message);

		return SUCCESS;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}
}
