package com.doufu.firstkotlin.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.classic.common.MultipleStatusView
import com.doufu.firstkotlin.MyApplication
import pub.devrel.easypermissions.EasyPermissions

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.base
 * @ClassName: BaseFragment
 * @Description: java类作用描述
 * @Author: lixindong
 * @CreateDate: 2019/8/27 10:26
 */
abstract class BaseFragment:Fragment(),EasyPermissions.PermissionCallbacks {
    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = false
    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false
    /**
     * 多种状态的 View 的切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(),null)
    }
    /**
     * 加载布局
     * @LayoutRes 表明该参数、变量或者函数返回值应该是一个 layout 布局文件类型的资源
     */
    @LayoutRes
    abstract fun getLayoutId():Int

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser){
            lazyLoadDataIfPrepared()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare=true
        initView()
        lazyLoadDataIfPrepared()
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }
    /**
     * 初始化 ViewI
     */
    abstract fun initView()


    override fun onDestroy() {
        super.onDestroy()
        activity?.let { MyApplication.getRefWatcher(it)?.watch(activity) }
    }

    private fun lazyLoadDataIfPrepared(){
        if(userVisibleHint&&isViewPrepare&&!hasLoadData){
            lazyLoad()
            hasLoadData=true
        }
    }

    open val mRetryClickListener:View.OnClickListener=View.OnClickListener {
        lazyLoad()
    }
    /**
     * 懒加载
     */

    abstract fun lazyLoad()

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }
}