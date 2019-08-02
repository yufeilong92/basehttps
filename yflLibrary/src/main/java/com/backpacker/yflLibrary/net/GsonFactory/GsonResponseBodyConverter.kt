package com.backpacker.yflLibrary.net.GsonFactory

import android.text.TextUtils
import com.google.gson.Gson
//import com.igexin.sdk.PushManager
import okhttp3.ResponseBody
import retrofit2.Converter
import java.lang.reflect.Type
import org.json.JSONObject


class GsonResponseBodyConverter<T>(val gson:Gson, val type:Type): Converter<ResponseBody,T>{

    override fun convert(value: ResponseBody?): T {
        val response = value!!.string()
        value.use { value ->
            // 这里的type实际类型是 LoginUserEntity<User>  User就是user字段的对象。
            if (!response.startsWith("{") || !response.endsWith("}")) {
                throw ResultException("服务器有误，请联系客服", "-10", "")
            }
            val jsonResult: JSONObject = JSONObject(response)
                    ?: throw ResultException("服务器有误，请联系客服", "-10", "")
            val status = jsonResult.optString("code")
            if (TextUtils.isEmpty(status)) {
                throw ResultException("其他问题", "-1038",response)
            } else {
                val code = jsonResult.optString("code")

                when(code){
                    "200" -> {
                        return gson.fromJson(response, type)
                    }
                    else -> {
                        throw ResultException(jsonResult.optString("message"), code, jsonResult.optString("result")
                                ?: "")
                    }
                }
            }
        }
    }
}