package com.agricraft.agricore.plant.versions.v1;

import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.lang.AgriString;
import com.agricraft.agricore.plant.AgriPlant;
import com.agricraft.agricore.plant.AgriTexture;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AgriPlant_1_12 implements AgriSerializable {

    private String path;
    private final String version;
    
    private final boolean enabled;

    private final String id;
    private final String plant_name;
    private final String seed_name;
    private final List<AgriStack_1_12> seed_items;
    private final AgriString description;

    private final double growth_chance;
    private final double growth_bonus;
    private final boolean bonemeal;
    private final int tier;

    private final boolean weedable;
    private final boolean aggressive;
    private final double spread_chance;
    private final double spawn_chance;
    private final double grass_drop_chance;
    private final double seed_drop_chance;
    private final double seed_drop_bonus;

    private final AgriProductList_1_12 products;
    private final AgriRequirement_1_12 requirement;
    private final AgriTexture texture;

    public AgriPlant_1_12() {
        this.enabled = false;
        this.path = "default/weed_plant.json";
        this.id = "weed_plant";
        this.plant_name = "Weed";
        this.seed_name = "Weed Seeds";
        this.seed_items = new ArrayList<>();
        this.description = new AgriString("An annoying plant.");
        this.bonemeal = true;
        this.tier = 1;
        this.growth_chance = 0.9;
        this.growth_bonus = 0.025;
        this.weedable = false;
        this.aggressive = false;
        this.spread_chance = 0.1;
        this.spawn_chance = 0;
        this.grass_drop_chance = 0.0;
        this.seed_drop_chance = 1.0;
        this.seed_drop_bonus = 0.0;
        this.products = new AgriProductList_1_12();
        this.requirement = new AgriRequirement_1_12();
        this.texture = new AgriTexture();
        this.version = Versions_1_12.VERSION;
    }

    public AgriPlant_1_12(String id, String plant_name, String seed_name, List<AgriStack_1_12> seed_items, AgriString description,
                          boolean bonemeal, int tier, double growth_chance, double growth_bonus, boolean weedable, boolean agressive,
                          double spread_chance, double spawn_chance, double grass_drop_chance, double seed_drop_chance, double seed_drop_bonus,
                          AgriProductList_1_12 products, AgriRequirement_1_12 requirement, AgriTexture texture, String path, boolean enabled) {
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
        this.growth_bonus = growth_bonus;
        this.weedable = weedable;
        this.aggressive = agressive;
        this.spread_chance = spread_chance;
        this.spawn_chance = spawn_chance;
        this.grass_drop_chance = grass_drop_chance;
        this.seed_drop_chance = seed_drop_chance;
        this.seed_drop_bonus = seed_drop_bonus;
        this.products = products;
        this.requirement = requirement;
        this.texture = texture;
        this.version = Versions_1_12.VERSION;
    }

    public AgriPlant toNew() {
        return new AgriPlant(this.id, new AgriString(this.plant_name), new AgriString(this.seed_name), this.seed_items.stream().map(stack -> stack.toNew("item")).collect(Collectors.toList()),
                this.description, this.texture.getGrowthStages(), this.texture.getGrowthStages()/2, this.bonemeal, this.tier, this.growth_chance,
                this.growth_bonus, this.isCloneable(), this.spread_chance, this.grass_drop_chance, this.seed_drop_chance, this.seed_drop_bonus, this.products.toNew(),
                this.requirement.toNew(), this.texture, this.path, this.enabled);
    }

    protected boolean isCloneable() {
        return !this.getPath().contains("resource");
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
}