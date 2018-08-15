package com.soinsoftware.petcity.bll;

import java.util.List;

import com.soinsoftware.petcity.dao.DataAccessibleObject;

/**
 * @author Carlos Rodriguez
 * @since 13/08/2018
 */
@SuppressWarnings(value = { "unchecked" })
public abstract class AbstractBll<T, P> {

	@SuppressWarnings("rawtypes")
	protected DataAccessibleObject dao;

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

	public void delete(final T entity) {
		dao.delete(entity);
	}

	public void closeDbConnection() {
		dao.close();
	}
}