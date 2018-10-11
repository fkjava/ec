package org.fkjava.ec.commerce.action;

import org.fkjava.ec.commerce.domain.ShoppingCart;
import org.fkjava.ec.commerce.service.CommerceService;
import org.fkjava.ec.commons.ServiceFactory;

import com.opensymphony.xwork2.ActionSupport;

public class ShowCartAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShoppingCart getShoppingCart() {
		CommerceService commerceService = ServiceFactory.getCommerceService();
		return commerceService.getShoppingCart();
	}
}
