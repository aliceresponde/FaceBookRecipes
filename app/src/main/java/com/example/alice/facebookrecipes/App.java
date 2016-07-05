package com.example.alice.facebookrecipes;

import android.app.Application;
import android.content.Intent;

import com.crashlytics.android.Crashlytics;
import com.example.alice.facebookrecipes.libs.di.LibsModule;
import com.example.alice.facebookrecipes.login.ui.LoginActivity;
import com.example.alice.facebookrecipes.recipelist.di.DaggerRecipeListComponent;
import com.example.alice.facebookrecipes.recipelist.di.RecipeListComponent;
import com.example.alice.facebookrecipes.recipelist.di.RecipeListModule;
import com.example.alice.facebookrecipes.recipelist.ui.RecipeListActivity;
import com.example.alice.facebookrecipes.recipelist.ui.RecipeListView;

import com.example.alice.facebookrecipes.recipelist.ui.adapters.OnItemClickListener;
import com.example.alice.facebookrecipes.recipemain.di.DaggerRecipeMainComponent;
import com.example.alice.facebookrecipes.recipemain.di.RecipeMainComponent;
import com.example.alice.facebookrecipes.recipemain.di.RecipeMainModule;
import com.example.alice.facebookrecipes.recipemain.ui.RecipeMainActivity;
import com.example.alice.facebookrecipes.recipemain.ui.RecipeMainView;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.raizlabs.android.dbflow.config.FlowManager;

import io.fabric.sdk.android.Fabric;

/**
 * Created by alice on 7/2/16.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        initFacebook();
        initDB();
    }

    private void initDB() {
        FlowManager.init(this);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(this);
    }

    public void logout() {
        //ciero sesion
        LoginManager.getInstance().logOut();
        //crear intent nuevo a partir de loginActivity.class
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public RecipeMainComponent getRecipeMainComponent(RecipeMainActivity activity, RecipeMainView view) {
        return DaggerRecipeMainComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .recipeMainModule(new RecipeMainModule(view))
                .build();
    }


    public RecipeListComponent getRecipeListComponent(RecipeListActivity activity, RecipeListView view, OnItemClickListener clickListener) {
        return
                DaggerRecipeListComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .recipeListModule(new RecipeListModule(view, clickListener))
                .build();
    }
}
