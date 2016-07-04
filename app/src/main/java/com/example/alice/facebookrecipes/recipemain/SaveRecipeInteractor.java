package com.example.alice.facebookrecipes.recipemain;

import com.example.alice.facebookrecipes.db.entities.Recipe;

/**
 * Created by alice on 7/3/16.
 */

public interface SaveRecipeInteractor {

    //objeto a guardar
    void execute(Recipe recipe);
}
