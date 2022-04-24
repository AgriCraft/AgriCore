package com.agricraft.agricore.templates;

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
        this.overridePlanting = true;
    }

    public AgriSeed(String seed, boolean useTags, String data, List<String> ignoredData) {
        super("item", seed, useTags, data, ignoredData);
        this.overridePlanting = true;
    }

    public boolean isOverridePlanting() {
        return this.overridePlanting;
    }

}
