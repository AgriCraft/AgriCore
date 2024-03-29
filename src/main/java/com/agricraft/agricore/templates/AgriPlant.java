package com.agricraft.agricore.templates;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.templates.versions.Versions;
import com.agricraft.agricore.templates.versions.v3.Versions_1_18;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class AgriPlant implements AgriSerializable, Comparable<AgriPlant> {

    private String path;
    private final String version;
    private final String json_documentation = "https://agridocs.readthedocs.io/en/master/agri_plant/";

    private final boolean enabled;
    private final List<String> mods;

    private final String id;

    private final String plant_lang_key;
    private final String seed_lang_key;
    private final String desc_lang_key;
    private final List<AgriSeed> seed_items;

    private final int[] stages;
    private final int harvestStage;

    private final double growth_chance;
    private final double growth_bonus;
    private final int tier;

    private final boolean cloneable;
    private final double spread_chance;
    private final double grass_drop_chance;
    private final double seed_drop_chance;
    private final double seed_drop_bonus;

    private final AgriProductList products;
    private final AgriProductList clip_products;
    private final AgriRequirement requirement;
    private final List<JsonElement> callbacks;
    private final List<AgriStructureGenSettings> structure_generation;

    private final AgriTexture texture;
    private final String seed_texture;
    private final String seed_model;
    private final List<AgriParticleEffect> particle_effects;

    public AgriPlant() {
        this.enabled = false;
        this.mods = Lists.newArrayList("agricraft", "minecraft");
        this.path = "default/weed_plant.json";
        this.version = Versions.LATEST;
        this.id = "weed_plant";
        this.plant_lang_key = "agricraft.plant.weeds";
        this.seed_lang_key = "agricraft.seed.weeds";
        this.desc_lang_key = "agricraft.plant.weeds.desc";
        this.seed_items = Lists.newArrayList();
        this.stages = new int[]{2,4,6,8,10,12,14,16};
        this.harvestStage = 4;
        this.tier = 1;
        this.cloneable = true;
        this.growth_chance = 0.9;
        this.growth_bonus = 0.025;
        this.spread_chance = 0.1;
        this.grass_drop_chance = 0.0;
        this.seed_drop_chance = 1.0;
        this.seed_drop_bonus = 0.0;
        this.products = new AgriProductList();
        this.clip_products = new AgriProductList();
        this.requirement = new AgriRequirement();
        this.callbacks = new ArrayList<>();
        this.structure_generation = new ArrayList<>();
        this.texture = new AgriTexture();
        this.seed_texture = "minecraft:item/wheat_seeds";
        this.seed_model = "minecraft:item/wheat_seeds";
        this.particle_effects = new ArrayList<>();
    }

    public AgriPlant(String id, String plant_lang_key, String seed_lang_key, String desc_lang_key, List<AgriSeed> seed_items, int[] stages, int harvestStage,
                     int tier, double growth_chance, double growth_bonus, boolean cloneable,
                     double spread_chance, double grass_drop_chance, double seed_drop_chance, double seed_drop_bonus,
                     AgriProductList products, AgriProductList clip_products, AgriRequirement requirement,
                     List<JsonElement> callbacks, List<AgriStructureGenSettings> structureGenSettings, AgriTexture texture,
                     String seed_texture, String seed_model, List<AgriParticleEffect> particle_effects, String path, boolean enabled) {

        this(id, plant_lang_key, seed_lang_key, desc_lang_key, seed_items, stages, harvestStage, tier, growth_chance,
                growth_bonus, cloneable, spread_chance, grass_drop_chance, seed_drop_chance, seed_drop_bonus,
                products, clip_products, requirement, callbacks, structureGenSettings, texture, seed_texture, seed_model, particle_effects, path, enabled,
                Lists.newArrayList("agricraft", "minecraft"));
    }

    public AgriPlant(String id, String plant_lang_key, String seed_lang_key, String desc_lang_key, List<AgriSeed> seed_items, int[] stages, int harvestStage,
                     int tier, double growth_chance, double growth_bonus, boolean cloneable,
                     double spread_chance, double grass_drop_chance, double seed_drop_chance, double seed_drop_bonus,
                     AgriProductList products, AgriProductList clip_products, AgriRequirement requirement,
                     List<JsonElement> callbacks, List<AgriStructureGenSettings> structureGenSettings, AgriTexture texture, String seed_texture,
                     String seed_model, List<AgriParticleEffect> particle_effects, String path, boolean enabled, List<String> mods) {

        Preconditions.checkArgument(stages.length > 0, "The number of stages must be larger than 0");
        Preconditions.checkArgument(harvestStage >= 0, "The harvest index can not be negative");
        Preconditions.checkArgument(harvestStage < stages.length, "The harvest index must be smaller than the number of stages");

        this.enabled = enabled;
        this.mods = mods;
        this.path = path;
        this.id = id;
        this.plant_lang_key = plant_lang_key;
        this.seed_lang_key = seed_lang_key;
        this.desc_lang_key = desc_lang_key;
        this.seed_items = seed_items;
        this.stages = stages;
        this.harvestStage = harvestStage;
        this.tier = tier;
        this.growth_chance = growth_chance;
        this.growth_bonus = growth_bonus;
        this.spread_chance = spread_chance;
        this.grass_drop_chance = grass_drop_chance;
        this.seed_drop_chance = seed_drop_chance;
        this.seed_drop_bonus = seed_drop_bonus;
        this.products = products;
        this.clip_products = clip_products;
        this.cloneable = cloneable;
        this.requirement = requirement;
        this.callbacks = callbacks;
        this.structure_generation = structureGenSettings;
        this.texture = texture;
        this.seed_texture = seed_texture;
        this.seed_model = seed_model;
        this.particle_effects = particle_effects;
        this.version = Versions_1_18.VERSION;
    }

    public String getId() {
        return id;
    }

    public String getPlantLangKey() {
        return plant_lang_key;
    }

    public String getSeedLangKey() {
        return seed_lang_key;
    }

    public String getDescLangKey() {
        return desc_lang_key;
    }

    public List<AgriSeed> getSeeds() {
        return this.seed_items;
    }

    public int getGrowthStages() {
        return this.stages.length;
    }

    public int getGrowthStageHeight(int stage) {
        return this.stages[stage];
    }

    public int getStageAfterHarvest() {
        return this.harvestStage;
    }

    public AgriProductList getProducts() {
        return products;
    }

    public AgriProductList getClipProducts() {
        return clip_products;
    }

    public boolean allowsCloning() {
        return this.cloneable;
    }

    public AgriRequirement getRequirement() {
        return requirement;
    }

    public AgriTexture getTexture() {
        return texture;
    }

    public String getSeedTexture() {
        return this.seed_texture;
    }

    public String getSeedModel() {
        return this.seed_model;
    }

    public List<AgriParticleEffect> getParticleEffects() {
        return this.particle_effects;
    }

    public int getTier() {
        return tier;
    }

    public double getSpreadChance() {
        return spread_chance;
    }

    public double getGrowthChance() {
        return growth_chance;
    }

    public double getGrowthBonus() {
        return growth_bonus;
    }

    public double getGrassDropChance() {
        return grass_drop_chance;
    }

    public double getSeedDropChance() {
        return seed_drop_chance;
    }

    public double getSeedDropBonus() {
        return seed_drop_bonus;
    }

    public List<JsonElement> getCallbacks() {
        return callbacks;
    }

    public List<AgriStructureGenSettings> getStructureGenSettings() {
        return structure_generation;
    }

    public boolean validate() {
        if (!this.enabled) {
            AgriCore.getCoreLogger().info("Disabled Plant: {0}!", id);
            return false;
        } else if (!this.requirement.validate()) {
            AgriCore.getCoreLogger().info("Invalid Plant: {0}! Invalid Requirement!", id);
            return false;
        } else if (!this.products.validate()) {
            AgriCore.getCoreLogger().info("Invalid Plant: {0}! Invalid Product!", id);
            return false;
        } else if (!this.clip_products.validate()) {
            AgriCore.getCoreLogger().info("Invalid Plant: {0}! Invalid Clip Product!", id);
            return false;
        } else if (!this.texture.validate()) {
            AgriCore.getCoreLogger().info("Invalid Plant: {0}! Invalid Texture!", id);
            return false;
        }
        if (!AgriCore.getValidator().isValidResource(seed_model)) {
            AgriCore.getCoreLogger().info("Invalid AgriTexture! Invalid Seed Model: \"{0}\"!", seed_model);
            return false;
        }
        this.particle_effects.removeIf(particleEffect -> !particleEffect.validate());
        this.seed_items.removeIf(seed -> !seed.validate());
        this.callbacks.removeIf(callback -> !AgriCore.getValidator().isValidPlantCallback(callback));
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\n").append(id).append(":\n");
        sb.append("\t- Growth Chance: ").append(growth_chance).append("\n");
        sb.append("\t- Growth Bonus: ").append(growth_bonus).append("\n");
        sb.append("\t- Seed Drop Chance: ").append(seed_drop_chance).append("\n");
        sb.append("\t- Seed Drop Bonus: ").append(seed_drop_bonus).append("\n");
        sb.append("\t- Grass Drop Chance: ").append(grass_drop_chance).append("\n");
        sb.append("\t- ").append(products.toString().replaceAll("\n", "\n\t").trim()).append("\n");
        sb.append("\t- ").append(requirement.toString().replaceAll("\n", "\n\t").trim()).append("\n");
        sb.append("\t- Particle Effect: \n\t").append(listToString(particle_effects).replaceAll("\n", "\n\t").trim()).append("\n");
        return sb.toString();
    }

    private String listToString(List<?> list) {
        return list.stream().collect(StringBuilder::new, (builder, item) -> builder.append("\n\t").append(item), StringBuilder::append).toString();
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
    public int compareTo(AgriPlant o) {
        return this.getId().compareTo(o.getId());
    }
}
