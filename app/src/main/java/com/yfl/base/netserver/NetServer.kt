package com.yfl.base.netserver

import com.yfl.base.base.BaseHttp

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.example.tsyc.netserver
 * @Email : yufeilong92@163.com
 * @Time :2019/7/8 16:12
 * @Purpose :
 */
object NetServer : BaseHttp() {
//    /***
//     * 登录接口
//     * @param phone  手机号
//     * @param psw   密码
//     */
//    fun Login(mContext: Context, phone: String, psw: String, respone: StringResultInterface) {
//
//        if (RetrofitFactory.judgmentNetWork(mContext)) {
//            RetrofitFactory.createMainRetrofit().Login(
//                username = phone,
//                password = psw
//            )
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(RxJaveObserver({
//                    respone.Success(it.data)
//                }, {
//                    respone.onError(it)
//                }, {
//                    respone.onComplise()
//                }))
//
//        }
//    }
//
//    /***
//     * 提交注册
//     */
//    fun submiteRegister(
//        mContext: Context,
//        phone: String,
//        code: String,
//        psw: String,
//        invite:String,
//        respone: StringResultInterface
//    ) {
//
//        if (RetrofitFactory.judgmentNetWork(mContext)) {
//            RetrofitFactory.createMainRetrofit().register(
//                phone = phone,
//                password = psw,
//                captcha = code,
//                invite = invite
//            )
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(RxJaveObserver({
//                    respone.Success(it.data)
//                }, {
//                    respone.onError(it)
//                }, {
//                    respone.onComplise()
//                }))
//
//        }
//    }
//
//    /***
//     * 请求验证码
//     */
//    fun requestCode(mContext: Context, type: String, phone: String, respone: StringResultInterface) {
//
//        if (RetrofitFactory.judgmentNetWork(mContext)) {
//
//            RetrofitFactory.createMainRetrofit().reqeustCode(
//                type = type,
//                phone = phone
//            )
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(RxJaveObserver({
//                    respone.Success(it.data)
//                }, {
//                    respone.onError(it)
//                }, {
//                    respone.onComplise()
//                }))
//        }
//
//    }
//
//    /**
//     * 忘记密码
//     */
//    fun submitForgetPsw(mContext: Context, phone: String, code: String, psw: String, respone: StringResultInterface) {
//        if (RetrofitFactory.judgmentNetWork(mContext)) {
//            RetrofitFactory.createMainRetrofit().submitForgetPsw(
//                phone = phone,
//                captcha = code,
//                password = psw
//            ).observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(RxJaveObserver(
//                    success = {
//                        respone.Success(it.data)
//                    }, error = {
//                        respone.onError(it)
//                    }, complate = {
//                        respone.onComplise()
//                    })
//                )
//        }
//    }
//
//    /**
//     * 请求首页数据
//     */
//    fun requestHomeData(mContext: Context,respone: StringResultInterface){
//        if (RetrofitFactory.judgmentNetWork(mContext)) {
//            RetrofitFactory.createMainRetrofit().requestHomeData()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(RxJaveObserver(
//                    success = {
//                        respone.Success(it.data)
//                    }, error = {
//                        respone.onError(it)
//                    }, complate = {
//                        respone.onComplise()
//                    })
//                )
//        }
//
//    }

}