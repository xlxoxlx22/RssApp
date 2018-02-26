package com.test.rssapp.ui.detail;


import android.annotation.TargetApi;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

@TargetApi(21)
public class DetailsTransition extends TransitionSet {
    private static final long FADE_DEFAULT_TIME = 300;
    private static final long MOVE_DEFAULT_TIME = 1000;

    public DetailsTransition() {
        setOrdering(ORDERING_TOGETHER);
        setDuration(MOVE_DEFAULT_TIME);
        setStartDelay(FADE_DEFAULT_TIME);
        addTransition(new ChangeBounds()).
                addTransition(new ChangeTransform()).
                addTransition(new ChangeImageTransform());
    }
}
