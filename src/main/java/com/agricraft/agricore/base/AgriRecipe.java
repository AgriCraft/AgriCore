/*
 */
package com.agricraft.agricore.base;

import com.agricraft.agricore.core.AgriCore;
import java.util.Arrays;

/**
 *
 * @author RlonRyan
 */
public class AgriRecipe {

	private final String[] format;
	private final String[] inputs;
	private final String result;

	private final boolean is_shapless;

	public AgriRecipe(String[] format, String[] inputs, String result, boolean is_shapless) {
		this.format = Arrays.copyOf(format, 3);
		this.inputs = Arrays.copyOf(inputs, 9);
		this.result = result;
		this.is_shapless = is_shapless;
	}

	public AgriRecipe() {
		this.format = new String[]{
			"123",
			"4_5",
			"123"
		};
		this.inputs = new String[]{
			"minecraft:gold_nugget",
			"minecraft:gold_nugget",
			"minecraft:gold_nugget",
			"minecraft:gold_nugget",
			"minecraft:gold_nugget",
			"minecraft:gold_nugget",
			"minecraft:gold_nugget",
			"minecraft:gold_nugget",
			"minecraft:gold_nugget",
			"minecraft:gold_nugget"
		};
		this.result = "minecraft:gold_ingot";
		this.is_shapless = false;
	}

	public boolean isShapless() {
		return is_shapless;
	}

	public String[] getFormat() {
		return format;
	}

	public String[] getInputs() {
		return inputs;
	}

	public String getResult() {
		return result;
	}

	public boolean validate() {
		for (String item : inputs) {
			if (!AgriCore.getValidator().isValidItem(item)) {
				return false;
			}
		}
		return AgriCore.getValidator().isValidItem(result);
	}

	@Override
	public String toString() {
		return "AgriRecipe: " + Arrays.toString(inputs) + " = " + result;
	}

}
