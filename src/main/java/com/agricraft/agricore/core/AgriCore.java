package com.agricraft.agricore.core;

import com.agricraft.agricore.defaults.AgriDefaultConverter;
import com.agricraft.agricore.defaults.AgriDefaultLog;
import com.agricraft.agricore.defaults.AgriDefaultTranslator;
import com.agricraft.agricore.defaults.AgriDefaultValidator;
import com.agricraft.agricore.lang.AgriTranslationAdapter;
import com.agricraft.agricore.lang.AgriTranslator;
import com.agricraft.agricore.log.AgriLogAdapter;
import com.agricraft.agricore.log.AgriLogManager;
import com.agricraft.agricore.log.AgriLogger;
import com.agricraft.agricore.registry.AgriMutations;
import com.agricraft.agricore.registry.AgriPlants;
import com.agricraft.agricore.registry.AgriSoils;
import com.agricraft.agricore.util.AgriConverter;
import com.agricraft.agricore.util.AgriValidator;

public final class AgriCore {

    private static AgriLogManager logManager;

    private static AgriTranslator translator;

    private static AgriValidator validator;

    private static AgriConverter converter;

    private static AgriPlants plants;

    private static AgriMutations mutations;

    private static AgriSoils soils;

    private AgriCore() {
    }

    static {
        AgriCore.logManager = new AgriLogManager(new AgriDefaultLog());
        AgriCore.translator = new AgriTranslator(new AgriDefaultTranslator());
        AgriCore.validator = new AgriDefaultValidator();
        AgriCore.converter = new AgriDefaultConverter();
        AgriCore.plants = new AgriPlants();
        AgriCore.mutations = new AgriMutations();
        AgriCore.soils = new AgriSoils();
    }

    public static void init(
            AgriLogAdapter log,
            AgriTranslationAdapter trans,
            AgriValidator validator,
            AgriConverter converter
    ) {
        AgriCore.logManager = new AgriLogManager(log);
        AgriCore.translator = new AgriTranslator(trans);
        AgriLogger logger = AgriCore.getCoreLogger();
        logger.info("Initializing core!");
        AgriCore.validator = validator;
        AgriCore.converter = converter;
        AgriCore.plants = new AgriPlants();
        AgriCore.mutations = new AgriMutations();
        AgriCore.soils = new AgriSoils();
        logger.info("Initialized core!");
    }

    public static AgriLogger getCoreLogger() {
        return getLogger("agricore");
    }

    public static AgriLogger getLogger(Object source) {
        return logManager.getLogger(source);
    }

    public static AgriTranslator getTranslator() {
        return translator;
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

    public static AgriMutations getMutations() {
        return mutations;
    }

    public static AgriPlants getPlants() {
        return plants;
    }

    public static AgriSoils getSoils() {
        return soils;
    }

}
