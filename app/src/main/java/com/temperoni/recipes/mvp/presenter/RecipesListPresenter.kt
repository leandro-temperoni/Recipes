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

    private var view: RecipesListView? = null

    fun setView(view: RecipesListView) {
        this.view = view
    }

    fun fetchData() {
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
        if (view != null) {
            if (event.isSuccess) {
                view!!.displayRecipes(model.getViewModelList(event.payload))
            }
        }
    }

    override fun onRecipeCardContainerTap(recipeId: String, imageUrl: String, sharedView: View) {
        if (view != null) {
            view!!.navigateToRecipeDetail(recipeId, imageUrl, sharedView)
        }
    }
}
