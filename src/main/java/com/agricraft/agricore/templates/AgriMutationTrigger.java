package com.agricraft.agricore.templates;

import com.agricraft.agricore.core.AgriCore;
import com.google.gson.JsonObject;

public class AgriMutationTrigger {
    private final String id;
    private final boolean required;
    private final boolean guaranteed;
    private final JsonObject parameters;

    public AgriMutationTrigger() {
        this.id = "none";
        this.required = false;
        this.guaranteed = false;
        this.parameters = new JsonObject();
    }

    public AgriMutationTrigger(String id, boolean required, boolean guaranteed, JsonObject parameters) {
        this.id = id;
        this.required = required;
        this.guaranteed = guaranteed;
        this.parameters = parameters;
    }

    public String getId() {
        return this.id;
    }

    public boolean isRequired() {
        return this.required;
    }

    public boolean isGuaranteed() {
        return this.guaranteed;
    }

    public JsonObject getParameters() {
        return this.parameters;
    }

    public boolean validate() {
        boolean valid = AgriCore.getValidator().isValidMutationTrigger(this.getId(), this.getParameters());
        if(!valid) {
            AgriCore.getCoreLogger().info("Invalid Mutation trigger {0}: {1}", this.getId(), this.getParameters());
        }
        return valid;
    }
}
