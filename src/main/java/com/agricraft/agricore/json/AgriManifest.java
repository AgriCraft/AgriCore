/*
 */
package com.agricraft.agricore.json;

import com.agricraft.agricore.core.AgriCore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author RlonRyan
 */
public class AgriManifest {

	public final Map<String, AgriManifestEntry> groups;

	private AgriManifest(Map<String, AgriManifestEntry> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nManifest:\n");
		for (Map.Entry<String, AgriManifestEntry> e : groups.entrySet()) {
			sb.append("\t- Group: ").append(e.getKey()).append("\n");
			sb.append("\t\t- Name: ").append(e.getValue().name).append("\n");
			sb.append("\t\t- Path: ").append(e.getValue().path).append("\n");
			sb.append("\t\t- Enabled: ").append(e.getValue().enabled).append("\n");
		}
		return sb.toString();
	}

	public static AgriManifest load(Path p) {
		try (Reader reader = Files.newBufferedReader(p)) {
			Gson gson = new Gson();
			return gson.fromJson(reader, AgriManifest.class);
		} catch (IOException e) {
			AgriCore.getLogger().trace(e);
			
			// Generate Defaults
			Map<String, AgriManifestEntry> groups = new HashMap<>();
			groups.put("vanilla", new AgriManifestEntry("vanilla", "vanilla/vanilla_group.json", true));
			
			return new AgriManifest(groups);
		}
	}

	public static boolean save(Path p, AgriManifest m) {
		p.getParent().toFile().mkdirs();
		try (BufferedWriter out = Files.newBufferedWriter(p, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			out.append(gson.toJson(m));
			out.newLine();
			return true;
		} catch (IOException e) {
			AgriCore.getLogger().info("Unable to save manifest!");
			AgriCore.getLogger().trace(e);
			return false;
		}
	}

}
