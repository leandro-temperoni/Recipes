package com.temperoni.recipes.ui.models;

import java.util.List;

/**
 * @author Leandro Temperoni
 */
public class InstructionItemViewModel {

    private String description;
    private boolean descriptionAvailable;
    private List<String> steps;

    public String getDescription() {
        return description;
    }

    public boolean hasDescriptionAvailable() {
        return descriptionAvailable;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDescriptionAvailable(boolean descriptionAvailable) {
        this.descriptionAvailable = descriptionAvailable;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }
}
