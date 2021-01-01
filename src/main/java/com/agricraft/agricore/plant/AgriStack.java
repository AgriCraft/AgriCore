package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AgriStack {

    protected final String item;

    protected final String tags;

    protected final List<String> ignoreTags;

    protected final boolean useOreDict;

    public AgriStack() {
        this("minecraft:dirt");
    }
    
    public AgriStack(String item) {
        this(item,false);
    }
    
    public AgriStack(String item, boolean useOreDict) {
        this(item, useOreDict, "");
    }

    public AgriStack(String item, boolean useOreDict, String tags, String... ignoreTags) {
        this(item, useOreDict, tags, Arrays.asList(ignoreTags));
    }

    public AgriStack(String item, boolean useOreDict, String tags, List<String> ignoreTags) {
        this.item = item;
        this.tags = tags;
        this.ignoreTags = ignoreTags;
        this.useOreDict = useOreDict;
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

    public <T> Optional<T> toStack(Class<T> token) {
        return this.toStack(token, 1);
    }

    public <T> Optional<T> toStack(Class<T> token, int amount) {
        return AgriCore.getConverter().toStack(token, item, amount, tags, useOreDict, ignoreTags);
    }

    public boolean validate() {
        return AgriCore.getValidator().isValidItem(item);
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
    
    public static final AgriStack fromString(String s) {
        final String[] parts = s.split(":");
        switch(parts.length) {
            case 3:
                return new AgriStack(parts[0] + ":" + parts[1]);
            default:
                return new AgriStack(s);
        }
    }

}
