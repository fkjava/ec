package org.fkjava.ec.commerce.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.fkjava.ec.commerce.domain.Article;
import org.fkjava.ec.commerce.vo.IndexPage;

public interface ArticleDao {

	Integer countByTypeCodeAndKeyWordLike(@Param("typeCode") String typeCode, @Param("keyword") String keyword);

	List<Article> findByTypeCodeAndKeyWordLike(@Param("typeCode") String typeCode, @Param("keyword") String keyword,
			@Param("page") IndexPage page);

	// 当方法的参数只使用一次的时候，可以不考虑@Param。如果参数要使用多次，必须使用@Param指定参数名
	// 如果有多个参数的时候，也必须使用@Param注解
	@Select("select * from ec_article where id=#{id}")
	@ResultMap("articleMap")
	Article findById(String id);

}
