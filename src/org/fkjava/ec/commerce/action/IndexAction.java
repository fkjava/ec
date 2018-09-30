package org.fkjava.ec.commerce.action;

import org.fkjava.ec.commerce.service.CommerceService;
import org.fkjava.ec.commerce.vo.IndexPage;
import org.fkjava.ec.commons.ServiceFactory;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 页码
	private Integer number;
	// 关键词
	private String keyword;
	// 类型的主键值，通过类型查询的时候需要使用
	private String typeCode;
	private IndexPage page;

	public String execute() {
		page = new IndexPage();
		if (number != null) {
			page.setNumber(number);
		} else {
			// 第一页从0开始
			page.setNumber(0);
		}

		// 设置每页读取12条记录
		page.setPageSize(6);

		// 调用业务逻辑层查询数据
		CommerceService commerceService = ServiceFactory.getCommerceService();
		page = commerceService.getIndexPage(typeCode, keyword, page);

		return SUCCESS;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public IndexPage getPage() {
		return page;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
}
