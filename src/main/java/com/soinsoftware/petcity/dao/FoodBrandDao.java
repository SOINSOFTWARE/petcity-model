// Soin Software, 2018
package com.soinsoftware.petcity.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.FoodBrand;

/**
 * @author Carlos Rodriguez
 * @since 22/11/2018
 */
@SuppressWarnings("unchecked")
public class FoodBrandDao extends AbstractDataAccessibleObject<FoodBrand, BigInteger> {

	public FoodBrandDao() throws IOException {
		super(FoodBrand.class);
	}

	public List<FoodBrand> select(Company company) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(true);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.eq("company", company));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}