package com.example.xuxin.myapplication.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuxin.myapplication.Constact;
import com.example.xuxin.myapplication.R;
import com.example.xuxin.myapplication.adapter.NewsAdapter;
import com.example.xuxin.myapplication.beans.NewsBean;
import com.example.xuxin.myapplication.manager.OkHttpClientManager;
import com.example.xuxin.myapplication.manager.OkHttpClientManager.ResultCallback;
import com.example.xuxin.myapplication.widge.NewsListView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by xuxin on 16-11-23.
 */

public class NewsFragment extends BaseTabFragment {
    public static final String TAG = "NewsFragment";
    private String mUrl = "http://v.juhe.cn/toutiao/index?type=top&key=fb8020bbe3361c39650cdcc43a20c3dd";
    private NewsListView mListView;
    private ArrayList<NewsBean> mNewBeanList;
    private NewsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        mListView = (NewsListView) view.findViewById(R.id.news_list);

        try {
            getNewsList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mAdapter = new NewsAdapter(getActivity(), mNewBeanList);
        mListView.setAdapter(mAdapter);
        return view;
    }

    private void getNewsList() throws IOException {

        OkHttpClientManager.getAsyn(mUrl, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                String s;
                s = response;
                try {
                    praseJaonByGson(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void praseJaonByGson(String s) throws JSONException {
        mNewBeanList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(s);
        Log.d(TAG,s);
        String sb = jsonObject.getString("result");
        JSONObject js = new JSONObject(sb);
        Log.d(TAG,sb);

            JSONArray jsonArray = js.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                NewsBean newsBean = new NewsBean();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String title = jsonObject1.getString("title");
                Log.d(TAG,""+title);
                newsBean.setTitle(title);
                mNewBeanList.add(newsBean);
            }
    }

}
