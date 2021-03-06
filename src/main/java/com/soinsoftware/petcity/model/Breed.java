// Soin Software, 2018
package com.soinsoftware.petcity.model;

import java.math.BigInteger;
import java.util.Date;

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
 * @since 22/11/2018
 */
@Entity(name = "breed")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Breed extends AbstractNameCompanyModel {

	private static final long serialVersionUID = 3187356044360567830L;

	@ManyToOne
	@JoinColumn(name = "idpettype")
	private PetType petType;

	public Breed() {
		super();
	}

	public Breed(Builder builder) {
		super(builder.id, builder.creation, builder.enabled, builder.name, builder.company);
		this.petType = builder.petType;
	}

	public PetType getPetType() {
		return petType;
	}

	@Override
	public void validate() {
		super.validate();
		if (petType == null) {
			throw new ModelValidationException("La especie es obligatoria");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Breed breed) {
		return new Builder(breed);
	}

	public static class Builder {

		private BigInteger id;
		private Date creation;
		private boolean enabled;
		private String name;
		private PetType petType;
		private Company company;

		private Builder() {
		}

		private Builder(Breed breed) {
			id(breed.getId()).creation(breed.getCreation()).enabled(breed.isEnabled()).name(breed.getName())
					.petType(breed.getPetType()).company(breed.getCompany());
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

		public Builder petType(PetType petType) {
			this.petType = petType;
			return this;
		}

		public Builder company(Company company) {
			this.company = company;
			return this;
		}

		public Breed build() {
			return new Breed(this);
		}
	}
}