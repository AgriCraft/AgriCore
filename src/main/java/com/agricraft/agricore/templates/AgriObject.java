package com.agricraft.agricore.templates;

import com.agricraft.agricore.core.AgriCore;

import java.util.*;

public class AgriObject {
    protected final String type;
    protected final String object;
    protected final boolean useTag;
    protected final String nbt;
    protected final List<String> ignoredNbt;

    public AgriObject() {
        this("block", "minecraft:dirt");
    }
    
    public AgriObject(String type, String object) {
        this(type, object,false);
    }
    
    public AgriObject(String type, String object, boolean useTags) {
        this(type, object, useTags, "");
    }

    public AgriObject(String type, String object, boolean useTags, String nbt, String... ignoredNbt) {
        this(type, object, useTags, nbt, Arrays.asList(ignoredNbt));
    }

    public AgriObject(String type, String object, boolean useTags, String nbt, List<String> ignoredNbt) {
        this.type = type;
        this.object = object;
        this.useTag = useTags;
        this.nbt = nbt;
        this.ignoredNbt = ignoredNbt;
    }

    public String getObjectString() {
        return object;
    }

    public boolean useTag() {
        return useTag;
    }

    public String getNbt() {
        return nbt;
    }

    public List<String> getIgnoredNbt() {
        return ignoredNbt;
    }

    public <T> Optional<T> convertSingle(Class<T> token) {
        return this.convertSingle(token, 1);
    }

    public <T> Optional<T> convertSingle(Class<T> token, int amount) {
        return this.convertAll(token, amount).stream().findFirst();
    }

    public <T> Collection<T> convertAll(Class<T> token) {
        return this.convertAll(token, 1);
    }

    public <T> Collection<T> convertAll(Class<T> token, int amount) {
        return AgriCore.getConverter().convert(token, object, amount, useTag, Collections.emptyList(), nbt, ignoredNbt);
    }

    public boolean validate() {
        return AgriCore.getValidator().isValidObject(type, object, useTag, Collections.emptyList());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nObject:");
        sb.append("\n\t- Type:").append(type);
        sb.append("\n\t- Object: ").append(object);
        sb.append("\n\t- UseTag: ").append(useTag);
        sb.append("\n\t- Data: ").append(nbt);
        sb.append("\n\t- IgnoreData: ").append(ignoredNbt);
        return sb.toString();
    }
}
