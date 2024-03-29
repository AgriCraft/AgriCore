package com.agricraft.agricore.templates.versions.v1;

import com.agricraft.agricore.templates.AgriTexture;
import com.agricraft.agricore.templates.versions.v2.AgriTexture_1_16;

public class AgriTexture_1_12 {

    private String render_type = "hash";
    private String seed_texture = "minecraft:items/seeds_wheat";

    private final String[] plant_textures = new String[]{
        "minecraft:blocks/wheat_stage_0",
        "minecraft:blocks/wheat_stage_1",
        "minecraft:blocks/wheat_stage_2",
        "minecraft:blocks/wheat_stage_3",
        "minecraft:blocks/wheat_stage_4",
        "minecraft:blocks/wheat_stage_5",
        "minecraft:blocks/wheat_stage_6",
        "minecraft:blocks/wheat_stage_7"
    };

    // GSON Tricker
    public AgriTexture_1_12() {
    }

    public AgriTexture_1_12(String render_type, String seed_texture, String[] plant_textures) {
        this.render_type = render_type;
        this.seed_texture = seed_texture;

        System.arraycopy(plant_textures, 0, this.plant_textures, 0, Math.min(plant_textures.length, this.plant_textures.length));

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

    public AgriTexture_1_16 toNew() {
        return new AgriTexture_1_16(this.render_type, this.correctTextures());
    }

    public String seed_texture() {
        return this.correctTexture(this.seed_texture);
    }

    private String[][] correctTextures() {
        String[][] textures = new String[this.plant_textures.length][];
        for(int i = 0; i < textures.length; i++) {
            textures[i] = new String[]{this.correctTexture(this.plant_textures[i])};
        }
        return textures;
    }

    private String correctTexture(String texture) {
        if (texture.contains("minecraft")) {
            if (texture.contains("items")) {
                return texture.replace("items", "item");
            } else if (texture.contains("blocks")) {
                return texture.replace("blocks", "block");
            }
        }
        if(texture.contains("agricraft")) {
            if (texture.contains("items")) {
                return texture.replace("seed_", "").replace("items", "seed");
            } else if (texture.contains("blocks")) {
                return texture.replace("crop_", "").replace("blocks", "plant");
            } else if (texture.contains("item")) {
                return texture.replace("seed_", "").replace("item", "plant");
            } else if (texture.contains("block")) {
                return texture.replace("crop_", "").replace("block", "seed");
            }
        }
        return texture;
    }

    public int getGrowthStages() {
        return plant_textures.length;
    }

}
