// Soin Software, 2018
package com.soinsoftware.petcity.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.soinsoftware.petcity.exception.ModelValidationException;

/**
 * @author Carlos Rodriguez
 * @since 22/11/2018
 */
@MappedSuperclass
public class AbstractNameCompanyModel extends CommonData {

	private static final long serialVersionUID = -244268414737742537L;
	
	private String name;
	@ManyToOne
	@JoinColumn(name = "idcompany")
	private Company company;

	public AbstractNameCompanyModel() {
		super();
	}

	public AbstractNameCompanyModel(BigInteger id, Date creation, boolean enabled, String name, Company company) {
		super(id, creation, enabled);
		this.name = name;
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public Company getCompany() {
		return company;
	}

	@Override
	public void validate() {
		if (name == null || name.trim().isEmpty()) {
			throw new ModelValidationException("El nombre es obligatorio");
		}
		if (company == null) {
			throw new ModelValidationException("La veterinaria es obligatoria");
		}
	}
}
