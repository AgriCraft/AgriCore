/*
 */
package com.agricraft.agricore.plant;

/**
 *
 * @author RlonRyan
 */
public final class AgriStat {

	// --------------------
	// Constants
	// --------------------
	public static final int STAT_MAX = 0b01111111;

	public static final int FULL_MASK = 0b11111111;

	public static final int BAD_MASK = 0b10000000_10000000_10000000;

	public static final int ANALYZED_MASK = 0b00000001_00000000_00000000_00000000;

	public static final String STRING_TEMPLATE = " - Growth: %d\n - Gain:   %d\n - Strength: %d\n - Analyzed: %b\n - Valid: %b\n";

	// --------------------
	// Unclasser
	// --------------------
	private AgriStat() {
	}

	// --------------------
	// Test Methods
	// --------------------
	public static boolean isValid(final int statcode) {
		return (statcode & BAD_MASK) == 0;
	}

	// --------------------
	// Unpacking Methods
	// --------------------
	public static int getGrowth(final int statcode) {
		return statcode & STAT_MAX;
	}

	public static int getGain(final int statcode) {
		return (statcode >>> 8) & STAT_MAX;
	}

	public static int getStrength(final int statcode) {
		return (statcode >>> 16) & STAT_MAX;
	}

	public static boolean getAnalyzed(final int statcode) {
		return (statcode & ANALYZED_MASK) != 0;
	}

	// --------------------
	// Packing Methods
	// --------------------
	public static int setGrowth(int statcode, int growth) {
		return (statcode & ~(FULL_MASK)) | ((growth & STAT_MAX));
	}

	public static int setGain(int statcode, int gain) {
		return (statcode & ~(FULL_MASK << 8)) | ((gain & STAT_MAX) << 8);
	}

	public static int setStregth(int statcode, int strength) {
		return (statcode & ~(FULL_MASK << 16)) | ((strength & STAT_MAX) << 16);
	}

	public static int setAnalyzed(int statcode, boolean analyzed) {
		if (analyzed) {
			return statcode | ANALYZED_MASK;
		} else {
			return statcode & ~ANALYZED_MASK;
		}
	}

	// --------------------
	// Packing Method
	// --------------------
	public static int encode(final int growth, final int gain, final int strength, final boolean analyzed) {
		int statcode = ((strength & STAT_MAX) << 16) | ((gain & STAT_MAX) << 8) | ((growth & STAT_MAX));
		if (analyzed) {
			statcode |= ANALYZED_MASK;
		}
		return statcode;
	}

	// --------------------
	// String Methods
	// --------------------
	public static String toString(final int statcode) {
		return String.format(STRING_TEMPLATE,
				getGrowth(statcode),
				getGain(statcode),
				getStrength(statcode),
				getAnalyzed(statcode),
				isValid(statcode)
		);
	}

}
