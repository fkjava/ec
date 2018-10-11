package org.fkjava.ec.commons.mapper.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fkjava.ec.commons.domain.Page;
import org.fkjava.ec.commons.domain.Pageable;
import org.fkjava.ec.commons.mapper.GenericDao;

/**
 * 子类继承通用DAO以后，传入实际的实体类型和ID类型
 * 
 * @author lwq
 *
 * @param <T>
 * @param <ID>
 */
public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

	// 这里不考虑其他的初始化方式，仅初始化一个默认的实体管理器工厂
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;
	static {
		ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("jpa-01");
	}
	// EntityManagerFactory在整个应用就一个，表示对应数据库
	// EntityManager则表示一个数据库的连接，如果有多个用户来访问我们的程序，每个用户需要一个连接（EntityManager）
	// 相当于把以前的SqlSession放入ThreadLocal里面一样！
	private static final ThreadLocal<EntityManager> THREAD_LOCAL = new ThreadLocal<>();
	private static final ThreadLocal<EntityTransaction> LOCAL_TRANSACTION = new ThreadLocal<>();// 存储事务

	@Override
	public EntityManager getEntityManager() {
		EntityManager em = THREAD_LOCAL.get();
		if (em == null) {
			em = ENTITY_MANAGER_FACTORY.createEntityManager();
			THREAD_LOCAL.set(em);
		}
		return em;
	}

	@Override
	public void begin() {
		EntityTransaction tx = LOCAL_TRANSACTION.get();
		if (tx == null) {
			tx = this.getEntityManager().getTransaction();
			tx.begin();
			LOCAL_TRANSACTION.set(tx);
		}
	}

	@Override
	public void commit() {
		EntityTransaction tx = LOCAL_TRANSACTION.get();
		if (tx != null) {
			tx.commit();
			LOCAL_TRANSACTION.remove();

			EntityManager em = THREAD_LOCAL.get();
			em.close();
			THREAD_LOCAL.remove();
		}
	}

	@Override
	public T save(T entity) {
		// 判断id是否有值，如果没有值表示新增，否则是修改
		// 其实如果不判断，那么就直接调用merge方法
		// 如果要判断id，需要通过反射获取@Id注解的属性，然后还要判断是否有主键生成器，比较麻烦
		// 所以这里只考虑merge
		EntityManager em = this.getEntityManager();
		T t = em.merge(entity);
		return t;
	}

	protected Class<T> getEntityClass() {
		// 利用反射得到实际的实体类型
		// 这里的this，就是new IdentityDaoImpl()
		// 于是这里获取的Class对象就是IdentityDaoImpl
		Class<?> currentClass = this.getClass();

		// 获取泛型化的父类
		Type genericSuperClass = currentClass.getGenericSuperclass();
//				System.out.println(genericSuperClass);
//				System.out.println(genericSuperClass.getClass().getName());
		ParameterizedType superClass = (ParameterizedType) genericSuperClass;

		// 获取实际类型
		Type[] types = superClass.getActualTypeArguments();

		// 第一个参数就是我们需要的实体类型
		@SuppressWarnings("unchecked") // 抑制未检查类的警告
		Class<T> entityClass = (Class<T>) types[0];
		return entityClass;
	}

	@Override
	public T findById(ID id) {
		EntityManager em = this.getEntityManager();

		Class<T> entityClass = this.getEntityClass();
		T t = em.find(entityClass, id);
		return t;
	}

	@Override
	public List<T> findAll() {
		Class<T> entityClass = this.getEntityClass();

		EntityManager em = this.getEntityManager();

		// 创建条件查询
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(entityClass);
		cq.from(entityClass);

		TypedQuery<T> query = em.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		Class<T> entityClass = this.getEntityClass();

		EntityManager em = this.getEntityManager();

		// 创建条件查询
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(entityClass);
		cq.from(entityClass);

		TypedQuery<T> query = em.createQuery(cq);

		// 设置分页参数
		query.setFirstResult(pageable.getFirstResult());
		query.setMaxResults(pageable.getSize());

		List<T> list = query.getResultList();

		Page<T> page = new Page<>();
		page.setContent(list);
		page.setNumber(pageable.getNumber());
		page.setRows(list.size());
		page.setSize(pageable.getSize());

		// 查询总记录数，根据总记录数计算总页数
		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
		Root<T> root = countQuery.from(entityClass);
		// count(*)
		Expression<Long> countExp = builder.count(root);
		countQuery.select(countExp);

		TypedQuery<Long> tq = em.createQuery(countQuery);
		Long totalRows = tq.getSingleResult();// 返回一个结果

		// 修改setTotalRows方法，在方法里面直接计算总页数
		page.setTotalRows(totalRows);
//		page.setTotalPages(totalPages);

		return page;
	}

	@Override
	public void delete(T entity) {
		EntityManager em = this.getEntityManager();
		em.remove(entity);
	}

	@Override
	public void deleteById(ID id) {
		// 两种方式删除：
		// 1.根据id把对象查询出来，然后调用EntityManager的remove方法
		// 2.使用动态条件来删除

		EntityManager em = this.getEntityManager();
		Class<T> clazz = this.getEntityClass();
		CriteriaBuilder builder = em.getCriteriaBuilder();

		// 要删除哪个表
		CriteriaDelete<T> delete = builder.createCriteriaDelete(clazz);
		Root<T> root = delete.from(clazz);

		// 使用第2种方式来实现
		// 1.利用反射，得到类里面或者父类里面，所有使用@Id注解的属性，由于id的值只是一个，所以暂时不考虑复合主键的情况。
		// 可能会有多个属性的名称
		List<String> propertyNames = findIds(clazz);
		// 2.根据得到的属性，生成Predicate对象，构建where语句。
		// 从集合里面获取第一个属性创建一个条件
		if (!propertyNames.isEmpty()) {
			Predicate predicate = builder.equal(root.get(propertyNames.get(0)), id);
			for (int i = 1; i < propertyNames.size(); i++) {
				// 暂时不考虑多个主键的情况，这个for循环不会进来的！
				Predicate predicate1 = builder.equal(root.get(propertyNames.get(0)), id);
				predicate = builder.and(predicate, predicate1);
			}
			delete.where(predicate);
		}
		// 3.创建CriteriaDelete对象。
		// 在前面创建了CriteriaDelete对象

		// 4.通过EntityManager的createQuery把CriteriaDelete传进去，调用executeUpdate方法。
		Query query = em.createQuery(delete);
		query.executeUpdate();
	}

	// 由于映射存在继承关系，所以需要递归才能找到所有的@Id注解的属性
	private List<String> findIds(Class<?> clazz) {
		List<String> list = new ArrayList<>();
		// 表示获取当前类定义的所有属性，包括私有的，不包括父类的
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// 获取属性上面的@Id注解，如果成员变量上面使用了@Id注解，就返回非空，否则返回null
			Id id = field.getAnnotation(Id.class);
			if (id != null) {
				// 把属性名称存储在集合里面
				list.add(field.getName());
			}
		}
		// 递归获取父类的Id属性
		Class<?> superClass = clazz.getSuperclass();// 【递归三要素】沿着固定的方向，不断向上找父类
		// 没有父类的时候，就会返回父类为null，Object类没有父类
		if (superClass != null) {// 【递归三要素】要有结束的时候
			List<String> superClassIds = findIds(superClass);// 【递归三要素】自己调用自己
			// 找到的父类Id属性加入到当前集合里面
			list.addAll(superClassIds);
		}
		return list;
	}

	public T findOne(String ql, Class<T> clazz, Object... objects) {
		TypedQuery<T> query = this.getEntityManager().createQuery(ql, clazz);
		for (int i = 1, j = 0; j < objects.length; j++, i++) {
			query.setParameter(i, objects[j]);
		}
		return query.getSingleResult();
	}

	public List<T> findList(String ql, Class<T> clazz, Object... objects) {
		return this.find(ql, clazz, false, objects);
	}

	public List<T> findListFromCache(String ql, Class<T> clazz, Object... objects) {
		return this.find(ql, clazz, true, objects);
	}

	private List<T> find(String ql, Class<T> clazz, boolean cacheable, Object... objects) {
		TypedQuery<T> query = this.getEntityManager().createQuery(ql, clazz);
		for (int i = 1, j = 0; j < objects.length; j++, i++) {
			query.setParameter(i, objects[j]);
		}

		query.setHint("org.hibernate.cacheable", cacheable);

		return query.getResultList();
	}
}
