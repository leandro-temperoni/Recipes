package com.temperoni.recipes.mvp.model;

import com.temperoni.recipes.RecipesManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * @author Leandro Temperoni
 */
public class RecipesListModelTest {

    @Mock
    RecipesManager recipesManager;

    private RecipesListModel model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        model = new RecipesListModel(recipesManager);
    }

    @Test
    public void itShouldFetchRecipes() throws Exception {
        model.fetchData();
        verify(recipesManager).fetchRecipes();
    }
}