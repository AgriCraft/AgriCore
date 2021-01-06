package com.agricraft.agricore.registry;

public interface AgriLoadableRegistry<T extends Comparable<T>> {

    boolean acceptsElement(String filename);

    Class<T> getElementClass();

    void registerElement(T element);

    void clearElements();

}
