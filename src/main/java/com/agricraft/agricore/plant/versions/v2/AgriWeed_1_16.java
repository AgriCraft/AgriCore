package com.agricraft.agricore.plant.versions.v2;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.plant.AgriTexture;
import com.agricraft.agricore.plant.AgriWeed;
import com.agricraft.agricore.plant.versions.Versions;
import com.google.common.collect.Lists;

import java.util.List;

public class AgriWeed_1_16 implements AgriSerializable, Comparable<AgriWeed_1_16> {

    private String path;
    private final String version;
    private final boolean enabled;
    private final List<String> mods;

    private final String id;

    private final String weed_lang_key;
    private final String desc_lang_key;

    private final int[] stages;
    private final double spawn_chance;
    private final double growth_chance;
    private final boolean aggressive;
    private final boolean lethal;

    private final AgriProductList_1_16 rake_drops;

    private final AgriRequirement_1_16 requirement;

    private final AgriTexture texture;

    public AgriWeed_1_16() {
        this.path = "default/weed_weed.json";
        this.version = Versions.LATEST;
        this.enabled = false;
        this.mods = Lists.newArrayList("agricraft", "minecraft");
        this.id = "weed_weed";
        this.weed_lang_key = "weeds";
        this.desc_lang_key = "";
        this.stages = new int[]{2,4,6,8,10,12,14,16};
        this.spawn_chance = 0.25;
        this.growth_chance = 0.9;
        this.aggressive = true;
        this.lethal = true;
        this.rake_drops = new AgriProductList_1_16();
        this.requirement = new AgriRequirement_1_16();
        this.texture = new AgriTexture();
    }
    public AgriWeed_1_16(String id, String path, String weed_lang_key, String desc_lang_key, int[] stages, double spawn_chance,
                         double growth_chance, boolean aggressive, boolean lethal, AgriProductList_1_16 rake_drops,
                         AgriRequirement_1_16 requirement, AgriTexture texture, boolean enabled) {
        this(id, path, weed_lang_key, desc_lang_key, stages, spawn_chance, growth_chance, aggressive, lethal, rake_drops,
                requirement, texture, enabled, Lists.newArrayList("agricraft", "minecraft"));
    }

    public AgriWeed_1_16(String id, String path, String weed_lang_key, String desc_lang_key, int[] stages, double spawn_chance,
                         double growth_chance, boolean aggressive, boolean lethal, AgriProductList_1_16 rake_drops,
                         AgriRequirement_1_16 requirement, AgriTexture texture, boolean enabled, List<String> mods) {
        this.id = id;
        this.path = path;
        this.enabled = enabled;
        this.mods = mods;
        this.weed_lang_key = weed_lang_key;
        this.desc_lang_key = desc_lang_key;
        this.stages = stages;
        this.spawn_chance =spawn_chance;
        this.growth_chance = growth_chance;
        this.aggressive = aggressive;
        this.lethal = lethal;
        this.rake_drops = rake_drops;
        this.requirement = requirement;
        this.texture = texture;
        this.version = Versions_1_16.VERSION;
    }

    public AgriWeed toNew() {
        return new AgriWeed(this.id, this.path, this.weed_lang_key, this.desc_lang_key, this.stages, this.spawn_chance,
                this.growth_chance, this.aggressive, this.lethal, this.rake_drops.toNew(), this.requirement.toNew(), this.texture, this.enabled, this.mods);
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
    public int compareTo(AgriWeed_1_16 o) {
        return this.id.compareTo(o.id);
    }
}
