// Soin Software, 2018
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.petcity.manager.PetCityManagerFactory;
import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.Drenching;

import junit.framework.TestCase;

/**
 * @author Carlos Rodriguez
 * @since 22/11/2018
 */
public class DrenchingBllTest extends TestCase {

	private DrenchingBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		PetCityManagerFactory.getInstance();
		bll = DrenchingBll.getInstance();
	}

	public void testSelectAll() {
		final List<Drenching> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Drenching> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCompany() throws IOException {
		final Company company = CompanyBll.getInstance().select("900957626-2");
		final List<Drenching> entities = bll.select(company);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}
}