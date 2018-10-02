package org.fkjava.ec.commerce.interceptor;

import java.util.Map;

import org.fkjava.ec.commerce.domain.ShoppingCart;
import org.fkjava.ec.commerce.service.CommerceService;
import org.fkjava.ec.commons.ServiceFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

// 检查Session里面是否有购物车，如果没有创建一个；
// 如果Session里面有购物车，获取出来，存储CommerceService里面的ThreadLocal对象
// 拦截器执行完成以后，需要把ThreadLocal里面的购物车清除
public class CheckingShoppingCartInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 检查是否是否有购物车
		Map<String, Object> session = ActionContext.getContext().getSession();
		ShoppingCart cart = (ShoppingCart) session.get(CommerceService.SESSION_CART_KEY);
		if (cart == null) {
			// Session里面没有购物车，创建一个并放入Session
			cart = new ShoppingCart();
			session.put(CommerceService.SESSION_CART_KEY, cart);
		}
		// 把购物车放入ThreadLocal里面
		CommerceService commerceService = ServiceFactory.getCommerceService();
		commerceService.setShoppingCart(cart);

		// 继续执行后面的Action
		return invocation.invoke();
	}

	@Override
	public void destroy() {
		super.destroy();

		// 从ThreadLocal里面删除购物车
		CommerceService commerceService = ServiceFactory.getCommerceService();
		commerceService.removeShoppingCart();
	}

}
