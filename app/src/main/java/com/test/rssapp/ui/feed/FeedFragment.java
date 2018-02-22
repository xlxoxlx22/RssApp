package com.test.rssapp.ui.feed;


import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.test.rssapp.helpers.ToastHelper;
import com.test.rssapp.network.model.Article;
import com.test.rssapp.rssapp.R;
import com.test.rssapp.ui.detail.DetailFragment;
import com.test.rssapp.ui.detail.DetailsTransition;
import com.test.rssapp.ui.feed.adapters.FeedAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;


public class FeedFragment extends Fragment implements FeedView{

    private int imageCounter = 0;
    private FeedAdapter mFeedAdapter;
    private Unbinder mFragmentUnbinder;
    private FeedPresenter mFeedPresenter;
    private Disposable mDisposable = null;

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.feed_list_recycler_view) RecyclerView mFeedRecyclerView;
    @BindView(R.id.feed_swipe_update_view) SwipeRefreshLayout mSwipeRefreshLayout;

    public FeedFragment() {
    }


    // TODO: Rename and change types and number of parameters
    public static FeedFragment newInstance() {
        FeedFragment fragment = new FeedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mDisposable!= null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        mFeedPresenter.unsubscribeView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  =  inflater.inflate(R.layout.fragment_feed, container, false);
        mFragmentUnbinder = ButterKnife.bind(this, view);
        mFeedPresenter = new FeedPresenter();
        mFeedPresenter.subscribeOnView(this);

        setupViews();
        setupRowClickListener();
        mFeedPresenter.tryLoadFeedFromCache();
        return view;
    }

    private void setupViews() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mFeedRecyclerView.setLayoutManager(layoutManager);
        mFeedRecyclerView.setHasFixedSize(true);

        mSwipeRefreshLayout.setOnRefreshListener(() -> mFeedPresenter.updateRssData());
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void setupRowClickListener(){
        mFeedAdapter = new FeedAdapter(getActivity(), new ArrayList<Article>());
        mFeedRecyclerView.setAdapter(mFeedAdapter);
        mDisposable = mFeedAdapter.getItemClickSubject()
                .subscribe(articleImageViewPair -> mFeedPresenter.showArticleDetails(articleImageViewPair),
                        throwable -> Log.e(getClass().getSimpleName(), "error = " + throwable.getLocalizedMessage()));
//                .subscribe(article -> mFeedPresenter.showArticleDetails(article));
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
    }

    @Override
    public void openFeedDetailsWithViewTransition(ImageView view) {

        Bundle arguments = mFeedPresenter.getExtraParamsBundle();
        DetailFragment articleDetails = DetailFragment.getInstance(arguments);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            articleDetails.setSharedElementEnterTransition(new DetailsTransition());
            articleDetails.setEnterTransition(new Fade());
            articleDetails.setExitTransition(new Fade());
            articleDetails.setSharedElementReturnTransition(new DetailsTransition());
        }

        ViewCompat.setTransitionName(view, String.format("articleDetails_$d",imageCounter++));
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(view, "article_image")
                .replace(R.id.container, articleDetails)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void showToastMessage(final int message) {
        if (getActivity() != null)
            getActivity().runOnUiThread(() -> ToastHelper.showToastMessageFromResource(getActivity(), message));
    }

    @Override
    public void showLoadingProgress() {
        if (getActivity() != null)
            getActivity().runOnUiThread(() -> mProgressBar.setVisibility(View.VISIBLE));
    }

    @Override
    public void hideLoadingProgress() {
        if (getActivity() != null)
            getActivity().runOnUiThread(() -> mProgressBar.setVisibility(View.GONE));
    }

}
