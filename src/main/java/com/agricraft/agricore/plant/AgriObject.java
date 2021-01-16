package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AgriObject {
    protected final String type;
    protected final String object;
    protected final boolean useTag;
    protected final String data;
    protected final List<String> ignoredData;

    public AgriObject() {
        this("block", "minecraft:dirt");
    }
    
    public AgriObject(String type, String object) {
        this(type, object,false);
    }
    
    public AgriObject(String type, String object, boolean useTags) {
        this(type, object, useTags, "");
    }

    public AgriObject(String type, String object, boolean useTags, String data, String... ignoredData) {
        this(type, object, useTags, data, Arrays.asList(ignoredData));
    }

    public AgriObject(String type, String object, boolean useTags, String data, List<String> ignoredData) {
        this.type = type;
        this.object = object;
        this.useTag = useTags;
        this.data = data;
        this.ignoredData = ignoredData;
    }

    public String getObjectString() {
        return object;
    }

    public boolean useTag() {
        return useTag;
    }

    public String getData() {
        return data;
    }

    public List<String> getIgnoredData() {
        return ignoredData;
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
        return AgriCore.getConverter().convert(token, object, amount, useTag, data, ignoredData);
    }

    public boolean validate() {
        return AgriCore.getValidator().isValidObject(type, object);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nObject:");
        sb.append("\n\t- Type:").append(type);
        sb.append("\n\t- Object: ").append(object);
        sb.append("\n\t- UseTag: ").append(useTag);
        sb.append("\n\t- Data: ").append(data);
        sb.append("\n\t- IgnoreData: ").append(ignoredData);
        return sb.toString();
    }
}
