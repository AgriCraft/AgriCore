package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;

import java.util.Arrays;

public class AgriFertilizerParticle extends AgriParticle {

    private final int amount;
    private final String[] when;

    public AgriFertilizerParticle() {
        super();
        this.amount = 2;
        this.when = new String[0];
    }

    public AgriFertilizerParticle(String particle, double deltaX, double deltaY, double deltaZ, int amount, String[] when) {
        super(particle, deltaX, deltaY, deltaZ);
        this.amount = amount;
        this.when = when;
    }

    public boolean shouldSpawn(String type) {
        return Arrays.asList(when).contains(type);
    }

    public int getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nAgriFertilizerParticle:");
        sb.append("\n\t- Particle: ").append(particle);
        sb.append("\n\t- DeltaX: ").append(delta_x);
        sb.append("\n\t- DeltaY: ").append(delta_y);
        sb.append("\n\t- DeltaZ: ").append(delta_z);
        sb.append("\n\t- Amount: ").append(amount);
        sb.append("\n\t- When: ").append(Arrays.toString(when));
        return sb.toString();
    }

    @Override
    public boolean validate() {
        if (!AgriCore.getValidator().isValidResource(this.particle)) {
            AgriCore.getCoreLogger().info("Invalid AgriFertilizerParticle! Invalid Particle Resource: \"{0}\"!", this.particle);
            return false;
        }
        return true;
    }
}
