package com.yfl.base.mvp.ResultView

interface StringResultInterface{
    fun<T> Success(t: T);
    fun onError(ex: Throwable);
    fun onComplise();

}