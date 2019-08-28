package com.doufu.firstkotlin.mvp.contract

import com.doufu.firstkotlin.base.IBaseView

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

    }
    interface Presenter{
        fun requestHomeData(num: Int)


    }
}