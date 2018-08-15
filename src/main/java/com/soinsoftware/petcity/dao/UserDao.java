/** Soin Software, 2018 */
package com.soinsoftware.petcity.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.petcity.model.User;

/**
 * @author Carlos Rodriguez
 * @since 14/08/2018
 */
public class UserDao extends AbstractDataAccessibleObject<User, BigInteger> {

	public UserDao() throws IOException {
		super();
	}

	@Override
	public User selectById(BigInteger pk) {
		return manager.find(User.class, pk);
	}

	@Override
	public Criteria buildCriteria() {
		final Session session = (Session) manager.getDelegate();
		return session.createCriteria(User.class);
	}
	
	public User select(final String document) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(User.class).load(document);
	}
	
	public User select(final String email, final String password) {
		final Criteria criteria = buildCriteria();
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("email", email));
		predicates.add(Restrictions.eq("password", password));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return (User) criteria.uniqueResult();
	}
}