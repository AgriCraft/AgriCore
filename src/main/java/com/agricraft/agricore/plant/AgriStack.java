package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AgriStack {

    protected final String item;

    protected final String tags;

    protected final List<String> ignoredTags;

    protected final boolean useTag;

    public AgriStack() {
        this("minecraft:dirt");
    }
    
    public AgriStack(String item) {
        this(item,false);
    }
    
    public AgriStack(String item, boolean useTags) {
        this(item, useTags, "");
    }

    public AgriStack(String item, boolean useTags, String tag, String... ignoredTags) {
        this(item, useTags, tag, Arrays.asList(ignoredTags));
    }

    public AgriStack(String item, boolean useTags, String tag, List<String> ignoredTags) {
        this.item = item;
        this.tags = tag;
        this.ignoredTags = ignoredTags;
        this.useTag = useTags;
    }

    public String getItem() {
        return item;
    }

    public String getTags() {
        return tags;
    }

    public List<String> getIgnoredTags() {
        return ignoredTags;
    }

    public boolean useTag() {
        return useTag;
    }

    public <T> Optional<T> toStack(Class<T> token) {
        return this.toStack(token, 1);
    }

    public <T> Optional<T> toStack(Class<T> token, int amount) {
        return AgriCore.getConverter().toStack(token, item, amount, tags, useTag, ignoredTags);
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
        sb.append("\n\t- IgnoreTags: ").append(ignoredTags);
        sb.append("\n\t- UseOreDict: ").append(useTag);
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
