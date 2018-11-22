// Soin Software, 2018
package com.soinsoftware.petcity.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.petcity.model.Breed;
import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.PetType;

/**
 * @author Carlos Rodriguez
 * @since 22/11/2018
 */
@SuppressWarnings("unchecked")
public class BreedDao extends AbstractDataAccessibleObject<Breed, BigInteger> {

	public BreedDao() throws IOException {
		super(Breed.class);
	}

	public List<Breed> select(Company company, PetType petType) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(true);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.or(Restrictions.eqOrIsNull("company", company), Restrictions.isNull("company")));
		predicates.add(Restrictions.eq("petType", petType));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}