package com.yfl.base.base

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.backpacker.yflLibrary.java.UriToFile
import com.backpacker.yflLibrary.kotlin.ImagerUtil
import com.backpacker.yflLibrary.kotlin.PermissionUtils
import com.backpacker.yflLibrary.kotlin.TakePhotoUtils
import com.backpacker.yflLibrary.kotlin.Util
import com.backpacker.yflLibrary.view.SelectCammerDialog
import com.yanzhenjie.permission.Permission
import com.yfl.base.base.BaseActivity
import me.nereo.multi_image_selector.MultiImageSelector
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File
import java.io.IOException

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.base
 * @Email : yufeilong92@163.com
 * @Time :2019/7/5 10:01
 * @Purpose :图片选择基类
 */
abstract class BaseSelectImageActivity : BaseActivity() {
    private val REQUEST_IMAGE_BACK = 1002//从相册选择
    private val PHOTO_PIC_CODE = 1001// 拍照
    lateinit var selectImageDialog: SelectCammerDialog
    private var temp = 1
    private var imagePaths: ArrayList<String> = arrayListOf()
    private var mCompressPaths: ArrayList<String> = arrayListOf()
    override fun onInitCreateView(savedInstanceState: Bundle?) {
        selectImageDialog = object : SelectCammerDialog(mContext) {
            override fun onFromPhoto() {
                PermissionUtils.showPermission(
                    this@BaseSelectImageActivity, "需要照相和读写权限，是否同意", arrayOf(
                        Permission.CAMERA,
                        Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    toSelectPhoto()
                }
            }

            override fun onTakePrice() {
                PermissionUtils.showPermission(
                    this@BaseSelectImageActivity, "需要照相和读写权限，是否同意", arrayOf(
                        Permission.CAMERA,
                        Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    toPicture()
                }
            }

        }
    }

    private var takePhoneUri: Uri? = null
    private fun toPicture() {
        try {
            takePhoneUri = TakePhotoUtils.takePhoto(mContext, PHOTO_PIC_CODE)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun toSelectPhoto() {
        if (temp == 1) {
            MultiImageSelector.create().showCamera(false).single().start(this, REQUEST_IMAGE_BACK)
        } else {
            MultiImageSelector.create().showCamera(false).count(temp).multi().origin(imagePaths)
                .start(this, REQUEST_IMAGE_BACK)
        }
    }

    fun toShowDialog() {
        Util.showDialog(selectImageDialog)
    }

    fun toShowDialog(num: Int) {
        temp = num
        Util.showDialog(selectImageDialog)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PHOTO_PIC_CODE -> {//拍照
                if (resultCode == Activity.RESULT_OK) {
                    if (takePhoneUri != null) {
                        val filePath = UriToFile.getFilePathFromURI(mContext, takePhoneUri!!)
                        imagePaths.add(filePath)
                        lunBanCompress(imagePaths)
                    }
                }
            }
            REQUEST_IMAGE_BACK -> {//相册
                if (data == null) return
                imagePaths.clear()
                val paths: MutableList<String> = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT)
                imagePaths.addAll(paths)
                lunBanCompress(imagePaths)
            }
        }

    }

    abstract fun onSelectImagePath(path: MutableList<String>)
    fun lunBanCompress(path: MutableList<String>) {
        mCompressPaths.clear()
        var index = 0
        Luban.with(mContext)
            .load(path) // 传人要压缩的图片列表
            .ignoreBy(100)// 忽略不压缩图片的大小
            .setTargetDir(ImagerUtil.getDefaultPath(mContext))
            .setCompressListener(object : OnCompressListener {
                override fun onSuccess(file: File?) {
                    ++index
                    mCompressPaths.add(file!!.toString())
                    if (index == path.size) {
                        onSelectImagePath(mCompressPaths)
                    }
                    Log.i("鲁班压缩===", "压缩成功")
                }

                override fun onError(e: Throwable?) {
                    mContext.onError(e!!)
                    Log.i("鲁班压缩===", "压缩异常")
                }

                override fun onStart() {
                    Log.i("鲁班压缩===", "开始压缩")
                }
            }).launch()
    }
}