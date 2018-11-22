// Soin Software, 2018
package com.soinsoftware.petcity.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 * @author Carlos Rodriguez
 * @since 21/11/2018
 */
@Entity(name = "pettype")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class PetType extends AbstractNameCompanyModel {

	private static final long serialVersionUID = -4499960650036322775L;

	public PetType() {
		super();
	}

	public PetType(Builder builder) {
		super(builder.id, builder.creation, builder.enabled, builder.name, builder.company);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(PetType petType) {
		return new Builder(petType);
	}

	public static class Builder {

		private BigInteger id;
		private Date creation;
		private boolean enabled;
		private String name;
		private Company company;

		private Builder() {
		}

		private Builder(PetType petType) {
			id(petType.getId()).creation(petType.getCreation()).enabled(petType.isEnabled()).name(petType.getName())
					.company(petType.getCompany());
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

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder company(Company company) {
			this.company = company;
			return this;
		}

		public PetType build() {
			return new PetType(this);
		}
	}
}