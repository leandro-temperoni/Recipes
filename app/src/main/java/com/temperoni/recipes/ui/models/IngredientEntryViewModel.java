package com.temperoni.recipes.ui.models;

/**
 * @author Leandro Temperoni
 */
public class IngredientEntryViewModel {

    private String description;
    private boolean isLastEntry;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLastEntry() {
        return isLastEntry;
    }

    public void setLastEntry(boolean lastEntry) {
        isLastEntry = lastEntry;
    }
}
