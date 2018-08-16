package com.temperoni.recipes.mvp.presenter

import android.view.View
import com.temperoni.recipes.domain.event.RecipesEvent
import com.temperoni.recipes.mvp.model.RecipesListModel
import com.temperoni.recipes.mvp.view.RecipesListView
import com.temperoni.recipes.ui.adapters.RecipesListAdapter.RecipesListListener
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

/**
 * @author Leandro Temperoni
 */
class RecipesListPresenter

    @Inject
    constructor(private val model: RecipesListModel, bus: EventBus) : BasePresenter(bus), RecipesListListener {

    var view: RecipesListView? = null

    fun fetchData() {
        view?.displayLoading()
        model.fetchData()
    }

    fun register() {
        bus.register(this)
    }

    fun unregister() {
        bus.unregister(this)
    }

    @Subscribe
    fun onRecipesReceived(event: RecipesEvent) {
        val data = event.payload as ArrayList?
        if (event.isSuccess && data != null) {
            if (data.isEmpty()) {
                view?.displayEmptyState()
            } else {
                view?.displayRecipes(model.getViewModelList(data))
            }
        } else {
            view?.displayError()
        }
    }

    override fun onRecipeCardContainerTap(recipeId: String, imageUrl: String, sharedView: View) {
        view?.navigateToRecipeDetail(recipeId, imageUrl, sharedView)
    }
}
