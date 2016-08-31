/*
 */
package com.agricraft.agricore.json;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.util.TypeHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 *
 * @author RlonRyan
 */
public class AgriSaver {

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static void saveElements(Path location, AgriSerializable... objects) {
		saveElements(location, TypeHelper.asList(objects));
	}

	public static void saveElements(Path location, List<? extends AgriSerializable> objects) {
		objects.forEach(obj -> saveElement(location, obj));
	}

	public static void saveElement(Path location, AgriSerializable obj) {
		// Determine if need to autoname file.
		if (location.getFileName().toString().indexOf('.') == -1) {
			location = location.resolve(obj.getPath());
		}
		try {
			Files.createDirectories(location.getParent());
		} catch (IOException e) {
			AgriCore.getCoreLogger().warn("Unable to create directories for element: \"{0}\"!", location);
			return;
		}
		try (Writer writer = Files.newBufferedWriter(location, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			gson.toJson(obj, writer);
			writer.append("\n");
		} catch (IOException e) {
			AgriCore.getCoreLogger().warn("Unable to save element: \"{0}\"!", location);
		}
	}

}
