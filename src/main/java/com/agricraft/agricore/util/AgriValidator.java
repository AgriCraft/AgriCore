package com.agricraft.agricore.util;

import javax.annotation.Nonnull;

public interface AgriValidator {
    <T> boolean isValidObject(final Class<T> token, final String item);

    default boolean isValidObject(final String token, final String item) {
        return this.isValidObject(this.getTokenClass(token), item);
    }

    @Nonnull
    <T> Class<T> getTokenClass(String token);

    boolean isValidSeason(final String season);

    boolean isValidResource(final String resource);

    boolean isValidCallback(final String callback);
    
    boolean isValidMod(final String modid);

}
