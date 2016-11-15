package com.hank.library.common;


import com.hank.library.baserx.RxManager;

/**
 * @author hank.he
 * @date 2016/10/8 10:19
 */
public abstract class BasePresenter<T extends IBaseView,M extends IModel> implements IPresenter<T,M> {

    protected static final String TAG = "BasePresenter";
    protected T mView;
    protected M mModel;
    public RxManager mRxManage = new RxManager();

    public BasePresenter(){
        onStart();
    }

    @Override
    public void setUpVM(T view, M model) {
        mView=view;
        mModel=model;
    }

    @Override
    public void onUnsubscribe() {
        mRxManage.clear();
        mView = null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public T getView() {
        return mView;
    }

    public M getModel() {
        return mModel;
    }

    public abstract void onStart();

}
