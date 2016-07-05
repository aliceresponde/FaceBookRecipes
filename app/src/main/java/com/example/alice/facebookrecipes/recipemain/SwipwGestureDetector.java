package com.example.alice.facebookrecipes.recipemain;

import android.gesture.Gesture;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by alice on 7/4/16.
 */

public class SwipwGestureDetector extends GestureDetector.SimpleOnGestureListener {
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    private SwipeGestureListener listener;

    public SwipwGestureDetector(SwipeGestureListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float diffY = e2.getY() -e1.getY();
        float diffX = e2.getX() -e1.getX();

        if (    Math.abs(diffX) > Math.abs(diffY)  &&
                Math.abs(diffX) > SWIPE_THRESHOLD  &&
                Math.abs(diffX) > SWIPE_VELOCITY_THRESHOLD){
            if (diffX > 0){
                listener.onKeep();
            }else {
                listener.onDismiss();
            }
        }

        return super.onFling(e1, e2, velocityX, velocityY);
    }
}
