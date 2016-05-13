/*
 */
package com.agricraft.agricore.test;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.plant.AgriMutation;
import com.agricraft.agricore.plant.AgriRequirement;
import com.agricraft.agricore.test.defaults.AgriCoreDefaultInitializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author RlonRyan
 */
public class TestMutation {

	public final AgriMutation mutation;

	public TestMutation() {
		
		// Setup Nearby
		Map<String, Integer> nearby = new HashMap<>();
		nearby.put("gold", 1);
		
		// Setup Requirement
		AgriRequirement requirement = new AgriRequirement(Arrays.asList("dirt"), Arrays.asList("stone"), nearby, 0, 10);

		// Setup Mutation
		this.mutation = new AgriMutation(0.5, "Wheat", "Wheat", "Wheat", requirement);
		
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		AgriCoreDefaultInitializer.initCore();
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

		//assertTrue(mutation.validate());

		final Random rand = new Random();

		final int trials = 10000;

		int hits = 0;

		for (int i = 0; i < trials; i++) {
			if (mutation.randomMutate(rand)) {
				hits++;
			}
		}

		AgriCore.getCoreLogger().info("Number of times mutated over " + trials + " trials: " + hits + " at a " + (((double) hits) / trials) * 100 + "% yield.");

		AgriCore.getCoreLogger().debug(mutation);

	}

	@Test
	public void testSave() {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Path path = Paths.get("config", "agricraft", "example", "example_mutation.json");

		try(BufferedWriter out = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)){
			gson.toJson(mutation, out);
		} catch (IOException e) {
			AgriCore.getCoreLogger().trace(e);
		}

	}

}
