package com.doufu.firstkotlin

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates

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
    private var refWatcher: RefWatcher? =null
    //companion object 修饰为伴生对象,伴生对象在类中只能存在一个，类似于java中的静态方法 Java 中使用类访问静态成员，静态方法。
    companion object{
        private val TAG="MyApplication"
        var context:Context by Delegates.notNull()
            /**
              *public：默认修饰符，被其修饰的在任何位置都能访问
             *private：表示只在这个类(以及它的所有成员)之内可以访问
             *protected：在当前类及其子类内访问
             *internal：在同一模块内使用
             */
            private set  // 但它的设值方法只在 MyApplication.kt 文件内可以访问
        fun getRefWatcher(context: Context): RefWatcher? {
            val myApplication=context.applicationContext as MyApplication   //通常，如果转换是不可能的，转换操作符会抛出一个异常, Kotlin 中的不安全转换由中缀操作符 as
            return myApplication.refWatcher
        }
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext

    }
}