package com.temperoni.recipes.utils;

import com.google.gson.Gson;
import com.temperoni.recipes.domain.dto.Recipe;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author Leandro Temperoni
 */
public class Decoder {

    public List<Recipe> decodeJson(String fileName) {
        return new Gson().fromJson(getJson(fileName), RecipeList.class).getRecipes();
    }

    private String getJson(String fileName) {
        String result = "";

        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private class RecipeList {
        private List<Recipe> recipes;

        public List<Recipe> getRecipes() {
            return recipes;
        }
    }
}
