package com.agricraft.agricore.templates.versions.v2;

import com.agricraft.agricore.templates.AgriBlockCondition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AgriBlockCondition_1_16 extends AgriObject_1_16 {
	protected final int strength;
	protected final int amount;
	protected final int min_x;
	protected final int min_y;
	protected final int min_z;
	protected final int max_x;
	protected final int max_y;
	protected final int max_z;
	
	public AgriBlockCondition_1_16() {
		super();
		this.strength = 11;
		this.amount = 1;
		this.min_x = 0;
		this.min_y = -2;
		this.min_z = 0;
		this.max_x = 0;
		this.max_y = -2;
		this.max_z = 0;
	}

	public AgriBlockCondition_1_16(int strength, int amount, int min_x, int min_y, int min_z, int max_x, int max_y, int max_z,
								   String item, boolean useTag, String tags, String... ignoreTags) {
		this(strength, amount, min_x, min_y, min_z, max_x, max_y, max_z, item, useTag, tags, Arrays.asList(ignoreTags));
	}

	public AgriBlockCondition_1_16(int strength, int amount, int min_x, int min_y, int min_z, int max_x, int max_y, int max_z,
								   String item, boolean useTag, String tags, List<String> ignoreTags) {
		super("block", item, useTag, tags, ignoreTags);
		this.strength = strength;
		this.amount = amount;
		this.min_x = min_x;
		this.min_y = min_y;
		this.min_z = min_z;
		this.max_x = max_x;
		this.max_y = max_y;
		this.max_z = max_z;
	}

	@Override
	public AgriBlockCondition toNew() {
		return new AgriBlockCondition(this.strength, this.amount, this.min_x, this.min_y, this.min_z, this.max_x, this.max_y, this.max_z,
				this.object, this.useTag, Collections.emptyList(), this.data, this.ignoredData);
	}

}
