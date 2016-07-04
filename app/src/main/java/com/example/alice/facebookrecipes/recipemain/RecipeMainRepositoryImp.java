package com.example.alice.facebookrecipes.recipemain;

import com.example.alice.facebookrecipes.BuildConfig;
import com.example.alice.facebookrecipes.api.RecipeSearchResponse;
import com.example.alice.facebookrecipes.api.RecipeService;
import com.example.alice.facebookrecipes.db.entities.Recipe;
import com.example.alice.facebookrecipes.recipemain.evnents.RecipeMainEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alice on 7/3/16.
 */

public class RecipeMainRepositoryImp implements RecipeMainRepository {

    private int recipePage;
    private EventBus eventBus;
    private RecipeService service;

    public RecipeMainRepositoryImp( EventBus eventBus, RecipeService service) {
        this.eventBus = eventBus;
        this.service = service;
    }


    @Override
    public void getNextRecipe() {
        Call<RecipeSearchResponse> call = service.search(BuildConfig.FOOD_API_KEY, RECENT_SORT, COUNT, recipePage);
        Callback<RecipeSearchResponse> callback = new Callback<RecipeSearchResponse>() {
            @Override
            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                if (response.isSuccessful()){
                    RecipeSearchResponse recipeSearchResponse = response.body();
                    if (recipeSearchResponse.getCount()==0){ // no encontro receta
                        setRecipePage( new Random().nextInt(RECIPE_RANGE));
                        getNextRecipe();
                    }else{
                        Recipe recipe = recipeSearchResponse.getFirstRecipe();
                        if (recipe != null ){
                            post(recipe);
                        }else {
                            post(response.message());
                        }
                    }
                }else {
                    post(response.message());
                }
            }

            @Override
            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {

            }
        };

        //asyncrona
        call.enqueue(callback);

    }

    @Override
    public void saveRecipe(Recipe recipe) {
        recipe.save();
        post();
    }

    @Override
    public void setRecipePage(int recipePage) {
        this.recipePage = recipePage;
    }

    
    private  void  post (String error, int eventType, Recipe recipe){
        RecipeMainEvent event = new RecipeMainEvent();
        event.setType(eventType);
        event.setError(error);
        event.setRecipe(recipe);
        eventBus.post(event);
    }

    private  void  post (Recipe recipe){
        post(null, RecipeMainEvent.NEXT_EVENT, recipe);
    }

    private  void  post (String error){
        post(error, RecipeMainEvent.NEXT_EVENT, null);
    }

    private  void  post (){
        post(null, RecipeMainEvent.SAVE_EVENT, null);
    }
}
