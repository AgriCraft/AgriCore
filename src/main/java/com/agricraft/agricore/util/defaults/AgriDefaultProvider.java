/*
 */
package com.agricraft.agricore.util.defaults;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.util.AgriProvider;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

/**
 *
 * @author RlonRyan
 */
public class AgriDefaultProvider implements AgriProvider {

	private final Properties properties;
	private final Path path;

	public AgriDefaultProvider(Path path) {
		this.properties = new Properties();
		this.path = path;
	}

	@Override
	public void init() {
		this.load();
	}

	@Override
	public void load() {
		AgriCore.getLogger().debug("Loading Properties: " + this.path + "!");
		try (BufferedReader in = Files.newBufferedReader(this.path)) {
			this.properties.load(in);
			AgriCore.getLogger().debug("Properties Loaded!");
		} catch (IOException e) {
			AgriCore.getLogger().warn("Unable to load config!");
			AgriCore.getLogger().trace(e);
		}
	}

	@Override
	public void save() {

		// Ensure path is good.
		this.path.getParent().toFile().mkdirs();
		
		AgriCore.getLogger().debug("Saving Properties: " + this.path + "!");

		try (BufferedWriter out = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			this.properties.store(out, "AgriCore Configuration File");
			AgriCore.getLogger().debug("Properties saved!");
		} catch (IOException e) {
			AgriCore.getLogger().warn("Unable to save config!");
			AgriCore.getLogger().trace(e);
		}

	}

	@Override
	public boolean getBoolean(String name, String category, boolean defaultValue, String comment) {
		if (properties.get(name) != null) {
			return Boolean.parseBoolean(this.properties.getProperty(name));
		} else {
			properties.setProperty(name, String.valueOf(defaultValue));
		}
		return defaultValue;
	}

	@Override
	public int getInt(String name, String category, int defaultValue, int minValue, int maxValue, String comment) {
		if (properties.get(name) != null) {
			try {
				return Integer.parseInt(this.properties.getProperty(name));
			} catch (NumberFormatException e) {
				AgriCore.getLogger().warn("Bad configuration option for: " + category + ":" + name + "!");
			}
		} else {
			properties.setProperty(name, String.valueOf(defaultValue));
		}
		return defaultValue;
	}

	@Override
	public float getFloat(String name, String category, float defaultValue, float minValue, float maxValue, String comment) {
		if (properties.get(name) != null) {
			try {
				return Float.parseFloat(this.properties.getProperty(name));
			} catch (NumberFormatException e) {
				AgriCore.getLogger().warn("Bad Float for Config: " + category + ":" + name + "!");
			}
		} else {
			properties.setProperty(name, String.valueOf(defaultValue));
		}
		return defaultValue;
	}
	
	@Override
	public double getDouble(String name, String category, double defaultValue, double minValue, double maxValue, String comment) {
		if (properties.get(name) != null) {
			try {
				return Double.parseDouble(this.properties.getProperty(name));
			} catch (NumberFormatException e) {
				AgriCore.getLogger().warn("Bad Double for Config: " + category + ":" + name + "!");
			}
		} else {
			properties.setProperty(name, String.valueOf(defaultValue));
		}
		return defaultValue;
	}

	@Override
	public String getString(String name, String category, String defaultValue, String comment) {
		if (properties.get(name) != null) {
			return properties.getProperty(name);
		} else {
			properties.setProperty(name, defaultValue);
		}
		return defaultValue;
	}

	@Override
	public String toString() {
		return this.path.toString() + ": "+ this.properties.toString();
	}

}
