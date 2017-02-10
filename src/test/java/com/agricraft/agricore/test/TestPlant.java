/*
 */
package com.agricraft.agricore.test;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.lang.AgriString;
import com.agricraft.agricore.plant.AgriPlant;
import com.agricraft.agricore.plant.AgriProduct;
import com.agricraft.agricore.plant.AgriProductList;
import com.agricraft.agricore.plant.AgriRenderType;
import com.agricraft.agricore.plant.AgriRequirement;
import com.agricraft.agricore.plant.AgriStack;
import com.agricraft.agricore.plant.AgriTexture;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author RlonRyan
 */
public class TestPlant {

    public final AgriPlant plant;

    public TestPlant() {

        // Setup Product
        AgriProduct item = new AgriProduct("wheat", 0, 1, 3, .5, true);
        
        // Setup Seed
        AgriStack seed = new AgriStack("minecraft:wheat_seeds", 0, "", true, true, "*");

        // Setup Products
        List<AgriProduct> items = new ArrayList<>();
        items.add(item);
        AgriProductList products = new AgriProductList(items);

        // Setup Nearby
        Map<String, Integer> nearby = new HashMap<>();
        nearby.put("air", 1);

        // Setup Requirement
        AgriRequirement requirement = new AgriRequirement(Arrays.asList("dirt"), Arrays.asList("stone"), nearby, 0, 10);

        // Setup Icon
        AgriTexture texture = new AgriTexture(AgriRenderType.CROSS, "seed_wheat", new String[0]);
        
        // Setup Description
        AgriString description = new AgriString("Wheat, the gluten that founded human society.");

        // Setup Plant
        plant = new AgriPlant("wheat_plant", "Wheat", "Wheat Seeds", Arrays.asList(seed), description, false, 1, 1.0, false, false, 0.1, 0, 0, products, requirement, texture, "default/wheat_plant.json", true);
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
