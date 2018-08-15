package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.petcity.dao.CompanyDao;
import com.soinsoftware.petcity.model.Company;

/**
 * @author Carlos Rodriguez
 * @since 13/08/2018
 */
public class CompanyBll extends AbstractBll<Company, BigInteger> {

	public CompanyBll() throws IOException {
		super();
		dao = new CompanyDao();
	}

	public Company select(final String document) {
		return ((CompanyDao) dao).select(document);
	}
}