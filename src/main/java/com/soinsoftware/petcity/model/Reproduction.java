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
@Entity(name = "reproduction")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Reproduction extends AbstractNameCompanyModel {

	private static final long serialVersionUID = -797281243971259428L;

	public Reproduction() {
		super();
	}

	public Reproduction(Builder builder) {
		super(builder.id, builder.creation, builder.enabled, builder.name, builder.company);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Reproduction reproduction) {
		return new Builder(reproduction);
	}

	public static class Builder {

		private BigInteger id;
		private Date creation;
		private boolean enabled;
		private String name;
		private Company company;

		private Builder() {
		}

		private Builder(Reproduction reproduction) {
			id(reproduction.getId()).creation(reproduction.getCreation()).enabled(reproduction.isEnabled())
					.name(reproduction.getName()).company(reproduction.getCompany());
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

		public Reproduction build() {
			return new Reproduction(this);
		}
	}
}