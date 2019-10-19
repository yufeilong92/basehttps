package com.yfl.base.mvp.Model

import com.yfl.base.mvp.Contrat.MainView
import com.yfl.base.retrofit.Request_Net
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import com.yfl.base.mvp.ResultView.RequestResultInterface

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.example.tsyc.mvp.Model
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:09
 * @Purpose :主界面数据层
 */
class MainModel : MainView.Model {
    override fun requestGson(context: RxAppCompatActivity, request: RequestResultInterface) {
        Request_Net.getText(context,{
            request.Success(it)
        }, {
            request.onComplise()

        }, {
            request.onError(it)
        })
    }
}