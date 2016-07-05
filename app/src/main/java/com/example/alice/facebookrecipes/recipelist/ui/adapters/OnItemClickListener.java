package com.example.alice.facebookrecipes.recipelist.ui.adapters;

import com.example.alice.facebookrecipes.db.entities.Recipe;

/**
 * Created by alice on 7/4/16.
 * Management Clinck on stored recipes
 */

public interface OnItemClickListener {
    void  onFavClick(Recipe recipe);
    void  onItemClick(Recipe recipe);
    void  onDeleteClick(Recipe recipe);
}
