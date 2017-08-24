package com.temperoni.recipes.mvp.presenter;

import com.temperoni.recipes.domain.event.RecipesEvent;
import com.temperoni.recipes.mvp.model.RecipesListModel;
import com.temperoni.recipes.mvp.view.RecipesListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

/**
 * @author Leandro Temperoni
 */

public class RecipesListPresenter extends BasePresenter {

    private RecipesListView view;
    private RecipesListModel model;

    @Inject
    RecipesListPresenter(RecipesListModel model, EventBus bus) {
        super(bus);
        this.model = model;
    }

    public void setView(RecipesListView view) {
        this.view = view;
    }

    public void fetchData() {
        model.fetchData();
    }

    public void register() {
        bus.register(this);
    }

    public void unregister() {
        bus.unregister(this);
    }

    @Subscribe
    public void onRecipesReceived(RecipesEvent event) {
        if (view != null) {
            if (event.isSuccess()) {
                view.displayRecipes(event.getPayload());
            }
        }
    }
}
