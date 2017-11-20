package uo.ri.conf;

import java.io.IOException;
import java.util.Properties;

public class Conf {
	private static Conf instance;
	private Properties properties;

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

	public static String get(String key) {
		return getInstance().getProperty(key);
	}

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
