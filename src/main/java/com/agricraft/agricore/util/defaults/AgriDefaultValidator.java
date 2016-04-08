/*
 */
package com.agricraft.agricore.util.defaults;

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
			AgriCore.getLogger().warn("Null Block!");
			return false;
		}
		AgriCore.getLogger().warn("Faking valid result for block: " + block + "!");
		return true;
	}

	@Override
	public boolean isValidItem(String item) {
		if (item == null) {
			AgriCore.getLogger().warn("Null Item!");
			return false;
		}
		AgriCore.getLogger().warn("Faking valid result for item: " + item + "!");
		return true;
	}

	@Override
	public boolean isValidTexture(String texture) {
		if (texture == null) {
			AgriCore.getLogger().warn("Null Texture!");
			return false;
		}
		AgriCore.getLogger().warn("Faking valid result for texture: " + texture + "!");
		return true;
	}

}
