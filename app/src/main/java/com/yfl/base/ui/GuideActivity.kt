package com.yfl.base.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yfl.base.base.BaseActivity
import com.yfl.base.R
import com.yfl.base.adapter.GuideAdapter
import com.yfl.base.fragment.GuideFragment
import kotlinx.android.synthetic.main.activity_guide.*

/**
 * @Title:
 * @Package
 * @Description: todo
 * @author: L-BackPacker
 * @date:   2019/7/6 12:41
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019/7/6
 */
class GuideActivity : BaseActivity() {
    override fun setInitContentView(): Int {
        return R.layout.activity_guide
    }

    override fun onInitCreateView(savedInstanceState: Bundle?) {
        initFragment()
    }

    fun initFragment() {
        val listFragment = mutableListOf<Fragment>()
        for (postion in 0 until 3) {
            val fragment = GuideFragment.newInstance("$postion", "")
            listFragment.add(fragment)
        }
        val adapter = GuideAdapter(fm = supportFragmentManager, list = listFragment)
        viewpager_content.adapter = adapter
    }


}
