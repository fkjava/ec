package org.fkjava.ec.commerce.action;

import org.fkjava.ec.commerce.service.CommerceService;
import org.fkjava.ec.commerce.vo.DetailPage;
import org.fkjava.ec.commons.ServiceFactory;

import com.opensymphony.xwork2.ActionSupport;

public class DetailAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 接收请求参数，要有set
	private String id;
	// 返回数据给页面，要有get
	private DetailPage page;

	public DetailPage getPage() {
		return page;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String execute() {
		CommerceService commerceService = ServiceFactory.getCommerceService();

		page = commerceService.getDetailPage(id);

		return SUCCESS;
	}
}
