/*
 */
package com.agricraft.agricore.test;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.test.config.DummyConfig;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author RlonRyan
 */
public class TestConfig {

	public TestConfig() {
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
	public void testConfig() {
		AgriCore.getConfig().load();
		AgriCore.getCoreLogger().info(AgriCore.getConfig().toString());
		AgriCore.getCoreLogger().info(DummyConfig.asString());
		AgriCore.getConfig().addConfigurable(DummyConfig.class);
		AgriCore.getCoreLogger().info(DummyConfig.asString());
		AgriCore.getConfig().save();
	}

}
