package com.yfl.base.base


/**
 * @Author : YFL  is Creating a porject in as
 * @Package com.example.myapplication.intereface
 * @Email : yufeilong92@163.com
 * @Time :2019/7/3 10:20
 * @Purpose :
 */
object DataMessageVo {
    val mHttp: String = "https://raw.githubusercontent.com/"
    val mImager: String = "http://www.kuaidi100.com/"
    var PASSWORD: ByteArray = "akmd".toByteArray();
    val BASE_HTTP = "http://tsyc.jiefutong.net"
    //注册
    val Code_Register: String = "1"
    //登录,
    val Code_Login: String = "2"
    //找回密码
    val Code_Retrieve: String = "3"
    //设置支付密码
    val Code_Pay: String = "4"
    //修改手机号
    val Code_revise: String = "5"
    //招聘绑定手机号
    val Code_advertise: String = "6"


}