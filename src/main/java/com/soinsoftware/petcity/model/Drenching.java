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
 * @since 22/11/2018
 */
@Entity(name = "drenching")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Drenching extends AbstractNameCompanyModel {

	private static final long serialVersionUID = -797281243971259428L;

	public Drenching() {
		super();
	}

	public Drenching(Builder builder) {
		super(builder.id, builder.creation, builder.enabled, builder.name, builder.company);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Drenching drenching) {
		return new Builder(drenching);
	}

	public static class Builder {

		private BigInteger id;
		private Date creation;
		private boolean enabled;
		private String name;
		private Company company;

		private Builder() {
		}

		private Builder(Drenching drenching) {
			id(drenching.getId()).creation(drenching.getCreation()).enabled(drenching.isEnabled())
					.name(drenching.getName()).company(drenching.getCompany());
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

		public Drenching build() {
			return new Drenching(this);
		}
	}
}