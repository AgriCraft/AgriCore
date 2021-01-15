package com.agricraft.agricore.plant;

import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.plant.versions.v2.Versions_1_16;

public class AgriWeed implements AgriSerializable, Comparable<AgriWeed> {

    private String path;
    private final String version;
    private final boolean enabled;

    private final String id;

    public AgriWeed() {
        this.path = "default/weed_weed.json";
        this.version = Versions_1_16.VERSION;
        this.enabled = false;
        this.id = "weed_weed";
    }

    public AgriWeed(String id, String path, boolean enabled) {
        this.id = id;
        this.path = path;
        this.enabled = enabled;
        this.version = Versions_1_16.VERSION;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public int compareTo(AgriWeed o) {
        return this.getId().compareTo(o.getId());
    }
}
