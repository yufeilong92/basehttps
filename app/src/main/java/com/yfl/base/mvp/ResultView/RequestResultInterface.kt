package com.yfl.base.mvp.ResultView

interface RequestResultInterface{
    fun<T> Success(t: T);
    fun onError(ex: Throwable);
    fun onComplise();
}
