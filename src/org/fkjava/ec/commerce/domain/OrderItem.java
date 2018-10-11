package org.fkjava.ec.commerce.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ec_order_item")
public class OrderItem {

	// 明细的id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// 购买的商品
	@ManyToOne()
	@JoinColumn(name = "article_id")
	private Article article;
	// 购买数量
	@Column(name = "order_num")
	private int number;
	// 对应哪个订单
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private Order order;

	public Double getSum() {
		return this.number * this.article.getDiscountPrice();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
