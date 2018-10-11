package org.fkjava.ec.commerce.mapper;

import java.util.List;

import org.fkjava.ec.commerce.domain.Article;
import org.fkjava.ec.commerce.vo.IndexPage;
import org.fkjava.ec.commons.mapper.GenericDao;

public interface ArticleDao extends GenericDao<Article, Integer> {

	Integer countByTypeCodeAndKeyWordLike(String typeCode, String keyword);

	List<Article> findByTypeCodeAndKeyWordLike(String typeCode, String keyword, IndexPage page);

	// 当方法的参数只使用一次的时候，可以不考虑@Param。如果参数要使用多次，必须使用@Param指定参数名
	// 如果有多个参数的时候，也必须使用@Param注解
//	@Select("select * from ec_article where id=#{id}")
//	@ResultMap("articleMap")
	//Article findById(String id);

}
