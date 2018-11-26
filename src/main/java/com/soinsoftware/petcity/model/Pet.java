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
 * @since 23/11/2018
 */
@Entity(name = "pet")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Pet extends AbstractNameCompanyModel {

	private static final long serialVersionUID = 5521414835456983910L;

	private String color;
	private String sex;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "borndate")
	private Date bornDate;
	@Column(name = "bornplace")
	private String bornPlace;
	private String photo;
	private String history;
	@ManyToOne
	@JoinColumn(name = "idreproduction")
	private Reproduction reproduction;
	@ManyToOne
	@JoinColumn(name = "idpettype")
	private PetType petType;
	@ManyToOne
	@JoinColumn(name = "idbreed")
	private Breed breed;
	@ManyToOne
	@JoinColumn(name = "idowner")
	private Owner owner;
	private byte[] photoBlob;

	public Pet() {
		super();
	}

	public Pet(Builder builder) {
		super(builder.id, builder.creation, builder.enabled, builder.name, builder.company);
		color = builder.color;
		sex = builder.sex;
		bornDate = builder.bornDate;
		bornPlace = builder.bornPlace;
		photo = builder.photo;
		history = builder.history;
		reproduction = builder.reproduction;
		petType = builder.petType;
		breed = builder.breed;
		owner = builder.owner;
		photoBlob = builder.photoBlob;
	}

	public String getColor() {
		return color;
	}

	public String getSex() {
		return sex;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public String getBornPlace() {
		return bornPlace;
	}

	public String getPhoto() {
		return photo;
	}

	public String getHistory() {
		return history;
	}

	public Reproduction getReproduction() {
		return reproduction;
	}

	public PetType getPetType() {
		return petType;
	}

	public Breed getBreed() {
		return breed;
	}

	public Owner getOwner() {
		return owner;
	}

	public byte[] getPhotoBlob() {
		return photoBlob;
	}
	
	@Override
	public void validate() {
		super.validate();
		if (color == null || color.trim().isEmpty()) {
			throw new ModelValidationException("El color de la mascota es obligatorio");
		}
		if (sex == null || sex.trim().isEmpty()) {
			throw new ModelValidationException("El sexo de la mascota es obligatorio");
		}
		if (bornPlace == null || bornPlace.trim().isEmpty()) {
			throw new ModelValidationException("El lugar de procedencia de la mascota es obligatorio");
		}
		if (reproduction == null) {
			throw new ModelValidationException("El estado reproductivo de la mascota es obligatorio");
		} else {
			reproduction.validate();
		}
		if (petType == null) {
			throw new ModelValidationException("La especie es obligatoria");
		} else {
			petType.validate();
		}
		if (breed == null) {
			throw new ModelValidationException("La raza es obligatoria");
		} else {
			breed.validate();
		}
		if (owner == null) {
			throw new ModelValidationException("El propietario es obligatorio");
		} else {
			owner.validate();
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Pet pet) {
		return new Builder(pet);
	}

	public static class Builder {

		private BigInteger id;
		private Date creation;
		private boolean enabled;
		private String name;
		private String color;
		private String sex;
		private Date bornDate;
		private String bornPlace;
		private String photo;
		private String history;
		private Reproduction reproduction;
		private PetType petType;
		private Breed breed;
		private Owner owner;
		private byte[] photoBlob;
		private Company company;

		private Builder() {
		}

		private Builder(Pet pet) {
			id(pet.getId()).creation(pet.getCreation()).enabled(pet.isEnabled()).name(pet.getName())
					.color(pet.getColor()).sex(pet.getSex()).bornDate(pet.getBornDate()).bornPlace(pet.getBornPlace())
					.photo(pet.getPhoto()).history(pet.getHistory()).reproduction(pet.getReproduction())
					.petType(pet.getPetType()).breed(pet.getBreed()).owner(pet.getOwner()).photoBlob(pet.getPhotoBlob())
					.company(pet.getCompany());
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

		public Builder color(String color) {
			this.color = color;
			return this;
		}

		public Builder sex(String sex) {
			this.sex = sex;
			return this;
		}

		public Builder bornDate(Date bornDate) {
			this.bornDate = bornDate;
			return this;
		}

		public Builder bornPlace(String bornPlace) {
			this.bornPlace = bornPlace;
			return this;
		}

		public Builder photo(String photo) {
			this.photo = photo;
			return this;
		}

		public Builder history(String history) {
			this.history = history;
			return this;
		}

		public Builder reproduction(Reproduction reproduction) {
			this.reproduction = reproduction;
			return this;
		}

		public Builder petType(PetType petType) {
			this.petType = petType;
			return this;
		}

		public Builder breed(Breed breed) {
			this.breed = breed;
			return this;
		}

		public Builder owner(Owner owner) {
			this.owner = owner;
			return this;
		}

		public Builder company(Company company) {
			this.company = company;
			return this;
		}

		public Builder photoBlob(byte[] photoBlob) {
			this.photoBlob = photoBlob;
			return this;
		}

		public Pet build() {
			return new Pet(this);
		}
	}
}