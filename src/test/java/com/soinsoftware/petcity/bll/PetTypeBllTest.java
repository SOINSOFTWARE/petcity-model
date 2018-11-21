/** Soin Software, 2018 */
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.petcity.manager.PetCityManagerFactory;
import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.PetType;

import junit.framework.TestCase;

/**
* @author Carlos Rodriguez
* @since 21/11/2018
*/
public class PetTypeBllTest extends TestCase {
	
	private PetTypeBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		PetCityManagerFactory.getInstance();
		bll = PetTypeBll.getInstance();
	}
	
	public void testSelectAll() {
		final List<PetType> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<PetType> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}
	
	public void testSelectByCompany() throws IOException {
		final Company company = CompanyBll.getInstance().select("900957626-2");
		final List<PetType> entities = bll.select(company);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}
}