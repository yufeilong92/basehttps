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

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            recLen--;
            if (recLen ==0) {
                button.setText("重新发送");
                button.setEnabled(true);
                button.setTextColor(mContext.getResources().getColor(R.color.red));
                recLen = 60;
                return;
            }
            button.setTextColor(mContext.getResources().getColor(R.color.gray));
            button.setText("重发" + "(" + recLen + ")");
            handler.postDelayed(this, 1000);
        }
    };

    public void startTime(Context context, final Button button) {
        this.button = button;
        this.mContext = context;
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
