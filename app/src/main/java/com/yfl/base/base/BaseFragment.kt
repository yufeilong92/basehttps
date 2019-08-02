package com.yfl.base.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.backpacker.yflLibrary.kotlin.PermissionUtils
import com.backpacker.yflLibrary.kotlin.T
import com.backpacker.yflLibrary.kotlin.Util
import com.backpacker.yflLibrary.net.HttpBaseResult
import com.backpacker.yflLibrary.view.MyProgreeDialog
import com.yfl.base.manage.ResultFragmentTo

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.base
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:43
 * @Purpose :碎片基类
 */
abstract class BaseFragment : Fragment() {

    abstract fun setContentView(): Int
    lateinit var mResultTo: ResultFragmentTo
    lateinit var mContext:Activity
    lateinit var progressDialog: MyProgreeDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(setContentView(), container, false)
    }

    abstract fun setInitCreatedContentView(view: View, savedInstanceState: Bundle?)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mResultTo = ResultFragmentTo(this.activity!!)
        setInitCreatedContentView(view, savedInstanceState)
        mContext= this.activity!!
        progressDialog = MyProgreeDialog(mContext)
    }
    private fun test() {
        PermissionUtils.showPermission(mContext, "", arrayOf("")) {

        }
    }


    fun showProgress() {
        Util.showDialog(progressDialog)
    }

    fun dismissProgress() {
        Util.dismissDialog(progressDialog)
    }

    fun onError(ex: Throwable) {
        dismissProgress()
        HttpBaseResult.onError(mContext, ex)
    }

    fun onComplate() {
        dismissProgress()
        HttpBaseResult.onComplate()
    }

    fun getStringWithId(id: Int): String {
        return mContext.resources.getString(id)
    }

    fun toShowToasct(content: String) {
        T.showToast(mContext, content)
    }

    open fun onHomeBack(v: View) {
        mResultTo.finishBase()
    }
}