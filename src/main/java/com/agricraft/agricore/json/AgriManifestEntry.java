/*
 */
package com.agricraft.agricore.json;

/**
 *
 * @author RlonRyan
 */
public final class AgriManifestEntry {
	
	public final String name;
	public final String path;
	public final boolean enabled;

	public AgriManifestEntry(String name, String path, boolean enabled) {
		this.name = name;
		this.path = path;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "\nGroup: " + name + "\n\t- Path: " + path + "\n\t- Enabled: " + enabled + "\n";
	}
	
}
