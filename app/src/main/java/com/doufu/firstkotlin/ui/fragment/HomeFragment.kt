package com.doufu.firstkotlin.ui.fragment

import android.os.Bundle
import com.doufu.firstkotlin.R
import com.doufu.firstkotlin.base.BaseFragment
import com.doufu.firstkotlin.mvp.contract.HomeContract
import com.doufu.firstkotlin.mvp.model.bean.HomeBean
import com.doufu.firstkotlin.mvp.presenter.HomePresenter
import com.doufu.firstkotlin.ui.adapter.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.ui.fragment
 * @ClassName: HomeFragment
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/8/27 10:26
 */
class HomeFragment:BaseFragment() ,HomeContract.View{

    private var mHomeAdapter: HomeAdapter? = null
    private val mPresenter by lazy { HomePresenter() }

    private var mTitle:String?=null

    private var isRefresh=false

    private var num:Int=1

    override fun lazyLoad() {
    }

    override fun getLayoutId(): Int= R.layout.fragment_home

    override fun initView() {
        mPresenter.attachView(this)
        mRefreshLayout.setEnableHeaderTranslationContent(true)
        mRefreshLayout.setOnRefreshListener{
            isRefresh=true
            mPresenter.requestHomeData(num)
        }
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

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
    override fun setHomeData(homeBean: HomeBean) {
        mLayoutStatusView?.showContent()

    }

    override fun showError(msg: String, errorCode: Int) {
    }
}