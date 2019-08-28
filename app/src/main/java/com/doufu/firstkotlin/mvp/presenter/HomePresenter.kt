package com.doufu.firstkotlin.mvp.presenter

import com.doufu.firstkotlin.base.BasePresenter
import com.doufu.firstkotlin.mvp.contract.HomeContract
import com.doufu.firstkotlin.mvp.model.HomeModel

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.mvp.presenter
 * @ClassName: HomePresenter
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/8/28 9:48
 */
class HomePresenter :BasePresenter<HomeContract.View>(),HomeContract.Presenter{

    private val homeModel by lazy{HomeModel()}

    override fun requestHomeData(num: Int) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable=homeModel

    }

}