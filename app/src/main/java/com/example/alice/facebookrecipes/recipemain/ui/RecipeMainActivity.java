package com.example.alice.facebookrecipes.recipemain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.alice.facebookrecipes.App;
import com.example.alice.facebookrecipes.R;
import com.example.alice.facebookrecipes.RecipeListActivity;
import com.example.alice.facebookrecipes.db.entities.Recipe;
import com.example.alice.facebookrecipes.libs.base.ImageLoader;
import com.example.alice.facebookrecipes.recipemain.RecipeMainPresenter;
import com.example.alice.facebookrecipes.recipemain.di.RecipeMainComponent;
import com.example.alice.facebookrecipes.recipemain.evnents.RecipeMainEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeMainActivity extends AppCompatActivity implements RecipeMainView{

    @BindView(R.id.imgRecipe)
    ImageView imgRecipe;
    @BindView(R.id.imgDismiss)
    ImageButton imgDismiss;
    @BindView(R.id.imgKeep)
    ImageButton imgKeep;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.layoutContainer)
    RelativeLayout layoutContainer;

    private Recipe currentRecipe;
    private ImageLoader imageLoader;
    private RecipeMainPresenter presenter;
    private RecipeMainComponent component; // permite hacer la injeccion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_main);
        ButterKnife.bind(this);

        setupInject();
        setupImageLoader();
        presenter.onCreate();
        presenter.getNextRecipe();
    }

    private void setupImageLoader() {
        RequestListener glideRequestListener = new RequestListener() {
            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                presenter.imageError(e.getLocalizedMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                presenter.imageReady();
                return false;
            }
        };

        imageLoader.setOnFinishedImageLoadingListener(glideRequestListener);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInject() {
        App app = (App) getApplication();
        component = app.getRecipeMainComponent(this,this);
        imageLoader = getImageLoader();
        presenter = getPresenter();
    }

//    ============================MENU======================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipes_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_list){
            navigateToListScreen();
        }else if (id == R.id.action_logout){
            logout();
        }

        return super.onOptionsItemSelected(item);
    }


    private void logout() {
        App app = (App) getApplication();
        app.logout();
    }

    private void navigateToListScreen() {
        startActivity( new Intent( this, RecipeListActivity.class));
    }


    //   ========================== click events ===============================================

    @OnClick(R.id.imgDismiss)
    public void onDismiss(){
        presenter.dismissRecipe();

    }

    @OnClick(R.id.imgKeep)
    public void onKeep(){
        if (currentRecipe != null){
            presenter.saveRecipe(currentRecipe);
        }
    }



//    ===================== RecipeMainView ================================================

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showUIElements() {
        imgKeep.setVisibility(View.VISIBLE);
        imgDismiss.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUIElements() {
        imgKeep.setVisibility(View.GONE);
        imgDismiss.setVisibility(View.GONE);
    }

    @Override
    public void saveAnimation() {

    }

    @Override
    public void dismissAnimation() {

    }

    @Override
    public void onRecipeSaved() {
        Snackbar.make(layoutContainer, R.string.recipemain_notice_saved, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setRecipe(Recipe recipe) {
        this.currentRecipe = recipe;
        imageLoader.load(imgRecipe, recipe.getImageURL());
    }

    @Override
    public void onGetRecipeError(String error) {
        String msgError  = String.format(getString(R.string.recipemain_error) ,error);
        Snackbar.make(layoutContainer, R.string.recipemain_error, Snackbar.LENGTH_SHORT).show();
    }

    public ImageLoader getImageLoader() {
        return component.getImageLoader();
    }

    public RecipeMainPresenter getPresenter() {
        return component.getPresenter();
    }
}
