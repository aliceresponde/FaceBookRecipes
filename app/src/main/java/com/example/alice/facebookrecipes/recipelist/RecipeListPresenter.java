package com.example.alice.facebookrecipes.recipelist;

import com.example.alice.facebookrecipes.db.entities.Recipe;
import com.example.alice.facebookrecipes.recipelist.events.RecipeListEvent;
import com.example.alice.facebookrecipes.recipelist.ui.RecipeListView;

/**
 * Created by alice on 7/4/16.
 */

public interface RecipeListPresenter {

//    =================EVNET=====================
    void onCreate();
    void onDestroy();


    void getRecipes();
    void removeRecipe(Recipe recipe);
    void toogleFavorite(Recipe recipe);
    void onEvnetMainThread(RecipeListEvent event);

//    ==============================VIEW======================
    RecipeListView getView();
}
