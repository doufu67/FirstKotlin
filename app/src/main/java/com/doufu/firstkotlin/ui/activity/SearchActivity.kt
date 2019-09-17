package com.doufu.firstkotlin.ui.activity

import android.graphics.Typeface
import android.os.Build
import android.support.annotation.RequiresApi
import android.transition.Transition
import android.transition.TransitionInflater
import com.doufu.firstkotlin.MyApplication
import com.doufu.firstkotlin.R
import com.doufu.firstkotlin.base.BaseActivity
import com.doufu.firstkotlin.mvp.contract.SearchContract
import com.doufu.firstkotlin.mvp.presenter.SearchPresenter

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.ui.activity
 * @ClassName: SearchActivity
 * @Description:
 * @Author: lixindong
 * @CreateDate: 2019/9/17 15:44
 */
class SearchActivity :BaseActivity(),SearchContract.View{
    private var mTextTypeface: Typeface? = null
    private val mPresenter by lazy { SearchPresenter() }
    init {
        mPresenter.attachView(this)
        //细黑简体字体
        mTextTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }
    override fun start() {
    }

    override fun initView() {
    }

    override fun initData() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            setUpEnterAnimation()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setUpEnterAnimation() {
        val transition=TransitionInflater.from(this)
            .inflateTransition(R.transition.arc_motion)
        window.sharedElementEnterTransition=transition
        transition.addListener(object :Transition.TransitionListener{
            override fun onTransitionEnd(transition: Transition?) {

            }

            override fun onTransitionResume(transition: Transition?) {
            }

            override fun onTransitionPause(transition: Transition?) {
            }

            override fun onTransitionCancel(transition: Transition?) {
            }

            override fun onTransitionStart(transition: Transition?) {
            }

        })
    }

    private fun animateRevealShow(){

    }
    override fun layoutId(): Int= R.layout.activity_search

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}