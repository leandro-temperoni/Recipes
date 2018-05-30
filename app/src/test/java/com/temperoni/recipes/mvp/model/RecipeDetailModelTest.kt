package com.temperoni.recipes.mvp.model

import android.content.Context
import com.temperoni.recipes.BuildConfig
import com.temperoni.recipes.manager.RecipesManager
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * @author Leandro Temperoni
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, manifest = "src/main/AndroidManifest.xml")
class RecipeDetailModelTest {

    @Mock
    private lateinit var recipesManager: RecipesManager

    private lateinit var context: Context

    private lateinit var model: RecipeDetailModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        context = RuntimeEnvironment.application
        model = RecipeDetailModel(recipesManager!!)
    }

    @Test
    fun itShouldFetchRecipeDetail() {
        model.fetchRecipeDetail("123")
        verify<RecipesManager>(recipesManager).fetchRecipeDetail("123")
    }
}