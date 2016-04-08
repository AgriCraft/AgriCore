/*
 */
package com.agricraft.agricore.core;

import com.agricraft.agricore.util.AgriLogger;
import com.agricraft.agricore.util.AgriValidator;
import com.agricraft.agricore.util.defaults.AgriDefaultLogger;
import com.agricraft.agricore.util.defaults.AgriDefaultValidator;

/**
 *
 * @author RlonRyan
 */
public final class AgriCore {
	
	private static AgriLogger logger = new AgriDefaultLogger();
	
	private static AgriValidator validator = new AgriDefaultValidator();

	private AgriCore() {
	}

	public static AgriLogger getLogger() {
		return logger;
	}

	public static AgriValidator getValidator() {
		return validator;
	}

	public static void setLogger(AgriLogger logger) {
		AgriCore.logger = logger;
	}

	public static void setValidator(AgriValidator validator) {
		AgriCore.validator = validator;
	}
	
}
