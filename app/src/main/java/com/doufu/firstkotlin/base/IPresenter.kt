package com.doufu.firstkotlin.base

import android.view.View

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.base
 * @ClassName: IPresenter
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/8/28 9:18
 */
interface IPresenter<in V:IBaseView> {
    fun attachView(mRootView: V)

    fun detachView()
}