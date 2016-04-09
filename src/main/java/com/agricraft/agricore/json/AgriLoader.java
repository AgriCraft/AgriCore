/*
 */
package com.agricraft.agricore.json;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.plant.AgriMutation;
import com.agricraft.agricore.plant.AgriPlant;
import com.agricraft.agricore.registry.AgriMutations;
import com.agricraft.agricore.registry.AgriPlants;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author RlonRyan
 */
public final class AgriLoader {

	private static final Gson gson = new Gson();

	private AgriLoader() {
	}

	public static boolean loadManifest(Path location, AgriPlants plants, AgriMutations mutations) {

		AgriManifest manifest;
		
		try {
			manifest = AgriManifest.load(location);
		} catch (IOException e) {
			AgriCore.getLogger().warn("No manifest at: " + location + "!");
			return false;
		}

		location = location.getParent();

		for (AgriManifestEntry e : manifest.elements) {
			if (e.enabled) {
				switch (e.type) {
					case GROUP:
						loadManifest(location.resolve(e.path), plants, mutations);
						break;
					case PLANT:
						loadPlant(location.resolve(e.path), plants);
						break;
					case MUTATION:
						loadMutation(location.resolve(e.path), mutations);
						break;
					default:
						AgriCore.getLogger().warn("Bad manifest entry: " + e);
				}
			}
		}
		return true;
	}

	private static void loadPlant(Path location, AgriPlants plants) {
		try (Reader reader = Files.newBufferedReader(location)) {
			plants.addPlant(gson.fromJson(reader, AgriPlant.class));
		} catch (IOException e) {
			AgriCore.getLogger().warn("Unable to load plant: " + location + "!");
			AgriCore.getLogger().trace(e);
		}
	}

	private static void loadMutation(Path location, AgriMutations mutations) {
		try (Reader reader = Files.newBufferedReader(location)) {
			mutations.addMutation(gson.fromJson(reader, AgriMutation.class));
		} catch (IOException e) {
			AgriCore.getLogger().warn("Unable to load mutation: " + location + "!");
			AgriCore.getLogger().trace(e);
		}
	}

}
