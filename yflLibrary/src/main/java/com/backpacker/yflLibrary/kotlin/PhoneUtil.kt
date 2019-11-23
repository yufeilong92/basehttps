package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.example.UtilsLibrary.R
import java.util.regex.Pattern

/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/3/31 23:02
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object PhoneUtil {
    /**
     * 150*****7856
     *
     * @param phone
     * @return
     */
    fun phoneHineNumber(phone: String): String {
        val str = "****"
        val sb = StringBuilder(phone)
        sb.replace(3, 7, str)
        return sb.toString()
    }

    /**
     * 判断是否为手机号码
     *
     * @return
     */
    fun isPhoneNum(phone: String): Boolean {
        val pattern = Pattern.compile("1[0-9]{10}")
        val matcher = pattern.matcher(phone)
        return matcher.matches()
    }


    /**
     * 判断是否400服务代码
     *
     * @param num
     * @return
     */
    fun is400or800(num: String): Boolean {
        return (!TextUtils.isEmpty(num)
                && (num.startsWith("400") || num.startsWith("800"))
                && num.length == 10)
    }

    /**
     * 判断是否区域号码
     *
     * @param num
     * @return
     */
    fun isAdCode(num: String): Boolean {
        return (!TextUtils.isEmpty(num) && num.matches("[0]{1}[0-9]{2,3}".toRegex())
                && !KotlinUtil.isRepeatedStr(num))
    }

    /**
     * 判断是否座机号码
     *
     * @param num
     * @return
     */
    fun isPhoneHome(num: String): Boolean {
        return (!TextUtils.isEmpty(num) && num.matches("[0-9]{7,8}".toRegex())
                && !KotlinUtil.isRepeatedStr(num))
    }

    fun isPhoneRight(mContext: Context, et: EditText): Boolean {
        val phone = KotlinStringUtil.getObjectToStr(et)
        if (KotlinStringUtil.isEmpty(phone)) {
            T.showToast(mContext, "请输入手机号码")
            return false

        }
        if (!PhoneUtil.isPhoneNum(phone)) {
            T.showToast(mContext, "请输入正确手机号码")
            return false
        }
        return true
    }

    fun playPhone(m: Context, phone: String) {
        if (KotlinStringUtil.isEmpty(phone)) {
            Toast.makeText(m, "电话号码为空", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(Intent.ACTION_DIAL)
        val data = Uri.parse("tel:$phone");
        intent.data = data;
        m.startActivity(intent);
    }

    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *
     * @param mobile 移动、联通、电信运营商的号码段
     *
     * 移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     * 、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）
     *
     * 联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）
     *
     * 电信的号段：133、153、180（未启用）、189
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkMobile(mobile: String): Boolean {
        val regex = "(\\+\\d+)?1[3458]\\d{9}$"
        return Pattern.matches(regex, mobile)
    }
    /**
     * 验证固定电话号码
     *
     * @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
     *
     * **国家（地区） 代码 ：**标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
     * 数字之后是空格分隔的国家（地区）代码。
     *
     * **区号（城市代码）：**这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
     * 对不使用地区或城市代码的国家（地区），则省略该组件。
     *
     * **电话号码：**这包含从 0 到 9 的一个或多个数字
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkPhone(phone: String): Boolean {
        val regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$"
        return Pattern.matches(regex, phone)
    }
    /**
     * 手机号码，中间4位星号替换
     *
     * @param phone 手机号
     * @return 星号替换的手机号
     */
    fun phoneNoHide(phone: String): String {
        // 括号表示组，被替换的部分$n表示第n组的内容
        // 正则表达式中，替换字符串，括号的意思是分组，在replace()方法中，
        // 参数二中可以使用$n(n为数字)来依次引用模式串中用括号定义的字串。
        // "(\d{3})\d{4}(\d{4})", "$1****$2"的这个意思就是用括号，
        // 分为(前3个数字)中间4个数字(最后4个数字)替换为(第一组数值，保持不变$1)(中间为*)(第二组数值，保持不变$2)
        return phone.replace("(\\d{3})\\d{4}(\\d{4})".toRegex(), "$1****$2")
    }
}