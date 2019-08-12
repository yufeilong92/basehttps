package com.backpacker.yflLibrary.java;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

public class ShareUtil {


    private volatile static ShareUtil singleton;

    private ShareUtil() {
    }

    public static ShareUtil getSingleton() {
        if (singleton == null) {
            synchronized (ShareUtil.class) {
                if (singleton == null) {
                    singleton = new ShareUtil();
                }
            }
        }
        return singleton;
    }

    public void shareText(Context mContext, String txt) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        // 比如发送文本形式的数据内容
// 指定发送的内容
        sendIntent.putExtra(Intent.EXTRA_TEXT, txt);
// 指定发送内容的类型
        sendIntent.setType("text/plain");
        sendIntent = Intent.createChooser(sendIntent, "分享");
        mContext.startActivity(sendIntent);
    }

    public void shareImage(Context context, Bitmap mbitmap) {
        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), mbitmap, null, null));
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");//设置分享内容的类型
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent = Intent.createChooser(intent, "分享");
        context.startActivity(intent);
    }
}
