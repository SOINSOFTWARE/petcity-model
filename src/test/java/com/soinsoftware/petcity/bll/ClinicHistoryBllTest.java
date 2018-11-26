// Soin Software, 2018
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.petcity.manager.PetCityManagerFactory;
import com.soinsoftware.petcity.model.ClinicHistory;
import com.soinsoftware.petcity.model.Company;

import junit.framework.TestCase;

/**
 * @author Carlos Rodriguez
 * @since 26/11/2018
 */
public class ClinicHistoryBllTest extends TestCase {

	private ClinicHistoryBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		PetCityManagerFactory.getInstance();
		bll = ClinicHistoryBll.getInstance();
	}

	public void testSelectAll() {
		final List<ClinicHistory> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<ClinicHistory> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCompany() throws IOException {
		final Company company = CompanyBll.getInstance().select("900957626-2");
		final List<ClinicHistory> entities = bll.select(company);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}
}