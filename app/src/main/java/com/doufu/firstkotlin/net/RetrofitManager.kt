package com.doufu.firstkotlin.net

import com.doufu.firstkotlin.MyApplication
import com.doufu.firstkotlin.api.ApiService
import com.doufu.firstkotlin.api.UrlConstant
import com.doufu.firstkotlin.utils.AppUtils
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.sql.Time
import java.util.concurrent.TimeUnit

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.net
 * @ClassName: RetrofitManager
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/8/28 10:51
 */
object RetrofitManager {
    /*LazyThreadSafetyMode.SYNCHRONIZED：初始化属性时会有双重锁检查，保证该值只在一个线程中计算，并且所有线程会得到相同的值。
    LazyThreadSafetyMode.PUBLICATION：多个线程会同时执行，初始化属性的函数会被多次调用，但是只有第一个返回的值被当做委托属性的值。
    LazyThreadSafetyMode.NONE：没有双重锁检查，不应该用在多线程下。
    lazy() 默认情况下会指定 LazyThreadSafetyMode.SYNCHRONIZED，这可能会造成不必要线程安全的开销，应该根据实际情况，指定合适的model来避免不需要的同步锁。
*/
    val service: ApiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        getRetrofit().create(ApiService::class.java)

    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(UrlConstant.BASE_URL)
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private fun getOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val cacheFile = File(MyApplication.context.cacheDir, "cache")
        val cache = Cache(cacheFile, 1024 * 1024 * 50)
        return OkHttpClient.Builder()
            .addInterceptor(addQueryParameterInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .cache(cache)
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .build()
    }

    private fun addQueryParameterInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val request: Request
            val modifiedUrl = originalRequest.url().newBuilder()
                .addQueryParameter("udid", "d2807c895f0348a180148c9dfa6f2feeac0781b5")
                .addQueryParameter("deviceModel", AppUtils.getMobileModel())
                .build()
            request = originalRequest.newBuilder().url(modifiedUrl).build()
            chain.proceed(request)
        }

    }
}