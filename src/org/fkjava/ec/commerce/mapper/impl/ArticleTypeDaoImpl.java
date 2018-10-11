package org.fkjava.ec.commerce.mapper.impl;

import java.util.List;

import org.fkjava.ec.commerce.domain.ArticleType;
import org.fkjava.ec.commerce.mapper.ArticleTypeDao;
import org.fkjava.ec.commons.mapper.impl.GenericDaoImpl;

public class ArticleTypeDaoImpl extends GenericDaoImpl<ArticleType, String> implements ArticleTypeDao {

	// select * from ec_article_type where length(code) = 4
	@Override
	public List<ArticleType> findTopTypes() {
		String ql = "from ArticleType where length(code) = 4";
		return super.findListFromCache(ql, ArticleType.class, new Object[0]);
	}

	@Override
	public List<ArticleType> findByCodeLike(String typeCode) {
		String ql = "from ArticleType where code like ?1";
		return super.findListFromCache(ql, ArticleType.class, typeCode);
	}

}
