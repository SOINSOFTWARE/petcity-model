// Soin Software, 2018
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.soinsoftware.petcity.dao.DrenchingDao;
import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.Drenching;

/**
 * @author Carlos Rodriguez
 * @since 22/11/2018
 */
public class DrenchingBll extends AbstractBll<Drenching, BigInteger> {

	private static DrenchingBll instance;

	private DrenchingBll() throws IOException {
		super(new DrenchingDao());
	}

	public List<Drenching> select(Company company) {
		List<Drenching> vaccines = ((DrenchingDao) dao).select(company);
		return vaccines.stream().sorted(Comparator.comparing(Drenching::getName)).collect(Collectors.toList());
	}

	public static DrenchingBll getInstance() throws IOException {
		if (instance == null) {
			instance = new DrenchingBll();
		}
		return instance;
	}
}