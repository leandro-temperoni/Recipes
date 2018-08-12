package com.temperoni.recipes.manager

import com.google.firebase.database.*
import com.temperoni.recipes.domain.dto.Recipe
import com.temperoni.recipes.domain.event.RecipeDetailEvent
import com.temperoni.recipes.domain.event.RecipesEvent
import com.temperoni.recipes.domain.event.ResponseEvent
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * @author Leandro Temperoni
 */
class RecipesManager @Inject constructor() {

    private lateinit var reference: DatabaseReference
    private var recipesQueryHandler: RecipesQueryHandler? = null
    private var recipeDetailQueryHandler: RecipeDetailQueryHandler? = null

    fun fetchRecipes() {
        reference = FirebaseDatabase.getInstance().getReference("recipes")
        recipesQueryHandler = RecipesQueryHandler()
        reference.addListenerForSingleValueEvent(recipesQueryHandler!!)
    }

    fun fetchRecipeDetail(recipeId: String) {
        reference = FirebaseDatabase.getInstance().getReference("recipes").child(recipeId)
        recipeDetailQueryHandler = RecipeDetailQueryHandler()
        reference.addListenerForSingleValueEvent(recipeDetailQueryHandler!!)
    }

    private fun postEvent(event: ResponseEvent<*>) {
        EventBus.getDefault().post(event)
        unRegisterListeners()
    }

    private fun unRegisterListeners() {
        recipesQueryHandler?.let { reference.removeEventListener(recipesQueryHandler!!) }
        recipeDetailQueryHandler?.let { reference.removeEventListener(recipeDetailQueryHandler!!) }
    }

    private inner class RecipesQueryHandler : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val recipes = mutableListOf<Recipe>()
            val iterable = dataSnapshot.children
            for (data in iterable) {
                val item = data.getValue(Recipe::class.java)
                item?.let { recipes.add(it) }
            }
            val event = RecipesEvent()
            event.payload = recipes
            postEvent(event)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            databaseError.message
            EventBus.getDefault().post(RecipesEvent())
        }
    }

    private inner class RecipeDetailQueryHandler : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val recipe = dataSnapshot.getValue(Recipe::class.java) ?: Recipe()
            val event = RecipeDetailEvent()
            event.payload = recipe
            postEvent(event)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            databaseError.message
            EventBus.getDefault().post(RecipeDetailEvent())
        }
    }
}
