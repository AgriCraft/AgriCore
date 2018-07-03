/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agricraft.agricore.exception;

import com.google.common.base.MoreObjects;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 *
 * @author Ryan
 */
public class SimpleExceptionContext implements ExceptionContext {

    private final HashMap<String, Object> entries;

    public SimpleExceptionContext() {
        this.entries = new LinkedHashMap<>();
    }

    @Override
    public ExceptionContext withEntry(String key, Object value) {
        this.entries.put(key, value);
        return this;
    }

    @Override
    public Stream<Map.Entry<String, Object>> getEntries() {
        return this.entries.entrySet().stream();
    }

    @Override
    public String toFormattedString() {
        final StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : this.entries.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("entries", this.entries)
                .toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.entries);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }

        final SimpleExceptionContext other = (SimpleExceptionContext) obj;

        return Objects.equals(this.entries, other.entries);
    }

}
