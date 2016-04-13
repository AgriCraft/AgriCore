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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RlonRyan
 */
public class AgriManifest {
	
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public final List<AgriManifestEntry> elements;

	private AgriManifest() {
		this.elements = new ArrayList<>();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nManifest:\n");
		for (AgriManifestEntry e : elements) {
			sb.append("\t- Entry:\n");
			sb.append("\t\t- Type: ").append(e.type).append("\n");
			sb.append("\t\t- Path: ").append(e.path).append("\n");
			sb.append("\t\t- Enabled: ").append(e.enabled).append("\n");
		}
		return sb.toString();
	}

	public static AgriManifest getEmptyManifest() {
		return new AgriManifest();
	}

	public static AgriManifest load(Path p) throws IOException {
		try (Reader reader = Files.newBufferedReader(p)) {
			return gson.fromJson(reader, AgriManifest.class);
		}
	}

	public static boolean save(Path p, AgriManifest m) {
		p.getParent().toFile().mkdirs();
		try (BufferedWriter out = Files.newBufferedWriter(p, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
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
