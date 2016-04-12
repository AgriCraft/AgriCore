/*
 */
package com.agricraft.agricore.registry;

import com.agricraft.agricore.plant.AgriMutation;
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
	
	public List<AgriMutation> getMutation(String parent1, String parent2) {
		final List<AgriMutation> results = new ArrayList<>();
		for(AgriMutation mutation : mutations) {
			if((mutation.parent1.equals(parent1) || mutation.parent1.equals(parent2)) && (mutation.parent2.equals(parent1) || mutation.parent2.equals(parent2))) {
				results.add(mutation);
			}
		}
		return results;
	}
	
	public List<AgriMutation> getAll() {
		return new ArrayList<>(this.mutations);
	}
	
	public void validate() {
		mutations.removeIf((e) -> (!e.validate()));
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nMutations:");
		for (AgriMutation mutation : mutations) {
			sb.append("\n\t- ").append(mutation).append("\n\t");
		}
		return sb.append("\n").toString();
	}

}
