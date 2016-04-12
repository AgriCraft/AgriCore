/*
 */
package com.agricraft.agricore.util.defaults;

import com.agricraft.agricore.util.AgriConverter;

/**
 *
 * @author RlonRyan
 */
public class AgriDefaultConverter implements AgriConverter {

	@Override
	public Object toStack(String element, int amount, int meta) {
		return String.format("Stack: { Element: '%s', Amount: %d, Meta: %d }", element, amount, meta);
	}
	
}
