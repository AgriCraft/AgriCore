package com.agricraft.agricore.json;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.plant.versions.v1.Versions_1_12;
import com.agricraft.agricore.registry.AgriLoadableRegistry;
import com.google.gson.*;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

public final class AgriLoader {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static boolean doWriteBack() {
        return AgriCore.getConfig().enableJsonWriteback();
    }

    private AgriLoader() {}

    public static void loadDirectory(Path dir, AgriLoadableRegistry... registries) {
        try {
            Files.walkFileTree(dir, new AgriFileWalker(dir, registries));
        } catch (IOException e) {
            AgriCore.getCoreLogger().debug("Unable to load directory: \"{0}\"!", dir);
        }
    }
  
    protected static <T extends AgriSerializable & Comparable<T>> void loadElement(Path root, Path location, AgriLoadableRegistry<T> registry) {
        // Ensure File Exists
        if (!Files.exists(location)) {
            AgriCore.getCoreLogger().warn("Tried to load non-existant File: \"{0}\"!", location);
            return;
        }
        // Attempt to load element. If fails, return.
        JsonElement json = readJson(location);
        if (json == null) {
            return;
        }
        T obj = parseVersionAware(json, location, registry.getElementVersion());
        if(obj != null) {
            obj.setPath(root.relativize(location).toString().replaceAll("\\\\", "/"));
        } else {
            AgriCore.getCoreLogger().warn("Could parse {0}, parser returned null.", location.getFileName());
            return;
        }
        // Writeback, to keep file formatted. If fails, ignore.
        if (doWriteBack()) {
            AgriSaver.saveElement(location, obj);
        }
        // Register the Element.
        registry.registerElement(obj);
    }

    private static JsonElement readJson(Path location) {
        try (Reader reader = Files.newBufferedReader(location)) {
            return new JsonParser().parse(reader);
        } catch (IOException | JsonParseException | NullPointerException e) {
            // IOException is thrown when unable to read file.
            // JsonParseExeption is thrown when JSON format is wrong.
            // NullPointerException is thrown when the file is empty.
            AgriCore.getCoreLogger().warn("Unable to read json: \"{0}\"!", location);
            AgriCore.getCoreLogger().trace(e);
            return null;
        }
    }

    @Nullable
    private static <T extends AgriSerializable & Comparable<T>> T parseVersionAware(JsonElement json, Path location, AgriJsonVersion<T> version) {
        // Parse the version
        String jsonVersion = parseVersion(json, location);
        if(version.descriptor().equals(jsonVersion)) {
            // Same version, can directly parse
            return parse(json, location, version.getElementClass());
        } else {
            // Wrong version, recursively convert to required version
            AgriCore.getCoreLogger().warn("Attempting to parse {0} from an older version ({1}), required version is {2}.",
                    location.getFileName(), jsonVersion, version.descriptor());
            AgriJsonVersion<?> prevVersion = version.previousVersion();
            Function<AgriSerializable, T> converter = version.versionConverter();
            // Check if valid version conversion exists
            if(prevVersion != null && converter != null) {
                // Recursively parse from the previous version
                AgriSerializable prev = parseVersionAware(json, location, prevVersion);
                if(prev != null) {
                    // Back up before conversion
                    Path backupPath = Paths.get(location.toString().replaceAll("defaults", "backups/" + prevVersion.descriptor()));
                    AgriSaver.saveElement(backupPath, prev);
                    // Convert and return
                    T obj = converter.apply(prev);
                    if(obj == null) {
                        // Version converter returned null, log a warning and return null
                        AgriCore.getCoreLogger().warn("Could not convert {0} from {1} to {2}, version converter returned null.",
                                location.getFileName(), jsonVersion, version.descriptor());
                    }
                    return obj;
                } else {
                    // Previous version parser returned null, log a warning and return null
                    AgriCore.getCoreLogger().warn("Could not convert {0} from {1} to {2}, parsing of version {1} returned null.",
                            location.getFileName(), jsonVersion, version.descriptor());
                    return null;
                }
            } else {
                // No version conversion defined, this means that the new version should be compatible with the old version
                AgriCore.getCoreLogger().warn("No conversion from version {0} to version {1} exists for this file, parsing with latest version.",
                        location.getFileName(), jsonVersion, version.descriptor());
                return parse(json, location, version.getElementClass());
            }
        }
    }

    private static String parseVersion(JsonElement json, Path location) {
        JsonObject jsonObject = json.getAsJsonObject();
        if(jsonObject.has("version")) {
            return jsonObject.get("version").getAsString();
        } else {
            // If no version is defined in the json, it is the first version: 1.12
            AgriCore.getCoreLogger().warn("Could not identify version for {0}, assuming default version ({1}).",
                    location.getFileName(), Versions_1_12.VERSION);
            return Versions_1_12.VERSION;
        }
    }

    private static <T extends AgriSerializable & Comparable<T>> T parse(JsonElement json, Path location, Class<T> clazz) {
        try {
            return GSON.fromJson(json, clazz);
        } catch (JsonParseException | NullPointerException e) {
            // JsonParseExeption is thrown when JSON format is wrong.
            AgriCore.getCoreLogger().warn("Unable to parse json: \"{0}\"!", location);
            AgriCore.getCoreLogger().trace(e);
            return null;
        }
    }
}
