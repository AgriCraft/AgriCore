/*
 */
package com.agricraft.agricore.core;

import com.agricraft.agricore.config.AgriConfig;
import com.agricraft.agricore.registry.AgriMutations;
import com.agricraft.agricore.registry.AgriPlants;
import com.agricraft.agricore.util.AgriConverter;
import com.agricraft.agricore.util.AgriLogger;
import com.agricraft.agricore.util.AgriProvider;
import com.agricraft.agricore.util.AgriValidator;
import com.agricraft.agricore.util.defaults.AgriDefaultConverter;
import com.agricraft.agricore.util.defaults.AgriDefaultLogger;
import com.agricraft.agricore.util.defaults.AgriDefaultProvider;
import com.agricraft.agricore.util.defaults.AgriDefaultValidator;
import java.nio.file.Paths;

/**
 *
 * @author RlonRyan
 */
public final class AgriCore {
	
	private static AgriLogger logger;
	
	private static AgriValidator validator;
	
	private static AgriConverter converter;
	
	private static AgriConfig config;
	
	private static AgriMutations mutations;
	
	private static AgriPlants plants;
	
	private AgriCore() {
	}
	
	public static void init() {
		AgriCore.init(
				new AgriDefaultLogger(),
				new AgriDefaultValidator(),
				new AgriDefaultConverter(),
				new AgriDefaultProvider(Paths.get("config", "agricraft", "agricraft.config"))
		);
	}
	
	public static void init(AgriLogger logger, AgriValidator validator, AgriConverter converter, AgriProvider provider) {
		logger.info("[AgriCore] Initializing core!");
		AgriCore.logger = logger;
		AgriCore.validator = validator;
		AgriCore.config = new AgriConfig(provider);
		AgriCore.plants = new AgriPlants();
		AgriCore.mutations = new AgriMutations();
		logger.info("[AgriCore] Loading config!");
		AgriCore.config.load();
		logger.info("[AgriCore] Loaded config!");
		logger.info("[AgriCore] Configuring modules!");
		AgriCore.config.addConfigurable(logger);
		AgriCore.config.addConfigurable(validator);
		AgriCore.config.addConfigurable(plants);
		AgriCore.config.addConfigurable(mutations);
		logger.info("[AgriCore] Configured modules!");
		logger.info("[AgriCore] Saving config!");
		AgriCore.config.save();
		logger.info("[AgriCore] Saved config!");
		logger.info("[AgriCore] Initialized core!");
	}

	public static AgriLogger getLogger() {
		return logger;
	}

	public static AgriValidator getValidator() {
		return validator;
	}

	public static AgriConverter getConverter() {
		return converter;
	}

	public static AgriConfig getConfig() {
		return config;
	}

	public static AgriMutations getMutations() {
		return mutations;
	}

	public static AgriPlants getPlants() {
		return plants;
	}
	
}
