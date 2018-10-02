package org.fkjava.ec.commerce.domain;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

	// 根据id就可以找到对应的购买明细（里面包含商品、购买数量）
	private Map<Integer, ShoppingCartItem> items = new HashMap<>();

	public Double getSum() {
		Double sum = 0.0;

//		for (ShoppingCartItem item : items.values()) {
//			sum = sum + item.getSum();
//		}

		// 流式API是Java 8以后比较重要的一个特征
		sum = items.values().stream() // 把所有的值转换为流式API
				// 所有map开头的方法，都会进行流的转换（映射关系）
				// 转换就是对应关系：如何把item和Double关联起来？对于我们现在这个代码：每个item的getSum方法就是对应关系。
				.mapToDouble(item -> item.getSum())// 把所有小计转换为Double流
				.sum();

		return sum;
	}

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

	public void delete(Integer id) {
		items.remove(id);
	}
}
