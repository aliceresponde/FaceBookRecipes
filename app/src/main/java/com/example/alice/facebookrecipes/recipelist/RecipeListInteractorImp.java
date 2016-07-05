package com.example.alice.facebookrecipes.recipelist;

/**
 * Created by alice on 7/4/16.
 */

public class RecipeListInteractorImp implements RecipeListInteractor {
    private RecipeListRepository repository;

    public RecipeListInteractorImp(RecipeListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeGetSavedRecipes() {
        repository.getSavedRecipes();
    }

    @Override
    public void executeSearchFavsRecipes() {
        repository.getFavoritesRecipes();
    }

}
