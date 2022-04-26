package com.agricraft.agricore.templates;

import com.agricraft.agricore.core.AgriCore;
import com.google.common.collect.Streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AgriTexture {

    private String render_type;
    private final String[] plant_models;
    private final String[][] plant_textures;
    private final List<AgriJournalTexture> journal_overrides;

    // GSON Tricker
    public AgriTexture() {
        this.render_type = "hash";
        this.plant_models = new String[]{};
        this.plant_textures = new String[][]{
                new String[]{"minecraft:block/wheat_stage0"},
                new String[]{"minecraft:block/wheat_stage1"},
                new String[]{"minecraft:block/wheat_stage2"},
                new String[]{"minecraft:block/wheat_stage3"},
                new String[]{"minecraft:block/wheat_stage4"},
                new String[]{"minecraft:block/wheat_stage5"},
                new String[]{"minecraft:block/wheat_stage6"},
                new String[]{"minecraft:block/wheat_stage7"}
        };
        this.journal_overrides = Collections.emptyList();
    }

    public AgriTexture(String render_type, String[][] plant_textures, String[] plant_models, List<AgriJournalTexture> journal_overrides) {
        this.render_type = render_type;
        this.plant_textures = plant_textures;
        this.plant_models = plant_models;
        this.journal_overrides = journal_overrides;
    }

    public String getRenderType() {
        return render_type;
    }

    public int getGrowthStages() {
        return plant_textures.length;
    }

    public List<String> getAllTextures() {
        return Streams.concat(
                Arrays.stream(this.plant_textures).flatMap(Arrays::stream),
                this.getJournalOverrides().stream().map(AgriJournalTexture::getTexture)
        ).distinct().collect(Collectors.toList());
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

    public List<AgriJournalTexture> getJournalOverrides() {
        return this.journal_overrides;
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
