package com.doufu.firstkotlin.utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import java.lang.Error
import java.security.MessageDigest

/**
 *
 * @ProjectName: FirstKotlin
 * @Package: com.doufu.firstkotlin.utils
 * @ClassName: AppUtils
 * @Description: APP 相关的工具类
 * @Author: lixindong
 * @CreateDate: 2019/8/15 16:52
 */
class AppUtils private constructor(){//constructor用来开始一个主构造方法的声明。
    init {
        throw Error("Do not need instantiate!")
    }
    //companion object 修饰为伴生对象,伴生对象在类中只能存在一个，类似于java中的静态方法 Java 中使用类访问静态成员，静态方法。
    companion object{


        private val DEBUG = true
        private val TAG = "AppUtils"

        /**
         * 得到软件显示版本信息
         *
         * @param context 上下文
         * @return 当前版本信息
         */
        fun getVerName(context: Context): String {
            var  verName=""
            try {
                val packageName=context.packageName
                verName=context.packageManager
                    .getPackageInfo(packageName,0).versionName
            }catch (e:PackageManager.NameNotFoundException){
                e.printStackTrace()
            }
            return verName
        }
        /**
         * 获取应用签名
         *
         * @param context 上下文
         * @param pkgName 包名
         * @return 返回应用的签名
         */
        @SuppressLint("PackageManagerGetSignatures")
        fun getSign(context: Context, pkgName:String):String?{
           return try {
               @SuppressLint("PackageManagerGetSignatures") val pis=context.packageManager
                     .getPackageInfo(pkgName,
                         PackageManager.GET_SIGNATURES)
                hexDigest(pis.signatures[0].toByteArray())
            } catch (e: Exception) {
               e.printStackTrace()
               null
            }


        }
        /**
         * 将签名字符串转换成需要的32位签名
         *
         * @param paramArrayOfByte 签名byte数组
         * @return 32位签名字符串
         */
        private fun hexDigest(paramArrayOfByte: ByteArray): String {
            val hexDigits = charArrayOf(
                48.toChar(),
                49.toChar(),
                50.toChar(),
                51.toChar(),
                52.toChar(),
                53.toChar(),
                54.toChar(),
                55.toChar(),
                56.toChar(),
                57.toChar(),
                97.toChar(),
                98.toChar(),
                99.toChar(),
                100.toChar(),
                101.toChar(),
                102.toChar()
            )
            try {
                val localMessageDigest = MessageDigest.getInstance("MD5")
                localMessageDigest.update(paramArrayOfByte)
                val arrayOfByte = localMessageDigest.digest()
                val arrayOfChar = CharArray(32)
                var i = 0
                var j = 0
                while (true) {
                    if (i > 16) {
                        return String(arrayOfChar)
                    }
                    val k = arrayOfByte[i].toInt()
                    /**
                     * shl(bits) => 有符号向左移 (类似Java的<<)
                     *shr(bits) => 有符号向右移 (类似Java的>>)
                     *ushr(bits) => 无符号向右移 (类似Java的>>>)
                     *and(bits) => 位运算符 and (同Java中的按位与)
                     *or(bits) => 位运算符 or (同Java中的按位或)
                     *xor(bits) => 位运算符 xor (同Java中的按位异或)
                     *inv() => 位运算符 按位取反 (同Java中的按位取反)
                     */
                    arrayOfChar[j] = hexDigits[0xF and k.ushr(4)]
                    arrayOfChar[++j] = hexDigits[k and 0xF]
                    i++
                    j++
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return ""
        }
        /**
         * 获取设备的可用内存大小
         *
         * @param context 应用上下文对象context
         * @return 当前内存大小
         */
        fun getDeviceUsableMemory(context: Context):Int{
            val am=context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val mi=ActivityManager.MemoryInfo()
            am.getMemoryInfo(mi)
            // 返回当前系统的可用内存
            return (mi.availMem/(1024*1024)).toInt()
        }

        fun getMobileModel():String{
            var model:String?=Build.MODEL
            model=model?.trim{it<=' '}?:""
            return model
        }
        val sdkVersion:Int
            //get()方法
            get() =Build.VERSION.SDK_INT
    }
}