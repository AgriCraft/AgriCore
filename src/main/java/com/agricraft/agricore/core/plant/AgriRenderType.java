/*
 */
package com.agricraft.agricore.core.plant;

/**
 *
 * @author RlonRyan
 */
public enum AgriRenderType {
	
	HASH('#'),
	CROSS('X');
	
	private final char id;

	private AgriRenderType(char id) {
		this.id = id;
	}

	public char id() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Render Type: %s (\'%c\')\n", name(), id());
	}
	
}
