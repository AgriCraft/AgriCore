/*
 */
package com.agricraft.agricore.util;

/**
 *
 * @author RlonRyan
 */
public interface AgriLogger {

	void all(Object object);

	void severe(Object object);

	void info(Object object);

	void warn(Object object);

	void debug(Object object);
	
	void error(Object object);

	void trace(Exception e);

}
