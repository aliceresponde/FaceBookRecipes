package com.example.alice.facebookrecipes.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by alice on 7/3/16.
 * Class to config  DB
 */


@Database(name = RecipiesDataBase.NAME, version =  RecipiesDataBase.VERSION)
public class RecipiesDataBase {
    public static final int VERSION = 1;
    public static final String NAME = "Recipies";
}
