package com.yfl.base.retrofit.GsonFactory

import java.io.IOException

class ResultException(
var msg: String = "",
var status: String = "",
var data: String = "") : IOException() {

}