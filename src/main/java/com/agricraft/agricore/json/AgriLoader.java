/*
 */
package com.agricraft.agricore.json;

import com.agricraft.agricore.config.AgriConfigCategory;
import com.agricraft.agricore.config.AgriConfigurable;
import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.registry.AgriLoadableRegistry;
import com.agricraft.agricore.registry.AgriMutations;
import com.agricraft.agricore.registry.AgriPlants;
import com.agricraft.agricore.util.TypeHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Stream;

/**
 *
 * @author RlonRyan
 */
public final class AgriLoader {
	
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	@AgriConfigurable(key = "Enable JSON Writeback", category = AgriConfigCategory.CORE, comment = "Set to false to disable automatic JSON writeback.")
	private static boolean writeback = true;
	
	static {
		AgriCore.getConfig().addConfigurable(AgriLoader.class);
	}
	
	private AgriLoader() {
	}

	public static void loadDirectory(Path dir, AgriPlants plants, AgriMutations mutations) {
		loadDirectory(dir, TypeHelper.asSet(plants, mutations));
	}

	public static void loadDirectory(Path dir, Set<AgriLoadableRegistry> registries) {
		try (Stream<Path> stream = Files.walk(dir)) {
			stream.forEach(p -> handleFile(dir, p, registries));
		} catch (IOException e) {
			AgriCore.getCoreLogger().debug("Unable to load directory: \"{0}\"!", dir);
		}
	}

	private static void handleFile(final Path root, Path location, Set<AgriLoadableRegistry> registries) {
		// Debug
		AgriCore.getCoreLogger().debug("Looking at file: \"{0}\"!", location);
		for (AgriLoadableRegistry r : registries) {
			if (r.acceptsElement(location.getFileName().toString())) {
				loadElement(root, location, r);
			}
		}
	}

	private static <T> void loadElement(Path root, Path location, AgriLoadableRegistry<T> registry) {

		// The Element
		T obj;

		// Ensure File Exists
		if (!Files.exists(location)) {
			return;
		}

		// Attempt to load element.
		// If fails, return.
		try (Reader reader = Files.newBufferedReader(location)) {
			obj = GSON.fromJson(reader, registry.getElementClass());
			if (obj instanceof AgriSerializable) {
				((AgriSerializable) obj).setPath(
						root.relativize(location).toString().replaceAll("\\\\", "/")
				);
			}
		} catch (IOException | JsonParseException e) {
			AgriCore.getCoreLogger().warn("Unable to load Element: \"{0}\"!", location);
			AgriCore.getCoreLogger().trace(e);
			return;
		}

		// Writeback, to keep file formatted.
		// If fails, ignore.
		if (writeback) {
			AgriSaver.saveElement(location, obj);
		}

		// Register the Element.
		registry.registerElement(obj);

	}

}
