package com.binbin.mymvp.presenter;

import com.binbin.mymvp.model.TestModel;
import com.binbin.mymvp.presenter.interf.IBasePresenter;
import com.binbin.mymvp.presenter.interf.IOnDataCallBack;
import com.binbin.mymvp.view.TestView;

import java.util.Objects;

/**
 * Created by -- on 2016/9/21.
 * 具体测试类
 */

public class TestPresenterImpl implements IBasePresenter,IOnDataCallBack {
    /*Presenter作为中间层，持有View和Model的引用*/
    private TestView testView;
    private TestModel testModel;

    public TestPresenterImpl(TestView iBaseView) {
        this.testView = iBaseView;
        testModel = new TestModel();
    }

    @Override
    public void loadData(Object...params) {
        testView.showLoadingDialog();
        testModel.loadData(this,params);
    }

    @Override
    public void onSuccess(final Object data) {
        testView.hideLoadingDialog();
        testView.bindData(data);
        testView.showSuccess();
    }

    @Override
    public void onFailure(final Object data) {
        testView.hideLoadingDialog();
        testView.showError(data);
    }
}
