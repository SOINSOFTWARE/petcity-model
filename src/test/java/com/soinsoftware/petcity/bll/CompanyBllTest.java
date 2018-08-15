package com.soinsoftware.petcity.bll;

import java.util.List;

import junit.framework.TestCase;

import com.soinsoftware.petcity.manager.PetCityManagerFactory;
import com.soinsoftware.petcity.model.Company;

/**
 * 
 * @author Carlos Rodriguez
 * @since 13/08/2018
 */
public class CompanyBllTest extends TestCase {

	private CompanyBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		PetCityManagerFactory.getInstance();
		bll = new CompanyBll();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}

	public void testSelectAll() {
		final List<Company> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Company> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByNameNotExists() {
		final Company entity = bll.select("900957626-1");
		assertNull(entity);
	}

	public void testSelectByNameExists() {
		final Company entity = bll.select("900957626-2");
		assertNotNull(entity);
	}
}