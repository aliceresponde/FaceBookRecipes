package com.example.alice.facebookrecipes.api;

import android.os.Build;

import com.example.alice.facebookrecipes.db.entities.Recipe;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alice on 7/3/16.
 * Si lo  recibo como parametro el testing sera mas  granular, de lo contrario sera mas superficial
 */

public class RecipeClient {
    private Retrofit retrofit;
    private final static  String BASE_URL = "http://food2fork.com/api/";

    public RecipeClient() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public RecipeService getRecipeService(){
        return this.retrofit.create(RecipeService.class);
    }
}
