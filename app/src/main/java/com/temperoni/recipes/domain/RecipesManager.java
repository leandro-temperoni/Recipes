package com.temperoni.recipes;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.temperoni.recipes.domain.dto.Recipe;
import com.temperoni.recipes.domain.event.RecipesEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Leandro Temperoni
 */
public class RecipesManager {

    @Inject
    public RecipesManager() {
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
                postEvent(recipes);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                EventBus.getDefault().post(new RecipesEvent());
            }
        });
    }

    private void postEvent(List<Recipe> recipes) {
        RecipesEvent event = new RecipesEvent();
        event.setPayload(recipes);
        EventBus.getDefault().post(event);
    }
}
