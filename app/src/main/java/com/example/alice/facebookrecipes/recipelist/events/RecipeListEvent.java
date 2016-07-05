package com.example.alice.facebookrecipes.recipelist.events;

import com.example.alice.facebookrecipes.db.entities.Recipe;

import java.util.List;

/**
 * Created by alice on 7/4/16.
 */
public class RecipeListEvent {

    private int type;
    private List<Recipe> recipeList;

    public final static  int READ_EVENT = 0;
    public final static  int UPDATE_EVENT = 1;
    public final static  int DELETE_EVENT = 2;

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
