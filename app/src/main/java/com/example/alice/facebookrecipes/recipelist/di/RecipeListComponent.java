package com.example.alice.facebookrecipes.recipelist.di;

import com.example.alice.facebookrecipes.libs.di.LibsModule;
import com.example.alice.facebookrecipes.recipelist.RecipeListPresenter;
import com.example.alice.facebookrecipes.recipelist.ui.adapters.RecipesAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alice on 7/3/16.
 * Especifica el API para la injeccion
 */
@Singleton
@Component(modules = {RecipeListModule.class, LibsModule.class})
public interface RecipeListComponent {

    RecipesAdapter getAdapter();
    //    ====================== RecipeMainModule =======================
    RecipeListPresenter getPresenter();
}
