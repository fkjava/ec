package org.fkjava.ec.commerce.mapper.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fkjava.ec.commerce.domain.Article;
import org.fkjava.ec.commerce.mapper.ArticleDao;
import org.fkjava.ec.commerce.vo.IndexPage;
import org.fkjava.ec.commons.mapper.impl.GenericDaoImpl;

public class ArticleDaoImpl extends GenericDaoImpl<Article, Integer> implements ArticleDao {

	@Override
	public Integer countByTypeCodeAndKeyWordLike(String typeCode, String keyword) {
		CriteriaBuilder builder = super.getEntityManager().getCriteriaBuilder();
		// 查询总记录数，根据总记录数计算总页数
		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
		Root<Article> root = countQuery.from(Article.class);
		// count(*)
		Expression<Long> countExp = builder.count(root);
		countQuery.select(countExp);

		// 查询条件
		Predicate predicate = null;
		if (typeCode != null) {
			predicate = builder.like(root.get("typeCode"), typeCode);
		}

		if (keyword != null) {
			if (predicate != null) {
				predicate = builder.and(builder.like(root.get("title"), keyword));
			} else {
				predicate = builder.like(root.get("title"), keyword);
			}
		}

		if (predicate != null) {
			countQuery.where(predicate);
		}

		TypedQuery<Long> tq = super.getEntityManager().createQuery(countQuery);
		Long totalRows = tq.getSingleResult();// 返回一个结果

		// int count = (int) (long) totalRows;

		int count = totalRows.intValue();

		return count;
	}

	@Override
	public List<Article> findByTypeCodeAndKeyWordLike(String typeCode, String keyword, IndexPage page) {

		Class<Article> entityClass = Article.class;

		EntityManager em = this.getEntityManager();

		// 创建条件查询
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Article> cq = builder.createQuery(entityClass);
		Root<Article> root = cq.from(entityClass);

		// 查询条件
		Predicate predicate = null;
		if (typeCode != null) {
			predicate = builder.like(root.get("typeCode"), typeCode);
		}

		if (keyword != null) {
			if (predicate != null) {
				predicate = builder.and(builder.like(root.get("title"), keyword));
			} else {
				predicate = builder.like(root.get("title"), keyword);
			}
		}

		if (predicate != null) {
			cq.where(predicate);
		}

		TypedQuery<Article> query = em.createQuery(cq);
		// 查询缓存
		query.setHint("org.hibernate.cacheable", true);
		// 设置分页参数
		query.setFirstResult(page.getOffset());
		query.setMaxResults(page.getPageSize());

		List<Article> list = query.getResultList();
		return list;
	}

}
