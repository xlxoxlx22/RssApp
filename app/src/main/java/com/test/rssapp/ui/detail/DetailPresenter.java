package com.test.rssapp.ui.detail;

import android.os.Bundle;
import android.text.TextUtils;

import com.test.rssapp.network.model.response.Article;
import com.test.rssapp.rssapp.R;
import com.test.rssapp.ui.base.BasePresenter;

import org.parceler.Parcels;


public class DetailPresenter<T extends DetailView> implements BasePresenter<T> {


    protected T mView;
    private Article mCurrentArticle;
    private static final String ARGUMENTS_ARTICLE_KEY = "article";

    public DetailPresenter(){}

    @SuppressWarnings("unchecked")
    @Override
    public void subscribeOnView(T baseView) {
        mView = baseView;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }


    public void setArticle(Bundle arguments){
        if (arguments != null && arguments.containsKey(ARGUMENTS_ARTICLE_KEY)) {
            mCurrentArticle = Parcels.unwrap(arguments.getParcelable(ARGUMENTS_ARTICLE_KEY));
        }
    }

    // title
    public String getDetailTitle(){
        if (mCurrentArticle != null && !TextUtils.isEmpty(mCurrentArticle.getTitle()))
            return mCurrentArticle.getTitle();
        else return "";
    }

    // author
    public String getDetailAuthor(){
        if (mCurrentArticle != null && !TextUtils.isEmpty(mCurrentArticle.getLink()))
            return mCurrentArticle.getLink();
        else return "";
    }

    // publishedAt
    public String getDetailPublishDate(){
        if (mCurrentArticle != null && !TextUtils.isEmpty(mCurrentArticle.getPublicationDate()))
            return mCurrentArticle.getPublicationDate();
        else return "";
    }

    // description
    public String getDetailContent(){
        if (mCurrentArticle != null && !TextUtils.isEmpty(mCurrentArticle.getDescription()))
            return mCurrentArticle.getDescription();
        else return "";
    }


    // urlToImage
    public String getDetaiImageUrl(){
        if (mCurrentArticle != null && !TextUtils.isEmpty(mCurrentArticle.getImage()))
            return mCurrentArticle.getImage();
        else return "";
    }


    public void onLinkClick(){
        if (mCurrentArticle != null && !TextUtils.isEmpty(mCurrentArticle.getLink())) {
            mView.openLink(mCurrentArticle.getLink());
        } else {
            mView.showToastMessage(R.string.link_is_empty);
        }
    }
}
