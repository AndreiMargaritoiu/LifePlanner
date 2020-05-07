package com.amdev.lifehelper.databases.plansdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Plan::class], version = 1, exportSchema = false)
abstract class PlansDatabase : RoomDatabase() {

    abstract val plansDao : PlansDao

    companion object {

        @Volatile
        private var INSTANCE: PlansDatabase? = null

        fun getInstance(context: Context): PlansDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlansDatabase::class.java,
                        "plans_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}