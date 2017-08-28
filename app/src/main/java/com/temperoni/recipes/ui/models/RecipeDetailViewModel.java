package com.temperoni.recipes.ui.models;

import com.temperoni.recipes.domain.dto.Ingredient;
import com.temperoni.recipes.domain.dto.InstructionItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leandro Temperoni
 */
public class RecipeDetailViewModel {

    private int id;
    private String imageUrl;
    private List<IngredientEntryViewModel> ingredients;
    private String introduction;
    private String name;
    private List<InstructionItemViewModel> instructions;

    private RecipeDetailViewModel(Builder builder) {
        id = builder.id;
        imageUrl = builder.imageUrl;
        ingredients = builder.ingredients;
        introduction = builder.introduction;
        name = builder.name;
        instructions = builder.instructions;
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

    public List<IngredientEntryViewModel> getIngredients() {
        return ingredients;
    }

    public String getIntroduction() {
        return introduction;
    }

    public List<InstructionItemViewModel> getInstructions() {
        return instructions;
    }

    public static class Builder {

        int id;
        String imageUrl;
        List<IngredientEntryViewModel> ingredients;
        String introduction;
        String name;
        List<InstructionItemViewModel> instructions;

        List<Ingredient> ingredientList;
        List<InstructionItem> instructionItemList;

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

        public Builder withInstructions(List<InstructionItem> instructionItemList) {
            this.instructionItemList = instructionItemList;
            return this;
        }

        public RecipeDetailViewModel build() {
            getIngredients(ingredientList);
            getInstructions(instructionItemList);

            return new RecipeDetailViewModel(this);
        }

        private void getIngredients(List<Ingredient> ingredientList) {
            ingredients = new ArrayList<>();
            for (Ingredient ingredient : ingredientList) {
                ingredients.add(getIngredientViewModel(ingredient));
            }

            if (!ingredients.isEmpty()) {
                ingredients.get(ingredientList.size() - 1).setLastEntry(true);
            }
        }

        private IngredientEntryViewModel getIngredientViewModel(Ingredient ingredient) {
            IngredientEntryViewModel ingredientEntryViewModel = new IngredientEntryViewModel();
            ingredientEntryViewModel.setDescription(ingredient.getAmount() + " de " + ingredient.getName());
            return ingredientEntryViewModel;
        }

        private void getInstructions(List<InstructionItem> instructionItemList) {
            instructions = new ArrayList<>();
            for (InstructionItem instructionItem : instructionItemList) {
                instructions.add(getInstructionItemViewModel(instructionItem));
            }
        }

        private InstructionItemViewModel getInstructionItemViewModel(InstructionItem instructionItem) {
            InstructionItemViewModel instructionItemViewModel = new InstructionItemViewModel();
            instructionItemViewModel.setDescription(instructionItem.getDescription());
            instructionItemViewModel.setSteps(instructionItem.getSteps());
            return instructionItemViewModel;
        }
    }
}
