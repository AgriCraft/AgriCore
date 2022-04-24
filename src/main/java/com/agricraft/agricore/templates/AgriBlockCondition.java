package com.agricraft.agricore.templates;

import com.agricraft.agricore.core.AgriCore;
import java.util.Arrays;
import java.util.List;

public class AgriBlockCondition extends AgriStateObject {
	protected final int strength;
	protected final int amount;
	protected final int min_x;
	protected final int min_y;
	protected final int min_z;
	protected final int max_x;
	protected final int max_y;
	protected final int max_z;
	
	public AgriBlockCondition() {
		super();
		this.strength = 11;
		this.amount = 1;
		this.min_x = 0;
		this.min_y = -2;
		this.min_z = 0;
		this.max_x = 0;
		this.max_y = -2;
		this.max_z = 0;
	}

	public AgriBlockCondition(int strength, int amount, int min_x, int min_y, int min_z, int max_x, int max_y, int max_z,
							  String block, boolean useTag, List<String> stateDefinition, String nbt, String... ignoredNbt) {
		this(strength, amount, min_x, min_y, min_z, max_x, max_y, max_z, block, useTag, stateDefinition, nbt, Arrays.asList(ignoredNbt));
	}

	public AgriBlockCondition(int strength, int amount, int min_x, int min_y, int min_z, int max_x, int max_y, int max_z,
							  String block, boolean useTag, List<String> stateDefinition, String nbt, List<String> ignoredNbt) {
		super("block", block, useTag, stateDefinition, nbt, ignoredNbt);
		this.strength = strength;
		this.amount = amount;
		this.min_x = min_x;
		this.min_y = min_y;
		this.min_z = min_z;
		this.max_x = max_x;
		this.max_y = max_y;
		this.max_z = max_z;
	}

	public int getStrength() {
		return this.strength;
	}

	public int getAmount() {
		return this.amount;
	}

	public int getMinX() {
		return this.min_x;
	}

	public int getMinY() {
		return this.min_y;
	}

	public int getMinZ() {
		return this.min_z;
	}

	public int getMaxX() {
		return this.max_x;
	}

	public int getMaxY() {
		return this.max_y;
	}

	public int getMaxZ() {
		return this.max_z;
	}

	@Override
	public boolean validate() {
		if (!super.validate()) {
			AgriCore.getCoreLogger().info("Invalid Condition: Invalid Block: {0}!", object);
			return false;
		}
		if(this.min_x > this.max_x) {
			AgriCore.getCoreLogger().info("Invalid Condition: min_x must not be larger than max_x: {0}!", object);
		}
		if(this.min_y > this.max_y) {
			AgriCore.getCoreLogger().info("Invalid Condition: min_y must not be larger than max_y: {0}!", object);
		}
		if(this.min_z > this.max_z) {
			AgriCore.getCoreLogger().info("Invalid Condition: min_z must not be larger than max_z: {0}!", object);
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nCondition:");
		sb.append("\n\t- ").append(super.toString().replaceAll("\n", "\n\t").trim());
		sb.append("\n\t- Amount: ").append(amount);
		sb.append("\n\t- Min Coord: (").append(min_x).append(", ").append(min_y).append(", ").append(min_z).append(")");
		sb.append("\n\t- Max Coord: (").append(max_x).append(", ").append(max_y).append(", ").append(max_z).append(")");
		return sb.toString();
	}

}
