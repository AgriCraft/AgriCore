package com.agricraft.agricore.registry;

import com.agricraft.agricore.json.AgriJsonVersion;
import com.agricraft.agricore.json.AgriSerializable;

public interface AgriLoadableRegistry<T extends AgriSerializable & Comparable<T>> {

    boolean acceptsElement(String filename);

    AgriJsonVersion<T> getElementVersion();

    void registerElement(T element);

    void clearElements();

}
