package com.amdev.lifehelper.databases.periodsdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.amdev.lifehelper.databases.plansdatabase.Plan

@Dao
interface PeriodsDao {

    @Insert
    fun insert(period: Period)

    @Update
    fun update(period: Period)

    @Delete
    fun delete(period: Period)

    @Query("SELECT * from period_table WHERE periodID = :key")
    fun get(key: Int): Period

    @Query("DELETE FROM period_table")
    fun deleteAllPeriods()

    @Query("SELECT * FROM period_table ORDER BY periodYear, periodMonth, periodDay DESC")
    fun getAllPeriods(): LiveData<List<Period>>
}