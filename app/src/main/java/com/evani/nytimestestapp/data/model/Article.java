package com.evani.nytimestestapp.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.evani.nytimestestapp.BR;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Article extends BaseObservable implements Serializable {

    @SerializedName("url")
    private String url;

    @SerializedName("title")
    @Bindable
    private String title;

    @SerializedName("abstract")
    private String subTitle;

    @SerializedName("published_date")
    @Bindable
    public String publishedDate;

    @SerializedName("byLine")
    @Bindable
    public String byLine;

   /* @SerializedName("media")
    private MediaList media;*/

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getByLine() {
        return byLine;
    }

    public Article(String byline,String title, String published_date) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.byLine = byLine;

        notifyPropertyChanged(BR.title);
        notifyPropertyChanged(BR.byLine);
        notifyPropertyChanged(BR.publishedDate);
    }




}
