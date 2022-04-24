package com.agricraft.agricore.util;

import com.google.gson.JsonElement;

import javax.annotation.Nonnull;
import java.util.List;

public interface AgriValidator {
    <T> boolean isValidObject(final Class<T> token, final String item, boolean useTag, List<String> stateDefinition);

    default boolean isValidObject(final String token, final String item, boolean useTag, List<String> stateDefinition) {
        return this.isValidObject(this.getTokenClass(token), item, useTag, stateDefinition);
    }

    @Nonnull
    <T> Class<T> getTokenClass(String token);

    boolean isValidRenderType(final String renderType);

    boolean isValidSeason(final String season);

    boolean isValidResource(final String resource);

    boolean isValidCallback(final JsonElement callback);

    boolean isValidHumidity(final String humidity);

    boolean isValidAcidity(final String acidity);

    boolean isValidNutrients(final String nutrients);
    
    boolean isValidMod(final String modid);

}
