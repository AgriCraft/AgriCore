package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AgriTexture {

    private AgriRenderType render_type = AgriRenderType.HASH;
    private String seed_model = "minecraft:item/wheat_seeds";

    private final String[] plant_textures = new String[]{
        "minecraft:block/wheat_stage0",
        "minecraft:block/wheat_stage1",
        "minecraft:block/wheat_stage2",
        "minecraft:block/wheat_stage3",
        "minecraft:block/wheat_stage4",
        "minecraft:block/wheat_stage5",
        "minecraft:block/wheat_stage6",
        "minecraft:block/wheat_stage7"
    };

    // GSON Tricker
    public AgriTexture() {
    }

    public AgriTexture(AgriRenderType render_type, String seed_model, String[] plant_textures) {

        this.render_type = render_type;
        this.seed_model = seed_model;

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

    public AgriRenderType getRenderType() {
        return render_type;
    }

    public String getSeedModel() {
        return seed_model;
    }
    
    public int getGrowthStages() {
        return plant_textures.length;
    }

    public List<String> getAllTextures() {
        final List<String> tex = new ArrayList<>(plant_textures.length + 1);
        tex.addAll(Arrays.asList(plant_textures));
        return tex;
    }

    public String[] getPlantTextures() {
        return Arrays.copyOf(plant_textures, plant_textures.length);
    }

    public String getPlantTexture(int stage) {
        return this.plant_textures[stage % this.plant_textures.length];
    }

    public boolean validate() {
        if (!AgriCore.getValidator().isValidResource(seed_model)) {
            AgriCore.getCoreLogger().debug("Invalid AgriTexture! Invalid Seed Texture: \"{0}\"!", seed_model);
            return false;
        }
        for (String texture : plant_textures) {
            if (!AgriCore.getValidator().isValidResource(texture)) {
                AgriCore.getCoreLogger().debug("Invalid AgriTexture! Invalid Plant Texture: \"{0}\"!", texture);
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("Icon Set:\n");
        sb.append("\t- Seed Texture: ").append(this.seed_model).append("\n");
        sb.append("\t- Render Type: ").append(this.render_type).append("\n");
        sb.append("\t- Supported Number of Growth Stages:").append(this.plant_textures.length).append("\n");
        sb.append("\t- Plant Textures:\n");
        for (int i = 0; i < this.plant_textures.length; i++) {
            sb.append("\t\t- ").append(i).append(" : ").append(this.plant_textures[i]).append("\n");
        }
        return sb.toString();
    }

}
