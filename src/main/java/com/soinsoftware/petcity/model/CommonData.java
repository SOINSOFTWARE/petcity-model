/** Soin Software, 2018 */
package com.soinsoftware.petcity.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Carlos Rodriguez
 * @since 13/08/2018
 */
@MappedSuperclass
public class CommonData implements Serializable {

	private static final long serialVersionUID = -6961800919721222709L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creation;

	private boolean enabled;

	public CommonData() {
		super();
	}

	public CommonData(Builder builder) {
		super();
		this.id = builder.id;
		this.creation = builder.creation;
		this.enabled = builder.enabled;
	}

	public BigInteger getId() {
		return id;
	}

	public Date getCreation() {
		return creation;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CommonData other = (CommonData) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public class Builder {

		private BigInteger id;

		private Date creation;

		private boolean enabled;

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

		public CommonData build() {
			return new CommonData(this);
		}
	}
}