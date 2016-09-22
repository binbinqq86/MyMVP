package com.binbin.mymvp.model;

import android.os.Handler;
import android.os.Looper;

import com.binbin.mymvp.model.interf.IBaseModel;
import com.binbin.mymvp.presenter.interf.IOnDataCallBack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by -- on 2016/9/21.
 */

public class TestModel implements IBaseModel {
    private final Handler mHandler=new Handler(Looper.getMainLooper());//用于在主线程更新数据和界面

    /**
     * 实现具体的数据获取操作
     * @param callBack  回调接口
     * @param params  具体请求参数
     */
    @Override
    public void loadData(final IOnDataCallBack callBack, Object...params) {
        new Thread(){//开启子线程进行网络请求
            @Override
            public void run() {
                super.run();
                // 通过http请求把图片获取下来。
                try {
                    // 1.声明访问的路径， url 网络资源 http ftp rtsp
                    URL url = new URL("http://www.baidu.com");
                    // 2.通过路径得到一个连接 http的连接
                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();
                    // 3.判断服务器给我们返回的状态信息。
                    // 200 成功 302 从定向 404资源没找到 5xx 服务器内部错误
                    int code = conn.getResponseCode();
                    if (code == 200) {
                        // 4.利用链接成功的 conn 得到输入流
                        String resultData = "";
                        InputStreamReader in = new InputStreamReader(conn.getInputStream());
                        BufferedReader buffer = new BufferedReader(in);
                        String inputLine = null;
                        while (((inputLine = buffer.readLine()) != null)){
                            resultData += inputLine + "\n";
                        }
//                        Log.e("tianbin",resultData);
                        sendSuccessCallBack(callBack,resultData);
                        in.close();
                    } else {
                        // 请求失败
                        sendFailCallBack(callBack,conn.getResponseMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    sendFailCallBack(callBack,"-_-*-_-*-_-");
                }
            }
        }.start();
    }

    private void sendFailCallBack(final IOnDataCallBack callBack,final Object data){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onFailure(data);
                }
            }
        });
    }
    private void sendSuccessCallBack(final IOnDataCallBack callback, final Object data) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(data);
                }
            }
        });
    }
}
