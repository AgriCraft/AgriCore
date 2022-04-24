package com.agricraft.agricore.plant;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class AgriListCondition {
    private final List<String> values;
    private final boolean blacklist;
    private final int ignoreFromStrength;

    public AgriListCondition() {
        this.values = Collections.emptyList();
        this.blacklist = true;
        this.ignoreFromStrength = 11;
    }

    public AgriListCondition(List<String> values, boolean blacklist, int ignoreFromStrength) {
        this.values = values;
        this.blacklist = blacklist;
        this.ignoreFromStrength = ignoreFromStrength;
    }

    public List<String> getValues() {
        return this.values;
    }

    public boolean isBlacklist() {
        return this.blacklist;
    }

    public int ignoreFromStrength() {
        return this.ignoreFromStrength;
    }

    public Stream<String> stream() {
        return this.getValues().stream();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder()
                .append(this.isBlacklist() ? "Blacklist" : "Whitelist")
                .append(" (strength < ")
                .append(this.ignoreFromStrength())
                .append("): {");
        boolean first = true;
        for(String value : this.getValues()) {
            if(first) {
                first = false;
            } else {
                sb.append(", ");
            }
            sb.append(value);
        }
        sb.append("}");
        return sb.toString();
    }
}
