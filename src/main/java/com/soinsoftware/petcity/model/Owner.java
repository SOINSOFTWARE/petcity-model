// Soin Software, 2018
package com.soinsoftware.petcity.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.soinsoftware.petcity.exception.ModelValidationException;

/**
 * @author Carlos Rodriguez
 * @since 23/11/2018
 */
@Entity(name = "owner")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Owner extends AbstractNameCompanyModel {

	private static final long serialVersionUID = 1460191234895349762L;

	@NaturalId
	private String document;
	@Column(name = "lastname")
	private String lastName;
	private String email;
	private String address;
	private String phone;
	private String phone2;

	public Owner() {
		super();
	}

	public Owner(Builder builder) {
		super(builder.id, builder.creation, builder.enabled, builder.name, builder.company);
		document = builder.document;
		lastName = builder.lastName;
		email = builder.email;
		address = builder.address;
		phone = builder.phone;
		phone2 = builder.phone2;
	}

	public String getDocument() {
		return document;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getPhone2() {
		return phone2;
	}
	
	@Override
	public void validate() {
		super.validate();
		if (document == null || document.trim().isEmpty()) {
			throw new ModelValidationException("El número de documento es obligatorio");
		}
		if (lastName == null || lastName.trim().isEmpty()) {
			throw new ModelValidationException("El apellido es obligatorio");
		}
		if (phone == null || phone.trim().isEmpty()) {
			throw new ModelValidationException("El número de celular es obligatorio");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Owner owner) {
		return new Builder(owner);
	}

	public static class Builder {

		private BigInteger id;
		private Date creation;
		private boolean enabled;
		private String document;
		private String name;
		private String lastName;
		private String email;
		private String address;
		private String phone;
		private String phone2;
		private Company company;

		private Builder() {
		}

		private Builder(Owner owner) {
			id(owner.getId()).creation(owner.getCreation()).enabled(owner.isEnabled()).document(owner.getDocument())
					.name(owner.getName()).lastName(owner.getLastName()).email(owner.getEmail())
					.address(owner.getAddress()).phone(owner.getPhone()).phone2(owner.getPhone2())
					.company(owner.getCompany());
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

		public Builder document(String document) {
			this.document = document;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public Builder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder phone2(String phone2) {
			this.phone2 = phone2;
			return this;
		}

		public Builder company(Company company) {
			this.company = company;
			return this;
		}

		public Owner build() {
			return new Owner(this);
		}
	}
}