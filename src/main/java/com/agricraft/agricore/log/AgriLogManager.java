/*
 */
package com.agricraft.agricore.log;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author RlonRyan
 */
public class AgriLogManager {
	
	private final AgriLogAdapter adapter;
	
	private final Map<Object, AgriLogger> loggers;

	public AgriLogManager(AgriLogAdapter adapter) {
		this.adapter = adapter;
		this.loggers = new HashMap<>();
	}
	
	public AgriLogger getLogger(Object source) {
		if (!loggers.containsKey(source)) {
			loggers.put(source, new AgriLogger(adapter, source));
		}
		return loggers.get(source);
	}
	
}
