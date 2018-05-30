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

    var view: RecipeDetailView? = null

    fun fetchRecipeDetail(recipeId: String?) {
        recipeId?.let { model.fetchRecipeDetail(it) }
    }

    fun register() {
        bus.register(this)
    }

    fun unregister() {
        bus.unregister(this)
    }

    @Subscribe
    fun onRecipeDetailReceived(event: RecipeDetailEvent) {
        if (event.isSuccess) {
            view?.displayRecipeDetail(model.getViewModel(event.payload))
        }
    }
}
