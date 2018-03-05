package com.test.rssapp.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by admin on 12.02.18.
 */

public interface BaseView {

    void showLoadingProgress();
    void hideLoadingProgress();

    void showToastMessage(@StringRes int message);

}
