package com.evani.nytimestestapp.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.evani.nytimestestapp.data.model.Article;
import com.evani.nytimestestapp.view.DetailsActivity;

public class NewsClickHandler {

    private final Context context;

    public NewsClickHandler(Context context) {
        this.context = context;
    }

    public void onSaveClick(View view, Article article) {
        Intent nextInt = new Intent(view.getContext(), DetailsActivity.class);
        nextInt.putExtra("SELECTED_NEWS",article);
        context.startActivity(nextInt);
    }
}