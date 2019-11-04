package com.bw.day114_mvp_demo;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Copyright (C)
 * <p>
 * FileName: NetUtil
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/4 14:44
 * <p>
 * Description:
 */
public class NetUtil {
    private NetUtil() {

    }

    private static class NetUtils {
        private static NetUtil netUtil = new NetUtil();
    }

    public static NetUtil getInstance() {
        return NetUtils.netUtil;
    }

    public interface Shared {
        void ondoGetSuccess(String json);

        void ondoPhotoSuccess(Bitmap bitmap);

        void onGetError(String error);

        void onPhotoError(String error);
    }

    private String io2String(InputStream inputStream) throws IOException {
        StringBuffer buffer = new StringBuffer();
        byte[] bytes = new byte[1024];
        int p = 0;
        while ((p = inputStream.read(bytes)) != -1) {
            String jj = new String(bytes, 0, p);
            buffer.append(jj);
        }
        return buffer.toString();
    }

    private Bitmap io2Bitmap(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

    @SuppressLint("StaticFieldLeak")
    public void doGet(final String http, final Shared shared) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                HttpURLConnection connection = null;
                InputStream inputStream = null;
                try {
                    URL url = new URL(http);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    if (connection.getResponseCode() == 200) {
                        inputStream = connection.getInputStream();
                        return io2String(inputStream);
                    }
                } catch (Exception e) {

                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (TextUtils.isEmpty(s)) {
                    shared.onGetError("请检查doInBackground代码");
                } else {
                    shared.ondoGetSuccess(s);
                }
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void doPhoto(final String http, final Shared shared) {
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                HttpURLConnection connection = null;
                InputStream inputStream = null;
                try {
                    URL url = new URL(http);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    if (connection.getResponseCode() == 200) {
                        inputStream = connection.getInputStream();
                        return io2Bitmap(inputStream);
                    }
                } catch (Exception e) {

                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                if (bitmap != null) {
                    shared.ondoPhotoSuccess(bitmap);
                } else {
                    shared.ondoPhotoSuccess(bitmap);
                }
            }
        }.execute();
    }
}
