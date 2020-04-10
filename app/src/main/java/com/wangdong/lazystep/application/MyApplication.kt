package com.wangdong.lazystep.application

import android.app.Activity
import android.app.Application
import android.media.audiofx.DynamicsProcessing
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.wangdong.lazystep.BuildConfig
import kotlin.system.exitProcess


/**
 *@author wangdong
 *@descripiton
 *@date 2020/4/7 17:06
 */
class MyApplication :Application() {
    var activityList:ArrayList<Activity> = ArrayList()
    override fun onCreate() {
        super.onCreate()
        instance = this
        initLog()
    }

    private fun initLog() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .tag("LazyStep") // 每个日志的全局标记。默认PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(object:AndroidLogAdapter(formatStrategy){
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })

    }

    companion object{
        private  var instance:MyApplication? = null
         fun getInstance():MyApplication{
            return  instance!!
        }

    }

    public fun addActivity(activity:Activity){
        if(activity != null){
            activityList.add(activity)
        }
    }

    public fun removeActivity(activiy:Activity){
        if(activiy != null && activityList.size>0 && activityList.contains(activiy)){
            activityList.remove(activiy)
        }
    }

    public fun exitApp(){
        try {

        }catch (e:Exception){
            activityList.forEach{
                it.finish()
            }
            activityList.clear()
        }finally {
            exitProcess(0)
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }



}