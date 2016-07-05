package com.example.alice.facebookrecipes.recipelist.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.example.alice.facebookrecipes.App;
import com.example.alice.facebookrecipes.R;
import com.example.alice.facebookrecipes.db.entities.Recipe;
import com.example.alice.facebookrecipes.libs.base.GlideImageLoader;
import com.example.alice.facebookrecipes.libs.base.ImageLoader;
import com.example.alice.facebookrecipes.recipelist.RecipeListPresenter;
import com.example.alice.facebookrecipes.recipelist.di.RecipeListComponent;
import com.example.alice.facebookrecipes.recipelist.events.RecipeListEvent;
import com.example.alice.facebookrecipes.recipelist.ui.adapters.OnItemClickListener;
import com.example.alice.facebookrecipes.recipelist.ui.adapters.RecipesAdapter;
import com.example.alice.facebookrecipes.recipemain.ui.RecipeMainActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeListActivity extends AppCompatActivity implements RecipeListView, OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecipesAdapter adapter;
    private RecipeListPresenter presenter;
    private RecipeListComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);
        setupToolbar();
        setupInjection();
        setupRecyclerView();
        presenter.onCreate();
        presenter.getRecipes();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

//    ==============================TOOBAR======================================================

    @OnClick(R.id.toolbar)
    public void onToolbarClick() {
        recyclerView.smoothScrollToPosition(0);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    //    =================================MENU====&& ============ACTIONS========================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipes_list, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_main) {
            navigateToMainScreen();
        }else if (id == R.id.action_all) {
              presenter.showAll();
        }else if (id == R.id.action_favs) {
              presenter.showFavs();
        }
        else if (id == R.id.action_logout) {
            logout();
        }

        return super.onOptionsItemSelected(item);
    }


    private void logout() {
        App app = (App) getApplication();
        app.logout();
    }

    private void navigateToMainScreen() {
        startActivity(new Intent(this, RecipeMainActivity.class));
    }

//    =============================================================================================

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }

    //    =============================Injectiosn===============================================
    private void setupInjection() {

        App app = (App) getApplication();
        component = app.getRecipeListComponent(this,this,this);
        adapter = getAdapter();
        presenter = getPresenter();


//        fakeAdapterTest();

    }

    private void fakeAdapterTest() {
        ImageLoader loader = new GlideImageLoader(Glide.with(this));
        Recipe recipe = new Recipe();
        recipe.setFavorite(false);
        recipe.setTitle("Prueba");
        recipe.setSourceURL("http://static.food2fork.com/icedcoffee5766.jpg");
        recipe.setImageURL("http://static.food2fork.com/icedcoffee5766.jpg");
        adapter = new RecipesAdapter(this, loader , Arrays.asList(recipe));

        presenter = new RecipeListPresenter() {
            @Override
            public void onCreate() {

            }

            @Override
            public void onDestroy() {

            }

            @Override
            public void getRecipes() {

            }

            @Override
            public void removeRecipe(Recipe recipe) {

            }

            @Override
            public void toogleFavorite(Recipe recipe) {

            }

            @Override
            public void onEvnetMainThread(RecipeListEvent event) {

            }

            @Override
            public RecipeListView getView() {
                return null;
            }

            @Override
            public void showAll() {

            }

            @Override
            public void showFavs() {

            }
        };
    }


    public RecipeListPresenter getPresenter() {
        return component.getPresenter();
    }

    public RecipesAdapter getAdapter() {
        return component.getAdapter();
    }

    //    =========================================RecipeListView =====================================
    @Override
    public void setRecipes(List<Recipe> data) {
        Log.i("storage", "read" + data.size());
        adapter.setRecipes(data);
    }

    @Override
    public void recipeUpdated() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void recipeDeleted(Recipe recipe) {
        adapter.removeRecipe(recipe);
    }

    //===============================OnItemClickListener ==========================================
    @Override
    public void onFavClick(Recipe recipe) {
        presenter.toogleFavorite(recipe);
    }

    @Override
    public void onItemClick(Recipe recipe) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getSourceURL()));
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Recipe recipe) {
        presenter.removeRecipe(recipe);
    }


}
