package com.doufu.firstkotlin.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.classic.common.MultipleStatusView

import pub.devrel.easypermissions.EasyPermissions

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.base
 * @ClassName: BaseActivity
 * @Description: 基类
 * @Author: lixindong
 * @CreateDate: 2019/8/12 16:29
 */
abstract class BaseActivity :AppCompatActivity(),EasyPermissions.PermissionCallbacks{

    /**
     * 多种状态的 View 的切换
     */

    protected var mLayoutStatusView: MultipleStatusView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initData()
        initView()
        start()
        initListener()
    }

    private fun initListener(){
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }
    //如果你允许创建一个类的子类，需要使用open 修饰符来标示这个类，另外需要给每一个可以被重写的属性或者方法添加open 修饰符
    open  val mRetryClickListener: View.OnClickListener=View.OnClickListener {
        start()
    }
    /**
     * 开始请求
     */
    abstract fun start()
    /**
     * 初始化 View
     */
    abstract fun initView()
    /**
     * 初始化数据
     */
    abstract fun initData()
    /**
     *  加载布局
     */
    abstract fun layoutId(): Int


    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }
}