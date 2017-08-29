package com.temperoni.recipes.mvp.model;

import android.content.Context;

import com.temperoni.recipes.manager.RecipesManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;

import static org.mockito.Mockito.verify;

/**
 * @author Leandro Temperoni
 */
public class RecipeDetailModelTest {

    @Mock
    RecipesManager recipesManager;

    Context context;

    RecipeDetailModel model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        context = RuntimeEnvironment.application;
        model = new RecipeDetailModel(recipesManager);
    }

    @Test
    public void itShouldFetchRecipeDetail() {
        model.fetchRecipeDetail("123");
        verify(recipesManager).fetchRecipeDetail("123");
    }
}