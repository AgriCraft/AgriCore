package com.agricraft.agricore.config;

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
    CLIENT,
    LOGGING;

    public String getDisplayName() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

}
