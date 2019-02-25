package com.evani.nytimestestapp.data.model;

import com.google.gson.annotations.SerializedName;

class MediaMetaData {

    @SerializedName("url")
    private String url;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private String height;

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }
}
