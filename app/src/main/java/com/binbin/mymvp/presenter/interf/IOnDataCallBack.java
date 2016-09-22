package com.binbin.mymvp.presenter.interf;

/**
 * Created by -- on 2016/9/21.
 * 数据返回时调用，可扩展其他情况
 */

public interface IOnDataCallBack {
    /**
     * 成功时回调
     */
    void onSuccess(Object data);
    /**
     * 失败时回调
     */
    void onFailure(Object data);
}
