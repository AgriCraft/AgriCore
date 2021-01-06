package com.agricraft.agricore.defaults;

import com.agricraft.agricore.util.AgriConverter;
import com.agricraft.agricore.util.TypeHelper;
import java.util.List;
import java.util.Optional;

public class AgriDefaultConverter implements AgriConverter {
    @Override
    public <T> Optional<T> toStack(Class<T> token, String element, int amount, String tags, boolean useOreDict, List<String> ignoreTags) {
        if (TypeHelper.isType(String.class, token)) {
            @SuppressWarnings("unchecked")
            final T stack = (T) String.format("Stack: { Token: '%s', Element: '%s', Amount: %d, Tags: '%s' }", token, element, amount, tags);
            return Optional.of(stack);
        } else {
            return Optional.empty();
        }
    }
}
