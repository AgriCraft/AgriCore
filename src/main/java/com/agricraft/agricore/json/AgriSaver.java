/*
 */
package com.agricraft.agricore.json;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.registry.AgriMutations;
import com.agricraft.agricore.registry.AgriPlants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author RlonRyan
 */
public class AgriSaver {
	
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
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
