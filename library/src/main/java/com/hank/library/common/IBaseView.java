package com.hank.library.common;

/**
 * @author hank.he
 * @date 2016/8/30 13:57
 */
public interface IBaseView {
    public void showLoadingProgress();

    public void dismissLoadingProgress();

    public void showError(String str);

}
