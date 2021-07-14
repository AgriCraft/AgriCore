package com.agricraft.agricore.plant.particle;

import com.agricraft.agricore.core.AgriCore;

import java.util.Arrays;

public class AgriParticleEffect extends AgriParticle {

    private final double probability;
    private final int[] stages;

    public AgriParticleEffect() {
        super();
        this.probability = 0F;
        this.stages = new int[0];
    }

    public AgriParticleEffect(String particle, double deltaX, double deltaY, double deltaZ, double probability, int[] stages) {
        super(particle, deltaX, deltaY, deltaZ);
        this.probability = probability;
        this.stages = stages;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nAgriParticleEffect:");
        sb.append("\n\t- Particle: ").append(particle);
        sb.append("\n\t- DeltaX: ").append(delta_x);
        sb.append("\n\t- DeltaY: ").append(delta_y);
        sb.append("\n\t- DeltaZ: ").append(delta_z);
        sb.append("\n\t- Probability: ").append(probability);
        sb.append("\n\t- Stages: ").append(Arrays.toString(stages));
        return sb.toString();
    }

    public double getProbability() {
        return this.probability;
    }

    public boolean allowParticles(int index) {
        return Arrays.stream(stages).anyMatch(value -> value == index);
    }

    @Override
    public boolean validate() {
        if (!AgriCore.getValidator().isValidResource(this.particle)) {
            AgriCore.getCoreLogger().info("Invalid AgriParticleEffect! Invalid Particle Resource: \"{0}\"!", this.particle);
            return false;
        }
        return true;
    }

}
