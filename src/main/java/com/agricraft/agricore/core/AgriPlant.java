/*
 */
package com.agricraft.agricore.core;

/**
 *
 * @author RlonRyan
 */
public class AgriPlant {

	public final String block;
	
	public final boolean bonemeal;

	public final AgriProductList products;

	public final AgriRequirement requirement;
	
	public final AgriTexture texture;

	public AgriPlant(String block, boolean bonemeal, AgriProductList products, AgriRequirement requirement, AgriTexture texture) {
		this.block = block;
		this.bonemeal = bonemeal;
		this.products = products;
		this.requirement = requirement;
		this.texture = texture;
	}

	public boolean validate() {
		if (!this.requirement.validate()) {
			AgriCore.getLogger().debug("Invalid Plant! Invalid Requirement!");
			return false;
		} else if (!this.products.validate()) {
			AgriCore.getLogger().debug("Invalid Plant! Invalid Product!");
			return false;
		} else if (!this.texture.validate()) {
			AgriCore.getLogger().debug("Invalid Plant! Invalid Icons!");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder().append("\nAgriPlant:\n");
		sb.append("\t- Block:").append(block).append("\n");
		sb.append("\t- Bonemeal: ").append(bonemeal).append("\n");
		sb.append("\t- ").append(products.toString().replaceAll("\n", "\n\t").trim()).append("\n");
		sb.append("\t- ").append(requirement.toString().replaceAll("\n", "\n\t").trim()).append("\n");
		return sb.toString();
	}

}
