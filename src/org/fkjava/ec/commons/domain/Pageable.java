package org.fkjava.ec.commons.domain;

/**
 * 分页参数
 * 
 * @author lwq
 *
 */
public class Pageable {

	/**
	 * 第几页，学习Spring Data的思路，从0开始表示第一页
	 */
	private int number;
	/**
	 * 每页多少条记录，maxResults
	 */
	private int size;

	/**
	 * 计算开始行号
	 * 
	 * @return 根据页码、每页最大记录数计算第几行开始读取数据
	 */
	public int getFirstResult() {
		if (number < 0) {
			// 最小0页
			number = 0;
		}
		return number * size;
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
}
