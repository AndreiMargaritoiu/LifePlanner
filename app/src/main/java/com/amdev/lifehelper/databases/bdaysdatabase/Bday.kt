package com.amdev.lifehelper.databases.bdaysdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bdays_table")
data class Bday (

    @ColumnInfo(name = "personName")
    var personName: String,

    @ColumnInfo(name = "bdayDate")
    var bdayDate: Int,

    @ColumnInfo(name = "bdayMonth")
    var bdayMonth: Int,

    @ColumnInfo(name = "details")
    var details: String

) {
    @PrimaryKey(autoGenerate = true)
    var bdayID: Int? = null
}