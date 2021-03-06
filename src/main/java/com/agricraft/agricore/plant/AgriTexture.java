package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AgriTexture {

    private String render_type = "hash";

    private final String[] plant_models = new String[]{};

    private final String[][] plant_textures = new String[][]{
            new String[]{"minecraft:block/wheat_stage0"},
            new String[]{"minecraft:block/wheat_stage1"},
            new String[]{"minecraft:block/wheat_stage2"},
            new String[]{"minecraft:block/wheat_stage3"},
            new String[]{"minecraft:block/wheat_stage4"},
            new String[]{"minecraft:block/wheat_stage5"},
            new String[]{"minecraft:block/wheat_stage6"},
            new String[]{"minecraft:block/wheat_stage7"}
    };

    // GSON Tricker
    public AgriTexture() {
    }

    public AgriTexture(String render_type, String[][] plant_textures) {

        this.render_type = render_type;

        System.arraycopy(plant_textures, 0, this.plant_textures, 0, Math.min(plant_textures.length, this.plant_textures.length));

        // Distribute the textures.
        String[] last = new String[]{"NO TEXTURE!"};
        for (int i = 0; i < this.plant_textures.length; i++) {
            if (this.plant_textures[i] == null) {
                this.plant_textures[i] = last;
            } else {
                last = this.plant_textures[i];
            }
        }

    }

    public String getRenderType() {
        return render_type;
    }
    
    public int getGrowthStages() {
        return plant_textures.length;
    }

    public List<String> getAllTextures() {
        return Arrays.stream(this.plant_textures).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
    }

    public String[][] getPlantTextures() {
        return Arrays.copyOf(plant_textures, plant_textures.length);
    }

    public String[] getPlantTextures(int stage) {
        return this.plant_textures[stage % this.plant_textures.length];
    }

    public boolean useModels() {
        return this.plant_models.length > 0;
    }

    public String getPlantModel(int index) {
        return this.plant_models[index % this.plant_models.length];
    }

    public boolean validate() {
        for (String[] textures : plant_textures) {
            for(String texture : textures) {
                if (!AgriCore.getValidator().isValidResource(texture)) {
                    AgriCore.getCoreLogger().info("Invalid AgriTexture! Invalid Plant Texture: \"{0}\"!", texture);
                    return false;
                }
            }
        }
        if(this.useModels()) {
            for(String model : plant_models) {
                if (!AgriCore.getValidator().isValidResource(model)) {
                    AgriCore.getCoreLogger().info("Invalid AgriTexture! Invalid Plant Model: \"{0}\"!", model);
                    return false;
                }
            }
        }
        if(!AgriCore.getValidator().isValidRenderType(render_type)) {
            AgriCore.getCoreLogger().info("Invalid AgriTexture! Invalid Render Type: \"{0}\"!", render_type);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("Icon Set:\n");
        sb.append("\t- Render Type: ").append(this.render_type).append("\n");
        sb.append("\t- Supported Number of Growth Stages:").append(this.plant_textures.length).append("\n");
        sb.append("\t- Plant Textures:\n");
        for (int i = 0; i < this.plant_textures.length; i++) {
            sb.append("\t\t- ").append(i).append(" : ").append(this.plant_textures[i]).append("\n");
        }
        return sb.toString();
    }

}
