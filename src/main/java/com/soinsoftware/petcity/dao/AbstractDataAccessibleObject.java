package com.soinsoftware.petcity.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import lombok.extern.log4j.Log4j;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.soinsoftware.petcity.manager.AbstractManagerFactory;
import com.soinsoftware.petcity.manager.PetCityManagerFactory;

/**
 * 
 * @param <T>
 *            Class that represents the model.
 * @param <P>
 *            Class that represents the primary key.
 * 
 * @author Carlos Rodriguez
 * @since 13/08/2018
 */
@Log4j
@Transactional
@SuppressWarnings("unchecked")
public abstract class AbstractDataAccessibleObject<T, P> implements
		DataAccessibleObject<T, P> {

	protected final EntityManager manager;

	/**
	 * Default constructor that must be used for all DAO implementations.
	 * 
	 * @throws IOException
	 */
	public AbstractDataAccessibleObject() throws IOException {
		super();
		final AbstractManagerFactory factory = PetCityManagerFactory
				.getInstance();
		this.manager = factory.createEntityManager();
	}
	
	@Override
	public List<T> selectAll() {
		final Criteria criteria = buildCriteria();
		return criteria.list();
	}

	@Override
	public List<T> selectAll(final boolean enabled) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(enabled);
		return criteria.list();
	}

	@Override
	public void persist(T record) {
		log.info("Persisting object: " + record.toString());
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}
			manager.persist(record);
		} finally {
			manager.getTransaction().commit();
		}
	}

	@Override
	public void persist(final EntityTransaction transaction, final T record) {
		log.info("Persisting object: " + record.toString());
		if (!transaction.isActive()) {
			transaction.begin();
		}
		manager.persist(record);
	}

	@Override
	public void delete(final T record) {
		log.info("Deleting object: " + record.toString());
		manager.remove(record);
	}

	@Override
	public void rollbackTransaction(final EntityTransaction transaction) {
		if (transaction != null) {
			transaction.rollback();
		}
	}

	@Override
	public void close() {
		manager.close();
	}

	/**
	 * Builds a {@link Criteria} object with a restriction.
	 * 
	 * @param enabled
	 *            filter list of data using the enabled column.
	 * @return {@link Criteria} object.
	 */
	public Criteria buildCriteriaWithEnabledRestriction(final boolean enabled) {
		final Criteria criteria = buildCriteria();
		final Criterion criterion = Restrictions.eq("enabled", enabled);
		criteria.add(criterion);
		return criteria;
	}

	protected Criterion[] buildPredicates(final List<Criterion> predicates) {
		final Criterion[] predicateArray = new Criterion[predicates.size()];
		for (int i = 0; i < predicates.size(); i++) {
			predicateArray[i] = predicates.get(i);
		}
		return predicateArray;
	}
}
