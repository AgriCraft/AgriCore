package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AgriStateObject extends AgriObject {
    protected final List<String> stateDefinition;

    public AgriStateObject() {
        super();
        this.stateDefinition = Collections.emptyList();
    }

    public AgriStateObject(String type, String object) {
        this(type, object, false);
    }

    public AgriStateObject(String type, String object, boolean useTags) {
        this(type, object, useTags, Collections.emptyList());
    }

    public AgriStateObject(String type, String block, boolean useTags, List<String> stateDefinition) {
        this(type, block, useTags, stateDefinition, "");
    }

    public AgriStateObject(String type, String block, boolean useTags, List<String> stateDefinition, String nbt, String... ignoredNbt) {
        this(type, block, useTags, stateDefinition, nbt, Arrays.asList(ignoredNbt));
    }

    public AgriStateObject(String type, String block, boolean useTags, List<String> stateDefinition, String nbt, List<String> ignoredNbt) {
        super(type, block, useTags, nbt, ignoredNbt);
        this.stateDefinition = stateDefinition;
    }

    public List<String> getStateDefinition() {
        return this.stateDefinition;
    }

    @Override
    public boolean validate() {
        return AgriCore.getValidator().isValidObject(type, object, useTag, this.getStateDefinition());
    }

    @Override
    public <T> Collection<T> convertAll(Class<T> token, int amount) {
        return AgriCore.getConverter().convert(token, object, amount, useTag, this.getStateDefinition(), nbt, ignoredNbt);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nBlock:");
        sb.append("\n\t- ").append(super.toString().replaceAll("\n", "\n\t").trim());
        sb.append("\n\t- State definition:");
        for(String s : this.getStateDefinition()) {
            sb.append("\n\t\t - ").append(s);
        }
        return sb.toString();
    }
}
