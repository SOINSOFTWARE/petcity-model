// Soin Software, 2018
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.joda.time.DateTime;

import com.soinsoftware.petcity.manager.PetCityManagerFactory;
import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.Notification;
import com.soinsoftware.petcity.model.Pet;

import junit.framework.TestCase;

/**
 * @author Carlos Rodriguez
 * @since 10/12/2018
 */
public class NotificationBllTest extends TestCase {

	private NotificationBll bll;

	protected void setUp() throws Exception {
		super.setUp();
		PetCityManagerFactory.getInstance();
		bll = NotificationBll.getInstance();
	}

	public void testSelectAll() {
		final List<Notification> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Notification> entities = bll.selectAll(true);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByPet() throws IOException {
		final Pet pet = PetBll.getInstance().selectById(new BigInteger("1"));
		final List<Notification> entities = bll.select(pet);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCompany() throws IOException {
		final Company company = CompanyBll.getInstance().select("900957626-2");
		final List<Notification> entities = bll.select(company);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCompanyAndDate() throws IOException {
		final Company company = CompanyBll.getInstance().select("900957626-2");
		final DateTime dateTime = new DateTime(2016, 11, 11, 14, 50, 50);
		final List<Notification> entities = bll.select(company, dateTime.toDate());
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}
}