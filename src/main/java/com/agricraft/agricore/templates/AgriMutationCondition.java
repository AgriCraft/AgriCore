package com.agricraft.agricore.templates;

import com.agricraft.agricore.core.AgriCore;
import com.google.gson.JsonObject;

public class AgriMutationCondition {
    private final String id;
    private final boolean required;
    private final double guaranteedChance;
    private final JsonObject parameters;

    public AgriMutationCondition() {
        this.id = "none";
        this.required = false;
        this.guaranteedChance = 0;
        this.parameters = new JsonObject();
    }

    public AgriMutationCondition(String id, boolean required, double guaranteedChance, JsonObject parameters) {
        this.id = id;
        this.required = required;
        this.guaranteedChance = guaranteedChance;
        this.parameters = parameters;
    }

    public String getId() {
        return this.id;
    }

    public boolean isRequired() {
        return this.required;
    }

    public double getGuaranteedChance() {
        return this.guaranteedChance;
    }

    public JsonObject getParameters() {
        return this.parameters;
    }

    public boolean validate() {
        if(!AgriCore.getValidator().isValidMutationCondition(this.getId(), this.getParameters())) {
            AgriCore.getCoreLogger().info("Invalid Mutation trigger {0}: {1}", this.getId(), this.getParameters());
            return false;
        }
        if(this.getGuaranteedChance() < 0 || this.getGuaranteedChance() > 1) {
            AgriCore.getCoreLogger().info("Invalid Mutation trigger guaranteed probability: {1}, the value must be between 0 and 1 (both inclusive)", this.getId(), this.getParameters());
            return false;
        }
        return true;
    }
}
