package com.evani.nytimestestapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class NYTimesApplication extends Application {

    private static final String ARTICLE_LIST = "article_list";

    public static void setArticleList(Activity activity , String articleGson) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(ARTICLE_LIST, articleGson);
        editor.apply();
    }

    public static String getArticleList(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(ARTICLE_LIST, "n/a");
    }
}
