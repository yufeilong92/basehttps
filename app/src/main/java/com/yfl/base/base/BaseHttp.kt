package com.yfl.base.base

import android.widget.Toast
import com.backpacker.yflLibrary.kotlin.NetWork
import com.yfl.base.retrofit.GsonFactory.ResultException
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.example.tsyc.base
 * @Email : yufeilong92@163.com
 * @Time :2019/7/8 9:20
 * @Purpose :请求基础类
 */
open class BaseHttp {
    /***
     * 结果基类
     */
    fun <T> RxJavaCallBack(
        success: (t: T) -> Unit,
        error: (t: Throwable) -> Unit,
        complate: () -> Unit
    ): Callback<T> {
        var callback = object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                error(t)
                complate()
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    success(body!!)
                } else {
                    val thread = Throwable(response.message(), ResultException())
                    error(thread)

                }
                complate()
            }
        }
        return callback
    }

    /***
     * 结果基类
     */
    fun <T> RxJaveObserver(success: (t: T) -> Unit, error: (t: Throwable) -> Unit, complate: () -> Unit): Observer<T> {
        return object : Observer<T> {
            override fun onComplete() {
                complate()
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: T) {
                success(t)
            }

            override fun onError(e: Throwable) {
                error(e)
            }
        };
    }

    /***
     * 请求网络基类
     */
    fun <T> requestHttpRxjava(
        mContext: RxAppCompatActivity,
        request: () -> Call<T>,
        success: (t: T) -> Unit,
        error: (t: Throwable) -> Unit,
        complate: () -> Unit
    ) {
        if (NetWork.isNetworkConnected(mContext)) {
            Observable.create(object : ObservableOnSubscribe<T> {
                override fun subscribe(emitter: ObservableEmitter<T>) {
                    val call = request()
                    call.enqueue(RxJavaCallBack(success = {
                        emitter.onNext(it)
                    }, error = {
                        emitter.onError(it)
                    }, complate = {
                        emitter.onComplete()
                    }))
                }
            }).compose(mContext.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(RxJaveObserver({
                    success(it)
                }, {
                    error(it)
                }, {
                    complate()
                }))
        } else {
            Toast.makeText(mContext, "网络异常，请检查网络", Toast.LENGTH_SHORT).show()
        }
    }
}