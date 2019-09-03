package com.doufu.firstkotlin.view.recyclerview

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.view.recyclerview
 * @ClassName: OnItemLongClickListener
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/9/3 15:57
 */
interface OnItemLongClickListener {
    fun onItemLongClick(obj:Any?,position:Int):Boolean
}