package com.bw.day114_mvp_demo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Copyright (C)
 * <p>
 * FileName: BaseActivity
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/4 14:58
 * <p>
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Layout());
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int Layout();
}
