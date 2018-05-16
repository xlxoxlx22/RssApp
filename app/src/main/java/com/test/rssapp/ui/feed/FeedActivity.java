package com.test.rssapp.ui.feed;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.test.rssapp.data.AppEvent;
import com.test.rssapp.data.AppEventBus;
import com.test.rssapp.helpers.ToastHelper;
import com.test.rssapp.network.model.response.Article;
import com.test.rssapp.rssapp.R;
import com.test.rssapp.ui.base.App;
import com.test.rssapp.ui.detail.DetailActivity;
import com.test.rssapp.ui.feed.adapters.FeedAdapter;
import com.test.rssapp.ui.feed.module.FeedModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public class FeedActivity extends AppCompatActivity implements FeedView {

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.feed_list_recycler_view) RecyclerView mFeedRecyclerView;
    @BindView(R.id.feed_swipe_update_view) SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject AppEventBus mAppEventBus;
    @Inject ToastHelper mToastHelper;
    @Inject FeedPresenter mFeedPresenter;

    FeedAdapter mFeedAdapter;
    Disposable mDisposable = null;
    Disposable mAppEventDisposable = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);                                                 // инициализируем view
        App.get(this).getComponent().plus(new FeedModule()).inject(this);   // объявляем модуль

        if (mFeedPresenter != null) mFeedPresenter.subscribeOnView(this);    // привязываем presenter

        mAppEventDisposable = mAppEventBus.getAppEventBus()
                .filter(appEvent -> appEvent == AppEvent.DATA_RECEIVED)
                .subscribe(appEvent -> mToastHelper.showToastMessageFromResource(R.string.feed_view_is_ready));

        setupViews();
        setupRowClickListener();
        mFeedPresenter.tryLoadFeedFromCache();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAppEventDisposable!= null && !mAppEventDisposable.isDisposed()) {
            mAppEventDisposable.dispose();
        }

        if (mDisposable!= null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        mFeedPresenter.unsubscribe();
    }


    private void setupViews() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mFeedRecyclerView.setLayoutManager(layoutManager);
        mFeedRecyclerView.setHasFixedSize(true);

        mSwipeRefreshLayout.setOnRefreshListener(() -> mFeedPresenter.updateRssData());
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void setupRowClickListener(){
        mFeedAdapter = new FeedAdapter(this, new ArrayList<>());
        mFeedRecyclerView.setAdapter(mFeedAdapter);
        mDisposable = mFeedAdapter.getItemClickSubject()
                .subscribe(article -> mFeedPresenter.showArticleDetails(article));
    }


    @Override
    public void updateAdapter(List<Article> articles) {
        mSwipeRefreshLayout.setRefreshing(false);
        if (articles != null) {
            mFeedAdapter.updateAdapterList(articles);
        }
    }


    @Override
    public void openFeedDetails() {
        // открыть окно с деталями новости
        DetailActivity.buildIntent(this, mFeedPresenter.getExtraParamsBundle());
    }

    @Override
    public void showToastMessage(final int message) {
        runOnUiThread(() -> mToastHelper.showToastMessageFromResource(message));
    }

    @Override
    public void showLoadingProgress() {
        runOnUiThread(() -> mProgressBar.setVisibility(View.VISIBLE));
    }

    @Override
    public void hideLoadingProgress() {
        runOnUiThread(() -> mProgressBar.setVisibility(View.GONE));
    }
}
