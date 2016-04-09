/*
 */
package com.agricraft.agricore.core.plant;

import com.agricraft.agricore.core.AgriCore;
import java.util.Random;

/**
 *
 * @author RlonRyan
 */
public class AgriMutation {

	public static final int MAX_CHANCE = 1000;

	public final int chance;

	public final String child;

	public final String parent1;
	public final String parent2;

	public final AgriRequirement requirement;

	public AgriMutation(int chance, String child, String parent1, String parent2, AgriRequirement requirement) {
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
		if (!AgriCore.getPlants().hasPlant(child)) {
			AgriCore.getLogger().info("Invalid Mutation: Invalid Child!");
			return false;
		} else if (!AgriCore.getPlants().hasPlant(parent1)) {
			AgriCore.getLogger().info("Invalid Mutation: Invalid Parent 1!");
			return false;
		} else if (!AgriCore.getPlants().hasPlant(parent2)) {
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
