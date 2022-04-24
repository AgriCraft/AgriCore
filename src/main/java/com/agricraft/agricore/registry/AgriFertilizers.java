package com.agricraft.agricore.registry;

import com.agricraft.agricore.json.AgriJsonVersion;
import com.agricraft.agricore.plant.AgriFertilizer;
import com.agricraft.agricore.plant.versions.v3.Versions_1_18;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AgriFertilizers implements AgriLoadableRegistry<AgriFertilizer> {
    private final Map<String, AgriFertilizer> fertilizers;

    public AgriFertilizers() {
        this.fertilizers = new HashMap<>();
    }

    public boolean hasFertilizer(String id) {
        return this.fertilizers.containsKey(id);
    }

    public boolean addFertilizer(AgriFertilizer fertilizer) {
        return this.fertilizers.putIfAbsent(fertilizer.getId(), fertilizer) == null;
    }

    public AgriFertilizer getFertilizer(String id) {
        return this.fertilizers.get(id);
    }

    public Collection<AgriFertilizer> getAll() {
        return Collections.unmodifiableCollection(this.fertilizers.values());
    }

    public void validate() {
        fertilizers.entrySet().removeIf((e) -> (!e.getValue().validate()));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nFertilizers:");
        for (AgriFertilizer fertilizer : fertilizers.values()) {
            sb.append("\n\t- Fertilizer: ");
            sb.append(fertilizer.toString().replaceAll("\n", "\n\t").trim());
        }
        return sb.append("\n").toString();
    }

    @Override
    public boolean acceptsElement(String filename) {
        return filename.toLowerCase().endsWith("_fertilizer.json");
    }

    @Override
    public AgriJsonVersion<AgriFertilizer> getElementVersion() {
        return Versions_1_18.FERTILIZER;
    }

    @Override
    public void registerElement(AgriFertilizer element) {
        this.addFertilizer(element);
    }

    @Override
    public void clearElements() {
        this.fertilizers.clear();
    }

    @Override
    public int hashCode() {
        return this.fertilizers.hashCode();
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
        final AgriFertilizers other = (AgriFertilizers) obj;
        return this.fertilizers.equals(other.fertilizers);
    }

}
