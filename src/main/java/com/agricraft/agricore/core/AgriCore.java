/*
 */
package com.agricraft.agricore.core;

import com.agricraft.agricore.config.AgriConfig;
import com.agricraft.agricore.util.AgriLogger;
import com.agricraft.agricore.util.AgriValidator;
import com.agricraft.agricore.util.defaults.AgriDefaultLogger;
import com.agricraft.agricore.util.defaults.AgriDefaultProvider;
import com.agricraft.agricore.util.defaults.AgriDefaultValidator;
import java.nio.file.Paths;

/**
 *
 * @author RlonRyan
 */
public final class AgriCore {
	
	private static AgriLogger logger = new AgriDefaultLogger();
	
	private static AgriValidator validator = new AgriDefaultValidator();
	
	private static AgriConfig config = new AgriConfig(new AgriDefaultProvider(Paths.get("config", "agricraft", "agricraft.config")));
	
	private AgriCore() {
	}

	public static AgriLogger getLogger() {
		return logger;
	}

	public static AgriValidator getValidator() {
		return validator;
	}

	public static AgriConfig getConfig() {
		return config;
	}

	public static void setLogger(AgriLogger logger) {
		AgriCore.logger = logger;
	}

	public static void setValidator(AgriValidator validator) {
		AgriCore.validator = validator;
	}

	public static void setConfig(AgriConfig config) {
		AgriCore.config = config;
	}
	
}
