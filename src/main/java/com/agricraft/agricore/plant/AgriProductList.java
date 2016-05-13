/*
 */
package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author RlonRyan
 */
public class AgriProductList {
	
	private final List<AgriProduct> products;

	public AgriProductList() {
		this.products = new ArrayList<>();
		this.products.add(new AgriProduct());
	}

	public AgriProductList(List<AgriProduct> products) {
		this.products = products;
	}
	
	public final List<AgriProduct> getAll() {
		return new ArrayList<>(this.products);
	}

	public final List<AgriProduct> getRandom(Random rand) {
		final List<AgriProduct> produce = new ArrayList<>();
		products.forEach((product) -> {
			if (product.shouldDrop(rand)) {
				produce.add(product);
			}
		});
		return produce;
	}
	
	public boolean validate() {
		for (AgriProduct product : this.products) {
			if (!product.validate()) {
				AgriCore.getCoreLogger().info("Invalid List: Invalid Product!");
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
