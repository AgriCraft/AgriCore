package com.agricraft.agricore.util;

import java.util.List;
import java.util.Optional;

@FunctionalInterface
public interface AgriConverter {

    <T> Optional<T> toStack(Class<T> token, String element, int amount, String tags, boolean useOreDict, List<String> ignoreTags);

}
