package com.example.xuxin.myapplication.Utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by xuxin on 16-11-21.
 */

public class MyApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        mContext = getApplicationContext();
    }
    public static Context getContextObj() {
        return mContext;
    }
}
