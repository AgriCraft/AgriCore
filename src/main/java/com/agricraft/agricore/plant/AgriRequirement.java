package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.util.TypeHelper;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AgriRequirement {

    private final int min_light;
    private final int max_light;

    private final List<String> soils;
    private final List<String> seasons;
    private final List<AgriBlockCondition> conditions;

    public AgriRequirement() {
        this.min_light = 10;
        this.max_light = 16;
        this.soils = new ArrayList<>();
        this.seasons = Lists.newArrayList("spring", "summer", "autumn", "winter");
        this.conditions = new ArrayList<>();
    }

    public AgriRequirement(List<String> soils, List<String> seasons, List<AgriBlockCondition> conditions, int min_light, int max_light) {
        this.soils = new ArrayList<>(soils);
        this.seasons = new ArrayList<>(seasons);
        this.conditions = conditions;
        this.min_light = min_light;
        this.max_light = max_light;
    }

    public int getMinLight() {
        return min_light;
    }

    public int getMaxLight() {
        return max_light;
    }

    public List<AgriSoil> getSoils() {
        return this.soils.stream()
                .map(AgriCore.getSoils()::getSoil)
                .filter(TypeHelper::isNonNull)
                .collect(Collectors.toList());
    }

    public List<String> getSeasons() {
        return this.seasons;
    }

    public List<AgriBlockCondition> getConditions() {
        return new ArrayList<>(this.conditions);
    }

    public boolean validate() {
        this.soils.removeIf(soil -> {
            if (!AgriCore.getSoils().hasSoil(soil)) {
                AgriCore.getCoreLogger().info("Invalid Requirement: Invalid Soil: {0}! Removing!", soil);
                return true;
            } else {
                return false;
            }
        });
        for (AgriBlockCondition condition : conditions) {
            if (!condition.validate()) {
                AgriCore.getCoreLogger().info("Invalid Requirement: Invalid Condition!", condition);
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nRequirement:");
        sb.append("\n\t- Light:");
        sb.append("\n\t\t- Min: ").append(min_light);
        sb.append("\n\t\t- Max: ").append(max_light);
        sb.append("\n\t- Soil:");
        this.soils.forEach((e) -> {
            sb.append("\n\t\t- AgriSoil: ").append(e);
        });
        sb.append("\n\t- Conditions:");
        this.conditions.forEach((e) -> {
            sb.append("\n\t\t- ").append(e.toString().replaceAll("\n", "\n\t\t").trim());
        });
        return sb.toString();
    }

}
