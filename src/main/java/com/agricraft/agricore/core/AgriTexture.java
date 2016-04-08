/*
 */
package com.agricraft.agricore.core;

import java.util.Arrays;

/**
 *
 * @author RlonRyan
 */
public class AgriTexture {
	
	private final AgriRenderType render_type;
	private final String seed_texture;
	private final String[] plant_textures;

	public AgriTexture(String seed_texture, AgriRenderType render_type, String[] plant_textures) {
		this.render_type = render_type;
		this.seed_texture = seed_texture;
		this.plant_textures = Arrays.copyOf(plant_textures, 16);
	}

	public AgriRenderType getRenderType() {
		return render_type;
	}

	public String getSeedTexture() {
		return seed_texture;
	}

	
	public String[] getPlantTextures() {
		return Arrays.copyOf(plant_textures, plant_textures.length);
	}

	public boolean validate() {
		if (false) {
			AgriCore.getLogger().debug("Invalid Plant! Invalid Requirement!");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append("Icon Set:\n");
		sb.append("\t- Seed Texture: ").append(this.seed_texture).append("\n");
		sb.append("\t- Render Type: ").append(this.render_type).append("\n");
		sb.append("\t- Plant Textures:\n");
		for (int i = 0; i < this.plant_textures.length; i++) {
			sb.append("\t\t- ").append(i).append(" : ").append(this.plant_textures[i]).append("\n");
		}
		return sb.toString();
	}
	
}
