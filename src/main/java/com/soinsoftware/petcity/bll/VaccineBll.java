// Soin Software, 2018
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.soinsoftware.petcity.dao.VaccineDao;
import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.Vaccine;

/**
 * @author Carlos Rodriguez
 * @since 22/11/2018
 */
public class VaccineBll extends AbstractBll<Vaccine, BigInteger> {

	private static VaccineBll instance;

	private VaccineBll() throws IOException {
		super(new VaccineDao());
	}

	public List<Vaccine> select(Company company) {
		List<Vaccine> vaccines = ((VaccineDao) dao).select(company);
		return vaccines.stream().sorted(Comparator.comparing(Vaccine::getName)).collect(Collectors.toList());
	}

	public static VaccineBll getInstance() throws IOException {
		if (instance == null) {
			instance = new VaccineBll();
		}
		return instance;
	}
}