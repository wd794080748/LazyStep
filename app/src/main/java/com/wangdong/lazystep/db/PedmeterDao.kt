package com.wangdong.lazystep.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.wangdong.lazystep.Bean.PedometerBean


/**
 *@author wangdong
 *@descripiton
 *@date 2020/4/10 0:19
 */
class PedmeterDao(context:Context) {

    private var dbHelper:DBHelper = DBHelper(context,"pedometer.db",null,VERSION)
    private var writableDatabase:SQLiteDatabase
    val COLUMNS = arrayOf(
        "id",
        "stepCount",
        "calorie",
        "distance",
        "pace",
        "speed",
        "startTime",
        "lastStepTime",
        "createTime"
    )

    init {
        writableDatabase = dbHelper.writableDatabase

    }

    companion object{
        private const val VERSION = 1
        private var instance:PedmeterDao? =null
        const val TABLE_NAME = "pedometer"

        fun getInstance(contenxt:Context):PedmeterDao{
            if(instance == null){
                synchronized(this){
                    if(instance == null){
                        instance = PedmeterDao(contenxt)
                    }
                }
            }
            return instance!!
        }
    }

    fun writeToDatabase(bean: PedometerBean) {
        val values = ContentValues()
        values.put("stepCount", bean.getStepCount())
        values.put("calorie", bean.getCalorie())
        values.put("distance", bean.getDistance())
        values.put("pace", bean.getPace())
        values.put("speed", bean.getSpeed())
        values.put("startTime", bean.getStartTime())
        values.put("lastStepTime", bean.getLastStepTime())
        values.put("createTime", bean.getCreateTime())
        writableDatabase.insert(TABLE_NAME, null, values)
        writableDatabase.close()
    }


    fun getByCreateTime(createTime: Long): PedometerBean? {
        var cursor: Cursor? = null
        val bean = PedometerBean()
        cursor = writableDatabase.rawQuery(
            "select * from " +TABLE_NAME + " where createTime=" + createTime.toString(),
            null
        )
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToNext()) {
                val id: Int = cursor.getInt(cursor.getColumnIndex(COLUMNS.get(0)))
                val stepCount: Int = cursor.getInt(cursor.getColumnIndex(COLUMNS.get(1)))
                val calorie: Double =
                    cursor.getDouble(cursor.getColumnIndex(COLUMNS.get(2)))
                val distance: Double =
                    cursor.getDouble(cursor.getColumnIndex(COLUMNS.get(3)))
                val pace: Int = cursor.getInt(cursor.getColumnIndex(COLUMNS.get(4)))
                val speed: Double =
                    cursor.getDouble(cursor.getColumnIndex(COLUMNS.get(5)))
                val startTime: Long =
                    cursor.getLong(cursor.getColumnIndex(COLUMNS.get(6)))
                val lastStepTime: Long =
                    cursor.getLong(cursor.getColumnIndex(COLUMNS.get(7)))
                val cTime: Long =
                    cursor.getLong(cursor.getColumnIndex(COLUMNS.get(8)))
                bean.setId(id)
                bean.setStepCount(stepCount)
                bean.setCalorie(calorie)
                bean.setDistance(distance)
                bean.setPace(pace)
                bean.setSpeed(speed)
                bean.setStartTime(startTime)
                bean.setLastStepTime(lastStepTime)
                bean.setCreateTime(cTime)
            }
        }
        cursor.close()
        writableDatabase.close()
        return bean
    }

    fun getFromDatabase(): ArrayList<PedometerBean>? {
        val pageSize = 20
        val offVal = 0
        var cursor: Cursor? = null
        cursor = writableDatabase.query(
           TABLE_NAME, null, null, null, null, null,
            "createTime desc limit $pageSize offset $offVal",
            null
        )
        if (cursor != null && cursor.getCount() > 0) {
            val data: ArrayList<PedometerBean> = ArrayList<PedometerBean>()
            while (cursor.moveToNext()) {
                val bean = PedometerBean()
                val id: Int = cursor.getInt(cursor.getColumnIndex(COLUMNS.get(0)))
                val stepCount: Int = cursor.getInt(cursor.getColumnIndex(COLUMNS.get(1)))
                val calorie: Double =
                    cursor.getDouble(cursor.getColumnIndex(COLUMNS.get(2)))
                val distance: Double =
                    cursor.getDouble(cursor.getColumnIndex(COLUMNS.get(3)))
                val pace: Int = cursor.getInt(cursor.getColumnIndex(COLUMNS.get(4)))
                val speed: Double =
                    cursor.getDouble(cursor.getColumnIndex(COLUMNS.get(5)))
                val startTime: Long =
                    cursor.getLong(cursor.getColumnIndex(COLUMNS.get(6)))
                val lastStepTime: Long =
                    cursor.getLong(cursor.getColumnIndex(COLUMNS.get(7)))
                val createTime: Long =
                    cursor.getLong(cursor.getColumnIndex(COLUMNS.get(8)))
                bean.setId(id)
                bean.setStepCount(stepCount)
                bean.setCalorie(calorie)
                bean.setDistance(distance)
                bean.setPace(pace)
                bean.setSpeed(speed)
                bean.setStartTime(startTime)
                bean.setLastStepTime(lastStepTime)
                bean.setCreateTime(createTime)
                data.add(bean)
            }
            cursor.close()
            writableDatabase.close()
            return data
        }
        return null
    }

    fun updateToDatabase(values: ContentValues?, createTime: Long) {
        writableDatabase.update(
            TABLE_NAME,
            values,
            "createTime=?",
            arrayOf(createTime.toString())
        )
        writableDatabase.close()
    }
}