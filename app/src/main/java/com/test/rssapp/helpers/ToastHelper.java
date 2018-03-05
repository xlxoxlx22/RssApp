package com.test.rssapp.helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.test.rssapp.di.scope.ApplicationScope;

import java.text.DecimalFormat;

import javax.inject.Inject;


@ApplicationScope
public class ToastHelper {
    private final Context mContext;

    @Inject
    public ToastHelper(@ApplicationScope @NonNull Context context){
        this.mContext = context;
    }

    private void make(boolean isLong, String text) {
        Toast.makeText(mContext, text, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }


    public void showToastMessageFromResource(int resId) {
        make(true, mContext.getResources().getString(resId));
    }

    public void showToastMessage(String text) {
        make(true, text);
    }
}
