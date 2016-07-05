package com.example.alice.facebookrecipes.recipelist.ui;

import com.example.alice.facebookrecipes.db.entities.Recipe;

import java.util.List;

/**
 * Created by alice on 7/4/16.
 */

public interface RecipeListView {
    void setRecipes(List<Recipe> data);
    void  recipeUpdated();
    void recipeDeleted(Recipe recipe);


}
