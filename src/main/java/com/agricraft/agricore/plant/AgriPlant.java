package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.lang.AgriString;
import com.agricraft.agricore.plant.versions.v2.Versions_1_16;
import com.google.common.base.Preconditions;

import java.util.Collection;

public class AgriPlant implements AgriSerializable, Comparable<AgriPlant> {

    private String path;
    private final String version;

    private final boolean enabled;

    private final String id;

    private final AgriString plant_name;
    private final AgriString seed_name;
    private final AgriSeed seed_items;
    private final AgriString description;

    private final int stages;
    private final int harvestStage;

    private final double growth_chance;
    private final double growth_bonus;
    private final boolean bonemeal;
    private final int tier;

    private final boolean cloneable;
    private final double spread_chance;
    private final double grass_drop_chance;
    private final double seed_drop_chance;
    private final double seed_drop_bonus;

    private final AgriProductList products;
    private final AgriRequirement requirement;
    private final AgriTexture texture;

    public AgriPlant() {
        this.enabled = false;
        this.path = "default/weed_plant.json";
        this.version = Versions_1_16.VERSION;
        this.id = "weed_plant";
        this.plant_name = new AgriString("Weed");
        this.seed_name = new AgriString("Weed Seeds");
        this.seed_items = new AgriSeed();
        this.description = new AgriString("An annoying plant.");
        this.stages = 8;
        this.harvestStage = 4;
        this.bonemeal = true;
        this.tier = 1;
        this.cloneable = true;
        this.growth_chance = 0.9;
        this.growth_bonus = 0.025;
        this.spread_chance = 0.1;
        this.grass_drop_chance = 0.0;
        this.seed_drop_chance = 1.0;
        this.seed_drop_bonus = 0.0;
        this.products = new AgriProductList();
        this.requirement = new AgriRequirement();
        this.texture = new AgriTexture();
    }

    public AgriPlant(String id, AgriString plant_name, AgriString seed_name, AgriSeed seed_items, AgriString description, int stages, int harvestStage,
                     boolean bonemeal, int tier, double growth_chance, double growth_bonus, boolean cloneable,
                     double spread_chance, double grass_drop_chance, double seed_drop_chance,
                     double seed_drop_bonus, AgriProductList products, AgriRequirement requirement, AgriTexture texture,
                     String path, boolean enabled) {

        Preconditions.checkArgument(stages > 0, "The number of stages must be larger than 0");
        Preconditions.checkArgument(harvestStage >= 0, "The harvest index can not be negative");
        Preconditions.checkArgument(harvestStage < stages, "The harvest index must be smaller than the number of stages");

        this.enabled = enabled;
        this.path = path;
        this.id = id;
        this.plant_name = plant_name;
        this.seed_name = seed_name;
        this.seed_items = seed_items;
        this.description = description;
        this.stages = stages;
        this.harvestStage = harvestStage;
        this.bonemeal = bonemeal;
        this.tier = tier;
        this.growth_chance = growth_chance;
        this.growth_bonus = growth_bonus;
        this.spread_chance = spread_chance;
        this.grass_drop_chance = grass_drop_chance;
        this.seed_drop_chance = seed_drop_chance;
        this.seed_drop_bonus = seed_drop_bonus;
        this.products = products;
        this.cloneable = cloneable;
        this.requirement = requirement;
        this.texture = texture;
        this.version = Versions_1_16.VERSION;
    }
    
    public String getId() {
        return id;
    }

    public String getPlantName() {
        return plant_name.toString();
    }

    public String getSeedName() {
        return seed_name.toString();
    }

    public AgriSeed getSeed() {
        return this.seed_items;
    }

    public Collection<AgriObject> getSeedItems() {
        return seed_items.getSeeds();
    }

    public int getGrowthStages() {
        return this.stages;
    }

    public int getStageAfterHarvest() {
        return this.harvestStage;
    }

    public AgriString getDescription() {
        return description;
    }

    public AgriProductList getProducts() {
        return products;
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

    public int getTier() {
        return tier;
    }

    public boolean canBonemeal() {
        return bonemeal;
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

    public boolean validate() {
        if (!this.enabled) {
            AgriCore.getCoreLogger().debug("Disabled Plant: {0}!", id);
            return false;
        } else if (!this.requirement.validate()) {
            AgriCore.getCoreLogger().debug("Invalid Plant: {0}! Invalid Requirement!", id);
            return false;
        } else if (!this.products.validate()) {
            AgriCore.getCoreLogger().debug("Invalid Plant: {0}! Invalid Product!", id);
            return false;
        } else if (!this.texture.validate()) {
            AgriCore.getCoreLogger().debug("Invalid Plant: {0}! Invalid Texture!", id);
            return false;
        } else if(!this.seed_items.validate()) {
            AgriCore.getCoreLogger().debug("Invalid Plant: {0}! No valid seed!", id);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\n").append(id).append(":\n");
        sb.append("\t- Bonemeal: ").append(bonemeal).append("\n");
        sb.append("\t- Growth Chance: ").append(growth_chance).append("\n");
        sb.append("\t- Growth Bonus: ").append(growth_bonus).append("\n");
        sb.append("\t- Seed Drop Chance: ").append(seed_drop_chance).append("\n");
        sb.append("\t- Seed Drop Bonus: ").append(seed_drop_bonus).append("\n");
        sb.append("\t- Grass Drop Chance: ").append(grass_drop_chance).append("\n");
        sb.append("\t- ").append(products.toString().replaceAll("\n", "\n\t").trim()).append("\n");
        sb.append("\t- ").append(requirement.toString().replaceAll("\n", "\n\t").trim()).append("\n");
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

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public int compareTo(AgriPlant o) {
        return this.getId().compareTo(o.getId());
    }
}
