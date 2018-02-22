package com.test.rssapp.ui.detail;


import android.annotation.TargetApi;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionSet;

@TargetApi(21)
public class DetailsTransition extends TransitionSet {
    private static final long FADE_DEFAULT_TIME = 300;
    private static final long MOVE_DEFAULT_TIME = 1000;

    public DetailsTransition() {
        setOrdering(ORDERING_SEQUENTIAL);
        setDuration(MOVE_DEFAULT_TIME);
        setStartDelay(FADE_DEFAULT_TIME);
        addTransition(new Fade(Fade.OUT))
                .addTransition(new ChangeBounds())
                .addTransition(new Fade(Fade.IN));
    }
}
