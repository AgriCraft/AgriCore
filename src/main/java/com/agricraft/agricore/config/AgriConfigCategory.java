/*
 */
package com.agricraft.agricore.config;

/**
 *
 * @author RlonRyan
 */
public enum AgriConfigCategory {
	
	CORE,
	FARMING,
	TOOLS,
	DEBUG,
	WORLD,
	IRRIGATION,
	STORAGE,
	DECORATION,
	COMPATIBILITY,
	CLIENT;

	public String getDisplayName() {
		return name().charAt(0) + name().substring(1).toLowerCase();
	}
	
}
