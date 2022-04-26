package com.agricraft.agricore.templates;

public class AgriJournalTexture {
    private final int stage;
    private final String texture;

    public AgriJournalTexture() {
        this.stage = 0;
        this.texture = "minecraft:missing_sprite";
    }

    public AgriJournalTexture(int stage, String texture) {
        this.stage = stage;
        this.texture = texture;
    }

    public int getStage() {
        return this.stage;
    }

    public String getTexture() {
        return this.texture;
    }
}