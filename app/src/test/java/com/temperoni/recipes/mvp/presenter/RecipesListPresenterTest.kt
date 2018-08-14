package com.temperoni.recipes.mvp.presenter

import android.view.View
import com.temperoni.recipes.domain.dto.Recipe
import com.temperoni.recipes.domain.event.RecipesEvent
import com.temperoni.recipes.mvp.model.RecipesListModel
import com.temperoni.recipes.mvp.view.RecipesListView
import com.temperoni.recipes.ui.models.RecipeViewModel
import org.greenrobot.eventbus.EventBus
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.*

/**
 * @author Leandro Temperoni
 */
class RecipesListPresenterTest {

    @Mock
    private lateinit var bus: EventBus

    @Mock
    private lateinit var model: RecipesListModel

    @Mock
    private lateinit var view: RecipesListView

    private lateinit var presenter: RecipesListPresenter

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = RecipesListPresenter(model, bus)
        presenter.view = view
    }

    @Test
    @Throws(Exception::class)
    fun itShouldFetchData() {
        presenter.fetchData()
        verify<RecipesListModel>(model).fetchData()
    }

    @Test
    fun itShouldDisplayRecipesWhenEventIsSuccess() {
        val event = mock(RecipesEvent::class.java)
        val recipeViewModels = ArrayList<RecipeViewModel>()
        val recipes = ArrayList<Recipe>()
        recipes.add(Recipe())
        `when`(event.isSuccess).thenReturn(true)
        `when`(event.payload).thenReturn(recipes)
        `when`(model.getViewModelList(event.payload)).thenReturn(recipeViewModels)

        presenter.onRecipesReceived(event)
        verify(view).displayRecipes(recipeViewModels)
    }

    @Test
    fun itShouldDisplayEmptyStateWhenEventIsSuccessAndDataIsEmpty() {
        val event = mock(RecipesEvent::class.java)
        val recipeViewModels = ArrayList<RecipeViewModel>()
        val recipes = ArrayList<Recipe>()
        `when`(event.isSuccess).thenReturn(true)
        `when`(event.payload).thenReturn(recipes)
        `when`(model.getViewModelList(event.payload)).thenReturn(recipeViewModels)

        presenter.onRecipesReceived(event)
        verify(view).displayEmptyState()
    }

    @Test
    fun itShouldDisplayErrorWhenEventIsNotSuccess() {
        val event = mock(RecipesEvent::class.java)

        presenter.onRecipesReceived(event)
        verify(view).displayError()
    }

    @Test
    @Throws(Exception::class)
    fun itShouldNavigateToRecipeDetail() {
        val sharedView = mock(View::class.java)
        presenter.onRecipeCardContainerTap("123", "https://www.asd.com/image.jpg", sharedView)
        verify<RecipesListView>(view).navigateToRecipeDetail("123", "https://www.asd.com/image.jpg", sharedView)
    }

    @Test
    @Throws(Exception::class)
    fun itShouldRegisterBus() {
        presenter.register()
        verify<EventBus>(bus).register(presenter)
    }

    @Test
    @Throws(Exception::class)
    fun itShouldUnregisterBus() {
        presenter.unregister()
        verify<EventBus>(bus).unregister(presenter)
    }
}