package com.yfl.base.retrofit.GsonFactory

import com.google.gson.annotations.SerializedName

class BaseErrorEntity{
    @SerializedName("status")
    var status:Int = 0

    @SerializedName("msg")
    var msg:String = ""

    @SerializedName("data")
    var data:String = ""
}