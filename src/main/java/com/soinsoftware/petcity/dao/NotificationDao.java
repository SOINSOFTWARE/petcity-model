// Soin Software, 2018
package com.soinsoftware.petcity.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.Notification;
import com.soinsoftware.petcity.model.Pet;

/**
 * @author Carlos Rodriguez
 * @since 10/12/2018
 */
@SuppressWarnings("unchecked")
public class NotificationDao extends AbstractDataAccessibleObject<Notification, BigInteger> {

	public NotificationDao() throws IOException {
		super(Notification.class);
	}

	public List<Notification> select(Pet pet) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(true);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("pet", pet));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Notification> select(Company company) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(true);
		criteria.createAlias("pet", "p");
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("p.company", company));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}

	public List<Notification> select(Company company, Date initialDate, Date finalDate) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(true);
		criteria.createAlias("pet", "p");
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("p.company", company));
		predicates.add(Restrictions.between("notificationDate", initialDate, finalDate));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}