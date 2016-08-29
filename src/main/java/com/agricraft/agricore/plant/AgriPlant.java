/*
 */
package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.lang.AgriString;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author RlonRyan
 */
public class AgriPlant implements AgriSerializable {

    private boolean enabled;
    private String path;

    private final String id;
    private final String plant_name;
    private final String seed_name;
    private final List<String> seed_items;
    private final AgriString description;

    private final double growth_chance;
    private final boolean bonemeal;
    private final int tier;

    private final boolean weedable;
    private final boolean aggressive;
    private final double spread_chance;
    private final double spawn_chance;

    private final AgriProductList products;
    private final AgriRequirement requirement;
    private final AgriTexture texture;

    public AgriPlant() {
        this.enabled = false;
        this.path = "default/weed_plant.json";
        this.id = "weed_plant";
        this.plant_name = "Weed";
        this.seed_name = "Weed Seeds";
        this.seed_items = Arrays.asList("agricraft:agri_seed");
        this.description = new AgriString("An annoying plant.");
        this.bonemeal = true;
        this.tier = 1;
        this.growth_chance = 0.9;
        this.weedable = false;
        this.aggressive = false;
        this.spread_chance = 0.1;
        this.spawn_chance = 0;
        this.products = new AgriProductList();
        this.requirement = new AgriRequirement();
        this.texture = new AgriTexture();
    }

    public AgriPlant(String id, String plant_name, String seed_name, List<String> seed_items, AgriString description, boolean bonemeal, int tier, double growth_chance, boolean weedable, boolean agressive, double spread_chance, double spawn_chance, AgriProductList products, AgriRequirement requirement, AgriTexture texture, String path, boolean enabled) {
        this.enabled = enabled;
        this.path = path;
        this.id = id;
        this.plant_name = plant_name;
        this.seed_name = seed_name;
        this.seed_items = seed_items;
        this.description = description;
        this.bonemeal = bonemeal;
        this.tier = tier;
        this.growth_chance = growth_chance;
        this.weedable = weedable;
        this.aggressive = agressive;
        this.spread_chance = spread_chance;
        this.spawn_chance = spawn_chance;
        this.products = products;
        this.requirement = requirement;
        this.texture = texture;
    }

    public String getId() {
        return id;
    }

    public String getPlantName() {
        return plant_name;
    }

    public String getSeedName() {
        return seed_name;
    }

    public Collection<String> getSeedItems() {
        return seed_items;
    }

    public AgriString getDescription() {
        return description;
    }

    public AgriProductList getProducts() {
        return products;
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

    public boolean isWeedable() {
        return weedable;
    }

    public boolean isAgressive() {
        return aggressive;
    }

    public double getSpawnChance() {
        return spawn_chance;
    }

    public double getSpreadChance() {
        return spread_chance;
    }

    public double getGrowthChance() {
        return growth_chance;
    }

    public boolean shouldSpawn(Random rand) {
        return spawn_chance > rand.nextDouble();
    }

    public boolean shouldSpread(Random rand) {
        return spread_chance > rand.nextDouble();
    }

    public boolean shouldGrow(Random rand) {
        return growth_chance > rand.nextDouble();
    }

    public boolean validate() {
        if (!this.requirement.validate()) {
            AgriCore.getCoreLogger().debug("Invalid Plant: {0}! Invalid Requirement!", id);
            return false;
        } else if (!this.products.validate()) {
            AgriCore.getCoreLogger().debug("Invalid Plant: {0}! Invalid Product!", id);
            return false;
        } else if (!this.texture.validate()) {
            AgriCore.getCoreLogger().debug("Invalid Plant: {0}! Invalid Texture!", id);
            return false;
        }
        this.seed_items.removeIf(s -> !AgriCore.getValidator().isValidItem(s));
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\n").append(id).append(":\n");
        sb.append("\t- Plant Name: ").append(plant_name).append("\n");
        sb.append("\t- Seed Name: ").append(seed_name).append("\n");
        sb.append("\t- Bonemeal: ").append(bonemeal).append("\n");
        sb.append("\t- Growth Chance: ").append(growth_chance).append("\n");
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
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

}
