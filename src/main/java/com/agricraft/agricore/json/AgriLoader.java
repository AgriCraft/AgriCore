/*
 */
package com.agricraft.agricore.json;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.base.AgriItem;
import com.agricraft.agricore.base.AgriRecipe;
import com.agricraft.agricore.plant.AgriMutation;
import com.agricraft.agricore.plant.AgriPlant;
import com.agricraft.agricore.registry.AgriItems;
import com.agricraft.agricore.registry.AgriMutations;
import com.agricraft.agricore.registry.AgriPlants;
import com.agricraft.agricore.registry.AgriRecipes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.function.Consumer;

/**
 *
 * @author RlonRyan
 */
public final class AgriLoader {

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	private AgriLoader() {
	}

	public static boolean loadManifest(Path location, AgriPlants plants, AgriMutations mutations, AgriRecipes recipes, AgriItems items) {

		AgriManifest manifest;

		try {
			manifest = AgriManifest.load(location);
		} catch (IOException | JsonParseException e) {
			AgriCore.getCoreLogger().warn("No manifest at: " + location + "!");
			return false;
		}

		location = location.getParent();

		for (AgriManifestEntry e : manifest.elements) {
			if (e.enabled) {
				switch (e.type) {
					case GROUP:
						loadManifest(location.resolve(e.path), plants, mutations, recipes, items);
						break;
					case PLANT:
						loadElement(location.resolve(e.path), "plant", AgriPlant.class, plants::addPlant);
						break;
					case MUTATION:
						loadElement(location.resolve(e.path), "mutation", AgriMutation.class, mutations::addMutation);
						break;
					case RECIPE:
						loadElement(location.resolve(e.path), "recipe", AgriRecipe.class, recipes::addRecipe);
						break;
					case ITEM:
						loadElement(location.resolve(e.path), "item", AgriItem.class, items::addItem);
						break;
					default:
						AgriCore.getCoreLogger().warn("Bad manifest entry: " + e);
				}
			}
		}
		return true;
	}

	private static <T> void loadElement(Path location, String typename, Class<T> type, Consumer<T> registry) {

		// The Mutation to Read
		T obj;
		
		// Load the backup.
		try {
			obj = type.newInstance();
		} catch (IllegalAccessException | InstantiationException e) {
			AgriCore.getCoreLogger().debug("Bad manifest element type: {0}!", type);
			return;
		}

		// Read file, to get plant.
		// If fails, return.
		if (Files.exists(location)) {
			try (Reader reader = Files.newBufferedReader(location)) {
				obj = gson.fromJson(reader, type);
			} catch (IOException | JsonParseException e) {
				AgriCore.getCoreLogger().warn("Unable to load " + typename + ": " + location + "!");
				AgriCore.getCoreLogger().trace(e);
				return;
			}
		}

		// Writeback, to keep file formatted.
		// If fails, ignore.
		try (Writer writer = Files.newBufferedWriter(location, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			gson.toJson(obj, writer);
			writer.append("\n");
		} catch (IOException e) {
			AgriCore.getCoreLogger().warn("Unable to write back " + typename + ": " + location + "!");
		}

		// Add Mutation to Registry
		registry.accept(obj);

	}

}
