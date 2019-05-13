package com.bawei.zhaoliang.mvp;

import com.bawei.zhaoliang.net.HttpUtil;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/13 3:15 PM
 * Description: Main MVP的契约
 */
public interface IMainContract {

    public interface IMainView {

        void showList(String result);
    }

    public interface IMainModel {

        /**
         * 请求网络数据
         *
         * @param url
         * @param callback
         */
        void doHttpGet(String url, HttpUtil.Callback callback);
    }

    public interface IMainPresenter {

        void attach(IMainView view);

        void detach();

        /**
         * 分级接口 展示数据
         *
         * @param url
         */
        void requstList(String url);
    }
}
