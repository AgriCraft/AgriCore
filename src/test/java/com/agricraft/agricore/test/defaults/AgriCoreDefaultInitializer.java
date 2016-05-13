/*
 */
package com.agricraft.agricore.test.defaults;

import com.agricraft.agricore.core.AgriCore;
import java.nio.file.Paths;

/**
 *
 * @author RlonRyan
 */
public final class AgriCoreDefaultInitializer {
	
	public static void initCore() {
		AgriCore.init(
				new AgriDefaultLog(),
				new AgriDefaultTranslator(),
				new AgriDefaultValidator(),
				new AgriDefaultConverter(),
				new AgriDefaultConfig(Paths.get("config", "agricraft", "agricraft.config"))
		);
	}
	
}
