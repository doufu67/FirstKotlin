package com.doufu.firstkotlin.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.rx.scheduler
 * @ClassName: IoMainScheduler
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/8/29 9:41
 */
class IoMainScheduler<T> :BaseScheduler<T>(Schedulers.io(),AndroidSchedulers.mainThread())