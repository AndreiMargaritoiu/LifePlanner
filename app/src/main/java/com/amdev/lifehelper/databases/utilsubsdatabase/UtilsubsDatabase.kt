package com.amdev.lifehelper.databases.utilsubsdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Utilsub::class], version = 1, exportSchema = false)
abstract class UtilsubsDatabase : RoomDatabase() {

    abstract val utilsubsDao: UtilsubsDao

    companion object {

        @Volatile
        private var INSTANCE: UtilsubsDatabase? = null

        fun getInstance(context: Context): UtilsubsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UtilsubsDatabase::class.java,
                        "utilsubs_database"
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