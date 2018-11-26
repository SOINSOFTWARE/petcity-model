// Soin Software, 2018
package com.soinsoftware.petcity.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.soinsoftware.petcity.exception.ModelValidationException;

/**
 * @author Carlos Rodriguez
 * @since 22/11/2018
 */
@MappedSuperclass
public class AbstractNameCompanyModel extends AbstractCompanyModel {

	private static final long serialVersionUID = -244268414737742537L;

	private String name;

	public AbstractNameCompanyModel() {
		super();
	}

	public AbstractNameCompanyModel(BigInteger id, Date creation, boolean enabled, String name, Company company) {
		super(id, creation, enabled, company);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void validate() {
		super.validate();
		if (name == null || name.trim().isEmpty()) {
			throw new ModelValidationException("El nombre es obligatorio");
		}
	}
}