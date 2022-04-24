package com.agricraft.agricore.templates;

import com.agricraft.agricore.core.AgriCore;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

public class AgriSoilCondition {
    private final String condition;
    private final String type;

    private final double tolerance_factor;

    public AgriSoilCondition() {
        this.condition = "default";
        this.type = "equal";
        this.tolerance_factor = 0.2;
    }

    public AgriSoilCondition(String condition, String type, double tolerance_factor) {
        this.condition = condition;
        this.type = type;
        this.tolerance_factor = tolerance_factor;
    }

    public String getCondition() {
        return this.condition;
    }

    public Type getType() {
        return Type.fromString(this.type).orElse(Type.EQUAL);
    }

    public double getToleranceFactor() {
        return this.tolerance_factor;
    }

    public boolean validate(Predicate<String> validator) {
        if(!validator.test(this.getCondition())) {
            AgriCore.getCoreLogger().info("Invalid soil condition: \"{0}\"", this.getCondition());
            return false;
        }
        if(!Type.fromString(this.type).isPresent()) {
            AgriCore.getCoreLogger().info("Invalid type: \"{0}\"", this.type);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.condition +": " + this.type + " (" + this.tolerance_factor + ")";
    }

    public enum Type{
        EQUAL(i -> i, i -> i),
        EQUAL_OR_LOWER(i -> 0, i -> i),
        EQUAL_OR_HIGHER(i -> i, i -> Integer.MAX_VALUE);

        private final IntUnaryOperator lower;
        private final IntUnaryOperator upper;

        Type(IntUnaryOperator lower, IntUnaryOperator upper) {
            this.lower = lower;
            this.upper = upper;
        }

        public int lowerLimit(int limit) {
            return this.lower.applyAsInt(limit);
        }

        public int upperLimit(int limit) {
            return this.upper.applyAsInt(limit);
        }

        public static Optional<Type> fromString(String type) {
            return Arrays.stream(values()).filter(value -> value.name().equalsIgnoreCase(type)).findAny();
        }
    }
}
