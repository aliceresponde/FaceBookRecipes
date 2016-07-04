package com.example.alice.facebookrecipes.recipemain;

import com.example.alice.facebookrecipes.db.entities.Recipe;

/**
 * Created by alice on 7/3/16.
 */

public interface RecipeMainRepository {
     public final static int COUNT = 1;
     public final static String RECENT_SORT = "r";
     public final static int RECIPE_RANGE = 100000;

    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void setRecipePage(int recipePage);

}
