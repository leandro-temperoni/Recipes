package com.temperoni.recipes.ui.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.temperoni.recipes.R;
import com.temperoni.recipes.ui.models.RecipeViewModel;

import java.util.List;

import static java.lang.String.*;

/**
 * @author Leandro Temperoni
 */

public class RecipesListAdapter extends RecyclerView.Adapter<RecipesListAdapter.RecipeViewHolder> {

    private List<RecipeViewModel> recipes;
    private RecipesListListener listener;

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.name.setText(recipes.get(position).getName());
        Picasso.with(holder.image.getContext())
                .load(recipes.get(position).getImageUrl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return recipes != null ? recipes.size() : 0;
    }

    public void setData(List<RecipeViewModel> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    public void setListener(RecipesListListener listener) {
        this.listener = listener;
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;
        CardView container;

        RecipeViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.recipe_name);
            image = itemView.findViewById(R.id.recipe_image);
            image.setDrawingCacheEnabled(true);
            container = itemView.findViewById(R.id.recipe_card_container);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onRecipeCardContainerTap(
                                valueOf(recipes.get(getAdapterPosition()).getId()),
                                valueOf(recipes.get(getAdapterPosition()).getImageUrl()),
                                image);
                    }
                }
            });
        }
    }

    public interface RecipesListListener {

        void onRecipeCardContainerTap(String recipeId, String imageUrl, View sharedView);
    }
}
