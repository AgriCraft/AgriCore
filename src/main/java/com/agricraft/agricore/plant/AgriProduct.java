package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class AgriProduct extends AgriObject {

    private final int min;
    private final int max;

    private final double chance;

    private final boolean required;

    public AgriProduct() {
        super();
        this.min = 5;
        this.max = 5;
        this.chance = 0.99;
        this.required = true;
    }

    public AgriProduct(String item, boolean useTag, int min, int max, double chance, boolean required, String data, String... ignoreTags) {
        this(item, useTag, min, max, chance, required, data, Arrays.asList(ignoreTags));
    }

    public AgriProduct(String item, boolean useTag, int min, int max, double chance, boolean required, String data, List<String> ignoreTags) {
        super("item", item, false, data, ignoreTags);
        this.min = min;
        this.max = max;
        this.chance = chance;
        this.required = required;
    }

    @Override
    public boolean validate() {
        if (!super.validate()) {
            AgriCore.getCoreLogger().info("Invalid Product: Invalid Item: {0}!", object);
            return false;
        } else if (min < 0) {
            AgriCore.getCoreLogger().info("Invalid Product: Min Amount Less Than Zero!");
            return false;
        } else if (max < min) {
            AgriCore.getCoreLogger().info("Invalid Product: Min Amount Greater Than Max: {0} > {1}!", min, max);
            return false;
        } else {
            return true;
        }
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public double getChance() {
        return chance;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean shouldDrop(final Random rand) {
        return chance > rand.nextDouble();
    }

    public int getAmount(final Random rand) {
        return min + rand.nextInt(max - min + 1);
    }

    public <T> Optional<T> convertSingle(Class<T> token, Random random) {
        return this.convertSingle(token, this.getAmount(random));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nProduct:");
        sb.append("\n\t- ").append(super.toString().replaceAll("\n", "\n\t").trim());
        sb.append("\n\t- Min Amount: ").append(min);
        sb.append("\n\t- Max Amount: ").append(max);
        sb.append("\n\t- Probability: ").append(chance);
        return sb.toString();
    }

}
