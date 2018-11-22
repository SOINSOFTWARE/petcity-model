// Soin Software, 2018
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.soinsoftware.petcity.dao.BreedDao;
import com.soinsoftware.petcity.model.Breed;
import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.PetType;

/**
 * @author Carlos Rodriguez
 * @since 22/11/2018
 */
public class BreedBll extends AbstractBll<Breed, BigInteger> {

	private static BreedBll instance;

	private BreedBll() throws IOException {
		super(new BreedDao());
	}

	public List<Breed> select(Company company, PetType petType) {
		List<Breed> breeds = ((BreedDao) dao).select(company, petType);
		return breeds.stream().sorted(Comparator.comparing(Breed::getName)).collect(Collectors.toList());
	}

	public static BreedBll getInstance() throws IOException {
		if (instance == null) {
			instance = new BreedBll();
		}
		return instance;
	}
}