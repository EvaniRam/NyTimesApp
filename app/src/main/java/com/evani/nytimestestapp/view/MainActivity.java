package com.evani.nytimestestapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.evani.nytimestestapp.NYTimesApplication;
import com.evani.nytimestestapp.R;
import com.evani.nytimestestapp.Util.Constants;
import com.evani.nytimestestapp.Util.Utils;
import com.evani.nytimestestapp.data.model.Article;
import com.evani.nytimestestapp.data.model.ArticleResponse;
import com.evani.nytimestestapp.network.ApiClient;
import com.evani.nytimestestapp.network.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView noDataTv;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Type listType;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        if(Utils.isOnline(this)) {
            //Network Connected ; call api
            callApi("all-sections", "7");
        }else {
            //No Network Scenario
            Toast.makeText(MainActivity.this, "No network connection. Loaded Offline data", Toast.LENGTH_LONG).show();
            getSavedData();
        }

    }

    private void init() {
        listType = new TypeToken<List<Article>>() {
        }.getType();
        gson = new Gson();

        progressBar =  findViewById(R.id.progressBar);
        noDataTv =  findViewById(R.id.noNewsTV);
        recyclerView =  findViewById(R.id.recycler_news);
    }

    private void callApi(String section , String period) {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<ArticleResponse> call = service.getArticleDetails(section,period, Constants.API_KEY);
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                int statusCode =  response.code();

                if(statusCode == 200 || response.isSuccessful() ) {
                    List<Article> articles = response.body().getArticles();
                    saveData(articles);
                }else {
                    displayError("Something went wrong.Try again.");
                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                displayError(t.getMessage());
            }
        });
    }

    private void saveData(List<Article> articles) {
        String articleGsonStr = gson.toJson(articles, listType);
        NYTimesApplication.setArticleList(this,articleGsonStr);
        getSavedData();
    }

    private void getSavedData() {
        //GET SAVED DATA
        String gsonList = NYTimesApplication.getArticleList(this);

        if (!gsonList.equals("n/a")) {
            //CONVERT TO LIST
            List<Article> newsList = gson.fromJson(gsonList, listType);
            setupRecycler(newsList);
        } else {
            displayError("No saved news to display...!");
        }
    }

    private void setupRecycler(List<Article> dataList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new ArticleAdapter(this, dataList));

        recyclerView.hasFixedSize();
        assert dataList != null;
        if (dataList.size() > 0) {
            dataVisible();
        } else {
            displayError("No News..!");
        }
    }

    private void displayError(String message) {
        noDataTv.setText(message);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        noDataTv.setVisibility(View.VISIBLE);
    }

    private void dataVisible() {
        noDataTv.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
