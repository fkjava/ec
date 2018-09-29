package org.fkjava.ec.commerce.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.fkjava.ec.commerce.domain.Article;
import org.fkjava.ec.commerce.vo.IndexPage;

public interface ArticleDao {

	Integer countByTypeCodeAndKeyWord(@Param("typeCode") String typeCode, @Param("keyword") String keyword);

	List<Article> findByTypeCodeAndKeyWord(@Param("typeCode") String typeCode, @Param("keyword") String keyword,
			@Param("page") IndexPage page);

}
