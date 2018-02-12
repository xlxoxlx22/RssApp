package com.test.rssapp.ui.feed.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.rssapp.network.model.Article;
import com.test.rssapp.rssapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by admin on 12.02.18.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder>  {

    private Context mContext;
    private final List<Article> mAndroidList;
    private PublishSubject<Article> mItemClickSubject = PublishSubject.create();

    public PublishSubject<Article> getItemClickSubject() {
        return mItemClickSubject;
    }

    public FeedAdapter(Context context, List<Article> androidList) {
        this.mAndroidList = androidList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mAndroidList.get(position));
    }

    @Override
    public int getItemCount() {
        return mAndroidList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.list_item_feed_title) TextView mTitle;
        @BindView(R.id.list_item_feed_image) ImageView mImage;
        @BindView(R.id.list_item_feed_description) TextView mDescription;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        public void bind(final Article item) {

            mTitle.setText(item.getTitle());
            mDescription.setText(item.getDescription());
            Picasso.with(mContext).load(item.getEnclosure().getLink()).fit().centerCrop().placeholder(R.mipmap.ic_launcher).into(mImage);

            itemView.setOnClickListener(v -> mItemClickSubject.onNext(item));

        }
    }
}
