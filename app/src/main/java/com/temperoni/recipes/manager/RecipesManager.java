package com.temperoni.recipes.manager;

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

    private Query query;
    private RecipesQueryHandler recipesQueryHandler;
    private RecipeDetailQueryHandler recipeDetailQueryHandler;

    @Inject
    RecipesManager() {
    }

    public void fetchRecipes() {
        query = FirebaseDatabase.getInstance().getReference("recipes");
        recipesQueryHandler = new RecipesQueryHandler();
        query.addListenerForSingleValueEvent(recipesQueryHandler);
    }

    public void fetchRecipeDetail(String recipeId) {
        query = FirebaseDatabase.getInstance().getReference("recipes").child(recipeId);
        recipeDetailQueryHandler = new RecipeDetailQueryHandler();
        query.addListenerForSingleValueEvent(recipeDetailQueryHandler);
    }

    private void postEvent(ResponseEvent event, Object payload, String errorMessage) {
        if (payload != null) {
            event.setPayload(payload);
        } else {
            event.setErrorMessage(errorMessage);
        }
        EventBus.getDefault().post(event);

        unRegisterListeners();
    }

    private void unRegisterListeners() {
        if (recipesQueryHandler != null) {
            query.removeEventListener(recipesQueryHandler);
        }
        if (recipeDetailQueryHandler != null) {
            query.removeEventListener(recipeDetailQueryHandler);
        }
    }

    private class RecipesQueryHandler implements ValueEventListener {
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
    }

    private class RecipeDetailQueryHandler implements ValueEventListener {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Recipe recipe = null;
            if (dataSnapshot != null) {
                recipe = dataSnapshot.getValue(Recipe.class);
            }
            postEvent(new RecipeDetailEvent(), recipe, null);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            databaseError.getMessage();
            EventBus.getDefault().post(new RecipeDetailEvent());
        }
    }
}
