package com.temperoni.recipes.mvp.view;

import com.temperoni.recipes.domain.dto.Recipe;

import java.util.List;

/**
 * @author Leandro Temperoni
 */

public interface RecipesListView {

    void displayRecipes(List<Recipe> recipes);
}
