package com.agricraft.agricore.templates;

public abstract class AgriParticle {

    protected final String particle;
    protected final double delta_x;
    protected final double delta_y;
    protected final double delta_z;

    public AgriParticle() {
        this.particle = "minecraft:block";
        this.delta_x = 0F;
        this.delta_y = 0F;
        this.delta_z = 0F;
    }

    public AgriParticle(String particle, double deltaX, double deltaY, double deltaZ) {
        this.particle = particle;
        this.delta_x = deltaX;
        this.delta_y = deltaY;
        this.delta_z = deltaZ;
    }

    public String getParticle() {
        return this.particle;
    }

    public double getDeltaX() {
        return this.delta_x;
    }

    public double getDeltaY() {
        return this.delta_y;
    }

    public double getDeltaZ() {
        return this.delta_z;
    }

    public abstract boolean validate();
}
