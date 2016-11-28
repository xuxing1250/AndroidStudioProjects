package com.example.xuxin.myapplication.Utils;

import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by xuxin on 16-11-24.
 */

public class OkHttpGet {
    private static OkHttpClient mOkHttpClient;


    public static void getOkHttpResponse() {

        mOkHttpClient = new OkHttpClient();

        final  Request request = new Request.Builder()
                                    .url("http://v.juhe.cn/toutiao/index?type=top&key=fb8020bbe3361c39650cdcc43a20c3dd")
                                    .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("MainActivity_xuxin", "faild");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d("MainActivity_xuxin", "success");

                String s = response.body().string();
                Log.d("MainActivity_xuxin", "success-----------"+s);

            }
        });
    }

}
