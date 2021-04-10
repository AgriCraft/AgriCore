package com.agricraft.agricore.plant.versions.v1;

import com.agricraft.agricore.plant.AgriObject;
import com.agricraft.agricore.plant.AgriRequirement;
import com.agricraft.agricore.plant.AgriSoilCondition;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AgriRequirement_1_12 {
    private final int min_light;
    private final int max_light;

    private final List<String> soils;
    private final List<AgriBlockCondition_1_12> conditions;

    public AgriRequirement_1_12() {
        this.min_light = 10;
        this.max_light = 16;
        this.soils = new ArrayList<>();
        this.conditions = new ArrayList<>();
    }

    public AgriRequirement_1_12(List<String> soils, List<AgriBlockCondition_1_12> conditions, int min_light, int max_light) {
        this.soils = new ArrayList<>(soils);
        this.conditions = conditions;
        this.min_light = min_light;
        this.max_light = max_light;
    }

    public AgriRequirement toNew() {
        return new AgriRequirement(
                new AgriSoilCondition(),
                new AgriSoilCondition(),
                new AgriSoilCondition(),
                min_light, max_light, 0.5D, Lists.newArrayList("spring", "summer", "autumn", "winter"),
                conditions.stream().map(AgriBlockCondition_1_12::toNew).collect(Collectors.toList()),
                new AgriObject("fluid", "minecraft:empty")
        );
    }
}
