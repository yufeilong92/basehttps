package com.yfl.base

import android.os.Bundle
import com.backpacker.yflLibrary.kotlin.LogUtil
import com.yfl.base.base.BaseSelectImageActivity
import kotlinx.android.synthetic.main.activity_select_imager.*

class SelectImagerActivity : BaseSelectImageActivity() {


    override fun setInitContentView(): Int {
        return R.layout.activity_select_imager
    }

    override fun onInitCreateView(savedInstanceState: Bundle?) {
        super.onInitCreateView(savedInstanceState)
        btn_select.setOnClickListener {
            toShowDialog(8)
        }

    }
    override fun onSelectImagePath(path: MutableList<String>) {
        for (item in path){
            LogUtil.i(item)
        }
    }
}
