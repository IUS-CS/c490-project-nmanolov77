package edu.ius.c490.spacexnotifier

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME = "LaunchesDB"
val TABLE_NAME = "Launches"
val COL_MISSION = "mission"
val COL_ROCKET_NAME = "rocket_name"
val COL_LAUNCH_TIME_UTC = "launch_time"
val COL_LAUNCH_SITE = "launch_site"
val COL_ID = "id"

class DataBaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE" + TABLE_NAME + " (" +
                COL_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_MISSION + "VARCHAR(256)," +
                COL_ROCKET_NAME + "VARCHAR(256)," +
                COL_LAUNCH_TIME_UTC + "VARCHAR(256)," +
                COL_LAUNCH_SITE + "VARCHAR(256)";
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
