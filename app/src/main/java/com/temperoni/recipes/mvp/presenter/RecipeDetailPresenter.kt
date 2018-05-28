package com.temperoni.recipes.mvp.presenter

import com.temperoni.recipes.domain.event.RecipeDetailEvent
import com.temperoni.recipes.mvp.model.RecipeDetailModel
import com.temperoni.recipes.mvp.view.RecipeDetailView

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

import javax.inject.Inject

/**
 * @author Leandro Temperoni
 */
class RecipeDetailPresenter

    @Inject
    constructor(private val model: RecipeDetailModel, bus: EventBus) : BasePresenter(bus) {

    private var view: RecipeDetailView? = null

    fun setView(view: RecipeDetailView) {
        this.view = view
    }

    fun fetchRecipeDetail(recipeId: String) {
        model.fetchRecipeDetail(recipeId)
    }

    fun register() {
        bus.register(this)
    }

    fun unregister() {
        bus.unregister(this)
    }

    @Subscribe
    fun onRecipeDetailReceived(event: RecipeDetailEvent) {
        if (view != null) {
            if (event.isSuccess) {
                view!!.displayRecipeDetail(model.getViewModel(event.payload))
            }
        }
    }
}
