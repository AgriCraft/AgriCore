package com.agricraft.agricore.templates.versions.v2;

import com.agricraft.agricore.templates.AgriFertilizerEffect;
import com.agricraft.agricore.templates.AgriFertilizerParticle;

import java.util.ArrayList;
import java.util.List;

public class AgriFertilizerEffect_1_16 {

    private final boolean reduce_growth;
    private final boolean kill_plant;
    private final AgriPlantList_1_16 negatively_affected;
    private final AgriPlantList_1_16 positively_affected;
    private final List<AgriFertilizerParticle> particles;

    public AgriFertilizerEffect_1_16() {
        reduce_growth = true;
        kill_plant = true;
        negatively_affected = new AgriPlantList_1_16();
        positively_affected = new AgriPlantList_1_16();
        particles = new ArrayList<>();
    }

    public AgriFertilizerEffect_1_16(boolean reduce_growth, boolean kill_plant, AgriPlantList_1_16 negatively_affected, AgriPlantList_1_16 positively_affected, List<AgriFertilizerParticle> particles) {
        this.reduce_growth = reduce_growth;
        this.kill_plant = kill_plant;
        this.negatively_affected = negatively_affected;
        this.positively_affected = positively_affected;
        this.particles = particles;
    }

    public AgriFertilizerEffect toNew() {
        return new AgriFertilizerEffect(this.reduce_growth, this.kill_plant, this.negatively_affected.toNew(), this.positively_affected.toNew(), this.particles);
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

}
