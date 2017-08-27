package com.temperoni.recipes.domain;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.temperoni.recipes.domain.dto.Recipe;
import com.temperoni.recipes.domain.event.RecipeDetailEvent;
import com.temperoni.recipes.domain.event.RecipesEvent;
import com.temperoni.recipes.domain.event.ResponseEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Leandro Temperoni
 */
public class RecipesManager {

    @Inject
    RecipesManager() {
    }

    public void fetchRecipes() {

        Query query = FirebaseDatabase.getInstance().getReference("recipes");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Recipe> recipes = new ArrayList<>();
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                for (DataSnapshot data : iterable) {
                    recipes.add(data.getValue(Recipe.class));
                }
                postEvent(new RecipesEvent(), recipes, null);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.getMessage();
                EventBus.getDefault().post(new RecipesEvent());
            }
        });
    }

    public void fetchRecipeDetail(String recipeId) {

        Query query = FirebaseDatabase.getInstance().getReference("recipes").child(recipeId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Recipe recipe = null;
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                for (DataSnapshot data : iterable) {
                    recipe = data.getValue(Recipe.class);
                }
                postEvent(new RecipeDetailEvent(), recipe, null);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                EventBus.getDefault().post(new RecipesEvent());
            }
        });
    }

    private void postEvent(ResponseEvent event, Object payload, String errorMessage) {
        if (payload != null) {
            event.setPayload(payload);
        } else {
            event.setErrorMessage(errorMessage);
        }
        EventBus.getDefault().post(event);
    }
}
