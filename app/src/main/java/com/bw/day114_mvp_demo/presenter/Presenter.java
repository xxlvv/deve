package com.bw.day114_mvp_demo.presenter;

import android.graphics.Bitmap;

import com.bw.day114_mvp_demo.Contract;
import com.bw.day114_mvp_demo.NetUtil;
import com.bw.day114_mvp_demo.model.Model;

/**
 * Copyright (C)
 * <p>
 * FileName: Presenter
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/4 14:54
 * <p>
 * Description:
 */
public class Presenter implements Contract.PresenterInter {
    private Contract.IVIew miView;
    private Contract.ModelShared modelShared;

    @Override
    public void onAttch(Contract.IVIew iView) {
        this.miView = iView;
        modelShared = new Model();
    }

    @Override
    public void onStart(String url) {
        NetUtil.getInstance().doGet(url, new NetUtil.Shared() {
            @Override
            public void ondoGetSuccess(String json) {
                miView.onSuccess(json);
            }

            @Override
            public void ondoPhotoSuccess(Bitmap bitmap) {

            }

            @Override
            public void onGetError(String error) {
                miView.onFiled(error);
            }

            @Override
            public void onPhotoError(String error) {

            }
        });
    }

    @Override
    public void End() {
        if (miView != null) {
            miView = null;
        }
        if (modelShared != null) {
            modelShared = null;
        }
    }
}
