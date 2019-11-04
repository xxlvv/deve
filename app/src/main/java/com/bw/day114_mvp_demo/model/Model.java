package com.bw.day114_mvp_demo.model;

import android.graphics.Bitmap;

import com.bw.day114_mvp_demo.Contract;
import com.bw.day114_mvp_demo.NetUtil;

/**
 * Copyright (C)
 * <p>
 * FileName: Model
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/4 14:43
 * <p>
 * Description:
 */
public class Model implements Contract.ModelShared {
    @Override
    public void doData_GET(String url, final Contract.ModelShareds modelShareds) {
        NetUtil.getInstance().doGet(url, new NetUtil.Shared() {
            @Override
            public void ondoGetSuccess(String json) {
                modelShareds.onSuccess(json);
            }

            @Override
            public void ondoPhotoSuccess(Bitmap bitmap) {

            }

            @Override
            public void onGetError(String error) {
                modelShareds.onFiled(error);
            }

            @Override
            public void onPhotoError(String error) {

            }
        });
    }

    @Override
    public void doData_POST(String url, Contract.ModelShareds modelShareds) {

    }
}
