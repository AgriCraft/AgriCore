/*
 */
package com.agricraft.agricore.test.defaults;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.util.AgriValidator;

/**
 *
 * @author RlonRyan
 */
public class AgriDefaultValidator implements AgriValidator {

	@Override
	public boolean isValidBlock(String block) {
		if (block == null) {
			AgriCore.getCoreLogger().warn("Null Block!");
			return false;
		}
		AgriCore.getCoreLogger().warn("Faking valid result for block: " + block + "!");
		return true;
	}

	@Override
	public boolean isValidItem(String item) {
		if (item == null) {
			AgriCore.getCoreLogger().warn("Null Item!");
			return false;
		}
		AgriCore.getCoreLogger().warn("Faking valid result for item: " + item + "!");
		return true;
	}

	@Override
	public boolean isValidTexture(String texture) {
		if (texture == null) {
			AgriCore.getCoreLogger().warn("Null Texture!");
			return false;
		}
		AgriCore.getCoreLogger().warn("Faking valid result for texture: " + texture + "!");
		return true;
	}

}
