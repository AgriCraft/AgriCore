package com.agricraft.agricore.templates;

import com.agricraft.agricore.core.AgriCore;

import java.util.Collections;
import java.util.List;

public class AgriPlantList {

    private final boolean blacklist;
    private final List<String> plant_list;

    public AgriPlantList() {
        this(true, Collections.emptyList());
    }


    public AgriPlantList(boolean blacklist, List<String> plant_list) {
        this.blacklist = blacklist;
        this.plant_list = plant_list;
    }

    public boolean isAffected(String plantId) {
        if (plant_list.contains(plantId)) {
            return !blacklist;
        }
        return blacklist;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nAgriPlantList:");
        sb.append("\n\t- Blacklist: ").append(blacklist);
        sb.append("\n\t- Plant List: ").append(plant_list.stream().collect(StringBuilder::new, (builder, item) -> builder.append("\n\t").append(item), StringBuilder::append).toString().replaceAll("\n", "\n\t").trim());
        return sb.toString();
    }

    public boolean validate() {
        if (!blacklist) {
            for (String plantId : plant_list) {
                if (AgriCore.getPlants().hasPlant(plantId)) {
                    return true;
                }
            }
            AgriCore.getCoreLogger().info("Invalid Whitelist : The list does not contains valid plants!");
        }
        return true;
    }
}
