/*
 */
package com.agricraft.agricore.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.core.AgriTexture;
import com.agricraft.agricore.core.AgriPlant;
import com.agricraft.agricore.core.AgriProduct;
import com.agricraft.agricore.core.AgriProductList;
import com.agricraft.agricore.core.AgriRenderType;
import com.agricraft.agricore.core.AgriRequirement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RlonRyan
 */
public class TestPlant {

	public final AgriPlant plant;

	public TestPlant() {

		// Setup Product
		AgriProduct item = new AgriProduct("wheat", 0, 1, 3, 500);

		// Setup Products
		List<AgriProduct> items = new ArrayList<>();
		items.add(item);
		AgriProductList products = new AgriProductList(items);

		// Setup Nearby
		Map<String, Integer> nearby = new HashMap<>();
		nearby.put("air", 1);

		// Setup Requirement
		AgriRequirement requirement = new AgriRequirement(Arrays.asList("dirt"), Arrays.asList("stone"), nearby);

		// Setup Icon
		AgriTexture texture = new AgriTexture("seed_wheat", AgriRenderType.CROSS, new String[]{"wheat"});

		// Setup Plant
		plant = new AgriPlant("wheat_plant", products, requirement, texture);
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
	public void test() {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		AgriCore.getLogger().debug(gson.toJson(plant));

		assertTrue(plant.validate());

		final Random rand = new Random();

		final int trials = 10000;

		int hits = 0;

		for (int i = 0; i < trials; i++) {
			List<AgriProduct> drops = plant.products.getRandom(rand);
			hits += drops.size();
		}

		AgriCore.getLogger().info("Number of times items were dropped over " + trials + " trials: " + hits + " at a " + (((double) hits) / trials) * 100 + "% yield.");

		AgriCore.getLogger().debug(plant);

	}
}
