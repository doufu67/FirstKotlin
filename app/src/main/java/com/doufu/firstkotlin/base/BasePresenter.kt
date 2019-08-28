package com.doufu.firstkotlin.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.RuntimeException

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.base
 * @ClassName: BasePresenter
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/8/28 9:20
 */
open class BasePresenter<T : IBaseView> : IPresenter<T> {
    var mRootView: T? = null
        private set// // 但它的设值方法只在 example.kt 文件内可以访问
    private var compositeDisposable = CompositeDisposable()


    override fun attachView(mRootView: T) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        mRootView = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

    private val isViewAtttached: Boolean
        get() = mRootView != null

    fun checkViewAttached() {
        if (!isViewAtttached) throw MvpViewNotAttachedException()
    }

    fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}

private class MvpViewNotAttachedException internal constructor() :
    RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")
