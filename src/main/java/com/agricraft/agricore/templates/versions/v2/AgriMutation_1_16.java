package com.agricraft.agricore.templates.versions.v2;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.templates.AgriMutation;
import com.agricraft.agricore.templates.versions.Versions;
import com.agricraft.agricore.templates.versions.v3.Versions_1_18;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public class AgriMutation_1_16 implements AgriSerializable, Comparable<AgriMutation_1_16> {

    private String path;
    private final String version;
    private final String json_documentation = "https://agridocs.readthedocs.io/en/master/agri_mutation/";

    private final boolean enabled;
    private final List<String> mods;

    private final double chance;

    private final String child;

    private final String parent1;
    private final String parent2;

    public AgriMutation_1_16() {
        this.enabled = false;
        this.mods = Lists.newArrayList("agricraft", "minecraft");
        this.path = "default/default_mutation.json";
        this.chance = 0;
        this.child = "carrot_plant";
        this.parent1 = "wheat_plant";
        this.parent2 = "potato_plant";
        this.version = Versions.LATEST;
    }

    public AgriMutation_1_16(double chance, String child, String parent1, String parent2, String path, boolean enabled) {
        this(chance, child, parent1, parent2, path, enabled, Lists.newArrayList("agricraft", "minecraft"));
    }

    public AgriMutation_1_16(double chance, String child, String parent1, String parent2, String path, boolean enabled, List<String> mods) {
        this.enabled = enabled;
        this.mods = mods;
        this.path = path;
        this.chance = chance;
        this.child = child;
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.version = Versions_1_18.VERSION;
    }

    public AgriMutation toNew() {
        return new AgriMutation(this.chance, this.child, this.parent1, this.parent2, Collections.emptyList(), this.path, this.enabled, this.mods);
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
    public int compareTo(AgriMutation_1_16 o) {
        return this.getPath().compareTo(o.getPath());
    }
}
