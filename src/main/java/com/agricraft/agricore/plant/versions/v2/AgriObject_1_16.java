package com.agricraft.agricore.plant.versions.v2;

import com.agricraft.agricore.plant.AgriBlock;
import com.agricraft.agricore.plant.AgriObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AgriObject_1_16 {
    protected final String type;
    protected final String object;
    protected final boolean useTag;
    protected final String data;
    protected final List<String> ignoredData;

    public AgriObject_1_16() {
        this("block", "minecraft:dirt");
    }
    
    public AgriObject_1_16(String type, String object) {
        this(type, object,false);
    }
    
    public AgriObject_1_16(String type, String object, boolean useTags) {
        this(type, object, useTags, "");
    }

    public AgriObject_1_16(String type, String object, boolean useTags, String data, String... ignoredData) {
        this(type, object, useTags, data, Arrays.asList(ignoredData));
    }

    public AgriObject_1_16(String type, String object, boolean useTags, String data, List<String> ignoredData) {
        this.type = type;
        this.object = object;
        this.useTag = useTags;
        this.data = data;
        this.ignoredData = ignoredData;
    }

    public AgriObject toNew() {
        return new AgriObject(this.type, this.object, this.useTag, this.data, this.ignoredData);
    }

    public AgriBlock toBlock() {
        return new AgriBlock(this.type, this.object, this.useTag, Collections.emptyList(), this.data, this.ignoredData);
    }
}
