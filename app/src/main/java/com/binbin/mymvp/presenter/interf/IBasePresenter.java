package com.binbin.mymvp.presenter.interf;

/**
 * Created by -- on 2016/9/21.
 */

public interface IBasePresenter {
   /**
    * 去获取数据，在实现类中调用真正的Model中的获取数据的方法
    * @param params
     */
   void loadData(Object...params);
}
