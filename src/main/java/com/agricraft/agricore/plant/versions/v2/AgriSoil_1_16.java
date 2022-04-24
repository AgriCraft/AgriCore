package com.agricraft.agricore.plant.versions.v2;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.plant.AgriStateObject;
import com.agricraft.agricore.plant.AgriSoil;
import com.agricraft.agricore.plant.versions.Versions;
import com.agricraft.agricore.util.TypeHelper;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class AgriSoil_1_16 implements AgriSerializable, Comparable<AgriSoil_1_16> {

    private String path;
    private final String version;
    private final String json_documentation = "https://agridocs.readthedocs.io/en/master/agri_soil/";

    private final boolean enabled;
    private final List<String> mods;
    private final String id;
    private final String lang_key;
    private final List<AgriObject_1_16> varients;

    private final String humidity;
    private final String acidity;
    private final String nutrients;

    private final double growth_modifier;

    public AgriSoil_1_16() {
        this.id = "dirt_soil";
        this.lang_key = "dirt";
        this.varients = TypeHelper.asList(new AgriObject_1_16());
        this.enabled = false;
        this.mods = Lists.newArrayList("agricraft", "minecraft");
        this.version = Versions.LATEST;
        this.humidity = "dry";
        this.acidity = "neutral";
        this.nutrients = "medium";
        this.growth_modifier = 1.0;
    }

    public AgriSoil_1_16(String id, String lang_key, List<AgriObject_1_16> varients, String humidity, String acidity, String nutrients, double growthMod, boolean enabled) {
        this(id, lang_key, varients, humidity, acidity, nutrients, growthMod, enabled, Lists.newArrayList("agricraft", "minecraft"));
    }

    public AgriSoil_1_16(String id, String lang_key, List<AgriObject_1_16> varients, String humidity, String acidity, String nutrients,
                         double growthMod, boolean enabled, List<String> mods) {
        this.id = id;
        this.lang_key = lang_key;
        this.varients = varients;
        this.enabled = enabled;
        this.humidity = humidity;
        this.acidity = acidity;
        this.nutrients = nutrients;
        this.growth_modifier = growthMod;
        this.mods = mods;
        this.version = Versions_1_16.VERSION;
    }

    public AgriSoil toNew() {
        return new AgriSoil(this.id, this.lang_key, this.convertVariants(), this.humidity, this.acidity, this.nutrients, this.growth_modifier, this.enabled, this.mods);
    }

    private List<AgriStateObject> convertVariants() {
        return this.varients.stream()
                .map(AgriObject_1_16::toBlock)
                .collect(Collectors.toList());
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
    public int compareTo(AgriSoil_1_16 o) {
        return this.id.compareTo(o.id);
    }
}
