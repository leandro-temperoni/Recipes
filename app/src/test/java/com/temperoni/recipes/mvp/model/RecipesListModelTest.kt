package com.temperoni.recipes.mvp.model

import android.content.Context
import com.temperoni.recipes.BuildConfig
import com.temperoni.recipes.manager.RecipesManager
import com.temperoni.recipes.utils.Decoder
import org.junit.Assert.assertEquals
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
class RecipesListModelTest {

    @Mock
    private lateinit var recipesManager: RecipesManager

    private lateinit var context: Context

    private lateinit var model: RecipesListModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        context = RuntimeEnvironment.application
        model = RecipesListModel(recipesManager)
    }

    @Test
    @Throws(Exception::class)
    fun itShouldFetchRecipes() {
        model.fetchData()
        verify<RecipesManager>(recipesManager).fetchRecipes()
    }

    @Test
    @Throws(Exception::class)
    fun itShouldCreateViewModelList() {
        val decoder = Decoder()
        val recipes = decoder.decodeJson(RECIPES_JSON_FILE)

        val recipeViewModels = model.getViewModelList(recipes)

        assertEquals(2, recipeViewModels.size.toLong())

        assertEquals(0, recipeViewModels[0].id.toLong())
        assertEquals("https://firebasestorage.googleapis.com/v0/b/recipes-cb2a2.appspot.com/o/Cheesecake%20de%20frutos%20rojos.jpg?alt=media&token=959dc319-061a-4210-8724-54878c785870", recipeViewModels[0].imageUrl)
        assertEquals("Cheesecake de frutos rojos", recipeViewModels[0].name)

        assertEquals(1, recipeViewModels[1].id.toLong())
        assertEquals("https://firebasestorage.googleapis.com/v0/b/recipes-cb2a2.appspot.com/o/Brownie%20con%20crema.jpg?alt=media&token=7caa100c-12ed-4814-b4bd-663da01b1dd7", recipeViewModels[1].imageUrl)
        assertEquals("Brownie con crema", recipeViewModels[1].name)
    }

    companion object {

        private const val RECIPES_JSON_FILE = "recipes.json"
    }
}