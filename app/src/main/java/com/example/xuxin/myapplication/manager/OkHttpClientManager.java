package com.example.xuxin.myapplication.manager;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;

/**
 * Created by xuxin on 16-11-24.
 */

public class OkHttpClientManager {
    private static OkHttpClientManager mOkHttpClientManager;
    private Handler mDelivery;
    private OkHttpClient mOkHttpClient;
    private Gson mGson;

    private static final String TAG = "OkHttpClientManager";

    private OkHttpClientManager() {
        mDelivery = new Handler(Looper.getMainLooper());
        mOkHttpClient = new OkHttpClient();
        mGson = new Gson();
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
    }
    //单例模式
    public static OkHttpClientManager getInstance() {
        if (mOkHttpClientManager == null) {
            synchronized (OkHttpClientManager.class) {
                if (mOkHttpClientManager == null) {
                    mOkHttpClientManager = new OkHttpClientManager();
                }
            }
        }
        return mOkHttpClientManager;
    }

    private Response _getAsyn(String url) throws IOException {
        final Request request = new Request.Builder()
                                    .url(url)
                                    .build();

        Call call = mOkHttpClient.newCall(request);
        Response execute = call.execute();
        return execute;
    }

    private String _getAsynStrinig(String url) throws IOException {
        Response execute = _getAsyn(url);
        return execute.body().string();

    }

    private void _getAsyn(String url, ResultCallback callback) {
        final Request request = new Request.Builder()
                                    .url(url)
                                    .build();
        deliveryResult(request, callback);
    }

    private void deliveryResult(Request request, final ResultCallback callback) {
        mOkHttpClient.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(final Request request, final IOException e)
            {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(final Response response)
            {
                try
                {
                    final String string = response.body().string();
                    if (callback.mType == String.class)
                    {
                        sendSuccessResultCallback(string, callback);
                    } else
                    {
                        Object o = mGson.fromJson(string, callback.mType);
                        sendSuccessResultCallback(o, callback);
                    }


                } catch (IOException e)
                {
                    sendFailedStringCallback(response.request(), e, callback);
                } catch (com.google.gson.JsonParseException e)//Json解析的错误
                {
                    sendFailedStringCallback(response.request(), e, callback);
                }

            }
        });
    }

    private void sendSuccessResultCallback(final Object object, final ResultCallback callback) {
        mDelivery.post(new Runnable()
        {
            @Override
            public void run()
            {
                if (callback != null)
                {
                    callback.onResponse(object);
                }
            }
        });
    }

    private void sendFailedStringCallback(final Request request, final Exception e, final ResultCallback callback) {
        mDelivery.post(new Runnable()
        {
            @Override
            public void run()
            {
                if (callback != null)
                    callback.onError(request, e);
            }
        });

    }

    /*
    *
    * ---------------------------
    *
    *
    * */
    public static Response getAsyn(String url) throws IOException {
        return getInstance()._getAsyn(url);
    }

    public static String getAsynString(String url) throws IOException {
        return getInstance()._getAsynStrinig(url);
    }

    public static void getAsyn(String url, final ResultCallback<?> callback) {
        getInstance()._getAsyn(url, callback);
    }

    public static abstract class ResultCallback<T>{
        Type mType;
        public ResultCallback() {
            mType = getSuperClassTypeParameter(getClass());
        }

        static Type getSuperClassTypeParameter(Class<?> subclass){
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class)
            {
                throw new RuntimeException("Missing type parameter.");

            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }
        public abstract void onError(Request request, Exception e);

        public abstract void onResponse(T response);
    }
}
