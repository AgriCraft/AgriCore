/*
 */
package com.agricraft.agricore.test;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriGroup;
import com.agricraft.agricore.json.AgriManifest;
import com.agricraft.agricore.json.AgriManifestEntry;
import com.agricraft.agricore.json.AgriPlants;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class TestManifest {

	public TestManifest() {
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
	public void testload() {

		Path p = Paths.get("config", "agricraft", "manifest.agri");
		AgriManifest m = AgriManifest.load(p);
		assertNotNull(m);
		AgriCore.getLogger().info(m);

		AgriCore.getLogger().info(m.groups.containsKey("vanilla") ? m.groups.get("vanilla") : "No Vanilla category!");
		AgriManifest.save(p, m);

		AgriManifest n = AgriManifest.load(p);
		assertNotNull(n);
		AgriCore.getLogger().info(n);
		assertTrue(m.toString().equals(n.toString()));

		for (AgriManifestEntry e : m.groups.values()) {
			if (e.enabled) {
				Path np = p.getParent().resolve(e.path);
				AgriGroup nm = AgriGroup.load(np);
				assertNotNull(nm);
				AgriCore.getLogger().info(np.toAbsolutePath());
				AgriCore.getLogger().info(nm);
				AgriGroup.save(np, nm);
			}
		}
		
		AgriPlants plants = new AgriPlants(p);
		
		plants.loadPlants();
		
		AgriCore.getLogger().debug(plants.toString());

	}

}
