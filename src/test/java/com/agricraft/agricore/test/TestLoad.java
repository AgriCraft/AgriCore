/*
 */
package com.agricraft.agricore.test;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriLoader;
import com.agricraft.agricore.test.defaults.AgriCoreDefaultInitializer;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class TestLoad {

	public TestLoad() {
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
	public void testLoad() {

		Path p = Paths.get("config", "agricraft");

		assertNotNull(p);

		AgriLoader.loadDirectory(p, AgriCore.getPlants(), AgriCore.getMutations());

		AgriCore.getCoreLogger().info(AgriCore.getPlants());

	}

}
