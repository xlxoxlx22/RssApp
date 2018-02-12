package com.test.rssapp.ui.feed;

import android.os.Bundle;

import com.test.rssapp.network.ApiService;
import com.test.rssapp.network.RetrofitClient;
import com.test.rssapp.network.model.Article;
import com.test.rssapp.ui.base.BasePresenter;

import org.parceler.Parcels;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class FeedPresenter<T extends FeedView> implements BasePresenter<T> {

    protected T mView;
    private Bundle mExtraParamsBundle = new Bundle();


    @SuppressWarnings("unchecked")
    public FeedPresenter(T baseView){
        subscribeOnView(baseView);
    }


    @Override
    public void subscribeOnView(T baseView) {
        this.mView = baseView;
    }

    @Override
    public void unsubscribeView() {
        mView = null;
    }

    public void loadRssData(){
        String rssUrl = "http://rss.cnn.com/rss/edition.rss";
        ApiService apiService =  RetrofitClient.getInstance().getApi();
        apiService.getRssList(rssUrl)
                .doOnNext(requestResponse -> mView.showLoadingProgress())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(requestResponse -> {
                    if (requestResponse != null && requestResponse.getStatus()) {

                        mView.hideLoadingProgress();
                        mView.updateAdapter(requestResponse.getArticles());
                    } else {
                        mView.showToastMessage("Can't receive data");
                    }
                }, throwable -> {
                    mView.hideLoadingProgress();
                    mView.showToastMessage(throwable.getLocalizedMessage());
                });
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


}
