package com.yfl.base.manage

import androidx.fragment.app.FragmentActivity
import com.backpacker.yflLibrary.base.StartFragmentManger
import com.yfl.base.SelectImagerActivity

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.example.tsyc.manage
 * @Email : yufeilong92@163.com
 * @Time :2019/7/5 10:33
 * @Purpose :跳转管理
 */
open class ResultFragmentTo(var context: FragmentActivity) : StartFragmentManger(context) {
     fun toSelectCammer(){
         jumpTo(SelectImagerActivity::class.java)
     }
}