package com.amdev.lifehelper.databases.plansdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.amdev.lifehelper.databases.bdaysdatabase.Bday

@Dao
interface PlansDao {

    @Insert
    fun insert(plan : Plan)

    @Update
    fun update(plan : Plan)

    @Delete
    fun delete(plan : Plan)

    @Query("SELECT * from plans_table WHERE planID = :key")
    fun get(key: Int): Plan

    @Query("DELETE FROM plans_table")
    fun deleteAllPlans()

    @Query("SELECT * FROM plans_table ORDER BY planMonth, planDay ASC")
    fun getAllPlans(): LiveData<List<Plan>>
}