package com.binbin.mymvp.view;

/**
 * Created by -- on 2016/9/21.
 * View负责数据显示，可扩展其他情况
 */

public interface IBaseView {
    void showLoadingDialog();

    void hideLoadingDialog();

    void showError(Object error);

    /**
     * 把数据与视图绑定
     * @param data  获取到的数据
     */
    void bindData(Object data);
}
