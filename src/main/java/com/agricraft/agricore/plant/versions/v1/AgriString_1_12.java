package com.agricraft.agricore.plant.versions.v1;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class AgriString_1_12 {

    @SerializedName(value = "default", alternate = {"normal"})
    private final String normal;
    private final Map<String, String> translations;

    public AgriString_1_12() {
        this.normal = "add translations in here.";
        this.translations = new HashMap<>();
    }

    public AgriString_1_12(String value) {
        this.normal = value;
        this.translations = new HashMap<>();
    }

    public AgriString_1_12(String normal, Map<String, String> translations) {
        this.normal = normal;
        this.translations = translations;
        if (this.translations.isEmpty()) {
        }

    }

    public String getNormal() {
        return normal;
    }

    public TreeMap<String, String> getTranslations() {
        TreeMap<String, String> result = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        result.putAll(translations);
        return result;
    }

    @Override
    public String toString() {
        return getNormal();
    }

}
