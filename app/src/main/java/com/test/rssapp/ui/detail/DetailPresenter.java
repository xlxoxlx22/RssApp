package com.test.rssapp.ui.detail;

import android.os.Bundle;
import android.text.TextUtils;

import com.test.rssapp.network.model.Article;
import com.test.rssapp.rssapp.R;
import com.test.rssapp.ui.base.BasePresenter;

import org.parceler.Parcels;


public class DetailPresenter<T extends DetailView> implements BasePresenter<T> {


    protected T mView;
    private Article mCurrentArticle;


    @SuppressWarnings("unchecked")
    public DetailPresenter(T baseView, Bundle arguments){
        mView = baseView;
        mCurrentArticle = Parcels.unwrap(arguments.getParcelable("article"));
    }

    @Override
    public void subscribeOnView(T baseView) {
        mView = baseView;
    }

    @Override
    public void unsubscribeView() {
        mView = null;
    }


    public String getDetailTitle(){
        if (mCurrentArticle != null && !TextUtils.isEmpty(mCurrentArticle.getTitle()))
            return mCurrentArticle.getTitle();
        else return "";
    }

    public String getDetailAuthor(){
        if (mCurrentArticle != null && !TextUtils.isEmpty(mCurrentArticle.getLink()))
            return mCurrentArticle.getLink();
        else return "";
    }

    public String getDetailPublishDate(){
        if (mCurrentArticle != null && !TextUtils.isEmpty(mCurrentArticle.getPublicationDate()))
            return mCurrentArticle.getPublicationDate();
        else return "";
    }

    public String getDetailContent(){
        if (mCurrentArticle != null && !TextUtils.isEmpty(mCurrentArticle.getDescription()))
            return mCurrentArticle.getDescription();
        else return "";
    }

    public String getDetaiImageUrl(){
        if (mCurrentArticle != null && !TextUtils.isEmpty(mCurrentArticle.getEnclosure().getImageUrl()))
            return mCurrentArticle.getEnclosure().getImageUrl();
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
