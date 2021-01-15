package com.agricraft.agricore.plant.versions.v1;

import com.agricraft.agricore.plant.AgriProduct;

import java.util.Arrays;
import java.util.List;

public class AgriProduct_1_12 extends AgriStack_1_12 {
    private final int min;
    private final int max;

    private final double chance;

    private final boolean required;

    public AgriProduct_1_12() {
        super();
        this.min = 5;
        this.max = 5;
        this.chance = 0.99;
        this.required = true;
    }

    public AgriProduct_1_12(String item, int min, int max, double chance, boolean required, String... ignoreTags) {
        this(item, min, max, chance, required, Arrays.asList(ignoreTags));
    }

    public AgriProduct_1_12(String item, int min, int max, double chance, boolean required, List<String> ignoreTags) {
        super(item, false, "", ignoreTags);
        this.min = min;
        this.max = max;
        this.chance = chance;
        this.required = required;
    }

    public AgriProduct toNew() {
        return new AgriProduct(item, min, max, chance, required, ignoreTags);
    }
}
