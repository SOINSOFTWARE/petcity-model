/** Soin Software, 2018 */
package com.soinsoftware.petcity.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.PetType;

/**
* @author Carlos Rodriguez
* @since 21/11/2018
*/
@SuppressWarnings("unchecked")
public class PetTypeDao extends AbstractDataAccessibleObject<PetType, BigInteger> {

	public PetTypeDao() throws IOException {
		super(PetType.class);
	}
	
	public List<PetType> select(Company company) {
		final Criteria criteria = buildCriteriaWithEnabledRestriction(true);
		final List<Criterion> predicates = new ArrayList<>();
		predicates.add(Restrictions.or(Restrictions.eqOrIsNull("company", company), Restrictions.isNull("company")));
		final Criterion criterion = Restrictions.and(buildPredicates(predicates));
		criteria.add(criterion);
		return criteria.list();
	}
}