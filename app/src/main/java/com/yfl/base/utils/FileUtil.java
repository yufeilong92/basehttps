package com.yfl.base.utils;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 18:23
 * @Purpose :文件保存
 */
public class FileUtil {

    public static String saveImag(Context mContext, Bitmap bitmap) {
        FileOutputStream outputStream;
        String path = mContext.getExternalCacheDir().getAbsolutePath() + File.separator + "/" + System.currentTimeMillis() + ".png";
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            path = file.toString();
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public static String getFilePath(Context mContext){
        return mContext.getExternalCacheDir().getAbsolutePath()+File.separator+"/";
    }
}
