package com.agricraft.agricore.templates.versions.v1;

import com.agricraft.agricore.templates.versions.v2.AgriBlockCondition_1_16;

import java.util.Arrays;
import java.util.List;

public class AgriBlockCondition_1_12 extends AgriStack_1_12 {
    protected final int amount;
    protected final int min_x;
    protected final int min_y;
    protected final int min_z;
    protected final int max_x;
    protected final int max_y;
    protected final int max_z;

    public AgriBlockCondition_1_12() {
        super();
        this.amount = 1;
        this.min_x = 0;
        this.min_y = -2;
        this.min_z = 0;
        this.max_x = 0;
        this.max_y = -2;
        this.max_z = 0;
    }

    public AgriBlockCondition_1_12(int amount, int min_x, int min_y, int min_z, int max_x, int max_y, int max_z, String item, boolean useOreDict, String tags, String... ignoreTags) {
        this(amount, min_x, min_y, min_z, max_x, max_y, max_z, item, useOreDict, tags, Arrays.asList(ignoreTags));
    }

    public AgriBlockCondition_1_12(int amount, int min_x, int min_y, int min_z, int max_x, int max_y, int max_z, String item, boolean useOreDict, String tags, List<String> ignoreTags) {
        super(item, useOreDict, tags, ignoreTags);
        this.amount = amount;
        this.min_x = min_x;
        this.min_y = min_y;
        this.min_z = min_z;
        this.max_x = max_x;
        this.max_y = max_y;
        this.max_z = max_z;
    }

    public AgriBlockCondition_1_16 toNew() {
        return new AgriBlockCondition_1_16(11, amount, min_x, min_y, min_z, max_x, max_y, max_z, this.transformItem(), this.useTag(), tags, ignoreTags);
    }
}
