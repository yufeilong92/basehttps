package com.yfl.base.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.backpacker.yflLibrary.java.flyn.Eyes
import com.yfl.base.R

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Eyes.translucentStatusBar(this,false)

    }
}
