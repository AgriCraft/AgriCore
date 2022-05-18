package com.agricraft.agricore.templates.versions.v2;

import com.agricraft.agricore.templates.AgriPlantList;

import java.util.Collections;
import java.util.List;

public class AgriPlantList_1_16 {

    private final boolean blacklist;
    private final List<String> plant_list;

    public AgriPlantList_1_16() {
        this(true, Collections.emptyList());
    }


    public AgriPlantList_1_16(boolean blacklist, List<String> plant_list) {
        this.blacklist = blacklist;
        this.plant_list = plant_list;
    }

    public AgriPlantList toNew() {
        return new AgriPlantList(this.blacklist, this.convertIds());
    }

    private List<String> convertIds() {
        return this.plant_list.stream().map(Versions_1_16::convertId).toList();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nAgriPlantList:");
        sb.append("\n\t- Blacklist: ").append(blacklist);
        sb.append("\n\t- Plant List: ").append(plant_list.stream().collect(StringBuilder::new, (builder, item) -> builder.append("\n\t").append(item), StringBuilder::append).toString().replaceAll("\n", "\n\t").trim());
        return sb.toString();
    }

}
