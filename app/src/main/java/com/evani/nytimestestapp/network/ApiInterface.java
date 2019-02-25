package com.evani.nytimestestapp.network;

import com.evani.nytimestestapp.data.model.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("mostpopular/v2/mostviewed/{section}/{period}.json")
    Call<ArticleResponse> getArticleDetails(@Path("section") String section, @Path("period") String period,
                                     @Query("api-key") String apiKey);
}
