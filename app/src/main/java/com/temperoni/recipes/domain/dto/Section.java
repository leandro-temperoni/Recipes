package com.temperoni.recipes.domain.dto;

import java.util.List;

/**
 * @author Leandro Temperoni
 */

class Section {

    private String description;
    private List<String> steps;

    public String getDescription() {
        return description;
    }

    public List<String> getSteps() {
        return steps;
    }
}
