/*
 */
package com.agricraft.agricore.util;

import java.util.List;

/**
 *
 * @author RlonRyan
 */
@FunctionalInterface
public interface AgriConverter {

    Object toStack(String element, int meta, int amount, String tags, boolean ignoreMeta, boolean useOreDict, List<String> ignoreTags);

}
