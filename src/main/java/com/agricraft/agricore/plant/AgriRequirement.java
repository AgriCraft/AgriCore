/*
 */
package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.util.TypeHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author RlonRyan
 */
public class AgriRequirement {

    private final int min_light;
    private final int max_light;

    private final List<String> soils;
    private final List<String> bases;

    private final Map<String, Integer> nearby;

    public AgriRequirement() {
        this.min_light = 10;
        this.max_light = 16;
        this.soils = new ArrayList<>();
        this.bases = new ArrayList<>();
        this.nearby = new HashMap<>();
    }

    public AgriRequirement(List<String> soils, List<String> bases, Map<String, Integer> nearby, int min_light, int max_light) {
        this.soils = new ArrayList<>(soils);
        this.bases = bases;
        this.nearby = nearby;
        this.min_light = 10;
        this.max_light = 16;
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

    public List<Object> getBases() {
        List<Object> res = new ArrayList<>(this.bases.size());
        bases.forEach((b) -> {
            Object stack = AgriCore.getConverter().toStack(b);
            if (stack != null) {
                res.add(stack);
            }
        });
        return res;
    }

    public Map<Object, Integer> getNearby() {
        Map<Object, Integer> res = new HashMap<>();
        nearby.forEach((block, dist) -> {
            Object stack = AgriCore.getConverter().toStack(block);
            if (stack != null) {
                res.put(stack, dist);
            }
        });
        return res;
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
        for (String block : bases) {
            if (!AgriCore.getValidator().isValidBlock(block)) {
                AgriCore.getCoreLogger().info("Invalid Requirement: Invalid Base: {0}!", block);
                return false;
            }
        }
        for (String block : nearby.keySet()) {
            if (!AgriCore.getValidator().isValidBlock(block)) {
                AgriCore.getCoreLogger().info("Invalid Requirement: Invalid Nearby: {0}!", block);
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nRequirement:\n");
        sb.append("\t- Light:\n");
        sb.append("\t\t- Min: ").append(min_light).append("\n");
        sb.append("\t\t- Max: ").append(max_light).append("\n");
        sb.append("\t- Soil:\n");
        this.soils.forEach((e) -> {
            sb.append("\t\t- AgriSoil: ").append(e).append("\n");
        });
        sb.append("\t- Base:\n");
        this.bases.forEach((e) -> {
            sb.append("\t\t- Block: ").append(e).append("\n");
        });
        sb.append("\t- Nearby:\n");
        this.nearby.entrySet().forEach((e) -> {
            sb.append("\t\t- Block: ").append(e.getKey()).append("\n\t");
            sb.append("\t\t- Distance: ").append(e.getValue()).append("\n");
        });
        return sb.toString();
    }

}
