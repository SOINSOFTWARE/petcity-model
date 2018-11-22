// Soin Software, 2018
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.soinsoftware.petcity.dao.FoodBrandDao;
import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.FoodBrand;

/**
 * @author Carlos Rodriguez
 * @since 22/11/2018
 */
public class FoodBrandBll extends AbstractBll<FoodBrand, BigInteger> {

	private static FoodBrandBll instance;

	private FoodBrandBll() throws IOException {
		super(new FoodBrandDao());
	}

	public List<FoodBrand> select(Company company) {
		List<FoodBrand> foodBrands = ((FoodBrandDao) dao).select(company);
		return foodBrands.stream().sorted(Comparator.comparing(FoodBrand::getName)).collect(Collectors.toList());
	}

	public static FoodBrandBll getInstance() throws IOException {
		if (instance == null) {
			instance = new FoodBrandBll();
		}
		return instance;
	}
}