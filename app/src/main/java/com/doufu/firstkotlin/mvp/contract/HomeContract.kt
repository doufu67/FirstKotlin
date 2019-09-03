package com.doufu.firstkotlin.mvp.contract

import com.doufu.firstkotlin.base.IBaseView
import com.doufu.firstkotlin.mvp.model.bean.HomeBean

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.mvp.contract
 * @ClassName: HomeContract
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/8/28 9:43
 */
interface HomeContract {
    interface View:IBaseView{
        /**
         * 设置第一次请求的数据
         */
        fun setHomeData(homeBean: HomeBean)
        /**
         * 显示错误信息
         */
        fun showError(msg: String,errorCode:Int)
    }
    interface Presenter{
        fun requestHomeData(num: Int)



    }
}