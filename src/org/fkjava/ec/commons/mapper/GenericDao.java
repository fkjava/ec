package org.fkjava.ec.commons.mapper;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.fkjava.ec.commons.domain.Page;
import org.fkjava.ec.commons.domain.Pageable;

/**
 * 
 * @author lwq
 *
 * @param <T> 传入实际的类型，表示操作哪个表
 * @param <ID> 主键的数据类型，必须是实现了Serializable接口的类型才能作为主键
 */
public interface GenericDao<T, ID extends Serializable> {

	/**
	 * 修改和新增都是使用此方法
	 * 
	 * @param entity
	 * @return
	 */
	T save(T entity);

	/**
	 * 根据id查询一个对象
	 * 
	 * @param id
	 * @return
	 */
	T findById(ID id);

	/**
	 * 查询实体对应的所有记录
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 
	 * @param pageable 分页参数的封装对象
	 * @return 返回当前页的数据
	 */
	Page<T> findAll(Pageable pageable);

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	void deleteById(ID id);

	/**
	 * 删除实体对象
	 * 
	 * @param entity
	 */
	void delete(T entity);

	/**
	 * 传入查询语句查询一个对象
	 * 
	 * @param ql
	 * @param objects
	 * @return
	 */
	T findOne(String ql, Class<T> clazz, Object... objects);

	/**
	 * 查询结果
	 * 
	 * @param ql
	 * @param clazz
	 * @param objects
	 * @return
	 */
	List<T> findList(String ql, Class<T> clazz, Object... objects);

	/**
	 * 是否启用查询缓存
	 * 
	 * @param ql
	 * @param clazz
	 * @param cacheable
	 * @param objects
	 * @return
	 */
	List<T> findListFromCache(String ql, Class<T> clazz, Object... objects);

	/**
	 * 为了给子类或者业务逻辑层扩展使用
	 * 
	 * @return
	 */
	EntityManager getEntityManager();

	/**
	 * 开启事务
	 */
	void begin();

	/**
	 * 提交事务
	 */
	void commit();
}
