package com.agricraft.agricore.plant.old.v1;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.plant.AgriObject;
import com.agricraft.agricore.util.TypeHelper;
import java.util.List;
import java.util.stream.Collectors;

public class AgriSoil_1_12 implements AgriSerializable {

    private String path;
    
    private final boolean enabled;

    private final String id;
    private final String name;
    private final List<AgriObject> varients;

    public AgriSoil_1_12() {
        this.enabled = true;
        this.id = "dirt_soil";
        this.name = "Dirt";
        this.varients = TypeHelper.asList(new AgriObject());
    }

    public AgriSoil_1_12(String id, String name, List<AgriObject> varients) {
        this.enabled = true;
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

    public List<AgriObject> getVarients() {
        return varients;
    }
    
    public <T> List<T> getVarients(Class<T> token) {
        return this.varients.stream()
                .flatMap(t -> t.convertAll(token).stream())
                .collect(Collectors.toList());
    }

    public boolean validate() {
        this.varients.removeIf(block -> {
            if (!block.validate()) {
                AgriCore.getCoreLogger().info("Invalid Soil Varient: {0}\nRemoving!", block);
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
    public void setPath(String path) {
        this.path = path;
    }

}
