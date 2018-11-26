// Soin Software, 2018
package com.soinsoftware.petcity.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.soinsoftware.petcity.exception.ModelValidationException;

/**
 * @author Carlos Rodriguez
 * @since 23/11/2018
 */
@Entity(name = "clinichistory")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class ClinicHistory extends AbstractCompanyModel {

	private static final long serialVersionUID = 3506141285915854448L;

	@ManyToOne
	@JoinColumn(name = "idpet")
	private Pet pet;
	@Column(name = "recordcustomid")
	private BigInteger recordCustomId;

	public ClinicHistory() {
		super();
	}

	public ClinicHistory(Builder builder) {
		super(builder.id, builder.creation, builder.enabled, builder.company);
		pet = builder.pet;
		recordCustomId = builder.recordCustomId;
	}

	public Pet getPet() {
		return pet;
	}

	public BigInteger getRecordCustomId() {
		return recordCustomId;
	}

	@Override
	public void validate() {
		super.validate();
		if (pet == null) {
			throw new ModelValidationException("La mascota es obligatoria");
		} else {
			pet.validate();
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(ClinicHistory clinicHistory) {
		return new Builder(clinicHistory);
	}

	public static class Builder {

		private BigInteger id;
		private Date creation;
		private boolean enabled;
		private Pet pet;
		private Company company;
		private BigInteger recordCustomId;

		private Builder() {
		}

		private Builder(ClinicHistory clinicHistory) {
			id(clinicHistory.getId()).creation(clinicHistory.getCreation()).enabled(clinicHistory.isEnabled())
					.pet(clinicHistory.pet).company(clinicHistory.getCompany());
		}

		public Builder id(BigInteger id) {
			this.id = id;
			return this;
		}

		public Builder creation(Date creation) {
			this.creation = creation;
			return this;
		}

		public Builder enabled(boolean enabled) {
			this.enabled = enabled;
			return this;
		}

		private Builder pet(Pet pet) {
			this.pet = pet;
			return this;
		}

		public Builder company(Company company) {
			this.company = company;
			return this;
		}

		public Builder recordCustomId(BigInteger recordCustomId) {
			this.recordCustomId = recordCustomId;
			return this;
		}

		public ClinicHistory build() {
			return new ClinicHistory(this);
		}
	}
}