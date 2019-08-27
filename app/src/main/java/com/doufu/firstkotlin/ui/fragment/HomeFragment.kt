package com.doufu.firstkotlin.ui.fragment

import android.os.Bundle
import android.support.v7.widget.DialogTitle
import com.doufu.firstkotlin.R
import com.doufu.firstkotlin.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.ui.fragment
 * @ClassName: HomeFragment
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/8/27 10:26
 */
class HomeFragment:BaseFragment() {

    private var mTitle:String?=null

    override fun lazyLoad() {
    }

    override fun getLayoutId(): Int= R.layout.activity_main

    override fun initView() {
    }
    companion object{
        fun getInstance(title:String):HomeFragment{
            val fragment=HomeFragment()
            val bundle=Bundle()
            fragment.arguments=bundle
            fragment.mTitle=title
            return fragment
        }
    }
}