package com.agricraft.agricore.plant.versions.v2;

import com.agricraft.agricore.plant.AgriProduct;

import java.util.Arrays;
import java.util.List;

public class AgriProduct_1_16 extends AgriObject_1_16 {

    private final int min;
    private final int max;

    private final double chance;

    private final boolean required;

    public AgriProduct_1_16() {
        super();
        this.min = 5;
        this.max = 5;
        this.chance = 0.99;
        this.required = true;
    }

    public AgriProduct_1_16(String item, boolean useTag, int min, int max, double chance, boolean required, String data, String... ignoreTags) {
        this(item, useTag, min, max, chance, required, data, Arrays.asList(ignoreTags));
    }

    public AgriProduct_1_16(String item, boolean useTag, int min, int max, double chance, boolean required, String data, List<String> ignoreTags) {
        super("item", item, useTag, data, ignoreTags);
        this.min = min;
        this.max = max;
        this.chance = chance;
        this.required = required;
    }

    @Override
    public AgriProduct toNew() {
        return new AgriProduct(this.object, this.useTag, this.min, this.max, this.chance, this.required, this.data, this.ignoredData);
    }
}
