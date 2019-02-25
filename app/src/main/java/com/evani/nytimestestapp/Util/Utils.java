package com.evani.nytimestestapp.Util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

public class Utils {

    public static boolean isOnline (Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
