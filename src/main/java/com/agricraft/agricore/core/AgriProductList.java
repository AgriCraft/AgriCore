/*
 */
package com.agricraft.agricore.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author RlonRyan
 */
public class AgriProductList {
	
	public static final int MAX_CHANCE = 1000;
	
	private final List<AgriProduct> products;

	public AgriProductList(List<AgriProduct> products) {
		this.products = products;
	}
	
	public final List<AgriProduct> getAll() {
		return new ArrayList<>(this.products);
	}

	public final List<AgriProduct> getRandom(Random rand) {
		final List<AgriProduct> produce = new ArrayList<>();
		products.forEach((product) -> {
			if (product.chance > rand.nextInt(MAX_CHANCE)) {
				produce.add(product);
			}
		});
		return produce;
	}
	
	public boolean validate() {
		for (AgriProduct product : this.products) {
			if (!product.validate()) {
				AgriCore.getLogger().info("Invalid List: Invalid Product!");
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nProduct List:\n");
		sb.append("\t- Products: ");
		this.products.forEach((e) -> {
			sb.append(e.toString().replaceAll("\n", "\n\t"));
		});
		return sb.toString();
	}
	
}
