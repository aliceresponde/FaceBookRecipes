package com.example.alice.facebookrecipes.recipelist.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.alice.facebookrecipes.App;
import com.example.alice.facebookrecipes.R;
import com.example.alice.facebookrecipes.db.entities.Recipe;
import com.example.alice.facebookrecipes.recipelist.RecipeListPresenter;
import com.example.alice.facebookrecipes.recipelist.ui.adapters.OnItemClickListener;
import com.example.alice.facebookrecipes.recipelist.ui.adapters.RecipesAdapter;
import com.example.alice.facebookrecipes.recipemain.ui.RecipeMainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeListActivity extends AppCompatActivity implements RecipeListView , OnItemClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    RecipesAdapter adapter ;
    RecipeListPresenter presenter;

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
    public void onToolbarClick(){
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
        if (id == R.id.action_main){
            navigateToMainScreen();
        }else if (id == R.id.action_logout){
            logout();
        }

        return super.onOptionsItemSelected(item);
    }


    private void logout() {
        App app = (App) getApplication();
        app.logout();
    }

    private void navigateToMainScreen() {
        startActivity( new Intent( this, RecipeMainActivity.class));
    }

//    =============================================================================================

    private void setupRecyclerView() {
        recyclerView.setLayoutManager( new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

    }

    private void setupInjection() {
    }

//    =========================================RecipeListView =====================================
    @Override
    public void setRecipes(List<Recipe> data) {
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
        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse(recipe.getSourceURL()));
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Recipe recipe) {
        presenter.removeRecipe(recipe);
    }


}
