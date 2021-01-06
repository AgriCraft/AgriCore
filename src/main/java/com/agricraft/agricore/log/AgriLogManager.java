package com.agricraft.agricore.log;

import java.util.HashMap;
import java.util.Map;

public class AgriLogManager {

    private final AgriLogAdapter adapter;

    private final Map<Object, AgriLogger> loggers;

    public AgriLogManager(AgriLogAdapter adapter) {
        this.adapter = adapter;
        this.loggers = new HashMap<>();
    }

    public AgriLogger getLogger(Object source) {
        if (!loggers.containsKey(source)) {
            AgriLogger logger = new AgriLogger(adapter, source);
            loggers.put(source, logger);
        }
        return loggers.get(source);
    }

}
