package com.binbin.mymvp.model.interf;

import com.binbin.mymvp.presenter.interf.IOnDataCallBack;

import java.io.Serializable;

/**
 * Created by -- on 2016/9/21.
 * Model负责数据获取处理
 * 获取业务数据接口，可根据不同情况进行扩展
 */
public interface IBaseModel {
    void loadData(IOnDataCallBack callBack,Object...params);
}
