package com.example.alice.facebookrecipes.recipelist.di;

import com.example.alice.facebookrecipes.db.entities.Recipe;
import com.example.alice.facebookrecipes.libs.base.ImageLoader;
import com.example.alice.facebookrecipes.recipelist.RecipeListInteractor;
import com.example.alice.facebookrecipes.recipelist.RecipeListInteractorImp;
import com.example.alice.facebookrecipes.recipelist.RecipeListPresenter;
import com.example.alice.facebookrecipes.recipelist.RecipeListPresenterImp;
import com.example.alice.facebookrecipes.recipelist.RecipeListRepository;
import com.example.alice.facebookrecipes.recipelist.RecipeListRepositoryImp;
import com.example.alice.facebookrecipes.recipelist.StoredRecipeListInteractor;
import com.example.alice.facebookrecipes.recipelist.StoredRecipeListInteractorImp;
import com.example.alice.facebookrecipes.recipelist.ui.RecipeListView;
import com.example.alice.facebookrecipes.recipelist.ui.adapters.OnItemClickListener;

import com.example.alice.facebookrecipes.recipelist.ui.adapters.RecipesAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alice on 7/3/16.
 */
@Module
public class RecipeListModule {

    RecipeListView view;
    OnItemClickListener clickListener;

    public RecipeListModule(RecipeListView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    //Deb proveer los elementos que el componente requiere
    @Provides @Singleton
    RecipeListView providesRecipeListView(){
        return  this.view;
    }




    //=====================================RecipeListPresenter=========================================================================================================================
    @Provides @Singleton
    RecipeListPresenter providesRecipeListPresenter(EventBus eventBus, RecipeListView view, RecipeListInteractor interactor, StoredRecipeListInteractor storedInteractor){
        return  new RecipeListPresenterImp(eventBus, view , interactor, storedInteractor);
    }

    // ===============================interactors ======================================================================================================================================
    @Provides @Singleton
    RecipeListInteractor providesRecipeListInteractor(RecipeListRepository repository){
        return  new RecipeListInteractorImp(repository);
    }

    @Provides @Singleton
    StoredRecipeListInteractor providesStoredRecipeListInteractor(RecipeListRepository repository){
        return  new StoredRecipeListInteractorImp(repository);
    }

    // ==========================================RecipeListRepository===================================================================================================================

    @Provides @Singleton
    RecipeListRepository providesRecipeListRepository( EventBus eventBus){
        return  new RecipeListRepositoryImp(eventBus);
    }

    //================================ADAPTER =========================================================================================================================================

    @Provides @Singleton
    RecipesAdapter providesRecipesAdapter(OnItemClickListener onItemClickListener, ImageLoader imageLoader, List<Recipe> recipeList){
        return  new RecipesAdapter(onItemClickListener, imageLoader, recipeList);
    }

    @Provides @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return  this.clickListener;
    }

    @Provides @Singleton
    List<Recipe> providesEmptyList(){
        return  new ArrayList<Recipe>();
    }


}
