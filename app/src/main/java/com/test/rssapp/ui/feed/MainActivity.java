package com.test.rssapp.ui.feed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.test.rssapp.helpers.ToastHelper;
import com.test.rssapp.network.model.Article;
import com.test.rssapp.rssapp.R;
import com.test.rssapp.ui.detail.DetailActivity;
import com.test.rssapp.ui.feed.adapters.FeedAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements FeedView {

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.feed_list_recycler_view) RecyclerView mFeedRecyclerView;

    FeedAdapter mFeedAdapter;
    FeedPresenter mFeedPresenter;
    Disposable mDisposable = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ButterKnife.bind(this);
        mFeedPresenter = new FeedPresenter(this);

        setupRecyclerView();
        setupRowClickListener();
        mFeedPresenter.loadRssData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable!= null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        mFeedPresenter.unsubscribeView();
    }


    private void setupRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mFeedRecyclerView.setLayoutManager(layoutManager);
        mFeedRecyclerView.setHasFixedSize(true);

        mDisposable = mFeedAdapter.getItemClickSubject()
                .subscribe(article -> openFeedDetails());
    }

    private void setupRowClickListener(){
        mDisposable = mFeedAdapter.getItemClickSubject()
                .subscribe(article -> mFeedPresenter.showArticleDetails(article));
    }


    @Override
    public void updateAdapter(List<Article> articles) {
        if (articles != null && articles.size() > 0) {
            mFeedAdapter = new FeedAdapter(this, articles);
            mFeedRecyclerView.setAdapter(mFeedAdapter);
            mFeedAdapter.notifyDataSetChanged();
        }
    }



    @Override
    public void openFeedDetails() {
        // открыть окно с деталями новости
        DetailActivity.buildIntent(this, mFeedPresenter.getExtraParamsBundle());
    }

    @Override
    public void showToastMessage(final String message) {
        runOnUiThread(() -> ToastHelper.showToastMessage(this, message));
    }

    @Override
    public void showLoadingProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
