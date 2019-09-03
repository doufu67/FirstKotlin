package com.doufu.firstkotlin.view.recyclerview

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.view.recyclerview
 * @ClassName: ViewHolder
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/9/3 15:19
 */
class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
    private var mView:SparseArray<View>?=null
    init {
        mView= SparseArray()
    }
    fun <T:View> getView(viewId:Int):T{
        var view:View?=mView?.get(viewId)
        if (view==null){
            view=itemView.findViewById(viewId)
            mView?.put(viewId,view)
        }
        return view as T
    }
    fun<T:ViewGroup> getViewGroup(viewId: Int):T{
        var view:View?=mView?.get(viewId)
        if (view==null){
            view=itemView.findViewById(viewId)
            mView?.put(viewId,view)
        }
        return view as T
    }

    @SuppressLint("SetTextI18n")
    fun setText(viewId: Int, text:CharSequence):ViewHolder{
        val view=getView<TextView>(viewId)
        view.text=""+text
        return this
    }
    fun setHintText(viewId: Int, text: CharSequence): ViewHolder {
        val view = getView<TextView>(viewId)
        view.hint = "" + text
        return this
    }

    fun setImageResource(viewId: Int,resId:Int):ViewHolder{
        val iv=getView<ImageView>(viewId)
        iv.setImageResource(resId)
        return this
    }
    fun setImagePath(viewId: Int,imageLoader:HolderImageLoader):ViewHolder{
        val iv=getView<ImageView>(viewId)
        imageLoader.loadImage(iv,imageLoader.path)
        return this
    }
    abstract class HolderImageLoader(val path: String) {

        /**
         * 需要去复写这个方法加载图片
         *
         * @param iv
         * @param path
         */
        abstract fun loadImage(iv: ImageView, path: String)
    }

    fun setViewVisibility(viewId: Int,visibility:Int):ViewHolder{
        getView<View>(viewId).visibility=visibility
        return this
    }

    fun setOnItemClickListener(listener: View.OnClickListener){
        itemView.setOnClickListener(listener)
    }

    fun setOnItemLongClickListener(listener: View.OnLongClickListener){
        itemView.setOnLongClickListener(listener)
    }
}