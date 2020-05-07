package com.amdev.lifehelper.databases.plansdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plans_table")
data class Plan (

    @ColumnInfo(name = "planName")
    var planName: String,

    @ColumnInfo(name = "planDay")
    var planDate: Int,

    @ColumnInfo(name = "planMonth")
    var planMonth: Int,

    @ColumnInfo(name = "details")
    var details: String

) {
    @PrimaryKey(autoGenerate = true)
    var planID: Int? = null
}