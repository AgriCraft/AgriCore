package com.agricraft.agricore.plant;

public class AgriParticleEffect {

    private final String id;
    private final double delta_x;
    private final double delta_y;
    private final double delta_z;
    private final double probability;

    public AgriParticleEffect() {
        this.id = "minecraft:block";
        this.delta_x = 0F;
        this.delta_y = 0F;
        this.delta_z = 0F;
        this.probability = 0F;
    }

    public AgriParticleEffect(String id, double deltaX, double deltaY, double deltaZ, double probability) {
        this.id = id;
        this.delta_x = deltaX;
        this.delta_y = deltaY;
        this.delta_z = deltaZ;
        this.probability = probability;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nParticleEffect:");
        sb.append("\n\t- Id:").append(id);
        sb.append("\n\t- DeltaX: ").append(delta_x);
        sb.append("\n\t- DeltaY: ").append(delta_y);
        sb.append("\n\t- DeltaZ: ").append(delta_z);
        sb.append("\n\t- Probability: ").append(probability);
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public double getDeltaX() {
        return delta_x;
    }

    public double getDeltaY() {
        return delta_y;
    }

    public double getDeltaZ() {
        return delta_z;
    }

    public double getProbability() {
        return probability;
    }

}
