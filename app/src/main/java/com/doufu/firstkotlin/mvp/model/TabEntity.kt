package com.doufu.firstkotlin.mvp.model

import android.graphics.drawable.Icon
import android.support.v7.widget.DialogTitle
import com.flyco.tablayout.listener.CustomTabEntity

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.mvp.model
 * @ClassName: TabEntity
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/8/27 17:34
 */
class TabEntity(var title: String,private var selectedIcon: Int,private  var unSelectedIcon:Int): CustomTabEntity{
    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabTitle(): String {
        return title
    }

}