package org.fkjava.ec.commerce.action;

import org.fkjava.ec.commerce.service.CommerceService;
import org.fkjava.ec.commons.ServiceFactory;

import com.opensymphony.xwork2.ActionSupport;

public class ShoppingCartAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 商品的id
	private Integer id;
	// 购买的数量
	private int number;

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String add() {

		// 调用业务逻辑层的代码，把id和number传入进去
		CommerceService commerceService = ServiceFactory.getCommerceService();
		commerceService.addToCart(id, number);

		return SUCCESS;
	}
}
