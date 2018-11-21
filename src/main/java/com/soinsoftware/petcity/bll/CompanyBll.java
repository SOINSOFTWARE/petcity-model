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
	
	private static CompanyBll instance;

	private CompanyBll() throws IOException {
		super(new CompanyDao());
	}

	public Company select(final String document) {
		return ((CompanyDao) dao).select(document);
	}
	
	public static CompanyBll getInstance() throws IOException {
		if (instance == null) {
			instance = new CompanyBll();
		}
		return instance;
	}
}