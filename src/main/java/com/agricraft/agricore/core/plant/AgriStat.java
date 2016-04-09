/*
 */
package com.agricraft.agricore.core.plant;

/**
 *
 * @author RlonRyan
 */
public final class AgriStat {
	
	// --------------------
	// Constants
	// --------------------
	
	public static final int STAT_MAX = 0b01111111;
	
	public static final int BAD_MASK = 0b10000000_10000000_10000000;
	
	public static final String STRING_TEMPLATE = " - Growth: %d\n - Gain:   %d\n - Strength: %d\n - Valid: %b\n";

	// --------------------
	// Unclasser
	// --------------------
	
	private AgriStat() {
	}
	
	// --------------------
	// Test Methods
	// --------------------
	
	public boolean isValid(final int statcode) {
		return (statcode & BAD_MASK) == 0;
	}
	
	// --------------------
	// Unpacking Methods
	// --------------------
	
	public int getGrowth(final int statcode) {
		return statcode & STAT_MAX;
	}
	
	public int getGain(final int statcode) {
		return (statcode >>> 8) & STAT_MAX;
	}
	
	public int getStrength(final int statcode) {
		return (statcode >>> 16) & STAT_MAX;
	}
	
	// --------------------
	// Packing Method
	// --------------------
	
	public int hashCode(final int growth, final int gain, final int strength) {
		return ((strength & STAT_MAX) << 16) | ((gain & STAT_MAX) << 8) | ((growth & STAT_MAX));
	}
	
	// --------------------
	// String Methods
	// --------------------
	
	public String toString(final int statcode) {
		return String.format(STRING_TEMPLATE, getGrowth(statcode), getGain(statcode), getStrength(statcode));
	}
	
}
