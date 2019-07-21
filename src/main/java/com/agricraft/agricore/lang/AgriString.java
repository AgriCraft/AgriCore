/*
 */
package com.agricraft.agricore.lang;

import com.agricraft.agricore.core.AgriCore;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author RlonRyan
 * @modify Yuri
 */
public class AgriString {

    @SerializedName(value = "default", alternate = {"normal"})
    private final String normal;
    private final Map<String, String> translations;

    public AgriString() {
        this.normal = "add translations in here.";
        this.translations = new HashMap<>();
        this.translations.put("en_US", normal);
    }

    public AgriString(String value) {
        this.normal = value;
        this.translations = new HashMap<>();
        this.translations.put("en_US", normal);
    }

    public AgriString(String normal, Map<String, String> translations) {
        this.normal = normal;
        this.translations = translations;
        if (this.translations.isEmpty()) {
            this.translations.put("en_US", normal);
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
        if (getTranslations().containsKey(AgriCore.getTranslator().getLocale())){
            return getTranslations().get(AgriCore.getTranslator().getLocale());
        }
        return normal;
    }

}
