/*
 */
package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.lang.AgriString;
import java.util.Random;

/**
 *
 * @author RlonRyan
 */
public class AgriPlant {

	private final String name;
	private final String id;
	private final AgriString description;

	private final double growth_chance;
	private final boolean bonemeal;
	private final int tier;

	private final AgriProductList products;
	private final AgriRequirement requirement;
	private final AgriTexture texture;

	public AgriPlant() {
		this.name = "Weed";
		this.id = "weed_plant";
		this.description = new AgriString("An annoying plant.");
		this.bonemeal = true;
		this.tier = 1;
		this.growth_chance = 0.9;
		this.products = new AgriProductList();
		this.requirement = new AgriRequirement();
		this.texture = new AgriTexture();
	}

	public AgriPlant(String name, String id, AgriString description, boolean bonemeal, int tier, double growth_chance, AgriProductList products, AgriRequirement requirement, AgriTexture texture) {
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

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
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

	public boolean shouldGrow(Random rand) {
		return this.growth_chance > rand.nextDouble();
	}

	public boolean validate() {
		if (!this.requirement.validate()) {
			AgriCore.getCoreLogger().debug("Invalid Plant! Invalid Requirement!");
			return false;
		} else if (!this.products.validate()) {
			AgriCore.getCoreLogger().debug("Invalid Plant! Invalid Product!");
			return false;
		} else if (!this.texture.validate()) {
			AgriCore.getCoreLogger().debug("Invalid Plant! Invalid Texture!");
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
