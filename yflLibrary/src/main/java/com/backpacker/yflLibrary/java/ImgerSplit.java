package com.backpacker.yflLibrary.java;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.java
 * @Description: t图片拼接
 * @author: L-BackPacker
 * @date: 2019/4/1 0:21
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class ImgerSplit {
    /**
     *
     * @param bit1 上面布局
     * @param bit2 下图片
     * @return
     */
    public static Bitmap add2Bitmap(Bitmap bit1, Bitmap bit2) {
        Bitmap newBit = null;
        int width = bit1.getWidth();
        if (bit2.getWidth() != width) {
            int h2 = bit2.getHeight() * width / bit2.getWidth();
            newBit = Bitmap.createBitmap(width, bit1.getHeight() + h2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(newBit);
            Bitmap newSizeBitmap2 = getNewSizeBitmap(bit2, width, h2);
            canvas.drawBitmap(bit1, 0, 0, null);
            canvas.drawBitmap(newSizeBitmap2, 0, bit1.getHeight(), null);
        } else {
            newBit = Bitmap.createBitmap(width, bit1.getHeight() + bit2.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(newBit);
            canvas.drawBitmap(bit1, 0, 0, null);
            canvas.drawBitmap(bit2, 0, bit1.getHeight(), null);
        }
        return newBit;
    }
    private static Bitmap getNewSizeBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        float scaleWidth = ((float) newWidth) / bitmap.getWidth();
        float scaleHeight = ((float) newHeight) / bitmap.getHeight();
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap bit1Scale = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,
                true);
        return bit1Scale;
    }
    /***
     * @param mContent 上下文
     * @param img  图片
     * @param size 倍数
     * @return
     */
    public static  void  setImgerSize(Context mContent, ImageView img,double size){
        WindowManager wm = (WindowManager) mContent.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        //屏幕宽度
        int widthPixels = dm.widthPixels;
        try {
            double div = ArithUtil.div(widthPixels, size, 2);
            ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
            layoutParams.height= (int) div;
            img.setLayoutParams(layoutParams);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    /***
     * @param mContent 上下文
     * @param img  图片
     * @param size 倍数
     * @return
     */
    public static  void  setImgerSize(Context mContent, ImageView img,String size){
        String[] split = size.split("x");
        try {
            double sizeDoubul = ArithUtil.div(Double.valueOf(split[0]), Double.valueOf(split[1]), 2);
            WindowManager wm = (WindowManager) mContent.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            //屏幕宽度
            int widthPixels = dm.widthPixels;
            try {
                double div = ArithUtil.div(widthPixels, sizeDoubul, 2);
                ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
                layoutParams.height= (int) div;
                img.setLayoutParams(layoutParams);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}