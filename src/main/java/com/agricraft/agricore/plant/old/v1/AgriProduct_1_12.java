package com.agricraft.agricore.plant;

import java.util.Arrays;
import java.util.List;

public class AgriProductOld extends AgriStackOld {
    private final int min;
    private final int max;

    private final double chance;

    private final boolean required;

    public AgriProductOld() {
        super();
        this.min = 5;
        this.max = 5;
        this.chance = 0.99;
        this.required = true;
    }

    public AgriProductOld(String item, int min, int max, double chance, boolean required, String... ignoreTags) {
        this(item, min, max, chance, required, Arrays.asList(ignoreTags));
    }

    public AgriProductOld(String item, int min, int max, double chance, boolean required, List<String> ignoreTags) {
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
