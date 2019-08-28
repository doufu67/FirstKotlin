package com.doufu.firstkotlin.mvp.model

import com.doufu.firstkotlin.mvp.model.bean.HomeBean
import com.doufu.firstkotlin.net.RetrofitManager
import java.util.*

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.mvp.model
 * @ClassName: HomeModel
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/8/28 10:24
 */
class HomeModel {

    fun requestHomeData(num:Int):Observable<HomeBean>{
        return RetrofitManager.service.getFirstHomeData(num)
            .compose()

    }


}