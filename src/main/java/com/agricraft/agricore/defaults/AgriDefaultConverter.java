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
	public Object toStack(String element) {
		return String.format("Stack: { Element: '%s' }", element);
	}

	@Override
	public Object toStack(String element, int amount, int meta) {
		return String.format("Stack: { Element: '%s', Amount: %d, Meta: %d }", element, amount, meta);
	}

}
