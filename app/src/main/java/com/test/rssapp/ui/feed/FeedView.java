package com.test.rssapp.ui.feed;

import com.test.rssapp.network.model.Article;
import com.test.rssapp.ui.base.BaseView;

import java.util.List;

/**
 * Created by admin on 12.02.18.
 */

public interface FeedView extends BaseView {

    void openFeedDetails();
    void showToastMessage(String message);
    void updateAdapter(List<Article> articles);


}
