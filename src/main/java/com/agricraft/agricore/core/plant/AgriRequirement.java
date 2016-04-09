/*
 */
package com.agricraft.agricore.core.plant;


import com.agricraft.agricore.core.AgriCore;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RlonRyan
 */
public class AgriRequirement {

	private final List<String> soils;
	private final List<String> bases;

	private final Map<String, Integer> nearby;

	public AgriRequirement(List<String> soils, List<String> bases, Map<String, Integer> nearby) {
		this.soils = soils;
		this.bases = bases;
		this.nearby = nearby;
	}

	public boolean validate() {
		for (String block : soils) {
			if (!AgriCore.getValidator().isValidBlock(block)) {
				AgriCore.getLogger().info("Invalid Requirement: Invalid Soil!");
				return false;
			}
		}
		for (String block : bases) {
			if (!AgriCore.getValidator().isValidBlock(block)) {
				AgriCore.getLogger().info("Invalid Requirement: Invalid Base!");
				return false;
			}
		}
		for (String block : nearby.keySet()) {
			if (!AgriCore.getValidator().isValidBlock(block)) {
				AgriCore.getLogger().info("Invalid Requirement: Invalid Nearby!");
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nRequirement:\n");
		sb.append("\t- Soil: \n");
		this.soils.forEach((e) -> {
			sb.append("\t\t- Block: ").append(e).append("\n");
		});
		sb.append("\t- Base: \n");
		this.bases.forEach((e) -> {
			sb.append("\t\t- Block: ").append(e).append("\n");
		});
		sb.append("\t- Nearby: \n");
		this.nearby.entrySet().forEach((e) -> {
			sb.append("\t\t- Block: ").append(e.getValue()).append("\n\t");
			sb.append("\t\t- Distance: ").append(e.getValue()).append("\n");
		});
		return sb.toString();
	}

}
