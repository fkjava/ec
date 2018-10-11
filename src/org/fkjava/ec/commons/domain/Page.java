package org.fkjava.ec.commons.domain;

import java.util.List;

/**
 * 分页返回的数据，表示一页数据
 * 
 * @author lwq
 *
 * @param <T> 实际的实体数据类型
 */
public class Page<T> {

	/**
	 * 当前页的内容
	 */
	private List<T> content;
	/**
	 * 页码
	 */
	private int number;
	/**
	 * 当前也最大记录数
	 */
	private int size;
	/**
	 * 总行数、总记录数
	 */
	private long totalRows;
	/**
	 * 总页数
	 */
	private int totalPages;
	/**
	 * 当前页读取到的记录数
	 */
	private int rows;

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;

		this.totalPages = (int) (this.totalRows / this.size);
		if (this.totalRows % this.size != 0) {
			// 不能整除，加上一页
			this.totalPages++;
		}
		if (totalPages == 0) {
			// 最少1页
			this.totalPages = 1;
		}
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
