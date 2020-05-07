package com.amdev.lifehelper.databases.utilsubsdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UtilsubsDao {

    @Insert
    fun insert(utilsub: Utilsub)

    @Update
    fun update(utilsub: Utilsub)

    @Delete
    fun delete(utilsub: Utilsub)

    @Query("SELECT * from utilsubs_table WHERE utilsubID = :key")
    fun get(key: Int): Utilsub

    @Query("DELETE FROM utilsubs_table")
    fun deleteAllUtilsubs()

    @Query("SELECT * FROM utilsubs_table ORDER BY deadline ASC")
    fun getAllUtilsubs(): LiveData<List<Utilsub>>
}
