package com.example.alice.facebookrecipes.api;

import com.example.alice.facebookrecipes.db.entities.Recipe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alice on 7/3/16.
 * Exponer el sercivio
 */

public interface RecipeService {
    @GET("search")
    Call<RecipeSearchResponse> search (@Query("key") String key,
                                       @Query("sort") String sort,
                                       @Query("count") int count,
                                       @Query("page") int page);
}
