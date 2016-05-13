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
	private final int base;
	private final int range;

	private final double chance;

	public AgriProduct() {
		this.item = "minecraft:wheat";
		this.meta = 0;
		this.base = 5;
		this.range = 5;
		this.chance = 0.99;
	}

	public AgriProduct(String item, int meta, int base, int range, double chance) {
		this.item = item;
		this.meta = meta;
		this.base = base;
		this.range = range;
		this.chance = chance;
	}

	public boolean validate() {
		if (!AgriCore.getValidator().isValidItem(item)) {
			AgriCore.getCoreLogger().info("Invalid Product: Invalid Item: " + item + "!");
			return false;
		} else {
			return true;
		}
	}
	
	public boolean shouldDrop(final Random rand) {
		return chance > rand.nextDouble();
	}
	
	public int getAmount(final Random rand) {
		return base + rand.nextInt(range);
	}

	public Object toStack() {
		return AgriCore.getConverter().toStack(this.item, this.base + 1, this.meta);
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
		sb.append("\n\t- Base Amount: ").append(base);
		sb.append("\n\t- Range Amount: ").append(range);
		sb.append("\n\t- Probability: ").append(chance);
		return sb.toString();
	}

}
