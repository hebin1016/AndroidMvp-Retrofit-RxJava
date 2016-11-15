package com.hank.library.util;

import rx.Subscriber;

public abstract class HttpSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        _complete();
    }

    @Override
    public void onError(Throwable e) {
        _complete();
        onError(e.getMessage());
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    public abstract void onSuccess(T t);

    public abstract void onError(String msg);

    public abstract void _complete();

}
