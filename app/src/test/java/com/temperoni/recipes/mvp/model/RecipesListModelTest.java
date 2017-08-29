package com.temperoni.recipes.mvp.model;

import android.content.Context;

import com.temperoni.recipes.BuildConfig;
import com.temperoni.recipes.domain.RecipesManager;
import com.temperoni.recipes.domain.dto.Recipe;
import com.temperoni.recipes.ui.models.RecipeViewModel;
import com.temperoni.recipes.utils.Decoder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * @author Leandro Temperoni
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "src/main/AndroidManifest.xml")
public class RecipesListModelTest {

    private static final String RECIPES_JSON_FILE = "recipes.json";

    @Mock
    RecipesManager recipesManager;

    Context context;

    private RecipesListModel model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        context = RuntimeEnvironment.application;
        model = new RecipesListModel(recipesManager);
    }

    @Test
    public void itShouldFetchRecipes() throws Exception {
        model.fetchData();
        verify(recipesManager).fetchRecipes();
    }

    @Test
    public void itShouldCreateViewModelList() throws Exception {
        Decoder decoder = new Decoder();
        List<Recipe> recipes = decoder.decodeJson(RECIPES_JSON_FILE);

        List<RecipeViewModel> recipeViewModels = model.getViewModelList(recipes);

        assertEquals(2, recipeViewModels.size());

        assertEquals(0, recipeViewModels.get(0).getId());
        assertEquals("https://firebasestorage.googleapis.com/v0/b/recipes-cb2a2.appspot.com/o/Cheesecake%20de%20frutos%20rojos.jpg?alt=media&token=959dc319-061a-4210-8724-54878c785870", recipeViewModels.get(0).getImageUrl());
        assertEquals("Cheesecake de frutos rojos", recipeViewModels.get(0).getName());

        assertEquals(1, recipeViewModels.get(1).getId());
        assertEquals("https://firebasestorage.googleapis.com/v0/b/recipes-cb2a2.appspot.com/o/Brownie%20con%20crema.jpg?alt=media&token=7caa100c-12ed-4814-b4bd-663da01b1dd7", recipeViewModels.get(1).getImageUrl());
        assertEquals("Brownie con crema", recipeViewModels.get(1).getName());
    }
}