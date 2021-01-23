package com.agricraft.agricore.plant;

import java.util.List;

public class AgriSeed extends AgriObject {
    protected final boolean overridePlanting;

    public AgriSeed() {
        this("minecraft:wheat_seeds");
    }

    public AgriSeed(String seed) {
        this(seed, false);
    }

    public AgriSeed(String seed, boolean useTags) {
        this(seed, useTags, "");
    }

    public AgriSeed(String seed, boolean useTags, String data, String... ignoredData) {
        super("item", seed, useTags, data, ignoredData);
        this.overridePlanting = false;
    }

    public AgriSeed(String seed, boolean useTags, String data, List<String> ignoredData) {
        super("item", seed, useTags, data, ignoredData);
        this.overridePlanting = false;
    }

    public boolean isOverridePlanting() {
        return this.overridePlanting;
    }

}
