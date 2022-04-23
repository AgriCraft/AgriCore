package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.plant.versions.Versions;
import com.agricraft.agricore.plant.versions.v2.Versions_1_16;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

public class AgriMutation implements AgriSerializable, Comparable<AgriMutation> {

    private String path;
    private final String version;
    private final String json_documentation = "https://agridocs.readthedocs.io/en/master/agri_mutation/";
    
    private final boolean enabled;
    private final List<String> mods;

    private final double chance;

    private final String child;

    private final String parent1;
    private final String parent2;

    public AgriMutation() {
        this.enabled = false;
        this.mods = Lists.newArrayList("agricraft", "minecraft");
        this.path = "default/default_mutation.json";
        this.chance = 0;
        this.child = "carrot_plant";
        this.parent1 = "wheat_plant";
        this.parent2 = "potato_plant";
        this.version = Versions.LATEST;
    }

    public AgriMutation(double chance, String child, String parent1, String parent2, String path, boolean enabled) {
        this(chance, child, parent1, parent2, path, enabled, Lists.newArrayList("agricraft", "minecraft"));
    }

    public AgriMutation(double chance, String child, String parent1, String parent2, String path, boolean enabled, List<String> mods) {
        this.enabled = enabled;
        this.mods = mods;
        this.path = path;
        this.chance = chance;
        this.child = child;
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.version = Versions_1_16.VERSION;
    }

    public boolean isChild(String child) {
        return this.child.equals(child);
    }

    public boolean isParent(String parent) {
        return this.parent1.equals(parent) || this.parent2.equals(parent);
    }

    public double getChance() {
        return chance;
    }

    public AgriPlant getChild() {
        return AgriCore.getPlants().getPlant(child);
    }

    public AgriPlant getParent1() {
        return AgriCore.getPlants().getPlant(parent1);
    }

    public AgriPlant getParent2() {
        return AgriCore.getPlants().getPlant(parent2);
    }

    public boolean randomMutate(final Random rand) {
        return (chance > rand.nextDouble());
    }

    public boolean validate() {
        if (!this.enabled) {
            AgriCore.getCoreLogger().info("Disabled Mutation: {0}", this);
            return false;
        } else if (!AgriCore.getPlants().hasPlant(child)) {
            AgriCore.getCoreLogger().info("Invalid Mutation: Invalid Child: \"{0}\"!\n{1}", child, this);
            return false;
        } else if (!AgriCore.getPlants().hasPlant(parent1)) {
            AgriCore.getCoreLogger().info("Invalid Mutation: Invalid Parent 1: \"{0}\"!\n{1}", parent1, this);
            return false;
        } else if (!AgriCore.getPlants().hasPlant(parent2)) {
            AgriCore.getCoreLogger().info("Invalid Mutation: Invalid Parent 2: \"{0}\"!\n{1}", parent2, this);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nMutation:\n");
        sb.append("\t- Parents:\n");
        sb.append("\t\t- Parent 1: ").append(parent1).append("\n");
        sb.append("\t\t- Parent 2: ").append(parent2).append("\n");
        sb.append("\t- Child: ").append(child).append("\n");
        sb.append("\t- Chance: ").append(chance).append("\n");
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
    public int compareTo(AgriMutation o) {
        return this.getPath().compareTo(o.getPath());
    }
}
