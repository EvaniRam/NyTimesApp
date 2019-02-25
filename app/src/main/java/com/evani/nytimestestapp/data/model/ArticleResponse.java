package com.evani.nytimestestapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleResponse {

    public ArticleResponse(String status, int num_results, List<Article> articles) {
        this.status = status;
        this.num_results = num_results;
        this.articles = articles;
    }

    @SerializedName("status")
    private final String status;

    @SerializedName("num_results")
    private final int num_results;

    @SerializedName("results")
    private final List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }
}
