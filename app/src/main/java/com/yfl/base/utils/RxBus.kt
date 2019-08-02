package com.yfl.base.utils

import com.backpacker.yflLibrary.demo.RxBus
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * @Author : YFL  is Creating a porject in basehttp
 * @Package com.example.tsyc.utils
 * @Email : yufeilong92@163.com
 * @Time :2019/7/25 17:07
 * @Purpose :
 */
class RxBus {
    companion object {
        private var sInstance: RxBus? = null
        private val mEventBus= PublishSubject.create<Any>()
        fun getInstance(): RxBus {
            if (sInstance == null) {
                synchronized(RxBus::class.java) {
                    if (sInstance == null)
                        sInstance = RxBus()
                }
            }
            return sInstance!!
        }
    }

    fun send(t: Any) {
        if (mEventBus.hasObservers()) {
            mEventBus.onNext(t)
        }
    }

    fun send(code: Int, event: Any) {
        if (mEventBus.hasObservers()) {
            val msg = Message(code, event)
            mEventBus.onNext(msg)
        }

    }

    fun <T> toObservable(cls:Class<T>): Observable<T>? {
        return mEventBus.ofType(cls)
    }

    fun <T> toObservable(code:Int,cls:Class<T>){
       mEventBus.ofType(Message::class.java)
           .filter {
               it.code==code&&cls.isInstance(it.event)
           }.map { it.event }

    }


    inner class Message(
        var code: Int,
        var event: Any
    )


}