package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.plant.versions.v2.Versions_1_16;
import com.agricraft.agricore.util.TypeHelper;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class AgriSoil implements AgriSerializable, Comparable<AgriSoil> {
    private String path;
    private final String version;
    private final boolean enabled;
    private final List<String> mods;
    private final String id;
    private final String lang_key;
    private final List<AgriObject> varients;

    public AgriSoil() {
        this.id = "dirt_soil";
        this.lang_key = "dirt";
        this.varients = TypeHelper.asList(new AgriObject());
        this.enabled = false;
        this.mods = Lists.newArrayList("agricraft", "minecraft");
        this.version = Versions_1_16.VERSION;
    }

    public AgriSoil(String id, String lang_key, List<AgriObject> varients, boolean enabled) {
        this(id, lang_key, varients, enabled, Lists.newArrayList("agricraft", "minecraft"));
    }

    public AgriSoil(String id, String lang_key, List<AgriObject> varients, boolean enabled, List<String> mods) {
        this.id = id;
        this.lang_key = lang_key;
        this.varients = varients;
        this.enabled = enabled;
        this.mods = mods;
        this.version = Versions_1_16.VERSION;
    }

    public String getId() {
        return id;
    }

    public String getLangKey() {
        return lang_key;
    }

    public <T> List<T> getVariants(Class<T> token) {
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
        sb.append("\t- Name: ").append(lang_key).append("\n");
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
    public boolean checkMods() {
        return this.mods.stream().allMatch(mod -> AgriCore.getValidator().isValidMod(mod));
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
    public int compareTo(AgriSoil o) {
        return this.id.compareTo(o.id);
    }
}
