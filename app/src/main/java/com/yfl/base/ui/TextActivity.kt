package com.yfl.base.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.backpacker.yflLibrary.view.PayPsdInputView
import com.backpacker.yflLibrary.view.pswedit.GridPasswordView
import com.backpacker.yflLibrary.view.pswedit.PasswordType
import com.yfl.base.R
import com.yfl.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_text.*

class TextActivity : BaseActivity() {
    override fun setInitContentView(): Int {
        return  R.layout.activity_text
    }

    override fun onInitCreateView(savedInstanceState: Bundle?) {
        onpwdchangerdTest()
    }
    fun onpwdchangerdTest() {
        psw_visibility_switcher.setOnClickListener {
            gpv_passwordType.togglePasswordVisibility()
        }
        pswtype_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2) {
                    0 -> {
                        gpv_passwordType.setPasswordType(PasswordType.NUMBER)
                    }
                    1 -> {
                        gpv_passwordType.setPasswordType(PasswordType.TEXT)
                    }
                    2 -> {
                        gpv_passwordType.setPasswordType(PasswordType.TEXTVISIBLE)
                    }
                    3 -> {
                        gpv_passwordType.setPasswordType(PasswordType.TEXTWEB)
                    }
                }

            }
        }
        var isFirst = true
        var firstPwd: String? = null
        gpv_normail_twice.setOnPasswordChangedListener(
            object : GridPasswordView.OnPasswordChangedListener {
                override fun onTextChanged(psw: String?) {
                    if (psw!!.length == 6 && isFirst) {
                        gpv_normail_twice.clearPassword()
                        isFirst = false
                        firstPwd = psw
                    } else if (psw.length == 6 && !isFirst) {
                        if (psw == firstPwd) {
                            Log.d("MainActivity", "The password is: $psw")
                        } else {
                            Log.d("MainActivity", "password doesn't match the previous one, try again!")
                            gpv_normail_twice.clearPassword()
                            isFirst = true
                        }
                    }
                }

                override fun onInputFinish(psw: String?) {
                }

            })

        c_edit_text_view_text.setOnFinishListener {
            Toast.makeText(this,"请求失败", Toast.LENGTH_SHORT).show()
        }
        c_edit_text_view_circle.setOnFinishListener {
            Toast.makeText(this,"请求失败", Toast.LENGTH_SHORT).show()
        }
        c_edit_text_view.setOnFinishListener {
            Toast.makeText(this,"请求失败", Toast.LENGTH_SHORT).show()
        }
        new_pawss.setComparePassword(object : PayPsdInputView.onPasswordListener{
            override fun onDifference(oldPsd: String?, newPsd: String?) {
                println(oldPsd+"///"+newPsd)
            }

            override fun onEqual(psd: String?) {
                println(psd)
            }

            override fun inputFinished(inputPsd: String?) {
                println(inputPsd)

            }

        })
        new_paws.setComparePassword(object : PayPsdInputView.onPasswordListener{
            override fun onDifference(oldPsd: String?, newPsd: String?) {
                println(oldPsd+"///"+newPsd)
            }

            override fun onEqual(psd: String?) {
                println(psd)
            }

            override fun inputFinished(inputPsd: String?) {
                println(inputPsd)
            }

        })
    }
}
