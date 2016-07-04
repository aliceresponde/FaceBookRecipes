package com.example.alice.facebookrecipes.recipemain;

import com.example.alice.facebookrecipes.db.entities.Recipe;
import com.example.alice.facebookrecipes.recipemain.evnents.RecipeMainEvent;
import com.example.alice.facebookrecipes.recipemain.ui.RecipeMainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by alice on 7/3/16.
 */

public class RecipeMainPresenterImp implements RecipeMainPresenter {

    private EventBus eventBus;
    private RecipeMainView view;
    SaveRecipeInteractor saveRecipeInteractor;
    GetNextRecipeInteractor getNextRecipeInteractor;

//    ===============================CONSTRUCTOR ================================================

    public RecipeMainPresenterImp(EventBus eventBus, RecipeMainView view, SaveRecipeInteractor saveRecipeInteractor, GetNextRecipeInteractor getNextRecipeInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.saveRecipeInteractor = saveRecipeInteractor;
        this.getNextRecipeInteractor = getNextRecipeInteractor;
    }

    //    =================================Ciclo de vida ============================================
    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }


    @Override
    public void dismissRecipe() {
        if (view != null) {
            view.dismissAnimation();
        }

        getNextRecipe();
    }

    @Override
    public void getNextRecipe() {
        if (view != null) {
            view.showProgress();
            view.hideUIElements();
        }
        getNextRecipeInteractor.execute();
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        if (view != null) {
            view.saveAnimation();
            view.hideUIElements();
            view.showProgress();
        }
        saveRecipeInteractor.execute(recipe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeMainEvent event) {
        if (view != null){
            String error = event.getError();
            if (error != null){
                view.hideProgress();
                view.onGetRecipeError(error);
            }else {
                if (event.getType() == RecipeMainEvent.NEXT_EVENT){
                    view.setRecipe(event.getRecipe());

                }else if (event.getType() == RecipeMainEvent.SAVE_EVENT){
                    view.onRecipeSaved();
                    getNextRecipeInteractor.execute();
                }
            }

        }
    }

    @Override
    public RecipeMainView getView() {
        return this.view;
    }

    @Override
    public void imageReady() {
        if (view != null){
            view.hideProgress();
            view.showUIElements();
        }
    }

    @Override
    public void imageError(String error) {
        if (view != null) {
            view.onGetRecipeError(error);
        }
    }
}
