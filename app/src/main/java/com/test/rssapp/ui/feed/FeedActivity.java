package com.test.rssapp.ui.feed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.test.rssapp.rssapp.R;
import com.test.rssapp.ui.detail.DetailFragment;

public class FeedActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, FeedFragment.newInstance())
                .commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
