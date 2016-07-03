package com.example.alice.facebookrecipes.db.entities;

import com.example.alice.facebookrecipes.db.RecipiesDataBase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.sql.DatabaseMetaData;

/**
 * Created by alice on 7/3/16.
 * Describo la netidad
 */

@Table(database = RecipiesDataBase.class)
public class Recipe extends BaseModel {
    @SerializedName("recipe_id")
    @PrimaryKey private String  recipeID;

    @Column private String title;

    @SerializedName("image_url")
    @Column private String imageURL;

    @SerializedName("source_url")
    @Column private String sourceURL;

    @Column private boolean favorite;

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    /**
     * Verify that exist, and compare to
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        boolean equal = false;
        if ( obj instanceof Recipe){
            Recipe recipe = (Recipe) obj;
            equal = this.getRecipeID().equals(recipe.getRecipeID());
        }
        return equal;
    }
}
