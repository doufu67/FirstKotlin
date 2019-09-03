package com.doufu.firstkotlin.rx.scheduler

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.rx.scheduler
 * @ClassName: SchedulerUtils
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/8/28 11:24
 */
object SchedulerUtils {
    fun<T> ioToMain():IoMainScheduler<T>{
        return IoMainScheduler()
    }
}