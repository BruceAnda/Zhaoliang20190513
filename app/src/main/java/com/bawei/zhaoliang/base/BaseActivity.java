package com.bawei.zhaoliang.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/13 3:07 PM
 * Description: 基类Activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        bindView();
        initData();
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();


    /**
     * 绑定View
     */
    protected abstract void bindView();


    /**
     * 填充布局
     *
     * @return
     */
    protected abstract int initLayout();
}
