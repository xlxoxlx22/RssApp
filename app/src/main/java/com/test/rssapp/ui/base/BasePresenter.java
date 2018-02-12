package com.test.rssapp.ui.base;

/**
 * Created by admin on 12.02.18.
 */

public interface BasePresenter<T extends BaseView> {

    void subscribeOnView(T baseView);
    void unsubscribeView();

}
