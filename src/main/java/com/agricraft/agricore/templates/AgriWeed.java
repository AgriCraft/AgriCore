package com.agricraft.agricore.templates;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.templates.versions.Versions;
import com.agricraft.agricore.templates.versions.v3.Versions_1_18;
import com.google.common.collect.Lists;

import java.util.List;

public class AgriWeed implements AgriSerializable, Comparable<AgriWeed> {

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

    private final AgriProductList rake_drops;

    private final AgriRequirement requirement;

    private final AgriTexture texture;

    public AgriWeed() {
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
        this.rake_drops = new AgriProductList();
        this.requirement = new AgriRequirement();
        this.texture = new AgriTexture();
    }
    public AgriWeed(String id, String path, String weed_lang_key, String desc_lang_key, int[] stages, double spawn_chance,
                    double growth_chance, boolean aggressive, boolean lethal, AgriProductList rake_drops,
                    AgriRequirement requirement, AgriTexture texture, boolean enabled) {
        this(id, path, weed_lang_key, desc_lang_key, stages, spawn_chance, growth_chance, aggressive, lethal, rake_drops,
                requirement, texture, enabled, Lists.newArrayList("agricraft", "minecraft"));
    }

    public AgriWeed(String id, String path, String weed_lang_key, String desc_lang_key, int[] stages, double spawn_chance,
                    double growth_chance, boolean aggressive, boolean lethal, AgriProductList rake_drops,
                    AgriRequirement requirement, AgriTexture texture, boolean enabled, List<String> mods) {
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
        this.version = Versions_1_18.VERSION;
    }

    public String getId() {
        return id;
    }

    public String getWeedLangKey() {
        return weed_lang_key;
    }

    public String getDescLangKey() {
        return desc_lang_key;
    }

    public int getGrowthStages() {
        return this.stages.length;
    }

    public int getGrowthStageHeight(int stage) {
        return this.stages[stage];
    }

    public double getSpawnChance() {
        return this.spawn_chance;
    }

    public double getGrowthChance() {
        return this.growth_chance;
    }

    public boolean isAggressive() {
        return this.aggressive;
    }

    public boolean isLethal() {
        return this.lethal;
    }

    public AgriProductList getRakeDrops() {
        return this.rake_drops;
    }

    public AgriRequirement getRequirement() {
        return requirement;
    }

    public List<String> getSeasons() {
        return this.requirement.getSeasons();
    }

    public AgriTexture getTexture() {
        return texture;
    }

    public boolean validate() {
        if (!this.enabled) {
            AgriCore.getCoreLogger().info("Disabled Weed: {0}!", id);
            return false;
        } else if (!this.requirement.validate()) {
            AgriCore.getCoreLogger().info("Invalid Weed: {0}! Invalid Requirement!", id);
            return false;
        } else if (!this.rake_drops.validate()) {
            AgriCore.getCoreLogger().info("Invalid Weed: {0}! Invalid Rake Drops!", id);
            return false;
        } else if (!this.texture.validate()) {
            AgriCore.getCoreLogger().info("Invalid Weed: {0}! Invalid Texture!", id);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\n").append(id).append(":\n");
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
    public int compareTo(AgriWeed o) {
        return this.getId().compareTo(o.getId());
    }
}
