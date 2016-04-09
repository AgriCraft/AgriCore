/*
 */
package com.agricraft.agricore.json;

/**
 *
 * @author RlonRyan
 */
public final class AgriManifestEntry {
	
	public final AgriManifestEntryType type;
	public final String path;
	public final boolean enabled;

	public AgriManifestEntry(AgriManifestEntryType type, String path, boolean enabled) {
		this.type = type;
		this.path = path;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "\nEntry:\n\t- Type: " + type + "\n\t- Path: " + path + "\n\t- Enabled: " + enabled + "\n";
	}
	
}
