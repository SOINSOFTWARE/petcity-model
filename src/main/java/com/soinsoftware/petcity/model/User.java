/** Soin Software, 2018 */
package com.soinsoftware.petcity.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 * @author Carlos Rodriguez
 * @since 14/08/2018
 */
@Entity(name = "user")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class User extends CommonData {

	private static final long serialVersionUID = -2749308307765754226L;
	
	@NaturalId
	private String document;
	private String name;
	@Column(name = "lastname")
	private String lastName;
	private String phone;
	private String email;
	private String password;
	@ManyToOne
	@JoinColumn(name = "idcompany")
	private Company company;

	public User() {
		super();
	}

	public User(Builder builder) {
		super(builder.id, builder.creation, builder.enabled);
		this.document = builder.document;
		this.name = builder.name;
		this.lastName = builder.lastName;
		this.phone = builder.phone;
		this.email = builder.email;
		this.password = builder.password;
		this.company = builder.company;
	}

	public String getDocument() {
		return document;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Company getCompany() {
		return company;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static Builder builder(User user) {
		return new Builder(user);
	}

	public static class Builder {
		
		private BigInteger id;
		private String document;
		private String name;
		private String lastName;
		private String phone;
		private String email;
		private String password;
		private Company company;
		private Date creation;
		private boolean enabled;
		
		public Builder() {}
		
		public Builder(User user) {
			id(user.getId())
				.document(user.document)
				.name(user.name)
				.lastName(user.lastName)
				.phone(user.phone)
				.email(user.email)
				.password(user.password)
				.company(user.company)
				.creation(user.getCreation())
				.enabled(user.isEnabled());
		}
		
		public Builder id(BigInteger id) {
			this.id = id;
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

		public Builder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder company(Company company) {
			this.company = company;
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
		
		public User build() {
			return new User(this);
		}
	}
}