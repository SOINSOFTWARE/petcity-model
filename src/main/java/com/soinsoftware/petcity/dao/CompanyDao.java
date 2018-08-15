package com.soinsoftware.petcity.dao;

import java.io.IOException;
import java.math.BigInteger;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.soinsoftware.petcity.model.Company;

/**
 * @author Carlos Rodriguez
 * @since 13/08/2018
 */
public class CompanyDao extends AbstractDataAccessibleObject<Company, BigInteger> {

	public CompanyDao() throws IOException {
		super();
	}
	
	@Override
	public Company selectById(final BigInteger pk) {
		return manager.find(Company.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(Company.class);
	}

	public Company select(final String document) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(Company.class).load(document);
	}
}