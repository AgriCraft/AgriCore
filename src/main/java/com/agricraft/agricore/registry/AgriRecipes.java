/*
 */
package com.agricraft.agricore.registry;

import com.agricraft.agricore.base.AgriRecipe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RlonRyan
 */
public class AgriRecipes {

	private final Map<String, List<AgriRecipe>> recipes;

	public AgriRecipes() {
		this.recipes = new HashMap<>();
	}

	public boolean hasRecipe(String result) {
		return this.recipes.containsKey(result);
	}

	public boolean addRecipe(AgriRecipe recipe) {
		if (!this.recipes.containsKey(recipe.getResult())) {
			List<AgriRecipe> list = new ArrayList<>();
			list.add(recipe);
			this.recipes.put(recipe.getResult(), list);
		} else {
			this.recipes.get(recipe.getResult()).add(recipe);
		}
		return true;
	}

	public List<AgriRecipe> getRecipe(String result) {
		return new ArrayList<>(this.recipes.get(result));
	}

	public List<AgriRecipe> getAll() {
		List<AgriRecipe> recipes = new ArrayList<>();
		this.recipes.values().forEach(recipes::addAll);
		return recipes;
	}

	public void validate() {
		recipes.values().forEach((l) -> {
			l.removeIf((e) -> {
				return !e.validate();
			});
		});
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nRecipes:");
		for (List<AgriRecipe> list : recipes.values()) {
			for (AgriRecipe r : list) {
				sb.append("\n\t- Recipe: ");
				sb.append(r.toString().replaceAll("\n", "\n\t").trim());
			}
		}
		return sb.append("\n").toString();
	}

}
