package com.bw.day114_mvp_demo;


import android.graphics.Bitmap;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.bw.day114_mvp_demo.base.BaseActivity;
import com.bw.day114_mvp_demo.bean.StudentBean;
import com.bw.day114_mvp_demo.presenter.Presenter;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

public class MainActivity extends BaseActivity implements Contract.IVIew {

    @Override
    protected void initData() {

    }

    private XBanner xbannerPointId;
    private GridView gone;
    private Presenter presenter;

    @Override
    protected void initView() {


        xbannerPointId = findViewById(R.id.xbanner_pointId);
        gone = findViewById(R.id.gone);

    }

    @Override
    protected int Layout() {
        presenter = new Presenter();
        presenter.onAttch(this);
        presenter.onStart(HttpUrl.url);
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(String json) {
        Gson gson = new Gson();
        StudentBean studentBean = gson.fromJson(json, StudentBean.class);
        final List<StudentBean.BannerdataBean> bannerdata = studentBean.getBannerdata();
        xbannerPointId.setBannerData(bannerdata);
        xbannerPointId.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, final View view, int position) {
                NetUtil.getInstance().doPhoto(bannerdata.get(position).getImageUrl(), new NetUtil.Shared() {
                    @Override
                    public void ondoGetSuccess(String json) {

                    }

                    @Override
                    public void ondoPhotoSuccess(Bitmap bitmap) {
                        ((ImageView) view).setImageBitmap(bitmap);
                    }

                    @Override
                    public void onGetError(String error) {

                    }

                    @Override
                    public void onPhotoError(String error) {

                    }
                });
            }
        });

        List<StudentBean.ListdataBean> listdata = studentBean.getListdata();
        gone.setAdapter(new MyBaseAdapter(MainActivity.this, listdata));

    }

    @Override
    public void onFiled(String error) {

    }


}
