package com.example.alice.facebookrecipes.recipelist;

import com.example.alice.facebookrecipes.db.entities.Recipe;

/**
 * Created by alice on 7/4/16.
 */

public interface RecipeListRepository {
//    ==============RecipeListInteractor ====================================

    void getSavedRecipes();

//    ====================StoredRecipeListInteractor========================
    void upateRecipe(Recipe recipe);
    void deleteRecipe(Recipe recipe);
}
