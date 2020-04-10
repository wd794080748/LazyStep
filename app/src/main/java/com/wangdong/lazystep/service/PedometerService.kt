package com.wangdong.lazystep.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Binder
import android.os.IBinder
import com.wangdong.lazystep.Bean.PedometerBean
import com.wangdong.lazystep.IPedometerService
import com.wangdong.lazystep.Listener.PedometerListener

class PedometerService : Service() {
    private lateinit var sendorManager:SensorManager
    private lateinit var pedometerBean:PedometerBean
    private lateinit var pedometerListener:PedometerListener
    override fun onCreate() {
        super.onCreate()
        sendorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        pedometerBean = PedometerBean()
        pedometerListener = PedometerListener(this,pedometerBean)
    }
    override fun onBind(intent: Intent): IBinder {
        return MyBinder()
    }
    inner class MyBinder:IPedometerService.Stub(){
        override fun getCalorie(): Double {
            TODO("Not yet implemented")
        }

        override fun getSetpsCount() {
            TODO("Not yet implemented")
        }

        override fun getServviceStatus(): Int {
            TODO("Not yet implemented")
        }

        override fun getInterval(): Int {
            TODO("Not yet implemented")
        }

        override fun getDistance(): Double {
            TODO("Not yet implemented")
        }

        override fun setSensitivity(sensitivity: Double) {
            TODO("Not yet implemented")
        }

        override fun resetCount() {
            TODO("Not yet implemented")
        }

        override fun startCount() {
            if(sendorManager != null && pedometerListener != null){
                var defaultSensor = sendorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                sendorManager.registerListener(pedometerListener,defaultSensor,SensorManager.SENSOR_DELAY_NORMAL)
                pedometerBean.startTime = System.currentTimeMillis()
            }
        }

        override fun setInterval(interval: Int) {
            TODO("Not yet implemented")
        }

        override fun getSensitivity(): Double {
            TODO("Not yet implemented")
        }

        override fun saveData() {
            TODO("Not yet implemented")
        }

        override fun getStartTimeStamp(): Long {
            TODO("Not yet implemented")
        }

        override fun stopCount() {
            TODO("Not yet implemented")
        }

    }

}
