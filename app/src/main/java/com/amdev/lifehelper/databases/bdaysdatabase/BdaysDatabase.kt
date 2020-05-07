package com.amdev.lifehelper.databases.bdaysdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Bday::class], version = 2, exportSchema = false)
abstract class BdaysDatabase : RoomDatabase() {

    abstract val bdaysDao : BdaysDao

    companion object {

        @Volatile
        private var INSTANCE: BdaysDatabase? = null

        fun getInstance(context: Context): BdaysDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BdaysDatabase::class.java,
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