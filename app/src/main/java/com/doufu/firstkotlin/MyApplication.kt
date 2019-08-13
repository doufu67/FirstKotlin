package com.doufu.firstkotlin

import android.app.Application

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin
 * @ClassName: MyApplication
 * @Description:
 * @Author: lixindong
 * @CreateDate: 2019/8/12 17:51
 */
class MyApplication : Application() {
    //companion object 修饰为伴生对象,伴生对象在类中只能存在一个，类似于java中的静态方法 Java 中使用类访问静态成员，静态方法。
    companion object{
        private val TAG="MyApplication"
    }
}