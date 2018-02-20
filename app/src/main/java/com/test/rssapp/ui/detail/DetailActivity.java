package com.test.rssapp.ui.detail;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.rssapp.network.model.Article;
import com.test.rssapp.rssapp.R;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {


    public static void buildIntent(Activity activity, Bundle extraParams) {
        if (activity != null) {
            Intent intent = new Intent(activity, DetailActivity.class);
            intent.putExtras(extraParams);
            activity.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle arguments = intent.getExtras();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, DetailFragment.getInstance(arguments))
                    .commit();
        }
    }
}
