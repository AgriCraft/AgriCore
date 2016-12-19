/*
 */
package com.agricraft.agricore.util;

import java.util.Arrays;
import java.util.List;

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
        return this.toStack(element, meta, amount, "", false, false);
    }

    default Object toStack(String element, int meta, int amount, String tags, boolean ignoreMeta, boolean useOreDict, String... ignoreTags) {
        return this.toStack(element, meta, amount, tags, ignoreMeta, useOreDict, Arrays.asList(ignoreTags));
    }

    Object toStack(String element, int meta, int amount, String tags, boolean ignoreMeta, boolean useOreDict, List<String> ignoreTags);

}
