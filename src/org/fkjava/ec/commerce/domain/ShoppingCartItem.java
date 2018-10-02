package org.fkjava.ec.commerce.domain;

public class ShoppingCartItem {

	// 购买哪个商品
	private Article article;
	// 购买多少件
	private Integer number;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
