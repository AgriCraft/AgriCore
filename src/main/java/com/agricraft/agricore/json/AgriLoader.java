package com.agricraft.agricore.json;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.registry.AgriLoadableRegistry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
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

    protected static <T extends AgriSerializable> void convertAndBackup(Path location, AgriJsonVersion<T> newVersion) {
        // Check if a previous version exists
        AgriJsonVersion<?> oldVersion = newVersion.previousVersion();
        Function<AgriSerializable, T> converter = newVersion.versionConverter();
        if (oldVersion == null || converter == null) {
            return;
        }
        // Go deeper
        convertAndBackup(location, oldVersion);
        // Fetch old and new classes
        Class<T> newClass = newVersion.getElementClass();
        Class<?> oldClass = oldVersion.getElementClass();
        // Convert and back up
        try (Reader reader = Files.newBufferedReader(location)) {
            //load old json
            AgriSerializable oldObject = (AgriSerializable) GSON.fromJson(reader, oldClass);
            //create backups
            Path pBackup = Paths.get(location.toString().replaceAll("defaults", "backups/" + oldVersion.descriptor()));
            //save backups json
            AgriSaver.saveElement(pBackup, oldObject);
            //convert old json to new json
            AgriSerializable newObject = converter.apply(oldObject);
            //save new json
            if(newObject != null) {
                AgriSaver.saveElement(location, newObject);
                AgriCore.getCoreLogger().info("Backup {0} to {1}.\nSuccessfully converted the {0} from {2} to {3}.",
                        location.getFileName(), pBackup, oldVersion.descriptor(), newVersion.descriptor());
            } else {
                AgriCore.getCoreLogger().warn("An error occurred while ConvertAndBackup the {0} from {1} to {2}. Converter not correctly implemented.",
                        location.getFileName(), oldVersion.descriptor(), newVersion.descriptor());
            }
        } catch (Exception e) {
            AgriCore.getCoreLogger().warn("An error occurred while ConvertAndBackup the {0} from {1} to {2}. Maybe the target is already {2}.",
                    location.getFileName(), oldVersion.descriptor(), newVersion.descriptor());
        }
    }
  
    protected static <T extends AgriSerializable & Comparable<T>> void loadElement(Path root, Path location, AgriLoadableRegistry<T> registry) {

        // The Element
        T obj;

        // Ensure File Exists
        if (!Files.exists(location)) {
            AgriCore.getCoreLogger().warn("Tried to load non-existant File: \"{0}\"!", location);
            return;
        }

        // Attempt to convert from previous versions
        convertAndBackup(location, registry.getElementVersion());
        
        // Attempt to load element.
        // If fails, return.
        try (Reader reader = Files.newBufferedReader(location)) {
            obj = GSON.fromJson(reader, registry.getElementVersion().getElementClass());
            obj.setPath(root.relativize(location).toString().replaceAll("\\\\", "/"));
        } catch (IOException | JsonParseException | NullPointerException e) {
            // IOException is thrown when unable to read file.
            // JsonParseExeption is thrown when JSON format is wrong.
            // NullPointerException is thrown when the file is empty.
            AgriCore.getCoreLogger().warn("Unable to load Element: \"{0}\"!", location);
            AgriCore.getCoreLogger().trace(e);
            return;
        }

        // Writeback, to keep file formatted.
        // If fails, ignore.
        if (doWriteBack()) {
            AgriSaver.saveElement(location, obj);
        }

        // Register the Element.
        registry.registerElement(obj);

    }

}
