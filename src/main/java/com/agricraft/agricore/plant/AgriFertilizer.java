package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.plant.versions.v2.Versions_1_16;
import com.agricraft.agricore.util.TypeHelper;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class AgriFertilizer implements AgriSerializable, Comparable<AgriFertilizer> {

    private String path;
    private final String version;
    private final String json_documentation = "https://agridocs.readthedocs.io/en/master/agri_fertilizer/";

    private final boolean enabled;
    private final List<String> mods;
    private final String id;
    private final String lang_key;
    private final List<AgriObject> variants;

    private final boolean trigger_mutation;
    private final boolean trigger_weeds;
    private final int potency;

    public AgriFertilizer() {
        this.id = "bone_meal_fertilizer";
        this.lang_key = "item.minecraft.bone_meal";
        this.variants = TypeHelper.asList(new AgriObject("item", "minecraft:bone_meal"));
        this.enabled = false;
        this.mods = Lists.newArrayList("agricraft", "minecraft");
        this.version = Versions_1_16.VERSION;
        this.trigger_mutation = true;
        this.trigger_weeds = true;
        this.potency = 1;
    }

    public AgriFertilizer(String id, String lang_key, List<AgriObject> variants, boolean trigger_mutation, boolean trigger_weeds, int potency, boolean enabled) {
        this(id, lang_key, variants, trigger_mutation, trigger_weeds, potency, enabled, Lists.newArrayList("agricraft", "minecraft"));
    }

    public AgriFertilizer(String id, String lang_key, List<AgriObject> variants, boolean trigger_mutation, boolean trigger_weeds, int potency,
                          boolean enabled, List<String> mods) {
        this.id = id;
        this.lang_key = lang_key;
        this.variants = variants;
        this.enabled = enabled;
        this.trigger_mutation = trigger_mutation;
        this.trigger_weeds = trigger_weeds;
        this.potency = potency;
        this.mods = mods;
        this.version = Versions_1_16.VERSION;
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

    public boolean canTriggerMutation() {
        return trigger_mutation;
    }

    public boolean canTriggerWeeds() {
        return trigger_weeds;
    }

    public int getPotency() {
        return potency;
    }

    public boolean validate() {
        this.variants.removeIf(item -> {
            if (!item.validate()) {
                AgriCore.getCoreLogger().info("Invalid Fertilizer Variant {0} for fertilizer {1}, removing the variant!", item, this.getId());
                return true;
            } else {
                return false;
            }
        });
        if(this.variants.isEmpty()) {
            AgriCore.getCoreLogger().info("Invalid Fertilizer: {0}, no valid variants found.", this.getId());
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nFertilizer:\n");
        sb.append("\t- Id: ").append(id).append("\n");
        sb.append("\t- Name: ").append(lang_key).append("\n");
        this.variants.forEach((e) -> {
            sb.append("\t- Item: ").append(e).append("\n");
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
    public int compareTo(AgriFertilizer o) {
        return this.id.compareTo(o.id);
    }
}
