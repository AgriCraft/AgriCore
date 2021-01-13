package com.agricraft.agricore.util;

import java.util.Collection;
import java.util.List;

@FunctionalInterface
public interface AgriConverter {
    <T> Collection<T> convert(Class<T> token, String object, int amount, boolean useTag, String data, List<String> ignoreData);
}
