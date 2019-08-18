package com.yfl.base.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.backpacker.yflLibrary.kotlin.T
import com.backpacker.yflLibrary.net.GsonFactory.ResultException

import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/18 10:49:
 * @Purpose :viwemodel基类
 */
class BaseViewModel : ViewModel() {

    val isShowProgress: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun error(ex: Throwable) {
        isShowProgress.value = 1
        try {
            val error = if (ex is SocketTimeoutException) {
                "网络连接超时，请稍后再试..."
            } else if (ex is ConnectException) {
                "网络连接超时，请稍后再试..."
            } else if (ex is UnknownHostException) {
                "网络连接超时，请稍后再试..."
            } else {
                if (ex is ResultException) {
                    when (ex.status) {

                    }
                    (ex as ResultException).msg   //抛出异常，抓取数据
                } else {
                    "网络连接异常，请稍候重试"
                }
            }
            //todo 处理数据弹出对话框

            ex.printStackTrace()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun complate() {
        isShowProgress.value = 1
    }


}