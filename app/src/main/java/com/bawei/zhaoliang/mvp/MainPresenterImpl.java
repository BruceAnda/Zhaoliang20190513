package com.bawei.zhaoliang.mvp;

import com.bawei.zhaoliang.net.HttpUtil;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/13 3:21 PM
 * Description:
 */
public class MainPresenterImpl implements IMainContract.IMainPresenter {

    private IMainContract.IMainView view;
    private IMainContract.IMainModel model;

    @Override
    public void attach(IMainContract.IMainView view) {
        this.view = view;
        model = new MainModelImpl();
    }

    @Override
    public void detach() {
        if (view != null) {
            view = null;
        }
        if (model != null) {
            model = null;
        }
    }

    @Override
    public void requstList(String url) {
        model.doHttpGet(url, new HttpUtil.Callback() {
            @Override
            public void onSuccess(String result) {
                view.showList(result);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
