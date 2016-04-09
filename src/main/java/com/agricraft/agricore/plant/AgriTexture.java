/*
 */
package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
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
		// Distribute the textures.
		String last = "NO TEXTURE!";
		for(int i = 0; i < this.plant_textures.length; i++) {
			if(this.plant_textures[i] == null) {
				this.plant_textures[i] = last;
			} else {
				last = this.plant_textures[i];
			}
		}
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

	public String getPlantTexture(int stage) {
		return this.plant_textures[stage % this.plant_textures.length];
	}

	public boolean validate() {
		if (!AgriCore.getValidator().isValidTexture(seed_texture)) {
			AgriCore.getLogger().debug("Invalid AgriTexture! Invalid Seed Texture: " + seed_texture);
			return false;
		}
		for (String texture : plant_textures) {
			if (!AgriCore.getValidator().isValidTexture(texture)) {
				AgriCore.getLogger().debug("Invalid AgriTexture! Invalid Plant Texture: " + texture);
				return false;
			}
		}
		return true;
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
