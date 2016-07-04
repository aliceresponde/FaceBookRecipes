package com.example.alice.facebookrecipes.recipemain.ui;

import com.example.alice.facebookrecipes.db.entities.Recipe;

/**
 * Created by alice on 7/3/16.
 */

public interface RecipeMainView {
    void showProgress();
    void hideProgress();

    void showUIElements();
    void hideUIElements();

    void saveAnimation();
    void dismissAnimation();

    void  onRecipeSaved();

    void setRecipe(Recipe recipe);
    void onGetRecipeError(String error);

}
