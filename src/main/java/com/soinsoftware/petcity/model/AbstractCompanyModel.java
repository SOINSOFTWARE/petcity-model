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
 * @since 26/11/2018
 */
@MappedSuperclass
public class AbstractCompanyModel extends CommonData {

	private static final long serialVersionUID = -244268414737742537L;

	@ManyToOne
	@JoinColumn(name = "idcompany")
	private Company company;

	public AbstractCompanyModel() {
		super();
	}

	public AbstractCompanyModel(BigInteger id, Date creation, boolean enabled, Company company) {
		super(id, creation, enabled);
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

	@Override
	public void validate() {
		if (company == null) {
			throw new ModelValidationException("La veterinaria es obligatoria");
		} else {
			company.validate();
		}
	}
}