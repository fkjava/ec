package org.fkjava.ec.commerce.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.fkjava.ec.identity.domain.User;

@Entity
@Table(name = "ec_order")
public class Order {

	// 主键值
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// 订单时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date orderTime;
	// 订单号
	@Column(name = "order_code")
	private String orderCode;
	// 订单状态
	@Column(name = "status")
	private String orderStatus;
	// 订单明细
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private List<OrderItem> items;
	// 订单的所属用户
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	// 直接在get方法上面写@Column注解，会自动作为一个属性使用
	@Column(name = "amount")
	public Double getSum() {
		Double sum = 0.0;

//		for (ShoppingCartItem item : items.values()) {
//			sum = sum + item.getSum();
//		}

		// 流式API是Java 8以后比较重要的一个特征
		sum = items.stream() // 把所有的值转换为流式API
				// 所有map开头的方法，都会进行流的转换（映射关系）
				// 转换就是对应关系：如何把item和Double关联起来？对于我们现在这个代码：每个item的getSum方法就是对应关系。
				.mapToDouble(item -> item.getSum())// 把所有小计转换为Double流
				.sum();

		return sum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
