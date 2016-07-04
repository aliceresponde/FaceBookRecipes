package com.example.alice.facebookrecipes.recipemain;

import com.example.alice.facebookrecipes.db.entities.Recipe;

/**
 * Created by alice on 7/3/16.
 */

public class SaveRecipeInteractorImp implements SaveRecipeInteractor {
    RecipeMainRepository repository;

    public SaveRecipeInteractorImp(RecipeMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Recipe recipe) {
        repository.saveRecipe(recipe);
    }
}
