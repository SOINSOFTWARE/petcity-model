// Soin Software, 2018
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.soinsoftware.petcity.manager.PetCityManagerFactory;
import com.soinsoftware.petcity.model.Breed;
import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.PetType;

import junit.framework.TestCase;

/**
 * @author Carlos Rodriguez
 * @since 22/11/2018
 */
public class BreedBllTest extends TestCase {

	private BreedBll bll;
	
	protected void setUp() throws Exception {
		super.setUp();
		PetCityManagerFactory.getInstance();
		bll = BreedBll.getInstance();
	}

	public void testSelectAll() {
		final List<Breed> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Breed> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCompanyAndPetType() throws IOException {
		final Company company = CompanyBll.getInstance().select("900957626-2");
		final PetType petType = PetTypeBll.getInstance().selectById(BigInteger.valueOf(1));
		final List<Breed> entities = bll.select(company, petType);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}
	
	public void testSelectByCompanyAndPetTypeAsNull() throws IOException {
		final Company company = CompanyBll.getInstance().select("900957626-2");
		final List<Breed> entities = bll.select(company, null);
		assertNotNull(entities);
		assertSame(entities.size(), 0);
	}
}
