package com.example.alice.facebookrecipes.recipelist;

import com.example.alice.facebookrecipes.db.entities.Recipe;

/**
 * Created by alice on 7/4/16.
 */

public interface StoredRecipeListInteractor {
    void executeUpdateSavedRecipe(Recipe recipe);
    void executeDeleteSavedRecipe(Recipe recipe);

}
