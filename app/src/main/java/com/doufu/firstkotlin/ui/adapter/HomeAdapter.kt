package com.doufu.firstkotlin.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.bgabanner.BGABanner
import com.doufu.firstkotlin.R

import com.doufu.firstkotlin.mvp.model.bean.HomeBean
import com.doufu.firstkotlin.view.recyclerview.ViewHolder
import com.doufu.firstkotlin.view.recyclerview.adapter.CommonAdapter
import io.reactivex.Observable

class HomeAdapter (context: Context,data: ArrayList<HomeBean.Issue.Item>): CommonAdapter<HomeBean.Issue.Item>(context,data,-1) {

    // banner 作为 RecyclerView 的第一项
    var bannerItemSize = 0

    companion object {

        private const val ITEM_TYPE_BANNER = 1    //Banner 类型
        private const val ITEM_TYPE_TEXT_HEADER = 2   //textHeader
        private const val ITEM_TYPE_CONTENT = 3    //item
    }

    fun setBannerSiae(count:Int){
        bannerItemSize=count
    }

    fun addItemData(itemList:ArrayList<HomeBean.Issue.Item>){
        this.mData.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when{
            position==0->
                ITEM_TYPE_BANNER
            mData[position+bannerItemSize-1].type=="textHeader"->
                ITEM_TYPE_TEXT_HEADER
            else->
                ITEM_TYPE_CONTENT
        }
    }

    override fun getItemCount(): Int {
        return when{
            mData.size>bannerItemSize->mData.size-bannerItemSize+1
            mData.isEmpty()->0
            else ->1
        }
    }
    override fun bindData(holder: ViewHolder, date: HomeBean.Issue.Item, position: Int) {
        when(getItemViewType(position)){
            ITEM_TYPE_CONTENT->{
//                toCollection(destination: C): C
//                集合所有元素添加到指定collection对象并返回
                val bannerItemData: ArrayList<HomeBean.Issue.Item> = mData.take(bannerItemSize).toCollection(ArrayList())
                val bannerFeedList=ArrayList<String>()
                val bannerTitleList=ArrayList<String>()
                Observable.fromIterable(bannerItemData)
                    .subscribe {list->
                        bannerFeedList.add(list.data?.cover?.feed?:"")
                        bannerTitleList.add(list.data?.title?:"")
                    }
//                with函数和前面的几个函数使用方式略有不同，
//                因为它不是以扩展的形式存在的。它是将某对象作为函数的参数，
//                在函数块内可以通过 this 指代该对象。返回值为函数块的最后一行或指定return表达式。
                with(holder){
                    getView<BGABanner>(R.id.banner).run {
                        setAutoPlayAble(bannerFeedList.size>1)
                        setData(bannerFeedList,bannerTitleList)
                        setAdapter{banner, _, feedImageUrl, position ->


                        }
                    }
                }
            }
        }
    }
    /**
     *  创建布局
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ITEM_TYPE_BANNER ->
                ViewHolder(inflaterView(R.layout.item_home_banner, parent))
            ITEM_TYPE_TEXT_HEADER ->
                ViewHolder(inflaterView(R.layout.item_home_header, parent))
            else ->
                ViewHolder(inflaterView(R.layout.item_home_content, parent))
        }
    }

    /**
     * 加载布局
     */
    private fun inflaterView(mLayoutId: Int, parent: ViewGroup): View {
        //创建view
        val view = mInflater?.inflate(mLayoutId, parent, false)
        return view ?: View(parent.context)
    }

}
