package com.amdev.lifehelper.databases.periodsdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "period_table")
data class Period (

    @ColumnInfo(name = "periodDay")
    var planDate: Int,

    @ColumnInfo(name = "periodMonth")
    var planMonth: Int,

    @ColumnInfo(name = "periodYear")
    var periodYear: Int

) {
    @PrimaryKey(autoGenerate = true)
    var periodID: Int? = null
}