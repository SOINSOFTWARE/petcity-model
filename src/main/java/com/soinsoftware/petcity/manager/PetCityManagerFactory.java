package com.soinsoftware.petcity.manager;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;

/**
 * This class provides the {@link EntityManagerFactory} to connect to database.
 * 
 * @author Carlos Rodriguez
 * @since 13/08/2018
 */
public class PetCityManagerFactory extends AbstractManagerFactory {

	private static PetCityManagerFactory instance;

	private static final String PACKAGE_INFO = "com.soinsoftware.petcity.model";

	private static final String PERSISTENCE_UNIT_NAME = "PetCity";

	private static final String PROPERTY_FILE = "/connection.properties";

	private PetCityManagerFactory() throws IOException {
		super(PACKAGE_INFO, PERSISTENCE_UNIT_NAME, PROPERTY_FILE, true);
	}

	private PetCityManagerFactory(final String propertyFilePath) throws IOException {
		super(PACKAGE_INFO, PERSISTENCE_UNIT_NAME, propertyFilePath, false);
	}

	public static PetCityManagerFactory getInstance() throws IOException {
		if (instance == null) {
			instance = new PetCityManagerFactory();
		}
		return instance;
	}

	public static PetCityManagerFactory getInstance(final String propertyFilePath) throws IOException {
		if (instance == null) {
			instance = new PetCityManagerFactory(propertyFilePath);
		}
		return instance;
	}
}