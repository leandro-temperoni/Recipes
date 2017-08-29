package com.temperoni.recipes.mvp.presenter;

import com.temperoni.recipes.domain.event.RecipeDetailEvent;
import com.temperoni.recipes.mvp.model.RecipeDetailModel;
import com.temperoni.recipes.mvp.view.RecipeDetailView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

/**
 * @author Leandro Temperoni
 */
public class RecipeDetailPresenter extends BasePresenter {

    private RecipeDetailView view;
    private RecipeDetailModel model;

    @Inject
    RecipeDetailPresenter(RecipeDetailModel model, EventBus bus) {
        super(bus);
        this.model = model;
    }

    public void setView(RecipeDetailView view) {
        this.view = view;
    }

    public void fetchRecipeDetail(String recipeId) {
        model.fetchRecipeDetail(recipeId);
    }

    public void register() {
        bus.register(this);
    }

    public void unregister() {
        bus.unregister(this);
    }

    @Subscribe
    public void onRecipeDetailReceived(RecipeDetailEvent event) {
        if (view != null) {
            if (event.isSuccess()) {
                view.displayRecipeDetail(model.getViewModel(event.getPayload()));
            }
        }
    }
}
