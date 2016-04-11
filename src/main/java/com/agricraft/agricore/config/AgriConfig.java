/*
 */
package com.agricraft.agricore.config;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.util.AgriProvider;
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
	
	private final AgriProvider provider;
	
	private final Map<Object, List<Field>> configurables;

	public AgriConfig(AgriProvider provider) {
		this.configurables = new HashMap<>();
		this.provider = provider;
	}
	
	public void init() {
		AgriCore.getLogger().debug("Initializing Config!");
		this.provider.init();
		AgriCore.getLogger().debug("Initialized Config!");
	}
	
	public void load() {
		AgriCore.getLogger().debug("Loading Config!");
		this.provider.load();
		AgriCore.getLogger().debug("Config Loaded!");
	}
	
	public void reload() {
		this.configurables.forEach(
				(configurable, fields) -> fields.forEach(
						(field) -> handleConfigurable(configurable, field)
				)
		);
	}
	
	public void save() {
		AgriCore.getLogger().debug("Saving Config!");
		this.provider.save();
		AgriCore.getLogger().debug("Config Saved!");
	}

	public final synchronized void addConfigurable(Object configurable) {
		if (!configurables.containsKey(configurable)) {
			List<Field> fields = new ArrayList<>();
			//LogHelper.debug("Registering Configurable: " + clazz.getCanonicalName());
			for (Field f : configurable.getClass().getDeclaredFields()) {
				if (f.getAnnotation(AgriConfigurable.class) != null) {
					//LogHelper.debug("Handling Configurable Field: " + f.getName());
					if (Modifier.isFinal(f.getModifiers())) {
						AgriCore.getLogger().error("Configurable Field: " + f.getName() + " is final!");
					} else {
						handleConfigurable(configurable, f);
						fields.add(f);
					}
				}
			}
			configurables.put(configurable, fields);
		}
	}

	protected final void handleConfigurable(Object configurable, Field f) {

		//LogHelper.debug("Loading Configurable Field: " + e.getName());
		final AgriConfigurable anno = f.getAnnotation(AgriConfigurable.class);
		try {

			f.setAccessible(true);
			Object obj = f.get(configurable);

			if (obj instanceof String) {
				f.set(null, provider.getString(anno.key(), anno.category().name(), (String) obj, anno.comment()));
			} else if (obj instanceof Boolean) {
				f.set(null, provider.getBoolean(anno.key(), anno.category().name(), (boolean) obj, anno.comment()));
			} else if (obj instanceof Integer) {
				int min = Integer.parseInt(anno.min());
				int max = Integer.parseInt(anno.max());
				f.set(null, provider.getInt(anno.key(), anno.category().name(), (int) obj, min, max, anno.comment()));
			} else if (obj instanceof Float) {
				float min = Float.parseFloat(anno.min());
				float max = Float.parseFloat(anno.max());
				f.set(null, provider.getFloat(anno.key(), anno.category().name(), (float) obj, min, max, anno.comment()));
			} else if (obj instanceof Double) {
				double min = Double.parseDouble(anno.min());
				double max = Double.parseDouble(anno.max());
				f.set(null, provider.getDouble(anno.key(), anno.category().name(), (double) obj, min, max, anno.comment()));
			} else {
				AgriCore.getLogger().debug("Bad Type: " + f.getType().toString());
			}

		} catch (NumberFormatException e) {
			AgriCore.getLogger().debug("Invalid parameter bound!");
		} catch (IllegalAccessException | IllegalArgumentException | SecurityException e) {
			AgriCore.getLogger().trace(e);
		}

	}

	@Override
	public String toString() {
		return "\nAgriConfig:\n" + this.provider.toString().replaceAll("\\{", "{\n\t").replaceAll("}", "\n}\n").replaceAll(", ", ",\n\t");
	}
	
}
