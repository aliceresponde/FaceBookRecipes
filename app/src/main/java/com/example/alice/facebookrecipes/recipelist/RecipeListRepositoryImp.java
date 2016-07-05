package com.example.alice.facebookrecipes.recipelist;

import com.example.alice.facebookrecipes.db.entities.Recipe;
import com.example.alice.facebookrecipes.db.entities.Recipe_Table;
import com.example.alice.facebookrecipes.recipelist.events.RecipeListEvent;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alice on 7/4/16.
 */

public class RecipeListRepositoryImp implements RecipeListRepository {
    private EventBus eventBus;


    public RecipeListRepositoryImp(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getSavedRecipes() {
        FlowCursorList<Recipe> storedRecipes = new FlowCursorList<Recipe>(false, Recipe.class);
        post(RecipeListEvent.READ_EVENT, storedRecipes.getAll());
        storedRecipes.close();
    }

    @Override
    public void getFavoritesRecipes() {
        List<Recipe> recipes = new Select()
                .from(Recipe.class)
                .where(Recipe_Table.favorite.in(true))
                .queryList();
        post(RecipeListEvent.READ_EVENT, recipes);
    }

    @Override
    public void upateRecipe(Recipe recipe) {
        recipe.update();
        post();
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        recipe.delete();
        post(RecipeListEvent.DELETE_EVENT, Arrays.asList(recipe));
    }




    private void  post (int evetType, List<Recipe> recipeList){
        RecipeListEvent event = new RecipeListEvent();
        event.setType(evetType);
        event.setRecipeList(recipeList);
        eventBus.post(event);
    }

    private void post(){
        post(RecipeListEvent.UPDATE_EVENT, null);
    }
}
