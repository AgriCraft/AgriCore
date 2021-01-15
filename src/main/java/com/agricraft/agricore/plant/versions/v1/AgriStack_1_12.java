package com.agricraft.agricore.plant.versions.v1;

import com.agricraft.agricore.plant.AgriObject;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.List;

public class AgriStack_1_12 {
    protected final String item;
    protected final boolean useOreDict;
    protected final String tags;
    protected final List<String> ignoreTags;

    public AgriStack_1_12() {
        this("minecraft:dirt");
    }

    public AgriStack_1_12(String item) {
        this(item, true);
    }

    public AgriStack_1_12(String item, boolean useOreDict) {
        this(item, useOreDict, "");
    }

    public AgriStack_1_12(String item, boolean useOreDict, String tags, String... ignoreTags) {
        this(item, useOreDict, tags, Arrays.asList(ignoreTags));
    }

    public AgriStack_1_12(String item, boolean useOreDict, String tags, List<String> ignoreTags) {
        this.item = item;
        this.tags = tags;
        this.ignoreTags = ignoreTags;
        this.useOreDict = useOreDict;
    }

    public AgriObject toNew(String type) {
        return new AgriObject(type, this.transformItem(), this.useTag(), this.tags, this.ignoreTags);
    }

    private final List<String> TYPES = ImmutableList.of(
            "ore",
            "nugget",
            "dust"
    );

    protected boolean useTag() {
        return this.item.split(":")[0].equalsIgnoreCase("oredict");
    }

    protected String transformItem() {
        if(this.useTag()) {
            String[] split = this.item.split(":");
            String domain;
            String path;
            if(split.length == 1) {
                domain = "minecraft";
                path = split[0];
            } else {
                domain = split[0];
                path = split[1];
            }
            if(domain.equalsIgnoreCase("oredict")) {
                domain = "forge";
            }
            for(String type : this.TYPES) {
                String corrected = this.handlePath(type, path);
                if(!corrected.equals(path)) {
                    path = corrected;
                    break;
                }
            }
            return domain + ":" + path;
        } else {
            return this.item;
        }
    }

    private String handlePath(String type, String path) {
        if(path.contains(type)) {
            int index = path.indexOf(type);
            String sub = path.substring(index + type.length());
            return type.toLowerCase() + "s/" + sub.toLowerCase();
        }
        return path;
    }
}
