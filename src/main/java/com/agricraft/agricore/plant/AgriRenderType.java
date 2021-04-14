package com.agricraft.agricore.plant;

import com.google.gson.annotations.SerializedName;

public enum AgriRenderType {

    @SerializedName(value = "hash", alternate = {"HASH", "#"})
    HASH('#'),
    @SerializedName(value = "cross", alternate = {"CROSS", "x", "X"})
    CROSS('X'),
    @SerializedName(value = "plus", alternate = {"PLUS", "+"})
    PLUS('+', 0.75F),
    @SerializedName(value = "rhombus", alternate = {"RHOMBUS", "⬦", "◇"})
    RHOMBUS('◇');

    private final char id;
    private final float heightModifier;

    AgriRenderType(char id) {
        this(id, 1.0F);
    }

    AgriRenderType(char id, float heightModifier) {
        this.id = id;
        this.heightModifier = heightModifier;
    }

    public char id() {
        return id;
    }

    public float getHeightModifier() {
        return this.heightModifier;
    }

    @Override
    public String toString() {
        return String.format("Render Type: %s (\'%c\')\n", name(), id());
    }

}
