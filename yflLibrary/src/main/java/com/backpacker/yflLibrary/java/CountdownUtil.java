package com.backpacker.yflLibrary.java;

import android.content.Context;
import android.os.Handler;
import android.widget.Button;
import com.example.UtilsLibrary.R;

public class CountdownUtil {
    int recLen = 60;
    private Context mContext;
    Handler handler = new Handler();
    private static CountdownUtil util;
    private Button button;
    private int mColor = -1;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            recLen--;
            if (recLen == 0) {
                button.setText("重新发送");
                button.setEnabled(true);
                if (mColor == -1) {
                    button.setTextColor(mContext.getResources().getColor(R.color.red));
                } else {
                    button.setTextColor(mContext.getResources().getColor(mColor));
                }
                recLen = 60;
                return;
            }
            button.setTextColor(mContext.getResources().getColor(R.color.gray));
            button.setText("重发" + "(" + recLen + ")");
            handler.postDelayed(this, 1000);
        }
    };

    /**
     *
     * @param context 上下文
     * @param button  显示按钮
     */
    public void startTime(Context context, final Button button) {
        this.button = button;
        this.mContext = context;
        button.setEnabled(false);
        handler.postDelayed(runnable, 1000);
    }

    /**
     *
     * @param context 上下文
     * @param color 失败的颜色
     * @param button  按钮
     */
    public void startTime(Context context, int color, final Button button) {
        this.button = button;
        this.mContext = context;
        this.mColor = color;
        button.setEnabled(false);
        handler.postDelayed(runnable, 1000);
    }

    public static CountdownUtil getInstance() {
        if (util == null)
            util = new CountdownUtil();
        return util;
    }

    public void stop() {
        handler.postDelayed(null, 1000);
    }
}
