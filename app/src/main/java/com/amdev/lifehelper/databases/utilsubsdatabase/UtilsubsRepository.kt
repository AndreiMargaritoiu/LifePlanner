package com.amdev.lifehelper.databases.utilsubsdatabase

import android.app.Application
import androidx.lifecycle.LiveData

class UtilsubsRepository (application: Application) {

    val database = UtilsubsDatabase.getInstance(application)
    val utilsubsDao : UtilsubsDao = database.utilsubsDao
    val allUtilsubs : LiveData<List<Utilsub>> = utilsubsDao.getAllUtilsubs()

    fun insert(utilsub: Utilsub) {
        utilsubsDao.insert(utilsub)
    }

    fun update(utilsub: Utilsub) {
        utilsubsDao.update(utilsub)
    }

    fun delete(utilsub: Utilsub) {
        utilsubsDao.delete(utilsub)
    }

    fun deleteAllUtilsubs() {
        utilsubsDao.deleteAllUtilsubs()
    }

    fun getAllutilsubs(): LiveData<List<Utilsub>> {
        return allUtilsubs
    }
}