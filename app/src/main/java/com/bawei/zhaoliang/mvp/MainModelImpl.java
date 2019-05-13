package com.bawei.zhaoliang.mvp;

import com.bawei.zhaoliang.net.HttpUtil;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/13 3:22 PM
 * Description:
 */
public class MainModelImpl implements IMainContract.IMainModel {

    private HttpUtil util;

    public MainModelImpl() {
        util = HttpUtil.getInstance();
    }

    @Override
    public void doHttpGet(String url, HttpUtil.Callback callback) {
        util.httpGet(url, callback);
    }
}
