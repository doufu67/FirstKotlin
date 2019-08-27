package com.doufu.firstkotlin.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import com.doufu.firstkotlin.R
import com.doufu.firstkotlin.base.BaseActivity
import com.doufu.firstkotlin.mvp.model.TabEntity
import com.doufu.firstkotlin.ui.fragment.HomeFragment
import com.doufu.firstkotlin.utils.showToast
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val mTitles = arrayOf("每日精选", "发现", "热门", "我的")

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(
        R.mipmap.ic_home_normal,
        R.mipmap.ic_discovery_normal,
        R.mipmap.ic_hot_normal,
        R.mipmap.ic_mine_normal
    )
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(
        R.mipmap.ic_home_selected,
        R.mipmap.ic_discovery_selected,
        R.mipmap.ic_hot_selected,
        R.mipmap.ic_mine_selected
    )

    private val mTabEntities=ArrayList<CustomTabEntity>()
    private var mHomeFragment: HomeFragment? =null
    private var mDiscoveryFragment: HomeFragment? = null
    private var mHotFragment: HomeFragment? = null
    private var mMineFragment: HomeFragment? = null

    //默认为0
    private var mIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {

        if(savedInstanceState!=null){
            mIndex=savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        tab_layout.currentTab=mIndex
        switchFragment(mIndex)
    }

    private fun switchFragment(position: Int) {
        val transaction=supportFragmentManager.beginTransaction()
        hideFragmenrt(transaction)
        when(position){
            0
            ->mHomeFragment?.let {
                transaction.show(it)
            }?:HomeFragment.getInstance(mTitles[position]).let {
            mHomeFragment=it
            transaction.add(R.id.fl_container,it,"home")

        }
            else->{

            }
        }
        mIndex=position
        tab_layout.currentTab=mIndex
        transaction.commitAllowingStateLoss()

    }

    private fun hideFragmenrt(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }

    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        if(tab_layout!=null){
            outState.putInt("currTabIndex", mIndex)
        }
    }
    private var mExitTime:Long=0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode==KeyEvent.KEYCODE_BACK){

            if (System.currentTimeMillis().minus(mExitTime)<=2000){
                finish()
            }else{
                mExitTime=System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
    private fun initTab() {
        (0 until mTitles.size)
            .mapTo(mTabEntities){TabEntity(mTitles[it],mIconSelectIds[it],mIconUnSelectIds[it])}
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object :OnTabSelectListener{
            override fun onTabSelect(position: Int) {
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {
            }

        })
    }


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
