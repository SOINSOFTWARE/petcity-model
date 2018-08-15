/** Soin Software, 2018 */
package com.soinsoftware.petcity.bll;

import java.util.List;

import junit.framework.TestCase;

import com.soinsoftware.petcity.manager.PetCityManagerFactory;
import com.soinsoftware.petcity.model.User;

/**
 * @author Carlos Rodriguez
 * @since 14/08/2018
 */
public class UserBllTest extends TestCase {
	
	private UserBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		PetCityManagerFactory.getInstance();
		bll = new UserBll();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		bll.closeDbConnection();
	}
	
	public void testSelectAll() {
		final List<User> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<User> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByDocumentNotExists() {
		final User entity = bll.select("19604741");
		assertNull(entity);
	}

	public void testSelectByDocumentExists() {
		final User entity = bll.select("19604742");
		assertNotNull(entity);
	}
	
	public void testSelectByLoginNotExists() {
		final User entity = bll.select("carlos.rodriguez@soinsoftware.com", "Soin020486");
		assertNull(entity);
	}

	public void testSelectByLoginExists() {
		final User entity = bll.select("carlos.rodriguez@soinsoftware.com", "Soin#020486");
		assertNotNull(entity);
	}
}