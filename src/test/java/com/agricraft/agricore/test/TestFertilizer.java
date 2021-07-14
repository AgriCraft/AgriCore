package com.agricraft.agricore.test;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.plant.fertilizer.AgriFertilizer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.junit.Assert.assertTrue;

public class TestFertilizer {

	public final AgriFertilizer fertilizer;

	public TestFertilizer() {

		// Setup Fertilizer
		this.fertilizer = new AgriFertilizer();
		
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

		assertTrue(fertilizer.validate());

	}

	@Test
	public void testSave() {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Path path = Paths.get("config", "agricraft", "example", "example_fertilizer.json");

		try(BufferedWriter out = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)){
			gson.toJson(fertilizer, out);
		} catch (IOException e) {
			AgriCore.getCoreLogger().trace(e);
		}

	}

}
