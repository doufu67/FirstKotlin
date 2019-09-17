package com.doufu.firstkotlin.mvp.presenter

import com.doufu.firstkotlin.base.BasePresenter
import com.doufu.firstkotlin.mvp.contract.HomeContract
import com.doufu.firstkotlin.mvp.model.HomeModel
import com.doufu.firstkotlin.mvp.model.bean.HomeBean

import com.doufu.firstkotlin.net.exception.ExceptionHandle

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
    private var bannerHomeBean:HomeBean?=null
    private var nextPageUrl: String? =null

    override fun requestHomeData(num: Int) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable=homeModel.requestHomeData(num)
            .flatMap{
                homeBean->
                val bannerItemList=homeBean.issueList[0].itemList
                bannerItemList.filter { item ->
                    item.type=="banner2"|| item.type=="horizontalScrollCard"
                }.forEach{item->
                bannerItemList.remove(item)
                }
                bannerHomeBean=homeBean
                homeModel.loadMoreData(homeBean.nextPageUrl)
            }.subscribe({homeBean->
                mRootView?.apply {
                    dismissLoading()
                    nextPageUrl=homeBean.nextPageUrl
                    val newBannerItemList=homeBean.issueList[0].itemList
                    newBannerItemList.filter { item ->
                        item.type=="banner2"||item.type=="horizontalScrollCard"
                    }.forEach{ item ->
                        //移除 item
                        newBannerItemList.remove(item)
                    }
                    bannerHomeBean!!.issueList[0].count=bannerHomeBean!!.issueList[0].itemList.size
                    bannerHomeBean?.issueList!![0].itemList.addAll(newBannerItemList)
                    setHomeData(bannerHomeBean!!)
                }

            },{
                t->
                mRootView?.apply {
                    dismissLoading()
                    showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                }
            })
        addSubscription(disposable)

    }

    override fun loadMoreData() {
        val disposable=nextPageUrl?.let {
            homeModel.loadMoreData(it)
                .subscribe({homeBean->
                    mRootView?.apply {
                        val newItemList=homeBean.issueList[0].itemList
                        newItemList.filter {item ->
                            item.type=="banner2"||item.type=="horizontalScrollCard"
                        }.forEach {item->
                            newItemList.remove(item)
                        }
                        nextPageUrl=homeBean.nextPageUrl
                        setMoreData(newItemList)
                    }

                },{t->
                    mRootView?.apply {
                        showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                    }
                })
        }
        if (disposable!=null){
            addSubscription(disposable)
        }
    }


}