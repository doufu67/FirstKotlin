package com.doufu.firstkotlin.ui.fragment

import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.doufu.firstkotlin.R
import com.doufu.firstkotlin.base.BaseFragment
import com.doufu.firstkotlin.mvp.contract.HomeContract
import com.doufu.firstkotlin.mvp.model.bean.HomeBean
import com.doufu.firstkotlin.mvp.presenter.HomePresenter
import com.doufu.firstkotlin.net.exception.ErrorStatus
import com.doufu.firstkotlin.ui.adapter.HomeAdapter
import com.doufu.firstkotlin.utils.StatusBarUtil
import com.doufu.firstkotlin.utils.showToast
import com.scwang.smartrefresh.header.MaterialHeader
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*

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

    private var mMaterialHeader: MaterialHeader? =null
    private val mPresenter by lazy { HomePresenter() }

    private var mTitle:String?=null

    private var isRefresh=false

    private var loadingMore = false


    private var num:Int=1


    override fun lazyLoad() {
        mPresenter.requestHomeData(num)
    }

    override fun getLayoutId(): Int= R.layout.fragment_home

    override fun initView() {
        mPresenter.attachView(this)
        mRefreshLayout.setEnableHeaderTranslationContent(true)
        mRefreshLayout.setOnRefreshListener{
            isRefresh=true
            mPresenter.requestHomeData(num)
        }
        mMaterialHeader=mRefreshLayout.refreshHeader as MaterialHeader?
        mMaterialHeader?.setShowBezierWave(true)
        mRefreshLayout.setPrimaryColorsId(R.color.color_light_black, R.color.color_title_bg)
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState==RecyclerView.SCROLL_STATE_IDLE){
                    val childCount=mRecyclerView.childCount
                    val itemCount =mRecyclerView.layoutManager.itemCount
                    val firstVisibleItem=(mRecyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if(firstVisibleItem+childCount==itemCount){
                        if (!loadingMore){
                            loadingMore=true
                            mPresenter.loadMoreData()
                        }
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentVisisbleItemPosition=linearLayoutManager.findFirstVisibleItemPosition()
                if(currentVisisbleItemPosition==0){
                    toolbar.setBackgroundColor(getColor(R.color.color_translucent))
                    iv_search.setImageResource(R.mipmap.ic_action_search_white)
                    tv_header_title.text=""
                }else{
                    if(mHomeAdapter?.mData!!.size>1){
                        toolbar.setBackgroundColor(getColor(R.color.color_title_bg))
                        iv_search.setImageResource(R.mipmap.ic_action_search_black)
                        val itemList=mHomeAdapter!!.mData
                        val item=itemList[currentVisisbleItemPosition+mHomeAdapter!!.bannerItemSize-1]
                        if(item.type=="textHeader"){
                            tv_header_title.text=item.data?.text

                        }else{
                            tv_header_title.text=simpleDateFormat.format(item.data?.date)
                        }
                    }
                }
            }
        })
        iv_search.setOnClickListener { openSearchActivity() }
        mLayoutStatusView=multipleStatusView
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it,toolbar) }
    }

    private fun openSearchActivity(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            val options=activity?.let { ActivityOptionsCompat.makeSceneTransitionAnimation(it,iv_search,iv_search.transitionName) }

        }else{

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
    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
    }

    private val simpleDateFormat by lazy {
        SimpleDateFormat("-MM. dd, 'Brunch' -", Locale.ENGLISH)
    }

    override fun showLoading() {
        if(!isRefresh){
            isRefresh=false
            mLayoutStatusView?.showLoading()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun dismissLoading() {
        mRefreshLayout.finishRefresh()
    }
    override fun setHomeData(homeBean: HomeBean) {
        mLayoutStatusView?.showContent()
        mHomeAdapter=activity?.let { HomeAdapter(it,homeBean.issueList[0].itemList) }
        mHomeAdapter?.setBannerSiae(homeBean.issueList[0].count)
        mRecyclerView.adapter=mHomeAdapter
        mRecyclerView.layoutManager=linearLayoutManager
        mRecyclerView.itemAnimator=DefaultItemAnimator()

    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
        if(errorCode== ErrorStatus.NETWORK_ERROR){
            mLayoutStatusView?.showNoNetwork()
        }else{
            mLayoutStatusView?.showError()
        }
    }

    fun getColor(colorId: Int): Int {
        return resources.getColor(colorId)
    }
    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {
        loadingMore=false
        mHomeAdapter?.addItemData(itemList)
    }
}