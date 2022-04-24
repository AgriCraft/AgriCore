package com.agricraft.agricore.plant.versions.v1;

import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.plant.AgriSoil;
import com.agricraft.agricore.plant.versions.v2.AgriSoil_1_16;
import com.agricraft.agricore.util.TypeHelper;
import java.util.List;
import java.util.stream.Collectors;

public class AgriSoil_1_12 implements AgriSerializable, Comparable<AgriSoil_1_12> {

    private String path;
    private final String version;
    
    private final boolean enabled;

    private final String id;
    private final String name;
    private final List<AgriStack_1_12> varients;

    public AgriSoil_1_12() {
        this.enabled = true;
        this.id = "dirt_soil";
        this.name = "Dirt";
        this.varients = TypeHelper.asList(new AgriStack_1_12());
        this.version = Versions_1_12.VERSION;
    }

    public AgriSoil_1_12(String id, String name, List<AgriStack_1_12> varients, boolean enabled) {
        this.id = id;
        this.name = name;
        this.varients = varients;
        this.enabled = enabled;
        this.version = Versions_1_12.VERSION;
    }

    public AgriSoil_1_16 toNew() {
        return new AgriSoil_1_16(
                this.id, this.name,
                this.varients.stream().map(stack -> stack.toNew("block")).collect(Collectors.toList()),
                "moist", "neutral", "medium", 1.0, enabled);
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean checkMods() {
        return false;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public int compareTo(AgriSoil_1_12 o) {
        return this.id.compareTo(o.id);
    }
}
