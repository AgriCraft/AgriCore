package com.agricraft.agricore.defaults;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.util.AgriValidator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.annotation.Nonnull;
import java.util.List;

public class AgriDefaultValidator implements AgriValidator {
    @Override
    public <T> boolean isValidObject(Class<T> token, String object, boolean useTag, List<String> stateDefinition) {
        if (object == null) {
            AgriCore.getCoreLogger().warn("Null Object!");
            return false;
        }
        AgriCore.getCoreLogger().warn("Faking valid result for object: " + object + "!");
        return true;
    }

    @Override
    @Nonnull
    @SuppressWarnings("unchecked")
    public <T> Class<T> getTokenClass(String token) {
        return (Class<T>) Object.class;
    }

    @Override
    public boolean isValidRenderType(String renderType) {
        return true;
    }

    @Override
    public boolean isValidSeason(String season) {
        return true;
    }

    @Override
    public boolean isValidResource(String resource) {
        if (resource == null) {
            AgriCore.getCoreLogger().warn("Null Texture!");
            return false;
        }
        AgriCore.getCoreLogger().warn("Faking valid result for texture: " + resource + "!");
        return true;
    }

    @Override
    public boolean isValidPlantCallback(JsonElement callback) {
        return true;
    }

    @Override
    public boolean isValidMutationCondition(String id, JsonObject args) {
        return true;
    }

    @Override
    public boolean isValidHumidity(String humidity) {
        return true;
    }

    @Override
    public boolean isValidAcidity(String acidity) {
        return true;
    }

    @Override
    public boolean isValidNutrients(String nutrients) {
        return true;
    }

    @Override
    public boolean isValidMod(String modid) {
        if (modid == null) {
            AgriCore.getCoreLogger().warn("Null Mod Id!");
            return false;
        }
        AgriCore.getCoreLogger().warn("Faking valid result for modid: " + modid + "!");
        return true;
    }

}
