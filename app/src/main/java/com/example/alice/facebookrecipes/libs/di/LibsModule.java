package com.example.alice.facebookrecipes.libs.di;




import android.app.Activity;
import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.alice.facebookrecipes.libs.GreenRobotsEventBus;
import com.example.alice.facebookrecipes.libs.base.EventBus;
import com.example.alice.facebookrecipes.libs.base.GlideImageLoader;
import com.example.alice.facebookrecipes.libs.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by alice on 6/23/16.
 * Provee la dependencia de las librerias
 */

@Module
public class LibsModule {
    private Activity activity;


    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Crea un new GreenRobotsEventBus a partir del mio
     * @param eventBus
     * @return
     */
    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus ){
        return new GreenRobotsEventBus(eventBus);
    }

    /**
     * Retorna unstancia del GreenRobotEventBus
     * @return
     */
    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }


    @Provides
    @Singleton
    RequestManager providesRequestManager(Activity activity){
        return Glide.with(activity);
    }

    @Provides
    @Singleton
    Activity providesFragment(){
        return  this.activity;
    }




}
