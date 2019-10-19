package com.yfl.base.mvp.Contrat

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import com.yfl.base.mvp.ResultView.RequestResultInterface

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.example.tsyc.mvp.Contrat
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:08
 * @Purpose :主界面数据
 */
interface MainView {
    interface View {
        fun Success(t: Any?)
        fun Error(ex: Throwable)
        fun Complise()
    }

    interface Model {
        fun requestGson(context: RxAppCompatActivity, request: RequestResultInterface)
    }

    interface Presenter {
        fun initMvp(view: View, model: Model)
        fun requestGson(context: RxAppCompatActivity)
    }
}