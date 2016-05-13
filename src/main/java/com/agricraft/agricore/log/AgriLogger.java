/*
 */
package com.agricraft.agricore.log;

import com.agricraft.agricore.core.AgriCore;

/**
 *
 * @author RlonRyan
 */
public class AgriLogger {

	private final AgriLogAdapter adapter;
	private final Object source;

	private boolean enabled = true;
	private boolean debugEnabled = true;
	private boolean infoEnabled = true;

	AgriLogger(AgriLogAdapter adapter, Object source) {
		this.adapter = adapter;
		this.source = source;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isDebugEnabled() {
		return enabled && debugEnabled;
	}

	public void setDebugEnabled(boolean debugEnabled) {
		this.debugEnabled = debugEnabled;
	}

	public boolean isInfoEnabled() {
		return enabled && infoEnabled;
	}

	public void setInfoEnabled(boolean infoEnabled) {
		this.infoEnabled = infoEnabled;
	}

	public void all(Object format, Object... objects) {
		if (isEnabled()) {
			adapter.all(source, AgriCore.getTranslator().translate(format), objects);
		}
	}

	public void severe(Object format, Object... objects) {
		if (isEnabled()) {
			adapter.severe(source, AgriCore.getTranslator().translate(format), objects);
		}
	}

	public void info(Object format, Object... objects) {
		if (isEnabled() && isInfoEnabled()) {
			adapter.info(source, AgriCore.getTranslator().translate(format), objects);
		}
	}

	public void warn(Object format, Object... objects) {
		if (isEnabled()) {
			adapter.warn(source, AgriCore.getTranslator().translate(format), objects);
		}
	}
	
	public void debug(Object format, Object... objects) {
		if (isEnabled() && isDebugEnabled()) {
			adapter.debug(source, AgriCore.getTranslator().translate(format), objects);
		}
	}

	public void error(Object format, Object... objects) {
		if (isEnabled()) {
			adapter.error(source, AgriCore.getTranslator().translate(format), objects);
		}
	}

	public void trace(Exception e) {
		if (isEnabled() && isDebugEnabled()) {
			adapter.trace(source, e);
		}
	}

}
