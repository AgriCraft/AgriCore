package com.agricraft.agricore.registry;

import com.agricraft.agricore.json.AgriJsonVersion;
import com.agricraft.agricore.templates.AgriWeed;
import com.agricraft.agricore.templates.versions.v3.Versions_1_18;
import com.google.common.collect.ImmutableSet;

import java.util.HashMap;
import java.util.Map;

public class AgriWeeds implements AgriLoadableRegistry<AgriWeed> {

    private final Map<String, AgriWeed> weeds;

    public AgriWeeds() {
        this.weeds = new HashMap<>();
    }

    public boolean hasWeed(String id) {
        return this.weeds.containsKey(id);
    }

    public boolean addWeed(AgriWeed plant) {
        return this.weeds.putIfAbsent(plant.getId(), plant) == null;
    }

    public AgriWeed getWeed(String id) {
        return this.weeds.get(id);
    }

    public ImmutableSet<AgriWeed> getAllElements() {
        return ImmutableSet.copyOf(this.weeds.values());
    }

    public void validate() {
        weeds.entrySet().removeIf((e) -> (!e.getValue().validate()));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nWeeds:");
        for (AgriWeed weed : weeds.values()) {
            sb.append("\n\t- Plant: ");
            sb.append(weed.toString().replaceAll("\n", "\n\t").trim());
        }
        return sb.append("\n").toString();
    }

    @Override
    public boolean acceptsElement(String filename) {
        return filename.toLowerCase().endsWith("_weed.json");
    }

    @Override
    public AgriJsonVersion<AgriWeed> getElementVersion() {
        return Versions_1_18.WEED;
    }

    @Override
    public void registerElement(AgriWeed element) {
        this.addWeed(element);
    }

    @Override
    public void clearElements() {
        this.weeds.clear();
    }

    @Override
    public int hashCode() {
        return this.weeds.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AgriWeeds other = (AgriWeeds) obj;
        return this.weeds.equals(other.weeds);
    }

}
