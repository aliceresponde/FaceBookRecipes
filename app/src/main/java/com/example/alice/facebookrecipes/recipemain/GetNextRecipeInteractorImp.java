package com.example.alice.facebookrecipes.recipemain;

import java.util.Random;

/**
 * Created by alice on 7/3/16.
 */

public class GetNextRecipeInteractorImp implements GetNextRecipeInteractor {
    RecipeMainRepository repository;

    public GetNextRecipeInteractorImp(RecipeMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        int recipePage  = new Random().nextInt(RecipeMainRepository.RECIPE_RANGE);
        repository.setRecipePage(recipePage);
        repository.getNextRecipe();
    }
}
