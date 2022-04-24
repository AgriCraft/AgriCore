package com.agricraft.agricore.defaults;

import com.agricraft.agricore.util.AgriConverter;
import com.agricraft.agricore.util.TypeHelper;
import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;

public class AgriDefaultConverter implements AgriConverter {
    @Override
    public <T> Collection<T> convert(Class<T> token, String object, int amount, boolean useTag, List<String> stateData, String nbt, List<String> ignoreNbt) {
        if (TypeHelper.isType(String.class, token)) {
            @SuppressWarnings("unchecked")
            final T string = (T) String.format("Object: { Token: '%s', Element: '%s', Amount: %d, Tags: '%s' }", token, amount, object, nbt);
            return ImmutableList.of(string);
        } else {
            return ImmutableList.of();
        }
    }
}
