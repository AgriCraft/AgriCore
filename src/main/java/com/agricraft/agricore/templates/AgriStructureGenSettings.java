package com.agricraft.agricore.templates;

import java.util.Collections;
import java.util.List;

public class AgriStructureGenSettings {
    private final List<String> structures;
    private final int weight;
    private final int statsMin;
    private final int statsMax;

    public AgriStructureGenSettings() {
        this.structures = Collections.emptyList();
        this.weight = 10;
        this.statsMin = 1;
        this.statsMax = 10;
    }

    public AgriStructureGenSettings(List<String> structures, int weight, int statsMin, int statsMax) {
        this.structures = structures;
        this.weight = weight;
        this.statsMin = statsMin;
        this.statsMax = statsMax;
    }

    public List<String> getStructures() {
        return this.structures;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getStatsMin() {
        return this.statsMin;
    }

    public int getStatsMax() {
        return this.statsMax;
    }
}
