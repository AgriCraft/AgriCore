/*
 */
package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class AgriStack {
    
    protected final String item;
    
    protected final int meta;
    
    protected final String tags;
    
    protected final boolean ignoreMeta;
    
    protected final List<String> ignoreTags;
    
    protected final boolean useOreDict;

    public AgriStack() {
        this.item = "minecraft:dirt";
        this.meta = 0;
        this.tags = "";
        this.ignoreMeta = false;
        this.ignoreTags = new ArrayList<>();
        this.useOreDict = false;
    }
    
    public AgriStack(String item, int meta, String tags, boolean ignoreMeta, boolean useOreDict, String... ignoreTags) {
        this(item, meta, tags, ignoreMeta, useOreDict, Arrays.asList(ignoreTags));
    }

    public AgriStack(String item, int meta, String tags, boolean ignoreMeta, boolean useOreDict, List<String> ignoreTags) {
        this.item = item;
        this.meta = meta;
        this.tags = tags;
        this.ignoreMeta = ignoreMeta;
        this.ignoreTags = ignoreTags;
        this.useOreDict = useOreDict;
    }

    public String getItem() {
        return item;
    }

    public int getMeta() {
        return meta;
    }

    public String getTags() {
        return tags;
    }
    
    public List<String> getIgnoreTags() {
        return ignoreTags;
    }

    public boolean isIgnoreMeta() {
        return ignoreMeta;
    }

    public boolean isUseOreDict() {
        return useOreDict;
    }
    
    public Object toStack() {
        return this.toStack(1);
    }
    
    public Object toStack(int amount) {
        return AgriCore.getConverter().toStack(item, meta, amount, tags, ignoreMeta, useOreDict, ignoreTags);
    }
    
    public boolean validate() {
        return AgriCore.getValidator().isValidItem(item);
    }
    
    @Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nStack:");
		sb.append("\n\t- Item: ").append(item);
		sb.append("\n\t- Meta: ").append(meta);
		sb.append("\n\t- Tags: ").append(tags);
		sb.append("\n\t- IgnoreMeta: ").append(ignoreMeta);
		sb.append("\n\t- IgnoreTags: ").append(ignoreTags);
		sb.append("\n\t- UseOreDict: ").append(useOreDict);
		return sb.toString();
	}
    
}
