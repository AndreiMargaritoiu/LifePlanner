package com.amdev.lifehelper.databases.utilsubsdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "utilsubs_table")
data class Utilsub (

    @ColumnInfo(name = "provider")
    var provider: String,

    @ColumnInfo(name = "deadline")
    var deadline: Int,

    @ColumnInfo(name = "amount")
    var amount: Int

) {
    @PrimaryKey(autoGenerate = true)
    var utilsubID: Int? = null
}