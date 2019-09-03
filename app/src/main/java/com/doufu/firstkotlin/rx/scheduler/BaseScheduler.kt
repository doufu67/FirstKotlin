package com.doufu.firstkotlin.rx.scheduler

import io.reactivex.*
import org.reactivestreams.Publisher

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.rx.scheduler
 * @ClassName: BaseScheduler
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/8/28 11:25
 */
open class BaseScheduler<T> protected constructor(private  val subscribeOnScheduler: Scheduler,
                                             private val observeOnScheduler: Scheduler):ObservableTransformer<T,T> ,
    //Transformer，顾名思义是转换器的意思
    SingleTransformer<T, T>,
    MaybeTransformer<T, T>,
    CompletableTransformer,
    FlowableTransformer<T, T>
{
    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
    }

    override fun apply(upstream: Maybe<T>): MaybeSource<T> {
        return upstream.subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)    }

    override fun apply(upstream: Completable): CompletableSource {
        return upstream.subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)    }

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}