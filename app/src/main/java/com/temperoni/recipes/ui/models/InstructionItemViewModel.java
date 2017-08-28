package com.temperoni.recipes.ui.models;

import java.util.List;

/**
 * @author Leandro Temperoni
 */
class InstructionItemViewModel {

    private String description;
    private List<String> steps;

    public String getDescription() {
        return description;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }
}
