package com.temperoni.recipes.ui.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.temperoni.recipes.R
import com.temperoni.recipes.ui.models.RecipeViewModel
import kotlinx.android.synthetic.main.recipe_list_item.view.*

/**
 * @author Leandro Temperoni
 */

class RecipesListAdapter : RecyclerView.Adapter<RecipesListAdapter.RecipeViewHolder>() {

    var recipes: List<RecipeViewModel>? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    lateinit var listener: RecipesListListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recipe_list_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.name.text = recipes!![position].name
        Picasso.with(holder.image.context)
                .load(recipes!![position].imageUrl)
                .into(holder.image)
    }

    override fun getItemCount(): Int {
        return recipes?.size ?: 0
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.name
        var image: ImageView = itemView.image
        var container: CardView = itemView.container

        init {

            image.isDrawingCacheEnabled = true

            container.setOnClickListener {
                listener.onRecipeCardContainerTap(
                        recipes?.get(adapterPosition)?.id.toString(),
                        recipes?.get(adapterPosition)?.imageUrl.toString(),
                        image)
            }
        }
    }

    interface RecipesListListener {

        fun onRecipeCardContainerTap(recipeId: String, imageUrl: String, sharedView: View)
    }
}
