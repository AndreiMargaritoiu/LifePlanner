package com.amdev.lifehelper.databases.periodsdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Period::class], version = 1, exportSchema = false)
abstract class PeriodsDatabase : RoomDatabase() {

    abstract val periodsDao : PeriodsDao

    companion object {

        @Volatile
        private var INSTANCE: PeriodsDatabase? = null

        fun getInstance(context: Context): PeriodsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PeriodsDatabase::class.java,
                        "bdays_database"
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