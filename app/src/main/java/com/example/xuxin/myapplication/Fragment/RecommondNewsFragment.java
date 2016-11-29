package com.example.xuxin.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.xuxin.myapplication.R;
import com.example.xuxin.myapplication.adapter.NewsAdapter;
import com.example.xuxin.myapplication.beans.NewsBean;
import com.example.xuxin.myapplication.manager.OkHttpClientManager;
import com.example.xuxin.myapplication.widge.ManyCircle;
import com.example.xuxin.myapplication.widge.NewsListView;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by xuxin on 16-11-29.
 */

public class RecommondNewsFragment extends BaseTabFragment implements AdapterView.OnItemClickListener{
    public static final String TAG = "RecommondFragment";
    private String mUrl = "http://v.juhe.cn/toutiao/index?type=top&key=fb8020bbe3361c39650cdcc43a20c3dd";
    private NewsListView mListView;
    private ArrayList<NewsBean> mNewBeanList;
    private NewsAdapter mAdapter;
    private TextView mLoading;
    private MyHandler mHandler;
    private ManyCircle mCircle;
    private String mWebUrl;



    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mAdapter = new NewsAdapter(getActivity(), mNewBeanList);
                    mListView.setAdapter(mAdapter);
                    setViewVisiable(mListView);
                    setViewInvisiable(mLoading);
                    setViewInvisiable(mCircle);
                    break;
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new MyHandler();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        mListView = (NewsListView) view.findViewById(R.id.news_list);
        mListView.setOnItemClickListener(this);
        mLoading = (TextView) view.findViewById(R.id.news_loading);
        mCircle = (ManyCircle) view.findViewById(R.id.image_loading);

        Log.d(TAG,"create");

        try {
            getNewsList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void getNewsList() throws IOException {

        OkHttpClientManager.getAsyn(mUrl, new OkHttpClientManager.ResultCallback<String>() {
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
        String sb = jsonObject.getString("result");
        JSONObject js = new JSONObject(sb);

        JSONArray jsonArray = js.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            NewsBean newsBean = new NewsBean();
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

            String title = jsonObject1.getString("title");
            String sourc_url = jsonObject1.getString("url");

            newsBean.setSource_url(sourc_url);
            newsBean.setTitle(title);
            mNewBeanList.add(newsBean);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mHandler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    void setViewVisiable(View v) {
        v.setVisibility(View.VISIBLE);
    }

    @Override
    void setViewInvisiable(View v) {
        v.setVisibility(View.GONE);
    }

    @Override
    protected void replaceFragment() {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url",mWebUrl);
        fragment.setArguments(bundle);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d(TAG,"click");
        NewsBean newsBean = mNewBeanList.get(i);
        mWebUrl = newsBean.getSource_url();
        replaceFragment();
    }
}
