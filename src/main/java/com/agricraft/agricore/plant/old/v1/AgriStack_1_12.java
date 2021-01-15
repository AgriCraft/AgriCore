package com.agricraft.agricore.plant.old.v1;

import com.agricraft.agricore.plant.AgriObject;

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

    public AgriObject toNew() {
        return new AgriObject("item", this.getItem(), this.isUseOreDict(), this.getTags(), this.getIgnoreTags());
    }

    public String getItem() {
        return item;
    }

    public String getTags() {
        return tags;
    }

    public List<String> getIgnoreTags() {
        return ignoreTags;
    }

    public boolean isUseOreDict() {
        return useOreDict;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nStack:");
        sb.append("\n\t- Item: ").append(item);
        sb.append("\n\t- Tags: ").append(tags);
        sb.append("\n\t- IgnoreTags: ").append(ignoreTags);
        sb.append("\n\t- UseOreDict: ").append(useOreDict);
        return sb.toString();
    }
}
