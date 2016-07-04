package com.example.alice.facebookrecipes.recipemain.di;

import com.example.alice.facebookrecipes.api.RecipeClient;
import com.example.alice.facebookrecipes.api.RecipeService;
import com.example.alice.facebookrecipes.db.entities.Recipe;
import com.example.alice.facebookrecipes.recipemain.GetNextRecipeInteractor;
import com.example.alice.facebookrecipes.recipemain.GetNextRecipeInteractorImp;
import com.example.alice.facebookrecipes.recipemain.RecipeMainPresenter;
import com.example.alice.facebookrecipes.recipemain.RecipeMainPresenterImp;
import com.example.alice.facebookrecipes.recipemain.RecipeMainRepository;
import com.example.alice.facebookrecipes.recipemain.RecipeMainRepositoryImp;
import com.example.alice.facebookrecipes.recipemain.SaveRecipeInteractor;
import com.example.alice.facebookrecipes.recipemain.SaveRecipeInteractorImp;
import com.example.alice.facebookrecipes.recipemain.ui.RecipeMainView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alice on 7/3/16.
 */
@Module
public class RecipeMainModule {

    RecipeMainView view;

    public RecipeMainModule(RecipeMainView view) {
        this.view = view;
    }

    //Deb proveer los elementos que el componente requiere
    @Provides @Singleton
    RecipeMainView providesRecipeMainView(){
        return  this.view;
    }

    //=====================================RecipeMainPresenter=========================================================================================================================
    @Provides @Singleton
    RecipeMainPresenter providesRecipeMainPresenter(EventBus eventBus, RecipeMainView view, SaveRecipeInteractor saveRecipeInteractor, GetNextRecipeInteractor getNextRecipeInteractor){
        return  new RecipeMainPresenterImp(eventBus, view , saveRecipeInteractor, getNextRecipeInteractor);
    }

    // ===============================interactors ======================================================================================================================================
    @Provides @Singleton
    SaveRecipeInteractor providesSaveRecipeInteractor(RecipeMainRepository repository){
        return  new SaveRecipeInteractorImp(repository);
    }

    @Provides @Singleton
    GetNextRecipeInteractor providesGetNextRecipeInteractor(RecipeMainRepository repository){
        return  new GetNextRecipeInteractorImp(repository);
    }

    // ==========================================RecipeMainRepository===================================================================================================================

    @Provides @Singleton
    RecipeMainRepository providesRecipeMainRepository( EventBus eventBus, RecipeService service){
        return  new RecipeMainRepositoryImp( eventBus , service);
    }

//    ================================================Retrofit============================================================================================================================

    @Provides @Singleton
    RecipeService providesRecipeService(){
        return  new RecipeClient().getRecipeService();
    }

}
