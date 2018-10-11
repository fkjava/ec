package org.fkjava.ec.commerce.action;

import org.fkjava.ec.commerce.domain.Order;
import org.fkjava.ec.commerce.service.CommerceService;
import org.fkjava.ec.commons.ServiceFactory;
import org.fkjava.ec.commons.domain.Page;
import org.fkjava.ec.commons.domain.Pageable;
import org.fkjava.ec.identity.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OrderAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 页码
	private int number;

	public String submit() {

		// 获取当前用户，每个用户的订单都要隔离
		// 必须要确保有登录才能执行后面的操作，所以此时需要通过拦截器判断是否已经登录，如果没有登录跳转到登录页面
		User user = (User) ActionContext.getContext().getSession().get("user");

		CommerceService commerceService = ServiceFactory.getCommerceService();

		// 如果有配送信息、支付方式等参数，通过方法传入给业务逻辑层
		// 购物车不需要传入，因为购物车本身已经通过拦截器放入ThreadLocal里面
		commerceService.submitOrder(user);
		return SUCCESS;
	}

	public String listOrder() {
		CommerceService commerceService = ServiceFactory.getCommerceService();
		User user = (User) ActionContext.getContext().getSession().get("user");

		Pageable pageable = new Pageable();
		pageable.setNumber(number);
		pageable.setSize(10);
		Page<Order> page = commerceService.findOrders(user, pageable);
		ActionContext.getContext().put("page", page);
		return SUCCESS;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
