package com.test.rssapp.ui.feed;

import com.test.rssapp.network.model.response.Article;
import com.test.rssapp.ui.base.BaseView;

import java.util.List;

public interface FeedView extends BaseView {

    void openFeedDetails();
    void updateAdapter(List<Article> articles);


}
