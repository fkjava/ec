package org.fkjava.ec.commerce.vo;

import java.util.List;

import org.fkjava.ec.commerce.domain.Article;
import org.fkjava.ec.commerce.domain.ArticleType;

// VO : 值对象（Value Object），用于封装在各个层之间传递的数据，不需要保存到数据库的。
public class IndexPage {

	// 查询出来的商品
	private List<Article> articles;
	// 所有的一级类型
	private List<ArticleType> topTypes;
	// 包含二级类型
	private List<ArticleType> types;

	// 第几页，从0开始
	private Integer number;
	// 一共多少页
	private Integer totalPages;
	// 当前页需要多少条记录
	private Integer pageSize;
	// 一共多少行
	private Integer totalRows;

	// 计算当前页的偏移量，从第几条记录开始读取数据
	public Integer getOffset() {
		// 比如现在第一页，偏移量应该是0
		// 如果是第二页，偏移量12
		return number * pageSize;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;

		// 计算总页数
		// 如果总记录数能够被当pageSize整除，商就是总页数，否则总页数要加1
//		this.totalPages = this.totalRows / this.pageSize;
//		if (this.totalRows % this.pageSize != 0) {
//			this.totalPages++;
//		}

		this.totalPages = (this.totalRows % this.pageSize == 0)//
				? (this.totalRows / this.pageSize)//
				: (this.totalRows / this.pageSize + 1);

		if (this.totalPages == 0) {
			this.totalPages = 1;
		}

		// number从0开始，但是totalPages从1开始
		if (number >= this.totalPages) {
			// 限制页面超过总页数
			number = this.totalPages - 1;
		}
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<ArticleType> getTopTypes() {
		return topTypes;
	}

	public void setTopTypes(List<ArticleType> topTypes) {
		this.topTypes = topTypes;
	}

	public List<ArticleType> getTypes() {
		return types;
	}

	public void setTypes(List<ArticleType> types) {
		this.types = types;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalRows() {
		return totalRows;
	}
}
