// Soin Software, 2018
package com.soinsoftware.petcity.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.petcity.model.ClinicHistory;
import com.soinsoftware.petcity.model.Company;

/**
 * @author Carlos Rodriguez
 * @since 26/11/2018
 */
@SuppressWarnings("unchecked")
public class ClinicHistoryDao extends AbstractDataAccessibleObject<ClinicHistory, BigInteger> {

	public ClinicHistoryDao() throws IOException {
		super(ClinicHistory.class);
	}

	public List<ClinicHistory> select(Company company) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(true);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("company", company));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}