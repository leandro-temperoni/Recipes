package com.temperoni.recipes.mvp.model;

import com.temperoni.recipes.manager.RecipesManager;
import com.temperoni.recipes.domain.dto.Recipe;
import com.temperoni.recipes.ui.models.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Leandro Temperoni
 */
public class RecipesListModel {

    private RecipesManager recipesManager;

    @Inject
    RecipesListModel(RecipesManager recipesManager) {
        this.recipesManager = recipesManager;
    }

    public void fetchData() {
        recipesManager.fetchRecipes();
    }

    public List<RecipeViewModel> getViewModelList(List<Recipe> recipes) {
        List<RecipeViewModel> recipeViewModels = new ArrayList<>();

        for (Recipe recipe : recipes) {
            RecipeViewModel recipeViewModel = new RecipeViewModel(
                    recipe.getId(),
                    recipe.getName(),
                    recipe.getImage());

            recipeViewModels.add(recipeViewModel);
        }

        return recipeViewModels;
    }
}
