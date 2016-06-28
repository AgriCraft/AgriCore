/*
 */
package com.agricraft.agricore.registry;

import com.agricraft.agricore.base.AgriItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RlonRyan
 */
public class AgriItems {

	private final Map<String, AgriItem> items;

	public AgriItems() {
		this.items = new HashMap<>();
	}

	public boolean hasItem(String id) {
		return this.items.containsKey(id);
	}

	public boolean addItem(AgriItem item) {
		return this.items.putIfAbsent(item.getId(), item) == null;
	}

	public AgriItem getItem(String id) {
		return this.items.get(id);
	}

	public List<AgriItem> getAll() {
		return new ArrayList<>(this.items.values());
	}

	public void validate() {
		items.entrySet().removeIf((e) -> (!e.getValue().validate()));
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nItems:");
		for (AgriItem item : items.values()) {
			sb.append("\n\t- Item: ");
			sb.append(item.toString().replaceAll("\n", "\n\t").trim());
		}
		return sb.append("\n").toString();
	}

}
