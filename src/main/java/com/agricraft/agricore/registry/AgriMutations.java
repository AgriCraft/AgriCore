/*
 */
package com.agricraft.agricore.registry;

import com.agricraft.agricore.core.plant.AgriMutation;
import com.agricraft.agricore.core.plant.AgriPlant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author RlonRyan
 */
public class AgriMutations {

	private final Set<AgriMutation> mutations;

	public AgriMutations() {
		this.mutations = new HashSet<>();
	}
	
	public boolean hasMutation(AgriMutation mutation) {
		return this.mutations.contains(mutation);
	}
	
	public boolean addMutation(AgriMutation mutation) {
		return this.mutations.add(mutation);
	}
	
	public List<AgriMutation> getMutation(AgriPlant parent1, AgriPlant parent2) {
		final List<AgriMutation> results = new ArrayList<>();
		for(AgriMutation mutation : mutations) {
			if((mutation.parent1 == parent1 || mutation.parent1 == parent2) && (mutation.parent2 == parent1 || mutation.parent2 == parent2)) {
				results.add(mutation);
			}
		}
		return results;
	}
	
	public List<AgriMutation> getAll() {
		return new ArrayList<>(this.mutations);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nPlants:");
		for (AgriMutation mutation : mutations) {
			sb.append("\n\t- ").append(mutation).append("\n\t");
		}
		return sb.append("\n").toString();
	}

}
