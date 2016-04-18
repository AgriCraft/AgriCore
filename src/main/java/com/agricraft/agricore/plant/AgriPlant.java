/*
 */
package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import java.util.Random;

/**
 *
 * @author RlonRyan
 */
public class AgriPlant {
	
	public final String name;
	
	public final String id;
	
	public final String description;
	
	public final boolean bonemeal;
	
	public final int tier;
	
	public final int growth_chance;

	public final AgriProductList products;

	public final AgriRequirement requirement;
	
	public final AgriTexture texture;

	public AgriPlant() {
		this.name = "Weed";
		this.id = "weed_plant";
		this.description = "An annoying plant.";
		this.bonemeal = true;
		this.tier = 1;
		this.growth_chance = 900;
		this.products = new AgriProductList();
		this.requirement = new AgriRequirement();
		this.texture = new AgriTexture();
	}

	public AgriPlant(String name, String id, String description, boolean bonemeal, int tier, int growth_chance, AgriProductList products, AgriRequirement requirement, AgriTexture texture) {
		this.name = name;
		this.id = id;
		this.description = description;
		this.bonemeal = bonemeal;
		this.tier = tier;
		this.growth_chance = growth_chance;
		this.products = products;
		this.requirement = requirement;
		this.texture = texture;
	}
	
	public boolean shouldGrow(Random rand) {
		return rand.nextInt(1000) < this.growth_chance;
	}

	public boolean validate() {
		if (!this.requirement.validate()) {
			AgriCore.getLogger().debug("Invalid Plant! Invalid Requirement!");
			return false;
		} else if (!this.products.validate()) {
			AgriCore.getLogger().debug("Invalid Plant! Invalid Product!");
			return false;
		} else if (!this.texture.validate()) {
			AgriCore.getLogger().debug("Invalid Plant! Invalid Texture!");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\n").append(name).append(":\n");
		sb.append("\t- Id: ").append(id).append("\n");
		sb.append("\t- Bonemeal: ").append(bonemeal).append("\n");
		sb.append("\t- Growth Chance: ").append(growth_chance).append("\n");
		sb.append("\t- ").append(products.toString().replaceAll("\n", "\n\t").trim()).append("\n");
		sb.append("\t- ").append(requirement.toString().replaceAll("\n", "\n\t").trim()).append("\n");
		return sb.toString();
	}

}
