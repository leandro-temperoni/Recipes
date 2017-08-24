package com.temperoni.recipes.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.temperoni.recipes.R;
import com.temperoni.recipes.domain.dto.Recipe;

import java.util.List;

/**
 * @author Leandro Temperoni
 */

public class RecipesListAdapter extends RecyclerView.Adapter<RecipesListAdapter.RecipeViewHolder> {

    private List<Recipe> recipes;

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
                .load(recipes.get(position).getImage())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return recipes != null ? recipes.size() : 0;
    }

    public void setData(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;

        RecipeViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.recipe_name);
            image = itemView.findViewById(R.id.recipe_image);
        }
    }
}
