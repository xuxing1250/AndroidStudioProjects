package com.example.xuxin.myapplication.activity;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by xuxin on 16-11-23.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getcontentViewId();

    protected abstract int getFragmentViewId();
}
