package uo.ri.conf;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * Configuration class that will get the strings from the properties file.
 *
 * @author Guillermo Facundo Colunga
 * @version 201711201802
 * @since 201711201802
 */
public class Conf {

	// Unique instance of the class.
	private static Conf instance;

	// Properties file.
	private Properties properties;

	// Path to the properties file.
	private final String CONF_FILE = "configuration.properties";

	private Conf() {
		this.properties = new Properties();
		try {
			properties.load(
					Conf.class.getClassLoader().getResourceAsStream(CONF_FILE));
		} catch (IOException e) {
			throw new RuntimeException("Properties file cannot be loaded", e);
		}
	}

	/**
	 * Gets the corresponding string for the given key from the configuration
	 * file.
	 * 
	 * @param key to look for at the configuration file.
	 * @return the string that matches the key in the configuration file.
	 */
	public static String get(String key) {
		return getInstance().getProperty(key);
	}

	/**
	 * Gets the property form the configuration file.
	 * 
	 * @param key to look for in the configuration file.
	 * @return the string that matches the key.
	 */
	public String getProperty(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			throw new RuntimeException("Property not found in conf file");
		}
		return value;
	}

	private static Conf getInstance() {
		if (instance == null) {
			instance = new Conf();
		}
		return instance;
	}
}
