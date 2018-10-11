package org.fkjava.ec.commerce.domain;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// 商品类型
@Entity
@Table(name = "ec_article_type")
@Cacheable
public class ArticleType {

	@Id
	// 不是自动生成的主键，有分级关系的
	// 所以这里不需要主键生成器
	private String code;
	private String name;
	private String remark;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
