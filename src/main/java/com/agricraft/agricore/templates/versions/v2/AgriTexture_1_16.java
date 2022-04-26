package com.agricraft.agricore.templates.versions.v2;

import com.agricraft.agricore.templates.AgriTexture;
import java.util.Collections;

public class AgriTexture_1_16 {

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
    public AgriTexture_1_16() {
    }

    public AgriTexture_1_16(String render_type, String[][] plant_textures) {

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

    public AgriTexture toNew() {
        return new AgriTexture(this.render_type, this.plant_textures, this.plant_models, Collections.emptyList());
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
