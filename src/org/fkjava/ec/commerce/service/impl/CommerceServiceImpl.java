package org.fkjava.ec.commerce.service.impl;

import java.util.List;

import org.fkjava.ec.commerce.domain.Article;
import org.fkjava.ec.commerce.domain.ArticleType;
import org.fkjava.ec.commerce.mapper.ArticleDao;
import org.fkjava.ec.commerce.mapper.ArticleTypeDao;
import org.fkjava.ec.commerce.service.CommerceService;
import org.fkjava.ec.commerce.vo.IndexPage;
import org.fkjava.ec.commons.MapperFactory;
import org.fkjava.ec.commons.ServiceFactory;

public class CommerceServiceImpl implements CommerceService {

	@Override
	public IndexPage getIndexPage(final String typeCode, final String keyword, final IndexPage page) {
		ArticleTypeDao articleTypeDao = MapperFactory.getMapper(ArticleTypeDao.class);
		// 1.查询一级类型
		List<ArticleType> topTypes = articleTypeDao.findTopTypes();
		// 2.如果有typeCode，根据typeCode查询二级类型；如果没有typeCode查询所有类型
		String shortCode = typeCode;
		String longCode = typeCode;
		if ("".equals(typeCode)) {
			shortCode = null;
			longCode = null;
		}
		if (shortCode != null) {
			// 永远用一级类型去模糊查询
			shortCode = shortCode.substring(0, 4);
			// 如果有typeCode那么就模糊查询
			shortCode = shortCode + "%";
		}
		if (longCode != null) {
			longCode = longCode + "%";
		}
		List<ArticleType> types = articleTypeDao.findByCodeLike(shortCode);

		ArticleDao articleDao = MapperFactory.getMapper(ArticleDao.class);
		// 3.如果有typeCode，要根据typeCode查询商品，包括子类型商品（like查询）；如果没有typeCode查询所有商品
		// 3.1.要查询总记录数
		Integer totalRows = articleDao.countByTypeCodeAndKeyWord(longCode, keyword);
		page.setTotalRows(totalRows);
		// 3.2.查询当前页的记录
		List<Article> articles = articleDao.findByTypeCodeAndKeyWord(longCode, keyword, page);

		page.setArticles(articles);
		page.setTopTypes(topTypes);
		page.setTypes(types);
		return page;
	}

	public static void main(String[] args) {
		IndexPage page = new IndexPage();
		page.setNumber(0);
		page.setPageSize(12);
		CommerceService cs = ServiceFactory.getCommerceService();
		cs.getIndexPage(null, null, page);
		System.out.println("------------------");
		cs.getIndexPage("0001", null, page);
		System.out.println("------------------");
		cs.getIndexPage("00020001", null, page);
	}
}
