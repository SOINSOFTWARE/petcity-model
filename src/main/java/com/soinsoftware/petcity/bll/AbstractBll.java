package com.soinsoftware.petcity.bll;

import java.util.List;

import com.soinsoftware.petcity.dao.DataAccessibleObject;
import com.soinsoftware.petcity.model.CommonData;

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
		if (((CommonData) entity).getId() == null) {
			dao.persist(entity);
		} else {
			dao.update(entity);
		}
	}
	
	public void rollback() {
		dao.rollbackTransaction();
	}

	public void closeDbConnection() {
		dao.close();
	}
}