package com.agricraft.agricore.templates.versions.v2;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.templates.*;
import com.agricraft.agricore.templates.AgriParticleEffect;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AgriPlant_1_16 implements AgriSerializable, Comparable<AgriPlant_1_16> {

    private String path;
    private final String version;
    private final String json_documentation = "https://agridocs.readthedocs.io/en/master/agri_plant/";

    private final boolean enabled;
    private final List<String> mods;

    private final String id;

    private final String plant_lang_key;
    private final String seed_lang_key;
    private final String desc_lang_key;
    private final List<AgriSeed_1_16> seed_items;

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

    private final AgriProductList_1_16 products;
    private final AgriProductList_1_16 clip_products;
    private final AgriRequirement_1_16 requirement;
    private final List<JsonElement> callbacks;
    private final AgriTexture texture;
    private final String seed_texture;
    private final String seed_model;
    private final List<AgriParticleEffect> particle_effects;

    public AgriPlant_1_16() {
        this.enabled = false;
        this.mods = Lists.newArrayList("agricraft", "minecraft");
        this.path = "default/weed_plant.json";
        this.version = Versions_1_16.VERSION;
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
        this.products = new AgriProductList_1_16();
        this.clip_products = new AgriProductList_1_16();
        this.requirement = new AgriRequirement_1_16();
        this.callbacks = new ArrayList<>();
        this.texture = new AgriTexture();
        this.seed_texture = "minecraft:item/wheat_seeds";
        this.seed_model = "minecraft:item/wheat_seeds";
        this.particle_effects = new ArrayList<>();
    }

    public AgriPlant_1_16(String id, String plant_lang_key, String seed_lang_key, String desc_lang_key, List<AgriSeed_1_16> seed_items, int[] stages, int harvestStage,
                          int tier, double growth_chance, double growth_bonus, boolean cloneable,
                          double spread_chance, double grass_drop_chance, double seed_drop_chance, double seed_drop_bonus,
                          AgriProductList_1_16 products, AgriProductList_1_16 clip_products, AgriRequirement_1_16 requirement,
                          List<JsonElement> callbacks, AgriTexture texture, String seed_texture, String seed_model, List<AgriParticleEffect> particle_effects, String path, boolean enabled) {

        this(id, plant_lang_key, seed_lang_key, desc_lang_key, seed_items, stages, harvestStage, tier, growth_chance,
                growth_bonus, cloneable, spread_chance, grass_drop_chance, seed_drop_chance, seed_drop_bonus,
                products, clip_products, requirement, callbacks, texture, seed_texture, seed_model, particle_effects, path, enabled,
                Lists.newArrayList("agricraft", "minecraft"));
    }

    public AgriPlant_1_16(String id, String plant_lang_key, String seed_lang_key, String desc_lang_key, List<AgriSeed_1_16> seed_items, int[] stages, int harvestStage,
                          int tier, double growth_chance, double growth_bonus, boolean cloneable,
                          double spread_chance, double grass_drop_chance, double seed_drop_chance, double seed_drop_bonus,
                          AgriProductList_1_16 products, AgriProductList_1_16 clip_products, AgriRequirement_1_16 requirement,
                          List<JsonElement> callbacks, AgriTexture texture, String seed_texture,
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
        this.texture = texture;
        this.seed_texture = seed_texture;
        this.seed_model = seed_model;
        this.particle_effects = particle_effects;
        this.version = Versions_1_16.VERSION;
    }

    public AgriPlant toNew() {
        return new AgriPlant(this.id, this.plant_lang_key, this.seed_lang_key, this.desc_lang_key, this.convertSeeds(), this.stages, this.harvestStage,
                this.tier, this.growth_chance, this.growth_bonus, this.cloneable, this.spread_chance, this.grass_drop_chance, this.seed_drop_chance,
                this.seed_drop_bonus, this.products.toNew(), this.clip_products.toNew(), this.requirement.toNew(), this.callbacks, this.texture, this.seed_texture,
                this.seed_model, this.particle_effects, this.path, this.enabled, this.mods);
    }

    private List<AgriSeed> convertSeeds() {
        return this.seed_items.stream()
                .map(AgriSeed_1_16::toNew)
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
    public int compareTo(AgriPlant_1_16 o) {
        return this.id.compareTo(o.id);
    }
}
