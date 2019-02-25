package com.evani.nytimestestapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.evani.nytimestestapp.R;
import com.evani.nytimestestapp.data.model.Article;
import com.evani.nytimestestapp.databinding.ActivityDetailBinding;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        Article article = (Article) getIntent().getSerializableExtra("SELECTED_NEWS");
        //Bindind selected article to the view
        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        binding.setNews(article);
    }
}
