/*
 */
package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import java.util.Random;

/**
 *
 * @author RlonRyan
 */
public class AgriProduct {

	private final String item;

	private final int meta;
	private final int min;
	private final int max;

	private final double chance;
	
	private final boolean required;

	public AgriProduct() {
		this.item = "minecraft:wheat";
		this.meta = 0;
		this.min = 5;
		this.max = 5;
		this.chance = 0.99;
		this.required = true;
	}

	public AgriProduct(String item, int meta, int min, int max, double chance, boolean required) {
		this.item = item;
		this.meta = meta;
		this.min = min;
		this.max = max;
		this.chance = chance;
		this.required = required;
	}

	public boolean validate() {
		if (!AgriCore.getValidator().isValidItem(item)) {
			AgriCore.getCoreLogger().info("Invalid Product: Invalid Item: {0}:{1}!", item, meta);
			return false;
		} else if (min < 1) {
            AgriCore.getCoreLogger().info("Invalid Product: Min Amount Less Than One!");
			return false;
        } else if (max < min) {
            AgriCore.getCoreLogger().info("Invalid Product: Min Amount Greater Than Max: {0} > {1}!", min, max);
			return false;
        } else {
			return true;
		}
	}

	public boolean isRequired() {
		return required;
	}
	
	public boolean shouldDrop(final Random rand) {
		return chance > rand.nextDouble();
	}
	
	public int getAmount(final Random rand) {
		return min + rand.nextInt(max - min + 1);
	}

	public Object toStack() {
		return AgriCore.getConverter().toStack(this.item, this.min, this.meta);
	}

	public Object toStack(Random rand) {
		return AgriCore.getConverter().toStack(this.item, this.getAmount(rand), this.meta);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nProduct:");
		sb.append("\n\t- Item: ").append(item);
		sb.append("\n\t- Meta: ").append(meta);
		sb.append("\n\t- Min Amount: ").append(min);
		sb.append("\n\t- Max Amount: ").append(max);
		sb.append("\n\t- Probability: ").append(chance);
		return sb.toString();
	}

}
