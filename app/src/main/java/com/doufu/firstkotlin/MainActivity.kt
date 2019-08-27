package com.doufu.firstkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.doufu.firstkotlin.base.BaseActivity
import com.flyco.tablayout.listener.CustomTabEntity

class MainActivity : BaseActivity() {
    private val mTitles = arrayOf("每日精选", "发现", "热门", "我的")

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected)

    private val mTabEntities=ArrayList<CustomTabEntity>()
    private val



    override fun start() {
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }


}
