package com.yfl.base.application

import android.app.Application
import com.backpacker.yflLibrary.kotlin.AppBarUtil
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.tencent.bugly.crashreport.CrashReport
import com.yfl.base.R
import net.danlew.android.joda.JodaTimeAndroid

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.example.tsyc.Application
 * @Email : yufeilong92@163.com
 * @Time :2019/7/5 16:44
 * @Purpose :
 */
class BaseApplication : Application() {

    lateinit var mInstance: BaseApplication

    companion object {
        private var instance: BaseApplication? = null
        fun toInstance(): BaseApplication {
            return instance!!
        }

        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(R.color.main_bg, R.color.main_text9)
                return@setDefaultRefreshHeaderCreator ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate)
            }

            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                return@setDefaultRefreshFooterCreator ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate)
            }
            instance = BaseApplication();
        }


    }

    fun getAppInstance(): BaseApplication {
        return mInstance
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        initbug()
        initData()
        JodaTimeAndroid.init(this)
    }

    fun initbug() {
        CrashReport.initCrashReport(applicationContext)
    }

    fun initData() {
        AppBarUtil.obtainScreenWH(this)
    }
}