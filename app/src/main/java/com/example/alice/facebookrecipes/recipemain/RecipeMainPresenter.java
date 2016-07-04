package com.example.alice.facebookrecipes.recipemain;

import com.example.alice.facebookrecipes.db.entities.Recipe;
import com.example.alice.facebookrecipes.recipemain.evnents.RecipeMainEvent;
import com.example.alice.facebookrecipes.recipemain.ui.RecipeMainView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by alice on 7/3/16.
 */

public interface RecipeMainPresenter {
//    ================EventBus==============
    void onCreate();
    void onDestroy();

    void dismissRecipe();
    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void onEventMainThread(RecipeMainEvent event);

    RecipeMainView getView();

    void imageReady();

    void imageError(String localizedMessage);
}
