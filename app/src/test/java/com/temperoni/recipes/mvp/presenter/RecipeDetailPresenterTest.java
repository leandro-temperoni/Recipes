package com.temperoni.recipes.mvp.presenter;

import com.temperoni.recipes.domain.event.RecipeDetailEvent;
import com.temperoni.recipes.mvp.model.RecipeDetailModel;
import com.temperoni.recipes.mvp.view.RecipeDetailView;
import com.temperoni.recipes.ui.models.RecipeDetailViewModel;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Leandro Temperoni
 */
public class RecipeDetailPresenterTest {

    @Mock
    private EventBus bus;

    @Mock
    private RecipeDetailModel model;

    @Mock
    private RecipeDetailView view;

    private RecipeDetailPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new RecipeDetailPresenter(model, bus);
        presenter.setView(view);
    }

    @Test
    public void itShouldRegisterBus() throws Exception {
        presenter.register();
        verify(bus).register(presenter);
    }

    @Test
    public void itShouldUnregisterBus() throws Exception {
        presenter.unregister();
        verify(bus).unregister(presenter);
    }

    @Test
    public void itShouldFetchRecipeDetail() throws Exception {
        presenter.fetchRecipeDetail("123");
        verify(model).fetchRecipeDetail("123");
    }

    @Test
    public void itShouldDisplayRecipeDetail() throws Exception {
        RecipeDetailEvent event = mock(RecipeDetailEvent.class);
        when(event.isSuccess()).thenReturn(true);
        RecipeDetailViewModel recipeDetailViewModel = mock(RecipeDetailViewModel.class);

        when(model.getViewModel(event.getPayload())).thenReturn(recipeDetailViewModel);

        presenter.onRecipeDetailReceived(event);
        verify(view).displayRecipeDetail(recipeDetailViewModel);
    }
}