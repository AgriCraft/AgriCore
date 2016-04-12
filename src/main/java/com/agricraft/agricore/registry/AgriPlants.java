/*
 */
package com.agricraft.agricore.registry;

import com.agricraft.agricore.plant.AgriPlant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RlonRyan
 */
public class AgriPlants {

	private final Map<String, AgriPlant> plants;

	public AgriPlants() {
		this.plants = new HashMap<>();
	}
	
	public boolean hasPlant(String id) {
		return this.plants.containsKey(id);
	}
	
	public boolean addPlant(AgriPlant plant) {
		return this.plants.putIfAbsent(plant.id, plant) == null;
	}
	
	public AgriPlant getPlant(String id) {
		return this.plants.get(id);
	}
	
	public List<AgriPlant> getAll() {
		return new ArrayList<>(this.plants.values());
	}
	
	public void validate() {
		plants.entrySet().removeIf((e) -> (!e.getValue().validate()));
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nPlants:");
		for (Map.Entry<String, AgriPlant> e : plants.entrySet()) {
			sb.append("\n\t- Plant: ");
			sb.append(e.getValue().toString().replaceAll("\n", "\n\t").trim());
		}
		return sb.append("\n").toString();
	}

}
