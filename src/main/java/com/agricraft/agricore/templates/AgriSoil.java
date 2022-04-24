package com.agricraft.agricore.templates;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.templates.versions.Versions;
import com.agricraft.agricore.templates.versions.v3.Versions_1_18;
import com.agricraft.agricore.util.TypeHelper;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class AgriSoil implements AgriSerializable, Comparable<AgriSoil> {

    private String path;
    private final String version;
    private final String json_documentation = "https://agridocs.readthedocs.io/en/master/agri_soil/";

    private final boolean enabled;
    private final List<String> mods;
    private final String id;
    private final String lang_key;
    private final List<AgriStateObject> variants;

    private final String humidity;
    private final String acidity;
    private final String nutrients;

    private final double growth_modifier;

    public AgriSoil() {
        this.id = "dirt_soil";
        this.lang_key = "dirt";
        this.variants = TypeHelper.asList(new AgriStateObject());
        this.enabled = false;
        this.mods = Lists.newArrayList("agricraft", "minecraft");
        this.version = Versions.LATEST;
        this.humidity = "dry";
        this.acidity = "neutral";
        this.nutrients = "medium";
        this.growth_modifier = 1.0;
    }

    public AgriSoil(String id, String lang_key, List<AgriStateObject> variants, String humidity, String acidity, String nutrients, double growthMod, boolean enabled) {
        this(id, lang_key, variants, humidity, acidity, nutrients, growthMod, enabled, Lists.newArrayList("agricraft", "minecraft"));
    }

    public AgriSoil(String id, String lang_key, List<AgriStateObject> variants, String humidity, String acidity, String nutrients,
                    double growthMod, boolean enabled, List<String> mods) {
        this.id = id;
        this.lang_key = lang_key;
        this.variants = variants;
        this.enabled = enabled;
        this.humidity = humidity;
        this.acidity = acidity;
        this.nutrients = nutrients;
        this.growth_modifier = growthMod;
        this.mods = mods;
        this.version = Versions_1_18.VERSION;
    }

    public String getId() {
        return id;
    }

    public String getLangKey() {
        return lang_key;
    }

    public <T> List<T> getVariants(Class<T> token) {
        return this.variants.stream()
                .flatMap(t -> t.convertAll(token).stream())
                .collect(Collectors.toList());
    }

    public String getHumidity() {
        return this.humidity;
    }

    public String getAcidity() {
        return this.acidity;
    }

    public String getNutrients() {
        return this.nutrients;
    }

    public double getGrowthModifier() {
        return this.growth_modifier;
    }

    public boolean validate() {
        this.variants.removeIf(block -> {
            if (!block.validate()) {
                AgriCore.getCoreLogger().info("Invalid Soil Varient {0} for soil {1}, removing the variant!", block, this.getId());
                return true;
            } else {
                return false;
            }
        });
        if(this.variants.isEmpty()) {
            AgriCore.getCoreLogger().info("Invalid Soil: {0}, no valid variants found.", this.getId());
        }
        if(!AgriCore.getValidator().isValidHumidity(this.getHumidity())) {
            AgriCore.getCoreLogger().info("Invalid Humidity ({\"0\"} on soil {1}.", this.getHumidity(), this.getId());
            return false;
        }
        if(!AgriCore.getValidator().isValidAcidity(this.getAcidity())) {
            AgriCore.getCoreLogger().info("Invalid Acidity ({\"0\"} on soil {1}.", this.getAcidity(), this.getId());
            return false;
        }
        if(!AgriCore.getValidator().isValidNutrients(this.getNutrients())) {
            AgriCore.getCoreLogger().info("Invalid Nutrients ({\"0\"} on soil {1}.", this.getNutrients(), this.getId());
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nSoil:\n");
        sb.append("\t- Id: ").append(id).append("\n");
        sb.append("\t- Name: ").append(lang_key).append("\n");
        this.variants.forEach((e) -> {
            sb.append("\t- Block: ").append(e).append("\n");
        });
        return sb.toString();
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean checkMods() {
        return this.mods.stream().allMatch(mod -> AgriCore.getValidator().isValidMod(mod));
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
    public int compareTo(AgriSoil o) {
        return this.id.compareTo(o.id);
    }
}
