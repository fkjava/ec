package org.fkjava.ec.commerce.service.impl;

import java.util.List;

import org.fkjava.ec.commerce.domain.Article;
import org.fkjava.ec.commerce.domain.ArticleType;
import org.fkjava.ec.commerce.domain.ShoppingCart;
import org.fkjava.ec.commerce.domain.ShoppingCartItem;
import org.fkjava.ec.commerce.mapper.ArticleDao;
import org.fkjava.ec.commerce.mapper.ArticleTypeDao;
import org.fkjava.ec.commerce.service.CommerceService;
import org.fkjava.ec.commerce.vo.DetailPage;
import org.fkjava.ec.commerce.vo.IndexPage;
import org.fkjava.ec.commons.MapperFactory;
import org.fkjava.ec.commons.ServiceFactory;

public class CommerceServiceImpl implements CommerceService {

	private static final ThreadLocal<ShoppingCart> THREAD_LOCAL_CART = new ThreadLocal<>();

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

		String serachKeyword = keyword;
		if ("".equals(serachKeyword)) {
			serachKeyword = null;
		}
		if (serachKeyword != null) {
			serachKeyword = "%" + serachKeyword + "%";
		}
		// 3.如果有typeCode，要根据typeCode查询商品，包括子类型商品（like查询）；如果没有typeCode查询所有商品
		// 3.1.要查询总记录数
		Integer totalRows = articleDao.countByTypeCodeAndKeyWordLike(longCode, serachKeyword);
		page.setTotalRows(totalRows);
		// 3.2.查询当前页的记录
		List<Article> articles = articleDao.findByTypeCodeAndKeyWordLike(longCode, serachKeyword, page);

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

	@Override
	public DetailPage getDetailPage(String id) {
		// 1.商品详情，目前只考虑查询一个Article对象
		ArticleDao articleDao = MapperFactory.getMapper(ArticleDao.class);
		Article article = articleDao.findById(id);

		// 扩展：商品的类型、购买记录、评论记录、相关性商品（推荐）、同类型商品

		DetailPage page = new DetailPage();
		page.setArticle(article);

		return page;
	}

	@Override
	public void addToCart(Integer id, int number) {
		ShoppingCart cart = this.getShoppingCart();
		// 1.判断购物车里面是否有id对应的商品
		ShoppingCartItem item = cart.getItem(id);
		if (item == null) {
			// 2.如果没有对应的商品，需要根据id从数据库把商品查询出来
			ArticleDao articleDao = MapperFactory.getMapper(ArticleDao.class);
			Article article = articleDao.findById(String.valueOf(id));

			// 3.把商品和购买的数量，封装成一个对象（ShoppingCartItem）
			// ShoppingCart是一个购物车，ShoppingCartItem表示购物车里面的一种商品（包括商品、购买数量）
			item = new ShoppingCartItem();
			item.setArticle(article);
			item.setNumber(number);

			cart.addItem(id, item);
		} else {
			// 4.如果有对应的商品，把ShoppingCartItem获取出来，累加数量
			number = number + item.getNumber();// 累加
			item.setNumber(number);
		}
	}

	// 调用此方法随时可以得到拦截器设置进来的购物车
	public ShoppingCart getShoppingCart() {
		return THREAD_LOCAL_CART.get();
	}

	@Override
	public void setShoppingCart(ShoppingCart cart) {
		THREAD_LOCAL_CART.set(cart);
	}

	@Override
	public void removeShoppingCart() {
		THREAD_LOCAL_CART.remove();
	}

	@Override
	public void updateCart(Integer id, int number) {
		ShoppingCart cart = this.getShoppingCart();
		// 1.判断购物车里面是否有id对应的商品
		ShoppingCartItem item = cart.getItem(id);
		if (item == null) {
			// 2.如果没有对应的商品，需要根据id从数据库把商品查询出来
			ArticleDao articleDao = MapperFactory.getMapper(ArticleDao.class);
			Article article = articleDao.findById(String.valueOf(id));

			// 3.把商品和购买的数量，封装成一个对象（ShoppingCartItem）
			// ShoppingCart是一个购物车，ShoppingCartItem表示购物车里面的一种商品（包括商品、购买数量）
			item = new ShoppingCartItem();
			item.setArticle(article);
			item.setNumber(number);

			cart.addItem(id, item);
		} else {
			// 4.修改数量
			item.setNumber(number);
		}
	}
	
	@Override
	public void deleteCart(Integer id) {
		ShoppingCart cart = this.getShoppingCart();
		cart.delete(id);
	}
}
