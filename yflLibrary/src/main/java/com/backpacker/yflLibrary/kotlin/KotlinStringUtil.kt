package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView



/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary
 * @Description:    字符串工具类
 * @author: L-BackPacker
 * @date:   2019/3/31 21:14
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object KotlinStringUtil {

    fun isEmpty(str: String?): Boolean {
        if (str == null || str == "")
            return true
        return false
    }

    fun getObjectToStr(v: View): String {
        if (v is TextView) {
            val tv = v as TextView
            return tv.text.toString().trim { it <= ' ' }
        }
        if (v is EditText) {
            val et = v as EditText
            return et.text.toString().trim { it <= ' ' }
        }
        if (v is Button) {
            val btn = v as Button
            return btn.getText().toString().trim()
        }
        return ""
    }

    fun getStringWid(m: Context, id: Int): String {
        return m.getResources().getString(id)
    }

}