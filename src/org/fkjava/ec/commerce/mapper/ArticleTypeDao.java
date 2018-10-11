package org.fkjava.ec.commerce.mapper;

import java.util.List;

import org.fkjava.ec.commerce.domain.ArticleType;
import org.fkjava.ec.commons.mapper.GenericDao;

public interface ArticleTypeDao extends GenericDao<ArticleType, String> {

	// 长度4的就是一级类型
	// @Select("select * from ec_article_type where length(code) = 4")
	List<ArticleType> findTopTypes();

	// 注意：typeCode可能为null，因此需要使用动态SQL语句，要用XML
	List<ArticleType> findByCodeLike(String typeCode);

}
