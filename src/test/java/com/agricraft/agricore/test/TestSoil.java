/*
 */
package com.agricraft.agricore.test;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.plant.AgriSoil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
public class TestSoil {

	public final AgriSoil soil;

	public TestSoil() {

		// Setup Mutation
		this.soil = new AgriSoil();
		
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

		assertTrue(soil.validate());

	}

	@Test
	public void testSave() {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Path path = Paths.get("config", "agricraft", "example", "example_soil.json");

		try(BufferedWriter out = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)){
			gson.toJson(soil, out);
		} catch (IOException e) {
			AgriCore.getCoreLogger().trace(e);
		}

	}

}
