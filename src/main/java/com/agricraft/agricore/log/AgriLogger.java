/*
 */
package com.agricraft.agricore.log;

import com.agricraft.agricore.config.AgriConfigCategory;
import com.agricraft.agricore.config.AgriConfigurable;
import com.agricraft.agricore.config.AgriConfigurableInstance;
import com.agricraft.agricore.core.AgriCore;

/**
 *
 * @author RlonRyan
 */
public class AgriLogger implements AgriConfigurableInstance {

	private final AgriLogAdapter adapter;
	private final Object source;

	@AgriConfigurable(key = "${log}_log", category = AgriConfigCategory.LOGGING, comment = "Set to true to enable logging on the ${log} channel.")
	private boolean enabled = true;
	@AgriConfigurable(key = "${log}_log_info", category = AgriConfigCategory.LOGGING, comment = "Set to true to enable logging on the ${log} channel.")
	private boolean infoEnabled = true;
	@AgriConfigurable(key = "${log}_log_debug", category = AgriConfigCategory.LOGGING, comment = "Set to true to enable logging on the ${log} channel.")
	private boolean debugEnabled = true;

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

	@Override
	public String resolve(String input) {
		return input.replaceAll("\\$\\{log\\}", String.valueOf(source));
	}

}
