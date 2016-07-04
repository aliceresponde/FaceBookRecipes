package com.example.alice.facebookrecipes.recipemain.di;

import com.example.alice.facebookrecipes.db.entities.Recipe;
import com.example.alice.facebookrecipes.libs.base.ImageLoader;
import com.example.alice.facebookrecipes.libs.di.LibsModule;
import com.example.alice.facebookrecipes.recipemain.RecipeMainPresenter;
import com.example.alice.facebookrecipes.recipemain.ui.RecipeMainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alice on 7/3/16.
 * Especifica el API para la injeccion
 */
@Singleton
@Component(modules = {RecipeMainModule.class, LibsModule.class})
public interface RecipeMainComponent {
//    void  inject(RecipeMainActivity activity);

//    ====================LibsModule================================
    ImageLoader getImageLoader();
//    ====================== RecipeMainModule =======================
    RecipeMainPresenter getPresenter ();
}
