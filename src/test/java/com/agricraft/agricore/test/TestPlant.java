package com.agricraft.agricore.test;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.plant.*;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestPlant {

    public final AgriPlant plant;

    public TestPlant() {

        // Setup Product
        AgriProduct item = new AgriProduct("wheat", false, 0, 3, .5, true, "");

        // Setup Seed
        AgriSeed seed = new AgriSeed("minecraft:wheat_seeds");

        // Setup Products
        List<AgriProduct> items = new ArrayList<>();
        items.add(item);
        List<AgriProduct> clip_items = new ArrayList<AgriProduct>();
        AgriProductList products = new AgriProductList(items);
        AgriProductList clip_products = new AgriProductList(clip_items);

        // Setup Condition
        AgriBlockCondition condition = new AgriBlockCondition(
                10, 1, 0, -2, 0, 0, -2, 0, "minecraft:stone", false, "");

        // Setup Requirement
        AgriRequirement requirement = new AgriRequirement();

        // Setup Icon
        AgriTexture texture = new AgriTexture(AgriRenderType.CROSS, new String[0][0]);

        // Setup Plant
        plant = new AgriPlant("wheat_plant", "plant.wheat.plant", "plant.wheat.seed", "plant.wheat.desc",
                Lists.newArrayList(seed), new int[]{2,4,6,8,10,12,14,16}, 4, true, 1, 1.0, 0.01,
                false, 0.1, 0, 1, 0,
                products, clip_products, requirement, Lists.newArrayList(), texture,
                "seed_wheat", "seed_wheat", "default/wheat_plant.json", true);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testValidate() {

        assertTrue(plant.validate());

        final Random rand = new Random();

        final int trials = 10000;

        int hits = 0;

        for (int i = 0; i < trials; i++) {
            List<AgriProduct> drops = plant.getProducts().getRandom(rand);
            hits += drops.size();
        }

        AgriCore.getCoreLogger().info("Number of times items were dropped over " + trials + " trials: " + hits + " at a " + (((double) hits) / trials) * 100 + "% yield.");

        AgriCore.getCoreLogger().debug(plant);

        AgriCore.getCoreLogger().debug(plant.getTexture());

    }

    @Test
    public void testSave() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path path = Paths.get("config", "agricraft", "example", "example_plant.json");

        try (BufferedWriter out = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            gson.toJson(plant, out);
        } catch (IOException e) {
            AgriCore.getCoreLogger().trace(e);
        }

    }

}
