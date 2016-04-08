/*
 */
package com.agricraft.agricore.json;

import com.google.gson.Gson;
import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.core.AgriPlant;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author RlonRyan
 */
public class AgriPlants {

	public final AgriManifest manifest;
	private final Path manfestLocation;

	public final Map<String, AgriPlant> plants;

	public AgriPlants(Path p) {
		this.plants = new HashMap<>();
		this.manifest = AgriManifest.load(p);
		this.manfestLocation = p;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nPlants:");
		for (Map.Entry<String, AgriPlant> e : plants.entrySet()) {
			sb.append("\n\t- Plant: ").append(e.getKey()).append("\n\t");
			sb.append(e.getValue().toString().replaceAll("\n", "\n\t").trim());
		}
		return sb.append("\n").toString();
	}

	public boolean loadPlants() {
		for (AgriManifestEntry e : this.manifest.groups.values()) {
			if (e.enabled) {
				Path p = this.manfestLocation.getParent().resolve(e.path);
				AgriGroup group = AgriGroup.load(p);
				for (AgriManifestEntry plant : group.plants.values()) {
					try {
						AgriCore.getLogger().info("Loading Plant: " + plant.name + "!");
						this.plants.put(plant.name, loadPlant(p, plant.path));
					} catch (IOException ex) {
						AgriCore.getLogger().trace(ex);
						return false;
					}
				}
			}
		}
		return true;
	}

	private static AgriPlant loadPlant(Path base, String end) throws IOException {
		try (Reader reader = Files.newBufferedReader(base.getParent().resolve(end))) {
			Gson gson = new Gson();
			return gson.fromJson(reader, AgriPlant.class);
		}
	}

}
