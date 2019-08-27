package com.doufu.firstkotlin.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.doufu.firstkotlin.MyApplication
import com.doufu.firstkotlin.R
import com.doufu.firstkotlin.base.BaseActivity
import com.doufu.firstkotlin.utils.AppUtils
import kotlinx.android.synthetic.main.activity_splash.*
import pub.devrel.easypermissions.EasyPermissions

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.ui.activity
 * @ClassName: SplashActivity
 * @Description: 启动页
 * @Author: lixindong
 * @CreateDate: 2019/8/15 15:47
 */
class SplashActivity : BaseActivity() {

    private var textTypeface: Typeface? = null
    private var descTypeFace: Typeface? = null
    private var alphaAnimation: AlphaAnimation? = null

    //    // 所以kotlin增加了一个新的关键字init用来处理类的初始化问题，init模块中的内容可以直接使用构造函数的参数。
    init {
        textTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/Lobster-1.4.otf")
        descTypeFace = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }

    override fun layoutId(): Int = R.layout.activity_splash
    override fun initData() {
    }

    override fun start() {
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        tv_app_name.typeface = textTypeface
        tv_splash_desc.typeface = descTypeFace
        tv_version_name.text = "v${AppUtils.getVerName(MyApplication.context)}"
        //渐变展示启动屏
        alphaAnimation = AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
                redirectTo()
            }

            override fun onAnimationEnd(animation: Animation?) {
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
        checkPermisson()
    }

    private fun redirectTo() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun checkPermisson() {
        val perms =
            arrayOf(android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        EasyPermissions.requestPermissions(this, "KotlinMvp应用需要以下权限，请允许", 0, *perms)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if (requestCode == 0) {
            if (perms.contains(android.Manifest.permission.READ_PHONE_STATE)
                && perms.contains(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ) {
                if (alphaAnimation != null) {
                    iv_web_icon.startAnimation(alphaAnimation)
                }
            }
        }
    }
}