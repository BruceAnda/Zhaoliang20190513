package com.bawei.zhaoliang.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.google.common.io.CharStreams;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/13 2:39 PM
 * Description:
 * 封装网络工具类
 * 需求：
 * 1. 采用单例封装
 * a) 声明一个实例
 * b）私有化构造方法
 * c）提供一个外部访问的方法
 * 2. 封装get请求
 */
public class HttpUtil {

    private static HttpUtil instance = new HttpUtil();

    private HttpUtil() {

    }

    public static HttpUtil getInstance() {
        return instance;
    }

    public boolean isNetConnection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

    public void httpGet(String url, final Callback callback) {
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... strings) {
                HttpURLConnection connection = null;
                try {
                    URL url1 = new URL(strings[0]);
                    connection = (HttpURLConnection) url1.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    if (connection.getResponseCode() == 200) {
                        return CharStreams.toString(new InputStreamReader(connection.getInputStream()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onError(e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                callback.onSuccess(s);
            }
        }.execute(url);
    }

    public interface Callback {
        void onSuccess(String result);

        void onError(String msg);
    }
}
