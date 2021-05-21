package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;

import java.util.Arrays;

public class AgriParticleEffect {

    private final String particle;
    private final double delta_x;
    private final double delta_y;
    private final double delta_z;
    private final double probability;
    private final int[] stages;

    public AgriParticleEffect() {
        this.particle = "minecraft:block";
        this.delta_x = 0F;
        this.delta_y = 0F;
        this.delta_z = 0F;
        this.probability = 0F;
        this.stages = new int[0];
    }

    public AgriParticleEffect(String particle, double deltaX, double deltaY, double deltaZ, double probability, int[] stages) {
        this.particle = particle;
        this.delta_x = deltaX;
        this.delta_y = deltaY;
        this.delta_z = deltaZ;
        this.probability = probability;
        this.stages = stages;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nParticleEffect:");
        sb.append("\n\t- Particle: ").append(particle);
        sb.append("\n\t- DeltaX: ").append(delta_x);
        sb.append("\n\t- DeltaY: ").append(delta_y);
        sb.append("\n\t- DeltaZ: ").append(delta_z);
        sb.append("\n\t- Probability: ").append(probability);
        sb.append("\n\t- Stages: ").append(Arrays.toString(stages));
        return sb.toString();
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

    public double getProbability() {
        return this.probability;
    }

    public boolean allowParticles(int index) {
        return Arrays.stream(stages).anyMatch(value -> value == index);
    }

    public boolean validate() {
        if (!AgriCore.getValidator().isValidResource(this.particle)) {
            AgriCore.getCoreLogger().info("Invalid AgriParticleEffect! Invalid Particle Resource: \"{0}\"!", this.particle);
            return false;
        }
        return true;
    }

}
