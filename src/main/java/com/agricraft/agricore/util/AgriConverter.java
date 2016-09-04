/*
 */
package com.agricraft.agricore.util;

/**
 *
 * @author RlonRyan
 */
@FunctionalInterface
public interface AgriConverter {

    default Object toStack(String element) {
        return this.toStack(element, 0);
    }

    default Object toStack(String element, int meta) {
        return this.toStack(element, meta, 1);
    }

    default Object toStack(String element, int meta, int amount) {
        return this.toStack(element, meta, amount, "", false, false, false);
    }
    
    Object toStack(String element, int meta, int amount, String tags, boolean ignoreMeta, boolean ignoreTags, boolean useOreDict);

}
