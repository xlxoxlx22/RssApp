package com.test.rssapp.ui.feed;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.test.rssapp.helpers.AppPreferences;
import com.test.rssapp.network.ApiService;
import com.test.rssapp.network.RetrofitClient;
import com.test.rssapp.network.model.Article;
import com.test.rssapp.rssapp.R;
import com.test.rssapp.ui.base.App;
import com.test.rssapp.ui.base.BasePresenter;

import org.parceler.Parcels;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class FeedPresenter<T extends FeedView> implements BasePresenter<T> {

    protected T mView;
    private Bundle mExtraParamsBundle = new Bundle();


    @SuppressWarnings("unchecked")
    public FeedPresenter(T baseView){
        mView = baseView;
    }

    @Override
    public void unsubscribeView() {
        mView = null;
    }

    public void tryLoadFeedFromCache(){
        List<Article> list = AppPreferences.getInstance().getArticlesList();
        if (list != null && list.size() != 0){
            mView.updateAdapter(list);
        } else {
            updateRssData();
        }
    }

    public void updateRssData(){
        String rssUrl = "http://rss.cnn.com/rss/edition_sport.rss";
        ApiService apiService =  RetrofitClient.getInstance().getApi();
        apiService.getRssList(rssUrl)
                .doOnNext(requestResponse -> {
                    mView.showLoadingProgress();
                    AppPreferences.getInstance().saveArticles(requestResponse);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> mView.hideLoadingProgress())
                .subscribe(requestResponse -> {
                    if (requestResponse != null && requestResponse.getStatus()) {
                        mView.updateAdapter(requestResponse.getArticles());
                    } else {
                        mView.showToastMessage(R.string.load_data_error);
                    }

                }, throwable -> mView.showToastMessage(R.string.load_data_error));
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
