/*
 */
package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.util.TypeHelper;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author RlonRyan
 */
public class AgriSoil implements AgriSerializable {

    private boolean enabled;
    private String path;

    private final String id;
    private final String name;
    private final List<String> varients;

    public AgriSoil() {
        this.id = "dirt_soil";
        this.name = "Dirt";
        this.varients = TypeHelper.asList("minecraft:dirt");
    }

    public AgriSoil(String id, String name, List<String> varients) {
        this.id = id;
        this.name = name;
        this.varients = varients;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Object> getVarients() {
        return this.varients.stream()
                .map(AgriCore.getConverter()::toStack)
                .filter(TypeHelper::isNonNull)
                .collect(Collectors.toList());
    }

    public boolean validate() {
        this.varients.removeIf(block -> {
            if (!AgriCore.getValidator().isValidBlock(block)) {
                AgriCore.getCoreLogger().info("Invalid Soil Varient: {0}! Removing!", block);
                return true;
            } else {
                return false;
            }
        });
        return !this.varients.isEmpty();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nSoil:\n");
        sb.append("\t- Id: ").append(id).append("\n");
        sb.append("\t- Name: ").append(name).append("\n");
        this.varients.forEach((e) -> {
            sb.append("\t- Block: ").append(e).append("\n");
        });
        return sb.toString();
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

}
