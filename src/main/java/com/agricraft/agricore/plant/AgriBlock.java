package com.agricraft.agricore.plant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AgriBlock extends AgriObject {
    protected final List<String> stateDefinition;

    public AgriBlock() {
        super();
        this.stateDefinition = Collections.emptyList();
    }

    public AgriBlock(String type, String block, boolean useTags, List<String> stateDefinition) {
        this(type, block, useTags, stateDefinition, "");

    }

    public AgriBlock(String type, String block, boolean useTags, List<String> stateDefinition, String nbt, String... ignoredNbt) {
        this(type, block, useTags, stateDefinition, nbt, Arrays.asList(ignoredNbt));
    }

    public AgriBlock(String type, String block, boolean useTags, List<String> stateDefinition, String nbt, List<String> ignoredNbt) {
        super("block", block, useTags, nbt, ignoredNbt);
        this.stateDefinition = stateDefinition;
    }

    public List<String> getStateDefinition() {
        return this.stateDefinition;
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
