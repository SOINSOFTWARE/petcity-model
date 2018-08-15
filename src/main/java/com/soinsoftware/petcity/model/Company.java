/** Soin Software, 2018 */
package com.soinsoftware.petcity.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 * @author Carlos Rodriguez
 * @since 13/08/2018
 */
@Entity(name = "company")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Company extends CommonData {

	private static final long serialVersionUID = -3794288197177180090L;
	
	@NaturalId
	private String document;
	
	private String name;
	
	private boolean paid;
	
	private String photo;
	
	@Column(name = "initialcustomid")
	private BigInteger initialCustomId;
	
	@Column(name = "actualcustomid")
	private BigInteger actualCustomId;
	
	public Company() {
		super();
	}
	
	public Company(Builder builder) {
		super(builder);
		this.document = builder.document;
		this.name = builder.name;
		this.paid = builder.paid;
		this.photo = builder.photo;
		this.initialCustomId = builder.initialCustomId;
		this.actualCustomId = builder.actualCustomId;
	}
	
	public String getDocument() {
		return document;
	}

	public String getName() {
		return name;
	}

	public boolean isPaid() {
		return paid;
	}

	public String getPhoto() {
		return photo;
	}

	public BigInteger getInitialCustomId() {
		return initialCustomId;
	}

	public BigInteger getActualCustomId() {
		return actualCustomId;
	}

	public class Builder extends CommonData.Builder {
		
		private String document;
		
		private String name;
		
		private boolean paid;
		
		private String photo;
		
		private BigInteger initialCustomId;
		
		private BigInteger actualCustomId;

		public Builder document(String document) {
			this.document = document;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder paid(boolean paid) {
			this.paid = paid;
			return this;
		}

		public Builder photo(String photo) {
			this.photo = photo;
			return this;
		}

		public Builder initialCustomId(BigInteger initialCustomId) {
			this.initialCustomId = initialCustomId;
			return this;
		}

		public Builder actualCustomId(BigInteger actualCustomId) {
			this.actualCustomId = actualCustomId;
			return this;
		}
		
		public Company build() {
			return new Company(this);
		}
	}
}