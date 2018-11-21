/** Soin Software, 2018 */
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.soinsoftware.petcity.dao.PetTypeDao;
import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.PetType;

/**
* @author Carlos Rodriguez
* @since 21/11/2018
*/
public class PetTypeBll extends AbstractBll<PetType, BigInteger> {
	
	private static PetTypeBll instance;
	
	private PetTypeBll() throws IOException {
		super(new PetTypeDao());
	}
	
	public List<PetType> select(Company company) {
		List<PetType> petTypes = ((PetTypeDao) dao).select(company);
		return petTypes.stream()
			.sorted(Comparator.comparing(PetType::getName))
			.collect(Collectors.toList());
	}
	
	public static PetTypeBll getInstance() throws IOException {
		if (instance == null) {
			instance = new PetTypeBll();
		}
		return instance;
	}
}