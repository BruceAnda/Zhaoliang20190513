package com.bawei.zhaoliang;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.zhaoliang.adapter.MainListAdapter;
import com.bawei.zhaoliang.base.BaseActivity;
import com.bawei.zhaoliang.bean.ItemsResponse;
import com.bawei.zhaoliang.mvp.IMainContract;
import com.bawei.zhaoliang.mvp.MainPresenterImpl;
import com.bawei.zhaoliang.net.HttpUtil;
import com.google.gson.Gson;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/13 3:10 PM
 * Description: 主界面
 */
public class MainActivity extends BaseActivity implements IMainContract.IMainView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView mainList;
    private IMainContract.IMainPresenter presenter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void initData() {

        if (HttpUtil.getInstance().isNetConnection(this)) {
            // 有网
            Log.i(TAG, "网络请求数据");
            presenter.requstList("https://code.aliyun.com/598254259/FristProject/raw/master/bw_list.txt");
        } else {
            // 没有网络
            // 读SP
            Log.i(TAG, "展示sp数据");
            String list = sharedPreferences.getString("list", "");
            if (TextUtils.isEmpty(list)) {
                Toast.makeText(this, "没有缓存到数据", Toast.LENGTH_SHORT).show();
            } else {
                showList(list);
            }
        }
    }

    @Override
    protected void bindView() {
        mainList = findViewById(R.id.main_list);
        presenter = new MainPresenterImpl();
        presenter.attach(this);

        sharedPreferences = getSharedPreferences("list", MODE_PRIVATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showList(String result) {
        // 存sp
        String list = sharedPreferences.getString("list", "");
        if (TextUtils.isEmpty(list)) {
            sharedPreferences.edit().putString("list", result).commit();
        }

        //Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        // 使用Gson解析
        Gson gson = new Gson();
        ItemsResponse response = gson.fromJson(result, ItemsResponse.class);

        // 创建Adapter
        // 给ListView设置Adapter
        MainListAdapter adapter = new MainListAdapter(this, response.items);
        mainList.setAdapter(adapter);
    }
}
