package com.agricraft.agricore.plant.versions.v2;

import com.agricraft.agricore.plant.AgriBlockCondition;
import com.agricraft.agricore.plant.AgriListCondition;
import com.agricraft.agricore.plant.AgriRequirement;
import com.agricraft.agricore.plant.AgriSoilCondition;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AgriRequirement_1_16 {
    private final AgriSoilCondition soil_humidity;
    private final AgriSoilCondition soil_acidity;
    private final AgriSoilCondition soil_nutrients;

    private final int min_light;
    private final int max_light;
    private final double light_tolerance_factor;

    private final List<String> seasons;
    private final List<AgriBlockCondition_1_16> conditions;
    private final AgriObject_1_16 fluid;

    public AgriRequirement_1_16() {
        this.soil_humidity = new AgriSoilCondition("damp", "equal", 0.15D);
        this.soil_acidity = new AgriSoilCondition("neutral", "equal", 0.2D);
        this.soil_nutrients = new AgriSoilCondition("medium", "equal_or_higher", 0.1D);
        this.min_light = 10;
        this.max_light = 16;
        this.light_tolerance_factor = 0.5D;
        this.seasons = Lists.newArrayList("spring", "summer", "autumn", "winter");
        this.conditions = new ArrayList<>();
        this.fluid = new AgriObject_1_16("fluid", "minecraft:empty");
    }

    public AgriRequirement_1_16(AgriSoilCondition soil_humidity, AgriSoilCondition soil_acidity, AgriSoilCondition soil_nutrients,
                                int min_light, int max_light, double light_tolerance_factor, List<String> seasons,
                                List<AgriBlockCondition_1_16> conditions, AgriObject_1_16 fluid) {
        this.soil_humidity = soil_humidity;
        this.soil_acidity = soil_acidity;
        this.soil_nutrients = soil_nutrients;
        this.min_light = min_light;
        this.max_light = max_light;
        this.light_tolerance_factor = light_tolerance_factor;
        this.seasons = new ArrayList<>(seasons);
        this.conditions = conditions;
        this.fluid = fluid;
    }

    public AgriRequirement toNew() {
        return new AgriRequirement(this.soil_humidity, this.soil_acidity, this.soil_nutrients, this.min_light, this.max_light,
                this.light_tolerance_factor, new AgriListCondition(), new AgriListCondition(), this.seasons, this.convertConditions(), this.fluid.toBlock());
    }

    private List<AgriBlockCondition> convertConditions() {
        return this.conditions.stream()
                .map(AgriBlockCondition_1_16::toNew)
                .collect(Collectors.toList());
    }
}
