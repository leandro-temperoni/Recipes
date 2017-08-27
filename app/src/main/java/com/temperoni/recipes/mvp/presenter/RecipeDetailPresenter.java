package com.temperoni.recipes.mvp.presenter;

import com.temperoni.recipes.mvp.model.RecipeDetailModel;
import com.temperoni.recipes.mvp.view.RecipeDetailView;

import org.greenrobot.eventbus.EventBus;

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
}
