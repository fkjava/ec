package org.fkjava.ec.commerce.domain;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

	// 根据id就可以找到对应的购买明细（里面包含商品、购买数量）
	private Map<Integer, ShoppingCartItem> items = new HashMap<>();

	public Map<Integer, ShoppingCartItem> getItems() {
		return items;
	}

	// 根据id获取购买的明细
	public ShoppingCartItem getItem(Integer id) {
		return items.get(id);
	}

	public void addItem(Integer id, ShoppingCartItem item) {
		items.put(id, item);
	}
}
