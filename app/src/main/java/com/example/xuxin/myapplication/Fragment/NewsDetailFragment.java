package com.example.xuxin.myapplication.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.xuxin.myapplication.R;

/**
 * Created by xuxin on 16-11-29.
 */

public class NewsDetailFragment extends BaseNewsFragment {
    public static final String TAG = "NewsDetailFragment";
    private WebView mWebView;
    private String mWebUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebUrl = getArguments().getString("url");
        Log.d(TAG,mWebUrl);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vivew = inflater.inflate(R.layout.detail_news, container, false);
        mWebView = (WebView) vivew.findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(mWebUrl);
        return vivew;
    }
}
