package com.yfl.base.vo

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.example.tsyc.vo
 * @Email : yufeilong92@163.com
 * @Time :2019/7/8 11:42
 * @Purpose :
 */
data class Login(
    val apk_file_url: String,
    val constraint: Boolean,
    val new_md5: String,
    val new_version: String,
    val target_size: String,
    val update: String,
    val update_log: String
)