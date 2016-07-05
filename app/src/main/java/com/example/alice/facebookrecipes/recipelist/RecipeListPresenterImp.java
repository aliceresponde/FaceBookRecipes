package com.example.alice.facebookrecipes.recipelist;

import com.example.alice.facebookrecipes.db.entities.Recipe;
import com.example.alice.facebookrecipes.recipelist.events.RecipeListEvent;
import com.example.alice.facebookrecipes.recipelist.ui.RecipeListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by alice on 7/4/16.
 */

public class RecipeListPresenterImp implements RecipeListPresenter {

    private EventBus eventBus;
    private RecipeListView  view;
    private RecipeListInteractor interactor;
    private StoredRecipeListInteractor storedInteractor;


//    ===========================Event ===================================================
    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Subscribe
    @Override
    public void onEvnetMainThread(RecipeListEvent event) {
        if (view != null){
            switch (event.getType()){
                case  RecipeListEvent.READ_EVENT:
                    view.setRecipes(event.getRecipeList());
                    break;

                case  RecipeListEvent.UPDATE_EVENT:
                    view.recipeUpdated();
                    break;

                case  RecipeListEvent.DELETE_EVENT:
                    Recipe recipe = event.getRecipeList().get(0);
                    view.recipeDeleted(recipe);
                    break;
            }
        }

    }
//    ==============================interactor================================================

    @Override
    public void getRecipes() {
        interactor.executeGetSavedRecipes();
    }

//    =========================StoredInteractor===========================================

    @Override
    public void removeRecipe(Recipe recipe) {
        storedInteractor.executeDeleteSavedRecipe(recipe);
    }

    @Override
    public void toogleFavorite(Recipe recipe) {
        boolean fav = recipe.getFavorite();
        recipe.setFavorite(!fav);
        storedInteractor.executeUpdateSavedRecipe(recipe);
    }

//  =========================================================================================

    @Override
    public RecipeListView getView() {
        return null;
    }
}
