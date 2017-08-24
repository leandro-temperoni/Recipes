package com.temperoni.recipes.mvp.presenter;

import com.temperoni.recipes.domain.dto.Recipe;
import com.temperoni.recipes.domain.event.RecipesEvent;
import com.temperoni.recipes.mvp.model.RecipesListModel;
import com.temperoni.recipes.mvp.view.RecipesListView;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Leandro Temperoni
 */
public class RecipesListPresenterTest {

    @Mock
    private EventBus bus;

    @Mock
    private RecipesListModel model;

    @Mock
    private RecipesListView view;

    private RecipesListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new RecipesListPresenter(model, bus);
        presenter.setView(view);
    }

    @Test
    public void itShouldFetchData() throws Exception {
        presenter.fetchData();
        verify(model).fetchData();
    }

    @Test
    public void itShouldDisplayRecipes() throws Exception {
        RecipesEvent event = mock(RecipesEvent.class);
        when(event.isSuccess()).thenReturn(true);
        ArrayList<Recipe> recipes = new ArrayList<>();
        when(event.getPayload()).thenReturn(recipes);
        presenter.onRecipesReceived(event);
        verify(view).displayRecipes(recipes);
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
}