package com.doufu.firstkotlin.net.exception

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.net.exception
 * @ClassName: ErrorStatus
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/9/2 9:44
 */
object ErrorStatus {
    /**
     * 响应成功
     * @JvmField
     *指示Kotlin编译器不为该属性生成getter/setter，
     *并将其作为字段公开。如果用来修饰val变量，就和const关键字的功能一样了
     */
    @JvmField
    val SUCCESS = 0

    /**
     * 未知错误
     */
    @JvmField
    val UNKNOWN_ERROR = 1002

    /**
     * 服务器内部错误
     */
    @JvmField
    val SERVER_ERROR = 1003

    /**
     * 网络连接超时
     */
    @JvmField
    val NETWORK_ERROR = 1004

    /**
     * API解析异常（或者第三方数据结构更改）等其他异常
     */
    @JvmField
    val API_ERROR = 1005

}