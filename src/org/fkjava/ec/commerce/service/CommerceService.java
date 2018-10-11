package org.fkjava.ec.commerce.service;

import org.fkjava.ec.commerce.domain.Order;
import org.fkjava.ec.commerce.domain.ShoppingCart;
import org.fkjava.ec.commerce.vo.DetailPage;
import org.fkjava.ec.commerce.vo.IndexPage;
import org.fkjava.ec.commons.domain.Page;
import org.fkjava.ec.commons.domain.Pageable;
import org.fkjava.ec.identity.domain.User;

public interface CommerceService {

	// 购物车存储在Session里面对应的KEY
	public static final String SESSION_CART_KEY = "CommerceService.shoppingCart";

	IndexPage getIndexPage(String typeCode, String keyword, IndexPage page);

	DetailPage getDetailPage(String id);

	void addToCart(Integer id, int number);

	public ShoppingCart getShoppingCart();

	void setShoppingCart(ShoppingCart cart);

	void removeShoppingCart();

	void updateCart(Integer id, int number);

	void deleteCart(Integer id);

	void submitOrder(User user);

	Page<Order> findOrders(User user, Pageable pageable);

}
