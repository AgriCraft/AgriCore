package com.agricraft.agricore.json;

import javax.annotation.Nullable;
import java.util.function.Function;

public interface AgriJsonVersion<T extends AgriSerializable & Comparable<T>> {

    Class<T> getElementClass();

    String descriptor();

    @Nullable
    default AgriJsonVersion<?> previousVersion() {
        return null;
    }

    @Nullable
    default Function<AgriSerializable, T> versionConverter() {
        return null;
    }
}
