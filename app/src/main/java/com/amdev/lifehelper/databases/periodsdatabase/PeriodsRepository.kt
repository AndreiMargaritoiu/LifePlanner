package com.amdev.lifehelper.databases.periodsdatabase

import android.app.Application
import androidx.lifecycle.LiveData

class PeriodsRepository (application: Application) {

    val database = PeriodsDatabase.getInstance(application)
    val periodsDao : PeriodsDao = database.periodsDao
    val allPeriods : LiveData<List<Period>> = periodsDao.getAllPeriods()

    fun insert(period: Period) {
        periodsDao.insert(period)
    }

    fun update(period: Period) {
        periodsDao.update(period)
    }

    fun delete(period: Period) {
        periodsDao.delete(period)
    }

    fun deleteAllPeriods() {
        periodsDao.deleteAllPeriods()
    }

    fun getAllPeriodsRep(): LiveData<List<Period>> {
        return allPeriods
    }
}