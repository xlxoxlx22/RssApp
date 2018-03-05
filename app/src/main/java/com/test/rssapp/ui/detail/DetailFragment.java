package com.test.rssapp.ui.detail;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.rssapp.helpers.ToastHelper;
import com.test.rssapp.rssapp.R;
import com.test.rssapp.ui.base.App;
import com.test.rssapp.ui.detail.module.DetailModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailFragment extends Fragment implements DetailView {

    private Unbinder mFragmentUnbinder;

    @Inject ToastHelper mToastHelper;
    @Inject DetailPresenter mDetailPresenter;

    @BindView(R.id.feed_detail_link) TextView mDetailLink;
    @BindView(R.id.feed_detail_title) TextView mDetailTitle;
    @BindView(R.id.feed_detail_image) ImageView mDetailImage;
    @BindView(R.id.feed_detail_content) TextView mDetailContent;
    @BindView(R.id.feed_detail_author) TextView mDetailPublishDate;

    public DetailFragment() {}

    public static DetailFragment getInstance(Bundle params) {
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(params);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.get(getActivity()).getComponent().plus(new DetailModule()).inject(this);

        mDetailPresenter.subscribeOnView(this);
        mDetailPresenter.setArticle(getArguments());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_detail, container, false);
        mFragmentUnbinder = ButterKnife.bind(this, view);
        setupViews();
        return view;
    }


    private void setupViews(){
        mDetailTitle.setText(mDetailPresenter.getDetailTitle());
        mDetailContent.setText(mDetailPresenter.getDetailContent());
        mDetailPublishDate.setText(mDetailPresenter.getDetailPublishDate());

        mDetailLink.setText(mDetailPresenter.getDetailAuthor());
        mDetailLink.setOnClickListener(v -> mDetailPresenter.onLinkClick());

        Picasso.with(getActivity())
                .load(mDetailPresenter.getDetaiImageUrl())
                .fit()
                .centerCrop()
                .into(mDetailImage);
    }


    @Override
    public void openLink(String url) {
        Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browser);
    }


    @Override
    public void showToastMessage(final int message) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(() -> mToastHelper.showToastMessageFromResource(message));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDetailPresenter.unsubscribe();
        mFragmentUnbinder.unbind();
    }

    @Override
    public void showLoadingProgress() {

    }

    @Override
    public void hideLoadingProgress() {

    }
}
