package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AgriSeed extends AgriObject {
    private final List<AgriObject> substitutes;

    public AgriSeed() {
        super();
        this.substitutes = new ArrayList<>();
    }

    public AgriSeed(String seed, List<AgriObject> substitutes) {
        super("item", seed);
        this.substitutes = substitutes;
    }

    public AgriSeed(String seed, boolean useTags, List<AgriObject> substitutes) {
        super("item", seed, useTags);
        this.substitutes = substitutes;
    }

    public AgriSeed(String seed, boolean useTags, List<AgriObject> substitutes, String data, String... ignoredData) {
        super("item", seed, useTags, data, ignoredData);
        this.substitutes = substitutes;
    }

    public AgriSeed(String seed, boolean useTags, List<AgriObject> substitutes, String data, List<String> ignoredData) {
        super("item", seed, useTags, data, ignoredData);
        this.substitutes = substitutes;
    }

    public boolean validate() {
        if (!super.validate()) {
            return false;
        }
        Iterator<AgriObject> iter = this.substitutes.iterator();
        while (iter.hasNext()) {
            AgriObject seed = iter.next();
            if (!seed.validate()) {
                AgriCore.getCoreLogger().info("Removing Invalid Seed Substitute: " + seed.object);
                iter.remove();
            }
        }
        return true;
    }

    public <T> List<T> convertSubstitutes(Class<T> token) {
        return this.convertSubstitutes(token, 1);
    }

    public <T> List<T> convertSubstitutes(Class<T> token, int amount) {
        return Stream.concat(
                this.convertAll(token, amount).stream(),
                this.substitutes.stream().flatMap(subs -> subs.convertAll(token, amount).stream())
        ).collect(Collectors.toList());
    }

    public Collection<AgriObject> getSeeds() {
        return new ImmutableList.Builder<AgriObject>().add(this).addAll(this.substitutes).build();
    }
}
