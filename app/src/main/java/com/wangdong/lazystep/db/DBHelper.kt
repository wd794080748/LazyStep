package com.wangdong.lazystep.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.wangdong.lazystep.db.PedmeterDao.Companion.TABLE_NAME

/**
 *@author wangdong
 *@descripiton
 *@date 2020/4/10 0:15
 */
class DBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        //execSQL用于执行SQL语句
        db?.execSQL("CREATE TABLE " + TABLE_NAME + " (id integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL," +
                "stepCount integer," +
                "calorie Double," +
                "distance Double DEFAULT NULL," +
                "pace INTEGER," +
                "speed Double," +
                "startTime Timestamp DEFAULT NULL," +
                "lastStepTime Timestamp  DEFAULT NULL," +
                "createTime Timestamp   DEFAULT NULL)");
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}