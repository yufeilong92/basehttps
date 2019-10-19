package com.zzzh.akhalteke.utils

import android.content.Context
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.backpacker.yflLibrary.kotlin.KotlinStringUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.UtilsLibrary.R
import java.io.File

/**
 * @Author : YFL  is Creating a porject in akhalteke-Android-driver
 * @Package com.zzzh.akhalteke.utils
 * @Email : yufeilong92@163.com
 * @Time :2019/5/15 11:42
 * @Purpose :图片加载
 */
object GlideUtil {
    /**
     * 加载图片
     */
    fun LoadImager(context: Context, img: ImageView, path: String) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (KotlinStringUtil.isEmpty(path)){
            img.setImageResource(R.mipmap.ic_default_picture)
            return
        }
        Glide.with(context)
                .load(path)
                .into(img)
    }
    /**
     * 加载图片
     */
    fun LoadImagerWithOutHttp(context: Context, img: ImageView, path: String) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (KotlinStringUtil.isEmpty(path)){
            img.setImageResource(R.mipmap.ic_default_picture)
            return
        }
        Glide.with(context)
            .load(path)
            .into(img)
    }

    /**
     * 加载四个圆角
     */
    fun loadQuadRangleImager(context: Context, img: ImageView, path: String) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (KotlinStringUtil.isEmpty(path)){
            img.setImageResource(R.mipmap.ic_default_picture)
            return
        }
        val roundedCorners = RoundedCorners(10);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
//         RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
//        val override = RequestOptions.bitmapTransform(roundedCorners).override(300, 300)
        val options = RequestOptions.bitmapTransform(roundedCorners);
//        val float=8.0f
//        val options = RequestOptions.bitmapTransform(
//            RoundCorner(
//                context,
//                leftTop = float,
//                rightBottom = float,
//                rightTop = float,
//                leftBottom = float
//            )
//        )
        Glide.with(context)
                .load(path)
                .apply(options)
                .into(img)
    }

    /**
     * 加载圆角
     */
    fun loadCilcleImager(context: Context, img: ImageView, path: String) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (KotlinStringUtil.isEmpty(path)){
            img.setImageResource(R.mipmap.ic_default_picture)
            return
        }
        val mRequestOptions = RequestOptions.circleCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                .skipMemoryCache(true);//不做内存缓存
        Glide.with(context)
                .load(path)
                .apply(mRequestOptions)
                .into(img)
    }

    /**
     * 加载圆角
     */
    fun loadHearCilcleImager(context: Context, img: ImageView, path: String) {
        //        img.scaleType=ImageView.ScaleType.FIT_XY
        if (KotlinStringUtil.isEmpty(path)){
            img.setImageResource(R.mipmap.ic_logo)
            return
        }
        val mRequestOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
            .skipMemoryCache(true);//不做内存缓存
        Glide.with(context)
            .load(path)
            .apply(mRequestOptions)
            .into(img)
    }

    /**
     * 加载图片
     */
    fun LoadBImager(context: Context, img: ImageView, path: String) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (KotlinStringUtil.isEmpty(path)){
            img.setImageResource(R.mipmap.ic_default_picture)
            return
        }
        Glide.with(context)
            .load(File(path))
            .into(img)
    }
    /**
     * 加载图片
     */
    fun LoadBImagerWithOutHttp(context: Context, img: ImageView, path: String) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (KotlinStringUtil.isEmpty(path)){
            img.setImageResource(R.mipmap.ic_default_picture)
            return
        }
        Glide.with(context)
            .load(File(path))
            .into(img)
    }

    /**
     * 加载四个圆角
     */
    fun loadBQuadRangleImager(context: Context, img: ImageView, path: String) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (KotlinStringUtil.isEmpty(path)){
            img.setImageResource(R.mipmap.ic_default_picture)
            return
        }
        val roundedCorners = RoundedCorners(10);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
//         RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
//        val override = RequestOptions.bitmapTransform(roundedCorners).override(300, 300)
        val options = RequestOptions.bitmapTransform(roundedCorners);
//        val float=8.0f
//        val options = RequestOptions.bitmapTransform(
//            RoundCorner(
//                context,
//                leftTop = float,
//                rightBottom = float,
//                rightTop = float,
//                leftBottom = float
//            )
//        )
        Glide.with(context)
            .load(File(path))
            .apply(options)
            .into(img)
    }

    /**
     * 加载圆角
     */
    fun loadBCilcleImager(context: Context, img: ImageView, path: String) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (KotlinStringUtil.isEmpty(path)){
            img.setImageResource(R.mipmap.ic_default_picture)
            return
        }
        val mRequestOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
            .skipMemoryCache(true);//不做内存缓存
        Glide.with(context)
            .load(File(path))
            .apply(mRequestOptions)
            .into(img)
    }

    /**
     * 加载圆角
     */
    fun loadBCilcleImager(context: Context, img: ImageView, path: Int) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        val resource = BitmapFactory.decodeResource(context.resources, path)
        val mRequestOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
            .skipMemoryCache(true);//不做内存缓存
        Glide.with(context)
            .load(resource)
            .apply(mRequestOptions)
            .into(img)
    }
}
