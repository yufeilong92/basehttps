package com.yfl.base.retrofit

import android.content.Context
import com.backpacker.yflLibrary.kotlin.NetWork
import com.backpacker.yflLibrary.kotlin.T
import com.backpacker.yflLibrary.net.CommonInterceptor
import com.backpacker.yflLibrary.net.GsonFactory.GsonDConverterFactory
import com.yfl.base.base.DataMessageVo
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.HashMap
import java.util.concurrent.TimeUnit

/**
 * Created by apple on 2018/7/8.
 */
object RetrofitFactory {

    //    val BASE_URL: String = "http://zzzh.natapp1.cc/"
    val BASE_URL: String = DataMessageVo.BASE_HTTP

    private val TIMEOUT: Long = 60
    private var mainRetrofit: MainRequest? = null
    private var mineRetrofit: MainRequest? = null
    val interceptor = CommonInterceptor()
    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    private fun buildGson(): Gson {
        return GsonBuilder().serializeNulls()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create()
    }

    private fun createRetrofit(): Retrofit {
        val map = HashMap<String, String>()
        map["client"] = "android"
        CommonInterceptor.setCommonParam(map)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
//            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonDConverterFactory(buildGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun createMainRetrofit(): MainRequest {
        if (mainRetrofit == null) {
            mainRetrofit = createRetrofit().create(MainRequest::class.java)
        }
        return mainRetrofit!!
    }

    fun createMineRetrofit(): MainRequest {
        if (mineRetrofit == null) {
            mineRetrofit = createRetrofit().create(MainRequest::class.java)
        }
        return mineRetrofit!!
    }

    /**
     * 判断当前网络是否可用
     */
    fun judgmentNetWork(context: Context): Boolean {
        if (NetWork.isNetworkConnected(context)) {
            return true
        }
        T.showToast(context, "当前网络不可用")
        return false
    }


}