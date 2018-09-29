package org.fkjava.ec.identity.action;

import org.fkjava.ec.commons.ServiceFactory;
import org.fkjava.ec.identity.service.IdentityService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ActiveAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 接收验证码
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	// 执行激活操作
	public String execute() {
		IdentityService identityService = ServiceFactory.getIdentityService();
		boolean success = identityService.active(code);
		if (success) {
			return SUCCESS;
		} else {
			ActionContext.getContext().getSession().put("title", "账户激活结果");
			ActionContext.getContext().getSession().put("message", "账户激活失败，可能是验证码已经失效或者账户已经被激活");
			return INPUT;
		}
	}
}
