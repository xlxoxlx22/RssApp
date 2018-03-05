package com.test.rssapp.ui.feed;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.test.rssapp.data.AppEvent;
import com.test.rssapp.data.AppEventBus;
import com.test.rssapp.data.AppPreferences;
import com.test.rssapp.network.ApiService;
import com.test.rssapp.network.model.request.RequestObject;
import com.test.rssapp.network.model.response.Article;
import com.test.rssapp.network.model.response.ResponseObject;
import com.test.rssapp.rssapp.R;
import com.test.rssapp.ui.base.BasePresenter;

import org.parceler.Parcels;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class FeedPresenter<T extends FeedView> implements BasePresenter<T> {

    protected T mView;
    private Context mContext;
    private ApiService mApiService;
    private AppEventBus mAppEventBus;
    private AppPreferences mAppPreferences;
    private Bundle mExtraParamsBundle = new Bundle();


    public FeedPresenter(@NonNull Context context,
                         @NonNull AppEventBus appEventBus,
                         @NonNull ApiService apiService,
                         @NonNull AppPreferences appPreferences) {
        this.mContext = context;
        this.mApiService = apiService;
        this.mAppEventBus = appEventBus;
        this.mAppPreferences = appPreferences;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void subscribeOnView(T baseView) {
        mView = baseView;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    public void tryLoadFeedFromCache(){
        List<Article> list = mAppPreferences.getArticlesList();
        if (list != null && list.size() != 0){
            mView.updateAdapter(list);
        } else {
            updateRssData();
        }
    }

    public void updateRssData(){
        mApiService.getRssList(getNewsSource())
                .doOnNext(responseObject -> mView.showLoadingProgress())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseObject -> {
                            if (checkIfHasResponse(responseObject)) {
                                mAppEventBus.onNext(AppEvent.DATA_RECEIVED);
                                mAppPreferences.saveArticles(responseObject);
                                mView.updateAdapter(responseObject.getArticles());
                            } else {
                                mView.showToastMessage(R.string.load_data_error);
                            }
                        },
                        throwable -> mView.showToastMessage(R.string.load_data_error),
                        () -> mView.hideLoadingProgress());
    }



    public void showArticleDetails(Article article) {
        mExtraParamsBundle.putParcelable("article", Parcels.wrap(article));
        mView.openFeedDetails();
    }

    public Bundle getExtraParamsBundle(){
        if (mExtraParamsBundle != null) {
            return mExtraParamsBundle;
        } else {
            return new Bundle();
        }
    }


    private boolean checkIfHasResponse(@NonNull ResponseObject response){
        return response.getStatus();
    }


    @NonNull
    private String getNewsSource(){
        return "google-news-ru";
    }

//    {"status":"error","code":"apiKeyMissing","message":"Your API key is missing. Append this to the URL with the apiKey param, or use the x-api-key HTTP header."}

}
