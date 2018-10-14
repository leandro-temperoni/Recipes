package com.temperoni.recipes.ui.models

import com.temperoni.recipes.BuildConfig
import com.temperoni.recipes.domain.dto.Recipe
import com.temperoni.recipes.utils.Decoder
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * @author Leandro Temperoni
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, manifest = "src/main/AndroidManifest.xml")
class RecipeDetailViewModelTest {

    @Test
    @Throws(Exception::class)
    fun itShouldCreateViewModelDetail() {
        val decoder = Decoder()
        val recipe : Recipe = decoder.decodeJson(RECIPES_JSON_FILE)?.get(0) ?: Recipe()

        val viewModel = RecipeDetailViewModel.Builder(
                recipe.id ?: 0,
                recipe.name ?: "",
                recipe.image ?: "",
                recipe.introduction ?: "")
                .withIngredients(recipe.ingredients ?: listOf())
                .withInstructions(recipe.instructions ?: listOf())
                .build()

        assertEquals(0, viewModel.id)
        assertNotNull(viewModel.imageUrl)
        assertEquals("Cheesecake de frutos rojos", viewModel.name)
        assertNotNull(viewModel.introduction)

        assertEquals(3, viewModel.ingredients?.size)
        assertEquals("800 gr de queso crema", viewModel.ingredients?.get(0)?.description)
        viewModel.ingredients?.get(0)?.isLastEntry?.let { assertFalse(it) }
        assertEquals("4 huevos", viewModel.ingredients?.get(1)?.description)
        viewModel.ingredients?.get(1)?.isLastEntry?.let { assertFalse(it) }
        assertEquals("Un toque de jugo de limon", viewModel.ingredients?.get(2)?.description)
        viewModel.ingredients?.get(2)?.isLastEntry?.let { assertTrue(it) }

        assertEquals(3, viewModel.instructions?.size)
        assertEquals("Para la base", viewModel.instructions?.get(0)?.description)
        assertEquals(3, viewModel.instructions?.get(0)?.steps?.size)
        assertEquals("Para la crema", viewModel.instructions?.get(1)?.description)
        assertEquals(7, viewModel.instructions?.get(1)?.steps?.size)
        assertEquals("Para el coulis", viewModel.instructions?.get(2)?.description)
        assertEquals(3, viewModel.instructions?.get(2)?.steps?.size)
    }

    companion object {

        private const val RECIPES_JSON_FILE = "recipes.json"
    }
}