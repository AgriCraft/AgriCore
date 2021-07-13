package com.agricraft.agricore.plant.fertilizer;

import com.agricraft.agricore.plant.particle.AgriFertilizerParticle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AgriFertilizerEffect {

    private final boolean reduce_growth;
    private final boolean kill_plant;
    private final AgriPlantList negatively_affected;
    private final AgriPlantList positively_affected;
    private final List<AgriFertilizerParticle> particles;

    public AgriFertilizerEffect() {
        reduce_growth = true;
        kill_plant = true;
        negatively_affected = new AgriPlantList();
        positively_affected = new AgriPlantList();
        particles = new ArrayList<>();
    }

    public AgriFertilizerEffect(boolean reduce_growth, boolean kill_plant, AgriPlantList negatively_affected, AgriPlantList positively_affected, List<AgriFertilizerParticle> particles) {
        this.reduce_growth = reduce_growth;
        this.kill_plant = kill_plant;
        this.negatively_affected = negatively_affected;
        this.positively_affected = positively_affected;
        this.particles = particles;
    }

    public boolean canReduceGrowth() {
        return this.reduce_growth;
    }

    public boolean canKillPlant() {
        return this.kill_plant;
    }

    public boolean canFertilize(String plantId) {
        return positively_affected.contains(plantId) || negatively_affected.contains(plantId);
    }

    public List<AgriFertilizerParticle> getParticles(String plantId) {
        String type = "neutral";
        if (negatively_affected.contains(plantId)) {
            type = "negative";
        } else if (positively_affected.contains(plantId)) {
            type = "positive";
        }
        String finalType = type;
        return particles.stream().filter(particle -> particle.shouldSpawn(finalType)).collect(Collectors.toList());

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nAgriFertilizerEffect:");
        sb.append("\n\t- Reduce Growth: ").append(reduce_growth);
        sb.append("\n\t- Kill Plant: ").append(kill_plant);
        sb.append("\n\t- Negatively Affected: ").append(negatively_affected.toString().replaceAll("\n", "\n\t").trim());
        sb.append("\n\t- Positively Affected: ").append(positively_affected.toString().replaceAll("\n", "\n\t").trim());
        sb.append("\n\t- Particles: \n\t").append(particles.stream().collect(StringBuilder::new, (builder, item) -> builder.append("\n\t").append(item), StringBuilder::append).toString().replaceAll("\n", "\n\t").trim());
        return sb.toString();
    }

    public boolean validate() {
        this.particles.removeIf(particle -> !particle.validate());
        return negatively_affected.validate() && positively_affected.validate();
    }
}
