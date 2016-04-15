/*
 */
package com.agricraft.agricore.util;

/**
 *
 * @author RlonRyan
 */
public interface AgriConverter {
	
	Object toStack(String element);
	
	Object toStack(String element, int amount, int meta);
	
}
