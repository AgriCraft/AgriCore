package com.agricraft.agricore.log;

import com.agricraft.agricore.core.AgriCore;

import java.util.Objects;

public class AgriLogger {

    private final AgriLogAdapter adapter;
    private final Object source;

    AgriLogger(AgriLogAdapter adapter, Object source) {
        this.adapter = adapter;
        this.source = source;
    }

    public boolean isEnabled() {
        return AgriCore.getConfig().enableLogging();
    }

    public void all(Object format, Object... objects) {
        if (this.isEnabled()) {
            adapter.all(source, Objects.toString(format), objects);
        }
    }

    public void severe(Object format, Object... objects) {
        if (this.isEnabled()) {
            adapter.severe(source, Objects.toString(format), objects);
        }
    }

    public void info(Object format, Object... objects) {
        if (this.isEnabled()) {
            adapter.info(source, Objects.toString(format), objects);
        }
    }

    public void warn(Object format, Object... objects) {
        if (this.isEnabled()) {
            adapter.warn(source, Objects.toString(format), objects);
        }
    }

    public void debug(Object format, Object... objects) {
        if (this.isEnabled()) {
            adapter.debug(source, Objects.toString(format), objects);
        }
    }

    public void error(Object format, Object... objects) {
        if (this.isEnabled()) {
            adapter.error(source, Objects.toString(format), objects);
        }
    }

    public void trace(Exception e) {
        if (this.isEnabled()) {
            adapter.trace(source, e);
        }
    }

    /*
    @Override
    public String resolve(String input) {
        return input.replaceAll("\\$\\{log\\}", String.valueOf(source));
    }
    */
}
