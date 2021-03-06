package com.agricraft.agricore.core;

import com.agricraft.agricore.config.AgriConfigAdapter;
import com.agricraft.agricore.defaults.*;
import com.agricraft.agricore.log.AgriLogAdapter;
import com.agricraft.agricore.log.AgriLogManager;
import com.agricraft.agricore.log.AgriLogger;
import com.agricraft.agricore.registry.*;
import com.agricraft.agricore.util.AgriConverter;
import com.agricraft.agricore.util.AgriValidator;

public final class AgriCore {

    private static AgriLogManager logManager;

    private static AgriValidator validator;

    private static AgriConverter converter;

    private static AgriPlants plants;

    private static AgriWeeds weeds;

    private static AgriMutations mutations;

    private static AgriSoils soils;

    private static AgriFertilizers fertilizers;

    private static AgriConfigAdapter config;

    private AgriCore() {
    }

    static {
        AgriCore.logManager = new AgriLogManager(new AgriDefaultLog());
        AgriCore.config = new AgriDefaultConfig();
        AgriCore.validator = new AgriDefaultValidator();
        AgriCore.converter = new AgriDefaultConverter();
        AgriCore.plants = new AgriPlants();
        AgriCore.weeds = new AgriWeeds();
        AgriCore.mutations = new AgriMutations();
        AgriCore.soils = new AgriSoils();
        AgriCore.fertilizers = new AgriFertilizers();
    }

    public static void init(
            AgriLogAdapter log,
            AgriValidator validator,
            AgriConverter converter,
            AgriConfigAdapter config
    ) {
        AgriCore.logManager = new AgriLogManager(log);
        AgriCore.config = config;
        AgriLogger logger = AgriCore.getCoreLogger();
        logger.info("Initializing core!");
        AgriCore.validator = validator;
        AgriCore.converter = converter;
        AgriCore.plants = new AgriPlants();
        AgriCore.weeds = new AgriWeeds();
        AgriCore.mutations = new AgriMutations();
        AgriCore.soils = new AgriSoils();
        AgriCore.fertilizers = new AgriFertilizers();
        logger.info("Initialized core!");
    }

    public static AgriLogger getCoreLogger() {
        return getLogger("agricore");
    }

    public static AgriLogger getLogger(Object source) {
        return logManager.getLogger(source);
    }

    public static AgriLogManager getLogManager() {
        return logManager;
    }

    public static AgriValidator getValidator() {
        return validator;
    }

    public static AgriConverter getConverter() {
        return converter;
    }

    public static AgriConfigAdapter getConfig() {
        return config;
    }

    public static AgriMutations getMutations() {
        return mutations;
    }

    public static AgriPlants getPlants() {
        return plants;
    }

    public static AgriWeeds getWeeds() {
        return weeds;
    }

    public static AgriSoils getSoils() {
        return soils;
    }

    public static AgriFertilizers getFertilizers() {
        return fertilizers;
    }

}
