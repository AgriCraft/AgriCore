/*
 */
package com.agricraft.agricore.defaults;

import com.agricraft.agricore.util.AgriConverter;

/**
 *
 * @author RlonRyan
 */
public class AgriDefaultConverter implements AgriConverter {

	@Override
	public Object toStack(String element, int meta, int amount, String tags, boolean ignoreMeta, boolean ignoreTags, boolean useOreDict) {
		return String.format("Stack: { Element: '%s', Meta: %d, Amount: %d, Tags: '%s' }", element, meta, amount, tags);
	}

}
