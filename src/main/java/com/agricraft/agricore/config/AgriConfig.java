/*
 */
package com.agricraft.agricore.config;

import com.agricraft.agricore.core.AgriCore;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RlonRyan
 */
public class AgriConfig {

	private final AgriConfigAdapter provider;

	private final Map<Class, List<Field>> configurableClasses;
	private final Map<Object, List<Field>> configurableObjects;

	private final List<AgriConfigListener> configurationListeners;

	public AgriConfig(AgriConfigAdapter provider) {
		this.configurableClasses = new HashMap<>();
		this.configurableObjects = new HashMap<>();
		this.configurationListeners = new ArrayList<>();
		this.provider = provider;
	}

	public void load() {
		//AgriCore.getCoreLogger().debug("Loading Config!");
		this.provider.load();
		this.configurableClasses.forEach(
				(configurable, fields) -> fields.forEach(
						(field) -> handleConfigurable(null, field)
				)
		);
		this.configurableObjects.forEach(
				(configurable, fields) -> fields.forEach(
						(field) -> handleConfigurable(configurable, field)
				)
		);
		this.configurationListeners.forEach(AgriConfigListener::onConfigChanged);
		//AgriCore.getCoreLogger().debug("Loaded Config!");
	}

	public void save() {
		//AgriCore.getCoreLogger().debug("Saving Config!");
		this.provider.save();
		//AgriCore.getCoreLogger().debug("Config Saved!");
	}

	public String getLocation() {
		return this.provider.getLocation();
	}

	public final synchronized void addListener(AgriConfigListener listener) {
		this.configurationListeners.add(listener);
	}

	public final synchronized void removeListener(AgriConfigListener listener) {
		if (listener != null) {
			this.configurationListeners.remove(listener);
		}
	}

	public final synchronized void addConfigurable(Class configurable) {
		if (!configurableClasses.containsKey(configurable)) {
			//AgriCore.getCoreLogger().debug("Registering Configurable Class: " + configurable.getCanonicalName());
			List<Field> fields = new ArrayList<>();
			for (Field f : configurable.getDeclaredFields()) {
				if (f.getAnnotation(AgriConfigurable.class) != null) {
					//AgriCore.getCoreLogger().debug("Handling Configurable Field: " + f.getName());
					if (Modifier.isFinal(f.getModifiers())) {
						AgriCore.getCoreLogger().error("Configurable Field: " + f.getName() + " is final!");
					} else if (Modifier.isStatic(f.getModifiers())) {
						handleConfigurable(configurable, f);
						fields.add(f);
					}
				}
			}
			configurableObjects.put(configurable, fields);
		}
	}

	public final synchronized void addConfigurable(Object configurable) {
		if (!configurableObjects.containsKey(configurable)) {
			List<Field> fields = new ArrayList<>();
			//AgriCore.getCoreLogger().debug("Registering Configurable: " + clazz.getCanonicalName());
			for (Field f : configurable.getClass().getDeclaredFields()) {
				if (f.getAnnotation(AgriConfigurable.class) != null) {
					//AgriCore.getCoreLogger().debug("Handling Configurable Field: " + f.getName());
					if (Modifier.isFinal(f.getModifiers())) {
						AgriCore.getCoreLogger().error("Configurable Field: " + f.getName() + " is final!");
					} else {
						handleConfigurable(configurable, f);
						fields.add(f);
					}
				}
			}
			configurableObjects.put(configurable, fields);
		}
	}

	protected final void handleConfigurable(Object configurable, Field f) {

		//AgriCore.getCoreLogger().debug("Loading Configurable Field: " + configurable.toString());
		final AgriConfigurable anno = f.getAnnotation(AgriConfigurable.class);
		try {

			f.setAccessible(true);
			Object obj = f.get(configurable);

			if (obj instanceof String) {
				f.set(configurable, provider.getString(anno.key(), anno.category().name(), (String) obj, anno.comment()));
			} else if (obj instanceof Boolean) {
				f.set(configurable, provider.getBoolean(anno.key(), anno.category().name(), (boolean) obj, anno.comment()));
			} else if (obj instanceof Integer) {
				int min = Integer.parseInt(anno.min());
				int max = Integer.parseInt(anno.max());
				f.set(configurable, provider.getInt(anno.key(), anno.category().name(), (int) obj, min, max, anno.comment()));
			} else if (obj instanceof Float) {
				float min = Float.parseFloat(anno.min());
				float max = Float.parseFloat(anno.max());
				f.set(configurable, provider.getFloat(anno.key(), anno.category().name(), (float) obj, min, max, anno.comment()));
			} else if (obj instanceof Double) {
				double min = Double.parseDouble(anno.min());
				double max = Double.parseDouble(anno.max());
				f.set(configurable, provider.getDouble(anno.key(), anno.category().name(), (double) obj, min, max, anno.comment()));
			} else {
				AgriCore.getCoreLogger().debug("Bad Type: " + f.getType().toString());
			}

		} catch (NumberFormatException e) {
			AgriCore.getCoreLogger().debug("Invalid parameter bound!");
		} catch (IllegalAccessException | IllegalArgumentException | SecurityException e) {
			AgriCore.getCoreLogger().trace(e);
		}

	}

	public boolean getBoolean(String name, AgriConfigCategory category, boolean defaultValue, String comment) {
		return this.provider.getBoolean(name, category.name(), defaultValue, comment);
	}

	public int getInt(String name, AgriConfigCategory category, int defaultValue, int minValue, int maxValue, String comment) {
		return this.provider.getInt(name, category.name(), defaultValue, minValue, maxValue, comment);
	}

	public float getFloat(String name, AgriConfigCategory category, float defaultValue, float minValue, float maxValue, String comment) {
		return this.provider.getFloat(name, category.name(), defaultValue, minValue, maxValue, comment);
	}

	public double getDouble(String name, AgriConfigCategory category, double defaultValue, double minValue, double maxValue, String comment) {
		return this.provider.getDouble(name, category.name(), defaultValue, minValue, maxValue, comment);
	}

	public String getString(String name, AgriConfigCategory category, String defaultValue, String comment) {
		return this.provider.getString(name, category.name(), defaultValue, comment);
	}

	@Override
	public String toString() {
		return "\nAgriConfig:\n" + this.provider.toString().replaceAll("\\{", "{\n\t").replaceAll("}", "\n}\n").replaceAll(", ", ",\n\t");
	}

}
