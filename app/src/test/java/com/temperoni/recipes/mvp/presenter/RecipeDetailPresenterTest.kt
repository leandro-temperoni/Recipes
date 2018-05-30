package com.temperoni.recipes.mvp.presenter

import com.temperoni.recipes.domain.event.RecipeDetailEvent
import com.temperoni.recipes.mvp.model.RecipeDetailModel
import com.temperoni.recipes.mvp.view.RecipeDetailView
import com.temperoni.recipes.ui.models.RecipeDetailViewModel
import org.greenrobot.eventbus.EventBus
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * @author Leandro Temperoni
 */
class RecipeDetailPresenterTest {

    @Mock
    private lateinit var bus: EventBus

    @Mock
    private lateinit var model: RecipeDetailModel

    @Mock
    private lateinit var view: RecipeDetailView

    private lateinit var presenter: RecipeDetailPresenter

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = RecipeDetailPresenter(model, bus)
        presenter.view = view
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

    @Test
    @Throws(Exception::class)
    fun itShouldFetchRecipeDetail() {
        presenter.fetchRecipeDetail("123")
        verify<RecipeDetailModel>(model).fetchRecipeDetail("123")
    }

    @Test
    @Throws(Exception::class)
    fun itShouldDisplayRecipeDetail() {
        val event = mock(RecipeDetailEvent::class.java)
        `when`(event.isSuccess).thenReturn(true)
        val recipeDetailViewModel = mock(RecipeDetailViewModel::class.java)

        `when`<RecipeDetailViewModel>(model.getViewModel(event.payload)).thenReturn(recipeDetailViewModel)

        presenter.onRecipeDetailReceived(event)
        verify<RecipeDetailView>(view).displayRecipeDetail(recipeDetailViewModel)
    }
}