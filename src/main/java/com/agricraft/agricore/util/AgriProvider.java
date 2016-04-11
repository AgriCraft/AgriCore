/*
 */
package com.agricraft.agricore.util;

/**
 *
 * @author RlonRyan
 */
public interface AgriProvider {
	
	void init();
	
	void load();
	
	void save();
	
	boolean getBoolean(String name, String category, boolean defaultValue, String comment);
	
	int getInt(String name, String category, int defaultValue, int minValue, int maxValue, String comment);
	
	float getFloat(String name, String category, float defaultValue, float minValue, float maxValue, String comment);
	
	double getDouble(String name, String category, double defaultValue, double minValue, double maxValue, String comment);
	
	public String getString(String name, String category, String defaultValue, String comment);
	
}
