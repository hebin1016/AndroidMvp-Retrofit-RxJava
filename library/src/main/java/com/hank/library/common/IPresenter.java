package com.hank.library.common;

/**
 * @author hank.he
 * @date 2016/10/8 10:21
 */
public interface IPresenter<T,M>{
    void onUnsubscribe();

    /**
     *
     * @param view
     * @param model
     */
    void setUpVM(T view, M model);
}
