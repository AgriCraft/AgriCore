package com.agricraft.agricore.templates;

import com.google.common.collect.Lists;

import java.util.List;

public class AgriStructureGenSettings {
    private final List<String> structures;
    private final int weight;
    private final int statsMin;
    private final int statsMax;

    public AgriStructureGenSettings() {
        this.structures = Lists.newArrayList(
                "agricraft:village/desert/greenhouse",
                "agricraft:village/plains/greenhouse",
                "agricraft:village/savanna/greenhouse",
                "agricraft:village/snowy/greenhouse",
                "agricraft:village/taiga/greenhouse",
                "agricraft:village/desert/greenhouse_irrigated",
                "agricraft:village/plains/greenhouse_irrigated",
                "agricraft:village/savanna/greenhouse_irrigated",
                "agricraft:village/snowy/greenhouse_irrigated",
                "agricraft:village/taiga/greenhouse_irrigated"
        );
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