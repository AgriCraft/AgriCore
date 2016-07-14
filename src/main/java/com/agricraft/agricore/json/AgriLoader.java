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
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 *
 * @author RlonRyan
 */
public final class AgriLoader {

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	private AgriLoader() {
	}

	public static void loadDirectory(Path dir, AgriPlants plants, AgriMutations mutations) {
		try (Stream<Path> stream = Files.walk(dir)) {
			stream.forEach(p -> handleFile(dir, p, plants, mutations));
		} catch (IOException e) {
			AgriCore.getCoreLogger().debug("Unable to load directory: \"{0}\"!", dir);
		}
	}

	private static void handleFile(final Path root, Path location, AgriPlants plants, AgriMutations mutations) {
		switch (getType(location.getFileName().toString())) {
			case "plant":
				loadElement(root, location, "plant", AgriPlant.class, plants::addPlant);
				break;
			case "mutation":
				loadElement(root, location, "mutation", AgriMutation.class, mutations::addMutation);
				break;
		}
	}

	private static String getType(String file) {
		String[] parts = file.split("_");
		if (parts.length < 2) {
			return "";
		}
		parts = parts[parts.length - 1].split("\\.");
		if (parts.length < 2) {
			return "";
		}
		return parts[0].toLowerCase();
	}

	private static <T> void loadElement(final Path root, final Path location, String typename, Class<T> type, Consumer<T> registry) {

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
				if (obj instanceof AgriSerializable) {
					((AgriSerializable) obj).setPath(root.relativize(location).toString().replaceAll("\\\\", "/"));
				}
			} catch (IOException | JsonParseException e) {
				AgriCore.getCoreLogger().warn("Unable to load " + typename + ": " + location + "!");
				AgriCore.getCoreLogger().trace(e);
				return;
			}
		}

		// Writeback, to keep file formatted.
		// If fails, ignore.
		saveElement(location, obj);

		// Add Mutation to Registry
		registry.accept(obj);

	}
	
	public static void saveDirectory(Path dir, AgriPlants plants, AgriMutations mutations) {
		plants.getAll().forEach(p -> saveElement(dir, p));
		mutations.getAll().forEach(m -> saveElement(dir, m));
	}

	public static void saveElement(Path location, Object obj) {
		if (location.getFileName().toString().indexOf('.') == -1 && obj instanceof AgriSerializable) {
			location = location.resolve(((AgriSerializable)obj).getPath());
		}
		try {
			Files.createDirectories(location.getParent());
		} catch (IOException e) {
			AgriCore.getCoreLogger().warn("Unable to create directories for element: \"{0}\"!", location);
		}
		try (Writer writer = Files.newBufferedWriter(location, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			gson.toJson(obj, writer);
			writer.append("\n");
		} catch (IOException e) {
			AgriCore.getCoreLogger().warn("Unable to save element: \"{0}\"!", location);
		}
	}

}
