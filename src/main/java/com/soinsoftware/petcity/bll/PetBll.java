// Soin Software, 2018
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.soinsoftware.petcity.dao.PetDao;
import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.Pet;

/**
 * @author Carlos Rodriguez
 * @since 10/12/2018
 */
public class PetBll extends AbstractBll<Pet, BigInteger> {

	private static PetBll instance;

	private PetBll() throws IOException {
		super(new PetDao());
	}

	public List<Pet> select(Company company) {
		List<Pet> pets = ((PetDao) dao).select(company);
		return pets.stream().sorted(Comparator.comparing(Pet::getName)).collect(Collectors.toList());
	}

	public static PetBll getInstance() throws IOException {
		if (instance == null) {
			instance = new PetBll();
		}
		return instance;
	}
}