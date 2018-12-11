// Soin Software, 2018
package com.soinsoftware.petcity.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.soinsoftware.petcity.exception.ModelValidationException;

/**
 * @author Carlos Rodriguez
 * @since 07/12/2018
 */
@Entity(name = "notification")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Notification extends CommonData {

	private static final long serialVersionUID = 3506141285915854448L;

	@Column(name = "title")
	private String notificationTitle;
	private String message;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "notificationdate")
	private Date notificationDate;
	@ManyToOne
	@JoinColumn(name = "idpet")
	private Pet pet;

	public Notification() {
		super();
	}

	public Notification(Builder builder) {
		super(builder.id, builder.creation, builder.enabled);
		notificationTitle = builder.notificationTitle;
		message = builder.message;
		notificationDate = builder.notificationDate;
		pet = builder.pet;
	}

	@Override
	public void validate() {
		if (notificationTitle == null || notificationTitle.trim().isEmpty()) {
			throw new ModelValidationException("El título es obligatorio");
		}
		if (message == null || message.trim().isEmpty()) {
			throw new ModelValidationException("El mensaje es obligatorio");
		}
		if (notificationDate == null) {
			throw new ModelValidationException("La fecha es obligatoria");
		}
		if (pet == null) {
			throw new ModelValidationException("La mascota es obligatoria");
		} else {
			pet.validate();
		}
	}

	public String getNotificationTitle() {
		return notificationTitle;
	}

	public String getMessage() {
		return message;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public Pet getPet() {
		return pet;
	}

	@Override
	public String getTitle() {
		return "Notificación para: " + pet.getName();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Notification notification) {
		return new Builder(notification);
	}

	public static class Builder {

		public String getNotificationTitle() {
			return notificationTitle;
		}

		private BigInteger id;
		private Date creation;
		private boolean enabled;
		private String notificationTitle;
		private String message;
		private Date notificationDate;
		private Pet pet;

		private Builder() {
		}

		private Builder(Notification notification) {
			id(notification.getId()).creation(notification.getCreation()).enabled(notification.isEnabled())
					.title(notification.notificationTitle).message(notification.message)
					.notificationDate(notification.notificationDate).pet(notification.pet);
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

		public Builder title(String notificationTitle) {
			this.notificationTitle = notificationTitle;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder notificationDate(Date notificationDate) {
			this.notificationDate = notificationDate;
			return this;
		}

		private Builder pet(Pet pet) {
			this.pet = pet;
			return this;
		}

		public Notification build() {
			return new Notification(this);
		}
	}
}