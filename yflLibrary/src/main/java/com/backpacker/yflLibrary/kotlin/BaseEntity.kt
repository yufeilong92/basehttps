package com.backpacker.yflLibrary.kotlin

import com.google.gson.annotations.SerializedName

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2019/7/5 17:36
 * @Purpose :
 */
class BaseEntity <E>(){

    @SerializedName("code")
    var code:String = ""

    @SerializedName("message")
    var message:String = ""

    @SerializedName("result")
    var data:E? = null

}