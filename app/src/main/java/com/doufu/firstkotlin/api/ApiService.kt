package com.doufu.firstkotlin.api

import com.doufu.firstkotlin.mvp.model.bean.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.api
 * @ClassName: ApiService
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/8/28 10:52
 */
interface ApiService {
    /**
     * 首页精选
     */
    @GET("v2/feed?")
    fun getFirstHomeData(@Query("num") num:Int): Observable<HomeBean>
}