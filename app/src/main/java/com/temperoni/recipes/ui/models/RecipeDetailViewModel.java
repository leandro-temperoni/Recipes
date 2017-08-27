package com.temperoni.recipes.ui.models;

import com.temperoni.recipes.domain.dto.Ingredient;
import com.temperoni.recipes.domain.dto.Section;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leandro Temperoni
 */
public class RecipeDetailViewModel {

    private int id;
    private String imageUrl;
    private List<String> ingredients;
    private String introduction;
    private String name;
    private List<SectionViewModel> sections;

    public RecipeDetailViewModel(Builder builder) {
        builder.id = id;
        builder.imageUrl = imageUrl;
        builder.ingredients = ingredients;
        builder.introduction = introduction;
        builder.name = name;
        builder.sections = sections;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getIntroduction() {
        return introduction;
    }

    public List<SectionViewModel> getSections() {
        return sections;
    }

    public static class Builder {

        int id;
        String imageUrl;
        List<String> ingredients;
        String introduction;
        String name;
        List<SectionViewModel> sections;

        List<Ingredient> ingredientList;
        List<Section> sectionList;

        public Builder(int id, String name, String imageUrl, String introduction) {
            this.id = id;
            this.name = name;
            this.imageUrl = imageUrl;
            this.introduction = introduction;
        }

        public Builder withIngredients(List<Ingredient> ingredientList) {
            this.ingredientList = ingredientList;
            return this;
        }

        public Builder withSections(List<Section> sectionList) {
            this.sectionList = sectionList;
            return this;
        }

        public RecipeDetailViewModel build() {
            getIngredients(ingredientList);
            getSections(sectionList);

            return new RecipeDetailViewModel(this);
        }

        private void getIngredients(List<Ingredient> ingredientList) {
            ingredients = new ArrayList<>();
            for (Ingredient ingredient : ingredientList) {
                ingredients.add(ingredient.getName() + " de " + ingredient.getAmount());
            }
        }

        private void getSections(List<Section> sectionList) {
            sections = new ArrayList<>();
            for (Section section : sectionList) {
                sections.add(getSectionViewModel(section));
            }
        }

        private SectionViewModel getSectionViewModel(Section section) {
            SectionViewModel sectionViewModel = new SectionViewModel();
            sectionViewModel.setDescription(section.getDescription());
            sectionViewModel.setSteps(section.getSteps());
            return sectionViewModel;
        }
    }
}
