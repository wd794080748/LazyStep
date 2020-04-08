package com.wangdong.lazystep.application

import android.app.Activity
import android.app.Application
import java.lang.Exception
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