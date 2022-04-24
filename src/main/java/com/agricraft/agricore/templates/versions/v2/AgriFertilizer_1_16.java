package com.agricraft.agricore.templates.versions.v2;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.templates.AgriObject;
import com.agricraft.agricore.templates.AgriFertilizer;
import com.agricraft.agricore.templates.AgriFertilizerEffect;
import com.agricraft.agricore.templates.versions.Versions;
import com.agricraft.agricore.util.TypeHelper;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class AgriFertilizer_1_16 implements AgriSerializable, Comparable<AgriFertilizer_1_16> {

    private String path;
    private final String version;
    private final String json_documentation = "https://agridocs.readthedocs.io/en/master/agri_fertilizer/";

    private final boolean enabled;
    private final List<String> mods;
    private final String id;
    private final String lang_key;
    private final List<AgriObject_1_16> variants;

    private final boolean trigger_mutation;
    private final boolean trigger_weeds;
    private final int potency;
    private final AgriFertilizerEffect effect;

    public AgriFertilizer_1_16() {
        this.id = "bone_meal_fertilizer";
        this.lang_key = "item.minecraft.bone_meal";
        this.variants = TypeHelper.asList(new AgriObject_1_16("item", "minecraft:bone_meal"));
        this.enabled = false;
        this.mods = Lists.newArrayList("agricraft", "minecraft");
        this.version = Versions.LATEST;
        this.trigger_mutation = true;
        this.trigger_weeds = true;
        this.potency = 1;
        this.effect = new AgriFertilizerEffect();
    }

    public AgriFertilizer_1_16(String id, String lang_key, List<AgriObject_1_16> variants, boolean trigger_mutation, boolean trigger_weeds, int potency, AgriFertilizerEffect effect, boolean enabled) {
        this(id, lang_key, variants, trigger_mutation, trigger_weeds, potency, effect, enabled, Lists.newArrayList("agricraft", "minecraft"));
    }

    public AgriFertilizer_1_16(String id, String lang_key, List<AgriObject_1_16> variants, boolean trigger_mutation, boolean trigger_weeds, int potency,
                               AgriFertilizerEffect effect, boolean enabled, List<String> mods) {
        this.id = id;
        this.lang_key = lang_key;
        this.variants = variants;
        this.enabled = enabled;
        this.trigger_mutation = trigger_mutation;
        this.trigger_weeds = trigger_weeds;
        this.potency = potency;
        this.effect = effect;
        this.mods = mods;
        this.version = Versions_1_16.VERSION;
    }

    public AgriFertilizer toNew() {
        return new AgriFertilizer(this.id, this.lang_key, this.convertVariants(), this.trigger_mutation, this.trigger_weeds, this.potency, this.effect, this.enabled, this.mods);
    }

    public List<AgriObject> convertVariants() {
        return this.variants.stream()
                .map(AgriObject_1_16::toNew)
                .collect(Collectors.toList());
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
    public int compareTo(AgriFertilizer_1_16 o) {
        return this.id.compareTo(o.id);
    }
}
