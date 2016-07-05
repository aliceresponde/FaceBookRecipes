package com.example.alice.facebookrecipes.recipelist;

import com.example.alice.facebookrecipes.db.entities.Recipe;

/**
 * Created by alice on 7/4/16.
 */

public class StoredRecipeListInteractorImp implements StoredRecipeListInteractor {
    private RecipeListRepository repository;

    public StoredRecipeListInteractorImp(RecipeListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeUpdateSavedRecipe(Recipe recipe) {
        repository.upateRecipe(recipe);
    }

    @Override
    public void executeDeleteSavedRecipe(Recipe recipe) {
        repository.deleteRecipe(recipe);
    }
}
