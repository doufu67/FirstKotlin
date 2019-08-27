package com.doufu.firstkotlin.utils
import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast
import com.doufu.firstkotlin.MyApplication

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.utils
 * @ClassName: Extensions
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/8/27 18:20
 */
fun Fragment.showToast(context: String):Toast{
    val toast=Toast.makeText(this.activity?.applicationContext,context,Toast.LENGTH_SHORT)
    toast.show()
    return toast
}


fun Context.showToast(content: String):Toast{
    val toast=Toast.makeText(MyApplication.context,content,Toast.LENGTH_SHORT)
    toast.show()
    return toast
}