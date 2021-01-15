package com.agricraft.agricore.json;

import javax.annotation.Nullable;

public interface AgriJsonVersion<T extends AgriSerializable> {

    Class<T> getElementClass();

    String descriptor();

    @Nullable
    default AgriJsonVersion<?> previousVersion() {
        return null;
    }
}
