package com.bw.day114_mvp_demo;

/**
 * Copyright (C)
 * <p>
 * FileName: Contract
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/4 14:39
 * <p>
 * Description:
 */
public class Contract {
    public interface ModelShared {
        void doData_GET(String url, ModelShareds modelShareds);

        void doData_POST(String url, ModelShareds modelShareds);
    }

    public interface ModelShareds {
        void onSuccess(String json);

        void onFiled(String error);
    }

    public interface PresenterInter {
        void onAttch(IVIew iView);

        void onStart(String url);

        void End();
    }

    public interface IVIew {
        void onSuccess(String json);

        void onFiled(String error);
    }

}
