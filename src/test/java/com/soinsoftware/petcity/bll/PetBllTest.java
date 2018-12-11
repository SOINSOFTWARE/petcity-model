// Soin Software, 2018
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.soinsoftware.petcity.manager.PetCityManagerFactory;
import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.Pet;

import junit.framework.TestCase;

/**
 * @author Carlos Rodriguez
 * @since 10/12/2018
 */
public class PetBllTest extends TestCase {

	private PetBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		PetCityManagerFactory.getInstance();
		bll = PetBll.getInstance();
	}

	public void testSelectAll() {
		final List<Pet> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Pet> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectById() throws IOException {
		final Pet entity = bll.selectById(new BigInteger("1"));
		assertNotNull(entity);
	}

	public void testSelectByCompany() throws IOException {
		final Company company = CompanyBll.getInstance().select("900957626-2");
		final List<Pet> entities = bll.select(company);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}
}