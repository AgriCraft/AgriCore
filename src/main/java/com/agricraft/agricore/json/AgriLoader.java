/*
 */
package com.agricraft.agricore.json;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.plant.AgriMutation;
import com.agricraft.agricore.plant.AgriPlant;
import com.agricraft.agricore.registry.AgriMutations;
import com.agricraft.agricore.registry.AgriPlants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author RlonRyan
 */
public final class AgriLoader {

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

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

		// The Plant to Read
		AgriPlant plant;

		// Read file, to get plant.
		// If fails, return.
		try (Reader reader = Files.newBufferedReader(location)) {
			plant = gson.fromJson(reader, AgriPlant.class);
		} catch (IOException e) {
			AgriCore.getLogger().warn("Unable to load plant: " + location + "!");
			AgriCore.getLogger().trace(e);
			return;
		}

		// Writeback, to keep file formatted.
		// If fails, ignore.
		try (Writer writer = Files.newBufferedWriter(location, StandardOpenOption.TRUNCATE_EXISTING)) {
			gson.toJson(plant, writer);
			writer.append("\n");
		} catch (IOException e) {
			AgriCore.getLogger().warn("Unable to write back plant: " + location + "!");
		}

		// Add Plant to Registry
		plants.addPlant(plant);

	}

	private static void loadMutation(Path location, AgriMutations mutations) {
		
		// The Mutation to Read
		AgriMutation mutation;

		// Read file, to get plant.
		// If fails, return.
		try (Reader reader = Files.newBufferedReader(location)) {
			mutation = gson.fromJson(reader, AgriMutation.class);
		} catch (IOException e) {
			AgriCore.getLogger().warn("Unable to load plant: " + location + "!");
			AgriCore.getLogger().trace(e);
			return;
		}

		// Writeback, to keep file formatted.
		// If fails, ignore.
		try (Writer writer = Files.newBufferedWriter(location, StandardOpenOption.TRUNCATE_EXISTING)) {
			gson.toJson(mutation, writer);
			writer.append("\n");
		} catch (IOException e) {
			AgriCore.getLogger().warn("Unable to write back plant: " + location + "!");
		}

		// Add Mutation to Registry
		mutations.addMutation(mutation);
		
	}

}
