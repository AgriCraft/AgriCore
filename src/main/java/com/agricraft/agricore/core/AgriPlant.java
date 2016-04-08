/*
 */
package com.agricraft.agricore.core;

/**
 *
 * @author RlonRyan
 */
public class AgriPlant {
	
	private static final String STRING_FORMAT = "\n" +
			"Plant:\n" +
			"\t- Block: %s\n" +
			"\t- %s\n" + 
			"\t- %s\n";

	public final String block;

	public final AgriProductList products;

	public final AgriRequirement requirement;
	
	public final AgriTexture texture;

	public AgriPlant(String block, AgriProductList products, AgriRequirement requirement, AgriTexture texture) {
		this.block = block;
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
		return String.format(STRING_FORMAT, block, products.toString().replaceAll("\n", "\n\t").trim(), requirement.toString().replaceAll("\n", "\n\t").trim());
	}

}
