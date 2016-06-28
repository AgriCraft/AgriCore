/*
 */
package com.agricraft.agricore.json;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author RlonRyan
 */
public enum AgriManifestEntryType {
	
	@SerializedName(value="group", alternate={"GROUP", "g", "G"})
	GROUP,
	@SerializedName(value="plant", alternate={"PLANT", "p", "P"})
	PLANT,
	@SerializedName(value="mutation", alternate={"MUTATION", "m", "M"})
	MUTATION,
	@SerializedName(value="invalid")
	INVALID;
	
}
