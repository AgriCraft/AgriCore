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

	private final byte[] seed_color = new byte[]{0, 0, 0};
	private final String[] plant_textures = new String[]{
		"minecraft:wheat_stage_0",
		"minecraft:wheat_stage_0",
		"minecraft:wheat_stage_0",
		"minecraft:wheat_stage_1",
		"minecraft:wheat_stage_1",
		"minecraft:wheat_stage_2",
		"minecraft:wheat_stage_2",
		"minecraft:wheat_stage_3",
		"minecraft:wheat_stage_3",
		"minecraft:wheat_stage_4",
		"minecraft:wheat_stage_4",
		"minecraft:wheat_stage_5",
		"minecraft:wheat_stage_5",
		"minecraft:wheat_stage_6",
		"minecraft:wheat_stage_6",
		"minecraft:wheat_stage_7",};

	public AgriTexture(AgriRenderType render_type, String seed_texture, byte[] seed_color, String[] plant_textures) {

		this.render_type = render_type;
		this.seed_texture = seed_texture;

		System.arraycopy(seed_color, 0, this.seed_color, 0, seed_color.length < this.seed_color.length ? seed_color.length : this.seed_color.length);
		System.arraycopy(plant_textures, 0, this.plant_textures, 0, plant_textures.length < this.plant_textures.length ? plant_textures.length : this.plant_textures.length);

		// Distribute the textures.
		String last = "NO TEXTURE!";
		for (int i = 0; i < this.plant_textures.length; i++) {
			if (this.plant_textures[i] == null) {
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

	public byte[] getSeedColor() {
		return Arrays.copyOf(seed_color, seed_color.length);
	}

	public int getSeedColorRGB() {
		int res = seed_color[2];
		res |= seed_color[1] << 8;
		res |= seed_color[0] << 16;
		return res;
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
		sb.append("\t- Seed Color:\n");
		sb.append("\t\t- Red:   ").append(seed_color[0]).append("\n");
		sb.append("\t\t- Green: ").append(seed_color[1]).append("\n");
		sb.append("\t\t- Blue:  ").append(seed_color[2]).append("\n");
		sb.append("\t- Render Type: ").append(this.render_type).append("\n");
		sb.append("\t- Plant Textures:\n");
		for (int i = 0; i < this.plant_textures.length; i++) {
			sb.append("\t\t- ").append(i).append(" : ").append(this.plant_textures[i]).append("\n");
		}
		return sb.toString();
	}

}
