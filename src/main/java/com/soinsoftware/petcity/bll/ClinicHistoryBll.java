// Soin Software, 2018
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.soinsoftware.petcity.dao.ClinicHistoryDao;
import com.soinsoftware.petcity.model.ClinicHistory;
import com.soinsoftware.petcity.model.Company;

/**
 * @author Carlos Rodriguez
 * @since 26/11/2018
 */
public class ClinicHistoryBll extends AbstractBll<ClinicHistory, BigInteger> {

	private static ClinicHistoryBll instance;

	private ClinicHistoryBll() throws IOException {
		super(new ClinicHistoryDao());
	}

	public List<ClinicHistory> select(Company company) {
		List<ClinicHistory> clinicHistories = ((ClinicHistoryDao) dao).select(company);
		return clinicHistories.stream()
				.sorted(Comparator.comparing(clinicHistory -> ((ClinicHistory) clinicHistory).getPet().getName()))
				.collect(Collectors.toList());
	}

	public static ClinicHistoryBll getInstance() throws IOException {
		if (instance == null) {
			instance = new ClinicHistoryBll();
		}
		return instance;
	}
}