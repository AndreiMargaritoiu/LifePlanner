package com.amdev.lifehelper.databases.bdaysdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BdaysDao {

    @Insert
    fun insert(bday : Bday)

    @Update
    fun update(bday : Bday)

    @Delete
    fun delete(bday : Bday)

    @Query("SELECT * from bdays_table WHERE bdayID = :key")
    fun get(key: Int): Bday

    @Query("DELETE FROM bdays_table")
    fun deleteAllBdays()

    @Query("SELECT * FROM bdays_table ORDER BY bdayMonth, bdayDate ASC")
    fun getAllBdays(): LiveData<List<Bday>>
}
