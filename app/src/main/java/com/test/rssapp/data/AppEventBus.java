package com.test.rssapp.data;

import io.reactivex.processors.PublishProcessor;


public class AppEventBus {

    private PublishProcessor<AppEvent> mAppEventPublishSubject;

    public AppEventBus() {
        mAppEventPublishSubject = PublishProcessor.create();
    }

    public void onNext(AppEvent appEvent) {
        mAppEventPublishSubject.onNext(appEvent);
    }

    public PublishProcessor<AppEvent> getAppEventBus() {
        return mAppEventPublishSubject;
    }

}
