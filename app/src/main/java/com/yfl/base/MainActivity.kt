package com.yfl.base

import android.os.Bundle
import com.yfl.base.base.BaseActivity

/**
 * @Title:  主界面
 * @Package com.example.tsyc
 * @Description: 主界面
 * @author: L-BackPacker
 * @date:   2019/7/6 15:01
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019/7/6
 */

class MainActivity : BaseActivity() {

    override fun setInitContentView(): Int {
        return R.layout.activity_main
    }

    override fun onInitCreateView(savedInstanceState: Bundle?) {

    }


}
