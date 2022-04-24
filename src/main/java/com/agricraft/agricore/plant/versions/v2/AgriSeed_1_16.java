package com.agricraft.agricore.plant.versions.v2;

import com.agricraft.agricore.plant.AgriSeed;

import java.util.List;

public class AgriSeed_1_16 extends AgriObject_1_16 {
    protected final boolean overridePlanting;

    public AgriSeed_1_16() {
        this("minecraft:wheat_seeds");
    }

    public AgriSeed_1_16(String seed) {
        this(seed, false);
    }

    public AgriSeed_1_16(String seed, boolean useTags) {
        this(seed, useTags, "");
    }

    public AgriSeed_1_16(String seed, boolean useTags, String data, List<String> ignoredData) {
        super("item", seed, useTags, data, ignoredData);
        this.overridePlanting = true;
    }

    public AgriSeed_1_16(String seed, boolean useTags, String data, String... ignoredData) {
        super("item", seed, useTags, data, ignoredData);
        this.overridePlanting = true;
    }

    @Override
    public AgriSeed toNew() {
        return new AgriSeed(this.object, this.useTag, this.data, this.ignoredData);
    }
}
