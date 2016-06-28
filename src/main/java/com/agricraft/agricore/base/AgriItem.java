/*
 */
package com.agricraft.agricore.base;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.lang.AgriString;

/**
 *
 * @author RlonRyan
 */
public class AgriItem {
	
	private final String id;
	private final AgriString name;
	private final String texture;

	public AgriItem(String id, AgriString name, String texture) {
		this.id = id;
		this.name = name;
		this.texture = texture;
	}

	public AgriItem() {
		this.id = "item_generic";
		this.name = new AgriString("Generic Item");
		this.texture = "agricraft:items/debugger";
	}

	public String getId() {
		return id;
	}

	public AgriString getName() {
		return name;
	}

	public String getTexture() {
		return texture;
	}
	
	public boolean validate() {
		return AgriCore.getValidator().isValidTexture(texture);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n").append(name).append(":\n");
		sb.append("\t- Id: ").append(id).append("\n");
		sb.append("\t- Texture: ").append(texture).append("\n");
		return sb.toString();
	}
	
}
