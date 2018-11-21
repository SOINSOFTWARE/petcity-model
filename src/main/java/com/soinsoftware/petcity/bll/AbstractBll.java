package com.soinsoftware.petcity.bll;

import java.util.List;

import com.soinsoftware.petcity.dao.DataAccessibleObject;

/**
 * @author Carlos Rodriguez
 * @since 13/08/2018
 */
public abstract class AbstractBll<T, P> {
	
	protected DataAccessibleObject<T, P> dao;
	
	protected AbstractBll(DataAccessibleObject<T, P> dao) {
		super();
		this.dao = dao;
	}

	public List<T> selectAll() {
		return dao.selectAll();
	}

	public List<T> selectAll(boolean enabled) {
		return dao.selectAll(enabled);
	}

	public T selectById(P pk) {
		return (T) dao.selectById(pk);
	}

	public void save(final T entity) {
		dao.persist(entity);
	}
	
	public void update(final T entity) {
		dao.update(entity);
	}

	public void delete(final T entity) {
		dao.delete(entity);
	}

	public void closeDbConnection() {
		dao.close();
	}
}