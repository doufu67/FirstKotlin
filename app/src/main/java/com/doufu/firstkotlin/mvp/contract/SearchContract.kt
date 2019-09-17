package com.doufu.firstkotlin.mvp.contract

import com.doufu.firstkotlin.base.IBaseView
import com.doufu.firstkotlin.base.IPresenter

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.mvp.contract
 * @ClassName: SearchContract
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/9/17 15:45
 */
interface SearchContract {
    interface View :IBaseView{

    }
    interface  Presenter:IPresenter<View>{

    }
}