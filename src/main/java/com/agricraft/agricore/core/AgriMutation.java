/*
 */
package com.agricraft.agricore.core;

import java.util.Random;

/**
 *
 * @author RlonRyan
 */
public class AgriMutation {

	public static final int MAX_CHANCE = 1000;

	public final int chance;

	public final AgriPlant child;

	public final AgriPlant parent1;
	public final AgriPlant parent2;

	public final AgriRequirement requirement;

	public AgriMutation(int chance, AgriPlant child, AgriPlant parent1, AgriPlant parent2, AgriRequirement requirement) {
		this.chance = chance;
		this.child = child;
		this.parent1 = parent1;
		this.parent2 = parent2;
		this.requirement = requirement;
	}

	public boolean randomMutate(final Random rand) {
		return (chance > rand.nextInt(MAX_CHANCE));
	}

	public boolean validate() {
		if (!this.child.validate()) {
			AgriCore.getLogger().info("Invalid Mutation: Invalid Child!");
			return false;
		} else if (!this.parent1.validate()) {
			AgriCore.getLogger().info("Invalid Mutation: Invalid Parent 1!");
			return false;
		} else if (!this.parent2.validate()) {
			AgriCore.getLogger().info("Invalid Mutation: Invalid Parent 2!");
			return false;
		} else if (!this.requirement.validate()) {
			AgriCore.getLogger().info("Invalid Mutation: Invalid Requirement!");
			return false;
		} else {
			return true;
		}
	}

}
