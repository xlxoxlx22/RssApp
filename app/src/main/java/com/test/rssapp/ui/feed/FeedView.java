package com.test.rssapp.ui.feed;

import android.view.View;
import android.widget.ImageView;

import com.test.rssapp.network.model.Article;
import com.test.rssapp.ui.base.BaseView;

import java.util.List;

public interface FeedView extends BaseView {

    void openFeedDetails();
    void openFeedDetailsWithViewTransition(ImageView view);
    void updateAdapter(List<Article> articles);


}
