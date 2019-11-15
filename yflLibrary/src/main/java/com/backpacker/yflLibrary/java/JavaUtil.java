package com.backpacker.yflLibrary.java;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.java
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2019/3/31 22:26
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class JavaUtil {
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int dp2px(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }
    public static int dp2px(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }
    /**
     * 改变颜色
     *
     * @param str1 要查询的字符串
     * @param str2 要该变颜色字符串
     * @return
     */
    public static Spanned repaceStr(String str1, String str2, String color) {

        if (color == null || TextUtils.isEmpty(color)) {
            color = "#fb595b";
        }
        String s = str1.replaceAll(str2, "<font color='" + color + "'><normal>" + str2 + "</normal></font>");
        Spanned spanned = Html.fromHtml(s);

        return spanned;

    }


}
