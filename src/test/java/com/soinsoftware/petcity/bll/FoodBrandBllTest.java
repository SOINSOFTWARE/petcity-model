// Soin Software, 2018
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.petcity.manager.PetCityManagerFactory;
import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.FoodBrand;

import junit.framework.TestCase;

/**
 * @author Carlos Rodriguez
 * @since 22/11/2018
 */
public class FoodBrandBllTest extends TestCase {

	private FoodBrandBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		PetCityManagerFactory.getInstance();
		bll = FoodBrandBll.getInstance();
	}

	public void testSelectAll() {
		final List<FoodBrand> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<FoodBrand> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCompany() throws IOException {
		final Company company = CompanyBll.getInstance().select("900957626-2");
		final List<FoodBrand> entities = bll.select(company);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}
}