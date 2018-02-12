package com.test.rssapp.helpers;

import android.content.Context;
import android.widget.Toast;


public class ToastHelper {

    private static void make(Context context, boolean isLong, String text) {
        Toast.makeText(context, text, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }


    public static void showToastMessageFromResource(Context context, int resId) {
        make(context, true, context.getResources().getString(resId));
    }

    public static void showToastMessage(Context context, String text) {
        make(context, true, text);
    }
}
